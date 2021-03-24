package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Packaging_Sustainability {
	private String Packaging_Sustainability_Feature;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getPackaging_Sustainability_Feature() {
		return Packaging_Sustainability_Feature;
	}

	public void setPackaging_Sustainability_Feature(String packaging_Sustainability_Feature) {
		Packaging_Sustainability_Feature = packaging_Sustainability_Feature;
	}

	
	

}
