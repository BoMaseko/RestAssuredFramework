package org.bongz.request.pojo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ContractMember {

	public ArrayList<ContractProduct> contractProducts;
	public Member member;
	public String contractRoleCd;
	public String relationToPMCd;
	public int contractDisplaySeq;
	public String effFromDate;
	public String effToDate;
}
