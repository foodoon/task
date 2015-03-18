package guda.task.web.action.admin;

import guda.task.biz.query.TaskQuery;
import guda.task.common.security.AppContexHolder;
import guda.task.common.util.CommonResultCode;
import guda.task.common.util.ErrorCode;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by foodoon on 2014/12/26.
 */
@Controller
public class AdminIndexAction {

    @RequestMapping(value = "admin/index.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {

        return "admin/index.vm";

    }

}
