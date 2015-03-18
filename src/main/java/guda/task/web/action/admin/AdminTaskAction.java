package guda.task.web.action.admin;

import guda.task.biz.AdminTaskBiz;
import guda.task.biz.query.AdminTaskQuery;
import guda.task.common.util.DateHelper;
import guda.task.web.action.admin.form.AdminTaskSearchForm;
import guda.task.web.action.seller.form.ActForm;
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
 * Created by foodoon on 2014/12/26.
 */
@Controller
public class AdminTaskAction {

    @Autowired
    private AdminTaskBiz adminTaskBiz;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "admin/task/index.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap, AdminTaskSearchForm adminTaskSearchForm,
                       BindingResult result, Map<String, Object> model) {
        Date date = new Date();
        Date end = DateHelper.endMHS(date);
        Date start = DateHelper.cleanMHS(date);
        adminTaskSearchForm.setEndDate(end);
        adminTaskSearchForm.setStartDate(start);
        AdminTaskQuery adminTaskQuery = new AdminTaskQuery();

        adminTaskQuery.setEndDate(end);
        adminTaskQuery.setStartDate(start);
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        adminTaskQuery.setPageNo(pageId);
        adminTaskQuery.setPageSize(pageSize);
        BizResult bizResult = adminTaskBiz.listTask(adminTaskQuery);
        if(bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "admin/task/index.vm";
        }
        return "500.vm";

    }
    @RequestMapping(value = "admin/task/index.htm", method = RequestMethod.POST)
    public String search(HttpServletRequest request, ModelMap modelMap,@Valid AdminTaskSearchForm adminTaskSearchForm,
                         BindingResult result, Map<String, Object> model) {
        AdminTaskQuery adminTaskQuery = new AdminTaskQuery();
        adminTaskQuery.setEndDate(DateHelper.endMHS(adminTaskSearchForm.getEndDate()));
        adminTaskQuery.setStartDate(DateHelper.cleanMHS(adminTaskSearchForm.getStartDate()));
        adminTaskQuery.setStatus(adminTaskSearchForm.getStatus());
        adminTaskQuery.setSellerNick(adminTaskSearchForm.getSellerNick());
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        adminTaskQuery.setPageNo(pageId);
        adminTaskQuery.setPageSize(pageSize);
        BizResult bizResult = adminTaskBiz.listTask(adminTaskQuery);
        if(bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "admin/task/index.vm";
        }
        return "500.vm";
    }
}
