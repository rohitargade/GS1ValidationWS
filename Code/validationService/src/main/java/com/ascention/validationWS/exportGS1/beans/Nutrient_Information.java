package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Nutrient_Information {
	private String Nutrient_Info_Id;
	private String Nutrient_Type;
	private String Nutrient_Qty_Measurement_Precision;
	private Double Qty_Contained;
	private String Qty_Contained_Uom;
	private Double Daily_Value_Intake_Pct;
	private String DVIP_Measurement_Precision;


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


	public String getNutrient_Type() {
		return Nutrient_Type;
	}


	public void setNutrient_Type(String nutrient_Type) {
		Nutrient_Type = nutrient_Type;
	}


	public String getNutrient_Qty_Measurement_Precision() {
		return Nutrient_Qty_Measurement_Precision;
	}


	public void setNutrient_Qty_Measurement_Precision(String nutrient_Qty_Measurement_Precision) {
		Nutrient_Qty_Measurement_Precision = nutrient_Qty_Measurement_Precision;
	}


	public Double getQty_Contained() {
		return Qty_Contained;
	}


	public void setQty_Contained(Double qty_Contained) {
		Qty_Contained = qty_Contained;
	}


	public String getQty_Contained_Uom() {
		return Qty_Contained_Uom;
	}


	public void setQty_Contained_Uom(String qty_Contained_Uom) {
		Qty_Contained_Uom = qty_Contained_Uom;
	}


	public Double getDaily_Value_Intake_Pct() {
		return Daily_Value_Intake_Pct;
	}


	public void setDaily_Value_Intake_Pct(Double daily_Value_Intake_Pct) {
		Daily_Value_Intake_Pct = daily_Value_Intake_Pct;
	}


	public String getDVIP_Measurement_Precision() {
		return DVIP_Measurement_Precision;
	}


	public void setDVIP_Measurement_Precision(String dVIP_Measurement_Precision) {
		DVIP_Measurement_Precision = dVIP_Measurement_Precision;
	}

	
	



	

}
