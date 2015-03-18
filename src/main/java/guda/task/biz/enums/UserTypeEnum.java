package guda.task.biz.enums;

/**
 * Created by foodoon on 2014/12/20.
 */
public enum UserTypeEnum {
    BUYER("买家", 0),
    SELLER("掌柜", 1),
    ADMIN("管理员", 2),
    SUPER_ADMIN("超级管理员", 9),
    ;

    private int value;
    private String name;

    private UserTypeEnum(String name, int val) {
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
        UserTypeEnum[] values = UserTypeEnum.values();
        for(UserTypeEnum e:values){
            if(e.getValue() == val){
                return e.getName();
            }
        }
        return null;
    }
}
