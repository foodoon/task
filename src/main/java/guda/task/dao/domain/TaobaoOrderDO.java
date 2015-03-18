package guda.task.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class TaobaoOrderDO {
    private Long id;

    @GenField(cn="用户ID")
    private String taobaoUserId;
    @GenField(cn="卖家昵称")
    private String sellerNick;
    @GenField(cn="图片地址")
    private String picPath;
    @GenField(cn="实付金额")
    private String payment;
    @GenField(cn="收货方姓名")
    private String receiverName;
    @GenField(cn="收货地区")
    private String receiverState;
    @GenField(cn="收货地址")
    private String receiverAddress;
    @GenField(cn="收货邮编")
    private String receiverZip;
    @GenField(cn="收货方手机号")
    private String receiverMobile;
    @GenField(cn="收货方电话")
    private String receiverPhone;

    private Date consignTime;
    @GenField(cn="实收金额")
    private String receivedPayment;

    @GenField(cn="交易编号")
    private Long tid;
    @GenField(cn="购买数量")
    private Long num;
    @GenField(cn="商品ID")
    private Long numIid;
    @GenField(cn="订单数量")
    private String status;
    @GenField(cn="交易类型")
    private String type;
    @GenField(cn="商品价格")
    private String price;

    private String discountFee;
    @GenField(cn="买家使用积分")
    private Long pointFee;
    @GenField(cn="商品金额")
    private String totalFee;
    @GenField(cn="订单创建时间")
    private Date created;
    @GenField(cn="买家付款时间")
    private Date payTime;
    @GenField(cn="交易修改时间")
    private Date modified;
    @GenField(cn="交易结束时间")
    private Date endTime;
    @GenField(cn="买家支付宝ID")
    private Long alipayId;
    @GenField(cn="支付宝交易号")
    private String alipayNo;

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

    public String getSellerNick() {
        return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
        this.sellerNick = sellerNick == null ? null : sellerNick.trim();
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState == null ? null : receiverState.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public String getReceiverZip() {
        return receiverZip;
    }

    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip == null ? null : receiverZip.trim();
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile == null ? null : receiverMobile.trim();
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    public Date getConsignTime() {
        return consignTime;
    }

    public void setConsignTime(Date consignTime) {
        this.consignTime = consignTime;
    }

    public String getReceivedPayment() {
        return receivedPayment;
    }

    public void setReceivedPayment(String receivedPayment) {
        this.receivedPayment = receivedPayment == null ? null : receivedPayment.trim();
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getNumIid() {
        return numIid;
    }

    public void setNumIid(Long numIid) {
        this.numIid = numIid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(String discountFee) {
        this.discountFee = discountFee == null ? null : discountFee.trim();
    }

    public Long getPointFee() {
        return pointFee;
    }

    public void setPointFee(Long pointFee) {
        this.pointFee = pointFee;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee == null ? null : totalFee.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getAlipayId() {
        return alipayId;
    }

    public void setAlipayId(Long alipayId) {
        this.alipayId = alipayId;
    }

    public String getAlipayNo() {
        return alipayNo;
    }

    public void setAlipayNo(String alipayNo) {
        this.alipayNo = alipayNo == null ? null : alipayNo.trim();
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
}