package guda.task.biz.enums;

/**
 * Created by foodoon on 2014/12/27.
 */
public enum DirectionEnum {

    IN("收入",1),
    OUT("支出",2),
    FREEZE("冻结",3),
    UNFREEZE("解冻",4),
    ;

    private int value;
    private String name;
    private DirectionEnum(String name,int val){
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
        DirectionEnum[] values = DirectionEnum.values();
        for(DirectionEnum e:values){
            if(e.getValue() == val){
                return e.getName();
            }
        }
        return null;
    }
}
