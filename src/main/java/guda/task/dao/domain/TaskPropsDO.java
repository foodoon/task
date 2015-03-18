package guda.task.dao.domain;

import guda.gen.GenField;

public class TaskPropsDO {
    private Long id;
    @GenField(cn="任务ID")
    private Long taskId;
    @GenField(cn="属性")
    private String requireKey;
    @GenField(cn="属性值")
    private String requireValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getRequireKey() {
        return requireKey;
    }

    public void setRequireKey(String requireKey) {
        this.requireKey = requireKey == null ? null : requireKey.trim();
    }

    public String getRequireValue() {
        return requireValue;
    }

    public void setRequireValue(String requireValue) {
        this.requireValue = requireValue == null ? null : requireValue.trim();
    }
}