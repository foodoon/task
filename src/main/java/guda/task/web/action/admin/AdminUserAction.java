package guda.task.web.action.admin;

import guda.task.biz.AccountBiz;
import guda.task.biz.AdminUserBiz;
import guda.task.biz.UserBiz;
import guda.task.biz.query.AdminTaskQuery;
import guda.task.biz.query.AdminUserQuery;
import guda.task.common.util.DateHelper;
import guda.task.dao.domain.AccountDO;
import guda.task.web.action.admin.form.AdminTaskSearchForm;
import guda.task.web.action.admin.form.AdminUserSearchForm;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by well on 2014/12/27.
 */
@Controller
public class AdminUserAction {

    @Autowired
    private AdminUserBiz adminUserBiz;
    @Autowired
    private UserBiz userBiz;
    @Autowired
    private AccountBiz accountBiz;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "admin/user/index.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap, AdminUserSearchForm adminUserSearchForm,
                       BindingResult result, Map<String, Object> model) {

        AdminUserQuery adminUserQuery = new AdminUserQuery();
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        adminUserQuery.setPageNo(pageId);
        adminUserQuery.setPageSize(pageSize);

        BizResult bizResult = adminUserBiz.listUser(adminUserQuery);
        if(bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "admin/user/index.vm";
        }
        return "500.vm";

    }
    @RequestMapping(value = "admin/user/index.htm", method = RequestMethod.POST)
    public String search(HttpServletRequest request, ModelMap modelMap,@Valid AdminUserSearchForm adminUserSearchForm,
                         BindingResult result, Map<String, Object> model) {
        AdminUserQuery adminUserQuery = new AdminUserQuery();
        adminUserQuery.setLoginName(adminUserSearchForm.getLoginName());
        adminUserQuery.setTaobaoUserNick(adminUserSearchForm.getTaobaoUserNick());
        adminUserQuery.setStatus(adminUserSearchForm.getStatus());
        adminUserQuery.setWangwang(adminUserSearchForm.getWangwang());
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        adminUserQuery.setPageNo(pageId);
        adminUserQuery.setPageSize(pageSize);
        BizResult bizResult = adminUserBiz.listUser(adminUserQuery);
        if(bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "admin/user/index.vm";
        }
        return "500.vm";
    }

    @RequestMapping(value = "admin/user/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap,  Map<String, Object> model) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = userBiz.detail(id);
        AccountDO accountDO = accountBiz.queryByUserId(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            modelMap.addAttribute("accountDO",accountDO);
            return "admin/user/detail.vm";
        } else {
            return "500.vm";
        }

    }
}
