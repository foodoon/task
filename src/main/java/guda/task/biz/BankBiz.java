package guda.task.biz;

import guda.task.dao.domain.BankDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface BankBiz {

        BizResult detail(long id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(long id);

        BizResult create(BankDO bankDO);

        BizResult update(BankDO bankDO);

}
