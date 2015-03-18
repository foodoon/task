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

import guda.task.biz.AccountDetailBiz;
import guda.task.dao.domain.AccountDetailDO;
import guda.task.web.form.AccountDetailEditForm;
import guda.task.web.form.AccountDetailForm;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;


@Controller
public class AccountDetailAction {


    @Autowired
    private AccountDetailBiz accountDetailBiz;


    @RequestMapping(value = "accountDetail/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, AccountDetailEditForm accountDetailEditForm,
        BindingResult result, Map<String,Object> model) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = accountDetailBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            accountDetailEditForm.initForm(((AccountDetailDO)bizResult.data.get("accountDetailDO")));
            return "accountDetail/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "accountDetail/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = accountDetailBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "accountDetail/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "accountDetail/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, AccountDetailForm accountDetailForm,
        BindingResult result, Map<String,Object> model) {
        return "accountDetail/create.vm";
    }

    @RequestMapping(value = "accountDetail/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid  AccountDetailForm accountDetailForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "accountDetail/create.vm";
        }
        AccountDetailDO accountDetailDO = accountDetailForm.toDO();
        BizResult bizResult = accountDetailBiz.create(accountDetailDO);
        if (bizResult.success) {
            return "redirect:/accountDetail/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "accountDetail/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid AccountDetailEditForm accountDetailEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "accountDetail/edit.vm";
        }
        AccountDetailDO accountDetailDO = accountDetailEditForm.toDO();
        BizResult bizResult = accountDetailBiz.update(accountDetailDO);
        if (bizResult.success) {
            return "redirect:/accountDetail/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "accountDetail/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = accountDetailBiz.delete(id);
        if (bizResult.success) {
            return "accountDetail/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
