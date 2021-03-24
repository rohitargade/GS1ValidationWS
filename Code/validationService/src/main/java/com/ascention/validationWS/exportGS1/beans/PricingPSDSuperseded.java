package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


public class PricingPSDSuperseded {
	private Integer Superseded_Price_Hdr_Id;
	private Integer Recipient_Id;
	private Integer Product_Export_Dtl_Id;
	private Integer Price_Relationship_Id;

	public Integer getSuperseded_Price_Hdr_Id() {
		return Superseded_Price_Hdr_Id;
	}


	public void setSuperseded_Price_Hdr_Id(Integer superseded_Price_Hdr_Id) {
		Superseded_Price_Hdr_Id = superseded_Price_Hdr_Id;
	}


	public Integer getRecipient_Id() {
		return Recipient_Id;
	}


	public void setRecipient_Id(Integer recipient_Id) {
		Recipient_Id = recipient_Id;
	}


	public Integer getProduct_Export_Dtl_Id() {
		return Product_Export_Dtl_Id;
	}


	public void setProduct_Export_Dtl_Id(Integer product_Export_Dtl_Id) {
		Product_Export_Dtl_Id = product_Export_Dtl_Id;
	}


	public Integer getPrice_Relationship_Id() {
		return Price_Relationship_Id;
	}


	public void setPrice_Relationship_Id(Integer price_Relationship_Id) {
		Price_Relationship_Id = price_Relationship_Id;
	}


	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }



}
