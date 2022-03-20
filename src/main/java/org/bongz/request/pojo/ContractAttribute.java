package org.bongz.request.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContractAttribute {

	public String attributeTypeCd;
    public String attributeValue;
    public String effFromDate;
    public String effToDate;
}
