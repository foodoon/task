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

import guda.task.biz.TaskPropsBiz;
import guda.task.dao.domain.TaskPropsDO;
import guda.task.web.form.TaskPropsEditForm;
import guda.task.web.form.TaskPropsForm;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;


@Controller
public class TaskPropsAction {


    @Autowired
    private TaskPropsBiz taskPropsBiz;

    @RequestMapping(value = "taskProps/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);
        modelMap.put("query",baseQuery);
        BizResult bizResult = taskPropsBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "taskProps/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "taskProps/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, TaskPropsEditForm taskPropsEditForm,
        BindingResult result, Map<String,Object> model) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = taskPropsBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            taskPropsEditForm.initForm(((TaskPropsDO)bizResult.data.get("taskPropsDO")));
            return "taskProps/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "taskProps/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = taskPropsBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "taskProps/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "taskProps/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, TaskPropsForm taskPropsForm,
        BindingResult result, Map<String,Object> model) {
        return "taskProps/create.vm";
    }

    @RequestMapping(value = "taskProps/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid  TaskPropsForm taskPropsForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "taskProps/create.vm";
        }
        TaskPropsDO taskPropsDO = taskPropsForm.toDO();
        BizResult bizResult = taskPropsBiz.create(taskPropsDO);
        if (bizResult.success) {
            return "redirect:/taskProps/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "taskProps/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid TaskPropsEditForm taskPropsEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "taskProps/edit.vm";
        }
        TaskPropsDO taskPropsDO = taskPropsEditForm.toDO();
        BizResult bizResult = taskPropsBiz.update(taskPropsDO);
        if (bizResult.success) {
            return "redirect:/taskProps/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "taskProps/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = taskPropsBiz.delete(id);
        if (bizResult.success) {
            return "taskProps/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
