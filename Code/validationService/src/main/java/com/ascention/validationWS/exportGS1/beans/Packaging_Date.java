package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Packaging_Date {
	private String Packaging_Date_Type;
	

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


	public String getPackaging_Date_Type() {
		return Packaging_Date_Type;
	}


	public void setPackaging_Date_Type(String packaging_Date_Type) {
		Packaging_Date_Type = packaging_Date_Type;
	}

}
