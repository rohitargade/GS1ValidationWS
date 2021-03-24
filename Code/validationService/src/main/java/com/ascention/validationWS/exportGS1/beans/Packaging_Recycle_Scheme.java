package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Packaging_Recycle_Scheme {
	private String Packaging_Recycle_Scheme;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getPackaging_Recycle_Scheme() {
		return Packaging_Recycle_Scheme;
	}

	public void setPackaging_Recycle_Scheme(String packaging_Recycle_Scheme) {
		Packaging_Recycle_Scheme = packaging_Recycle_Scheme;
	}

	

	

}
