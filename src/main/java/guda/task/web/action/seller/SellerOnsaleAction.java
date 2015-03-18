package guda.task.web.action.seller;

import guda.task.biz.TaobaoItemBiz;
import guda.task.biz.query.TaobaoItemQuery;
import guda.task.common.security.AppContexHolder;
import guda.task.dao.domain.TaobaoItemDO;
import guda.task.web.form.TaobaoItemForm;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by foodoon on 2014/12/21.
 */
@Controller
public class SellerOnsaleAction {

    @Autowired
    private TaobaoItemBiz taobaoItemBiz;

    @RequestMapping(value = "/seller/act/onsale.htm", method = RequestMethod.GET)
    public String doGet(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        TaobaoItemQuery taobaoItemQuery = new TaobaoItemQuery();
        taobaoItemQuery.setPageNo(pageId);
        taobaoItemQuery.setPageSize(pageSize);
        taobaoItemQuery.setTaobaoUserId(AppContexHolder.getContext().getUserDO().getTaobaoUserId());
        BizResult bizResult = taobaoItemBiz.list(taobaoItemQuery);
        modelMap.put("query",taobaoItemQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "seller/act/onsale.vm";
        } else {
            return "common/error.vm";
        }
    }
}
