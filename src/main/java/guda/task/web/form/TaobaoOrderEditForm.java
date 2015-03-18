package guda.task.web.form;

import guda.task.dao.domain.TaobaoOrderDO;


public class TaobaoOrderEditForm extends TaobaoOrderForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaobaoOrderDO toDO(){
        TaobaoOrderDO taobaoOrderDO  =super.toDO();
        taobaoOrderDO.setId(this.id);
        return taobaoOrderDO;
    }

    public void initForm(TaobaoOrderDO taobaoOrderDO){
        if(taobaoOrderDO == null){
        return ;
    }
    this.setTaobaoUserId(taobaoOrderDO.getTaobaoUserId());
    this.setSellerNick(taobaoOrderDO.getSellerNick());
    this.setPicPath(taobaoOrderDO.getPicPath());
    this.setPayment(taobaoOrderDO.getPayment());
    this.setReceiverName(taobaoOrderDO.getReceiverName());
    this.setReceiverState(taobaoOrderDO.getReceiverState());
    this.setReceiverAddress(taobaoOrderDO.getReceiverAddress());
    this.setReceiverZip(taobaoOrderDO.getReceiverZip());
    this.setReceiverMobile(taobaoOrderDO.getReceiverMobile());
    this.setReceiverPhone(taobaoOrderDO.getReceiverPhone());
    this.setReceivedPayment(taobaoOrderDO.getReceivedPayment());
    this.setTid(taobaoOrderDO.getTid());
    this.setNum(taobaoOrderDO.getNum());
    this.setNumIid(taobaoOrderDO.getNumIid());
    this.setStatus(taobaoOrderDO.getStatus());
    this.setType(taobaoOrderDO.getType());
    this.setPrice(taobaoOrderDO.getPrice());
    this.setPointFee(taobaoOrderDO.getPointFee());
    this.setTotalFee(taobaoOrderDO.getTotalFee());
    this.setCreated(taobaoOrderDO.getCreated());
    this.setPayTime(taobaoOrderDO.getPayTime());
    this.setModified(taobaoOrderDO.getModified());
    this.setEndTime(taobaoOrderDO.getEndTime());
    this.setAlipayId(taobaoOrderDO.getAlipayId());
    this.setAlipayNo(taobaoOrderDO.getAlipayNo());
}

}
