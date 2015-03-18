package guda.task.web.action.seller;

import guda.mvc.form.Form;
import guda.task.biz.*;
import guda.task.biz.enums.DirectionEnum;
import guda.task.biz.enums.SearchEnum;
import guda.task.biz.enums.TaskStatusEnum;
import guda.task.biz.query.TaskQuery;
import guda.task.biz.vo.TaskVO;
import guda.task.biz.vo.TaskViewForSellerVO;
import guda.task.common.security.AppContexHolder;
import guda.task.common.util.CommonResultCode;
import guda.task.common.util.ErrorCode;
import guda.task.common.util.TaskProperty;
import guda.task.dao.domain.*;
import guda.task.web.action.seller.form.ActForm;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.Convert;
import guda.tools.web.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

/**
 * Created by foodoon on 2014/12/21.
 */
@Controller
public class SellerActAction {

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

    @RequestMapping(value = "seller/act/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, ActForm actForm,
                         BindingResult result) {
        String id = request.getParameter("id");
        TaobaoItemDO taobaoItemDO = taobaoItemBiz.queryById(Convert.toLong(id));
        if (taobaoItemDO == null) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }
        modelMap.addAttribute("item", taobaoItemDO);
        return "seller/act/create.vm";
    }

    @RequestMapping(value = "seller/act/ing.htm", method = RequestMethod.GET)
    public String ing(HttpServletRequest request, ModelMap modelMap) {
        TaskQuery taskQuery = new TaskQuery();
        modelMap.put("query", taskQuery);
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        taskQuery.setPageNo(pageId);
        taskQuery.setPageSize(pageSize);
        taskQuery.setSellerId(AppContexHolder.getContext().getUserDO().getId());
        BizResult bizResult = taskListBiz.listIng(taskQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "seller/act/ing.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "seller/act/his.htm", method = RequestMethod.GET)
    public String his(HttpServletRequest request, ModelMap modelMap) {
        TaskQuery taskQuery = new TaskQuery();
        modelMap.put("query", taskQuery);
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        taskQuery.setPageNo(pageId);
        taskQuery.setPageSize(pageSize);
        taskQuery.setSellerId(AppContexHolder.getContext().getUserDO().getId());
        BizResult bizResult = taskListBiz.listHis(taskQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "seller/act/his.vm";
        } else {
            return "common/error.vm";
        }

    }



    @RequestMapping(value = "seller/act/init.htm", method = RequestMethod.GET)
    public String initList(HttpServletRequest request, ModelMap modelMap) {
        TaskQuery taskQuery = new TaskQuery();
        modelMap.put("query", taskQuery);
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        taskQuery.setPageNo(pageId);
        taskQuery.setPageSize(pageSize);
        taskQuery.setSellerId(AppContexHolder.getContext().getUserDO().getId());
        taskQuery.setStatus(TaskStatusEnum.INIT.getValue());
        BizResult bizResult = taskListBiz.list(taskQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "seller/act/init.vm";
        } else {
            return "common/error.vm";
        }

    }

    @Form
    @RequestMapping(value = "seller/act/doPublish.htm", method = RequestMethod.GET)
    public String doPublish(HttpServletRequest request, ModelMap modelMap) {
        String id = request.getParameter("id");
        TaskListDO taskListDO = taskListBiz.queryById(Convert.toLong(id));
        if (taskListDO == null) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }
        if (taskListDO.getStatus().intValue() != TaskStatusEnum.INIT.getValue()) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.STATUS_ERROR));
            return "500.vm";
        }
        TaobaoItemDO taobaoItemDO = taobaoItemBiz.queryById(Convert.toLong(taskListDO.getItemId()));
        if (taobaoItemDO == null) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }
        if (!taobaoItemDO.getTaobaoUserId().equals(AppContexHolder.getContext().getUserDO().getTaobaoUserId())) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.PERSSION_ERROR));
            return "500.vm";
        }
        //检查并冻结金额
        long freeze = AccountHelper.count(taskListDO);
        AccountDO accountDO = accountBiz.queryByUserId(AppContexHolder.getContext().getUserDO().getId());
        if(accountDO == null){
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.ACCOUNT_NOT_EXIST));
            return "500.vm";
        }
        if(accountDO.getAmount()<freeze){
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.ACCOUNT_NOT_ENOUGH));
            return "500.vm";
        }
        accountDO.setAmount(accountDO.getAmount() - freeze);
        accountDO.setFreeze(accountDO.getFreeze() + freeze);
        accountBiz.update(accountDO);
        AccountDetailDO accountDetailDO = new AccountDetailDO();
        accountDetailDO.setAmount(freeze);
        accountDetailDO.setAccountFrom(accountDO.getId());
        accountDetailDO.setAccountId(accountDO.getId());
        accountDetailDO.setTaskId(taskListDO.getId());
        accountDetailDO.setGmtCreated(new Date());
        accountDetailDO.setDirection(DirectionEnum.FREEZE.getValue());
        accountDetailBiz.create(accountDetailDO);
        taskListDO.setStatus(TaskStatusEnum.PUBLISH.getValue());
        taskListBiz.update(taskListDO);
        modelMap.addAttribute("item", taobaoItemDO);
        return "seller/act/init.vm";
    }

    @Form
    @RequestMapping(value = "seller/act/doCreate.htm", method = RequestMethod.POST)
    public String doCreateTask(HttpServletRequest request, ModelMap modelMap, @Valid ActForm actForm,
                           BindingResult result, Map<String, Object> model) {
        if (result.hasErrors()) {
            return "seller/act/create.vm";
        }
        TaobaoItemDO taobaoItemDO = taobaoItemBiz.queryById(Convert.toLong(actForm.getId()));
        if (taobaoItemDO == null) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }
        if (!taobaoItemDO.getTaobaoUserId().equals(AppContexHolder.getContext().getUserDO().getTaobaoUserId())) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.PERSSION_ERROR));
            return "500.vm";
        }
        long fee = Convert.toLong(actForm.getAmountFee());
        if (fee == 0) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.REQUEST_ERROR));
            return "500.vm";
        }
        TaskListDO taskListDO = new TaskListDO();
        taskListDO.setItemId(actForm.getId());
        taskListDO.setAmountFee(fee * 100);
        taskListDO.setAmountPay(taobaoItemDO.getPrice());
        taskListDO.setStatus(TaskStatusEnum.INIT.getValue());
        taskListDO.setComment(actForm.getComment());
        taskListDO.setSellerName(AppContexHolder.getContext().getUserDO().getTaobaoUserNick());
        taskListDO.setSellerId(AppContexHolder.getContext().getUserDO().getId());
        taskListDO.setDescript(actForm.getDescript());
        taskListDO.setItemName(taobaoItemDO.getTitle());
        taskListDO.setItemPic(taobaoItemDO.getPicUrl());
        taskListDO.setGmtCreated(new Date());
        taskListDO.setItemUrl(getItemUrl(taobaoItemDO));

        BizResult bizResult = taskListBiz.create(taskListDO);
        long taskId = Convert.toLong(bizResult.data.get("id"));
        createTaskProperty(taskId, actForm);
        if (bizResult.success) {
            return "redirect:/seller/act/init.htm";
        } else {
            return "common/error.vm";
        }

    }


    @Form
    @RequestMapping(value = "seller/act/doSave.htm", method = RequestMethod.POST)
    public String doSave(HttpServletRequest request, ModelMap modelMap, @Valid ActForm actForm,
                           BindingResult result, Map<String, Object> model) {
        if (result.hasErrors()) {
            return "seller/act/edit.vm";
        }
        TaskListDO taskListDO = taskListBiz.queryById(Convert.toLong(actForm.getId()));
        if (taskListDO == null) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }
        if (!taskListDO.getSellerId().equals(AppContexHolder.getContext().getUserDO().getId())) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.PERSSION_ERROR));
            return "500.vm";
        }
        if(taskListDO.getStatus().intValue()!=TaskStatusEnum.INIT.getValue()){
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.STATUS_ERROR));
            return "500.vm";
        }
        long fee = Convert.toLong(actForm.getAmountFee());
        if (fee == 0) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.REQUEST_ERROR));
            return "500.vm";
        }
        taskListDO.setAmountFee(actForm.getAmountFee()*100);
        taskListDO.setStatus(TaskStatusEnum.INIT.getValue());
        taskListDO.setComment(actForm.getComment());
        taskListDO.setDescript(actForm.getDescript());
        BizResult bizResult = taskListBiz.update(taskListDO);
        taskPropsBiz.deleteByTaskId(taskListDO.getId());
        createTaskProperty(taskListDO.getId(), actForm);
        if (bizResult.success) {
            return "redirect:/seller/act/index.htm";
        } else {
            return "common/error.vm";
        }

    }


    @Form
    @RequestMapping(value = "seller/act/doDelete.htm", method = RequestMethod.GET)
    public String doDelete(HttpServletRequest request, ModelMap modelMap, Map<String, Object> model) {
        String id = request.getParameter("id");
        TaskListDO taskListDO = taskListBiz.queryById(Convert.toLong(id));
        if (taskListDO == null) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }
        if (taskListDO.getStatus().intValue() != TaskStatusEnum.INIT.getValue()) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.STATUS_ERROR));
            return "500.vm";
        }
        TaobaoItemDO taobaoItemDO = taobaoItemBiz.queryById(Convert.toLong(taskListDO.getItemId()));
        if (taobaoItemDO == null) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }
        if (!taobaoItemDO.getTaobaoUserId().equals(AppContexHolder.getContext().getUserDO().getTaobaoUserId())) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.PERSSION_ERROR));
            return "500.vm";
        }

        BizResult delete = taskListBiz.delete(taskListDO.getId());
        if (delete.success) {
            return "redirect:/seller/act/init.htm";
        } else {
            return "500.vm";
        }

    }

    @RequestMapping(value = "seller/act/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        String id = request.getParameter("id");
        TaskListDO taskListDO = taskListBiz.queryById(Convert.toLong(id));
        if (taskListDO == null) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }
        TaskViewForSellerVO taskVO = new TaskViewForSellerVO();
        taskVO.setTaskListDO(taskListDO);
        Map<String, String> stringStringMap = taskPropsBiz.queryByTaskId(taskListDO.getId());
        taskVO.setSearchUrl(stringStringMap.get(TaskProperty.SEARCH_URL));
        taskVO.setSearchKey(stringStringMap.get(TaskProperty.SEARCH_KEY));
        taskVO.setSearchResultLocation("大概第" + stringStringMap.get(TaskProperty.SEARCH_RESULT_PAGE) + "页第" + stringStringMap.get(TaskProperty.SEARCH_RESULT_ROW) + "行第"
                + stringStringMap.get(TaskProperty.SEARCH_RESULT_COL) + "个");
        modelMap.addAttribute("taskVO", taskVO);
        return "seller/act/detail.vm";
    }

    @RequestMapping(value = "seller/act/copy.htm", method = RequestMethod.GET)
    public String copy(ActForm actForm,
                       BindingResult result, HttpServletRequest request, ModelMap modelMap) {
        String id = request.getParameter("id");
        TaskListDO taskListDO = taskListBiz.queryById(Convert.toLong(id));
        if (taskListDO == null) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }
        actForm.setItemName(taskListDO.getItemName());
        actForm.setDescript(taskListDO.getDescript());
        actForm.setAmountFee(taskListDO.getAmountFee() / 100);
        actForm.setComment(taskListDO.getComment());
        actForm.setAmountPay(taskListDO.getAmountPay());
        actForm.setItemId(taskListDO.getItemId());
        actForm.setItemPic(taskListDO.getItemPic());
        actForm.setItemUrl(taskListDO.getItemUrl());

        Map<String, String> stringStringMap = taskPropsBiz.queryByTaskId(taskListDO.getId());
        actForm.setSearchKey(stringStringMap.get(TaskProperty.SEARCH_KEY));
        actForm.setSearchUrl(String.valueOf(SearchEnum.getValueByName(stringStringMap.get(TaskProperty.SEARCH_URL))));
        actForm.setSearchResultPage(stringStringMap.get(TaskProperty.SEARCH_RESULT_PAGE));
        actForm.setSearchResultRow(stringStringMap.get(TaskProperty.SEARCH_RESULT_ROW));
        actForm.setSearchResultCol(stringStringMap.get(TaskProperty.SEARCH_RESULT_COL));
        TaobaoItemDO taobaoItemDO = taobaoItemBiz.queryByItemId(Convert.toLong(taskListDO.getItemId()));
        if (taobaoItemDO == null) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }
        if (!taobaoItemDO.getTaobaoUserId().equals(AppContexHolder.getContext().getUserDO().getTaobaoUserId())) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.PERSSION_ERROR));
            return "500.vm";
        }
        modelMap.addAttribute("item", taobaoItemDO);
        return "seller/act/copy.vm";
    }


    @RequestMapping(value = "seller/act/edit.htm", method = RequestMethod.GET)
    public String edit(ActForm actForm,
                       BindingResult result, HttpServletRequest request, ModelMap modelMap) {
        String id = request.getParameter("id");
        TaskListDO taskListDO = taskListBiz.queryById(Convert.toLong(id));
        if (taskListDO == null) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }
        actForm.setId(String.valueOf(taskListDO.getId()));
        actForm.setItemName(taskListDO.getItemName());
        actForm.setDescript(taskListDO.getDescript());
        actForm.setAmountFee(taskListDO.getAmountFee() / 100);
        actForm.setComment(taskListDO.getComment());
        actForm.setAmountPay(taskListDO.getAmountPay());
        actForm.setItemId(taskListDO.getItemId());
        actForm.setItemPic(taskListDO.getItemPic());
        actForm.setItemUrl(taskListDO.getItemUrl());

        Map<String, String> stringStringMap = taskPropsBiz.queryByTaskId(taskListDO.getId());
        actForm.setSearchKey(stringStringMap.get(TaskProperty.SEARCH_KEY));
        actForm.setSearchUrl(String.valueOf(SearchEnum.getValueByName(stringStringMap.get(TaskProperty.SEARCH_URL))));
        actForm.setSearchResultPage(stringStringMap.get(TaskProperty.SEARCH_RESULT_PAGE));
        actForm.setSearchResultRow(stringStringMap.get(TaskProperty.SEARCH_RESULT_ROW));
        actForm.setSearchResultCol(stringStringMap.get(TaskProperty.SEARCH_RESULT_COL));
        TaobaoItemDO taobaoItemDO = taobaoItemBiz.queryByItemId(Convert.toLong(taskListDO.getItemId()));
        if (taobaoItemDO == null) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }
        if (!taobaoItemDO.getTaobaoUserId().equals(AppContexHolder.getContext().getUserDO().getTaobaoUserId())) {
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.PERSSION_ERROR));
            return "500.vm";
        }
        modelMap.addAttribute("item", taobaoItemDO);
        modelMap.addAttribute("taskList", taskListDO);
        return "seller/act/edit.vm";
    }

    private void createTaskProperty(long taskId, ActForm actForm) {
        TaskPropsDO taskPropsDO = new TaskPropsDO();
        taskPropsDO.setTaskId(taskId);
        taskPropsDO.setRequireKey(TaskProperty.SEARCH_KEY);
        taskPropsDO.setRequireValue(actForm.getSearchKey());
        taskPropsBiz.create(taskPropsDO);

        taskPropsDO = new TaskPropsDO();
        taskPropsDO.setTaskId(taskId);
        taskPropsDO.setRequireKey(TaskProperty.SEARCH_URL);
        taskPropsDO.setRequireValue(SearchEnum.getNameByValue(Convert.toInt(actForm.getSearchUrl())));
        taskPropsBiz.create(taskPropsDO);

        taskPropsDO = new TaskPropsDO();
        taskPropsDO.setTaskId(taskId);
        taskPropsDO.setRequireKey(TaskProperty.SEARCH_RESULT_PAGE);
        taskPropsDO.setRequireValue(actForm.getSearchResultPage());
        taskPropsBiz.create(taskPropsDO);
        taskPropsDO = new TaskPropsDO();
        taskPropsDO.setTaskId(taskId);
        taskPropsDO.setRequireKey(TaskProperty.SEARCH_RESULT_ROW);
        taskPropsDO.setRequireValue(actForm.getSearchResultRow());
        taskPropsBiz.create(taskPropsDO);
        taskPropsDO = new TaskPropsDO();
        taskPropsDO.setTaskId(taskId);
        taskPropsDO.setRequireKey(TaskProperty.SEARCH_RESULT_COL);
        taskPropsDO.setRequireValue(actForm.getSearchResultCol());
        taskPropsBiz.create(taskPropsDO);
    }


    private String getItemUrl(TaobaoItemDO taobaoItemDO) {
        if ("b".equalsIgnoreCase(taobaoItemDO.getType())) {
            return "http://detail.tmall.com/item.htm?id=" + taobaoItemDO.getNumIid();
        } else {
            return "http://item.taobao.com/item.htm?id=" + taobaoItemDO.getNumIid();
        }
    }
}
