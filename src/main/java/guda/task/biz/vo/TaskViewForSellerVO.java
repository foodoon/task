package guda.task.biz.vo;

import guda.task.dao.domain.TaskAcceptDO;
import guda.task.dao.domain.TaskListDO;
import guda.task.dao.domain.UserDO;

/**
 * Created by well on 2014/12/26.
 */
public class TaskViewForSellerVO {

    private TaskListDO taskListDO;

    private String searchUrl;
    private String searchKey;
    private String searchResultLocation;

    private TaskAcceptDO taskAcceptDO;

    private UserDO acceptUser;

    private UserDO sellerUser;

    public UserDO getSellerUser() {
        return sellerUser;
    }

    public void setSellerUser(UserDO sellerUser) {
        this.sellerUser = sellerUser;
    }

    public UserDO getAcceptUser() {
        return acceptUser;
    }

    public void setAcceptUser(UserDO acceptUser) {
        this.acceptUser = acceptUser;
    }

    public TaskListDO getTaskListDO() {
        return taskListDO;
    }

    public void setTaskListDO(TaskListDO taskListDO) {
        this.taskListDO = taskListDO;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
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
}
