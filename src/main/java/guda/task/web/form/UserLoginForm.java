package guda.task.web.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by foodoon on 2014/12/21.
 */
public class UserLoginForm {

    @NotEmpty(message = "不能为空")
    private String name;

    @NotEmpty(message = "不能为空")
    private String password;

    private String captcha;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
