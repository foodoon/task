package guda.task.biz.vo;

import guda.task.dao.domain.AccountDetailDO;
import guda.task.dao.domain.TaskListDO;

/**
 * Created by foodoon on 2014/12/28.
 */
public class AccountDetailVO {

    private AccountDetailDO accountDetailDO;

    private TaskListDO taskListDO;

    public AccountDetailDO getAccountDetailDO() {
        return accountDetailDO;
    }

    public void setAccountDetailDO(AccountDetailDO accountDetailDO) {
        this.accountDetailDO = accountDetailDO;
    }

    public TaskListDO getTaskListDO() {
        return taskListDO;
    }

    public void setTaskListDO(TaskListDO taskListDO) {
        this.taskListDO = taskListDO;
    }
}
