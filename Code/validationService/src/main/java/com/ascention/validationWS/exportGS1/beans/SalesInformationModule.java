package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class SalesInformationModule {

	private List<Selling_Uom> lstSelling_Uom;
	private List<Selling_Price_Comparison> lstSelling_Price_Comparison;
	private List<Suggested_Price> lstSuggested_Price;


	public List<Selling_Uom> getLstSelling_Uom() {
		return lstSelling_Uom;
	}


	public void setLstSelling_Uom(List<Selling_Uom> lstSelling_Uom) {
		this.lstSelling_Uom = lstSelling_Uom;
	}


	public List<Selling_Price_Comparison> getLstSelling_Price_Comparison() {
		return lstSelling_Price_Comparison;
	}


	public void setLstSelling_Price_Comparison(List<Selling_Price_Comparison> lstSelling_Price_Comparison) {
		this.lstSelling_Price_Comparison = lstSelling_Price_Comparison;
	}


	public List<Suggested_Price> getLstSuggested_Price() {
		return lstSuggested_Price;
	}


	public void setLstSuggested_Price(List<Suggested_Price> lstSuggested_Price) {
		this.lstSuggested_Price = lstSuggested_Price;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}






}
