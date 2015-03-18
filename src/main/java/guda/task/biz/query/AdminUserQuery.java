package guda.task.biz.query;

import guda.tools.web.page.BaseQuery;

/**
 * Created by well on 2014/12/27.
 */
public class AdminUserQuery extends BaseQuery{

    private String loginName;
    private Integer status;
    private String taobaoUserNick;
    private String wangwang;

    public String getWangwang() {
        return wangwang;
    }

    public void setWangwang(String wangwang) {
        this.wangwang = wangwang;
    }

    public String getTaobaoUserNick() {
        return taobaoUserNick;
    }

    public void setTaobaoUserNick(String taobaoUserNick) {
        this.taobaoUserNick = taobaoUserNick;
    }

    public AdminUserQuery() {
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
