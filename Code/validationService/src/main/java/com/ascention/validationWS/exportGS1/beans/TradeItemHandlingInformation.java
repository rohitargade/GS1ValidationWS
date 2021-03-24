package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemHandlingInformation {

	private String Handling_Instr_Ref;
	


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}



	public String getHandling_Instr_Ref() {
		return Handling_Instr_Ref;
	}



	public void setHandling_Instr_Ref(String handling_Instr_Ref) {
		Handling_Instr_Ref = handling_Instr_Ref;
	}




	



}
