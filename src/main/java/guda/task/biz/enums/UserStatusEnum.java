package guda.task.biz.enums;

/**
 * Created by foodoon on 2014/12/20.
 */
public enum UserStatusEnum {

    INIT("初始化",0),
    NORMAL("正常",1),
    FORBID("禁用",2),
    PUNISH("处罚",3),
    ;

    private int value;
    private String name;
    private UserStatusEnum(String name,int val){
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
        UserStatusEnum[] values = UserStatusEnum.values();
        for(UserStatusEnum e:values){
            if(e.getValue() == val){
                return e.getName();
            }
        }
        return null;
    }
}
