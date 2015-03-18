package guda.task.biz;

import guda.task.biz.query.AccountDetailQuery;
import guda.task.biz.query.AccountQuery;
import guda.task.dao.domain.AccountDetailDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface AccountDetailBiz {

        BizResult detail(long id);

        BizResult list(AccountDetailQuery accountDetailQuery);

        BizResult delete(long id);

        BizResult create(AccountDetailDO accountDetailDO);

        BizResult update(AccountDetailDO accountDetailDO);

}
