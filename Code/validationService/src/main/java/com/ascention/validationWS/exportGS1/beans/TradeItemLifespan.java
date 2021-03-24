package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemLifespan {

	private Integer Min_Life_Arrival;
	private Integer Min_Life_Production;
	private Integer Open_Prod_Lifespan;

	public Integer getMin_Life_Arrival() {
		return Min_Life_Arrival;
	}

	public void setMin_Life_Arrival(Integer min_Life_Arrival) {
		Min_Life_Arrival = min_Life_Arrival;
	}

	public Integer getMin_Life_Production() {
		return Min_Life_Production;
	}

	public void setMin_Life_Production(Integer min_Life_Production) {
		Min_Life_Production = min_Life_Production;
	}

	public Integer getOpen_Prod_Lifespan() {
		return Open_Prod_Lifespan;
	}

	public void setOpen_Prod_Lifespan(Integer open_Prod_Lifespan) {
		Open_Prod_Lifespan = open_Prod_Lifespan;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

		



}
