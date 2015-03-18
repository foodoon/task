package guda.task.dao.domain;

import java.util.Date;

public class OperateLogDO {
    private Long id;

    private Long userId;

    private String operate;

    private String operateData;

    private Date gmtCreated;

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

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate == null ? null : operate.trim();
    }

    public String getOperateData() {
        return operateData;
    }

    public void setOperateData(String operateData) {
        this.operateData = operateData == null ? null : operateData.trim();
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
}