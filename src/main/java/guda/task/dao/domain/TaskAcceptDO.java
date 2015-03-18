package guda.task.dao.domain;

import java.util.Date;

public class TaskAcceptDO {
    private Long id;

    private Long taskId;

    private Long userId;

    private Integer status;

    private String taobaoTradeNo;

    private Date gmtCreated;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTaobaoTradeNo() {
        return taobaoTradeNo;
    }

    public void setTaobaoTradeNo(String taobaoTradeNo) {
        this.taobaoTradeNo = taobaoTradeNo == null ? null : taobaoTradeNo.trim();
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}