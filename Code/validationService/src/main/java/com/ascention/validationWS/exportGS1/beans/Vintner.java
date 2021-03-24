package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Vintner {
	private String Vintner;

	public String getVintner() {
		return Vintner;
	}

	public void setVintner(String vintner) {
		Vintner = vintner;
	}

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }
}
