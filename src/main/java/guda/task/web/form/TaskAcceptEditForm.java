package guda.task.web.form;

import guda.task.dao.domain.TaskAcceptDO;


public class TaskAcceptEditForm extends TaskAcceptForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskAcceptDO toDO(){
        TaskAcceptDO taskAcceptDO  =super.toDO();
        taskAcceptDO.setId(this.id);
        return taskAcceptDO;
    }

    public void initForm(TaskAcceptDO taskAcceptDO){
        if(taskAcceptDO == null){
        return ;
    }
    this.setTaskId(taskAcceptDO.getTaskId());
    this.setUserId(taskAcceptDO.getUserId());
    this.setStatus(taskAcceptDO.getStatus());
}

}
