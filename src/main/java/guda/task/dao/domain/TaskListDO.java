package guda.task.dao.domain;

import java.util.Date;

public class TaskListDO {
    private Long id;

    private Long sellerId;

    private String sellerName;

    private Long amountPay;

    private Long amountFee;

    private String itemId;

    private String itemName;

    private String itemUrl;

    private String itemPic;

    private String descript;

    private String comment;

    private Integer status;

    private Integer scored;

    private Date gmtCreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
    }

    public Long getAmountPay() {
        return amountPay;
    }

    public void setAmountPay(Long amountPay) {
        this.amountPay = amountPay;
    }

    public Long getAmountFee() {
        return amountFee;
    }

    public void setAmountFee(Long amountFee) {
        this.amountFee = amountFee;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl == null ? null : itemUrl.trim();
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic == null ? null : itemPic.trim();
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript == null ? null : descript.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getScored() {
        return scored;
    }

    public void setScored(Integer scored) {
        this.scored = scored;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
}