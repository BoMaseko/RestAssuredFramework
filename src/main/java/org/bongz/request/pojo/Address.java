package org.bongz.request.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Address {

	   public String addrTypeCd;
	    public String addrLine1;
	    public String addrLine2;
	    public String cityTown;
	    public String countryCd;
	    public String effFromDate;
	    public String effToDate;
	    public String provinceState;
	    public String sourceCd;
	    public String zipPostalCd;
}
