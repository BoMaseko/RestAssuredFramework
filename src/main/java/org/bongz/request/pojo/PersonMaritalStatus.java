package org.bongz.request.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PersonMaritalStatus {

	public String maritalStatusCd;
	public String effFromDate;
	public String effToDate;
	public String sourceCd;
}
