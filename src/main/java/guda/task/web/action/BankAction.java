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

import guda.task.biz.BankBiz;
import guda.task.dao.domain.BankDO;
import guda.task.web.form.BankEditForm;
import guda.task.web.form.BankForm;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;


@Controller
public class BankAction {


    @Autowired
    private BankBiz bankBiz;

    @RequestMapping(value = "bank/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);
        modelMap.put("query",baseQuery);
        BizResult bizResult = bankBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "bank/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "bank/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, BankEditForm bankEditForm,
        BindingResult result, Map<String,Object> model) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = bankBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            bankEditForm.initForm(((BankDO)bizResult.data.get("bankDO")));
            return "bank/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "bank/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = bankBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "bank/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "bank/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, BankForm bankForm,
        BindingResult result, Map<String,Object> model) {
        return "bank/create.vm";
    }

    @RequestMapping(value = "bank/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid  BankForm bankForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "bank/create.vm";
        }
        BankDO bankDO = bankForm.toDO();
        BizResult bizResult = bankBiz.create(bankDO);
        if (bizResult.success) {
            return "redirect:/bank/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "bank/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid BankEditForm bankEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "bank/edit.vm";
        }
        BankDO bankDO = bankEditForm.toDO();
        BizResult bizResult = bankBiz.update(bankDO);
        if (bizResult.success) {
            return "redirect:/bank/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "bank/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = bankBiz.delete(id);
        if (bizResult.success) {
            return "bank/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
