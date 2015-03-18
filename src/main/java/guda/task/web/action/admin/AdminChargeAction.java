package guda.task.web.action.admin;

import guda.mvc.form.Form;
import guda.task.biz.AccountBiz;
import guda.task.biz.AccountDetailBiz;
import guda.task.biz.UserBiz;
import guda.task.biz.enums.DirectionEnum;
import guda.task.common.util.CommonResultCode;
import guda.task.common.util.ErrorCode;
import guda.task.dao.domain.AccountDO;
import guda.task.dao.domain.AccountDetailDO;
import guda.task.dao.domain.UserDO;
import guda.task.web.action.admin.form.AdminChargeForm;
import guda.task.web.action.seller.form.ActForm;
import guda.tools.web.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

/**
 * Created by well on 2014/12/29.
 */
@Controller
public class AdminChargeAction {

    @Autowired
    private UserBiz userBiz;
    @Autowired
    private AccountBiz accountBiz;
    @Autowired
    private AccountDetailBiz accountDetailBiz;

    @RequestMapping(value = "admin/user/charge.htm", method = RequestMethod.GET)
    public String list(AdminChargeForm adminChargeForm,
                       BindingResult result,HttpServletRequest request, ModelMap modelMap) {
        long userId = RequestUtil.getLong(request,"id");
        UserDO userDO = userBiz.queryById(userId);
        if(userDO == null){
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }
        modelMap.addAttribute("userDO",userDO);
        adminChargeForm.setUserId(userId);
        return "admin/user/charge.vm";

    }

    @Form
    @RequestMapping(value = "admin/user/doCharge.htm", method = RequestMethod.POST)
    public String doCharge(@Valid AdminChargeForm adminChargeForm,
                       BindingResult result,HttpServletRequest request, ModelMap modelMap) {
        if (result.hasErrors()) {
            UserDO userDO = userBiz.queryById(adminChargeForm.getUserId());
            modelMap.addAttribute("userDO",userDO);
            return "admin/user/charge.vm";
        }
        if(adminChargeForm.getAmount()<=0){
            result.rejectValue("amount",CommonResultCode.PARAM_MISS);
            UserDO userDO = userBiz.queryById(adminChargeForm.getUserId());
            modelMap.addAttribute("userDO",userDO);
            return "admin/user/charge.vm";
        }
        UserDO userDO = userBiz.queryById(adminChargeForm.getUserId());
        if(userDO == null){
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.RECORD_NOT_FOUND));
            return "500.vm";
        }
        AccountDO accountDO = accountBiz.queryByUserId(userDO.getId());
        if(accountDO == null){
            modelMap.addAttribute("errorMsg", ErrorCode.getMessage(CommonResultCode.ACCOUNT_NOT_EXIST));
            return "500.vm";
        }
        accountDO.setAmount(accountDO.getAmount() + adminChargeForm.getAmount()*100);
        accountBiz.update(accountDO);
        AccountDetailDO accountDetailDO = new AccountDetailDO();
        accountDetailDO.setAccountFrom(accountDO.getId());
        accountDetailDO.setAmount(accountDO.getId());
        accountDetailDO.setDirection(DirectionEnum.IN.getValue());
        accountDetailDO.setGmtCreated(new Date());
        accountDetailDO.setAmount(adminChargeForm.getAmount()*100);
        accountDetailDO.setRemark("管理员充值");
        accountDetailBiz.create(accountDetailDO);
        return "admin/user/chargeSuccess.vm";

    }
}
