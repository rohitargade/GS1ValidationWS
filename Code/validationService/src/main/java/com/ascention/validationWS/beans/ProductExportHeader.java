package com.ascention.validationWS.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ProductExportHeader {

	private Integer Product_Export_Hdr_Id;
	private Integer Client_Id;
	private Integer Org_Id;
	private String Target_Market;
	private Integer Product_Id;
	private Integer Product_Export_Dtl_Id;
	private Integer Product_Hierarchy_Id;
	private Integer Country_Origin_Id;
	
	public Integer getProduct_Export_Hdr_Id() {
		return Product_Export_Hdr_Id;
	}
	public void setProduct_Export_Hdr_Id(Integer product_Export_Hdr_Id) {
		Product_Export_Hdr_Id = product_Export_Hdr_Id;
	}
	public Integer getClient_Id() {
		return Client_Id;
	}
	public void setClient_Id(Integer client_Id) {
		Client_Id = client_Id;
	}
	
	public String getTarget_Market() {
		return Target_Market;
	}
	public void setTarget_Market(String target_Market) {
		Target_Market = target_Market;
	}
	public Integer getProduct_Id() {
		return Product_Id;
	}
	public void setProduct_Id(Integer product_Id) {
		Product_Id = product_Id;
	}
	public Integer getProduct_Export_Dtl_Id() {
		return Product_Export_Dtl_Id;
	}
	public void setProduct_Export_Dtl_Id(Integer product_Export_Dtl_Id) {
		Product_Export_Dtl_Id = product_Export_Dtl_Id;
	}
	public Integer getProduct_Hierarchy_Id() {
		return Product_Hierarchy_Id;
	}
	public void setProduct_Hierarchy_Id(Integer product_Hierarchy_Id) {
		Product_Hierarchy_Id = product_Hierarchy_Id;
	}
	public Integer getCountry_Origin_Id() {
		return Country_Origin_Id;
	}
	public void setCountry_Origin_Id(Integer country_Origin_Id) {
		Country_Origin_Id = country_Origin_Id;
	}


	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }
	public Integer getOrg_Id() {
		return Org_Id;
	}
	public void setOrg_Id(Integer org_Id) {
		Org_Id = org_Id;
	}

}
