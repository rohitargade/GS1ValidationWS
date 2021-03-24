package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemMeasurements {
	private Double Depth;
	private String Depth_Uom;
	private Double Height;
	private String Height_Uom;
	private Double Width;
	private String Width_Uom;
	private Double Net_Weight;
	private String Net_Weight_Uom;
	private Double Gross_Weight;
	private String Gross_Weight_Uom;
	private Double Drained_Weight;
	private String Drained_Weight_Uom;
	private Double Net_Volume;
	private String In_Box_Cube_Dim_Uom;
	private Double Net_Base_Volume;
	private String Front_Face_Type;


	public Double getDepth() {
		return Depth;
	}


	public void setDepth(Double depth) {
		Depth = depth;
	}


	public String getDepth_Uom() {
		return Depth_Uom;
	}


	public void setDepth_Uom(String depth_Uom) {
		Depth_Uom = depth_Uom;
	}


	public Double getHeight() {
		return Height;
	}


	public void setHeight(Double height) {
		Height = height;
	}


	public String getHeight_Uom() {
		return Height_Uom;
	}


	public void setHeight_Uom(String height_Uom) {
		Height_Uom = height_Uom;
	}


	public Double getWidth() {
		return Width;
	}


	public void setWidth(Double width) {
		Width = width;
	}


	public String getWidth_Uom() {
		return Width_Uom;
	}


	public void setWidth_Uom(String width_Uom) {
		Width_Uom = width_Uom;
	}


	public Double getNet_Weight() {
		return Net_Weight;
	}


	public void setNet_Weight(Double net_Weight) {
		Net_Weight = net_Weight;
	}


	public String getNet_Weight_Uom() {
		return Net_Weight_Uom;
	}


	public void setNet_Weight_Uom(String net_Weight_Uom) {
		Net_Weight_Uom = net_Weight_Uom;
	}


	public Double getGross_Weight() {
		return Gross_Weight;
	}


	public void setGross_Weight(Double gross_Weight) {
		Gross_Weight = gross_Weight;
	}


	public String getGross_Weight_Uom() {
		return Gross_Weight_Uom;
	}


	public void setGross_Weight_Uom(String gross_Weight_Uom) {
		Gross_Weight_Uom = gross_Weight_Uom;
	}


	public Double getDrained_Weight() {
		return Drained_Weight;
	}


	public void setDrained_Weight(Double drained_Weight) {
		Drained_Weight = drained_Weight;
	}


	public String getDrained_Weight_Uom() {
		return Drained_Weight_Uom;
	}


	public void setDrained_Weight_Uom(String drained_Weight_Uom) {
		Drained_Weight_Uom = drained_Weight_Uom;
	}


	public Double getNet_Volume() {
		return Net_Volume;
	}


	public void setNet_Volume(Double net_Volume) {
		Net_Volume = net_Volume;
	}


	public String getIn_Box_Cube_Dim_Uom() {
		return In_Box_Cube_Dim_Uom;
	}


	public void setIn_Box_Cube_Dim_Uom(String in_Box_Cube_Dim_Uom) {
		In_Box_Cube_Dim_Uom = in_Box_Cube_Dim_Uom;
	}


	public Double getNet_Base_Volume() {
		return Net_Base_Volume;
	}


	public void setNet_Base_Volume(Double net_Base_Volume) {
		Net_Base_Volume = net_Base_Volume;
	}


	public String getFront_Face_Type() {
		return Front_Face_Type;
	}


	public void setFront_Face_Type(String front_Face_Type) {
		Front_Face_Type = front_Face_Type;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
