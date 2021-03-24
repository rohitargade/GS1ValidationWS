package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class WarrantyInformation {
	private String Warranty_Desc;
	private Float Warranty_Duration;
	private String Warranty_Duration_Uom;

	public String getWarranty_Desc() {
		return Warranty_Desc;
	}

	public void setWarranty_Desc(String warranty_Desc) {
		Warranty_Desc = warranty_Desc;
	}

	public Float getWarranty_Duration() {
		return Warranty_Duration;
	}

	public void setWarranty_Duration(Float warranty_Duration) {
		Warranty_Duration = warranty_Duration;
	}

	public String getWarranty_Duration_Uom() {
		return Warranty_Duration_Uom;
	}

	public void setWarranty_Duration_Uom(String warranty_Duration_Uom) {
		Warranty_Duration_Uom = warranty_Duration_Uom;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


}
