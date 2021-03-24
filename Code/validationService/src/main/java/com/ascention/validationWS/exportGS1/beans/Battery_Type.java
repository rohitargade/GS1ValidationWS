package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Battery_Type {
	private String Battery_Type;

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

	public String getBattery_Type() {
		return Battery_Type;
	}

	public void setBattery_Type(String battery_Type) {
		Battery_Type = battery_Type;
	}

	
}
