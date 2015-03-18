package guda.task.web.action;

import guda.task.biz.TaskAcceptBiz;
import guda.task.biz.UserBiz;
import guda.task.biz.enums.TaskAcceptStatusEnum;
import guda.task.biz.enums.UserStatusEnum;
import guda.task.biz.vo.AjaxResponce;
import guda.task.common.security.AppContexHolder;
import guda.task.common.security.AppContext;
import guda.task.common.util.CommonResultCode;
import guda.task.common.util.ErrorCode;
import guda.task.common.util.MD5;
import guda.task.dao.domain.TaskAcceptDO;
import guda.task.dao.domain.UserDO;
import guda.task.web.constans.SessionConstants;
import guda.task.web.form.UserLoginForm;
import guda.tools.web.util.RequestUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by well on 2014/12/30.
 */
@Controller
public class LoginAjaxAction extends BaseAjaxAction {

    @Autowired
    private UserBiz userBiz;
    @Autowired
    private TaskAcceptBiz taskAcceptBiz;

    @RequestMapping(value = "login.json", method = RequestMethod.POST)
    public void login(
            HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {

        try {
            String user = request.getParameter("name");
            String password = request.getParameter("password");
            long taskId = RequestUtil.getLong(request, "taskId");
            if (user == null || password == null || taskId <= 0) {
                ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.PARAM_MISS)), response);
                return;
            }
            // 校验验证码
            Integer count = (Integer) request.getSession().getAttribute(LoginAction.LOGIN_ERROR_COUNT);
            if (count == null) {
                count = 0;
                request.getSession().setAttribute(LoginAction.LOGIN_ERROR_COUNT, 0);
            }
            if (count != null && count > 3) {
                ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.PARAM_MISS)), response);
                return;
            }
            UserDO userDO = userBiz.queryUser(user);
            if (userDO == null) {
                ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.LOGIN_USER_OR_PASSWORD_NOT_MATCH)), response);
                return;
            }

            if (!MD5.md5(password).equals(userDO.getPassword())) {
                if (count != null && count > 3) {
                    modelMap.addAttribute("showCaptcha", true);
                }
                request.getSession().setAttribute(LoginAction.LOGIN_ERROR_COUNT, new Integer(++count));
                ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.LOGIN_USER_OR_PASSWORD_NOT_MATCH)), response);
                return;
            }
            if (userDO.getStatus().intValue() != UserStatusEnum.NORMAL.getValue()) {
                ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.LOGIN_USER_OR_PASSWORD_NOT_MATCH)), response);
                return;
            }
            //校验任务是否是自己的
            TaskAcceptDO query = taskAcceptBiz.query(userDO.getId(), taskId);
            if (query == null) {
                ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.TASK_NOT_EXIST)), response);
                return;
            }
            if (query.getStatus() != TaskAcceptStatusEnum.ACCEPT.getValue()) {
                ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.TASK_NOT_EXIST)), response);
                return;
            }
            request.getSession().removeAttribute(LoginAction.LOGIN_ERROR_COUNT);
            AppContext appContext = new AppContext();
            appContext.setUserDO(userDO);
            AppContexHolder.setContext(appContext);
            request.getSession().setAttribute(SessionConstants.APP_CONTEXT, appContext);
            ajaxReturn(new AjaxResponce(true), response);
            return;
        } catch (Exception e) {
            log.error("", e);
            // 异常，重置验证码
            Integer count = (Integer) request.getSession().getAttribute(LoginAction.LOGIN_ERROR_COUNT);
            if (count == null) {
                request.getSession().setAttribute(LoginAction.LOGIN_ERROR_COUNT, new Integer(1));
            } else {
                request.getSession().setAttribute(LoginAction.LOGIN_ERROR_COUNT, new Integer(count + 1));
            }
            request.getSession().removeAttribute(
                    com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

        }
        ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.UNKOWN_ERROR)), response);
    }
}
