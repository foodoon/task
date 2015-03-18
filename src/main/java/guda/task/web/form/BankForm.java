package guda.task.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.task.dao.domain.BankDO;
import javax.validation.constraints.NotNull;

public class BankForm {
                    @NotNull     private Long userId;

                    @NotEmpty(message = "不能为空")
            private String bankType;

                    @NotEmpty(message = "不能为空")
            private String bankName;

                    @NotEmpty(message = "不能为空")
            private String bankNo;

    public Long getUserId() {
       return userId;
    }

    public void setUserId(Long userId) {
       this.userId = userId;
    }
    public String getBankType() {
       return bankType;
    }

    public void setBankType(String bankType) {
       this.bankType = bankType;
    }
    public String getBankName() {
       return bankName;
    }

    public void setBankName(String bankName) {
       this.bankName = bankName;
    }
    public String getBankNo() {
       return bankNo;
    }

    public void setBankNo(String bankNo) {
       this.bankNo = bankNo;
    }

    public BankDO toDO(){
       BankDO bankDO  = new BankDO();
            bankDO.setUserId(this.userId);
                bankDO.setBankType(this.bankType);
                bankDO.setBankName(this.bankName);
                bankDO.setBankNo(this.bankNo);
           return bankDO;
}

}
