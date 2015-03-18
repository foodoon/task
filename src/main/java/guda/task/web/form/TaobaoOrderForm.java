package guda.task.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.task.dao.domain.TaobaoOrderDO;
import javax.validation.constraints.NotNull;

public class TaobaoOrderForm {
                    @NotEmpty(message = "{不能为空}")
            private String taobaoUserId;

                    @NotEmpty(message = "{不能为空}")
            private String sellerNick;

                    @NotEmpty(message = "{不能为空}")
            private String picPath;

                    @NotEmpty(message = "{不能为空}")
            private String payment;

                    @NotEmpty(message = "{不能为空}")
            private String receiverName;

                    @NotEmpty(message = "{不能为空}")
            private String receiverState;

                    @NotEmpty(message = "{不能为空}")
            private String receiverAddress;

                    @NotEmpty(message = "{不能为空}")
            private String receiverZip;

                    @NotEmpty(message = "{不能为空}")
            private String receiverMobile;

                    @NotEmpty(message = "{不能为空}")
            private String receiverPhone;

                    @NotEmpty(message = "{不能为空}")
            private String receivedPayment;

                    @NotNull     private Long tid;

                    @NotNull     private Long num;

                    @NotNull     private Long numIid;

                    @NotEmpty(message = "{不能为空}")
            private String status;

                    @NotEmpty(message = "{不能为空}")
            private String type;

                    @NotEmpty(message = "{不能为空}")
            private String price;

                    @NotNull     private Long pointFee;

                    @NotEmpty(message = "{不能为空}")
            private String totalFee;

                    @NotNull     private Date created;

                    @NotNull     private Date payTime;

                    @NotNull     private Date modified;

                    @NotNull     private Date endTime;

                    @NotNull     private Long alipayId;

                    @NotEmpty(message = "{不能为空}")
            private String alipayNo;

    public String getTaobaoUserId() {
       return taobaoUserId;
    }

    public void setTaobaoUserId(String taobaoUserId) {
       this.taobaoUserId = taobaoUserId;
    }
    public String getSellerNick() {
       return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
       this.sellerNick = sellerNick;
    }
    public String getPicPath() {
       return picPath;
    }

    public void setPicPath(String picPath) {
       this.picPath = picPath;
    }
    public String getPayment() {
       return payment;
    }

    public void setPayment(String payment) {
       this.payment = payment;
    }
    public String getReceiverName() {
       return receiverName;
    }

    public void setReceiverName(String receiverName) {
       this.receiverName = receiverName;
    }
    public String getReceiverState() {
       return receiverState;
    }

    public void setReceiverState(String receiverState) {
       this.receiverState = receiverState;
    }
    public String getReceiverAddress() {
       return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
       this.receiverAddress = receiverAddress;
    }
    public String getReceiverZip() {
       return receiverZip;
    }

    public void setReceiverZip(String receiverZip) {
       this.receiverZip = receiverZip;
    }
    public String getReceiverMobile() {
       return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
       this.receiverMobile = receiverMobile;
    }
    public String getReceiverPhone() {
       return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
       this.receiverPhone = receiverPhone;
    }
    public String getReceivedPayment() {
       return receivedPayment;
    }

    public void setReceivedPayment(String receivedPayment) {
       this.receivedPayment = receivedPayment;
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
       this.status = status;
    }
    public String getType() {
       return type;
    }

    public void setType(String type) {
       this.type = type;
    }
    public String getPrice() {
       return price;
    }

    public void setPrice(String price) {
       this.price = price;
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
       this.totalFee = totalFee;
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
       this.alipayNo = alipayNo;
    }

    public TaobaoOrderDO toDO(){
       TaobaoOrderDO taobaoOrderDO  = new TaobaoOrderDO();
            taobaoOrderDO.setTaobaoUserId(this.taobaoUserId);
                taobaoOrderDO.setSellerNick(this.sellerNick);
                taobaoOrderDO.setPicPath(this.picPath);
                taobaoOrderDO.setPayment(this.payment);
                taobaoOrderDO.setReceiverName(this.receiverName);
                taobaoOrderDO.setReceiverState(this.receiverState);
                taobaoOrderDO.setReceiverAddress(this.receiverAddress);
                taobaoOrderDO.setReceiverZip(this.receiverZip);
                taobaoOrderDO.setReceiverMobile(this.receiverMobile);
                taobaoOrderDO.setReceiverPhone(this.receiverPhone);
                taobaoOrderDO.setReceivedPayment(this.receivedPayment);
                taobaoOrderDO.setTid(this.tid);
                taobaoOrderDO.setNum(this.num);
                taobaoOrderDO.setNumIid(this.numIid);
                taobaoOrderDO.setStatus(this.status);
                taobaoOrderDO.setType(this.type);
                taobaoOrderDO.setPrice(this.price);
                taobaoOrderDO.setPointFee(this.pointFee);
                taobaoOrderDO.setTotalFee(this.totalFee);
                taobaoOrderDO.setCreated(this.created);
                taobaoOrderDO.setPayTime(this.payTime);
                taobaoOrderDO.setModified(this.modified);
                taobaoOrderDO.setEndTime(this.endTime);
                taobaoOrderDO.setAlipayId(this.alipayId);
                taobaoOrderDO.setAlipayNo(this.alipayNo);
           return taobaoOrderDO;
}

}
