package guda.task.biz.vo;

import guda.task.dao.domain.*;

/**
 * Created by well on 2014/12/22.
 */
public class TaskAcceptVO {

    private TaskAcceptDO taskAcceptDO;
    private TaskListDO taskListDO;
    private UserDO seller;
    private String searchUrl;
    private String searchKey;
    private String searchResultLocation;

    private TaobaoShopDO shop;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }

    public TaobaoShopDO getShop() {
        return shop;
    }

    public void setShop(TaobaoShopDO shop) {
        this.shop = shop;
    }

    public UserDO getSeller() {
        return seller;
    }

    public void setSeller(UserDO seller) {
        this.seller = seller;
    }

    public String getSearchResultLocation() {
        return searchResultLocation;
    }

    public void setSearchResultLocation(String searchResultLocation) {
        this.searchResultLocation = searchResultLocation;
    }

    public TaskAcceptDO getTaskAcceptDO() {
        return taskAcceptDO;
    }

    public void setTaskAcceptDO(TaskAcceptDO taskAcceptDO) {
        this.taskAcceptDO = taskAcceptDO;
    }

    public TaskListDO getTaskListDO() {
        return taskListDO;
    }

    public void setTaskListDO(TaskListDO taskListDO) {
        this.taskListDO = taskListDO;
    }
}
