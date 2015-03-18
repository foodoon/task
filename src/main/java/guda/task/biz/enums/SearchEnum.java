package guda.task.biz.enums;

/**
 * Created by well on 2014/12/23.
 */
public enum SearchEnum {

    TAOBAO("http://taobao.com",0),
    TMALL("http://tmall.com",1),
    ;

    private int value;
    private String name;
    private SearchEnum(String name,int val){
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
        SearchEnum[] values = SearchEnum.values();
        for(SearchEnum e:values){
            if(e.getValue() == val){
                return e.getName();
            }
        }
        return null;
    }

    public static Integer getValueByName(String name){
        SearchEnum[] values = SearchEnum.values();
        for(SearchEnum e:values){
            if(e.getName().equalsIgnoreCase(name)){
                return e.getValue();
            }
        }
        return null;
    }

}
