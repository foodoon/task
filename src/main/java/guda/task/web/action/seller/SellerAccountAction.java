package guda.task.web.action.seller;

import guda.task.biz.AccountBiz;
import guda.task.biz.AccountDetailBiz;
import guda.task.biz.query.AccountDetailQuery;
import guda.task.common.security.AppContexHolder;
import guda.task.dao.domain.AccountDO;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by well on 2014/12/29.
 */
@Controller
public class SellerAccountAction {

    @Autowired
    private AccountDetailBiz accountDetailBiz;
    @Autowired
    private AccountBiz accountBiz;

    @RequestMapping(value = "seller/user/account.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        AccountDO accountDO = accountBiz.queryByUserId(AppContexHolder.getContext().getUserDO().getId());
        modelMap.addAttribute("accountDO",accountDO);
        AccountDetailQuery accountDetailQuery = new AccountDetailQuery();
        accountDetailQuery.setAccountId(accountDO.getId());
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        accountDetailQuery.setPageNo(pageId);
        accountDetailQuery.setPageSize(pageSize);
        BizResult bizResult = accountDetailBiz.list(accountDetailQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "seller/user/account.vm";
        } else {
            return "500.vm";
        }
    }

    @RequestMapping(value = "seller/user/account/list.htm", method = RequestMethod.GET)
    public String accountList(HttpServletRequest request, ModelMap modelMap) {
        AccountDO accountDO = accountBiz.queryByUserId(AppContexHolder.getContext().getUserDO().getId());
        modelMap.addAttribute("accountDO",accountDO);
        AccountDetailQuery accountDetailQuery = new AccountDetailQuery();
        accountDetailQuery.setAccountId(accountDO.getId());
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        accountDetailQuery.setPageNo(pageId);
        accountDetailQuery.setPageSize(pageSize);
        BizResult bizResult = accountDetailBiz.list(accountDetailQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "seller/user/account/list.vm";
        } else {
            return "500.vm";
        }
    }
}
