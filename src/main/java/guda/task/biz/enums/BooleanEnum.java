package guda.task.biz.enums;

/**
 * Created by foodoon on 2014/12/21.
 */
public enum BooleanEnum {

    TRUE("是",1),
    FALSE("否",0),
            ;

    private int value;
    private String name;
    private BooleanEnum(String name,int val){
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
        BooleanEnum[] values = BooleanEnum.values();
        for(BooleanEnum e:values){
            if(e.getValue() == val){
                return e.getName();
            }
        }
        return null;
    }
}
