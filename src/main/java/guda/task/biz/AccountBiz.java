package guda.task.biz;

import guda.task.biz.query.AccountQuery;
import guda.task.dao.domain.AccountDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface AccountBiz {

        BizResult detail(long id);

        BizResult list(AccountQuery accountQuery);

        BizResult delete(long id);

        BizResult create(AccountDO accountDO);

        BizResult update(AccountDO accountDO);

        AccountDO queryByUserId(long userId);

}
