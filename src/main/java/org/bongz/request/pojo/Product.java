package org.bongz.request.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {

	   public String productReferenceNumber;
	    public String productName;
	    public String entityRoleCode;
	    public String effectiveFromDate;
	    public String effectiveToDate;
	    public String productDesc;
}
