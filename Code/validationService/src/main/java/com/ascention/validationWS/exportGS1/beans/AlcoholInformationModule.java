package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class AlcoholInformationModule {
	private Integer Vintage;
	private String PercentageOfAlcoholByVolume;
	private List<Vintner> Vintner;
	
	

	public String getPercentageOfAlcoholByVolume() {
		return PercentageOfAlcoholByVolume;
	}

	public void setPercentageOfAlcoholByVolume(String percentageOfAlcoholByVolume) {
		this.PercentageOfAlcoholByVolume = percentageOfAlcoholByVolume;
	}

	public List<Vintner> getVintner() {
		return Vintner;
	}

	public void setVintner(List<Vintner> vintner) {
		this.Vintner = vintner;
	}

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

	public Integer getVintage() {
		return Vintage;
	}

	public void setVintage(Integer vintage) {
		Vintage = vintage;
	}
}
