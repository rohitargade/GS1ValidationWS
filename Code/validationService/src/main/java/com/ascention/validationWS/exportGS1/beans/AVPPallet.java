package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class AVPPallet {

	private Integer baseUnitsPerPallet;
	private Float palletDepth;
	private Float palletWidth;
	private Float palletHeight;
	private Float palletVolume;
	private Float palletNetWeight;
	private Float palletGrossWeight;
	private String Pallet_Build;
	
	
	public Integer getBaseUnitsPerPallet() {
		return baseUnitsPerPallet;
	}
	public void setBaseUnitsPerPallet(Integer baseUnitsPerPallet) {
		this.baseUnitsPerPallet = baseUnitsPerPallet;
	}
	public Float getPalletDepth() {
		return palletDepth;
	}
	public void setPalletDepth(Float palletDepth) {
		this.palletDepth = palletDepth;
	}
	public Float getPalletWidth() {
		return palletWidth;
	}
	public void setPalletWidth(Float palletWidth) {
		this.palletWidth = palletWidth;
	}
	public Float getPalletHeight() {
		return palletHeight;
	}
	public void setPalletHeight(Float palletHeight) {
		this.palletHeight = palletHeight;
	}
	public Float getPalletVolume() {
		return palletVolume;
	}
	public void setPalletVolume(Float palletVolume) {
		this.palletVolume = palletVolume;
	}
	public Float getPalletNetWeight() {
		return palletNetWeight;
	}
	public void setPalletNetWeight(Float palletNetWeight) {
		this.palletNetWeight = palletNetWeight;
	}
	public Float getPalletGrossWeight() {
		return palletGrossWeight;
	}
	public void setPalletGrossWeight(Float palletGrossWeight) {
		this.palletGrossWeight = palletGrossWeight;
	}
	public String getPallet_Build() {
		return Pallet_Build;
	}
	public void setPallet_Build(String pallet_Build) {
		Pallet_Build = pallet_Build;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}




}
