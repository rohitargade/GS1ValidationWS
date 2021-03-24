package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class BatteryInformationModule {
	private String Batteries_Included;
	private List<Battery_Type> Battery_Type;
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

	public String getBatteries_Included() {
		return Batteries_Included;
	}

	public void setBatteries_Included(String batteries_Included) {
		Batteries_Included = batteries_Included;
	}

	public List<Battery_Type> getBattery_Type() {
		return Battery_Type;
	}

	public void setBattery_Type(List<Battery_Type> battery_Type) {
		Battery_Type = battery_Type;
	}
}
