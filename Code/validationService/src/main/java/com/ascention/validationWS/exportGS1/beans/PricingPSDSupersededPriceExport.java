package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


public class PricingPSDSupersededPriceExport {
	private Integer Price_Export_Id;
	

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }


	public Integer getPrice_Export_Id() {
		return Price_Export_Id;
	}


	public void setPrice_Export_Id(Integer price_Export_Id) {
		Price_Export_Id = price_Export_Id;
	}



}
