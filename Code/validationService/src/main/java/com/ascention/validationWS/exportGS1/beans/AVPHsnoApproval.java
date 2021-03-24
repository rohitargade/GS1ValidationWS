package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class AVPHsnoApproval {

	private String Hsno_Approval_Num;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getHsno_Approval_Num() {
		return Hsno_Approval_Num;
	}

	public void setHsno_Approval_Num(String hsno_Approval_Num) {
		Hsno_Approval_Num = hsno_Approval_Num;
	}

	


}
