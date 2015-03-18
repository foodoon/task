package guda.task.biz.query;

import guda.tools.web.page.BaseQuery;

/**
 * Created by well on 2014/12/22.
 */
public class TaskQuery extends BaseQuery{

    private int status;

    private Long sellerId;

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
