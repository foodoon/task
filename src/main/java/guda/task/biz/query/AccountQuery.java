package guda.task.biz.query;

import guda.tools.web.page.BaseQuery;

/**
 * Created by foodoon on 2014/12/27.
 */
public class AccountQuery extends BaseQuery{

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
