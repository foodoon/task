package guda.task.biz.query;

import guda.tools.web.page.BaseQuery;

import java.util.Date;

/**
 * Created by foodoon on 2014/12/26.
 */
public class AdminTaskQuery extends BaseQuery {

    private Date startDate;
    private Date endDate;

    private String sellerNick;
    private Integer status;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSellerNick() {
        return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
        this.sellerNick = sellerNick;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
