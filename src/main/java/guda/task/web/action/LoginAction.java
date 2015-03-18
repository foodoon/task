package guda.task.web.action;

import guda.mvc.form.Form;
import guda.task.biz.UserBiz;
import guda.task.biz.enums.UserStatusEnum;
import guda.task.common.security.AppContexHolder;
import guda.task.common.security.AppContext;
import guda.task.common.util.CommonResultCode;
import guda.task.common.util.MD5;
import guda.task.dao.domain.UserDO;
import guda.task.web.constans.SessionConstants;
import guda.task.web.form.UserLoginForm;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Created by foodoon on 2014/12/19.
 */
@Controller
public class LoginAction {

    private Logger log = LoggerFactory.getLogger(LoginAction.class);

    @Autowired
    private UserBiz userBiz;

    public static final String LOGIN_ERROR_COUNT = "LOGIN_ERROR_COUNT";

    @RequestMapping(value = "login.htm", method = RequestMethod.GET)
    public String list(UserLoginForm userLoginForm, BindingResult result, HttpServletRequest request, ModelMap modelMap) {
        AppContext context = AppContexHolder.getContext();
        if(context.isBuyer()){
            return "redirect:/buyer/task/list.htm";
        }else if(context.isSeller()){
            return "redirect:/seller/act/ing.htm";
        }else if(context.isAdmin()){
            return "redirect:/admin/task/index.htm";
        }
        Integer count = (Integer) request.getSession().getAttribute(LOGIN_ERROR_COUNT);
        if (count != null && count > 3) {
            modelMap.addAttribute("showCaptcha", true);
        } else {
            modelMap.addAttribute("showCaptcha", false);
        }
        return "login.vm";

    }

    @RequestMapping(value = "loginOut.htm", method = RequestMethod.GET)
    public String loginOut( HttpServletRequest request, ModelMap modelMap) {
        request.getSession().removeAttribute(SessionConstants.TAOBAO_ACCESS_TOKEN);
        request.getSession().removeAttribute(SessionConstants.APP_CONTEXT);
        return "redirect:/login.htm";

    }


    @RequestMapping(value = "login.htm", method = RequestMethod.POST)
    public String adminLogin(@Valid UserLoginForm userLoginForm, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {

        try {

            if (result.hasErrors()) {
                return "login.vm";
            }
            // 校验验证码
            Integer count = (Integer) request.getSession().getAttribute(LOGIN_ERROR_COUNT);
            if (count == null) {
                count = 0;
                request.getSession().setAttribute(LOGIN_ERROR_COUNT,0);
            }
            if (count != null && count > 3) {
                modelMap.addAttribute("showCaptcha", true);
                String kaptcha = (String) request.getSession().getAttribute(
                        com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
                if (StringUtils.isBlank(kaptcha)
                        || !kaptcha.equalsIgnoreCase(userLoginForm.getCaptcha())) {
                    result.rejectValue("captcha", CommonResultCode.LOGIN_CAPTCHA_ERROR);
                    return "login.vm";
                }
            }
            UserDO userDO = userBiz.queryUser(userLoginForm.getName());
            if (userDO == null) {
                if (count != null && count > 3) {
                    modelMap.addAttribute("showCaptcha", true);
                }
                request.getSession().setAttribute(LOGIN_ERROR_COUNT,new Integer(++count));
                result.rejectValue("name", CommonResultCode.USER_NOT_EXIST);
                return "login.vm";
            }

            if (!MD5.md5(userLoginForm.getPassword()).equals(userDO.getPassword())) {
                if (count != null && count > 3) {
                    modelMap.addAttribute("showCaptcha", true);
                }
                request.getSession().setAttribute(LOGIN_ERROR_COUNT,new Integer(++count));
                result.rejectValue("password", CommonResultCode.LOGIN_USER_OR_PASSWORD_NOT_MATCH);
                return "login.vm";
            }
            if (userDO.getStatus().intValue() != UserStatusEnum.NORMAL.getValue()) {
                return "redirect:/loginWarn.htm?code=" + String.valueOf(userDO.getStatus().intValue());
            }
            request.getSession().removeAttribute(LOGIN_ERROR_COUNT);
            AppContext appContext = new AppContext();
            appContext.setUserDO(userDO);
            AppContexHolder.setContext(appContext);
            request.getSession().setAttribute(SessionConstants.APP_CONTEXT, appContext);
            if(appContext.isBuyer()){
                return "redirect:/buyer/task/list.htm";
            }else if(appContext.isSeller()){
                return "redirect:/seller/act/ing.htm";
            }else{
                return "redirect:/admin/task/index.htm";
            }

        } catch (Exception e) {
            log.error("", e);
            // 异常，重置验证码
            Integer count = (Integer) request.getSession().getAttribute(LOGIN_ERROR_COUNT);
            if (count == null) {
                request.getSession().setAttribute(LOGIN_ERROR_COUNT, new Integer(1));
            } else {
                request.getSession().setAttribute(LOGIN_ERROR_COUNT, new Integer(count + 1));
            }
            request.getSession().removeAttribute(
                    com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

        }
        return "500.vm";
    }

    @RequestMapping(value = "loginWarn.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        String code = request.getParameter("code");
        if (String.valueOf(UserStatusEnum.INIT.getValue()).equals(code)) {
            modelMap.addAttribute("errorMsg", "您的帐号在审核中，无法登录");
        } else if (String.valueOf(UserStatusEnum.PUNISH.getValue()).equals(code)) {
            modelMap.addAttribute("errorMsg", "您的帐号在处罚期，无法登录");
        } else if (String.valueOf(UserStatusEnum.FORBID.getValue()).equals(code)) {
            modelMap.addAttribute("errorMsg", "您的帐号已经被禁用，无法登录");
        } else {
            modelMap.addAttribute("errorMsg", "服务端错误");
        }
        return "loginWarn.vm";

    }
}
