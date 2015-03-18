package guda.task.web.form;

import guda.task.dao.domain.TaobaoItemDO;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class TaobaoItemForm {

    private String taobaoUserId;

    private Long cid;

    private String sellerCids;

    @NotNull(message = "不能为空")
    private String picUrl;

    private Long num;

    private Date listTime;

    private Date delistTime;

    @NotNull(message = "不能为空")
    private String title;

    @NotNull(message = "不能为空")
    private Long price;

    @NotNull(message = "不能为空")
    private String itemUrl;

    private Integer hasDiscount;

    private Integer hasShowcase;

    private String approveStatus;

    private Integer isVirtual;

    private Long numIid;


    private String nick;

    private String type;

    private Long validThru;

    private Long soldQuantity;

    private Integer violation;

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getTaobaoUserId() {
        return taobaoUserId;
    }

    public void setTaobaoUserId(String taobaoUserId) {
        this.taobaoUserId = taobaoUserId;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getSellerCids() {
        return sellerCids;
    }

    public void setSellerCids(String sellerCids) {
        this.sellerCids = sellerCids;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Date getListTime() {
        return listTime;
    }

    public void setListTime(Date listTime) {
        this.listTime = listTime;
    }

    public Date getDelistTime() {
        return delistTime;
    }

    public void setDelistTime(Date delistTime) {
        this.delistTime = delistTime;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(Integer hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public Integer getHasShowcase() {
        return hasShowcase;
    }

    public void setHasShowcase(Integer hasShowcase) {
        this.hasShowcase = hasShowcase;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Long getNumIid() {
        return numIid;
    }

    public void setNumIid(Long numIid) {
        this.numIid = numIid;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getValidThru() {
        return validThru;
    }

    public void setValidThru(Long validThru) {
        this.validThru = validThru;
    }

    public Long getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Long soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public Integer getViolation() {
        return violation;
    }

    public void setViolation(Integer violation) {
        this.violation = violation;
    }

    public TaobaoItemDO toDO() {
        TaobaoItemDO taobaoItemDO = new TaobaoItemDO();
        taobaoItemDO.setTaobaoUserId(this.taobaoUserId);
        taobaoItemDO.setCid(this.cid);
        taobaoItemDO.setSellerCids(this.sellerCids);
        taobaoItemDO.setPicUrl(this.picUrl);
        taobaoItemDO.setNum(this.num);
        taobaoItemDO.setListTime(this.listTime);
        taobaoItemDO.setDelistTime(this.delistTime);
        taobaoItemDO.setPrice(this.price);
        taobaoItemDO.setHasDiscount(this.hasDiscount);
        taobaoItemDO.setHasShowcase(this.hasShowcase);
        taobaoItemDO.setApproveStatus(this.approveStatus);
        taobaoItemDO.setIsVirtual(this.isVirtual);
        taobaoItemDO.setNumIid(this.numIid);
        taobaoItemDO.setTitle(this.title);
        taobaoItemDO.setNick(this.nick);
        taobaoItemDO.setType(this.type);
        taobaoItemDO.setValidThru(this.validThru);
        taobaoItemDO.setSoldQuantity(this.soldQuantity);
        taobaoItemDO.setViolation(this.violation);
        taobaoItemDO.setItemUrl(this.itemUrl);
        return taobaoItemDO;
    }

}
