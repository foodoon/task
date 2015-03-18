package guda.task.biz;

import guda.task.dao.domain.TaskPropsDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

import java.util.List;
import java.util.Map;

public interface TaskPropsBiz {

        BizResult detail(long id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(long id);

        BizResult create(TaskPropsDO taskPropsDO);

        BizResult update(TaskPropsDO taskPropsDO);

       Map<String,String> queryByTaskId(long taskId);

       void deleteByTaskId(long taskId);

}
