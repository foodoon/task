package guda.task.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class TaobaoShopDO {

    private Long id;

    @GenField(cn="淘宝用户ID")
    private String taobaoUserId;
    @GenField(cn="店铺ID")
    private Long sid;
    @GenField(cn="店铺地址")
    private String shopUrl;
    @GenField(cn="店铺所属类目编号")
    private Long cid;
    @GenField(cn="卖家昵称")
    private String nick;
    @GenField(cn="店铺标题")
    private String title;
    @GenField(cn="店铺公告")
    private String descript;
    @GenField(cn="店铺创建时间")
    private Date created;
    @GenField(cn="商品描述评分")
    private String itemScore;
    @GenField(cn="服务态度评分")
    private String serviceScore;
    @GenField(cn="发货速度评分")
    private String deliveryScore;

    private Date gmtCreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaobaoUserId() {
        return taobaoUserId;
    }

    public void setTaobaoUserId(String taobaoUserId) {
        this.taobaoUserId = taobaoUserId == null ? null : taobaoUserId.trim();
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl == null ? null : shopUrl.trim();
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript == null ? null : descript.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getItemScore() {
        return itemScore;
    }

    public void setItemScore(String itemScore) {
        this.itemScore = itemScore == null ? null : itemScore.trim();
    }

    public String getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(String serviceScore) {
        this.serviceScore = serviceScore == null ? null : serviceScore.trim();
    }

    public String getDeliveryScore() {
        return deliveryScore;
    }

    public void setDeliveryScore(String deliveryScore) {
        this.deliveryScore = deliveryScore == null ? null : deliveryScore.trim();
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
}