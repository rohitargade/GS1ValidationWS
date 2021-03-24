package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class AVPHsnoClassification {

	private String Hsno_Classification;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getHsno_Classification() {
		return Hsno_Classification;
	}

	public void setHsno_Classification(String hsno_Classification) {
		Hsno_Classification = hsno_Classification;
	}



}
