package guda.task.web.action.buyer;

import guda.task.biz.TaobaoItemBiz;
import guda.task.biz.TaskAcceptBiz;
import guda.task.biz.TaskListBiz;
import guda.task.biz.TaskPropsBiz;
import guda.task.biz.query.TaskAcceptQuery;
import guda.task.biz.query.TaskQuery;
import guda.task.common.security.AppContexHolder;
import guda.task.common.util.CommonResultCode;
import guda.task.common.util.ErrorCode;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by well on 2014/12/22.
 */
@Controller
public class BuyerTaskAction {

    @Autowired
    private TaskListBiz taskListBiz;
    @Autowired
    private TaskPropsBiz taskPropsBiz;
    @Autowired
    private TaobaoItemBiz taobaoItemBiz;
    @Autowired
    private TaskAcceptBiz taskAcceptBiz;


    @RequestMapping(value = "buyer/task/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        boolean b = taskAcceptBiz.canAcceptCheckWeek(AppContexHolder.getContext().getUserDO().getId());
        if(!b){
            modelMap.addAttribute("pageMsg", ErrorCode.getMessage(CommonResultCode.ACCEPT_TASK_EXCEED));
        }
        TaskQuery taskQuery = new TaskQuery();
        modelMap.put("query", taskQuery);
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        taskQuery.setPageNo(pageId);
        taskQuery.setPageSize(pageSize);
        BizResult bizResult = taskListBiz.listPublishForBuyer(taskQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "buyer/task/list.vm";
        } else {
            return "common/error.vm";
        }
    }

    @RequestMapping(value = "buyer/task/ing.htm", method = RequestMethod.GET)
    public String ing(HttpServletRequest request, ModelMap modelMap) {

        TaskAcceptQuery taskAcceptQuery = new TaskAcceptQuery();
        modelMap.put("query", taskAcceptQuery);
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        taskAcceptQuery.setPageNo(pageId);
        taskAcceptQuery.setPageSize(pageSize);
        taskAcceptQuery.setUserId(AppContexHolder.getContext().getUserDO().getId());
        BizResult bizResult = taskAcceptBiz.listIngForBuyer(taskAcceptQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "buyer/task/ing.vm";
        } else {
            return "common/error.vm";
        }
    }


    @RequestMapping(value = "buyer/task/his.htm", method = RequestMethod.GET)
    public String his(HttpServletRequest request, ModelMap modelMap) {

        TaskAcceptQuery taskAcceptQuery = new TaskAcceptQuery();
        modelMap.put("query", taskAcceptQuery);
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        taskAcceptQuery.setPageNo(pageId);
        taskAcceptQuery.setPageSize(pageSize);
        taskAcceptQuery.setUserId(AppContexHolder.getContext().getUserDO().getId());
        BizResult bizResult = taskAcceptBiz.listHisForBuyer(taskAcceptQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "buyer/task/his.vm";
        } else {
            return "common/error.vm";
        }
    }




}
