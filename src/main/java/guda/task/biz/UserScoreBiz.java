package guda.task.biz;

import guda.task.biz.query.UserScoreQuery;
import guda.task.dao.domain.UserScoreDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface UserScoreBiz {

        BizResult detail(long id);

        BizResult list(UserScoreQuery userScoreQuery);

        BizResult delete(long id);

        BizResult create(UserScoreDO userScoreDO);

        BizResult update(UserScoreDO userScoreDO);

}
