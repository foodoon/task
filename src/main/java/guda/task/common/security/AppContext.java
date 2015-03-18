package guda.task.common.security;

import guda.task.biz.enums.UserTypeEnum;
import guda.task.dao.domain.UserDO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by foodoon on 2014/12/20.
 */
public class AppContext {

    private UserDO userDO;

    public boolean auth(){
        if(userDO == null){
            return false;
        }
        return true;
    }

    public boolean isSeller(){
        if(userDO == null){
            return false;
        }
        return userDO.getUserType().intValue() == UserTypeEnum.SELLER.getValue();
    }

    public boolean isBuyer(){
        if(userDO == null){
            return false;
        }
        return userDO.getUserType().intValue() == UserTypeEnum.BUYER.getValue();
    }

    public boolean isAdmin(){
        if(userDO == null){
            return false;
        }
        return userDO.getUserType().intValue() == UserTypeEnum.ADMIN.getValue();
    }

    public boolean isSupper(){
        if(userDO == null){
            return false;
        }
        return userDO.getUserType().intValue() == UserTypeEnum.SUPER_ADMIN.getValue();
    }

    private Map<String,Object> attr = new HashMap<String,Object>();

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }

    public void putAttr(String key,Object val){
        attr.put(key,val);
    }

    public Object getAttr(String key){
        return attr.get(key);
    }


}
