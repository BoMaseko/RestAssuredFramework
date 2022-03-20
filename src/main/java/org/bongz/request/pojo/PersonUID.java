package org.bongz.request.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PersonUID {

    public String uidNo;
    public String typeCd;
    public String countryCd;
    public String effFromDate;
    public String effToDate;
}
