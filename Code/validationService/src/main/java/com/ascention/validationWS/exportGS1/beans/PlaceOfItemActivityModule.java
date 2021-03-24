package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class PlaceOfItemActivityModule {

	private List<Import_Classification> lstImport_Classification;
	private List<PlaceOfProductActivity> lstPlaceOfProductActivity;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}






	public List<PlaceOfProductActivity> getLstPlaceOfProductActivity() {
		return lstPlaceOfProductActivity;
	}






	public void setLstPlaceOfProductActivity(List<PlaceOfProductActivity> lstPlaceOfProductActivity) {
		this.lstPlaceOfProductActivity = lstPlaceOfProductActivity;
	}






	public List<Import_Classification> getLstImport_Classification() {
		return lstImport_Classification;
	}






	public void setLstImport_Classification(List<Import_Classification> lstImport_Classification) {
		this.lstImport_Classification = lstImport_Classification;
	}




}
