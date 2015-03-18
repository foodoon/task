package guda.task.web.action.admin.form;

import javax.validation.constraints.NotNull;

/**
 * Created by well on 2014/12/29.
 */
public class AdminChargeForm {
    @NotNull(message = "不能为空")
    private Long userId;
    @NotNull(message = "不能为空")
    private long amount;

    private String taobaoUserNick;
    private String loginName;

    public String getTaobaoUserNick() {
        return taobaoUserNick;
    }

    public void setTaobaoUserNick(String taobaoUserNick) {
        this.taobaoUserNick = taobaoUserNick;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
