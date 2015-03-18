package guda.task.web.form;

import guda.task.dao.domain.UserDO;


public class UserEditForm extends UserForm {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDO toDO() {
        UserDO userDO = super.toDO();
        userDO.setId(this.id);
        return userDO;
    }

    public void initForm(UserDO userDO) {
        if (userDO == null) {
            return;
        }
        this.setLoginName(userDO.getLoginName());
        this.setQq(userDO.getQq());
        this.setWangwang(userDO.getWangwang());
        this.setMail(userDO.getMail());
        this.setPhone(userDO.getPhone());
        this.setUserType(userDO.getUserType());
        this.setPassword(userDO.getPassword());
        this.setRecommendQq(userDO.getRecommendQq());
    }

}
