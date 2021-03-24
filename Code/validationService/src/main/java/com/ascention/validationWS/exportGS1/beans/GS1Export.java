package com.ascention.validationWS.exportGS1.beans;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.ascention.validationWS.beans.HierarchyGTINCountryOrigin;
import com.ascention.validationWS.beans.ProductExportHeader;
import com.ascention.validationWS.beans.ProductHierarchy;
import com.ascention.validationWS.exportGS1.dao.NonfoodIngredientModuleDAO;

public class GS1Export {
	private List<ProductExportHeader> lstProductExportHdr;
	private List<ProductHierarchy> lstProductHierarchy;
	private AlcoholInformationModule alcoholInformationModule;
	private BatteryInformationModule batteryInformationModule;
	private List<AllergenInformationModule> lstAllergenInformationModule;
	private List<CertificationInformationModule> lstCertificationInformationModule;
	private List<ChemicalRegulationInformationModule> lstChemicalRegulationInformationModule;
	private List<ConsumerInstructionsModule> lstConsumerInstructionsModule;
	private List<DeliveryPurchasingInformationModule> lstDeliveryPurchasingInformationModule;
	private List<LeadTimes> lstLeadTimes;
	private List<OrderQuantity> lstOrderQuantity;
	private List<Dates> lstDates;
	private List<DietInformationModule> lstDietInformationModule;
	private List<DurableGoodsCharacteristicsModule> lstDurableGoodsCharacteristicsModule;
	private List<DutyFeeTaxInformationModule> lstDutyFeeTaxInformationModule;
	private FarmingAndProcessingInformationModule farmingAndProcessingInformationModule;
	private FoodAndBeverageIngredientModule foodAndBeverageIngredientModule;
	private FoodAndBeveragePreparationServingModule foodAndBeveragePreparationServingModule;
	private FoodAndBeveragePropertiesInformationModule foodAndBeveragePropertiesInformationModule;
	private MarketingInformationModule marketingInformationModule;
	private NonfoodIngredientModule nonfoodIngredientModule;
	private PackagingInformationModule packagingInformationModule;
	private PackagingMarkingModule packagingMarkingModule;
	private PlaceOfItemActivityModule placeOfItemActivityModule;
	private List<ProductCharacteristicsModule> lstProductCharacteristicsModule;
	private ReferencedFileDetailInformationModule referencedFileDetailInformationModule;
	private SalesInformationModule salesInformationModule;
	private List<SecurityTagInformationModule> lstSecurityTagInformationModule;
	private List<TradeItem> lstTradeItem;
	private TradeItemDescriptionModule tradeItemDescriptionModule;
	private NutritionalInformationModule nutritionalInformationModule;
	private TradeItemHandlingModule tradeItemHandlingModule;
	private TradeItemHierarchyModule tradeItemHierarchyModule;
	private TradeItemLifespanModule tradeItemLifespanModule;
	private TradeItemMeasurementsModule tradeItemMeasurementsModule;
	private TradeItemSizeModule tradeItemSizeModule;
	private TradeItemTemperatureInformationModule tradeItemTemperatureInformationModule;
	private List<TransportationHazardousClassification> lstTransportationHazardousClassification;
	private List<VariableTradeItemInformation> lstVariableTradeItemInformation;
	private List<WarrantyInformation> lstWarrantyInformation;
	private List<HierarchyGTINCountryOrigin> lstHierarchyGTINCountryOrigin;
	private AVP avp;
	
	
	private Integer Product_Id;
	private Integer Client_Id;
	private Integer Org_Id;
	private String strHierarchyIds;
	private String strCOOIds;
	private Integer numberOfLevels;
	public void setHierarchyLevels(List<ProductHierarchy> lstProductHierarchy) {
		
		for (ProductHierarchy ph : lstProductHierarchy) {
			if (ph.getProd_Unit_Desc().equals("CASE")) {
				ph.setLevel(1);
			} else if (ph.getProd_Unit_Desc().equals("PACK_OR_INNER_PACK") && lstProductHierarchy.size() > 2) {
				ph.setLevel(2);
			} else if (ph.getProd_Unit_Desc().equals("BASE_UNIT_OR_EACH")) {
				if (lstProductHierarchy.size() > 2) {
					ph.setLevel(3);
				} else {
					ph.setLevel(2);
				}
			}
		}
	}

	
	public String getStrHierarchyIds() {
		return strHierarchyIds;
	}

	public void setStrHierarchyIds(String strHierarchyIds) {
		this.strHierarchyIds = strHierarchyIds;
	}

	public Integer getProduct_Id() {
		return Product_Id;
	}

	public void setProduct_Id(Integer product_Id) {
		Product_Id = product_Id;
	}

	public Integer getClient_Id() {
		return Client_Id;
	}

	public void setClient_Id(Integer client_Id) {
		Client_Id = client_Id;
	}

	public Integer getOrg_Id() {
		return Org_Id;
	}

	public void setOrg_Id(Integer org_Id) {
		Org_Id = org_Id;
	}

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }
	
	public List<ProductExportHeader> getLstProductExportHdr() {
		return lstProductExportHdr;
	}
	public void setLstProductExportHdr(List<ProductExportHeader> lstProductExportHdr) {
		this.lstProductExportHdr = lstProductExportHdr;
	}
	public List<ProductHierarchy> getLstProductHierarchy() {
		return lstProductHierarchy;
	}
	public void setLstProductHierarchy(List<ProductHierarchy> lstProductHierarchy) {
		this.lstProductHierarchy = lstProductHierarchy;
	}

	public String getStrCOOIds() {
		return strCOOIds;
	}

	public void setStrCOOIds(String strCOOIds) {
		this.strCOOIds = strCOOIds;
	}

	

	public Integer getNumberOfLevels() {
		return numberOfLevels;
	}


	public void setNumberOfLevels(Integer numberOfLevels) {
		this.numberOfLevels = numberOfLevels;
	}


	public AlcoholInformationModule getAlcoholInformationModule() {
		return alcoholInformationModule;
	}


	public void setAlcoholInformationModule(AlcoholInformationModule alcoholInformationModule) {
		this.alcoholInformationModule = alcoholInformationModule;
	}


	public List<AllergenInformationModule> getLstAllergenInformationModule() {
		return lstAllergenInformationModule;
	}


	public void setLstAllergenInformationModule(List<AllergenInformationModule> lstAllergenInformationModule) {
		this.lstAllergenInformationModule = lstAllergenInformationModule;
	}


	public BatteryInformationModule getBatteryInformationModule() {
		return batteryInformationModule;
	}


	public void setBatteryInformationModule(BatteryInformationModule batteryInformationModule) {
		this.batteryInformationModule = batteryInformationModule;
	}


	public List<CertificationInformationModule> getLstCertificationInformationModule() {
		return lstCertificationInformationModule;
	}


	public void setLstCertificationInformationModule(List<CertificationInformationModule> lstCertificationInformationModule) {
		this.lstCertificationInformationModule = lstCertificationInformationModule;
	}


	public List<ChemicalRegulationInformationModule> getLstChemicalRegulationInformationModule() {
		return lstChemicalRegulationInformationModule;
	}


	public void setLstChemicalRegulationInformationModule(List<ChemicalRegulationInformationModule> lstChemicalRegulationInformationModule) {
		this.lstChemicalRegulationInformationModule = lstChemicalRegulationInformationModule;
	}


	public List<ConsumerInstructionsModule> getLstConsumerInstructionsModule() {
		return lstConsumerInstructionsModule;
	}


	public void setLstConsumerInstructionsModule(List<ConsumerInstructionsModule> lstConsumerInstructionsModule) {
		this.lstConsumerInstructionsModule = lstConsumerInstructionsModule;
	}


	public List<DeliveryPurchasingInformationModule> getLstDeliveryPurchasingInformationModule() {
		return lstDeliveryPurchasingInformationModule;
	}


	public void setLstDeliveryPurchasingInformationModule(List<DeliveryPurchasingInformationModule> lstDeliveryPurchasingInformationModule) {
		this.lstDeliveryPurchasingInformationModule = lstDeliveryPurchasingInformationModule;
	}


	public List<LeadTimes> getLstLeadTimes() {
		return lstLeadTimes;
	}


	public void setLstLeadTimes(List<LeadTimes> lstLeadTimes) {
		this.lstLeadTimes = lstLeadTimes;
	}


	public List<OrderQuantity> getLstOrderQuantity() {
		return lstOrderQuantity;
	}


	public void setLstOrderQuantity(List<OrderQuantity> lstOrderQuantity) {
		this.lstOrderQuantity = lstOrderQuantity;
	}


	public List<Dates> getLstDates() {
		return lstDates;
	}


	public void setLstDates(List<Dates> lstDates) {
		this.lstDates = lstDates;
	}


	public List<DietInformationModule> getLstDietInformationModule() {
		return lstDietInformationModule;
	}


	public void setLstDietInformationModule(List<DietInformationModule> lstDietInformationModule) {
		this.lstDietInformationModule = lstDietInformationModule;
	}


	public List<DurableGoodsCharacteristicsModule> getLstDurableGoodsCharacteristicsModule() {
		return lstDurableGoodsCharacteristicsModule;
	}


	public void setLstDurableGoodsCharacteristicsModule(List<DurableGoodsCharacteristicsModule> lstDurableGoodsCharacteristicsModule) {
		this.lstDurableGoodsCharacteristicsModule = lstDurableGoodsCharacteristicsModule;
	}


	public List<DutyFeeTaxInformationModule> getLstDutyFeeTaxInformationModule() {
		return lstDutyFeeTaxInformationModule;
	}


	public void setLstDutyFeeTaxInformationModule(List<DutyFeeTaxInformationModule> lstDutyFeeTaxInformationModule) {
		this.lstDutyFeeTaxInformationModule = lstDutyFeeTaxInformationModule;
	}


	public FarmingAndProcessingInformationModule getFarmingAndProcessingInformationModule() {
		return farmingAndProcessingInformationModule;
	}


	public void setFarmingAndProcessingInformationModule(FarmingAndProcessingInformationModule farmingAndProcessingInformationModule) {
		this.farmingAndProcessingInformationModule = farmingAndProcessingInformationModule;
	}


	public FoodAndBeverageIngredientModule getFoodAndBeverageIngredientModule() {
		return foodAndBeverageIngredientModule;
	}


	public void setFoodAndBeverageIngredientModule(FoodAndBeverageIngredientModule foodAndBeverageIngredientModule) {
		this.foodAndBeverageIngredientModule = foodAndBeverageIngredientModule;
	}


	public FoodAndBeveragePreparationServingModule getFoodAndBeveragePreparationServingModule() {
		return foodAndBeveragePreparationServingModule;
	}


	public void setFoodAndBeveragePreparationServingModule(FoodAndBeveragePreparationServingModule foodAndBeveragePreparationServingModule) {
		this.foodAndBeveragePreparationServingModule = foodAndBeveragePreparationServingModule;
	}


	public FoodAndBeveragePropertiesInformationModule getFoodAndBeveragePropertiesInformationModule() {
		return foodAndBeveragePropertiesInformationModule;
	}


	public void setFoodAndBeveragePropertiesInformationModule(FoodAndBeveragePropertiesInformationModule foodAndBeveragePropertiesInformationModule) {
		this.foodAndBeveragePropertiesInformationModule = foodAndBeveragePropertiesInformationModule;
	}


	public MarketingInformationModule getMarketingInformationModule() {
		return marketingInformationModule;
	}


	public void setMarketingInformationModule(MarketingInformationModule marketingInformationModule) {
		this.marketingInformationModule = marketingInformationModule;
	}


	public NonfoodIngredientModule getNonfoodIngredientModule() {
		return nonfoodIngredientModule;
	}


	public void setNonfoodIngredientModule(NonfoodIngredientModule nonfoodIngredientModule) {
		this.nonfoodIngredientModule = nonfoodIngredientModule;
	}


	public PackagingInformationModule getPackagingInformationModule() {
		return packagingInformationModule;
	}


	public void setPackagingInformationModule(PackagingInformationModule packagingInformationModule) {
		this.packagingInformationModule = packagingInformationModule;
	}


	public PackagingMarkingModule getPackagingMarkingModule() {
		return packagingMarkingModule;
	}


	public void setPackagingMarkingModule(PackagingMarkingModule packagingMarkingModule) {
		this.packagingMarkingModule = packagingMarkingModule;
	}


	public PlaceOfItemActivityModule getPlaceOfItemActivityModule() {
		return placeOfItemActivityModule;
	}


	public void setPlaceOfItemActivityModule(PlaceOfItemActivityModule placeOfItemActivityModule) {
		this.placeOfItemActivityModule = placeOfItemActivityModule;
	}


	public List<ProductCharacteristicsModule> getLstProductCharacteristicsModule() {
		return lstProductCharacteristicsModule;
	}


	public void setLstProductCharacteristicsModule(List<ProductCharacteristicsModule> lstProductCharacteristicsModule) {
		this.lstProductCharacteristicsModule = lstProductCharacteristicsModule;
	}


	

	public ReferencedFileDetailInformationModule getReferencedFileDetailInformationModule() {
		return referencedFileDetailInformationModule;
	}


	public void setReferencedFileDetailInformationModule(ReferencedFileDetailInformationModule referencedFileDetailInformationModule) {
		this.referencedFileDetailInformationModule = referencedFileDetailInformationModule;
	}


	public SalesInformationModule getSalesInformationModule() {
		return salesInformationModule;
	}


	public void setSalesInformationModule(SalesInformationModule salesInformationModule) {
		this.salesInformationModule = salesInformationModule;
	}


	public List<SecurityTagInformationModule> getLstSecurityTagInformationModule() {
		return lstSecurityTagInformationModule;
	}


	public void setLstSecurityTagInformationModule(List<SecurityTagInformationModule> lstSecurityTagInformationModule) {
		this.lstSecurityTagInformationModule = lstSecurityTagInformationModule;
	}


	public List<TradeItem> getLstTradeItem() {
		return lstTradeItem;
	}


	public void setLstTradeItem(List<TradeItem> lstTradeItem) {
		this.lstTradeItem = lstTradeItem;
	}


	public TradeItemDescriptionModule getTradeItemDescriptionModule() {
		return tradeItemDescriptionModule;
	}


	public void setTradeItemDescriptionModule(TradeItemDescriptionModule tradeItemDescriptionModule) {
		this.tradeItemDescriptionModule = tradeItemDescriptionModule;
	}


	public TradeItemHandlingModule getTradeItemHandlingModule() {
		return tradeItemHandlingModule;
	}


	public void setTradeItemHandlingModule(TradeItemHandlingModule tradeItemHandlingModule) {
		this.tradeItemHandlingModule = tradeItemHandlingModule;
	}


	public TradeItemHierarchyModule getTradeItemHierarchyModule() {
		return tradeItemHierarchyModule;
	}


	public void setTradeItemHierarchyModule(TradeItemHierarchyModule tradeItemHierarchyModule) {
		this.tradeItemHierarchyModule = tradeItemHierarchyModule;
	}


	public TradeItemLifespanModule getTradeItemLifespanModule() {
		return tradeItemLifespanModule;
	}


	public void setTradeItemLifespanModule(TradeItemLifespanModule tradeItemLifespanModule) {
		this.tradeItemLifespanModule = tradeItemLifespanModule;
	}


	public TradeItemMeasurementsModule getTradeItemMeasurementsModule() {
		return tradeItemMeasurementsModule;
	}


	public void setTradeItemMeasurementsModule(TradeItemMeasurementsModule tradeItemMeasurementsModule) {
		this.tradeItemMeasurementsModule = tradeItemMeasurementsModule;
	}


	public TradeItemSizeModule getTradeItemSizeModule() {
		return tradeItemSizeModule;
	}


	public void setTradeItemSizeModule(TradeItemSizeModule tradeItemSizeModule) {
		this.tradeItemSizeModule = tradeItemSizeModule;
	}


	public TradeItemTemperatureInformationModule getTradeItemTemperatureInformationModule() {
		return tradeItemTemperatureInformationModule;
	}


	public void setTradeItemTemperatureInformationModule(TradeItemTemperatureInformationModule tradeItemTemperatureInformationModule) {
		this.tradeItemTemperatureInformationModule = tradeItemTemperatureInformationModule;
	}


	public List<TransportationHazardousClassification> getLstTransportationHazardousClassification() {
		return lstTransportationHazardousClassification;
	}


	public void setLstTransportationHazardousClassification(List<TransportationHazardousClassification> lstTransportationHazardousClassification) {
		this.lstTransportationHazardousClassification = lstTransportationHazardousClassification;
	}


	public List<VariableTradeItemInformation> getLstVariableTradeItemInformation() {
		return lstVariableTradeItemInformation;
	}


	public void setLstVariableTradeItemInformation(List<VariableTradeItemInformation> lstVariableTradeItemInformation) {
		this.lstVariableTradeItemInformation = lstVariableTradeItemInformation;
	}


	public List<WarrantyInformation> getLstWarrantyInformation() {
		return lstWarrantyInformation;
	}


	public void setLstWarrantyInformation(List<WarrantyInformation> lstWarrantyInformation) {
		this.lstWarrantyInformation = lstWarrantyInformation;
	}


	public AVP getAvp() {
		return avp;
	}


	public void setAvp(AVP avp) {
		this.avp = avp;
	}


	public List<HierarchyGTINCountryOrigin> getLstHierarchyGTINCountryOrigin() {
		return lstHierarchyGTINCountryOrigin;
	}


	public void setLstHierarchyGTINCountryOrigin(List<HierarchyGTINCountryOrigin> lstHierarchyGTINCountryOrigin) {
		this.lstHierarchyGTINCountryOrigin = lstHierarchyGTINCountryOrigin;
	}


	public NutritionalInformationModule getNutritionalInformationModule() {
		return nutritionalInformationModule;
	}


	public void setNutritionalInformationModule(NutritionalInformationModule nutritionalInformationModule) {
		this.nutritionalInformationModule = nutritionalInformationModule;
	}


	

	
	
	
}
