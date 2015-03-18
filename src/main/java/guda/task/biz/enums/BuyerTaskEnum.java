package guda.task.biz.enums;

/**
 * Created by foodoon on 2014/12/20.
 */
public enum BuyerTaskEnum {

    ACCEPT("接受",0),
    FINISH("完成",1),
    SIGN("签收",2),
    SUCCESS("任务成功",9),
    ;

    private int value;
    private String name;
    private BuyerTaskEnum(String name,int val){
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
        BuyerTaskEnum[] values = BuyerTaskEnum.values();
        for(BuyerTaskEnum e:values){
            if(e.getValue() == val){
                return e.getName();
            }
        }
        return null;
    }
}
