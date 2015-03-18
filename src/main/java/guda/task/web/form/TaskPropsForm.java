package guda.task.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.task.dao.domain.TaskPropsDO;
import javax.validation.constraints.NotNull;

public class TaskPropsForm {
                    @NotNull     private Long taskId;

                    @NotEmpty(message = "不能为空")
            private String requireKey;

                    @NotEmpty(message = "不能为空")
            private String requireValue;

    public Long getTaskId() {
       return taskId;
    }

    public void setTaskId(Long taskId) {
       this.taskId = taskId;
    }
    public String getRequireKey() {
       return requireKey;
    }

    public void setRequireKey(String requireKey) {
       this.requireKey = requireKey;
    }
    public String getRequireValue() {
       return requireValue;
    }

    public void setRequireValue(String requireValue) {
       this.requireValue = requireValue;
    }

    public TaskPropsDO toDO(){
       TaskPropsDO taskPropsDO  = new TaskPropsDO();
            taskPropsDO.setTaskId(this.taskId);
                taskPropsDO.setRequireKey(this.requireKey);
                taskPropsDO.setRequireValue(this.requireValue);
           return taskPropsDO;
}

}
