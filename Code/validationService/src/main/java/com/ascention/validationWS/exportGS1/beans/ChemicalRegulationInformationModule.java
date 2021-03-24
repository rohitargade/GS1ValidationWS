package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ChemicalRegulationInformationModule {
	private String Chem_Regl_Agency;
	private String Chem_Regl_Name;
	private String Chem_Phys_State;
	
	public String getChem_Regl_Agency() {
		return Chem_Regl_Agency;
	}

	public void setChem_Regl_Agency(String chem_Regl_Agency) {
		Chem_Regl_Agency = chem_Regl_Agency;
	}

	public String getChem_Regl_Name() {
		return Chem_Regl_Name;
	}

	public void setChem_Regl_Name(String chem_Regl_Name) {
		Chem_Regl_Name = chem_Regl_Name;
	}

	public String getChem_Phys_State() {
		return Chem_Phys_State;
	}

	public void setChem_Phys_State(String chem_Phys_State) {
		Chem_Phys_State = chem_Phys_State;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
