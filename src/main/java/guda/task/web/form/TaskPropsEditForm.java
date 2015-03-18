package guda.task.web.form;

import guda.task.dao.domain.TaskPropsDO;


public class TaskPropsEditForm extends TaskPropsForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskPropsDO toDO(){
        TaskPropsDO taskPropsDO  =super.toDO();
        taskPropsDO.setId(this.id);
        return taskPropsDO;
    }

    public void initForm(TaskPropsDO taskPropsDO){
        if(taskPropsDO == null){
        return ;
    }
    this.setTaskId(taskPropsDO.getTaskId());
    this.setRequireKey(taskPropsDO.getRequireKey());
    this.setRequireValue(taskPropsDO.getRequireValue());
}

}
