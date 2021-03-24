package com.ascention.validationWS.exportGS1.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TradeItemHierarchy {
	private Integer Product_Id;
	private Integer Product_Hierarchy_Id;
	private String Gtin;
	private String Is_Base_Unit;
	private String Is_Consumer_Unit;
	private String Is_Despatch_Unit;
	private String Is_Invoice_Unit;
	private String Is_Orderable_Unit;
	private String Is_Service;
	private String Is_Non_Physical;
	private String Is_Recalled;
	private String Prod_Unit_Desc;
	private String Brand_Owner_GLN;
	private String Brand_Owner_Name;
	private String Brand_Owner_Address;
	private String Manufacturer_GLN;
	private String Manufacturer_Name;
	private String Manufacturer_Address;
	private String Display_Ready_Packaging;
	private String Gpc_Brick;
	private String Community_Visibility_Date;
	private String Effective_Date;
	private String Publication_Date;

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

	public String getGtin() {
		return Gtin;
	}

	public void setGtin(String gtin) {
		Gtin = gtin;
	}

	public String getIs_Base_Unit() {
		return Is_Base_Unit;
	}

	public void setIs_Base_Unit(String is_Base_Unit) {
		Is_Base_Unit = is_Base_Unit;
	}

	public String getIs_Consumer_Unit() {
		return Is_Consumer_Unit;
	}

	public void setIs_Consumer_Unit(String is_Consumer_Unit) {
		Is_Consumer_Unit = is_Consumer_Unit;
	}

	public String getIs_Despatch_Unit() {
		return Is_Despatch_Unit;
	}

	public void setIs_Despatch_Unit(String is_Despatch_Unit) {
		Is_Despatch_Unit = is_Despatch_Unit;
	}

	public String getIs_Invoice_Unit() {
		return Is_Invoice_Unit;
	}

	public void setIs_Invoice_Unit(String is_Invoice_Unit) {
		Is_Invoice_Unit = is_Invoice_Unit;
	}

	public String getIs_Orderable_Unit() {
		return Is_Orderable_Unit;
	}

	public void setIs_Orderable_Unit(String is_Orderable_Unit) {
		Is_Orderable_Unit = is_Orderable_Unit;
	}

	public String getIs_Service() {
		return Is_Service;
	}

	public void setIs_Service(String is_Service) {
		Is_Service = is_Service;
	}

	public String getIs_Non_Physical() {
		return Is_Non_Physical;
	}

	public void setIs_Non_Physical(String is_Non_Physical) {
		Is_Non_Physical = is_Non_Physical;
	}

	public String getIs_Recalled() {
		return Is_Recalled;
	}

	public void setIs_Recalled(String is_Recalled) {
		Is_Recalled = is_Recalled;
	}

	public String getProd_Unit_Desc() {
		return Prod_Unit_Desc;
	}

	public void setProd_Unit_Desc(String prod_Unit_Desc) {
		Prod_Unit_Desc = prod_Unit_Desc;
	}

	public String getBrand_Owner_GLN() {
		return Brand_Owner_GLN;
	}

	public void setBrand_Owner_GLN(String brand_Owner_GLN) {
		Brand_Owner_GLN = brand_Owner_GLN;
	}

	public String getBrand_Owner_Name() {
		return Brand_Owner_Name;
	}

	public void setBrand_Owner_Name(String brand_Owner_Name) {
		Brand_Owner_Name = brand_Owner_Name;
	}

	public String getBrand_Owner_Address() {
		return Brand_Owner_Address;
	}

	public void setBrand_Owner_Address(String brand_Owner_Address) {
		Brand_Owner_Address = brand_Owner_Address;
	}

	public String getManufacturer_GLN() {
		return Manufacturer_GLN;
	}

	public void setManufacturer_GLN(String manufacturer_GLN) {
		Manufacturer_GLN = manufacturer_GLN;
	}

	public String getManufacturer_Name() {
		return Manufacturer_Name;
	}

	public void setManufacturer_Name(String manufacturer_Name) {
		Manufacturer_Name = manufacturer_Name;
	}

	public String getManufacturer_Address() {
		return Manufacturer_Address;
	}

	public void setManufacturer_Address(String manufacturer_Address) {
		Manufacturer_Address = manufacturer_Address;
	}

	public String getDisplay_Ready_Packaging() {
		return Display_Ready_Packaging;
	}

	public void setDisplay_Ready_Packaging(String display_Ready_Packaging) {
		Display_Ready_Packaging = display_Ready_Packaging;
	}

	public String getGpc_Brick() {
		return Gpc_Brick;
	}

	public void setGpc_Brick(String gpc_Brick) {
		Gpc_Brick = gpc_Brick;
	}

	public String getCommunity_Visibility_Date() {
		return Community_Visibility_Date;
	}

	public void setCommunity_Visibility_Date(String community_Visibility_Date) {
		Community_Visibility_Date = community_Visibility_Date;
	}

	public String getEffective_Date() {
		return Effective_Date;
	}

	public void setEffective_Date(String effective_Date) {
		Effective_Date = effective_Date;
	}

	public String getPublication_Date() {
		return Publication_Date;
	}

	public void setPublication_Date(String publication_Date) {
		Publication_Date = publication_Date;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	

	

}
