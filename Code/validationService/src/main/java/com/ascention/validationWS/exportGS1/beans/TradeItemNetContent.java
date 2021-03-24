package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemNetContent {
	private Float Net_Content;
	private String Net_Content_Uom;

	public Float getNet_Content() {
		return Net_Content;
	}


	public void setNet_Content(Float net_Content) {
		Net_Content = net_Content;
	}


	public String getNet_Content_Uom() {
		return Net_Content_Uom;
	}


	public void setNet_Content_Uom(String net_Content_Uom) {
		Net_Content_Uom = net_Content_Uom;
	}



	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
