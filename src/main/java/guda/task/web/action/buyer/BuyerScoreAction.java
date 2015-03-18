package guda.task.web.action.buyer;

import guda.task.App;
import guda.task.biz.UserScoreBiz;
import guda.task.biz.UserScoreStatBiz;
import guda.task.biz.query.AccountDetailQuery;
import guda.task.biz.query.UserScoreQuery;
import guda.task.common.security.AppContexHolder;
import guda.task.dao.domain.UserScoreStatDO;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by foodoon on 2014/12/28.
 */
@Controller
public class BuyerScoreAction {

    @Autowired
    private UserScoreBiz userScoreBiz;
    @Autowired
    private UserScoreStatBiz userScoreStatBiz;

    @RequestMapping(value = "buyer/user/score.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        UserScoreStatDO userScoreStatDO = userScoreStatBiz.queryByUserId(AppContexHolder.getContext().getUserDO().getId());
        if(userScoreStatDO!=null) {
            modelMap.addAttribute("userScore", userScoreStatDO.getScore()*100.00 / userScoreStatDO.getScoreNum()/5 + "%");
        }
        UserScoreQuery userScoreQuery = new UserScoreQuery();
        userScoreQuery.setUserId(AppContexHolder.getContext().getUserDO().getId());
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        userScoreQuery.setPageNo(pageId);
        userScoreQuery.setPageSize(pageSize);
        BizResult bizResult = userScoreBiz.list(userScoreQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "buyer/user/score.vm";
        } else {
            return "500.vm";
        }

    }
}
