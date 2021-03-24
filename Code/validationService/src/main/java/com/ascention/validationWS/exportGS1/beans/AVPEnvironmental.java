package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class AVPEnvironmental {

	private String Environmental_Claim;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getEnvironmental_Claim() {
		return Environmental_Claim;
	}

	public void setEnvironmental_Claim(String environmental_Claim) {
		Environmental_Claim = environmental_Claim;
	}

	



}
