package org.bongz.request.pojo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class contractRoot {

    public String applicationNo;
    public String employerGroupCd;
    public String employerGroupOfferingCd;
    public String allowedBillingFrequencyTypeCd;
    public int totalPremium;
    public String toBeMailed;
    public String programCd;
    public String contractDate;
    public String contractEndDate;
    public String prefMethodReceivePolicy;
    public String supplierStateCd;
    public String contractSource;
    public String statusCd;
    public ArrayList<AppAdvisor> appAdvisors;
    public ArrayList<ContractAttribute> contractAttributes;
    public ArrayList<ContractMember> contractMembers;
    public BankDetail bankDetail;
}
