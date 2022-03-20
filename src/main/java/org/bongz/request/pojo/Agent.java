package org.bongz.request.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Agent {

    public String agentExtRef;
    public String agentName;
    public String agentLastName;
    public String mobileNo;
    public String emailAddress;
    public String intermediaryBranch;
    public String intermediaryBranchCd;
    public String agentCompanyCd;
    public String consultantCode;
}
