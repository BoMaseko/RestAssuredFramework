package org.bongz.request.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmailAddress {

    public String emailAddrTypeCd;
    public String emailAddr;
    public String sourceCd;
    public String effFromDate;
    public String effToDate;
}
