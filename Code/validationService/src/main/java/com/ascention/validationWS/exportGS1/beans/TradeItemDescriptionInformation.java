package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemDescriptionInformation {
	private String Addl_Prod_Desc;
	private String Short_Desc;
	private String Functional_Name;
	private String Label_Desc;
	private String TradeItemDescription;
	private String Prod_Group_Code;
	private String Prod_Group_Code_Desc;
	private String Brand_Name;
	private String Sub_Brand_Name;
	private String Colour_Code;
	private String Colour_Code_Provider;
	private String Colour_Desc;

	public String getAddl_Prod_Desc() {
		return Addl_Prod_Desc;
	}

	public void setAddl_Prod_Desc(String addl_Prod_Desc) {
		Addl_Prod_Desc = addl_Prod_Desc;
	}

	public String getShort_Desc() {
		return Short_Desc;
	}

	public void setShort_Desc(String short_Desc) {
		Short_Desc = short_Desc;
	}

	public String getFunctional_Name() {
		return Functional_Name;
	}

	public void setFunctional_Name(String functional_Name) {
		Functional_Name = functional_Name;
	}

	public String getLabel_Desc() {
		return Label_Desc;
	}

	public void setLabel_Desc(String label_Desc) {
		Label_Desc = label_Desc;
	}



	public String getProd_Group_Code() {
		return Prod_Group_Code;
	}

	public void setProd_Group_Code(String prod_Group_Code) {
		Prod_Group_Code = prod_Group_Code;
	}

	public String getProd_Group_Code_Desc() {
		return Prod_Group_Code_Desc;
	}

	public void setProd_Group_Code_Desc(String prod_Group_Code_Desc) {
		Prod_Group_Code_Desc = prod_Group_Code_Desc;
	}

	public String getBrand_Name() {
		return Brand_Name;
	}

	public void setBrand_Name(String brand_Name) {
		Brand_Name = brand_Name;
	}

	public String getSub_Brand_Name() {
		return Sub_Brand_Name;
	}

	public void setSub_Brand_Name(String sub_Brand_Name) {
		Sub_Brand_Name = sub_Brand_Name;
	}

	public String getColour_Code() {
		return Colour_Code;
	}

	public void setColour_Code(String colour_Code) {
		Colour_Code = colour_Code;
	}

	public String getColour_Code_Provider() {
		return Colour_Code_Provider;
	}

	public void setColour_Code_Provider(String colour_Code_Provider) {
		Colour_Code_Provider = colour_Code_Provider;
	}

	public String getColour_Desc() {
		return Colour_Desc;
	}

	public void setColour_Desc(String colour_Desc) {
		Colour_Desc = colour_Desc;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getTradeItemDescription() {
		return TradeItemDescription;
	}

	public void setTradeItemDescription(String tradeItemDescription) {
		TradeItemDescription = tradeItemDescription;
	}

}
