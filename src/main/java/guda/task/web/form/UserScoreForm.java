package guda.task.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.task.dao.domain.UserScoreDO;
import javax.validation.constraints.NotNull;

public class UserScoreForm {

    public UserScoreDO toDO(){
       UserScoreDO userScoreDO  = new UserScoreDO();
       return userScoreDO;
}

}
