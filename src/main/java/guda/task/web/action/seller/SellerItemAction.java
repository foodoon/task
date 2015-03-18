package guda.task.web.action.seller;

import guda.mvc.form.Form;
import guda.task.biz.TaobaoItemBiz;
import guda.task.biz.query.TaobaoItemQuery;
import guda.task.common.security.AppContexHolder;
import guda.task.common.util.CommonResultCode;
import guda.task.common.util.ErrorCode;
import guda.task.dao.domain.TaobaoItemDO;
import guda.task.web.form.TaobaoItemEditForm;
import guda.task.web.form.TaobaoItemForm;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by foodoon on 2014/12/27.
 */
@Controller
public class SellerItemAction {

    @Autowired
    private TaobaoItemBiz taobaoItemBiz;

    @RequestMapping(value = "/seller/item/onsale.htm", method = RequestMethod.GET)
    public String doGet(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        TaobaoItemQuery taobaoItemQuery = new TaobaoItemQuery();
        taobaoItemQuery.setPageNo(pageId);
        taobaoItemQuery.setPageSize(pageSize);
        taobaoItemQuery.setTaobaoUserId(AppContexHolder.getContext().getUserDO().getTaobaoUserId());
        BizResult bizResult = taobaoItemBiz.list(taobaoItemQuery);
        modelMap.put("query",taobaoItemQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "seller/item/onsale.vm";
        } else {
            return "500.vm";
        }
    }

    @RequestMapping(value = "seller/item/createOnsale.htm", method = RequestMethod.GET)
    public String testCreate(HttpServletRequest request, ModelMap modelMap, TaobaoItemForm taobaoItemForm,
                             BindingResult result, Map<String,Object> model) {
        return "seller/item/createOnsale.vm";
    }





    @Form
    @RequestMapping(value = "seller/item/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid TaobaoItemForm taobaoItemForm,
                               BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "seller/item/createOnsale.vm";
        }
        TaobaoItemDO taobaoItemDO = taobaoItemForm.toDO();
        taobaoItemDO.setPrice(taobaoItemDO.getPrice()*100);
        taobaoItemDO.setTaobaoUserId(AppContexHolder.getContext().getUserDO().getTaobaoUserId());
        BizResult bizResult = taobaoItemBiz.create(taobaoItemDO);
        if (bizResult.success) {
            return "redirect:/seller/item/onsale.htm";
        } else {
            return "500.vm";
        }

    }

    @RequestMapping(value = "seller/item/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, TaobaoItemEditForm taobaoItemEditForm,
                       BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "seller/item/edit.vm";
        }
        long id = RequestUtil.getLong(request, "id");
        TaobaoItemDO taobaoItemDO1 = taobaoItemBiz.queryById(id);
        if(taobaoItemDO1 == null){
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }
        taobaoItemEditForm.setItemUrl(taobaoItemDO1.getItemUrl());
        taobaoItemEditForm.setPicUrl(taobaoItemDO1.getPicUrl());
        taobaoItemEditForm.setPrice(taobaoItemDO1.getPrice()/100);
        taobaoItemEditForm.setTitle(taobaoItemDO1.getTitle());
        taobaoItemEditForm.setId(taobaoItemDO1.getId());
        return "seller/item/edit.vm";
    }

    @Form
    @RequestMapping(value = "seller/item/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, ModelMap modelMap,@Valid TaobaoItemEditForm taobaoItemEditForm,
                           BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "seller/item/edit.vm";
        }
        TaobaoItemDO taobaoItemDO1 = taobaoItemBiz.queryById(taobaoItemEditForm.getId());
        if(taobaoItemDO1 == null){
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }
        taobaoItemDO1.setPrice(taobaoItemEditForm.getPrice()*100);
        taobaoItemDO1.setItemUrl(taobaoItemEditForm.getItemUrl());
        taobaoItemDO1.setPicUrl(taobaoItemEditForm.getPicUrl());
        taobaoItemDO1.setTitle(taobaoItemEditForm.getTitle());
        BizResult bizResult = taobaoItemBiz.update(taobaoItemDO1);
        if (bizResult.success) {
            return "redirect:/seller/item/onsale.htm";
        } else {
            return "500.vm";
        }

    }


    @Form
    @RequestMapping(value = "seller/item/doDelete.htm", method = RequestMethod.GET)
    public String doDelete(HttpServletRequest request, ModelMap modelMap, Map<String,Object> model) {
        long id = RequestUtil.getLong(request, "id");
        TaobaoItemDO taobaoItemDO1 = taobaoItemBiz.queryById(id);
        if(taobaoItemDO1 == null){
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }

        BizResult bizResult = taobaoItemBiz.delete(id);
        if (bizResult.success) {
            return "redirect:/seller/item/onsale.htm";
        } else {
            return "500.vm";
        }

    }
}
