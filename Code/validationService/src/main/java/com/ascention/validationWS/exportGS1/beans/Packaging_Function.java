package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Packaging_Function {
	private String Packaging_Function_Code;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getPackaging_Function_Code() {
		return Packaging_Function_Code;
	}

	public void setPackaging_Function_Code(String packaging_Function_Code) {
		Packaging_Function_Code = packaging_Function_Code;
	}

}
