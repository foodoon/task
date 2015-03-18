package guda.task.biz;

import guda.task.biz.query.AdminTaskQuery;
import guda.tools.web.page.BizResult;

/**
 * Created by foodoon on 2014/12/26.
 */
public interface AdminTaskBiz {

    BizResult listTask(AdminTaskQuery adminTaskQuery);
}
