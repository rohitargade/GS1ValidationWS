package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Selling_Price_Comparison {

	private String Price_Comparison_Content_Type;
	private Double Price_Comparison_Measurement;
	private String Price_Comparison_Measurement_Uom;


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


	public String getPrice_Comparison_Content_Type() {
		return Price_Comparison_Content_Type;
	}


	public void setPrice_Comparison_Content_Type(String price_Comparison_Content_Type) {
		Price_Comparison_Content_Type = price_Comparison_Content_Type;
	}


	


	public String getPrice_Comparison_Measurement_Uom() {
		return Price_Comparison_Measurement_Uom;
	}


	public void setPrice_Comparison_Measurement_Uom(String price_Comparison_Measurement_Uom) {
		Price_Comparison_Measurement_Uom = price_Comparison_Measurement_Uom;
	}


	public Double getPrice_Comparison_Measurement() {
		return Price_Comparison_Measurement;
	}


	public void setPrice_Comparison_Measurement(Double price_Comparison_Measurement) {
		Price_Comparison_Measurement = price_Comparison_Measurement;
	}






}
