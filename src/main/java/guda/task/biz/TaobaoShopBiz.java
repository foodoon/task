package guda.task.biz;

import guda.task.dao.domain.TaobaoShopDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface TaobaoShopBiz {

        BizResult detail(long id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(long id);

        BizResult create(TaobaoShopDO taobaoShopDO);

        BizResult update(TaobaoShopDO taobaoShopDO);

}
