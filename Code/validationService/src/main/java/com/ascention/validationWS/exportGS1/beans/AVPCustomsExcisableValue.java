package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class AVPCustomsExcisableValue {

	private String customsOrExcisableValue;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getCustomsOrExcisableValue() {
		return customsOrExcisableValue;
	}

	public void setCustomsOrExcisableValue(String customsOrExcisableValue) {
		this.customsOrExcisableValue = customsOrExcisableValue;
	}




}
