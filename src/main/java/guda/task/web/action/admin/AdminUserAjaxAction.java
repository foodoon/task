package guda.task.web.action.admin;

import guda.mvc.form.Form;
import guda.task.biz.AccountBiz;
import guda.task.biz.UserBiz;
import guda.task.biz.enums.UserStatusEnum;
import guda.task.biz.vo.AjaxResponce;
import guda.task.common.util.CommonResultCode;
import guda.task.common.util.ErrorCode;
import guda.task.dao.domain.AccountDO;
import guda.task.dao.domain.UserDO;
import guda.task.web.action.BaseAjaxAction;
import guda.tools.web.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by foodoon on 2014/12/27.
 */
@Controller
public class AdminUserAjaxAction extends BaseAjaxAction{
    private Logger log = LoggerFactory.getLogger(AdminUserAjaxAction.class);
    @Autowired
    private UserBiz userBiz;
    @Autowired
    private AccountBiz accountBiz;
    @Form
    @RequestMapping(value = "admin/user/doPass.json", method = RequestMethod.POST)
    public void doPass(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        if(id <=0){
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.PARAM_MISS)), response);
            return;
        }
        UserDO userDO = userBiz.queryById(id);
        if(userDO == null){
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        userDO.setStatus(UserStatusEnum.NORMAL.getValue());
        try {
            userBiz.update(userDO);
            //创建账户
            AccountDO accontDO = new AccountDO();
            accontDO.setAmount(0L);
            accontDO.setFreeze(0L);
            accontDO.setUserId(userDO.getId());
            accontDO.setGmtModified(new Date());
            accountBiz.create(accontDO);
            ajaxReturn(new AjaxResponce(true), response);
            return;
        }catch(Exception e){
            log.error("",e);
        }
        ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.UNKOWN_ERROR)), response);
    }

    @Form
    @RequestMapping(value = "admin/user/doForbit.json", method = RequestMethod.POST)
    public void doForbit(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        if(id <=0){
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.PARAM_MISS)), response);
            return;
        }
        UserDO userDO = userBiz.queryById(id);
        if(userDO == null){
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        userDO.setStatus(UserStatusEnum.FORBID.getValue());
        try {
            userBiz.update(userDO);
            ajaxReturn(new AjaxResponce(true), response);
            return;
        }catch(Exception e){
            log.error("",e);
        }
        ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.UNKOWN_ERROR)), response);
    }


    @Form
    @RequestMapping(value = "admin/user/doReuse.json", method = RequestMethod.POST)
    public void doReuse(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        if(id <=0){
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.PARAM_MISS)), response);
            return;
        }
        UserDO userDO = userBiz.queryById(id);
        if(userDO == null){
            ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND)), response);
            return;
        }
        userDO.setStatus(UserStatusEnum.NORMAL.getValue());
        try {
            userBiz.update(userDO);
            ajaxReturn(new AjaxResponce(true), response);
            return;
        }catch(Exception e){
            log.error("",e);
        }
        ajaxReturn(new AjaxResponce(false, ErrorCode.getMessage(CommonResultCode.UNKOWN_ERROR)), response);
    }
}
