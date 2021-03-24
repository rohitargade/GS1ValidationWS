package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class SecurityTagInformationModule {
	private String Security_Tag_Loc;
	private String Security_Tag_Type;
	

	public String getSecurity_Tag_Loc() {
		return Security_Tag_Loc;
	}


	public void setSecurity_Tag_Loc(String security_Tag_Loc) {
		Security_Tag_Loc = security_Tag_Loc;
	}


	public String getSecurity_Tag_Type() {
		return Security_Tag_Type;
	}


	public void setSecurity_Tag_Type(String security_Tag_Type) {
		Security_Tag_Type = security_Tag_Type;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
