package guda.task.biz.enums;

/**
 * Created by foodoon on 2014/12/20.
 */
public enum TaskStatusEnum {

    INIT("初始化",0),
    PUBLISH("已经发布，等待拍客接受",1),
    ACCEPT("拍客已接受，等待拍客完成任务",2),

    TAOBAO_STATUS("淘宝交易进行中，等待拍客签收",7),
    SIGN("拍客签收",8),
    BREAK("终止",98),
    SUCCESS("任务成功",99),
    ;

    private int value;
    private String name;
    private TaskStatusEnum(String name,int val){
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
        TaskStatusEnum[] values = TaskStatusEnum.values();
        for(TaskStatusEnum e:values){
            if(e.getValue() == val){
                return e.getName();
            }
        }
        return null;
    }
}
