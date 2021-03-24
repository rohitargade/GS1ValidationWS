package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Nutritional_Claim_Detail {
	private String Nutritional_Claim_Type;
	private String Nutritional_Claim_Element;
	

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


	public String getNutritional_Claim_Type() {
		return Nutritional_Claim_Type;
	}


	public void setNutritional_Claim_Type(String nutritional_Claim_Type) {
		Nutritional_Claim_Type = nutritional_Claim_Type;
	}


	public String getNutritional_Claim_Element() {
		return Nutritional_Claim_Element;
	}


	public void setNutritional_Claim_Element(String nutritional_Claim_Element) {
		Nutritional_Claim_Element = nutritional_Claim_Element;
	}

}
