package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class CIPPublishToGLN {
	private String publishToGLN;
	
	
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }



	public String getPublishToGLN() {
		return publishToGLN;
	}



	public void setPublishToGLN(String publishToGLN) {
		this.publishToGLN = publishToGLN;
	}



}
