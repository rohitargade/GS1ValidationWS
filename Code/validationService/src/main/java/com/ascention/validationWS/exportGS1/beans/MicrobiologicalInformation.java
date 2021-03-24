package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class MicrobiologicalInformation {
	private String Organism_Code;
	private Float Organism_Max_Value;
	private String Organism_Max_Value_Uom;

	
	public String getOrganism_Code() {
		return Organism_Code;
	}


	public void setOrganism_Code(String organism_Code) {
		Organism_Code = organism_Code;
	}




	public String getOrganism_Max_Value_Uom() {
		return Organism_Max_Value_Uom;
	}


	public void setOrganism_Max_Value_Uom(String organism_Max_Value_Uom) {
		Organism_Max_Value_Uom = organism_Max_Value_Uom;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


	public Float getOrganism_Max_Value() {
		return Organism_Max_Value;
	}


	public void setOrganism_Max_Value(Float organism_Max_Value) {
		Organism_Max_Value = organism_Max_Value;
	}

}
