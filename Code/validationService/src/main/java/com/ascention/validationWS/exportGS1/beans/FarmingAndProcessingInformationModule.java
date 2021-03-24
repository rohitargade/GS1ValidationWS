package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class FarmingAndProcessingInformationModule {
	private String Gm_Declaration_Code;
	private String Irradiated_Code;
	private List<Grow_Method> lstGrowMethod;
	
	public String getGm_Declaration_Code() {
		return Gm_Declaration_Code;
	}

	public void setGm_Declaration_Code(String gm_Declaration_Code) {
		Gm_Declaration_Code = gm_Declaration_Code;
	}

	public String getIrradiated_Code() {
		return Irradiated_Code;
	}

	public void setIrradiated_Code(String irradiated_Code) {
		Irradiated_Code = irradiated_Code;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public List<Grow_Method> getLstGrowMethod() {
		return lstGrowMethod;
	}

	public void setLstGrowMethod(List<Grow_Method> lstGrowMethod) {
		this.lstGrowMethod = lstGrowMethod;
	}
}
