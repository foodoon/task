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

import guda.task.biz.TaobaoShopBiz;
import guda.task.dao.domain.TaobaoShopDO;
import guda.task.web.form.TaobaoShopEditForm;
import guda.task.web.form.TaobaoShopForm;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;


@Controller
public class TaobaoShopAction {


    @Autowired
    private TaobaoShopBiz taobaoShopBiz;

    @RequestMapping(value = "taobaoShop/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);
        modelMap.put("query",baseQuery);
        BizResult bizResult = taobaoShopBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "taobaoShop/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "taobaoShop/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, TaobaoShopEditForm taobaoShopEditForm,
        BindingResult result, Map<String,Object> model) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = taobaoShopBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            taobaoShopEditForm.initForm(((TaobaoShopDO)bizResult.data.get("taobaoShopDO")));
            return "taobaoShop/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "taobaoShop/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = taobaoShopBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "taobaoShop/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "taobaoShop/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, TaobaoShopForm taobaoShopForm,
        BindingResult result, Map<String,Object> model) {
        return "taobaoShop/create.vm";
    }

    @RequestMapping(value = "taobaoShop/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid  TaobaoShopForm taobaoShopForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "taobaoShop/create.vm";
        }
        TaobaoShopDO taobaoShopDO = taobaoShopForm.toDO();
        BizResult bizResult = taobaoShopBiz.create(taobaoShopDO);
        if (bizResult.success) {
            return "redirect:/taobaoShop/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "taobaoShop/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid TaobaoShopEditForm taobaoShopEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "taobaoShop/edit.vm";
        }
        TaobaoShopDO taobaoShopDO = taobaoShopEditForm.toDO();
        BizResult bizResult = taobaoShopBiz.update(taobaoShopDO);
        if (bizResult.success) {
            return "redirect:/taobaoShop/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "taobaoShop/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = taobaoShopBiz.delete(id);
        if (bizResult.success) {
            return "taobaoShop/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
