package guda.task.web.action.admin.form;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by well on 2014/12/27.
 */
public class AdminTaskSearchForm {

    @NotNull(message = "不能为空")
    private Date startDate;
    @NotNull(message = "不能为空")
    private Date endDate;
    private String sellerNick;
    private Integer status = 999;

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
