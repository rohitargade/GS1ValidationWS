package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemInformationProviderDetails {
	private String Info_Provider_GLN;
	private String Info_Provider_Name;
	private String Info_Provider_Address;

	public String getInfo_Provider_GLN() {
		return Info_Provider_GLN;
	}

	public void setInfo_Provider_GLN(String info_Provider_GLN) {
		Info_Provider_GLN = info_Provider_GLN;
	}

	public String getInfo_Provider_Name() {
		return Info_Provider_Name;
	}

	public void setInfo_Provider_Name(String info_Provider_Name) {
		Info_Provider_Name = info_Provider_Name;
	}

	public String getInfo_Provider_Address() {
		return Info_Provider_Address;
	}

	public void setInfo_Provider_Address(String info_Provider_Address) {
		Info_Provider_Address = info_Provider_Address;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
