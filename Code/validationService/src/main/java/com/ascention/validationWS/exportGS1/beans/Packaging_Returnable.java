package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Packaging_Returnable {
	private String Packaging_Marked_Returnable;
	private String Is_Price_On_Pack;
	private String Marked_As_Recyclable;
	private String Packaging_Marked_Recyclable_Scheme;


	public String getPackaging_Marked_Returnable() {
		return Packaging_Marked_Returnable;
	}


	public void setPackaging_Marked_Returnable(String packaging_Marked_Returnable) {
		Packaging_Marked_Returnable = packaging_Marked_Returnable;
	}


	public String getIs_Price_On_Pack() {
		return Is_Price_On_Pack;
	}


	public void setIs_Price_On_Pack(String is_Price_On_Pack) {
		Is_Price_On_Pack = is_Price_On_Pack;
	}


	public String getMarked_As_Recyclable() {
		return Marked_As_Recyclable;
	}


	public void setMarked_As_Recyclable(String marked_As_Recyclable) {
		Marked_As_Recyclable = marked_As_Recyclable;
	}


	public String getPackaging_Marked_Recyclable_Scheme() {
		return Packaging_Marked_Recyclable_Scheme;
	}


	public void setPackaging_Marked_Recyclable_Scheme(String packaging_Marked_Recyclable_Scheme) {
		Packaging_Marked_Recyclable_Scheme = packaging_Marked_Recyclable_Scheme;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
