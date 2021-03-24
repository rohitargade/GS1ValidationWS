package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


public class PricingTierRule {
	
	private Integer Price_Dtl_Id;
	private Integer Tier_Rule_Id;
	private Integer Tier_Rule_Seq;
	private String Rule_Operator;
	private String Rule_Range_Qualifier;
	private Double Rule_Min;
	private String Rule_Min_Uom;
	private Double Rule_Max;
	private String Rule_Max_Uom;


	public Integer getPrice_Dtl_Id() {
		return Price_Dtl_Id;
	}


	public void setPrice_Dtl_Id(Integer price_Dtl_Id) {
		Price_Dtl_Id = price_Dtl_Id;
	}


	public Integer getTier_Rule_Id() {
		return Tier_Rule_Id;
	}


	public void setTier_Rule_Id(Integer tier_Rule_Id) {
		Tier_Rule_Id = tier_Rule_Id;
	}


	public Integer getTier_Rule_Seq() {
		return Tier_Rule_Seq;
	}


	public void setTier_Rule_Seq(Integer tier_Rule_Seq) {
		Tier_Rule_Seq = tier_Rule_Seq;
	}


	public String getRule_Operator() {
		return Rule_Operator;
	}


	public void setRule_Operator(String rule_Operator) {
		Rule_Operator = rule_Operator;
	}


	public String getRule_Range_Qualifier() {
		return Rule_Range_Qualifier;
	}


	public void setRule_Range_Qualifier(String rule_Range_Qualifier) {
		Rule_Range_Qualifier = rule_Range_Qualifier;
	}


	public Double getRule_Min() {
		return Rule_Min;
	}


	public void setRule_Min(Double rule_Min) {
		Rule_Min = rule_Min;
	}


	public String getRule_Min_Uom() {
		return Rule_Min_Uom;
	}


	public void setRule_Min_Uom(String rule_Min_Uom) {
		Rule_Min_Uom = rule_Min_Uom;
	}


	public Double getRule_Max() {
		return Rule_Max;
	}


	public void setRule_Max(Double rule_Max) {
		Rule_Max = rule_Max;
	}


	public String getRule_Max_Uom() {
		return Rule_Max_Uom;
	}


	public void setRule_Max_Uom(String rule_Max_Uom) {
		Rule_Max_Uom = rule_Max_Uom;
	}


	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }



	
}
