package guda.task.biz;

import guda.task.dao.domain.UserScoreStatDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface UserScoreStatBiz {

        BizResult detail(long id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(long id);

        BizResult create(UserScoreStatDO userScoreStatDO);

        BizResult update(UserScoreStatDO userScoreStatDO);

    UserScoreStatDO  queryByUserId(long userId);

}
