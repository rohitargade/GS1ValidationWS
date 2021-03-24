package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Nutrient_Serving_Size {
	private String Nutrient_Info_Id;
	private Double Serving_Size;
	private String Serving_Size_Uom;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getNutrient_Info_Id() {
		return Nutrient_Info_Id;
	}

	public void setNutrient_Info_Id(String nutrient_Info_Id) {
		Nutrient_Info_Id = nutrient_Info_Id;
	}

	public Double getServing_Size() {
		return Serving_Size;
	}

	public void setServing_Size(Double serving_Size) {
		Serving_Size = serving_Size;
	}

	public String getServing_Size_Uom() {
		return Serving_Size_Uom;
	}

	public void setServing_Size_Uom(String serving_Size_Uom) {
		Serving_Size_Uom = serving_Size_Uom;
	}

	



	

}
