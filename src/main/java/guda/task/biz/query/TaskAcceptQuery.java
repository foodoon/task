package guda.task.biz.query;

import guda.tools.web.page.BaseQuery;

/**
 * Created by well on 2014/12/22.
 */
public class TaskAcceptQuery extends BaseQuery {

    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
