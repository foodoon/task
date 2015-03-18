package guda.task.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import guda.task.dao.domain.UserDO;

import javax.validation.constraints.NotNull;

public class UserForm {
    @NotEmpty(message = "不能为空")
    @Length(min=4,max=12 ,message = "长度在4到12个字符之间")
    private String loginName;

    @NotEmpty(message = "不能为空")
    @Length(min=4,message = "最少4个字符")
    private String password;

    @NotEmpty(message = "不能为空")
    @Length(min=4,message = "最少4个字符")
    private String qq;

    @NotEmpty(message = "不能为空")
    private String wangwang;

    @NotEmpty(message = "不能为空")
    @Email(message = "{邮箱格式不正确}")
    private String mail;

    @NotEmpty(message = "不能为空")
    private String phone;

    @NotEmpty(message = "不能为空")
    private String recommendQq;

    private Integer userType;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWangwang() {
        return wangwang;
    }

    public void setWangwang(String wangwang) {
        this.wangwang = wangwang;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getRecommendQq() {
        return recommendQq;
    }

    public void setRecommendQq(String recommendQq) {
        this.recommendQq = recommendQq;
    }

    public UserDO toDO() {
        UserDO userDO = new UserDO();
        userDO.setLoginName(this.loginName);
        userDO.setQq(this.qq);
        userDO.setWangwang(this.wangwang);
        userDO.setMail(this.mail);
        userDO.setPhone(this.phone);
        userDO.setUserType(this.userType);
        userDO.setPassword(this.password);
        userDO.setRecommendQq(this.recommendQq);
        return userDO;
    }

}
