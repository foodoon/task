package guda.task.biz.enums;

/**
 * Created by well on 2014/12/22.
 */
public enum TaskAcceptStatusEnum {
    ACCEPT("您已经接受任务，请尽快完成任务",0),

    TAOBAO_STATUS("淘宝交易进行中",7),
    SIGN("您已经签收，等待掌柜确认",8),
    BREAK("终止",98),
    SUCCESS("任务成功",99),
    ;

    private int value;
    private String name;
    private TaskAcceptStatusEnum(String name,int val){
        this.name = name;
        this.value = val;
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByValue(int val){
        TaskAcceptStatusEnum[] values = TaskAcceptStatusEnum.values();
        for(TaskAcceptStatusEnum e:values){
            if(e.getValue() == val){
                return e.getName();
            }
        }
        return null;
    }
}
