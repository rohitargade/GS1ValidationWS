package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class CIPHeader {
	private String Sender_Content_Owner;
	private String Receiver_sourceDataPool;
	private String Target_Market;

	public String getSender_Content_Owner() {
		return Sender_Content_Owner;
	}

	public void setSender_Content_Owner(String sender_Content_Owner) {
		Sender_Content_Owner = sender_Content_Owner;
	}

	public String getReceiver_sourceDataPool() {
		return Receiver_sourceDataPool;
	}

	public void setReceiver_sourceDataPool(String receiver_sourceDataPool) {
		Receiver_sourceDataPool = receiver_sourceDataPool;
	}

	public String getTarget_Market() {
		return Target_Market;
	}

	public void setTarget_Market(String target_Market) {
		Target_Market = target_Market;
	}

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }
}
