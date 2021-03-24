package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ServingQuantityInformation {
	private Float Servings_Per_Pack;

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


	public Float getServings_Per_Pack() {
		return Servings_Per_Pack;
	}


	public void setServings_Per_Pack(Float servings_Per_Pack) {
		Servings_Per_Pack = servings_Per_Pack;
	}

}
