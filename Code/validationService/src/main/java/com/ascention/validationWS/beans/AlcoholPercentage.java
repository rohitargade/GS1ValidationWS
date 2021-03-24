package com.ascention.validationWS.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class AlcoholPercentage {
	private Float Alcohol_By_Vol;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public Float getAlcohol_By_Vol() {
		return Alcohol_By_Vol;
	}

	public void setAlcohol_By_Vol(Float alcohol_By_Vol) {
		Alcohol_By_Vol = alcohol_By_Vol;
	}
}
