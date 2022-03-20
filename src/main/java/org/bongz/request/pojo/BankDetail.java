package org.bongz.request.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BankDetail {

    public String financialInstitutionCd;
    public String accountTypeCd;
    public String branchCd;
    public String accountNo;
    public String paymentMethodCd;
    public int deductionDay;
    public String accountHolder;
    public String personUidValue;
    public Object personUidType;
    public String effFromDate;
    public String effToDate;
    public String bankName;
    public String bankCity;
}
