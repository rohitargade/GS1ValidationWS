package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Dates {
	private String Recipient_Id;
	private String Consumer_First_Availability_Date;
	private String End_Availability_Date;
	private String First_Order_Date;
	private String Start_Availability_Date;


	public String getRecipient_Id() {
		return Recipient_Id;
	}


	public void setRecipient_Id(String recipient_Id) {
		Recipient_Id = recipient_Id;
	}


	public String getConsumer_First_Availability_Date() {
		return Consumer_First_Availability_Date;
	}


	public void setConsumer_First_Availability_Date(String consumer_First_Availability_Date) {
		Consumer_First_Availability_Date = consumer_First_Availability_Date;
	}


	public String getEnd_Availability_Date() {
		return End_Availability_Date;
	}


	public void setEnd_Availability_Date(String end_Availability_Date) {
		End_Availability_Date = end_Availability_Date;
	}


	public String getFirst_Order_Date() {
		return First_Order_Date;
	}


	public void setFirst_Order_Date(String first_Order_Date) {
		First_Order_Date = first_Order_Date;
	}


	public String getStart_Availability_Date() {
		return Start_Availability_Date;
	}


	public void setStart_Availability_Date(String start_Availability_Date) {
		Start_Availability_Date = start_Availability_Date;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
