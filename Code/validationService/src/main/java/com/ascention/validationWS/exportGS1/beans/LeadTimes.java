package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class LeadTimes {
	private String Recipient_Id;
	private Double Ord_Lead_Time;
	private String Ord_Lead_Time_Uom;

	public String getRecipient_Id() {
		return Recipient_Id;
	}

	public void setRecipient_Id(String recipient_Id) {
		Recipient_Id = recipient_Id;
	}

	

	public String getOrd_Lead_Time_Uom() {
		return Ord_Lead_Time_Uom;
	}

	public void setOrd_Lead_Time_Uom(String ord_Lead_Time_Uom) {
		Ord_Lead_Time_Uom = ord_Lead_Time_Uom;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public Double getOrd_Lead_Time() {
		return Ord_Lead_Time;
	}

	public void setOrd_Lead_Time(Double ord_Lead_Time) {
		Ord_Lead_Time = ord_Lead_Time;
	}
}
