package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Grow_Method {
	private String Grow_Method;

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

	public String getGrow_Method() {
		return Grow_Method;
	}

	public void setGrow_Method(String grow_Method) {
		Grow_Method = grow_Method;
	}

	
	
}
