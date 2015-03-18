package guda.task.biz.query;

import guda.tools.web.page.BaseQuery;

/**
 * Created by foodoon on 2014/12/28.
 */
public class AccountDetailQuery extends BaseQuery{

    private Long accountId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
