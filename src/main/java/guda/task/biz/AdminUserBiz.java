package guda.task.biz;

import guda.task.biz.query.AdminUserQuery;
import guda.tools.web.page.BizResult;

/**
 * Created by well on 2014/12/27.
 */
public interface AdminUserBiz {

    BizResult listUser(AdminUserQuery adminUserQuery);
}
