package guda.task.web.form;

import guda.task.dao.domain.UserScoreDO;


public class UserScoreEditForm extends UserScoreForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserScoreDO toDO(){
        UserScoreDO userScoreDO  =super.toDO();
        userScoreDO.setId(this.id);
        return userScoreDO;
    }

    public void initForm(UserScoreDO userScoreDO){
        if(userScoreDO == null){
        return ;
    }
}

}
