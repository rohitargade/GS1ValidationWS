package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class CatalogueItemNotification {
	private  String Sender_and_Content_Owner;
	private String Receiver;
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
