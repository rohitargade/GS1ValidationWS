package com.ascention.validationWS.exportGS1.beans;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


public class PricingPSDDetail {
	private Integer Price_Dtl_Id;	
	private Integer Price_Type_App_Seq;
	private String Price_Type;
	private String Price_Type_Subcat;
	private BigDecimal Price_Value;
	private String Price_Value_Type;
	private String Alt_Loc_Grouping;
	private String Ref_Doc_Identifier;
	private String Ref_Doc_Desc;
	private Integer Target_Price_Id;
	private String Price_Region;




	public String getPrice_Type() {
		return Price_Type;
	}



	public void setPrice_Type(String price_Type) {
		Price_Type = price_Type;
	}



	public String getPrice_Type_Subcat() {
		return Price_Type_Subcat;
	}



	public void setPrice_Type_Subcat(String price_Type_Subcat) {
		Price_Type_Subcat = price_Type_Subcat;
	}



	


	public String getPrice_Value_Type() {
		return Price_Value_Type;
	}



	public void setPrice_Value_Type(String price_Value_Type) {
		Price_Value_Type = price_Value_Type;
	}



	public String getAlt_Loc_Grouping() {
		return Alt_Loc_Grouping;
	}



	public void setAlt_Loc_Grouping(String alt_Loc_Grouping) {
		Alt_Loc_Grouping = alt_Loc_Grouping;
	}



	public String getRef_Doc_Identifier() {
		return Ref_Doc_Identifier;
	}



	public void setRef_Doc_Identifier(String ref_Doc_Identifier) {
		Ref_Doc_Identifier = ref_Doc_Identifier;
	}



	public String getRef_Doc_Desc() {
		return Ref_Doc_Desc;
	}



	public void setRef_Doc_Desc(String ref_Doc_Desc) {
		Ref_Doc_Desc = ref_Doc_Desc;
	}



	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }



	public Integer getPrice_Type_App_Seq() {
		return Price_Type_App_Seq;
	}



	public void setPrice_Type_App_Seq(Integer price_Type_App_Seq) {
		Price_Type_App_Seq = price_Type_App_Seq;
	}



	public BigDecimal getPrice_Value() {
		return Price_Value;
	}



	public void setPrice_Value(BigDecimal price_Value) {
		Price_Value = price_Value;
	}



	public Integer getPrice_Dtl_Id() {
		return Price_Dtl_Id;
	}



	public void setPrice_Dtl_Id(Integer price_Dtl_Id) {
		Price_Dtl_Id = price_Dtl_Id;
	}



	public Integer getTarget_Price_Id() {
		return Target_Price_Id;
	}



	public void setTarget_Price_Id(Integer target_Price_Id) {
		Target_Price_Id = target_Price_Id;
	}



	public String getPrice_Region() {
		return Price_Region;
	}



	public void setPrice_Region(String price_Region) {
		Price_Region = price_Region;
	}
}
