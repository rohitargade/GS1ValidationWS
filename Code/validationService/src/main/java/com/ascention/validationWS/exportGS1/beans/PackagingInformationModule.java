package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class PackagingInformationModule {

	private List<Packaging> lstPackaging;
	private List<Packaging_Function> lstPackaging_Function;
	private List<Packaging_Recycle> lstPackaging_Recycle;
	private List<Packaging_Recycle_Scheme> lstPackaging_Recycle_Scheme;
	private List<Packaging_Sustainability> lstPackaging_Sustainability;
	private List<Packaging_Material> lstPackaging_Material;
	private List<Packaging_Deposit> lstPackaging_Deposit;

	
	

	public List<Packaging> getLstPackaging() {
		return lstPackaging;
	}




	public void setLstPackaging(List<Packaging> lstPackaging) {
		this.lstPackaging = lstPackaging;
	}




	public List<Packaging_Function> getLstPackaging_Function() {
		return lstPackaging_Function;
	}




	public void setLstPackaging_Function(List<Packaging_Function> lstPackaging_Function) {
		this.lstPackaging_Function = lstPackaging_Function;
	}




	public List<Packaging_Recycle> getLstPackaging_Recycle() {
		return lstPackaging_Recycle;
	}




	public void setLstPackaging_Recycle(List<Packaging_Recycle> lstPackaging_Recycle) {
		this.lstPackaging_Recycle = lstPackaging_Recycle;
	}




	public List<Packaging_Recycle_Scheme> getLstPackaging_Recycle_Scheme() {
		return lstPackaging_Recycle_Scheme;
	}




	public void setLstPackaging_Recycle_Scheme(List<Packaging_Recycle_Scheme> lstPackaging_Recycle_Scheme) {
		this.lstPackaging_Recycle_Scheme = lstPackaging_Recycle_Scheme;
	}




	public List<Packaging_Sustainability> getLstPackaging_Sustainability() {
		return lstPackaging_Sustainability;
	}




	public void setLstPackaging_Sustainability(List<Packaging_Sustainability> lstPackaging_Sustainability) {
		this.lstPackaging_Sustainability = lstPackaging_Sustainability;
	}




	public List<Packaging_Material> getLstPackaging_Material() {
		return lstPackaging_Material;
	}




	public void setLstPackaging_Material(List<Packaging_Material> lstPackaging_Material) {
		this.lstPackaging_Material = lstPackaging_Material;
	}




	public List<Packaging_Deposit> getLstPackaging_Deposit() {
		return lstPackaging_Deposit;
	}




	public void setLstPackaging_Deposit(List<Packaging_Deposit> lstPackaging_Deposit) {
		this.lstPackaging_Deposit = lstPackaging_Deposit;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}




}
