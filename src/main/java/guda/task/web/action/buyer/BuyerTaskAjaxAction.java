package guda.task.web.action.buyer;

import guda.mvc.form.Form;
import guda.task.biz.TaobaoItemBiz;
import guda.task.biz.TaskAcceptBiz;
import guda.task.biz.TaskListBiz;
import guda.task.biz.TaskPropsBiz;
import guda.task.biz.enums.TaskAcceptStatusEnum;
import guda.task.biz.enums.TaskStatusEnum;
import guda.task.biz.query.TaskAcceptQuery;
import guda.task.biz.query.TaskQuery;
import guda.task.biz.vo.AjaxResponce;
import guda.task.common.security.AppContexHolder;
import guda.task.common.util.CommonResultCode;
import guda.task.common.util.ErrorCode;
import guda.task.dao.domain.TaskAcceptDO;
import guda.task.dao.domain.TaskListDO;
import guda.task.web.action.BaseAjaxAction;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.Convert;
import guda.tools.web.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by well on 2014/12/25.
 */
@Controller
public class BuyerTaskAjaxAction extends BaseAjaxAction {

    @Autowired
    private TaskListBiz taskListBiz;
    @Autowired
    private TaskPropsBiz taskPropsBiz;
    @Autowired
    private TaobaoItemBiz taobaoItemBiz;
    @Autowired
    private TaskAcceptBiz taskAcceptBiz;

    @Form
    @RequestMapping(value = "buyer/task/doFinish.json", method = RequestMethod.POST)
    public void doFinish(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String id = request.getParameter("id");
        String taobaoOrder = request.getParameter("taobaoOrder");
        if (id == null || taobaoOrder == null) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.PARAM_MISS)), response);
            return;
        }
        TaskAcceptDO taskAcceptDO1 = taskAcceptBiz.queryById(Convert.toLong(id));
        if (taskAcceptDO1 == null) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        if (taskAcceptDO1.getStatus().intValue() != TaskAcceptStatusEnum.ACCEPT.getValue()) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.STATUS_ERROR)), response);
            return;
        }
        if (taskAcceptDO1.getUserId().longValue() != AppContexHolder.getContext().getUserDO().getId()) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.PERSSION_ERROR)), response);
            return;
        }
        TaskListDO taskListDO = taskListBiz.queryById(taskAcceptDO1.getTaskId());
        if (taskListDO == null) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        if (taskListDO.getStatus().intValue() != TaskStatusEnum.ACCEPT.getValue()) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.STATUS_ERROR)), response);
            return;
        }
        try {
            taskAcceptDO1.setStatus(TaskAcceptStatusEnum.TAOBAO_STATUS.getValue());
            taskAcceptDO1.setTaobaoTradeNo(taobaoOrder);
            taskAcceptBiz.update(taskAcceptDO1);
            taskListDO.setStatus(TaskStatusEnum.TAOBAO_STATUS.getValue());
            taskListBiz.update(taskListDO);
            ajaxReturn(new AjaxResponce(true), response);
            return;
        } catch (Exception e) {
            log.error("", e);
        }
        ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.UNKOWN_ERROR)), response);
    }

    @Form
    @RequestMapping(value = "buyer/task/doAccept.json", method = RequestMethod.POST)
    public void doAccept(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String id = request.getParameter("id");
        TaskListDO taskListDO = taskListBiz.queryById(Convert.toLong(id));
        if (taskListDO == null) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        if (taskListDO.getStatus().intValue() != TaskStatusEnum.PUBLISH.getValue()) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.STATUS_ERROR)), response);
            return;
        }
        try {
            //校验本周接单数量，不能超过三
            boolean b = taskAcceptBiz.canAcceptCheckWeek(AppContexHolder.getContext().getUserDO().getId());
            if(!b){
                ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.PERSSION_ERROR)), response);
                return;
            }
            taskListDO.setStatus(TaskStatusEnum.ACCEPT.getValue());
            taskListBiz.update(taskListDO);
            TaskAcceptDO taskAcceptDO = new TaskAcceptDO();
            taskAcceptDO.setStatus(TaskAcceptStatusEnum.ACCEPT.getValue());
            taskAcceptDO.setGmtCreated(new Date());
            taskAcceptDO.setGmtModified(new Date());
            taskAcceptDO.setTaskId(taskListDO.getId());
            taskAcceptDO.setUserId(AppContexHolder.getContext().getUserDO().getId());
            taskAcceptBiz.create(taskAcceptDO);
            ajaxReturn(new AjaxResponce(true), response);
            return;
        } catch (Exception e) {
            log.error("", e);
        }
        ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.UNKOWN_ERROR)), response);

    }

    @Form
    @RequestMapping(value = "buyer/task/doCancel.json", method = RequestMethod.POST)
    public void doCancel(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String id = request.getParameter("id");
        TaskAcceptDO taskAcceptDO1 = taskAcceptBiz.queryById(Convert.toLong(id));
        if (taskAcceptDO1 == null) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        if (taskAcceptDO1.getStatus().intValue() != TaskAcceptStatusEnum.ACCEPT.getValue()) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.STATUS_ERROR)), response);
            return;
        }
        if (taskAcceptDO1.getUserId().longValue() != AppContexHolder.getContext().getUserDO().getId()) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.PERSSION_ERROR)), response);
            return;
        }
        TaskListDO taskListDO = taskListBiz.queryById(taskAcceptDO1.getTaskId());
        if (taskListDO == null) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        if (taskListDO.getStatus().intValue() != TaskStatusEnum.ACCEPT.getValue()) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.STATUS_ERROR)), response);
            return;
        }
        try {
            taskAcceptDO1.setStatus(TaskAcceptStatusEnum.BREAK.getValue());
            taskAcceptBiz.update(taskAcceptDO1);
            taskListDO.setStatus(TaskStatusEnum.PUBLISH.getValue());
            taskListBiz.update(taskListDO);
            ajaxReturn(new AjaxResponce(true), response);
            return;
        } catch (Exception e) {
            log.error("", e);
        }
        ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.UNKOWN_ERROR)), response);

    }

    @Form
    @RequestMapping(value = "buyer/task/doSign.json", method = RequestMethod.POST)
    public void doSign(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String id = request.getParameter("id");
        TaskAcceptDO taskAcceptDO1 = taskAcceptBiz.queryById(Convert.toLong(id));
        if (taskAcceptDO1 == null) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        if (taskAcceptDO1.getStatus().intValue() != TaskAcceptStatusEnum.TAOBAO_STATUS.getValue()) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.STATUS_ERROR)), response);
            return;
        }
        if (taskAcceptDO1.getUserId().longValue() != AppContexHolder.getContext().getUserDO().getId()) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.PERSSION_ERROR)), response);
            return;
        }
        TaskListDO taskListDO = taskListBiz.queryById(taskAcceptDO1.getTaskId());
        if (taskListDO == null) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        if (taskListDO.getStatus().intValue() != TaskStatusEnum.TAOBAO_STATUS.getValue()) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.STATUS_ERROR)), response);
            return;
        }
        try {
            taskAcceptDO1.setStatus(TaskAcceptStatusEnum.SIGN.getValue());
            taskAcceptBiz.update(taskAcceptDO1);
            taskListDO.setStatus(TaskStatusEnum.SIGN.getValue());
            taskListBiz.update(taskListDO);
            ajaxReturn(new AjaxResponce(true), response);
            return;
        } catch (Exception e) {
            log.error("", e);
        }
        ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.UNKOWN_ERROR)), response);
    }
    @Form
    @RequestMapping(value = "buyer/task/doBreak.json", method = RequestMethod.POST)
    public void doBreak(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String id = request.getParameter("id");
        TaskAcceptDO taskAcceptDO1 = taskAcceptBiz.queryById(Convert.toLong(id));
        if (taskAcceptDO1 == null) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        if (taskAcceptDO1.getStatus().intValue() != TaskAcceptStatusEnum.ACCEPT.getValue()) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.STATUS_ERROR)), response);
            return;
        }
        if (taskAcceptDO1.getUserId().longValue() != AppContexHolder.getContext().getUserDO().getId()) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.PERSSION_ERROR)), response);
            return;
        }
        TaskListDO taskListDO = taskListBiz.queryById(taskAcceptDO1.getTaskId());
        if (taskListDO == null) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        if (taskListDO.getStatus().intValue() != TaskStatusEnum.ACCEPT.getValue()) {
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.STATUS_ERROR)), response);
            return;
        }
        try {
            taskAcceptDO1.setStatus(TaskAcceptStatusEnum.BREAK.getValue());
            taskAcceptBiz.update(taskAcceptDO1);
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
