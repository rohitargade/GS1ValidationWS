package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Packaging_Deposit {
	private Float Package_Deposit_Amnt;
	private String Package_Deposit_Amnt_Curr;
	private String Package_Deposit_Id;
	private String Package_Deposit_End_Date;
	private String Numeric_Id;
	private String Package_Deposit_Subdivision;

	

	public String getPackage_Deposit_Amnt_Curr() {
		return Package_Deposit_Amnt_Curr;
	}

	public void setPackage_Deposit_Amnt_Curr(String package_Deposit_Amnt_Curr) {
		Package_Deposit_Amnt_Curr = package_Deposit_Amnt_Curr;
	}

	public String getPackage_Deposit_Id() {
		return Package_Deposit_Id;
	}

	public void setPackage_Deposit_Id(String package_Deposit_Id) {
		Package_Deposit_Id = package_Deposit_Id;
	}

	public String getPackage_Deposit_End_Date() {
		return Package_Deposit_End_Date;
	}

	public void setPackage_Deposit_End_Date(String package_Deposit_End_Date) {
		Package_Deposit_End_Date = package_Deposit_End_Date;
	}

	public String getNumeric_Id() {
		return Numeric_Id;
	}

	public void setNumeric_Id(String numeric_Id) {
		Numeric_Id = numeric_Id;
	}

	public String getPackage_Deposit_Subdivision() {
		return Package_Deposit_Subdivision;
	}

	public void setPackage_Deposit_Subdivision(String package_Deposit_Subdivision) {
		Package_Deposit_Subdivision = package_Deposit_Subdivision;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public Float getPackage_Deposit_Amnt() {
		return Package_Deposit_Amnt;
	}

	public void setPackage_Deposit_Amnt(Float package_Deposit_Amnt) {
		Package_Deposit_Amnt = package_Deposit_Amnt;
	}

}
