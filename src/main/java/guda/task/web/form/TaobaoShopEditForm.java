package guda.task.web.form;

import guda.task.dao.domain.TaobaoShopDO;


public class TaobaoShopEditForm extends TaobaoShopForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaobaoShopDO toDO(){
        TaobaoShopDO taobaoShopDO  =super.toDO();
        taobaoShopDO.setId(this.id);
        return taobaoShopDO;
    }

    public void initForm(TaobaoShopDO taobaoShopDO){
        if(taobaoShopDO == null){
        return ;
    }
    this.setDeliveryScore(taobaoShopDO.getDeliveryScore());
    this.setServiceScore(taobaoShopDO.getServiceScore());
    this.setItemScore(taobaoShopDO.getItemScore());
    this.setCreated(taobaoShopDO.getCreated());
    this.setDescript(taobaoShopDO.getDescript());
    this.setTitle(taobaoShopDO.getTitle());
    this.setNick(taobaoShopDO.getNick());
    this.setCid(taobaoShopDO.getCid());
    this.setShopUrl(taobaoShopDO.getShopUrl());
    this.setSid(taobaoShopDO.getSid());
    this.setTaobaoUserId(taobaoShopDO.getTaobaoUserId());
}

}
