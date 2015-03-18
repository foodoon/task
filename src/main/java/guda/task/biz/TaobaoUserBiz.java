package guda.task.biz;

import guda.task.dao.domain.TaobaoUserDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface TaobaoUserBiz {

        BizResult detail(long id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(long id);

        BizResult create(TaobaoUserDO taobaoUserDO);

        BizResult update(TaobaoUserDO taobaoUserDO);

    TaobaoUserDO queryByUserId(String userId);

}
