package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class CIPGTIN {
	private String Gtin;
	
	
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }



	public String getGtin() {
		return Gtin;
	}



	public void setGtin(String gtin) {
		Gtin = gtin;
	}
}
