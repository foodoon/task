package guda.task.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.task.dao.domain.TaskAcceptDO;
import javax.validation.constraints.NotNull;

public class TaskAcceptForm {
                    @NotNull     private Long taskId;

                    @NotNull     private Long userId;

                    @NotNull     private Integer status;

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

    public TaskAcceptDO toDO(){
       TaskAcceptDO taskAcceptDO  = new TaskAcceptDO();
            taskAcceptDO.setTaskId(this.taskId);
                taskAcceptDO.setUserId(this.userId);
                taskAcceptDO.setStatus(this.status);
           return taskAcceptDO;
}

}
