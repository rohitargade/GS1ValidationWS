package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class PreparationServing {
	private String Prep_ConsumptionPrecautions;
	private String Prep_Instr;
	private String Prep_Type;
	private String Serving_Suggestion;


	public String getPrep_ConsumptionPrecautions() {
		return Prep_ConsumptionPrecautions;
	}


	public void setPrep_ConsumptionPrecautions(String prep_ConsumptionPrecautions) {
		Prep_ConsumptionPrecautions = prep_ConsumptionPrecautions;
	}


	public String getPrep_Instr() {
		return Prep_Instr;
	}


	public void setPrep_Instr(String prep_Instr) {
		Prep_Instr = prep_Instr;
	}


	public String getPrep_Type() {
		return Prep_Type;
	}


	public void setPrep_Type(String prep_Type) {
		Prep_Type = prep_Type;
	}


	public String getServing_Suggestion() {
		return Serving_Suggestion;
	}


	public void setServing_Suggestion(String serving_Suggestion) {
		Serving_Suggestion = serving_Suggestion;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
