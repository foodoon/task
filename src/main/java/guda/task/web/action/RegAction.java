package guda.task.web.action;

import guda.mvc.form.Form;
import guda.task.biz.UserBiz;
import guda.task.biz.enums.UserStatusEnum;
import guda.task.biz.enums.UserTypeEnum;
import guda.task.common.util.CommonResultCode;
import guda.task.common.util.MD5;
import guda.task.dao.domain.UserDO;
import guda.task.web.form.SellerRegForm;
import guda.task.web.form.UserForm;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by foodoon on 2014/12/20.
 */
@Controller
public class RegAction {


    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "reg.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, UserForm userForm,
                         BindingResult result, Map<String,Object> model) {
        return "reg.vm";
    }
    @Form
    @RequestMapping(value = "doReg.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid UserForm userForm,
                           BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "reg.vm";
        }
        if(userBiz.queryUser(userForm.getLoginName())!=null){
            result.rejectValue("loginName", CommonResultCode.USER_NAME_DUPLICATE);
            return "reg.vm";
        }
        //校验手机是否用过
        if(userBiz.checkEmailExist(userForm.getMail())){
            result.rejectValue("mail", CommonResultCode.REG_ERROR_EMAIL_EXIST);
            return "reg.vm";
        }
        if(userBiz.checkPhoneExist(userForm.getPhone())){
            result.rejectValue("phone", CommonResultCode.REG_ERROR_PHONE_EXIST);
            return "reg.vm";
        }
        //校验邮箱是否用过
        UserDO userDO = userForm.toDO();
        userDO.setStatus(UserStatusEnum.INIT.getValue());
        userDO.setUserType(UserTypeEnum.BUYER.getValue());
        userDO.setPassword(MD5.md5(userForm.getPassword()));
        BizResult bizResult = userBiz.create(userDO);
        if (bizResult.success) {
            return "redirect:/regSuccess.htm";
        } else {
            return "redirect:/500.htm";
        }

    }


    @RequestMapping(value = "sellerReg.htm", method = RequestMethod.GET)
    public String sellerReg(HttpServletRequest request, ModelMap modelMap, SellerRegForm sellerRegForm,
                         BindingResult result, Map<String,Object> model) {

        return "regSeller.vm";
    }

    @Form
    @RequestMapping(value = "doSellerReg.htm", method = RequestMethod.POST)
    public String doSellerReg(HttpServletRequest request, ModelMap modelMap,@Valid SellerRegForm sellerRegForm,
                           BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "regSeller.vm";
        }
        if(userBiz.queryUser(sellerRegForm.getLoginName())!=null){
            result.rejectValue("loginName", CommonResultCode.USER_NAME_DUPLICATE);
            return "regSeller.vm";
        }
        //校验手机是否用过
        if(userBiz.checkEmailExist(sellerRegForm.getMail())){
            result.rejectValue("mail", CommonResultCode.REG_ERROR_EMAIL_EXIST);
            return "regSeller.vm";
        }
        if(userBiz.checkPhoneExist(sellerRegForm.getPhone())){
            result.rejectValue("phone", CommonResultCode.REG_ERROR_PHONE_EXIST);
            return "regSeller.vm";
        }
        //校验邮箱是否用过
        UserDO userDO = sellerRegForm.toDO();
        userDO.setShopUrl(sellerRegForm.getShopUrl());
        userDO.setStatus(UserStatusEnum.INIT.getValue());
        userDO.setPassword(MD5.md5(sellerRegForm.getPassword()));
        userDO.setUserType(UserTypeEnum.SELLER.getValue());
        BizResult bizResult = userBiz.create(userDO);
        if (bizResult.success) {
            return "redirect:/regSuccess.htm";
        } else {
            return "redirect:/500.htm";
        }

    }
}
