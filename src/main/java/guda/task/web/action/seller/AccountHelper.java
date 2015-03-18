package guda.task.web.action.seller;

import guda.task.dao.domain.TaskListDO;

/**
 * Created by well on 2014/12/29.
 */
public class AccountHelper {

    public static long count(TaskListDO taskListDO){
        long goods = 500;
        return taskListDO.getAmountFee() + taskListDO.getAmountPay() + goods + taskListDO.getAmountFee() /10;
    }
}
