package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Packaging_Material {
	private String Pack_Mat_Type;
	private Double Pack_Mat_Comp_Qty;
	private String Pack_Mat_Comp_Qty_Uom;
	
	public String getPack_Mat_Type() {
		return Pack_Mat_Type;
	}

	public void setPack_Mat_Type(String pack_Mat_Type) {
		Pack_Mat_Type = pack_Mat_Type;
	}

	

	public String getPack_Mat_Comp_Qty_Uom() {
		return Pack_Mat_Comp_Qty_Uom;
	}

	public void setPack_Mat_Comp_Qty_Uom(String pack_Mat_Comp_Qty_Uom) {
		Pack_Mat_Comp_Qty_Uom = pack_Mat_Comp_Qty_Uom;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public Double getPack_Mat_Comp_Qty() {
		return Pack_Mat_Comp_Qty;
	}

	public void setPack_Mat_Comp_Qty(Double pack_Mat_Comp_Qty) {
		Pack_Mat_Comp_Qty = pack_Mat_Comp_Qty;
	}



	
	

}
