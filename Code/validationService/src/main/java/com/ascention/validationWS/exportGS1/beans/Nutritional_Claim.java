package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Nutritional_Claim {
	private String Nutritional_Claim;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getNutritional_Claim() {
		return Nutritional_Claim;
	}

	public void setNutritional_Claim(String nutritional_Claim) {
		Nutritional_Claim = nutritional_Claim;
	}

	
}
