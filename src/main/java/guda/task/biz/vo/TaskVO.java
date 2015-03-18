package guda.task.biz.vo;

import guda.task.dao.domain.TaskListDO;

/**
 * Created by foodoon on 2014/12/23.
 */
public class TaskVO {

    private TaskListDO taskListDO;

    private String searchUrl;
    private String searchKey;
    private String searchResultLocation;

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
}
