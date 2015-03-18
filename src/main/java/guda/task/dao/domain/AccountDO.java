package guda.task.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class AccountDO {

    private Long id;


    private Long userId;
    @GenField(cn="账户余额")
    private Long amount;
    @GenField(cn="冻结金额  ")
    private Long freeze;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getFreeze() {
        return freeze;
    }

    public void setFreeze(Long freeze) {
        this.freeze = freeze;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}