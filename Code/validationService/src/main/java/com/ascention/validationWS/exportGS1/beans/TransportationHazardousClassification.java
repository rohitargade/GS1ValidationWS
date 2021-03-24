package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TransportationHazardousClassification {
	private String Dg_Regulation_Code;
	private Float Flash_Point_Temp;
	private String Flash_Point_Temp_Uom;
	private String Dg_Class;
	private String Dg_Hazardous_Code;
	private String Dg_Packing_Group;
	private String Dg_Shipping_Name;
	private String Dg_Technical_Name;
	private String Un_Dg_Num;
	private String Dg_Secondary_Sub_Div;
	
	
	public String getDg_Regulation_Code() {
		return Dg_Regulation_Code;
	}

	public void setDg_Regulation_Code(String dg_Regulation_Code) {
		Dg_Regulation_Code = dg_Regulation_Code;
	}

	

	public String getFlash_Point_Temp_Uom() {
		return Flash_Point_Temp_Uom;
	}

	public void setFlash_Point_Temp_Uom(String flash_Point_Temp_Uom) {
		Flash_Point_Temp_Uom = flash_Point_Temp_Uom;
	}

	public String getDg_Class() {
		return Dg_Class;
	}

	public void setDg_Class(String dg_Class) {
		Dg_Class = dg_Class;
	}

	public String getDg_Hazardous_Code() {
		return Dg_Hazardous_Code;
	}

	public void setDg_Hazardous_Code(String dg_Hazardous_Code) {
		Dg_Hazardous_Code = dg_Hazardous_Code;
	}

	public String getDg_Packing_Group() {
		return Dg_Packing_Group;
	}

	public void setDg_Packing_Group(String dg_Packing_Group) {
		Dg_Packing_Group = dg_Packing_Group;
	}

	public String getDg_Shipping_Name() {
		return Dg_Shipping_Name;
	}

	public void setDg_Shipping_Name(String dg_Shipping_Name) {
		Dg_Shipping_Name = dg_Shipping_Name;
	}

	public String getDg_Technical_Name() {
		return Dg_Technical_Name;
	}

	public void setDg_Technical_Name(String dg_Technical_Name) {
		Dg_Technical_Name = dg_Technical_Name;
	}

	public String getUn_Dg_Num() {
		return Un_Dg_Num;
	}

	public void setUn_Dg_Num(String un_Dg_Num) {
		Un_Dg_Num = un_Dg_Num;
	}

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public Float getFlash_Point_Temp() {
		return Flash_Point_Temp;
	}

	public void setFlash_Point_Temp(Float flash_Point_Temp) {
		Flash_Point_Temp = flash_Point_Temp;
	}

	public String getDg_Secondary_Sub_Div() {
		return Dg_Secondary_Sub_Div;
	}

	public void setDg_Secondary_Sub_Div(String dg_Secondary_Sub_Div) {
		Dg_Secondary_Sub_Div = dg_Secondary_Sub_Div;
	}

}
