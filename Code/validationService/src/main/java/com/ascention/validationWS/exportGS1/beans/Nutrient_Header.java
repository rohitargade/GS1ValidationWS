package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Nutrient_Header {
	private String Nutrient_Info_Id;
	private String Prep_State;
	private String Nutrient_Basis_Qty_Type;
	private Double Nutrient_Basis_Qty;
	private String Nutrient_Basis_Qty_Uom;
	private String Nutrient_Basis_Qty_Desc;

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

	public String getPrep_State() {
		return Prep_State;
	}

	public void setPrep_State(String prep_State) {
		Prep_State = prep_State;
	}

	public String getNutrient_Basis_Qty_Type() {
		return Nutrient_Basis_Qty_Type;
	}

	public void setNutrient_Basis_Qty_Type(String nutrient_Basis_Qty_Type) {
		Nutrient_Basis_Qty_Type = nutrient_Basis_Qty_Type;
	}



	public String getNutrient_Basis_Qty_Uom() {
		return Nutrient_Basis_Qty_Uom;
	}

	public void setNutrient_Basis_Qty_Uom(String nutrient_Basis_Qty_Uom) {
		Nutrient_Basis_Qty_Uom = nutrient_Basis_Qty_Uom;
	}

	public String getNutrient_Basis_Qty_Desc() {
		return Nutrient_Basis_Qty_Desc;
	}

	public void setNutrient_Basis_Qty_Desc(String nutrient_Basis_Qty_Desc) {
		Nutrient_Basis_Qty_Desc = nutrient_Basis_Qty_Desc;
	}

	public Double getNutrient_Basis_Qty() {
		return Nutrient_Basis_Qty;
	}

	public void setNutrient_Basis_Qty(Double nutrient_Basis_Qty) {
		Nutrient_Basis_Qty = nutrient_Basis_Qty;
	}

	

}
