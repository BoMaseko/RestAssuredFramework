package org.bongz.request.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContractProduct {

	public String productCd;
	public String planCd;
	public String effFromDate;
	public String effToDate;
}
