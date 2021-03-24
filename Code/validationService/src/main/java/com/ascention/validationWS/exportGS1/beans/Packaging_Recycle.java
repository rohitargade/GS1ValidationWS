package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Packaging_Recycle {
	private String Packaging_Recycle_Process;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getPackaging_Recycle_Process() {
		return Packaging_Recycle_Process;
	}

	public void setPackaging_Recycle_Process(String packaging_Recycle_Process) {
		Packaging_Recycle_Process = packaging_Recycle_Process;
	}

	

}
