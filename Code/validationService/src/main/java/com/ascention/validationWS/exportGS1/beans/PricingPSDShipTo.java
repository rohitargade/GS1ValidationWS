package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class PricingPSDShipTo {
	private String Organisation_Address_GLN;
	

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }


	public String getOrganisation_Address_GLN() {
		return Organisation_Address_GLN;
	}


	public void setOrganisation_Address_GLN(String organisation_Address_GLN) {
		Organisation_Address_GLN = organisation_Address_GLN;
	}


	
}
