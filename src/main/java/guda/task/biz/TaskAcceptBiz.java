package guda.task.biz;

import guda.task.biz.query.TaskAcceptQuery;
import guda.task.dao.domain.TaskAcceptDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface TaskAcceptBiz {

    BizResult detail(long id);

    BizResult list(BaseQuery baseQuery);

    BizResult delete(long id);

    BizResult create(TaskAcceptDO taskAcceptDO);

    BizResult update(TaskAcceptDO taskAcceptDO);

    BizResult listIngForBuyer(TaskAcceptQuery taskAcceptQuery);

    BizResult listHisForBuyer(TaskAcceptQuery taskAcceptQuery);

    TaskAcceptDO queryById(long id);

    TaskAcceptDO queryByTaskIdAndStatusInSing(long taskId);

    boolean canAcceptCheckWeek(long userid);

    TaskAcceptDO queryValidAccept(long taskId);

    TaskAcceptDO query(long user,long taskId);

}
