package com.ascention.validationWS.exportGS1.beans;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemPegMeasurements {
	private Integer Peg_Hole_Num;
	private BigDecimal Peg_Horizontal;
	private String Peg_Horizontal_Uom;
	private BigDecimal Peg_Vertical;
	private String Peg_Vertical_Uom;


	public Integer getPeg_Hole_Num() {
		return Peg_Hole_Num;
	}


	public void setPeg_Hole_Num(Integer peg_Hole_Num) {
		Peg_Hole_Num = peg_Hole_Num;
	}


	

	public String getPeg_Horizontal_Uom() {
		return Peg_Horizontal_Uom;
	}


	public void setPeg_Horizontal_Uom(String peg_Horizontal_Uom) {
		Peg_Horizontal_Uom = peg_Horizontal_Uom;
	}



	public String getPeg_Vertical_Uom() {
		return Peg_Vertical_Uom;
	}


	public void setPeg_Vertical_Uom(String peg_Vertical_Uom) {
		Peg_Vertical_Uom = peg_Vertical_Uom;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


	public BigDecimal getPeg_Horizontal() {
		return Peg_Horizontal;
	}


	public void setPeg_Horizontal(BigDecimal peg_Horizontal) {
		Peg_Horizontal = peg_Horizontal;
	}


	public BigDecimal getPeg_Vertical() {
		return Peg_Vertical;
	}


	public void setPeg_Vertical(BigDecimal peg_Vertical) {
		Peg_Vertical = peg_Vertical;
	}

}
