package guda.task.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.task.dao.domain.TaobaoShopDO;
import javax.validation.constraints.NotNull;

public class TaobaoShopForm {
                    @NotEmpty(message = "{不能为空}")
            private String deliveryScore;

                    @NotEmpty(message = "{不能为空}")
            private String serviceScore;

                    @NotEmpty(message = "{不能为空}")
            private String itemScore;

                    @NotNull     private Date created;

                    @NotEmpty(message = "{不能为空}")
            private String descript;

                    @NotEmpty(message = "{不能为空}")
            private String title;

                    @NotEmpty(message = "{不能为空}")
            private String nick;

                    @NotNull     private Long cid;

                    @NotEmpty(message = "{不能为空}")
            private String shopUrl;

                    @NotNull     private Long sid;

                    @NotEmpty(message = "{不能为空}")
            private String taobaoUserId;

    public String getDeliveryScore() {
       return deliveryScore;
    }

    public void setDeliveryScore(String deliveryScore) {
       this.deliveryScore = deliveryScore;
    }
    public String getServiceScore() {
       return serviceScore;
    }

    public void setServiceScore(String serviceScore) {
       this.serviceScore = serviceScore;
    }
    public String getItemScore() {
       return itemScore;
    }

    public void setItemScore(String itemScore) {
       this.itemScore = itemScore;
    }
    public Date getCreated() {
       return created;
    }

    public void setCreated(Date created) {
       this.created = created;
    }
    public String getDescript() {
       return descript;
    }

    public void setDescript(String descript) {
       this.descript = descript;
    }
    public String getTitle() {
       return title;
    }

    public void setTitle(String title) {
       this.title = title;
    }
    public String getNick() {
       return nick;
    }

    public void setNick(String nick) {
       this.nick = nick;
    }
    public Long getCid() {
       return cid;
    }

    public void setCid(Long cid) {
       this.cid = cid;
    }
    public String getShopUrl() {
       return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
       this.shopUrl = shopUrl;
    }
    public Long getSid() {
       return sid;
    }

    public void setSid(Long sid) {
       this.sid = sid;
    }
    public String getTaobaoUserId() {
       return taobaoUserId;
    }

    public void setTaobaoUserId(String taobaoUserId) {
       this.taobaoUserId = taobaoUserId;
    }

    public TaobaoShopDO toDO(){
       TaobaoShopDO taobaoShopDO  = new TaobaoShopDO();
            taobaoShopDO.setDeliveryScore(this.deliveryScore);
                taobaoShopDO.setServiceScore(this.serviceScore);
                taobaoShopDO.setItemScore(this.itemScore);
                taobaoShopDO.setCreated(this.created);
                taobaoShopDO.setDescript(this.descript);
                taobaoShopDO.setTitle(this.title);
                taobaoShopDO.setNick(this.nick);
                taobaoShopDO.setCid(this.cid);
                taobaoShopDO.setShopUrl(this.shopUrl);
                taobaoShopDO.setSid(this.sid);
                taobaoShopDO.setTaobaoUserId(this.taobaoUserId);
           return taobaoShopDO;
}

}
