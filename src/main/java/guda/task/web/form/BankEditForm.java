package guda.task.web.form;

import guda.task.dao.domain.BankDO;


public class BankEditForm extends BankForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankDO toDO(){
        BankDO bankDO  =super.toDO();
        bankDO.setId(this.id);
        return bankDO;
    }

    public void initForm(BankDO bankDO){
        if(bankDO == null){
        return ;
    }
    this.setUserId(bankDO.getUserId());
    this.setBankType(bankDO.getBankType());
    this.setBankName(bankDO.getBankName());
    this.setBankNo(bankDO.getBankNo());
}

}
