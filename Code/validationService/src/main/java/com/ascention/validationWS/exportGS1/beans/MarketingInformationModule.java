package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class MarketingInformationModule {
	private String Feature_Code_Ref;
	private String Prod_Marketing_Msg;
	private String Target_Consumer_Age_Group_Code;
	private String Target_Consumer_Gender;
	private String Season_Name;
	private String Seasonal_Availability_Start_Date;
	private String Seasonal_Availability_End_Date;
	private List<PA_Feature_Benefit> lstPA_Feature_Benefit;
	
	
	
	public String getFeature_Code_Ref() {
		return Feature_Code_Ref;
	}





	public void setFeature_Code_Ref(String feature_Code_Ref) {
		Feature_Code_Ref = feature_Code_Ref;
	}





	public String getProd_Marketing_Msg() {
		return Prod_Marketing_Msg;
	}





	public void setProd_Marketing_Msg(String prod_Marketing_Msg) {
		Prod_Marketing_Msg = prod_Marketing_Msg;
	}





	public String getTarget_Consumer_Age_Group_Code() {
		return Target_Consumer_Age_Group_Code;
	}





	public void setTarget_Consumer_Age_Group_Code(String target_Consumer_Age_Group_Code) {
		Target_Consumer_Age_Group_Code = target_Consumer_Age_Group_Code;
	}





	public String getTarget_Consumer_Gender() {
		return Target_Consumer_Gender;
	}





	public void setTarget_Consumer_Gender(String target_Consumer_Gender) {
		Target_Consumer_Gender = target_Consumer_Gender;
	}





	public String getSeason_Name() {
		return Season_Name;
	}





	public void setSeason_Name(String season_Name) {
		Season_Name = season_Name;
	}





	public String getSeasonal_Availability_Start_Date() {
		return Seasonal_Availability_Start_Date;
	}





	public void setSeasonal_Availability_Start_Date(String seasonal_Availability_Start_Date) {
		Seasonal_Availability_Start_Date = seasonal_Availability_Start_Date;
	}





	public String getSeasonal_Availability_End_Date() {
		return Seasonal_Availability_End_Date;
	}





	public void setSeasonal_Availability_End_Date(String seasonal_Availability_End_Date) {
		Seasonal_Availability_End_Date = seasonal_Availability_End_Date;
	}





	public List<PA_Feature_Benefit> getLstPA_Feature_Benefit() {
		return lstPA_Feature_Benefit;
	}





	public void setLstPA_Feature_Benefit(List<PA_Feature_Benefit> lstPA_Feature_Benefit) {
		this.lstPA_Feature_Benefit = lstPA_Feature_Benefit;
	}




	

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


}
