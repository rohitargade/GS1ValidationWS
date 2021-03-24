package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class PricingPSDHeader {
	private String Sender_and_Content_Owner;
	private String Receiver;
	private String partyReceivingPrivateData;
	private String Relationship_Code;
	private Integer Relationship_Seq_Id;
	private String Gtin;
	private String Numeric_Id;
	private String Price_Action;
	
	private Integer Price_Relationship_Id;
	private String Dist_Method;
	private Integer Price_Basis_Qty;
	private String Price_Basis_Qty_Uom;
	private String Effective_Start_Date;
	private String Effective_Start_Date_Context;
	private String Effective_End_Date;
	private String Effective_End_Date_Context;



	
	
	
	public String getSender_and_Content_Owner() {
		return Sender_and_Content_Owner;
	}






	public void setSender_and_Content_Owner(String sender_and_Content_Owner) {
		Sender_and_Content_Owner = sender_and_Content_Owner;
	}






	public String getReceiver() {
		return Receiver;
	}






	public void setReceiver(String receiver) {
		Receiver = receiver;
	}






	public String getPartyReceivingPrivateData() {
		return partyReceivingPrivateData;
	}






	public void setPartyReceivingPrivateData(String partyReceivingPrivateData) {
		this.partyReceivingPrivateData = partyReceivingPrivateData;
	}






	public String getRelationship_Code() {
		return Relationship_Code;
	}






	public void setRelationship_Code(String relationship_Code) {
		Relationship_Code = relationship_Code;
	}






	public String getGtin() {
		return Gtin;
	}






	public void setGtin(String gtin) {
		Gtin = gtin;
	}






	public String getNumeric_Id() {
		return Numeric_Id;
	}






	public void setNumeric_Id(String numeric_Id) {
		Numeric_Id = numeric_Id;
	}






	public Integer getPrice_Relationship_Id() {
		return Price_Relationship_Id;
	}






	public void setPrice_Relationship_Id(Integer price_Relationship_Id) {
		Price_Relationship_Id = price_Relationship_Id;
	}






	public String getDist_Method() {
		return Dist_Method;
	}






	public void setDist_Method(String dist_Method) {
		Dist_Method = dist_Method;
	}






	public Integer getPrice_Basis_Qty() {
		return Price_Basis_Qty;
	}






	public void setPrice_Basis_Qty(Integer price_Basis_Qty) {
		Price_Basis_Qty = price_Basis_Qty;
	}






	public String getPrice_Basis_Qty_Uom() {
		return Price_Basis_Qty_Uom;
	}






	public void setPrice_Basis_Qty_Uom(String price_Basis_Qty_Uom) {
		Price_Basis_Qty_Uom = price_Basis_Qty_Uom;
	}






	public String getEffective_Start_Date() {
		return Effective_Start_Date;
	}






	public void setEffective_Start_Date(String effective_Start_Date) {
		Effective_Start_Date = effective_Start_Date;
	}






	public String getEffective_Start_Date_Context() {
		return Effective_Start_Date_Context;
	}






	public void setEffective_Start_Date_Context(String effective_Start_Date_Context) {
		Effective_Start_Date_Context = effective_Start_Date_Context;
	}






	public String getEffective_End_Date() {
		return Effective_End_Date;
	}






	public void setEffective_End_Date(String effective_End_Date) {
		Effective_End_Date = effective_End_Date;
	}






	public String getEffective_End_Date_Context() {
		return Effective_End_Date_Context;
	}






	public void setEffective_End_Date_Context(String effective_End_Date_Context) {
		Effective_End_Date_Context = effective_End_Date_Context;
	}






	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }






	public Integer getRelationship_Seq_Id() {
		return Relationship_Seq_Id;
	}






	public void setRelationship_Seq_Id(Integer relationship_Seq_Id) {
		Relationship_Seq_Id = relationship_Seq_Id;
	}






	public String getPrice_Action() {
		return Price_Action;
	}






	public void setPrice_Action(String price_Action) {
		Price_Action = price_Action;
	}
}
