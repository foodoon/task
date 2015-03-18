package guda.task.biz.query;

import guda.tools.web.page.BaseQuery;

/**
 * Created by foodoon on 2014/12/21.
 */
public class TaobaoItemQuery extends BaseQuery {

    private String taobaoUserId;

    public String getTaobaoUserId() {
        return taobaoUserId;
    }

    public void setTaobaoUserId(String taobaoUserId) {
        this.taobaoUserId = taobaoUserId;
    }
}
