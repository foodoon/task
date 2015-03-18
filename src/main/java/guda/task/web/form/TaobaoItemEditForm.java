package guda.task.web.form;

import guda.task.dao.domain.TaobaoItemDO;


public class TaobaoItemEditForm extends TaobaoItemForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaobaoItemDO toDO(){
        TaobaoItemDO taobaoItemDO  =super.toDO();
        taobaoItemDO.setId(this.id);
        return taobaoItemDO;
    }

    public void initForm(TaobaoItemDO taobaoItemDO){
        if(taobaoItemDO == null){
        return ;
    }
    this.setTaobaoUserId(taobaoItemDO.getTaobaoUserId());
    this.setCid(taobaoItemDO.getCid());
    this.setSellerCids(taobaoItemDO.getSellerCids());
    this.setPicUrl(taobaoItemDO.getPicUrl());
    this.setNum(taobaoItemDO.getNum());
    this.setListTime(taobaoItemDO.getListTime());
    this.setDelistTime(taobaoItemDO.getDelistTime());
    this.setPrice(taobaoItemDO.getPrice());
    this.setHasDiscount(taobaoItemDO.getHasDiscount());
    this.setHasShowcase(taobaoItemDO.getHasShowcase());
    this.setApproveStatus(taobaoItemDO.getApproveStatus());
    this.setIsVirtual(taobaoItemDO.getIsVirtual());
    this.setNumIid(taobaoItemDO.getNumIid());
    this.setTitle(taobaoItemDO.getTitle());
    this.setNick(taobaoItemDO.getNick());
    this.setType(taobaoItemDO.getType());
    this.setValidThru(taobaoItemDO.getValidThru());
    this.setSoldQuantity(taobaoItemDO.getSoldQuantity());
    this.setViolation(taobaoItemDO.getViolation());
}

}
