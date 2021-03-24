package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class CertificationInformationModule {
	private String Certification_Agency;
	private String Certification_Std;
	private String Certification_Value;

	public String getCertification_Agency() {
		return Certification_Agency;
	}

	public void setCertification_Agency(String certification_Agency) {
		Certification_Agency = certification_Agency;
	}

	public String getCertification_Std() {
		return Certification_Std;
	}

	public void setCertification_Std(String certification_Std) {
		Certification_Std = certification_Std;
	}

	public String getCertification_Value() {
		return Certification_Value;
	}

	public void setCertification_Value(String certification_Value) {
		Certification_Value = certification_Value;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
