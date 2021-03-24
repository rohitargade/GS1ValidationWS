package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class AdditiveInformation {
	
	private String AdditiveName;
	private String Containment_Level;
		
	public String getAdditiveName() {
		return AdditiveName;
	}

	public void setAdditiveName(String additiveName) {
		AdditiveName = additiveName;
	}

	public String getContainment_Level() {
		return Containment_Level;
	}

	public void setContainment_Level(String containment_Level) {
		Containment_Level = containment_Level;
	}

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }



	
	
}
