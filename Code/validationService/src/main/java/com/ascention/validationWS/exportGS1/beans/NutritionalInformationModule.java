package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class NutritionalInformationModule {

	private List<Nutritional_Claim> lstNutritional_Claim;
	private List<Nutritional_Claim_Detail> lstNutritional_Claim_Detail;
	private List<Serving_Size_Desc> lstServing_Size_Desc;
	private List<Nutrient_Header> lstNutrient_Header;	
	private List<Nutrient_Serving_Size> lstNutrient_Serving_Size;
	private List<Nutrient_Information> lstNutrient_Information;
	
	public List<Nutritional_Claim> getLstNutritional_Claim() {
		return lstNutritional_Claim;
	}


	public void setLstNutritional_Claim(List<Nutritional_Claim> lstNutritional_Claim) {
		this.lstNutritional_Claim = lstNutritional_Claim;
	}
	
	

	public List<Nutritional_Claim_Detail> getLstNutritional_Claim_Detail() {
		return lstNutritional_Claim_Detail;
	}


	public void setLstNutritional_Claim_Detail(List<Nutritional_Claim_Detail> lstNutritional_Claim_Detail) {
		this.lstNutritional_Claim_Detail = lstNutritional_Claim_Detail;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


	public List<Serving_Size_Desc> getLstServing_Size_Desc() {
		return lstServing_Size_Desc;
	}


	public void setLstServing_Size_Desc(List<Serving_Size_Desc> lstServing_Size_Desc) {
		this.lstServing_Size_Desc = lstServing_Size_Desc;
	}


	public List<Nutrient_Header> getLstNutrient_Header() {
		return lstNutrient_Header;
	}


	public void setLstNutrient_Header(List<Nutrient_Header> lstNutrient_Header) {
		this.lstNutrient_Header = lstNutrient_Header;
	}


	public List<Nutrient_Serving_Size> getLstNutrient_Serving_Size() {
		return lstNutrient_Serving_Size;
	}


	public void setLstNutrient_Serving_Size(List<Nutrient_Serving_Size> lstNutrient_Serving_Size) {
		this.lstNutrient_Serving_Size = lstNutrient_Serving_Size;
	}


	public List<Nutrient_Information> getLstNutrient_Information() {
		return lstNutrient_Information;
	}


	public void setLstNutrient_Information(List<Nutrient_Information> lstNutrient_Information) {
		this.lstNutrient_Information = lstNutrient_Information;
	}


}
