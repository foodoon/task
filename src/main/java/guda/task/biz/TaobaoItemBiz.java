package guda.task.biz;

import guda.task.biz.query.TaobaoItemQuery;
import guda.task.dao.domain.TaobaoItemDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

import java.util.List;

public interface TaobaoItemBiz {

        BizResult detail(long id);

        BizResult list(TaobaoItemQuery taobaoItemQuery);

        BizResult delete(long id);

        BizResult create(TaobaoItemDO taobaoItemDO);

        BizResult update(TaobaoItemDO taobaoItemDO);

        TaobaoItemDO queryById(long id);

        TaobaoItemDO queryByItemId(long itemId);


}
