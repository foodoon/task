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

import guda.task.biz.TaobaoItemBiz;
import guda.task.dao.domain.TaobaoItemDO;
import guda.task.web.form.TaobaoItemEditForm;
import guda.task.web.form.TaobaoItemForm;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;


@Controller
public class TaobaoItemAction {


    @Autowired
    private TaobaoItemBiz taobaoItemBiz;



    @RequestMapping(value = "taobaoItem/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, TaobaoItemEditForm taobaoItemEditForm,
        BindingResult result, Map<String,Object> model) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = taobaoItemBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            taobaoItemEditForm.initForm(((TaobaoItemDO)bizResult.data.get("taobaoItemDO")));
            return "taobaoItem/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "taobaoItem/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = taobaoItemBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "taobaoItem/detail.vm";
        } else {
            return "common/error.vm";
        }

    }



    @RequestMapping(value = "taobaoItem/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid TaobaoItemEditForm taobaoItemEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "taobaoItem/edit.vm";
        }
        TaobaoItemDO taobaoItemDO = taobaoItemEditForm.toDO();
        BizResult bizResult = taobaoItemBiz.update(taobaoItemDO);
        if (bizResult.success) {
            return "redirect:/taobaoItem/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "taobaoItem/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = taobaoItemBiz.delete(id);
        if (bizResult.success) {
            return "taobaoItem/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
