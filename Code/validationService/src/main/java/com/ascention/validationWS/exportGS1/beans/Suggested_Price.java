package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Suggested_Price {

	private Float Suggested_Price;
	private String Suggested_Price_Curr;
	private Double Suggested_Price_Basis_Qty;
	private String Suggested_Price_Basis_Qty_Uom;
	private String Suggested_Price_Start_Date;
	private String Suggested_Price_End_Date;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	

	public String getSuggested_Price_Curr() {
		return Suggested_Price_Curr;
	}

	public void setSuggested_Price_Curr(String suggested_Price_Curr) {
		Suggested_Price_Curr = suggested_Price_Curr;
	}

	

	public String getSuggested_Price_Basis_Qty_Uom() {
		return Suggested_Price_Basis_Qty_Uom;
	}

	public void setSuggested_Price_Basis_Qty_Uom(String suggested_Price_Basis_Qty_Uom) {
		Suggested_Price_Basis_Qty_Uom = suggested_Price_Basis_Qty_Uom;
	}

	public String getSuggested_Price_Start_Date() {
		return Suggested_Price_Start_Date;
	}

	public void setSuggested_Price_Start_Date(String suggested_Price_Start_Date) {
		Suggested_Price_Start_Date = suggested_Price_Start_Date;
	}

	public String getSuggested_Price_End_Date() {
		return Suggested_Price_End_Date;
	}

	public void setSuggested_Price_End_Date(String suggested_Price_End_Date) {
		Suggested_Price_End_Date = suggested_Price_End_Date;
	}



	public Float getSuggested_Price() {
		return Suggested_Price;
	}



	public void setSuggested_Price(Float suggested_Price) {
		Suggested_Price = suggested_Price;
	}



	public Double getSuggested_Price_Basis_Qty() {
		return Suggested_Price_Basis_Qty;
	}



	public void setSuggested_Price_Basis_Qty(Double suggested_Price_Basis_Qty) {
		Suggested_Price_Basis_Qty = suggested_Price_Basis_Qty;
	}


	




}
