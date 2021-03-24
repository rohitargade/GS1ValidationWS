package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class AllergenInformationModule {
	private String Specification_Agency;
	private String Specification_Name;
	private String Allergen_Statement;
	private String Allergen_Type_Code;
	private String Containment_Level;


	public String getSpecification_Agency() {
		return Specification_Agency;
	}


	public void setSpecification_Agency(String specification_Agency) {
		Specification_Agency = specification_Agency;
	}


	public String getSpecification_Name() {
		return Specification_Name;
	}


	public void setSpecification_Name(String specification_Name) {
		Specification_Name = specification_Name;
	}


	public String getAllergen_Statement() {
		return Allergen_Statement;
	}


	public void setAllergen_Statement(String allergen_Statement) {
		Allergen_Statement = allergen_Statement;
	}


	public String getAllergen_Type_Code() {
		return Allergen_Type_Code;
	}


	public void setAllergen_Type_Code(String allergen_Type_Code) {
		Allergen_Type_Code = allergen_Type_Code;
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
