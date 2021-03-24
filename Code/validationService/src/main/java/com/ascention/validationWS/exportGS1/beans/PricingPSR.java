package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class PricingPSR {
	private String contentOwner;
	private String Receiver;
	private String Relationship_Code;
	private String partyReceivingPrivateData;
	private String Currency_Id;
	private String Numeric_Id;
	private String Effective_Start_Date;
	private String Effective_End_Date;

	

	

	public String getContentOwner() {
		return contentOwner;
	}





	public void setContentOwner(String contentOwner) {
		this.contentOwner = contentOwner;
	}





	public String getReceiver() {
		return Receiver;
	}





	public void setReceiver(String receiver) {
		Receiver = receiver;
	}





	public String getRelationship_Code() {
		return Relationship_Code;
	}





	public void setRelationship_Code(String relationship_Code) {
		Relationship_Code = relationship_Code;
	}





	public String getPartyReceivingPrivateData() {
		return partyReceivingPrivateData;
	}





	public void setPartyReceivingPrivateData(String partyReceivingPrivateData) {
		this.partyReceivingPrivateData = partyReceivingPrivateData;
	}





	public String getCurrency_Id() {
		return Currency_Id;
	}





	public void setCurrency_Id(String currency_Id) {
		Currency_Id = currency_Id;
	}





	public String getNumeric_Id() {
		return Numeric_Id;
	}





	public void setNumeric_Id(String numeric_Id) {
		Numeric_Id = numeric_Id;
	}





	public String getEffective_Start_Date() {
		return Effective_Start_Date;
	}





	public void setEffective_Start_Date(String effective_Start_Date) {
		Effective_Start_Date = effective_Start_Date;
	}





	public String getEffective_End_Date() {
		return Effective_End_Date;
	}





	public void setEffective_End_Date(String effective_End_Date) {
		Effective_End_Date = effective_End_Date;
	}





	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }
}
