package com.ascention.validationWS.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ValidationRule {

	private	Integer rule_id;
	private	String validation_code;
	private String Internal_Rule_Id;
	
	
	public Integer getRule_id() {
		return rule_id;
	}
	public void setRule_id(Integer rule_id) {
		this.rule_id = rule_id;
	}
	public String getValidation_code() {
		return validation_code;
	}
	public void setValidation_code(String validation_code) {
		this.validation_code = validation_code;
	}
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }
	public String getInternal_Rule_Id() {
		return Internal_Rule_Id;
	}
	public void setInternal_Rule_Id(String internal_Rule_Id) {
		Internal_Rule_Id = internal_Rule_Id;
	}
}
