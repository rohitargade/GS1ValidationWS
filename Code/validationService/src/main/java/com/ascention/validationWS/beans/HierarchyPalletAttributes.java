package com.ascention.validationWS.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class HierarchyPalletAttributes {

	private String Carton_Per_Ship_Unit;
	private String Carton_Layer_Per_Pallet;
	private String Carton_Per_Pallet_Layer;
	private String Height;
	private String Gross_Weight;
	private String Net_Weight;
	private Integer Product_Id;
	private Integer Product_Hierarchy_Id;
	
	public Integer getProduct_Id() {
		return Product_Id;
	}


	public void setProduct_Id(Integer product_Id) {
		Product_Id = product_Id;
	}


	public Integer getProduct_Hierarchy_Id() {
		return Product_Hierarchy_Id;
	}


	public void setProduct_Hierarchy_Id(Integer product_Hierarchy_Id) {
		Product_Hierarchy_Id = product_Hierarchy_Id;
	}


	public String getCarton_Per_Ship_Unit() {
		return Carton_Per_Ship_Unit;
	}


	public void setCarton_Per_Ship_Unit(String carton_Per_Ship_Unit) {
		Carton_Per_Ship_Unit = carton_Per_Ship_Unit;
	}


	public String getCarton_Layer_Per_Pallet() {
		return Carton_Layer_Per_Pallet;
	}


	public void setCarton_Layer_Per_Pallet(String carton_Layer_Per_Pallet) {
		Carton_Layer_Per_Pallet = carton_Layer_Per_Pallet;
	}


	public String getCarton_Per_Pallet_Layer() {
		return Carton_Per_Pallet_Layer;
	}


	public void setCarton_Per_Pallet_Layer(String carton_Per_Pallet_Layer) {
		Carton_Per_Pallet_Layer = carton_Per_Pallet_Layer;
	}


	public String getHeight() {
		return Height;
	}


	public void setHeight(String height) {
		Height = height;
	}


	public String getGross_Weight() {
		return Gross_Weight;
	}


	public void setGross_Weight(String gross_Weight) {
		Gross_Weight = gross_Weight;
	}


	public String getNet_Weight() {
		return Net_Weight;
	}


	public void setNet_Weight(String net_Weight) {
		Net_Weight = net_Weight;
	}


	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }
}
