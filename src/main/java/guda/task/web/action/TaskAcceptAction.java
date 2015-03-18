package guda.task.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import guda.task.biz.TaskAcceptBiz;
import guda.task.dao.domain.TaskAcceptDO;
import guda.task.web.form.TaskAcceptEditForm;
import guda.task.web.form.TaskAcceptForm;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;


@Controller
public class TaskAcceptAction {


    @Autowired
    private TaskAcceptBiz taskAcceptBiz;

    @RequestMapping(value = "taskAccept/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);
        modelMap.put("query",baseQuery);
        BizResult bizResult = taskAcceptBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "taskAccept/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "taskAccept/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, TaskAcceptEditForm taskAcceptEditForm,
        BindingResult result, Map<String,Object> model) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = taskAcceptBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            taskAcceptEditForm.initForm(((TaskAcceptDO)bizResult.data.get("taskAcceptDO")));
            return "taskAccept/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "taskAccept/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = taskAcceptBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "taskAccept/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "taskAccept/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, TaskAcceptForm taskAcceptForm,
        BindingResult result, Map<String,Object> model) {
        return "taskAccept/create.vm";
    }

    @RequestMapping(value = "taskAccept/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid  TaskAcceptForm taskAcceptForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "taskAccept/create.vm";
        }
        TaskAcceptDO taskAcceptDO = taskAcceptForm.toDO();
        BizResult bizResult = taskAcceptBiz.create(taskAcceptDO);
        if (bizResult.success) {
            return "redirect:/taskAccept/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "taskAccept/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid TaskAcceptEditForm taskAcceptEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "taskAccept/edit.vm";
        }
        TaskAcceptDO taskAcceptDO = taskAcceptEditForm.toDO();
        BizResult bizResult = taskAcceptBiz.update(taskAcceptDO);
        if (bizResult.success) {
            return "redirect:/taskAccept/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "taskAccept/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = taskAcceptBiz.delete(id);
        if (bizResult.success) {
            return "taskAccept/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
