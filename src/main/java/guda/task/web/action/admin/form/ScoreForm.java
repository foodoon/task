package guda.task.web.action.admin.form;

import javax.validation.constraints.NotNull;

/**
 * Created by foodoon on 2014/12/28.
 */
public class ScoreForm {
    @NotNull(message="不能为空")
    private Long taskId;

    @NotNull(message="不能为空")
    private Integer score;

    private String msg;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
