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

import guda.task.biz.UserScoreBiz;
import guda.task.dao.domain.UserScoreDO;
import guda.task.web.form.UserScoreEditForm;
import guda.task.web.form.UserScoreForm;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;


@Controller
public class UserScoreAction {


    @Autowired
    private UserScoreBiz userScoreBiz;



    @RequestMapping(value = "userScore/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, UserScoreEditForm userScoreEditForm,
        BindingResult result, Map<String,Object> model) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = userScoreBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            userScoreEditForm.initForm(((UserScoreDO)bizResult.data.get("userScoreDO")));
            return "userScore/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "userScore/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = userScoreBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "userScore/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "userScore/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, UserScoreForm userScoreForm,
        BindingResult result, Map<String,Object> model) {
        return "userScore/create.vm";
    }

    @RequestMapping(value = "userScore/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid  UserScoreForm userScoreForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "userScore/create.vm";
        }
        UserScoreDO userScoreDO = userScoreForm.toDO();
        BizResult bizResult = userScoreBiz.create(userScoreDO);
        if (bizResult.success) {
            return "redirect:/userScore/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "userScore/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid UserScoreEditForm userScoreEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "userScore/edit.vm";
        }
        UserScoreDO userScoreDO = userScoreEditForm.toDO();
        BizResult bizResult = userScoreBiz.update(userScoreDO);
        if (bizResult.success) {
            return "redirect:/userScore/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "userScore/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = userScoreBiz.delete(id);
        if (bizResult.success) {
            return "userScore/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
