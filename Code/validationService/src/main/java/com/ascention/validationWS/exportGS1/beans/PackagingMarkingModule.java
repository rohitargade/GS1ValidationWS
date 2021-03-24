package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class PackagingMarkingModule {

	private List<Packaging_Marking> lstPackaging_Marking;
	private List<Packaging_Returnable> lstPackaging_Returnable;
	private List<Packaging_Date> lstPackaging_Date;


	public List<Packaging_Marking> getLstPackaging_Marking() {
		return lstPackaging_Marking;
	}


	public void setLstPackaging_Marking(List<Packaging_Marking> lstPackaging_Marking) {
		this.lstPackaging_Marking = lstPackaging_Marking;
	}


	public List<Packaging_Returnable> getLstPackaging_Returnable() {
		return lstPackaging_Returnable;
	}


	public void setLstPackaging_Returnable(List<Packaging_Returnable> lstPackaging_Returnable) {
		this.lstPackaging_Returnable = lstPackaging_Returnable;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


	public List<Packaging_Date> getLstPackaging_Date() {
		return lstPackaging_Date;
	}


	public void setLstPackaging_Date(List<Packaging_Date> lstPackaging_Date) {
		this.lstPackaging_Date = lstPackaging_Date;
	}




}
