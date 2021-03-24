package com.ascention.validationWS.beans;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.ascention.validationWS.exportGS1.beans.AVPCustomsExcisableValue;
import com.ascention.validationWS.exportGS1.beans.AVPEnvironmental;
import com.ascention.validationWS.exportGS1.beans.AVPHsnoApproval;
import com.ascention.validationWS.exportGS1.beans.AVPHsnoClassification;
import com.ascention.validationWS.exportGS1.beans.AVPPallet;
import com.ascention.validationWS.exportGS1.beans.AllergenInformationModule;
import com.ascention.validationWS.exportGS1.beans.Battery_Type;
import com.ascention.validationWS.exportGS1.beans.Dates;
import com.ascention.validationWS.exportGS1.beans.Grow_Method;
import com.ascention.validationWS.exportGS1.beans.LeadTimes;
import com.ascention.validationWS.exportGS1.beans.Nutrient_Header;
import com.ascention.validationWS.exportGS1.beans.Nutrient_Information;
import com.ascention.validationWS.exportGS1.beans.Nutrient_Serving_Size;
import com.ascention.validationWS.exportGS1.beans.Nutritional_Claim;
import com.ascention.validationWS.exportGS1.beans.Nutritional_Claim_Detail;
import com.ascention.validationWS.exportGS1.beans.AVP;
import com.ascention.validationWS.exportGS1.beans.Packaging;
import com.ascention.validationWS.exportGS1.beans.Packaging_Date;
import com.ascention.validationWS.exportGS1.beans.Packaging_Deposit;
import com.ascention.validationWS.exportGS1.beans.Packaging_Function;
import com.ascention.validationWS.exportGS1.beans.Packaging_Marking;
import com.ascention.validationWS.exportGS1.beans.Packaging_Material;
import com.ascention.validationWS.exportGS1.beans.Packaging_Recycle;
import com.ascention.validationWS.exportGS1.beans.Packaging_Recycle_Scheme;
import com.ascention.validationWS.exportGS1.beans.Packaging_Returnable;
import com.ascention.validationWS.exportGS1.beans.Packaging_Sustainability;
import com.ascention.validationWS.exportGS1.beans.ProductCharacteristicsModule;
import com.ascention.validationWS.exportGS1.beans.ReferencedFileHeader;
import com.ascention.validationWS.exportGS1.beans.Serving_Size_Desc;
import com.ascention.validationWS.exportGS1.beans.TradeItemDescriptionInformation;
import com.ascention.validationWS.exportGS1.beans.TradeItemHierarchyQuantity;
import com.ascention.validationWS.exportGS1.beans.TradeItemMeasurements;
import com.ascention.validationWS.exportGS1.beans.TradeItemNetContent;
import com.ascention.validationWS.exportGS1.beans.TradeItemPegMeasurements;
import com.ascention.validationWS.exportGS1.beans.TradeItemTemperatureInformation;
import com.ascention.validationWS.exportGS1.beans.TradeItemVariant_Desc;

public class ProductCore {
	private List<GPCAttributes> lstGPCAttributes;
	private List<GTINCountryOrigin> lstGTINCountryOrigin;
	private List<OrderQty> lstOrderQty;
	private List<Duty> lstDuty;
	private List<PalletAttributes> lstPalletAttributes;
	private List<UNSPSCAttributes> lstUNSPSCAttributes;
	private List<AlcoholPercentage> lstAlcoholPercentage;
	private List<Packaging> lstPackaging;
	private List<Packaging_Material> lstPackagingMaterial;
	private List<Packaging_Function> lstPackagingFunction;
	private List<Packaging_Deposit> lstPackaging_Deposit;
	private List<Packaging_Recycle> lstPackaging_Recycle;
	private List<Packaging_Recycle_Scheme> lstPackaging_Recycle_Scheme;
	private List<Packaging_Sustainability> lstPackaging_Sustainability ;
	private List<Battery_Type> lstBattery_Type;
	
	private String Is_Country_In_CodeList;
	
	private AVP AVP;
	private AVPPallet aVPPallet;
	private List<AVPCustomsExcisableValue> lstAVPCustomsExcisableValue;
	private List<AVPHsnoApproval> lstAVPHsnoApproval;
	private List<AVPHsnoClassification> lstAVPHsnoClassification;
	private List<AVPEnvironmental> lstAVPEnvironmental;
	
	private List<TradeItemMeasurements> lstTradeItemMeasurements;
	private List<TradeItemPegMeasurements> lstTradeItemPegMeasurements;
	private List<TradeItemNetContent> lstTradeItemNetContent;
	
	
	private List<TradeItemDescriptionInformation> lstTradeItemDescriptionInformation;
	private List<TradeItemVariant_Desc> lstTradeItemVariant_Desc;

	private List<Dates> lstDates;
	
	private List<TradeItemHierarchyQuantity> lstTradeItemHierarchyQuantity;

	
	private List<Packaging_Marking> lstPackaging_Marking;
	private List<Packaging_Returnable> lstPackaging_Returnable;
	private List<Packaging_Date> lstPackaging_Date;
	
	private List<TradeItemTemperatureInformation> lstTradeItemTemperatureInformation;
	private List<LeadTimes> lstLeadTimes;

	
	private List<Nutritional_Claim> lstNutritional_Claim;
	private List<Nutritional_Claim_Detail> lstNutritional_Claim_Detail;
	private List<Serving_Size_Desc> lstServing_Size_Desc;
	private List<Nutrient_Header> lstNutrient_Header;	
	private List<Nutrient_Serving_Size> lstNutrient_Serving_Size;
	private List<Nutrient_Information> lstNutrient_Information;
	
	private List<AllergenInformationModule> lstAllergenInformationModule;
	
	private List<Grow_Method> lstGrowMethod;
	
	private List<ProductCharacteristicsModule> lstProductCharacteristicsModule;
	
	private List<ReferencedFileHeader> lstReferencedFileHeader;

	
	private Integer Product_Id;
	private Integer Product_Hierarchy_Id;
	private String Brand_Name;
	private String Sub_Brand_Name;
	private String Functional_Name;
	private String Short_Desc;
	private String Addl_Prod_Desc;
	private String Full_Desc;
	private String Is_Service;
	private String Is_Variable_Unit;
	private String Is_Non_Physical;
	private String Batch_Num_Reqd;
	private String Order_Uom;
	private String Selling_Uom;
	private Integer Min_Life_Arrival;
	private Integer Min_Life_Production;
	private String Gm_Declaration_Code;
	private String Irradiated_Code;
	private String Gpc_Segment;
	private String Gpc_Family;
	private String Gpc_Class;
	private String Gpc_Brick;
	private String Prod_Group_Code;
	private String Prod_Group_Code_Desc;
	private String Final_Batch_Expiry_Date;
	private String Is_Recalled;
	private String Feature_Code_Ref;
	private String Warning_Copy_Desc;
	private String Transportation_Type;
	private String Dg_Class;
	private String Dg_Subsidiary_Class;
	private String Dg_Hazardous_Code;
	private String Dg_Packing_Group;
	private String Dg_Regulation_Code;
	private String Dg_Shipping_Name;
	private String Dg_Technical_Name;
	private String Flash_Point_Temp;
	private String Flash_Point_Temp_Uom;
	private String Flash_Point_Type;
	private String Hazardous_Identifier;
	private String Dangerous_Good;
	private String Hazardous_Good;
	private String Sds_Issue_Date;
	private String Un_Dg_Num;
	private String Cds_Mat_Type;
	private String Chem_Regl_Agency;
	private String Chem_Regl_Name;
	private String Chem_Phys_State;
	private String Consumer_Storage_Instr;
	private String Consumer_Usage_Instr;
	private String Target_Consumer_Gender;
	private String Prod_Marketing_Msg;
	private String Colour_Code;
	private String Colour_Code_Provider;
	private String Colour_Desc;
	private String Descriptive_Size;
	private String Size_Code_List;
	private String Size_Code_List_Value;
	private String Has_Safety_Warning;
	private String Import_Classification_Type;
	private String Import_Classification_Value;
	private String Non_Food_Ingredient_Statement;
	private String Labelling_Claims;
	private String Consumer_Recommended_Use;
	private String Suitable_For;
	private String Batteries_Included;
	private String Target_Consumer_Age_Group_Code;
	private String Custom_Stats_Code;
	private String Label_Desc;
	private String Prod_Keyword;
	private String Is_Repairable;
	private String Ce_Code;
	private String Ce_Type;
	private String Alcohol_Strength;
	private String Champagne_Ind;
	private Integer Liquor_Age;
	private String Liquor_Mkt_Segment;
	private Float Std_Drinks;
	private String Provenance_Statement;
	private String Sweetness_Level_Ind;
	private Integer Vintage;
	private String Wooded_Code;
	private String Ingr_Statement;
	private String Open_Prod_Lifespan;
	private String Shelf_Life_After_Thaw;
	private String Servings_Per_Pack;
	private String Gram_Measure_Dec;
	private String Total_Energy_Dec;
	private String Health_Star_Rating;
	private String Interpretive_Term_Opt_Nutrient;
	private String Interpretive_Term_Sat_Fat;
	private String Interpretive_Term_Sodium;
	private String Interpretive_Term_Sugar;
	private String Optional_Nutrient_Code;
	private String Diet_Type_Desc;
	private String Labelling_Logos;
	private String Labelling_Country_Origin;
	private String Labelling_Ingr_Statement;
	private String Labelling_Packed_Statement;
	private Float Labelling_Aus_Content_Pct;
	private String Labelling_Prod_Name;
	private String Labelling_Addtl_Phrase;
	private String Labelling_Addtl_Dtls;
	private String Packaging_Label_Accreditation;
	private String Prod_Activity_Type;
	private String Prod_Activity_Region_Desc;
	private String Serving_Size_Desc;
	private String Prod_Unit_Desc;
	private String Is_Base_Unit;
	private String Is_Consumer_Unit;
	private String Is_Despatch_Unit;
	private String Is_Invoice_Unit;
	private String Is_Orderable_Unit;
	private String Is_Non_Sold_Prod_Returnable;
	private Integer Base_Unit_Prod_Id;
	private Integer Base_Unit_Qty;
	private Integer Child_Unit_Prod_Id;
	private Integer Child_Unit_Qty;
	private String Child_Layer_Cnt;
	private Integer Child_Layer_Unit_Depth;
	private Integer Child_Layer_Unit_Width;
	private Integer Manufacturer_Id;
	private String Cntry_Origin_Statement;
	private String Prod_Finish_Desc;
	private String Season_Name;
	private String Seasonal_Availability_Start_Date;
	private String Seasonal_Availability_End_Date;
	private Float Depth;
	private String Depth_Uom;
	private BigDecimal Height;
	private String Height_Uom;
	private Float Width;
	private String Width_Uom;
	private Float Net_Weight;
	private String Net_Weight_Uom;
	private Float Gross_Weight;
	private String Gross_Weight_Uom;
	private Float Drained_Weight;
	private String Drained_Weight_Uom;
	private Float Net_Volume;
	private String Net_Volume_Uom;
	private String Trade_Measurement_Method;
	private Float Dec_Weight_Vol;
	private String Dec_Weight_Vol_Uom;
	private Float In_Box_Cube_Dim;
	private String In_Box_Cube_Dim_Uom;
	private Float Net_Base_Volume;
	private String Net_Base_Volume_Uom;
	private Float Gross_Volume;
	private String Gross_Volume_Uom;
	private String Community_Visibility_Date;
	private String Display_Ready_Packaging;
	private String Packaging_Marked_Returnable;
	private String Is_Price_On_Pack;
	private String Marked_As_Recyclable;
	private String Warning_On_Package;
	private String Nesting;
	private String Packaging_Marked_Recyclable_Scheme;
	private String Packaging_Type_Code;
	private String Packaging_Type_Desc;
	private String Platform_Type;
	private String Security_Tag_Loc;
	private String Security_Tag_Type;
	private String Shipping_Container_Qty_Desc;
	private String Handling_Instr_Ref;
	private String Hazard_Pack_Type;
	private String Hazardous_Packaging_Type;
	private String Hazardous_Unit_Vol;
	private String Hazardous_Unit_Vol_Uom;
	private String Hazardous_Unit_Size;
	private String Hazardous_Unit_Size_Uom;
	private String Promotional_Product;
	private String Price_Comparison_Content_Type;
	private String Price_Comparison_Measurement;
	private String Price_Comparison_Measurement_Uom;
	private String Mfr_Internal_Ref;
	private String Front_Face_Type;
	private String RfId_On_Packaging;

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

	
	public boolean hasNutrientInfoForEachHeader(List<Nutrient_Header> lstNutrient_Header,List<Nutrient_Serving_Size> lstNutrient_Serving_Size, List<Nutrient_Information> lstNutrient_Information  ) {
		
		boolean foundAllNutrientInfoIdsInServingSize = false;
		boolean foundAllNutrientInfoIdsInNutrientInfo = false;
		
		
		for (Nutrient_Header eleNutrient_Header:lstNutrient_Header) {
			foundAllNutrientInfoIdsInServingSize = false;	
			foundAllNutrientInfoIdsInNutrientInfo = false;
			String Nutrient_Info_Id = eleNutrient_Header.getNutrient_Info_Id();
			for (Nutrient_Serving_Size eleNutrient_Serving_Size: lstNutrient_Serving_Size) {
				if(eleNutrient_Serving_Size.getNutrient_Info_Id().equalsIgnoreCase(Nutrient_Info_Id))
					foundAllNutrientInfoIdsInServingSize = true;
			}
			
			for (Nutrient_Information eleNutrient_Information:lstNutrient_Information) {
				if(eleNutrient_Information.getNutrient_Info_Id().equalsIgnoreCase(Nutrient_Info_Id)) 
					foundAllNutrientInfoIdsInNutrientInfo = true;
			}
			if(!(foundAllNutrientInfoIdsInServingSize && foundAllNutrientInfoIdsInNutrientInfo)) {
				return false;
			}
		}
	
		return true;
	}
	
	public boolean hasDuplicateTradingPartnerAssigned(List<GTINCountryOrigin> lstGTINCountryOrigin) {
		int ctr = 0;
		for (GTINCountryOrigin eleGTINCountryOrigin : lstGTINCountryOrigin) {
			if(eleGTINCountryOrigin.getAddl_Prod_Num_Type()!=null) {
				if(eleGTINCountryOrigin.getAddl_Prod_Num_Type().equalsIgnoreCase("TRADING_PARTNER_ASSIGNED")) {
					ctr = ctr + 1;
				}	
			}
			
		}
		
		if(ctr>1) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean hasNetContentInTradeItemDescription(List<TradeItemNetContent> lstTradeItemNetContent, List<TradeItemDescriptionInformation> lstTradeItemDescriptionInformation) {
		
		String Net_Content = "";
		for(TradeItemNetContent eleTradeItemNetContent : lstTradeItemNetContent) {
			if(eleTradeItemNetContent.getNet_Content()!=null)
				Net_Content = String.valueOf(Math.round(eleTradeItemNetContent.getNet_Content()));
			
			
			if(!Net_Content.equalsIgnoreCase("") && !Net_Content.equalsIgnoreCase("1")) {
				for (TradeItemDescriptionInformation eleTradeItemDescriptionInformation : lstTradeItemDescriptionInformation ) {
					if(!eleTradeItemDescriptionInformation.getTradeItemDescription().contains(Net_Content)) {
						return false;
					}
				}
			}
			
		}
		
		return true;
		
		
	}
	
public boolean hasAllNutrientTypesInNutrientInformationForNutrientHeader(List<Nutrient_Header> lstNutrient_Header, List<Nutrient_Information> lstNutrient_Information, String strValuesToCheck  ) {
		
		
		String[] strArrayValuesToCheck = strValuesToCheck.split(",");
		List<String> expected = new ArrayList<String>();
		List<String> actual = new ArrayList<String>();
		expected = Arrays.asList(strArrayValuesToCheck);
		for (Nutrient_Header eleNutrient_Header:lstNutrient_Header) {
		
			String Nutrient_Info_Id = eleNutrient_Header.getNutrient_Info_Id();
			actual = new ArrayList<String>();
			for (Nutrient_Information eleNutrient_Information:lstNutrient_Information) {
				if(eleNutrient_Information.getNutrient_Info_Id().equals(Nutrient_Info_Id)){
					actual.add(eleNutrient_Information.getNutrient_Type());
				}
			}
			
			if(!actual.containsAll(expected))
				return false;
		}
	
		return true;
	}

public boolean isNutrientTypeDuplicatedInAListForEachNutrientHeader(List<Nutrient_Header> lstNutrient_Header, List<Nutrient_Information> lstNutrient_Information){
	

	for(Nutrient_Header eleNutrient_Header : lstNutrient_Header ) {

		List<String> actual = new ArrayList<String>();

		try {
			String Nutrient_Info_Id = eleNutrient_Header.getNutrient_Info_Id();
			actual = new ArrayList<String>();
			for (Nutrient_Information eleNutrient_Information:lstNutrient_Information) {
				if(eleNutrient_Information.getNutrient_Info_Id().equals(Nutrient_Info_Id)){
					actual.add(eleNutrient_Information.getNutrient_Type());
				}
			}
			Set<String> setUniqueValues = new HashSet<String>(actual);

			if(actual.size() > setUniqueValues.size()) {
				return true;
			}
			
			
		} catch (Exception e) {
	
			e.printStackTrace();
		}
	}
	
	return false;
		
}


	public boolean isValueDuplicatedInAList(List<Object> lstToValidate, String classname,
			String fieldname) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> c;
		Method method;
		List<String> actual = new ArrayList<String>();

		try {
			try {
				c = Class.forName("com.ascention.validationWS.beans." + classname);
			} catch (Exception e) {
				c = Class.forName("com.ascention.validationWS.exportGS1.beans." + classname);	 
			}
			method = c.getDeclaredMethod("get" + fieldname);
			
			for (Object ele : lstToValidate) {				
				
				actual.add((String) method.invoke(ele));
			}
			
			Set<String> setUniqueValues = new HashSet<String>(actual);

			if(actual.size() > setUniqueValues.size()) {
				return true;
			}
			
			
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		return false;
	
	}
	
	public Long dateComparison(String dateToCompare, String dateCompareWith) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		try {
			if(dateCompareWith.equalsIgnoreCase("TODAY")) {
				Date currentDate = new Date();
				dateCompareWith = formatter.format(currentDate);
				Date date = formatter.parse(dateToCompare);
				long diff = currentDate.getTime() - date.getTime();
				//System.out.println("Date Difference Is ::>" + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
				return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			} 
			Date date = formatter.parse(dateToCompare);
			Date dateToCompareWith = formatter.parse(dateCompareWith);			
			long diff = dateToCompareWith.getTime() - date.getTime();
			//System.out.println("Date Difference Is ::>" + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
			return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);			
		} catch (ParseException e) {
			e.printStackTrace();
			return 99999L;
		}
		
	}
	
	public boolean hasAllGPCAttributesElements(List<GPCAttributes> lstToValidate, String strAllValuesToBePresent) {
		//System.out.println("Called ProductCore::hasAllElements:" + strAllValuesToBePresent);
		
		String str[] = strAllValuesToBePresent.split(",");
		List<String> expected = new ArrayList<String>();
		List<String> actual = new ArrayList<String>();
		expected = Arrays.asList(str);
		
		for (GPCAttributes ele : lstToValidate) {
			actual.add(ele.getGpc_Attr_Type_Code());
		}
		
		//System.out.println("Comparing Expected::>" + expected.toString() + " Actual::>" +  actual.toString());
		
		//System.out.println("Returning " + actual.containsAll(expected));
		
		return actual.containsAll(expected);
		
	}
	
	public boolean hasAllElementsInList(List<Object> lstToValidate, String strAllValuesToBePresent, String classname,
			String fieldname) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//System.out.println("Called ProductCore::hasAllElements:" + strAllValuesToBePresent);
		List<String> expected = new ArrayList<String>();
		List<String> actual = new ArrayList<String>();
		Class<?> c;
		Method method;
		try {
			try {
				c = Class.forName("com.ascention.validationWS.beans." + classname);
			} catch (Exception e) {
				c = Class.forName("com.ascention.validationWS.exportGS1.beans." + classname);	 
			}
			method = c.getDeclaredMethod("get" + fieldname);
			String str[] = strAllValuesToBePresent.split(",");
			expected = Arrays.asList(str);
			for (Object ele : lstToValidate) {				
				actual.add((String) method.invoke(ele));
			}
//			System.out.println("Comparing Expected::>" + expected.toString() + " Actual::>" + actual.toString());
//			System.out.println("Returning " + actual.containsAll(expected));
		} catch (Exception e) {

			e.printStackTrace();
		}
		return actual.containsAll(expected);

	}

	public boolean hasKeyValueInList(List<Object> lstToValidate,String classname, String keyField, String valueField, String keyToCompare, String valueToCompare) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//System.out.println("Called ProductCore::hasKeyValueInList");
		Class<?> c;
		Method keyMethod ;
		Method valueMethod ;
		try {
			try {
				c = Class.forName("com.ascention.validationWS.beans." + classname);
			} catch (Exception e) {
				c = Class.forName("com.ascention.validationWS.exportGS1.beans." + classname);	 
			}
			keyMethod = c.getDeclaredMethod("get" + keyField);
			valueMethod = c.getDeclaredMethod("get" + valueField);
			for (Object ele : lstToValidate) {				
				if ( keyMethod.invoke(ele).equals(keyToCompare) &&  valueMethod.invoke(ele).equals(valueToCompare)) {
					return true;
				}
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;

	}

	public boolean hasValueInList(List<Object> lstToValidate,String classname, String valueField, String valueToCompare) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//System.out.println("Called ProductCore::hasValueInList");
		Class<?> c;
		
		Method valueMethod ;
		try {
			try {
				c = Class.forName("com.ascention.validationWS.beans." + classname);
			} catch (Exception e) {
				c = Class.forName("com.ascention.validationWS.exportGS1.beans." + classname);	 
			}
			valueMethod = c.getDeclaredMethod("get" + valueField);
			for (Object ele : lstToValidate) {				
				if ( valueMethod.invoke(ele).equals(valueToCompare)) {
					return true;
				}
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;

	}

	
	
	public List<GPCAttributes> getLstGPCAttributes() {
		return lstGPCAttributes;
	}


	public void setLstGPCAttributes(List<GPCAttributes> lstGPCAttributes) {
		this.lstGPCAttributes = lstGPCAttributes;
	}


	public List<GTINCountryOrigin> getLstGTINCountryOrigin() {
		return lstGTINCountryOrigin;
	}


	public void setLstGTINCountryOrigin(List<GTINCountryOrigin> lstGTINCountryOrigin) {
		this.lstGTINCountryOrigin = lstGTINCountryOrigin;
	}


	public List<OrderQty> getLstOrderQty() {
		return lstOrderQty;
	}


	public void setLstOrderQty(List<OrderQty> lstOrderQty) {
		this.lstOrderQty = lstOrderQty;
	}


	public List<Duty> getLstDuty() {
		return lstDuty;
	}


	public void setLstDuty(List<Duty> lstDuty) {
		this.lstDuty = lstDuty;
	}


	public List<PalletAttributes> getLstPalletAttributes() {
		return lstPalletAttributes;
	}


	public void setLstPalletAttributes(List<PalletAttributes> lstPalletAttributes) {
		this.lstPalletAttributes = lstPalletAttributes;
	}


	public List<UNSPSCAttributes> getLstUNSPSCAttributes() {
		return lstUNSPSCAttributes;
	}


	public void setLstUNSPSCAttributes(List<UNSPSCAttributes> lstUNSPSCAttributes) {
		this.lstUNSPSCAttributes = lstUNSPSCAttributes;
	}


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


	public String getBrand_Name() {
		return Brand_Name;
	}


	public void setBrand_Name(String brand_Name) {
		Brand_Name = brand_Name;
	}


	public String getSub_Brand_Name() {
		return Sub_Brand_Name;
	}


	public void setSub_Brand_Name(String sub_Brand_Name) {
		Sub_Brand_Name = sub_Brand_Name;
	}


	public String getFunctional_Name() {
		return Functional_Name;
	}


	public void setFunctional_Name(String functional_Name) {
		Functional_Name = functional_Name;
	}


	public String getShort_Desc() {
		return Short_Desc;
	}


	public void setShort_Desc(String short_Desc) {
		Short_Desc = short_Desc;
	}


	public String getAddl_Prod_Desc() {
		return Addl_Prod_Desc;
	}


	public void setAddl_Prod_Desc(String addl_Prod_Desc) {
		Addl_Prod_Desc = addl_Prod_Desc;
	}


	public String getFull_Desc() {
		return Full_Desc;
	}


	public void setFull_Desc(String full_Desc) {
		Full_Desc = full_Desc;
	}


	public String getIs_Service() {
		return Is_Service;
	}


	public void setIs_Service(String is_Service) {
		Is_Service = is_Service;
	}


	public String getIs_Variable_Unit() {
		return Is_Variable_Unit;
	}


	public void setIs_Variable_Unit(String is_Variable_Unit) {
		Is_Variable_Unit = is_Variable_Unit;
	}


	public String getIs_Non_Physical() {
		return Is_Non_Physical;
	}


	public void setIs_Non_Physical(String is_Non_Physical) {
		Is_Non_Physical = is_Non_Physical;
	}


	public String getBatch_Num_Reqd() {
		return Batch_Num_Reqd;
	}


	public void setBatch_Num_Reqd(String batch_Num_Reqd) {
		Batch_Num_Reqd = batch_Num_Reqd;
	}


	public String getOrder_Uom() {
		return Order_Uom;
	}


	public void setOrder_Uom(String order_Uom) {
		Order_Uom = order_Uom;
	}


	public String getSelling_Uom() {
		return Selling_Uom;
	}


	public void setSelling_Uom(String selling_Uom) {
		Selling_Uom = selling_Uom;
	}


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


	public String getGm_Declaration_Code() {
		return Gm_Declaration_Code;
	}


	public void setGm_Declaration_Code(String gm_Declaration_Code) {
		Gm_Declaration_Code = gm_Declaration_Code;
	}


	public String getIrradiated_Code() {
		return Irradiated_Code;
	}


	public void setIrradiated_Code(String irradiated_Code) {
		Irradiated_Code = irradiated_Code;
	}


	public String getGpc_Segment() {
		return Gpc_Segment;
	}


	public void setGpc_Segment(String gpc_Segment) {
		Gpc_Segment = gpc_Segment;
	}


	public String getGpc_Family() {
		return Gpc_Family;
	}


	public void setGpc_Family(String gpc_Family) {
		Gpc_Family = gpc_Family;
	}


	public String getGpc_Class() {
		return Gpc_Class;
	}


	public void setGpc_Class(String gpc_Class) {
		Gpc_Class = gpc_Class;
	}


	public String getGpc_Brick() {
		return Gpc_Brick;
	}


	public void setGpc_Brick(String gpc_Brick) {
		Gpc_Brick = gpc_Brick;
	}


	public String getProd_Group_Code() {
		return Prod_Group_Code;
	}


	public void setProd_Group_Code(String prod_Group_Code) {
		Prod_Group_Code = prod_Group_Code;
	}


	public String getProd_Group_Code_Desc() {
		return Prod_Group_Code_Desc;
	}


	public void setProd_Group_Code_Desc(String prod_Group_Code_Desc) {
		Prod_Group_Code_Desc = prod_Group_Code_Desc;
	}


	public String getFinal_Batch_Expiry_Date() {
		return Final_Batch_Expiry_Date;
	}


	public void setFinal_Batch_Expiry_Date(String final_Batch_Expiry_Date) {
		Final_Batch_Expiry_Date = final_Batch_Expiry_Date;
	}


	public String getIs_Recalled() {
		return Is_Recalled;
	}


	public void setIs_Recalled(String is_Recalled) {
		Is_Recalled = is_Recalled;
	}


	public String getFeature_Code_Ref() {
		return Feature_Code_Ref;
	}


	public void setFeature_Code_Ref(String feature_Code_Ref) {
		Feature_Code_Ref = feature_Code_Ref;
	}


	public String getWarning_Copy_Desc() {
		return Warning_Copy_Desc;
	}


	public void setWarning_Copy_Desc(String warning_Copy_Desc) {
		Warning_Copy_Desc = warning_Copy_Desc;
	}


	public String getTransportation_Type() {
		return Transportation_Type;
	}


	public void setTransportation_Type(String transportation_Type) {
		Transportation_Type = transportation_Type;
	}


	public String getDg_Class() {
		return Dg_Class;
	}


	public void setDg_Class(String dg_Class) {
		Dg_Class = dg_Class;
	}


	public String getDg_Subsidiary_Class() {
		return Dg_Subsidiary_Class;
	}


	public void setDg_Subsidiary_Class(String dg_Subsidiary_Class) {
		Dg_Subsidiary_Class = dg_Subsidiary_Class;
	}


	public String getDg_Hazardous_Code() {
		return Dg_Hazardous_Code;
	}


	public void setDg_Hazardous_Code(String dg_Hazardous_Code) {
		Dg_Hazardous_Code = dg_Hazardous_Code;
	}


	public String getDg_Packing_Group() {
		return Dg_Packing_Group;
	}


	public void setDg_Packing_Group(String dg_Packing_Group) {
		Dg_Packing_Group = dg_Packing_Group;
	}


	public String getDg_Regulation_Code() {
		return Dg_Regulation_Code;
	}


	public void setDg_Regulation_Code(String dg_Regulation_Code) {
		Dg_Regulation_Code = dg_Regulation_Code;
	}


	public String getDg_Shipping_Name() {
		return Dg_Shipping_Name;
	}


	public void setDg_Shipping_Name(String dg_Shipping_Name) {
		Dg_Shipping_Name = dg_Shipping_Name;
	}


	public String getDg_Technical_Name() {
		return Dg_Technical_Name;
	}


	public void setDg_Technical_Name(String dg_Technical_Name) {
		Dg_Technical_Name = dg_Technical_Name;
	}


	public String getFlash_Point_Temp() {
		return Flash_Point_Temp;
	}


	public void setFlash_Point_Temp(String flash_Point_Temp) {
		Flash_Point_Temp = flash_Point_Temp;
	}


	public String getFlash_Point_Temp_Uom() {
		return Flash_Point_Temp_Uom;
	}


	public void setFlash_Point_Temp_Uom(String flash_Point_Temp_Uom) {
		Flash_Point_Temp_Uom = flash_Point_Temp_Uom;
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


	public String getUn_Dg_Num() {
		return Un_Dg_Num;
	}


	public void setUn_Dg_Num(String un_Dg_Num) {
		Un_Dg_Num = un_Dg_Num;
	}


	public String getCds_Mat_Type() {
		return Cds_Mat_Type;
	}


	public void setCds_Mat_Type(String cds_Mat_Type) {
		Cds_Mat_Type = cds_Mat_Type;
	}


	public String getChem_Regl_Agency() {
		return Chem_Regl_Agency;
	}


	public void setChem_Regl_Agency(String chem_Regl_Agency) {
		Chem_Regl_Agency = chem_Regl_Agency;
	}


	public String getChem_Regl_Name() {
		return Chem_Regl_Name;
	}


	public void setChem_Regl_Name(String chem_Regl_Name) {
		Chem_Regl_Name = chem_Regl_Name;
	}


	public String getChem_Phys_State() {
		return Chem_Phys_State;
	}


	public void setChem_Phys_State(String chem_Phys_State) {
		Chem_Phys_State = chem_Phys_State;
	}


	public String getConsumer_Storage_Instr() {
		return Consumer_Storage_Instr;
	}


	public void setConsumer_Storage_Instr(String consumer_Storage_Instr) {
		Consumer_Storage_Instr = consumer_Storage_Instr;
	}


	public String getConsumer_Usage_Instr() {
		return Consumer_Usage_Instr;
	}


	public void setConsumer_Usage_Instr(String consumer_Usage_Instr) {
		Consumer_Usage_Instr = consumer_Usage_Instr;
	}


	public String getTarget_Consumer_Gender() {
		return Target_Consumer_Gender;
	}


	public void setTarget_Consumer_Gender(String target_Consumer_Gender) {
		Target_Consumer_Gender = target_Consumer_Gender;
	}


	public String getProd_Marketing_Msg() {
		return Prod_Marketing_Msg;
	}


	public void setProd_Marketing_Msg(String prod_Marketing_Msg) {
		Prod_Marketing_Msg = prod_Marketing_Msg;
	}


	public String getColour_Code() {
		return Colour_Code;
	}


	public void setColour_Code(String colour_Code) {
		Colour_Code = colour_Code;
	}


	public String getColour_Code_Provider() {
		return Colour_Code_Provider;
	}


	public void setColour_Code_Provider(String colour_Code_Provider) {
		Colour_Code_Provider = colour_Code_Provider;
	}


	public String getColour_Desc() {
		return Colour_Desc;
	}


	public void setColour_Desc(String colour_Desc) {
		Colour_Desc = colour_Desc;
	}


	public String getDescriptive_Size() {
		return Descriptive_Size;
	}


	public void setDescriptive_Size(String descriptive_Size) {
		Descriptive_Size = descriptive_Size;
	}


	public String getSize_Code_List() {
		return Size_Code_List;
	}


	public void setSize_Code_List(String size_Code_List) {
		Size_Code_List = size_Code_List;
	}


	public String getSize_Code_List_Value() {
		return Size_Code_List_Value;
	}


	public void setSize_Code_List_Value(String size_Code_List_Value) {
		Size_Code_List_Value = size_Code_List_Value;
	}


	public String getHas_Safety_Warning() {
		return Has_Safety_Warning;
	}


	public void setHas_Safety_Warning(String has_Safety_Warning) {
		Has_Safety_Warning = has_Safety_Warning;
	}


	public String getImport_Classification_Type() {
		return Import_Classification_Type;
	}


	public void setImport_Classification_Type(String import_Classification_Type) {
		Import_Classification_Type = import_Classification_Type;
	}


	public String getImport_Classification_Value() {
		return Import_Classification_Value;
	}


	public void setImport_Classification_Value(String import_Classification_Value) {
		Import_Classification_Value = import_Classification_Value;
	}


	public String getNon_Food_Ingredient_Statement() {
		return Non_Food_Ingredient_Statement;
	}


	public void setNon_Food_Ingredient_Statement(String non_Food_Ingredient_Statement) {
		Non_Food_Ingredient_Statement = non_Food_Ingredient_Statement;
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


	public String getBatteries_Included() {
		return Batteries_Included;
	}


	public void setBatteries_Included(String batteries_Included) {
		Batteries_Included = batteries_Included;
	}


	public String getTarget_Consumer_Age_Group_Code() {
		return Target_Consumer_Age_Group_Code;
	}


	public void setTarget_Consumer_Age_Group_Code(String target_Consumer_Age_Group_Code) {
		Target_Consumer_Age_Group_Code = target_Consumer_Age_Group_Code;
	}


	public String getCustom_Stats_Code() {
		return Custom_Stats_Code;
	}


	public void setCustom_Stats_Code(String custom_Stats_Code) {
		Custom_Stats_Code = custom_Stats_Code;
	}


	public String getLabel_Desc() {
		return Label_Desc;
	}


	public void setLabel_Desc(String label_Desc) {
		Label_Desc = label_Desc;
	}


	public String getProd_Keyword() {
		return Prod_Keyword;
	}


	public void setProd_Keyword(String prod_Keyword) {
		Prod_Keyword = prod_Keyword;
	}


	public String getIs_Repairable() {
		return Is_Repairable;
	}


	public void setIs_Repairable(String is_Repairable) {
		Is_Repairable = is_Repairable;
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


	


	public String getLiquor_Mkt_Segment() {
		return Liquor_Mkt_Segment;
	}


	public void setLiquor_Mkt_Segment(String liquor_Mkt_Segment) {
		Liquor_Mkt_Segment = liquor_Mkt_Segment;
	}


	


	public String getProvenance_Statement() {
		return Provenance_Statement;
	}


	public void setProvenance_Statement(String provenance_Statement) {
		Provenance_Statement = provenance_Statement;
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


	public String getIngr_Statement() {
		return Ingr_Statement;
	}


	public void setIngr_Statement(String ingr_Statement) {
		Ingr_Statement = ingr_Statement;
	}


	public String getOpen_Prod_Lifespan() {
		return Open_Prod_Lifespan;
	}


	public void setOpen_Prod_Lifespan(String open_Prod_Lifespan) {
		Open_Prod_Lifespan = open_Prod_Lifespan;
	}


	public String getShelf_Life_After_Thaw() {
		return Shelf_Life_After_Thaw;
	}


	public void setShelf_Life_After_Thaw(String shelf_Life_After_Thaw) {
		Shelf_Life_After_Thaw = shelf_Life_After_Thaw;
	}


	public String getServings_Per_Pack() {
		return Servings_Per_Pack;
	}


	public void setServings_Per_Pack(String servings_Per_Pack) {
		Servings_Per_Pack = servings_Per_Pack;
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


	public String getDiet_Type_Desc() {
		return Diet_Type_Desc;
	}


	public void setDiet_Type_Desc(String diet_Type_Desc) {
		Diet_Type_Desc = diet_Type_Desc;
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


	public String getPackaging_Label_Accreditation() {
		return Packaging_Label_Accreditation;
	}


	public void setPackaging_Label_Accreditation(String packaging_Label_Accreditation) {
		Packaging_Label_Accreditation = packaging_Label_Accreditation;
	}


	public String getProd_Activity_Type() {
		return Prod_Activity_Type;
	}


	public void setProd_Activity_Type(String prod_Activity_Type) {
		Prod_Activity_Type = prod_Activity_Type;
	}


	public String getProd_Activity_Region_Desc() {
		return Prod_Activity_Region_Desc;
	}


	public void setProd_Activity_Region_Desc(String prod_Activity_Region_Desc) {
		Prod_Activity_Region_Desc = prod_Activity_Region_Desc;
	}


	public String getServing_Size_Desc() {
		return Serving_Size_Desc;
	}


	public void setServing_Size_Desc(String serving_Size_Desc) {
		Serving_Size_Desc = serving_Size_Desc;
	}


	public String getProd_Unit_Desc() {
		return Prod_Unit_Desc;
	}


	public void setProd_Unit_Desc(String prod_Unit_Desc) {
		Prod_Unit_Desc = prod_Unit_Desc;
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


	public String getIs_Non_Sold_Prod_Returnable() {
		return Is_Non_Sold_Prod_Returnable;
	}


	public void setIs_Non_Sold_Prod_Returnable(String is_Non_Sold_Prod_Returnable) {
		Is_Non_Sold_Prod_Returnable = is_Non_Sold_Prod_Returnable;
	}


	public Integer getBase_Unit_Prod_Id() {
		return Base_Unit_Prod_Id;
	}


	public void setBase_Unit_Prod_Id(Integer base_Unit_Prod_Id) {
		Base_Unit_Prod_Id = base_Unit_Prod_Id;
	}


	public Integer getBase_Unit_Qty() {
		return Base_Unit_Qty;
	}


	public void setBase_Unit_Qty(Integer base_Unit_Qty) {
		Base_Unit_Qty = base_Unit_Qty;
	}


	public Integer getChild_Unit_Prod_Id() {
		return Child_Unit_Prod_Id;
	}


	public void setChild_Unit_Prod_Id(Integer child_Unit_Prod_Id) {
		Child_Unit_Prod_Id = child_Unit_Prod_Id;
	}


	public Integer getChild_Unit_Qty() {
		return Child_Unit_Qty;
	}


	public void setChild_Unit_Qty(Integer child_Unit_Qty) {
		Child_Unit_Qty = child_Unit_Qty;
	}


	public String getChild_Layer_Cnt() {
		return Child_Layer_Cnt;
	}


	public void setChild_Layer_Cnt(String child_Layer_Cnt) {
		Child_Layer_Cnt = child_Layer_Cnt;
	}


	public Integer getChild_Layer_Unit_Depth() {
		return Child_Layer_Unit_Depth;
	}


	public void setChild_Layer_Unit_Depth(Integer child_Layer_Unit_Depth) {
		Child_Layer_Unit_Depth = child_Layer_Unit_Depth;
	}


	public Integer getChild_Layer_Unit_Width() {
		return Child_Layer_Unit_Width;
	}


	public void setChild_Layer_Unit_Width(Integer child_Layer_Unit_Width) {
		Child_Layer_Unit_Width = child_Layer_Unit_Width;
	}


	public Integer getManufacturer_Id() {
		return Manufacturer_Id;
	}


	public void setManufacturer_Id(Integer manufacturer_Id) {
		Manufacturer_Id = manufacturer_Id;
	}


	public String getCntry_Origin_Statement() {
		return Cntry_Origin_Statement;
	}


	public void setCntry_Origin_Statement(String cntry_Origin_Statement) {
		Cntry_Origin_Statement = cntry_Origin_Statement;
	}


	public String getProd_Finish_Desc() {
		return Prod_Finish_Desc;
	}


	public void setProd_Finish_Desc(String prod_Finish_Desc) {
		Prod_Finish_Desc = prod_Finish_Desc;
	}


	public String getSeason_Name() {
		return Season_Name;
	}


	public void setSeason_Name(String season_Name) {
		Season_Name = season_Name;
	}


	public String getSeasonal_Availability_Start_Date() {
		return Seasonal_Availability_Start_Date;
	}


	public void setSeasonal_Availability_Start_Date(String seasonal_Availability_Start_Date) {
		Seasonal_Availability_Start_Date = seasonal_Availability_Start_Date;
	}


	public String getSeasonal_Availability_End_Date() {
		return Seasonal_Availability_End_Date;
	}


	public void setSeasonal_Availability_End_Date(String seasonal_Availability_End_Date) {
		Seasonal_Availability_End_Date = seasonal_Availability_End_Date;
	}


	public Float getDepth() {
		return Depth;
	}


	public void setDepth(Float depth) {
		Depth = depth;
	}


	public String getDepth_Uom() {
		return Depth_Uom;
	}


	public void setDepth_Uom(String depth_Uom) {
		Depth_Uom = depth_Uom;
	}


	public BigDecimal getHeight() {
		return Height;
	}


	public void setHeight(BigDecimal height) {
		Height = height;
	}


	public String getHeight_Uom() {
		return Height_Uom;
	}


	public void setHeight_Uom(String height_Uom) {
		Height_Uom = height_Uom;
	}


	public Float getWidth() {
		return Width;
	}


	public void setWidth(Float width) {
		Width = width;
	}


	public String getWidth_Uom() {
		return Width_Uom;
	}


	public void setWidth_Uom(String width_Uom) {
		Width_Uom = width_Uom;
	}


	public Float getNet_Weight() {
		return Net_Weight;
	}


	public void setNet_Weight(Float net_Weight) {
		Net_Weight = net_Weight;
	}


	public String getNet_Weight_Uom() {
		return Net_Weight_Uom;
	}


	public void setNet_Weight_Uom(String net_Weight_Uom) {
		Net_Weight_Uom = net_Weight_Uom;
	}


	public Float getGross_Weight() {
		return Gross_Weight;
	}


	public void setGross_Weight(Float gross_Weight) {
		Gross_Weight = gross_Weight;
	}


	public String getGross_Weight_Uom() {
		return Gross_Weight_Uom;
	}


	public void setGross_Weight_Uom(String gross_Weight_Uom) {
		Gross_Weight_Uom = gross_Weight_Uom;
	}


	public Float getDrained_Weight() {
		return Drained_Weight;
	}


	public void setDrained_Weight(Float drained_Weight) {
		Drained_Weight = drained_Weight;
	}


	public String getDrained_Weight_Uom() {
		return Drained_Weight_Uom;
	}


	public void setDrained_Weight_Uom(String drained_Weight_Uom) {
		Drained_Weight_Uom = drained_Weight_Uom;
	}


	public Float getNet_Volume() {
		return Net_Volume;
	}


	public void setNet_Volume(Float net_Volume) {
		Net_Volume = net_Volume;
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


	public Float getDec_Weight_Vol() {
		return Dec_Weight_Vol;
	}


	public void setDec_Weight_Vol(Float dec_Weight_Vol) {
		Dec_Weight_Vol = dec_Weight_Vol;
	}


	public String getDec_Weight_Vol_Uom() {
		return Dec_Weight_Vol_Uom;
	}


	public void setDec_Weight_Vol_Uom(String dec_Weight_Vol_Uom) {
		Dec_Weight_Vol_Uom = dec_Weight_Vol_Uom;
	}


	public Float getIn_Box_Cube_Dim() {
		return In_Box_Cube_Dim;
	}


	public void setIn_Box_Cube_Dim(Float in_Box_Cube_Dim) {
		In_Box_Cube_Dim = in_Box_Cube_Dim;
	}


	public String getIn_Box_Cube_Dim_Uom() {
		return In_Box_Cube_Dim_Uom;
	}


	public void setIn_Box_Cube_Dim_Uom(String in_Box_Cube_Dim_Uom) {
		In_Box_Cube_Dim_Uom = in_Box_Cube_Dim_Uom;
	}


	public Float getNet_Base_Volume() {
		return Net_Base_Volume;
	}


	public void setNet_Base_Volume(Float net_Base_Volume) {
		Net_Base_Volume = net_Base_Volume;
	}


	public String getNet_Base_Volume_Uom() {
		return Net_Base_Volume_Uom;
	}


	public void setNet_Base_Volume_Uom(String net_Base_Volume_Uom) {
		Net_Base_Volume_Uom = net_Base_Volume_Uom;
	}


	public Float getGross_Volume() {
		return Gross_Volume;
	}


	public void setGross_Volume(Float gross_Volume) {
		Gross_Volume = gross_Volume;
	}


	public String getGross_Volume_Uom() {
		return Gross_Volume_Uom;
	}


	public void setGross_Volume_Uom(String gross_Volume_Uom) {
		Gross_Volume_Uom = gross_Volume_Uom;
	}


	public String getCommunity_Visibility_Date() {
		return Community_Visibility_Date;
	}


	public void setCommunity_Visibility_Date(String community_Visibility_Date) {
		Community_Visibility_Date = community_Visibility_Date;
	}


	public String getDisplay_Ready_Packaging() {
		return Display_Ready_Packaging;
	}


	public void setDisplay_Ready_Packaging(String display_Ready_Packaging) {
		Display_Ready_Packaging = display_Ready_Packaging;
	}


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


	public String getPackaging_Marked_Recyclable_Scheme() {
		return Packaging_Marked_Recyclable_Scheme;
	}


	public void setPackaging_Marked_Recyclable_Scheme(String packaging_Marked_Recyclable_Scheme) {
		Packaging_Marked_Recyclable_Scheme = packaging_Marked_Recyclable_Scheme;
	}


	public String getPackaging_Type_Code() {
		return Packaging_Type_Code;
	}


	public void setPackaging_Type_Code(String packaging_Type_Code) {
		Packaging_Type_Code = packaging_Type_Code;
	}


	public String getPackaging_Type_Desc() {
		return Packaging_Type_Desc;
	}


	public void setPackaging_Type_Desc(String packaging_Type_Desc) {
		Packaging_Type_Desc = packaging_Type_Desc;
	}


	public String getPlatform_Type() {
		return Platform_Type;
	}


	public void setPlatform_Type(String platform_Type) {
		Platform_Type = platform_Type;
	}


	public String getSecurity_Tag_Loc() {
		return Security_Tag_Loc;
	}


	public void setSecurity_Tag_Loc(String security_Tag_Loc) {
		Security_Tag_Loc = security_Tag_Loc;
	}


	public String getSecurity_Tag_Type() {
		return Security_Tag_Type;
	}


	public void setSecurity_Tag_Type(String security_Tag_Type) {
		Security_Tag_Type = security_Tag_Type;
	}


	public String getShipping_Container_Qty_Desc() {
		return Shipping_Container_Qty_Desc;
	}


	public void setShipping_Container_Qty_Desc(String shipping_Container_Qty_Desc) {
		Shipping_Container_Qty_Desc = shipping_Container_Qty_Desc;
	}


	public String getHandling_Instr_Ref() {
		return Handling_Instr_Ref;
	}


	public void setHandling_Instr_Ref(String handling_Instr_Ref) {
		Handling_Instr_Ref = handling_Instr_Ref;
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


	public String getPrice_Comparison_Content_Type() {
		return Price_Comparison_Content_Type;
	}


	public void setPrice_Comparison_Content_Type(String price_Comparison_Content_Type) {
		Price_Comparison_Content_Type = price_Comparison_Content_Type;
	}


	public String getPrice_Comparison_Measurement() {
		return Price_Comparison_Measurement;
	}


	public void setPrice_Comparison_Measurement(String price_Comparison_Measurement) {
		Price_Comparison_Measurement = price_Comparison_Measurement;
	}


	public String getPrice_Comparison_Measurement_Uom() {
		return Price_Comparison_Measurement_Uom;
	}


	public void setPrice_Comparison_Measurement_Uom(String price_Comparison_Measurement_Uom) {
		Price_Comparison_Measurement_Uom = price_Comparison_Measurement_Uom;
	}


	public String getMfr_Internal_Ref() {
		return Mfr_Internal_Ref;
	}


	public void setMfr_Internal_Ref(String mfr_Internal_Ref) {
		Mfr_Internal_Ref = mfr_Internal_Ref;
	}


	public String getFront_Face_Type() {
		return Front_Face_Type;
	}


	public void setFront_Face_Type(String front_Face_Type) {
		Front_Face_Type = front_Face_Type;
	}


	public String getRfId_On_Packaging() {
		return RfId_On_Packaging;
	}


	public void setRfId_On_Packaging(String rfId_On_Packaging) {
		RfId_On_Packaging = rfId_On_Packaging;
	}


	public Integer getLiquor_Age() {
		return Liquor_Age;
	}


	public void setLiquor_Age(Integer liquor_Age) {
		Liquor_Age = liquor_Age;
	}


	public Float getStd_Drinks() {
		return Std_Drinks;
	}


	public void setStd_Drinks(Float std_Drinks) {
		Std_Drinks = std_Drinks;
	}


	public Integer getVintage() {
		return Vintage;
	}


	public void setVintage(Integer vintage) {
		Vintage = vintage;
	}


	public List<AlcoholPercentage> getLstAlcoholPercentage() {
		return lstAlcoholPercentage;
	}


	public void setLstAlcoholPercentage(List<AlcoholPercentage> lstAlcoholPercentage) {
		this.lstAlcoholPercentage = lstAlcoholPercentage;
	}


	public List<Packaging> getLstPackaging() {
		return lstPackaging;
	}


	public void setLstPackaging(List<Packaging> lstPackaging) {
		this.lstPackaging = lstPackaging;
	}


	public List<Packaging_Material> getLstPackagingMaterial() {
		return lstPackagingMaterial;
	}


	public void setLstPackagingMaterial(List<Packaging_Material> lstPackagingMaterial) {
		this.lstPackagingMaterial = lstPackagingMaterial;
	}


	public List<Packaging_Function> getLstPackagingFunction() {
		return lstPackagingFunction;
	}


	public void setLstPackagingFunction(List<Packaging_Function> lstPackagingFunction) {
		this.lstPackagingFunction = lstPackagingFunction;
	}


	public List<Packaging_Deposit> getLstPackaging_Deposit() {
		return lstPackaging_Deposit;
	}


	public void setLstPackaging_Deposit(List<Packaging_Deposit> lstPackaging_Deposit) {
		this.lstPackaging_Deposit = lstPackaging_Deposit;
	}


	public List<Packaging_Recycle> getLstPackaging_Recycle() {
		return lstPackaging_Recycle;
	}


	public void setLstPackaging_Recycle(List<Packaging_Recycle> lstPackaging_Recycle) {
		this.lstPackaging_Recycle = lstPackaging_Recycle;
	}


	public List<Packaging_Recycle_Scheme> getLstPackaging_Recycle_Scheme() {
		return lstPackaging_Recycle_Scheme;
	}


	public void setLstPackaging_Recycle_Scheme(List<Packaging_Recycle_Scheme> lstPackaging_Recycle_Scheme) {
		this.lstPackaging_Recycle_Scheme = lstPackaging_Recycle_Scheme;
	}


	public List<Packaging_Sustainability> getLstPackaging_Sustainability() {
		return lstPackaging_Sustainability;
	}


	public void setLstPackaging_Sustainability(List<Packaging_Sustainability> lstPackaging_Sustainability) {
		this.lstPackaging_Sustainability = lstPackaging_Sustainability;
	}


	public AVP getAVP() {
		return AVP;
	}


	public void setAVP(AVP aVP) {
		AVP = aVP;
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


	public List<TradeItemMeasurements> getLstTradeItemMeasurements() {
		return lstTradeItemMeasurements;
	}


	public void setLstTradeItemMeasurements(List<TradeItemMeasurements> lstTradeItemMeasurements) {
		this.lstTradeItemMeasurements = lstTradeItemMeasurements;
	}


	public List<TradeItemPegMeasurements> getLstTradeItemPegMeasurements() {
		return lstTradeItemPegMeasurements;
	}


	public void setLstTradeItemPegMeasurements(List<TradeItemPegMeasurements> lstTradeItemPegMeasurements) {
		this.lstTradeItemPegMeasurements = lstTradeItemPegMeasurements;
	}


	public List<TradeItemNetContent> getLstTradeItemNetContent() {
		return lstTradeItemNetContent;
	}


	public void setLstTradeItemNetContent(List<TradeItemNetContent> lstTradeItemNetContent) {
		this.lstTradeItemNetContent = lstTradeItemNetContent;
	}


	public List<TradeItemDescriptionInformation> getLstTradeItemDescriptionInformation() {
		return lstTradeItemDescriptionInformation;
	}


	public void setLstTradeItemDescriptionInformation(
			List<TradeItemDescriptionInformation> lstTradeItemDescriptionInformation) {
		this.lstTradeItemDescriptionInformation = lstTradeItemDescriptionInformation;
	}


	public List<TradeItemVariant_Desc> getLstTradeItemVariant_Desc() {
		return lstTradeItemVariant_Desc;
	}


	public void setLstTradeItemVariant_Desc(List<TradeItemVariant_Desc> lstTradeItemVariant_Desc) {
		this.lstTradeItemVariant_Desc = lstTradeItemVariant_Desc;
	}


	public List<Dates> getLstDates() {
		return lstDates;
	}


	public void setLstDates(List<Dates> lstDates) {
		this.lstDates = lstDates;
	}


	public List<TradeItemHierarchyQuantity> getLstTradeItemHierarchyQuantity() {
		return lstTradeItemHierarchyQuantity;
	}


	public void setLstTradeItemHierarchyQuantity(List<TradeItemHierarchyQuantity> lstTradeItemHierarchyQuantity) {
		this.lstTradeItemHierarchyQuantity = lstTradeItemHierarchyQuantity;
	}


	public List<Packaging_Marking> getLstPackaging_Marking() {
		return lstPackaging_Marking;
	}


	public void setLstPackaging_Marking(List<Packaging_Marking> lstPackaging_Marking) {
		this.lstPackaging_Marking = lstPackaging_Marking;
	}


	public List<Packaging_Returnable> getLstPackaging_Returnable() {
		return lstPackaging_Returnable;
	}


	public void setLstPackaging_Returnable(List<Packaging_Returnable> lstPackaging_Returnable) {
		this.lstPackaging_Returnable = lstPackaging_Returnable;
	}


	public List<Packaging_Date> getLstPackaging_Date() {
		return lstPackaging_Date;
	}


	public void setLstPackaging_Date(List<Packaging_Date> lstPackaging_Date) {
		this.lstPackaging_Date = lstPackaging_Date;
	}


	public List<TradeItemTemperatureInformation> getLstTradeItemTemperatureInformation() {
		return lstTradeItemTemperatureInformation;
	}


	public void setLstTradeItemTemperatureInformation(List<TradeItemTemperatureInformation> lstTradeItemTemperatureInformation) {
		this.lstTradeItemTemperatureInformation = lstTradeItemTemperatureInformation;
	}


	public List<LeadTimes> getLstLeadTimes() {
		return lstLeadTimes;
	}


	public void setLstLeadTimes(List<LeadTimes> lstLeadTimes) {
		this.lstLeadTimes = lstLeadTimes;
	}


	public List<Nutritional_Claim> getLstNutritional_Claim() {
		return lstNutritional_Claim;
	}


	public void setLstNutritional_Claim(List<Nutritional_Claim> lstNutritional_Claim) {
		this.lstNutritional_Claim = lstNutritional_Claim;
	}


	public List<Nutritional_Claim_Detail> getLstNutritional_Claim_Detail() {
		return lstNutritional_Claim_Detail;
	}


	public void setLstNutritional_Claim_Detail(List<Nutritional_Claim_Detail> lstNutritional_Claim_Detail) {
		this.lstNutritional_Claim_Detail = lstNutritional_Claim_Detail;
	}


	public List<Serving_Size_Desc> getLstServing_Size_Desc() {
		return lstServing_Size_Desc;
	}


	public void setLstServing_Size_Desc(List<Serving_Size_Desc> lstServing_Size_Desc) {
		this.lstServing_Size_Desc = lstServing_Size_Desc;
	}


	public List<Nutrient_Header> getLstNutrient_Header() {
		return lstNutrient_Header;
	}


	public void setLstNutrient_Header(List<Nutrient_Header> lstNutrient_Header) {
		this.lstNutrient_Header = lstNutrient_Header;
	}


	public List<Nutrient_Serving_Size> getLstNutrient_Serving_Size() {
		return lstNutrient_Serving_Size;
	}


	public void setLstNutrient_Serving_Size(List<Nutrient_Serving_Size> lstNutrient_Serving_Size) {
		this.lstNutrient_Serving_Size = lstNutrient_Serving_Size;
	}


	public List<Nutrient_Information> getLstNutrient_Information() {
		return lstNutrient_Information;
	}


	public void setLstNutrient_Information(List<Nutrient_Information> lstNutrient_Information) {
		this.lstNutrient_Information = lstNutrient_Information;
	}


	public List<AllergenInformationModule> getLstAllergenInformationModule() {
		return lstAllergenInformationModule;
	}


	public void setLstAllergenInformationModule(List<AllergenInformationModule> lstAllergenInformationModule) {
		this.lstAllergenInformationModule = lstAllergenInformationModule;
	}


	public List<Grow_Method> getLstGrowMethod() {
		return lstGrowMethod;
	}


	public void setLstGrowMethod(List<Grow_Method> lstGrowMethod) {
		this.lstGrowMethod = lstGrowMethod;
	}


	public List<ProductCharacteristicsModule> getLstProductCharacteristicsModule() {
		return lstProductCharacteristicsModule;
	}


	public void setLstProductCharacteristicsModule(List<ProductCharacteristicsModule> lstProductCharacteristicsModule) {
		this.lstProductCharacteristicsModule = lstProductCharacteristicsModule;
	}


	public List<ReferencedFileHeader> getLstReferencedFileHeader() {
		return lstReferencedFileHeader;
	}


	public void setLstReferencedFileHeader(List<ReferencedFileHeader> lstReferencedFileHeader) {
		this.lstReferencedFileHeader = lstReferencedFileHeader;
	}


	public String getIs_Country_In_CodeList() {
		return Is_Country_In_CodeList;
	}


	public void setIs_Country_In_CodeList(String is_Country_In_CodeList) {
		Is_Country_In_CodeList = is_Country_In_CodeList;
	}


	public List<Battery_Type> getLstBattery_Type() {
		return lstBattery_Type;
	}


	public void setLstBattery_Type(List<Battery_Type> lstBattery_Type) {
		this.lstBattery_Type = lstBattery_Type;
	}


	


	
}

