package guda.task.biz.vo;

import guda.task.dao.domain.TaskListDO;
import guda.task.dao.domain.UserScoreDO;

/**
 * Created by foodoon on 2014/12/28.
 */
public class UserScoreVO {

    private UserScoreDO userScoreDO;
    private TaskListDO taskListDO;

    public UserScoreDO getUserScoreDO() {
        return userScoreDO;
    }

    public void setUserScoreDO(UserScoreDO userScoreDO) {
        this.userScoreDO = userScoreDO;
    }

    public TaskListDO getTaskListDO() {
        return taskListDO;
    }

    public void setTaskListDO(TaskListDO taskListDO) {
        this.taskListDO = taskListDO;
    }
}
