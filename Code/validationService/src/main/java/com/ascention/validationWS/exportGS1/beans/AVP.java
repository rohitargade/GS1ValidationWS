package com.ascention.validationWS.exportGS1.beans;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class AVP {

	private String Full_Desc;
	private String Final_Batch_Expiry_Date;
	private String Flash_Point_Type;
	private String Hazardous_Identifier;
	private String Dangerous_Good;
	private String Hazardous_Good;
	private String Sds_Issue_Date;
	private String Cds_Mat_Type;
	private String Has_Safety_Warning;
	private String Labelling_Claims;
	private String Consumer_Recommended_Use;
	private String Suitable_For;
	private String Custom_Stats_Code;
	private String Ce_Code;
	private String Ce_Type;
	private String Tga_Warning_Statement;
	private String Alcohol_Strength;
	private String Champagne_Ind;
	private String Liquor_Age;
	private String Liquor_Mkt_Segment;
	private String Std_Drinks;
	private String Sweetness_Level_Ind;
	private String Wooded_Code;
	private String Shelf_Life_After_Thaw;
	private String Gram_Measure_Dec;
	private String Total_Energy_Dec;
	private String Health_Star_Rating;
	private String Interpretive_Term_Opt_Nutrient;
	private String Interpretive_Term_Sat_Fat;
	private String Interpretive_Term_Sodium;
	private String Interpretive_Term_Sugar;
	private String Optional_Nutrient_Code;
	private String Labelling_Logos;
	private String Labelling_Country_Origin;
	private String Labelling_Ingr_Statement;
	private String Labelling_Packed_Statement;
	private Float Labelling_Aus_Content_Pct;
	private String Labelling_Prod_Name;
	private String Labelling_Addtl_Phrase;
	private String Labelling_Addtl_Dtls;
	private String gtin;
	private String Is_Base_Unit;
	private String Base_Unit_Qty;
	private String Child_Layer_Unit_Depth;
	private String Child_Layer_Unit_Width;
	private Double Net_Volume;
	private String Net_Volume_Uom;
	private String Trade_Measurement_Method;
	private String Dec_Weight_Vol;
	private String Dec_Weight_Vol_Uom;
	private String Warning_On_Package;
	private String Nesting;
	private String Hazard_Pack_Type;
	private String Hazardous_Packaging_Type;
	private String Hazardous_Unit_Vol;
	private String Hazardous_Unit_Vol_Uom;
	private String Hazardous_Unit_Size;
	private String Hazardous_Unit_Size_Uom;
	private String Promotional_Product;
	private String Mfr_Internal_Ref;
	private String Priority_Food;

	private AVPPallet aVPPallet;
	private List<AVPCustomsExcisableValue> lstAVPCustomsExcisableValue;
	private List<AVPHsnoApproval> lstAVPHsnoApproval;
	private List<AVPHsnoClassification> lstAVPHsnoClassification;
	private List<AVPEnvironmental> lstAVPEnvironmental;
	
	public String getFull_Desc() {
		return Full_Desc;
	}


	public void setFull_Desc(String full_Desc) {
		Full_Desc = full_Desc;
	}


	public String getFinal_Batch_Expiry_Date() {
		return Final_Batch_Expiry_Date;
	}


	public void setFinal_Batch_Expiry_Date(String final_Batch_Expiry_Date) {
		Final_Batch_Expiry_Date = final_Batch_Expiry_Date;
	}


	public String getFlash_Point_Type() {
		return Flash_Point_Type;
	}


	public void setFlash_Point_Type(String flash_Point_Type) {
		Flash_Point_Type = flash_Point_Type;
	}


	public String getHazardous_Identifier() {
		return Hazardous_Identifier;
	}


	public void setHazardous_Identifier(String hazardous_Identifier) {
		Hazardous_Identifier = hazardous_Identifier;
	}


	public String getDangerous_Good() {
		return Dangerous_Good;
	}


	public void setDangerous_Good(String dangerous_Good) {
		Dangerous_Good = dangerous_Good;
	}


	public String getHazardous_Good() {
		return Hazardous_Good;
	}


	public void setHazardous_Good(String hazardous_Good) {
		Hazardous_Good = hazardous_Good;
	}


	public String getSds_Issue_Date() {
		return Sds_Issue_Date;
	}


	public void setSds_Issue_Date(String sds_Issue_Date) {
		Sds_Issue_Date = sds_Issue_Date;
	}


	public String getCds_Mat_Type() {
		return Cds_Mat_Type;
	}


	public void setCds_Mat_Type(String cds_Mat_Type) {
		Cds_Mat_Type = cds_Mat_Type;
	}


	public String getHas_Safety_Warning() {
		return Has_Safety_Warning;
	}


	public void setHas_Safety_Warning(String has_Safety_Warning) {
		Has_Safety_Warning = has_Safety_Warning;
	}


	public String getLabelling_Claims() {
		return Labelling_Claims;
	}


	public void setLabelling_Claims(String labelling_Claims) {
		Labelling_Claims = labelling_Claims;
	}


	public String getConsumer_Recommended_Use() {
		return Consumer_Recommended_Use;
	}


	public void setConsumer_Recommended_Use(String consumer_Recommended_Use) {
		Consumer_Recommended_Use = consumer_Recommended_Use;
	}


	public String getSuitable_For() {
		return Suitable_For;
	}


	public void setSuitable_For(String suitable_For) {
		Suitable_For = suitable_For;
	}


	public String getCustom_Stats_Code() {
		return Custom_Stats_Code;
	}


	public void setCustom_Stats_Code(String custom_Stats_Code) {
		Custom_Stats_Code = custom_Stats_Code;
	}


	public String getCe_Code() {
		return Ce_Code;
	}


	public void setCe_Code(String ce_Code) {
		Ce_Code = ce_Code;
	}


	public String getCe_Type() {
		return Ce_Type;
	}


	public void setCe_Type(String ce_Type) {
		Ce_Type = ce_Type;
	}


	public String getAlcohol_Strength() {
		return Alcohol_Strength;
	}


	public void setAlcohol_Strength(String alcohol_Strength) {
		Alcohol_Strength = alcohol_Strength;
	}


	public String getChampagne_Ind() {
		return Champagne_Ind;
	}


	public void setChampagne_Ind(String champagne_Ind) {
		Champagne_Ind = champagne_Ind;
	}


	public String getLiquor_Age() {
		return Liquor_Age;
	}


	public void setLiquor_Age(String liquor_Age) {
		Liquor_Age = liquor_Age;
	}


	public String getLiquor_Mkt_Segment() {
		return Liquor_Mkt_Segment;
	}


	public void setLiquor_Mkt_Segment(String liquor_Mkt_Segment) {
		Liquor_Mkt_Segment = liquor_Mkt_Segment;
	}


	public String getStd_Drinks() {
		return Std_Drinks;
	}


	public void setStd_Drinks(String std_Drinks) {
		Std_Drinks = std_Drinks;
	}


	public String getSweetness_Level_Ind() {
		return Sweetness_Level_Ind;
	}


	public void setSweetness_Level_Ind(String sweetness_Level_Ind) {
		Sweetness_Level_Ind = sweetness_Level_Ind;
	}


	public String getWooded_Code() {
		return Wooded_Code;
	}


	public void setWooded_Code(String wooded_Code) {
		Wooded_Code = wooded_Code;
	}


	public String getShelf_Life_After_Thaw() {
		return Shelf_Life_After_Thaw;
	}


	public void setShelf_Life_After_Thaw(String shelf_Life_After_Thaw) {
		Shelf_Life_After_Thaw = shelf_Life_After_Thaw;
	}


	public String getGram_Measure_Dec() {
		return Gram_Measure_Dec;
	}


	public void setGram_Measure_Dec(String gram_Measure_Dec) {
		Gram_Measure_Dec = gram_Measure_Dec;
	}


	public String getTotal_Energy_Dec() {
		return Total_Energy_Dec;
	}


	public void setTotal_Energy_Dec(String total_Energy_Dec) {
		Total_Energy_Dec = total_Energy_Dec;
	}


	public String getHealth_Star_Rating() {
		return Health_Star_Rating;
	}


	public void setHealth_Star_Rating(String health_Star_Rating) {
		Health_Star_Rating = health_Star_Rating;
	}


	public String getInterpretive_Term_Opt_Nutrient() {
		return Interpretive_Term_Opt_Nutrient;
	}


	public void setInterpretive_Term_Opt_Nutrient(String interpretive_Term_Opt_Nutrient) {
		Interpretive_Term_Opt_Nutrient = interpretive_Term_Opt_Nutrient;
	}


	public String getInterpretive_Term_Sat_Fat() {
		return Interpretive_Term_Sat_Fat;
	}


	public void setInterpretive_Term_Sat_Fat(String interpretive_Term_Sat_Fat) {
		Interpretive_Term_Sat_Fat = interpretive_Term_Sat_Fat;
	}


	public String getInterpretive_Term_Sodium() {
		return Interpretive_Term_Sodium;
	}


	public void setInterpretive_Term_Sodium(String interpretive_Term_Sodium) {
		Interpretive_Term_Sodium = interpretive_Term_Sodium;
	}


	public String getInterpretive_Term_Sugar() {
		return Interpretive_Term_Sugar;
	}


	public void setInterpretive_Term_Sugar(String interpretive_Term_Sugar) {
		Interpretive_Term_Sugar = interpretive_Term_Sugar;
	}


	public String getOptional_Nutrient_Code() {
		return Optional_Nutrient_Code;
	}


	public void setOptional_Nutrient_Code(String optional_Nutrient_Code) {
		Optional_Nutrient_Code = optional_Nutrient_Code;
	}


	public String getLabelling_Logos() {
		return Labelling_Logos;
	}


	public void setLabelling_Logos(String labelling_Logos) {
		Labelling_Logos = labelling_Logos;
	}


	public String getLabelling_Country_Origin() {
		return Labelling_Country_Origin;
	}


	public void setLabelling_Country_Origin(String labelling_Country_Origin) {
		Labelling_Country_Origin = labelling_Country_Origin;
	}


	public String getLabelling_Ingr_Statement() {
		return Labelling_Ingr_Statement;
	}


	public void setLabelling_Ingr_Statement(String labelling_Ingr_Statement) {
		Labelling_Ingr_Statement = labelling_Ingr_Statement;
	}


	public String getLabelling_Packed_Statement() {
		return Labelling_Packed_Statement;
	}


	public void setLabelling_Packed_Statement(String labelling_Packed_Statement) {
		Labelling_Packed_Statement = labelling_Packed_Statement;
	}


	public Float getLabelling_Aus_Content_Pct() {
		return Labelling_Aus_Content_Pct;
	}


	public void setLabelling_Aus_Content_Pct(Float labelling_Aus_Content_Pct) {
		Labelling_Aus_Content_Pct = labelling_Aus_Content_Pct;
	}


	public String getLabelling_Prod_Name() {
		return Labelling_Prod_Name;
	}


	public void setLabelling_Prod_Name(String labelling_Prod_Name) {
		Labelling_Prod_Name = labelling_Prod_Name;
	}


	public String getLabelling_Addtl_Phrase() {
		return Labelling_Addtl_Phrase;
	}


	public void setLabelling_Addtl_Phrase(String labelling_Addtl_Phrase) {
		Labelling_Addtl_Phrase = labelling_Addtl_Phrase;
	}


	public String getLabelling_Addtl_Dtls() {
		return Labelling_Addtl_Dtls;
	}


	public void setLabelling_Addtl_Dtls(String labelling_Addtl_Dtls) {
		Labelling_Addtl_Dtls = labelling_Addtl_Dtls;
	}


	public String getGtin() {
		return gtin;
	}


	public void setGtin(String gtin) {
		this.gtin = gtin;
	}


	public String getIs_Base_Unit() {
		return Is_Base_Unit;
	}


	public void setIs_Base_Unit(String is_Base_Unit) {
		Is_Base_Unit = is_Base_Unit;
	}


	public String getBase_Unit_Qty() {
		return Base_Unit_Qty;
	}


	public void setBase_Unit_Qty(String base_Unit_Qty) {
		Base_Unit_Qty = base_Unit_Qty;
	}


	public String getChild_Layer_Unit_Depth() {
		return Child_Layer_Unit_Depth;
	}


	public void setChild_Layer_Unit_Depth(String child_Layer_Unit_Depth) {
		Child_Layer_Unit_Depth = child_Layer_Unit_Depth;
	}


	public String getChild_Layer_Unit_Width() {
		return Child_Layer_Unit_Width;
	}


	public void setChild_Layer_Unit_Width(String child_Layer_Unit_Width) {
		Child_Layer_Unit_Width = child_Layer_Unit_Width;
	}


	

	public String getNet_Volume_Uom() {
		return Net_Volume_Uom;
	}


	public void setNet_Volume_Uom(String net_Volume_Uom) {
		Net_Volume_Uom = net_Volume_Uom;
	}


	public String getTrade_Measurement_Method() {
		return Trade_Measurement_Method;
	}


	public void setTrade_Measurement_Method(String trade_Measurement_Method) {
		Trade_Measurement_Method = trade_Measurement_Method;
	}


	public String getDec_Weight_Vol() {
		return Dec_Weight_Vol;
	}


	public void setDec_Weight_Vol(String dec_Weight_Vol) {
		Dec_Weight_Vol = dec_Weight_Vol;
	}


	public String getDec_Weight_Vol_Uom() {
		return Dec_Weight_Vol_Uom;
	}


	public void setDec_Weight_Vol_Uom(String dec_Weight_Vol_Uom) {
		Dec_Weight_Vol_Uom = dec_Weight_Vol_Uom;
	}


	public String getWarning_On_Package() {
		return Warning_On_Package;
	}


	public void setWarning_On_Package(String warning_On_Package) {
		Warning_On_Package = warning_On_Package;
	}


	public String getNesting() {
		return Nesting;
	}


	public void setNesting(String nesting) {
		Nesting = nesting;
	}


	public String getHazard_Pack_Type() {
		return Hazard_Pack_Type;
	}


	public void setHazard_Pack_Type(String hazard_Pack_Type) {
		Hazard_Pack_Type = hazard_Pack_Type;
	}


	public String getHazardous_Packaging_Type() {
		return Hazardous_Packaging_Type;
	}


	public void setHazardous_Packaging_Type(String hazardous_Packaging_Type) {
		Hazardous_Packaging_Type = hazardous_Packaging_Type;
	}


	public String getHazardous_Unit_Vol() {
		return Hazardous_Unit_Vol;
	}


	public void setHazardous_Unit_Vol(String hazardous_Unit_Vol) {
		Hazardous_Unit_Vol = hazardous_Unit_Vol;
	}


	public String getHazardous_Unit_Vol_Uom() {
		return Hazardous_Unit_Vol_Uom;
	}


	public void setHazardous_Unit_Vol_Uom(String hazardous_Unit_Vol_Uom) {
		Hazardous_Unit_Vol_Uom = hazardous_Unit_Vol_Uom;
	}


	public String getHazardous_Unit_Size() {
		return Hazardous_Unit_Size;
	}


	public void setHazardous_Unit_Size(String hazardous_Unit_Size) {
		Hazardous_Unit_Size = hazardous_Unit_Size;
	}


	public String getHazardous_Unit_Size_Uom() {
		return Hazardous_Unit_Size_Uom;
	}


	public void setHazardous_Unit_Size_Uom(String hazardous_Unit_Size_Uom) {
		Hazardous_Unit_Size_Uom = hazardous_Unit_Size_Uom;
	}


	public String getPromotional_Product() {
		return Promotional_Product;
	}


	public void setPromotional_Product(String promotional_Product) {
		Promotional_Product = promotional_Product;
	}


	public String getMfr_Internal_Ref() {
		return Mfr_Internal_Ref;
	}


	public void setMfr_Internal_Ref(String mfr_Internal_Ref) {
		Mfr_Internal_Ref = mfr_Internal_Ref;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


	public AVPPallet getaVPPallet() {
		return aVPPallet;
	}


	public void setaVPPallet(AVPPallet aVPPallet) {
		this.aVPPallet = aVPPallet;
	}


	public List<AVPCustomsExcisableValue> getLstAVPCustomsExcisableValue() {
		return lstAVPCustomsExcisableValue;
	}


	public void setLstAVPCustomsExcisableValue(List<AVPCustomsExcisableValue> lstAVPCustomsExcisableValue) {
		this.lstAVPCustomsExcisableValue = lstAVPCustomsExcisableValue;
	}


	public List<AVPHsnoApproval> getLstAVPHsnoApproval() {
		return lstAVPHsnoApproval;
	}


	public void setLstAVPHsnoApproval(List<AVPHsnoApproval> lstAVPHsnoApproval) {
		this.lstAVPHsnoApproval = lstAVPHsnoApproval;
	}


	public List<AVPHsnoClassification> getLstAVPHsnoClassification() {
		return lstAVPHsnoClassification;
	}


	public void setLstAVPHsnoClassification(List<AVPHsnoClassification> lstAVPHsnoClassification) {
		this.lstAVPHsnoClassification = lstAVPHsnoClassification;
	}


	public List<AVPEnvironmental> getLstAVPEnvironmental() {
		return lstAVPEnvironmental;
	}


	public void setLstAVPEnvironmental(List<AVPEnvironmental> lstAVPEnvironmental) {
		this.lstAVPEnvironmental = lstAVPEnvironmental;
	}


	public Double getNet_Volume() {
		return Net_Volume;
	}


	public void setNet_Volume(Double net_Volume) {
		Net_Volume = net_Volume;
	}


	public String getPriority_Food() {
		return Priority_Food;
	}


	public void setPriority_Food(String priority_Food) {
		Priority_Food = priority_Food;
	}


	public String getTga_Warning_Statement() {
		return Tga_Warning_Statement;
	}


	public void setTga_Warning_Statement(String tga_Warning_Statement) {
		Tga_Warning_Statement = tga_Warning_Statement;
	}




}
