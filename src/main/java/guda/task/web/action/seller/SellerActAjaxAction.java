package guda.task.web.action.seller;

import guda.mvc.form.Form;
import guda.task.biz.*;
import guda.task.biz.enums.DirectionEnum;
import guda.task.biz.enums.TaskAcceptStatusEnum;
import guda.task.biz.enums.TaskStatusEnum;
import guda.task.biz.vo.AjaxResponce;
import guda.task.common.security.AppContexHolder;
import guda.task.common.util.CommonResultCode;
import guda.task.common.util.ErrorCode;
import guda.task.dao.domain.*;
import guda.task.web.action.BaseAjaxAction;
import guda.tools.web.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by foodoon on 2014/12/25.
 */
@Controller
public class SellerActAjaxAction extends BaseAjaxAction {

    @Autowired
    private TaskListBiz taskListBiz;
    @Autowired
    private TaskPropsBiz taskPropsBiz;
    @Autowired
    private TaobaoItemBiz taobaoItemBiz;
    @Autowired
    private TaskAcceptBiz taskAcceptBiz;
    @Autowired
    private AccountBiz accountBiz;
    @Autowired
    private AccountDetailBiz accountDetailBiz;

    @Form
    @RequestMapping(value = "seller/act/doConfirm.json", method = RequestMethod.POST)
    public void doConfirmSuccess(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String id = request.getParameter("id");
        TaskListDO taskListDO = taskListBiz.queryById(Convert.toLong(id));
        if (taskListDO == null) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        if (taskListDO.getStatus().intValue() != TaskStatusEnum.SIGN.getValue()) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.STATUS_ERROR)), response);
            return;
        }
        TaobaoItemDO taobaoItemDO = taobaoItemBiz.queryByItemId(Convert.toLong(taskListDO.getItemId()));
        if (taobaoItemDO == null) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        if (!taobaoItemDO.getTaobaoUserId().equals(AppContexHolder.getContext().getUserDO().getTaobaoUserId())) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.PERSSION_ERROR)), response);
            return;
        }
        TaskAcceptDO taskAcceptDO = taskAcceptBiz.queryByTaskIdAndStatusInSing(taskListDO.getId());
        if (taskAcceptDO == null) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        try {
            long totalFee = AccountHelper.count(taskListDO);
            taskAcceptDO.setStatus(TaskAcceptStatusEnum.SUCCESS.getValue());
            taskAcceptBiz.update(taskAcceptDO);
            taskListDO.setStatus(TaskStatusEnum.SUCCESS.getValue());
            taskListBiz.update(taskListDO);
            AccountDO sellerAccount =accountBiz.queryByUserId(taskListDO.getSellerId());
            AccountDO buyerAccount =accountBiz.queryByUserId(taskAcceptDO.getUserId());
            AccountDetailDO accountDetailDO = new AccountDetailDO();
            accountDetailDO.setAccountFrom(sellerAccount.getId());
            accountDetailDO.setAccountId(buyerAccount.getId());
            accountDetailDO.setAmount(totalFee);
            accountDetailDO.setGmtCreated(new Date());
            accountDetailDO.setTaskId(taskListDO.getId());
            accountDetailDO.setDirection(DirectionEnum.IN.getValue());
            accountDetailBiz.create(accountDetailDO);

            accountDetailDO = new AccountDetailDO();
            accountDetailDO.setAccountFrom(buyerAccount.getId());
            accountDetailDO.setAccountId(sellerAccount.getId());
            accountDetailDO.setAmount(totalFee);
            accountDetailDO.setGmtCreated(new Date());
            accountDetailDO.setTaskId(taskListDO.getId());
            accountDetailDO.setDirection(DirectionEnum.OUT.getValue());
            accountDetailBiz.create(accountDetailDO);
            buyerAccount.setAmount(buyerAccount.getAmount() + totalFee);
            accountBiz.update(buyerAccount);
            //卖家账户解冻并支出
            sellerAccount.setFreeze(sellerAccount.getFreeze()-totalFee);
            accountBiz.update(sellerAccount);
            ajaxReturn(new AjaxResponce(true), response);
            return;
        } catch (Exception e) {
            log.error("", e);
        }
        ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.UNKOWN_ERROR)), response);

    }



    @Form
    @RequestMapping(value = "seller/act/doBreak.json", method = RequestMethod.POST)
    public void doBreak(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String id = request.getParameter("id");
        TaskListDO taskListDO = taskListBiz.queryById(Convert.toLong(id));
        if (taskListDO == null) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        if (taskListDO.getStatus().intValue() <TaskStatusEnum.ACCEPT.getValue() || taskListDO.getStatus().intValue()>=TaskStatusEnum.BREAK.getValue()) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.STATUS_ERROR)), response);
            return;
        }
        TaobaoItemDO taobaoItemDO = taobaoItemBiz.queryByItemId(Convert.toLong(taskListDO.getItemId()));
        if (taobaoItemDO == null) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        if (!taobaoItemDO.getTaobaoUserId().equals(AppContexHolder.getContext().getUserDO().getTaobaoUserId())) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.PERSSION_ERROR)), response);
            return;
        }
        try {
            //解冻任务资金
            //校验是否有冻结保证金
            AccountDO sellerAccount =accountBiz.queryByUserId(taskListDO.getSellerId());
            long totalFee = AccountHelper.count(taskListDO);
            AccountDetailDO accountDetailDO = new AccountDetailDO();
            accountDetailDO.setAccountFrom(sellerAccount.getId());
            accountDetailDO.setAccountId(sellerAccount.getId());
            accountDetailDO.setAmount(totalFee);
            accountDetailDO.setGmtCreated(new Date());
            accountDetailDO.setTaskId(taskListDO.getId());
            accountDetailDO.setDirection(DirectionEnum.UNFREEZE.getValue());
            accountDetailBiz.create(accountDetailDO);
            //卖家账户解冻
            sellerAccount.setFreeze(sellerAccount.getFreeze()-totalFee);
            sellerAccount.setAmount(sellerAccount.getAmount() + totalFee);
            accountBiz.update(sellerAccount);

            taskListDO.setStatus(TaskStatusEnum.BREAK.getValue());
            taskListBiz.update(taskListDO);
            ajaxReturn(new AjaxResponce(true), response);
            return;
        } catch (Exception e) {
            log.error("", e);
        }
        ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.UNKOWN_ERROR)), response);

    }
    @Form
    @RequestMapping(value = "seller/act/doCancel.json", method = RequestMethod.POST)
    public void doCancel(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String id = request.getParameter("id");
        TaskListDO taskListDO = taskListBiz.queryById(Convert.toLong(id));
        if (taskListDO == null) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        if (taskListDO.getStatus().intValue() != TaskStatusEnum.INIT.getValue()&& taskListDO.getStatus().intValue() != TaskStatusEnum.PUBLISH.getValue()) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.STATUS_ERROR)), response);
            return;
        }
        TaobaoItemDO taobaoItemDO = taobaoItemBiz.queryByItemId(Convert.toLong(taskListDO.getItemId()));
        if (taobaoItemDO == null) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        if (!taobaoItemDO.getTaobaoUserId().equals(AppContexHolder.getContext().getUserDO().getTaobaoUserId())) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.PERSSION_ERROR)), response);
            return;
        }
        try {
            //解冻任务资金
            AccountDO sellerAccount =accountBiz.queryByUserId(taskListDO.getSellerId());
            long totalFee = AccountHelper.count(taskListDO);
            AccountDetailDO accountDetailDO = new AccountDetailDO();
            accountDetailDO.setAccountFrom(sellerAccount.getId());
            accountDetailDO.setAccountId(sellerAccount.getId());
            accountDetailDO.setAmount(totalFee);
            accountDetailDO.setGmtCreated(new Date());
            accountDetailDO.setTaskId(taskListDO.getId());
            accountDetailDO.setDirection(DirectionEnum.UNFREEZE.getValue());
            accountDetailBiz.create(accountDetailDO);
            //卖家账户解冻
            sellerAccount.setFreeze(sellerAccount.getFreeze()-totalFee);
            sellerAccount.setAmount(sellerAccount.getAmount() + totalFee);
            accountBiz.update(sellerAccount);

            taskListDO.setStatus(TaskStatusEnum.INIT.getValue());
            taskListBiz.update(taskListDO);
            ajaxReturn(new AjaxResponce(true), response);
            return;
        } catch (Exception e) {
            log.error("", e);
        }
        ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.UNKOWN_ERROR)), response);

    }
}
