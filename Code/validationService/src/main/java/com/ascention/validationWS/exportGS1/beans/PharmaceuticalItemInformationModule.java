package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class PharmaceuticalItemInformationModule {
	private String Dosage_Recommendation;
	private String Drug_Interactions;
	
	

	


	public String getDosage_Recommendation() {
		return Dosage_Recommendation;
	}






	public void setDosage_Recommendation(String dosage_Recommendation) {
		Dosage_Recommendation = dosage_Recommendation;
	}






	public String getDrug_Interactions() {
		return Drug_Interactions;
	}






	public void setDrug_Interactions(String drug_Interactions) {
		Drug_Interactions = drug_Interactions;
	}






	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
