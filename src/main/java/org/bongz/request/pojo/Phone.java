package org.bongz.request.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Phone {

	   public String phoneTypeCd;
	    public String phoneNo;
	    public String countryCd;
	    public String areaCd;
	    public String extension;
	    public String effFromDate;
	    public String effToDate;
	    public String sourceCd;
}
