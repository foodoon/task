package guda.task.biz;

import guda.task.biz.query.TaskQuery;
import guda.task.dao.domain.TaskListDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface TaskListBiz {

        BizResult detail(long id);

        BizResult list(TaskQuery taskQuery);

        BizResult delete(long id);

        BizResult create(TaskListDO taskListDO);

        BizResult update(TaskListDO taskListDO);

       TaskListDO queryById(long id);

       BizResult listIng(TaskQuery taskQuery);

       BizResult listHis(TaskQuery taskQuery);


       BizResult listPublishForBuyer(TaskQuery taskQuery);



}
