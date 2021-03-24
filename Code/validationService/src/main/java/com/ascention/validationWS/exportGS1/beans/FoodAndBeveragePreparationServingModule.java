package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class FoodAndBeveragePreparationServingModule {
	
	private List<ServingQuantityInformation> lstServingQuantityInformation;
	private List<PreparationServing> lstPreparationServing;
		
	

	

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }





	public List<ServingQuantityInformation> getLstServingQuantityInformation() {
		return lstServingQuantityInformation;
	}





	public void setLstServingQuantityInformation(List<ServingQuantityInformation> lstServingQuantityInformation) {
		this.lstServingQuantityInformation = lstServingQuantityInformation;
	}





	public List<PreparationServing> getLstPreparationServing() {
		return lstPreparationServing;
	}





	public void setLstPreparationServing(List<PreparationServing> lstPreparationServing) {
		this.lstPreparationServing = lstPreparationServing;
	}



	
	
}
