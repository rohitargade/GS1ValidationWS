package com.ascention.validationWS.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.FileWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.hamcrest.collection.IsEmptyCollection;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ascention.GS1.pricing.schema.PriceSynchronisationDocumentMessageType;
import com.ascention.GS1.schema.*;
import com.ascention.validationWS.beans.AlcoholPercentage;
import com.ascention.validationWS.beans.CountryActiveList;
import com.ascention.validationWS.beans.Duty;
import com.ascention.validationWS.beans.GPCAttributes;
import com.ascention.validationWS.beans.GTINCountryOrigin;
import com.ascention.validationWS.beans.HierarchyAlcoholPercentage;
import com.ascention.validationWS.beans.HierarchyDuty;
import com.ascention.validationWS.beans.HierarchyGTINCountryOrigin;
import com.ascention.validationWS.beans.HierarchyOrderQty;
import com.ascention.validationWS.beans.HierarchyPalletAttributes;
import com.ascention.validationWS.beans.HierarchyUNSPSCAttributes;
import com.ascention.validationWS.beans.OrderQty;
import com.ascention.validationWS.beans.PalletAttributes;
import com.ascention.validationWS.beans.ProductAdditional;
import com.ascention.validationWS.beans.ProductAdditionalArray;
import com.ascention.validationWS.beans.ProductCore;
import com.ascention.validationWS.beans.ProductDetails;
import com.ascention.validationWS.beans.ProductExport;
import com.ascention.validationWS.beans.ProductExportHeader;
import com.ascention.validationWS.beans.ProductHierarchy;
import com.ascention.validationWS.beans.ProductRecipientHeader;
import com.ascention.validationWS.beans.Result;
import com.ascention.validationWS.beans.UNSPSCAttributes;
import com.ascention.validationWS.beans.ValidationFailure;
import com.ascention.validationWS.service.RulesValidationService;
import com.ascention.validationWS.service.ReloadDroolsRulesService;
import com.ascention.validationWS.dao.*;
import com.ascention.validationWS.exportGS1.dao.*;
import com.ascention.validationWS.exportGS1.beans.*;



import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



@RestController
public class RulesValidationController {
	private static final ObjectFactoryAlcoholInformationModule alcoholInfoObjectFactory = new ObjectFactoryAlcoholInformationModule();
	private static final ObjectFactoryChemicalRegulationInformationModule chemInfoObjectFactory = new ObjectFactoryChemicalRegulationInformationModule();
	
	
	private static final ObjectFactory objectFactory = new ObjectFactory() ;
	private static final com.ascention.GS1.pricing.schema.ObjectFactory pricingObjFactory = new com.ascention.GS1.pricing.schema.ObjectFactory();
	
	private final RulesValidationService rulesValidationService;
	private static UUID uniqueIdentifier = UUID.randomUUID();
	
	@Resource
	private ReloadDroolsRulesService rules;
	
	@Autowired
	private CatalogueItemNotificationDAO catalogueItemNotificationDAO;
	@Autowired
	private ProductCoreDAO productCoreDAO;
	@Autowired
	private CountryActiveListDAO countryActiveListDAO;
	@Autowired
	private GPCAttributesDAO GPCAttributeDAO;
	@Autowired
	private GTINCountryOriginDAO GTINCountryOriginDAO;
	@Autowired
	private OrderQtyDAO orderQtyDAO;	
	@Autowired
	private DutyDAO dutyDAO;	
	@Autowired
	private PalletAttributesDAO palletAttributesDAO;
	@Autowired
	private UNSPSCAttributesDAO UNSPSCAttributesDAO;
	@Autowired
	private ProductDetailsDAO productDetailsDAO;
	@Autowired
	private ValidationFailureDAO validationFailureDAO;
	
	
	@Autowired
	private AlcoholPercentageDAO alcoholPercentageDAO;
	
	
	@Autowired
	private ProductExportHeaderDAO productExportHeaderDAO;
	@Autowired
	private ProductRecipientHeaderDAO productRecipientHeaderDAO;
	
	@Autowired
	private ProductHierarchyDAO productHierarchyDAO;
	@Autowired
	private HierarchyGPCAttributesDAO hierarchyGPCAttributesDAO;
	@Autowired
	private HierarchyAlcoholPercentageDAO hierarchyAlcoholPercentageDAO;
	@Autowired
	private HierarchyGTINCountryOriginDAO hierarchyGTINCountryOriginDAO;
	@Autowired
	private HierarchyOrderQtyDAO hierarchyOrderQtyDAO;	
	@Autowired
	private HierarchyDutyDAO hierarchyDutyDAO;	
	@Autowired
	private HierarchyPalletAttributesDAO hierarchyPalletAttributesDAO;
	@Autowired
	private HierarchyUNSPSCAttributesDAO hierarchyUNSPSCAttributesDAO;
	
	@Autowired
	private TradeItemAdditionalTradeItemDAO tradeItemAdditionalTradeItemDAO;
	
	@Autowired
	private AlcoholInformationModuleDAO alcoholInformationModuleDAO;
	@Autowired
	private VintnerDAO vintnerDAO;
	@Autowired
	private AllergenInformationModuleDAO allergenInformationModuleDAO;	
	@Autowired
	private BatteryTypeDAO batteryTypeDAO;
	@Autowired
	private BatteryInformationModuleDAO batteryInformationModuleDAO;	
	@Autowired
	private CertificationInformationModuleDAO certificationInformationModuleDAO;
	@Autowired
	private ChemicalRegulationInformationModuleDAO chemicalRegulationInformationModuleDAO;	
	@Autowired
	private ConsumerInstructionsModuleDAO consumerInstructionsModuleDAO;	
	@Autowired
	private DeliveryPurchasingInformationModuleDAO deliveryPurchasingInformationModuleDAO;
	@Autowired
	private LeadTimesDAO leadTimesDAO;	
	@Autowired
	private DatesDAO datesDAO;	
	@Autowired
	private OrderQuantityDAO orderQuantityDAO;	
	@Autowired
	private DietInformationModuleDAO dietInformationModuleDAO;
	@Autowired
	private DurableGoodsCharacteristicsModuleDAO durableGoodsCharacteristicsModuleDAO; 
	@Autowired
	private DutyFeeTaxInformationModuleDAO dutyFeeTaxInformationModuleDAO;
	@Autowired
	private PharmaceuticalItemInformationModuleDAO pharmaceuticalItemInformationModuleDAO;
	@Autowired
	private GrowMethodDAO growMethodDAO;
	@Autowired
	private FarmingAndProcessingInformationModuleDAO farmingAndProcessingInformationModuleDAO;
	
	
	@Autowired
	private FoodAndBeverageIngredientModuleDAO foodAndBeverageIngredientModuleDAO;
	@Autowired
	private FoodAndBeverageIngredientDAO foodAndBeverageIngredientDAO;
	@Autowired
	private AdditiveInformationDAO additiveInformationDAO;
	
	
	@Autowired
	private PreparationServingDAO preparationServingDAO; 
	@Autowired
	private ServingQuantityInformationDAO servingQuantityInformationDAO; 
	
	
	@Autowired
	private MicrobiologicalInformationDAO microbiologicalInformationDAO;
	@Autowired
	private PhysiochemicalCharacteristicDAO physiochemicalCharacteristicDAO;
	
	@Autowired
	private PA_Feature_BenefitDAO pa_Feature_BenefitDAO;
	@Autowired
	private MarketingInformationModuleDAO marketingInformationModuleDAO; 
	
	@Autowired
	private NonfoodIngredientModuleDAO nonfoodIngredientModuleDAO;
	
	@Autowired
	private PackagingDAO packagingDAO;
	@Autowired
	private Packaging_FunctionDAO packaging_FunctionDAO;
	@Autowired
	private Packaging_RecycleDAO packaging_RecycleDAO;
	@Autowired
	private Packaging_Recycle_SchemeDAO packaging_Recycle_SchemeDAO;
	@Autowired
	private Packaging_SustainabilityDAO packaging_SustainabilityDAO;
	@Autowired
	private Packaging_MaterialDAO packaging_MaterialDAO;
	@Autowired
	private Packaging_DepositDAO packaging_DepositDAO;
	
	@Autowired
	private Packaging_MarkingDAO packaging_MarkingDAO;
	@Autowired
	private Packaging_ReturnableDAO packaging_ReturnableDAO;
	@Autowired
	private Packaging_DateDAO packaging_DateDAO;

	@Autowired
	private PlaceOfProductActivityDAO placeOfProductActivityDAO;
	@Autowired
	private Import_ClassificationDAO import_ClassificationDAO;
	@Autowired
	private ProductCharacteristicsModuleDAO productCharacteristicsModuleDAO;
	@Autowired
	private ReferencedHeaderDAO referencedHeaderDAO;
	@Autowired
	private Selling_Price_ComparisonDAO selling_Price_ComparisonDAO; 
	@Autowired
	private Selling_UomDAO selling_UomDAO;
	@Autowired
	private Suggested_PriceDAO suggested_PriceDAO;
	@Autowired
	private SecurityTagInformationModuleDAO securityTagInformationModuleDAO;
	
	
	@Autowired
	private TradeItemInformationProviderDetailsDAO tradeItemInformationProviderDetailsDAO;
	@Autowired
	private TradeItemTargetMarketDAO tradeItemTargetMarketDAO;
	@Autowired
	private TradeItemHierarchyDAO tradeItemHierarchyDAO;
	@Autowired
	private TradeItemGPCAttributesDAO tradeItemGPCAttributesDAO;
	@Autowired
	private TradeItemUNSPSCAttributesDAO tradeItemUNSPSCAttributesDAO;
	@Autowired
	private TradeItemChildProductDAO tradeItemChildProductDAO;


	@Autowired
	private TradeItemVariant_DescDAO tradeItemVariant_DescDAO;
	@Autowired
	private TradeItemDescriptionInformationDAO tradeItemDescriptionInformationDAO;
	
	@Autowired
	private TradeItemHandlingInformationDAO tradeItemHandlingInformationDAO;
	@Autowired 
	private TradeItemLifespanDAO tradeItemLifespanDAO;
	@Autowired
	private TradeItemHierarchyQuantityDAO tradeItemHierarchyQuantityDAO;
	
	@Autowired
	private TradeItemMeasurementsDAO tradeItemMeasurementsDAO;
	@Autowired
	private TradeItemPegMeasurementsDAO tradeItemPegMeasurementsDAO;
	@Autowired
	private TradeItemNetContentDAO tradeItemNetContentDAO;
	
	@Autowired
	private TradeItemSizeDAO tradeItemSizeDAO;
	
	@Autowired
	private TradeItemTemperatureInformationDAO tradeItemTemperatureInformationDAO;
	
	@Autowired
	private TransportationHazardousClassificationDAO transportationHazardousClassificationDAO;
	@Autowired
	private VariableTradeItemInformationDAO variableTradeItemInformationDAO;
	@Autowired
	private WarrantyInformationDAO warrantyInformationDAO;
	
	@Autowired
	private Nutritional_ClaimDAO nutritional_ClaimDAO;
	@Autowired
	private Nutritional_Claim_DetailDAO nutritional_Claim_DetailDAO;
	@Autowired
	private Serving_Size_DescDAO serving_Size_DescDAO;
	@Autowired
	private Nutrient_HeaderDAO nutrient_HeaderDAO;	
	@Autowired
	private Nutrient_Serving_SizeDAO nutrient_Serving_SizeDAO;
	@Autowired
	private Nutrient_InformationDAO nutrient_InformationDAO;
	
	@Autowired
	private AVPDAO avpDAO;
	@Autowired
	private AVPPalletDAO avpPalletDAO;
	@Autowired
	private AVPCustomsExcisableValueDAO avpCustomsExcisableValueDAO;
	@Autowired
	private AVPHsnoApprovalDAO avpHsnoApprovalDAO;
	@Autowired
	private AVPEnvironmentalDAO avpEnvironmentalDAO;
	@Autowired
	private AVPHsnoClassificationDAO avpHsnoClassificationDAO;
	
	@Autowired
	private AnimalFeedingModuleDAO animalFeedingModuleDAO;
	@Autowired
	private TradeItemReferencedTradeItemDAO tradeItemReferencedTradeItemDAO;
	
	
	@Autowired
	private CIPHeaderDAO CIPHeaderDAO;
	@Autowired
	private CIPGTINDAO CIPGTINDAO;
	@Autowired
	private CIPPublishToGLNDAO CIPPublishToGLNDAO;
	
	
	@Autowired
	private GenericExportXMLDAO genericExportXMLDAO;
	
	@Value("${server.port}")
	private int serverPort;
	@Autowired
	public RulesValidationController(RulesValidationService rulesValidationService) {
		this.rulesValidationService = rulesValidationService;
	}
	private RestTemplate restTemplate = new RestTemplate();
	

	private XMLGregorianCalendar getXMLGregorianDate(String strDate) {
		try {
			Date packDepEnd;
		
			try {
				
				if (strDate != null) {
					strDate = strDate.replaceAll("\\s+", "T");
					strDate += "Z";

					packDepEnd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(strDate);
					GregorianCalendar c = new GregorianCalendar();
					c.setTime(packDepEnd);

					return DatatypeFactory.newInstance().newXMLGregorianCalendar(strDate);
				}
			
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  												
			
		} catch (DatatypeConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	
	
	
	public static XMLGregorianCalendar getXMLGregorianCalendarNow() 
            throws DatatypeConfigurationException
    {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        String FORMATER = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	    DateFormat format = new SimpleDateFormat(FORMATER);
        XMLGregorianCalendar now = 
            datatypeFactory.newXMLGregorianCalendar(format.format(gregorianCalendar.getTime()));
        return now;
    }
	
	@RequestMapping(value = "/genericAPIExportXML", produces = "application/json")
	public String genericExport(@RequestParam(required = true) Integer Channel_Publish_Id) {

		GenericExportXML channelPublishDetails = genericExportXMLDAO.getAllChannel_Publish_IdDetails(Channel_Publish_Id);
		
		if(channelPublishDetails!=null) {
			channelPublishDetails.getChannel_Publish_Id();
			String fileType = channelPublishDetails.getFile_Type();
			channelPublishDetails.getIs_Active();
			switch(fileType) {
			case "CIN":
				this.exportData(channelPublishDetails.getRecord_Id(), channelPublishDetails.getOperation().toUpperCase());
				break;
				
			case "CIP":
				this.exportProductCIP(channelPublishDetails.getRecord_Id(), channelPublishDetails.getOperation().toUpperCase());
				break;
				
			case "CIHW":
				this.exportProductCIHW(channelPublishDetails.getRecord_Id(), channelPublishDetails.getOperation().toUpperCase());
				break;	
			
			case "PSD":				
				String PSDResponse = restTemplate.getForEntity("http://localhost:"+serverPort+"/exportPSDXML?Price_Export="+channelPublishDetails.getRecord_Id()+"&operation="+channelPublishDetails.getOperation().toUpperCase()+"", String.class).getBody();
				break;	
			case "PSR":
				String PSRResponse = restTemplate.getForEntity("http://localhost:"+serverPort+"/exportPSRXML?Price_Relationship="+channelPublishDetails.getRecord_Id()+"&operation="+channelPublishDetails.getOperation().toUpperCase()+"", String.class).getBody();
				break;
			default:
				return "Unknown File Type";
				
			}
			channelPublishDetails.getStatus_Code();
			channelPublishDetails.getTable_Name();
			return String.valueOf(Channel_Publish_Id);
		}
		
		
		
		return "Incorrect Channel Id";
	}
	@RequestMapping(value = "/exportXML", produces = "application/json")
	public String exportData(@RequestParam(required = true) Integer Product_Export_Hdr_Id, @RequestParam(required = true) String operation) {

		GS1Export gs1Export = new GS1Export();
		List<ProductExportHeader> lstProductExportHdr = productExportHeaderDAO.getAllProductExportHeader(Product_Export_Hdr_Id);
		gs1Export.setLstProductExportHdr(lstProductExportHdr);
		uniqueIdentifier = UUID.randomUUID();
		String strHierarchyIds = "";
		String strCOOIds = "";
		
		
		
		for(ProductExportHeader prodExportHdr: lstProductExportHdr ) {
			gs1Export.setClient_Id(prodExportHdr.getClient_Id());
			gs1Export.setOrg_Id(prodExportHdr.getOrg_Id());
			gs1Export.setProduct_Id(prodExportHdr.getProduct_Id());
			if(strHierarchyIds.equals("")){
				strHierarchyIds = prodExportHdr.getProduct_Hierarchy_Id().toString();
				strCOOIds = prodExportHdr.getCountry_Origin_Id().toString();
			}else {
				strHierarchyIds += "," + prodExportHdr.getProduct_Hierarchy_Id().toString();
				strCOOIds += "," + prodExportHdr.getCountry_Origin_Id().toString();
			}
		}
		
		
		gs1Export.setStrCOOIds(strCOOIds);
		gs1Export.setStrHierarchyIds(strHierarchyIds);
		
		List<ProductHierarchy> lstProductHierarchy = productHierarchyDAO.getAllProductHierachyAttributes(gs1Export.getProduct_Id(), strHierarchyIds, gs1Export.getClient_Id(), gs1Export.getOrg_Id());
		gs1Export.setLstProductHierarchy(lstProductHierarchy);
		
		gs1Export.setHierarchyLevels(lstProductHierarchy);
		gs1Export.setNumberOfLevels(lstProductHierarchy.size());
		
		lstProductHierarchy.sort(new Comparator<ProductHierarchy>() {
			public int compare(ProductHierarchy s1, ProductHierarchy s2) {
				return s1.getLevel()-s2.getLevel();
			}
		});
		
		
/*		catalogueItemNotificationMessage
			StandardBusinessDocumentHeader
				Sender
				Receiver
				DocumentIdentification
			Transaction
				transactionIdentification			
				documentCommand
					documentCommandHeader
					catalogueItemNotification
						catalogueItemNotificationIdentification
						catalogueItem
							catalogueItemState
							tradeItem
							catalogueItemChildItemLink
								catalogueItem
									catalogueItemState
									tradeItem
									catalogueItemChildItemLink
										catalogueItem
											catalogueItemState
											tradeItem
		
	*/	
		
		
		CatalogueItemNotificationMessageType catalogueItemNotificationMessage = objectFactory.createCatalogueItemNotificationMessageType();
		
			StandardBusinessDocumentHeader standardBusinessDocumentHeader = new StandardBusinessDocumentHeader() ;
			standardBusinessDocumentHeader.setHeaderVersion("1.0");
			DocumentIdentification documentIdentification = new DocumentIdentification();
			documentIdentification.setStandard("GS1");
			documentIdentification.setType("catalogueItemNotification");
			documentIdentification.setTypeVersion("3.1");
			documentIdentification.setInstanceIdentifier(uniqueIdentifier.toString());
			documentIdentification.setMultipleType(false);
			
			try {
				documentIdentification.setCreationDateAndTime(getXMLGregorianCalendarNow());
			} catch (DatatypeConfigurationException e1) {				
				e1.printStackTrace();
			}
			standardBusinessDocumentHeader.setDocumentIdentification(documentIdentification);

			CatalogueItemNotification catalogueNotif = catalogueItemNotificationDAO.getAllCatalogueItemNotification(Product_Export_Hdr_Id);
			if(catalogueNotif!=null) {
				Partner senderInfo = new Partner();
				PartnerIdentification senderID = new PartnerIdentification();
				senderID.setAuthority("GS1");
				senderID.setValue(catalogueNotif.getSender_and_Content_Owner());
				senderInfo.setIdentifier(senderID);
				standardBusinessDocumentHeader.getSender().add(senderInfo);
				
				Partner receiverInfo = new Partner();
				PartnerIdentification receiverID = new PartnerIdentification();
				receiverID.setAuthority("GS1");
				receiverID.setValue(catalogueNotif.getReceiver());
				receiverInfo.setIdentifier(receiverID);
				standardBusinessDocumentHeader.getReceiver().add(receiverInfo);
				
				
			}
			
			
			TransactionType transaction =  new TransactionType() ;
				EntityIdentificationType entityIdentification = new EntityIdentificationType() ;
				entityIdentification.setEntityIdentification(uniqueIdentifier.toString());
				PartyIdentificationType partyIdentificationType = new PartyIdentificationType();
				
				
				
				DocumentCommandType documentCommand = new DocumentCommandType();
					DocumentCommandHeaderType documentCommandHeader = new DocumentCommandHeaderType();					
					documentCommandHeader.setType(DocumentCommandEnumerationType.fromValue(operation));
					documentCommandHeader.setDocumentCommandIdentification(entityIdentification);
					documentCommand.setDocumentCommandHeader(documentCommandHeader);
				
					
					CatalogueItemNotificationType catalogueItemNotification = objectFactory.createCatalogueItemNotificationType();
					try {
						catalogueItemNotification.setCreationDateTime(getXMLGregorianCalendarNow());
						catalogueItemNotification.setLastUpdateDateTime(getXMLGregorianCalendarNow());
					} catch (DatatypeConfigurationException e1) {
						e1.printStackTrace();
					}
					catalogueItemNotification.setDocumentStatusCode(DocumentStatusEnumerationType.ORIGINAL);
					catalogueItemNotification.setDocumentStructureVersion("3.1");
					
					EntityIdentificationType catlogItemNotifIdentification = new EntityIdentificationType();
					catlogItemNotifIdentification.setEntityIdentification(uniqueIdentifier.toString());
					partyIdentificationType.setGln(catalogueNotif.getSender_and_Content_Owner());//(tradeItem.getLstTradeItemHierarchy().get(0).getBrand_Owner_GLN());

					catlogItemNotifIdentification.setContentOwner(partyIdentificationType);
					catalogueItemNotification.setCatalogueItemNotificationIdentification(catlogItemNotifIdentification);
					catalogueItemNotification.setIsReload(false);
					
					CatalogueItemType baseCatalogueItem = new CatalogueItemType();
					//baseCatalogueItem.setDataRecipient("9312345531165");
					//baseCatalogueItem.setSourceDataPool("9312345506705");
					CatalogueItemStateType catalogueItemState = new CatalogueItemStateType();
						catalogueItemState.setCatalogueItemStateCode(CatalogueItemStateEnumerationType.REGISTERED);
						baseCatalogueItem.setCatalogueItemState(catalogueItemState);
						TradeItemType baseTradeItem = new TradeItemType();
						
						CatalogueItemChildItemLinkType catalogueItemChildItemLink = new CatalogueItemChildItemLinkType();
						CatalogueItemType childCatalogueItem = new CatalogueItemType();
						TradeItemType childTradeItem = new TradeItemType();
						
						CatalogueItemChildItemLinkType catalogueItemCaseItemLink = new CatalogueItemChildItemLinkType();

						
						for (ProductHierarchy prodHier : lstProductHierarchy) {
							Integer Product_Id = prodHier.getProduct_Id();
							String Product_Hierarchy_id = prodHier.getProduct_Hierarchy_Id().toString();
							Integer Client_Id = gs1Export.getClient_Id();
							Integer Org_Id = gs1Export.getOrg_Id();
							Integer Product_Export_Dtl_Id = gs1Export.getLstProductExportHdr().get(0).getProduct_Export_Dtl_Id();
							String Target_Market = gs1Export.getLstProductExportHdr().get(0).getTarget_Market();
							
							
							
							List<TradeItem> lstTradeItem = new ArrayList<TradeItem>();
							TradeItem tradeItem = new TradeItem();
							//tradeItem.setLstTradeItemChildProduct(tradeItemChildProductDAO.getAllTradeItemChildProduct(Product_Id, Product_Hierarchy_id, strCOOIds));
							tradeItem.setLstTradeItemGPCAttributes(tradeItemGPCAttributesDAO.getAllTradeItemGPCAttributes(Product_Id, Product_Hierarchy_id));
							tradeItem.setLstTradeItemHierarchy(tradeItemHierarchyDAO.getAllTradeItemHierarchy(Product_Id, Product_Hierarchy_id, strCOOIds, Client_Id));
							tradeItem.setLstTradeItemInformationProviderDetails(tradeItemInformationProviderDetailsDAO.getAllTradeItemInformationProviderDetails(Product_Export_Hdr_Id));
							tradeItem.setLstTradeItemTargetMarket(tradeItemTargetMarketDAO.getAllTradeItemTargetMarket(Product_Export_Hdr_Id));
							tradeItem.setLstTradeItemUNSPSCAttributes(tradeItemUNSPSCAttributesDAO.getAllTradeItemUNSPSCAttributes(Product_Id));
							lstTradeItem.add(tradeItem);		
							gs1Export.setLstTradeItem(lstTradeItem);
							
							partyIdentificationType.setGln(catalogueNotif.getSender_and_Content_Owner());//(tradeItem.getLstTradeItemHierarchy().get(0).getBrand_Owner_GLN());
							entityIdentification.setContentOwner(partyIdentificationType );		

							boolean isLowestLevel = false;
							if(prodHier.getLevel() == gs1Export.getNumberOfLevels()) {
								isLowestLevel = true;
							}
							
							if (prodHier.getLevel()==1) {
								
								// Code for case
								baseTradeItem = getTradeItemAtALevel(Product_Export_Hdr_Id, gs1Export, strHierarchyIds, strCOOIds,
										prodHier, Product_Id, Product_Hierarchy_id, Client_Id, Org_Id,
										Product_Export_Dtl_Id, Target_Market, tradeItem, isLowestLevel);
								
							}else if (prodHier.getLevel()==2) {
								// Code for Inner_Case
								//childTradeItem.setGtin("19355456005016");
								childTradeItem = getTradeItemAtALevel(Product_Export_Hdr_Id, gs1Export, strHierarchyIds, strCOOIds,
										prodHier, Product_Id, Product_Hierarchy_id, Client_Id, Org_Id,
										Product_Export_Dtl_Id, Target_Market, tradeItem, isLowestLevel);
								catalogueItemChildItemLink.setQuantity(BigInteger.valueOf(lstProductHierarchy.get(0).getChild_Unit_Qty()));

								childCatalogueItem.setTradeItem(childTradeItem);		
								childCatalogueItem.setCatalogueItemState(catalogueItemState);
							}else if (prodHier.getLevel()==3) {
								// Code for Base
								CatalogueItemType caseCatalogueItem = new CatalogueItemType();
								TradeItemType caseTradeItem = getTradeItemAtALevel(Product_Export_Hdr_Id, gs1Export, strHierarchyIds, strCOOIds,
										prodHier, Product_Id, Product_Hierarchy_id, Client_Id, Org_Id,
										Product_Export_Dtl_Id, Target_Market, tradeItem, isLowestLevel);
								//caseTradeItem.setGtin("99355456005016");
								caseCatalogueItem.setTradeItem(caseTradeItem);
								caseCatalogueItem.setCatalogueItemState(catalogueItemState);
								catalogueItemCaseItemLink.setCatalogueItem(caseCatalogueItem);
								catalogueItemCaseItemLink.setQuantity(BigInteger.valueOf(lstProductHierarchy.get(1).getChild_Unit_Qty()));
								childCatalogueItem.getCatalogueItemChildItemLink().add(catalogueItemCaseItemLink);
							}
						}
						
						catalogueItemChildItemLink.setCatalogueItem(childCatalogueItem);	
						baseCatalogueItem.getCatalogueItemChildItemLink().add(catalogueItemChildItemLink);	
						baseCatalogueItem.setTradeItem(baseTradeItem);
					
					catalogueItemNotification.setCatalogueItem(baseCatalogueItem);
					
					
					documentCommand.getListCatalogueItemNotification().add(catalogueItemNotification);
								
				
			transaction.setDocumentCommand(documentCommand);
			transaction.setTransactionIdentification(entityIdentification);
			
			catalogueItemNotificationMessage.getTransaction().add(transaction);
			catalogueItemNotificationMessage.setStandardBusinessDocumentHeader(standardBusinessDocumentHeader); 
		
		
		JAXBElement<CatalogueItemNotificationMessageType> jaxbCatalogueItemNotificationMessage = objectFactory.createCatalogueItemNotificationMessage(catalogueItemNotificationMessage); 
		StringWriter sw = new StringWriter();

		
		try {

			
			//JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactoryCatalogueItemNotification.class);//"com.ascention.GS1.schema");
			JAXBContext jaxbContext = JAXBContext.newInstance("com.ascention.GS1.schema");
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			//jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.unece.org/cefact/namespaces/StandardBusinessDocumentHeader http://www.gs1globalregistry.net/3.1/schemas/sbdh/StandardBusinessDocumentHeader.xsd urn:gs1:gdsn:catalogue_item_notification:xsd:3 http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/CatalogueItemNotification.xsd urn:gs1:gdsn:alcohol_information:xsd:3 urn:gs1:gdsn:alcohol_information:xsd:3 http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/AlcoholInformationModule.xsd");
			//JAXBElement<AlcoholInformationModuleType> jaxbElementAlcoholInformationModule = alm;
			
			jaxbMarshaller.marshal(jaxbCatalogueItemNotificationMessage, sw);
			//jaxbMarshaller.marshal(jaxbCatalogueItemNotificationMessage, System.out);
			//sw.write(sw.toString().replace("ns38:document xsi:type\"catalogue_item_notification:catalogueItemNotification\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "catalogue_item_notification:catalogueItemNotification"));
			//sw.write(sw.toString().replace("/ns38:document", "/catalogue_item_notification:catalogueItemNotification"));
			
		
			
			// Logic to remove all the empty tags
			
			final String regex1 = "<([a-zA-Z0-9-\\:_]*)[^>]*/>";
		    final String regex2 = "<([a-zA-Z0-9-\\:_]*)[^>]*>\\s*</\\1>";

		    String xmlString = sw.toString();
		   // System.out.println(xmlString);

		    final Pattern pattern1 = Pattern.compile(regex1);
		    final Pattern pattern2 = Pattern.compile(regex2);

		    Matcher matcher1;
		    Matcher matcher2;
		    do { 
		        xmlString = xmlString.replaceAll(regex1, "").replaceAll(regex2, "");
		        matcher1 = pattern1.matcher(xmlString);
		        matcher2 = pattern2.matcher(xmlString);
		    } while (matcher1.find() || matcher2.find());


		    
		    
		    System.out.println(xmlString);
		    
			FileWriterWithEncoding fw = new FileWriterWithEncoding("C:\\Export\\Export_For_Product_Export_Hdr_Id_"+ Product_Export_Hdr_Id + ".xml",StandardCharsets.UTF_8);			
			fw.write(xmlString);
			fw.close();
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
				
		//System.out.println("GS1 Export Details : " + gs1Export.toString()); 
		return sw.toString();
	}

	private TradeItemType getTradeItemAtALevel(Integer Product_Export_Hdr_Id, GS1Export gs1Export, String strHierarchyIds,
			String strCOOIds,   ProductHierarchy prodHier, Integer Product_Id,
			String Product_Hierarchy_id, Integer Client_Id, Integer Org_Id, Integer Product_Export_Dtl_Id,
			String Target_Market, TradeItem tradeItem, boolean isLowestLevel) {
		
		
		TradeItemType baseTradeItem = new TradeItemType();
		baseTradeItem.setGtin(tradeItem.getLstTradeItemHierarchy().get(0).getGtin());
		baseTradeItem.setIsTradeItemABaseUnit(tradeItem.getLstTradeItemHierarchy().get(0).getIs_Base_Unit().equals("1") ? true : false);
		baseTradeItem.setIsTradeItemAConsumerUnit(tradeItem.getLstTradeItemHierarchy().get(0).getIs_Consumer_Unit().equals("1") ? true : false);
		baseTradeItem.setIsTradeItemADespatchUnit(tradeItem.getLstTradeItemHierarchy().get(0).getIs_Despatch_Unit().equals("1") ? true : false);
		baseTradeItem.setIsTradeItemAnInvoiceUnit(tradeItem.getLstTradeItemHierarchy().get(0).getIs_Invoice_Unit().equals("1") ? true : false);
		baseTradeItem.setIsTradeItemAnOrderableUnit(tradeItem.getLstTradeItemHierarchy().get(0).getIs_Orderable_Unit().equals("1") ? true : false);
		baseTradeItem.setIsTradeItemAService(tradeItem.getLstTradeItemHierarchy().get(0).getIs_Service().equals("1") ? true : false);
		baseTradeItem.setIsTradeItemNonphysical(tradeItem.getLstTradeItemHierarchy().get(0).getIs_Non_Physical().equals("1") ? true : false);
		baseTradeItem.setIsTradeItemRecalled(tradeItem.getLstTradeItemHierarchy().get(0).getIs_Recalled().equals("1") ? true : false);
		
		TradeItemUnitDescriptorCodeType tradeItemUnitDescriptorCode = new TradeItemUnitDescriptorCodeType();
		tradeItemUnitDescriptorCode.setValue(tradeItem.getLstTradeItemHierarchy().get(0).getProd_Unit_Desc());
		baseTradeItem.setTradeItemUnitDescriptorCode(tradeItemUnitDescriptorCode);
		
		DisplayUnitInformationType displayUnitInfo = new DisplayUnitInformationType();
		if(tradeItem.getLstTradeItemHierarchy().get(0).getDisplay_Ready_Packaging()!=null) {
			displayUnitInfo.setHasDisplayReadyPackaging(NonBinaryLogicEnumerationType.fromValue(tradeItem.getLstTradeItemHierarchy().get(0).getDisplay_Ready_Packaging()));
			baseTradeItem.setDisplayUnitInformation(displayUnitInfo);
		}
		List<HierarchyGTINCountryOrigin> lstHierarchyGTINCountryOrigin =  hierarchyGTINCountryOriginDAO.getAllGTINCountryOriginAttributesForProduct(Product_Id, Product_Hierarchy_id, strCOOIds, Client_Id, Org_Id);
		gs1Export.setLstHierarchyGTINCountryOrigin(lstHierarchyGTINCountryOrigin);
		
		
		for(HierarchyGTINCountryOrigin hierarchyGTINCountryOrigin : lstHierarchyGTINCountryOrigin) {
			AdditionalTradeItemIdentificationType additionalTradeItemIdentification = new AdditionalTradeItemIdentificationType();
			additionalTradeItemIdentification.setAdditionalTradeItemIdentificationTypeCode("SUPPLIER_ASSIGNED");
			additionalTradeItemIdentification.setValue(hierarchyGTINCountryOrigin.getAddl_Prod_Num_Value());
			baseTradeItem.getAdditionalTradeItemIdentification().add(additionalTradeItemIdentification);
		}
		
		AdditionalTradeItemIdentificationType artg_details = new AdditionalTradeItemIdentificationType();
		List<TradeItemAdditionalTradeItem> lstArtgIds = tradeItemAdditionalTradeItemDAO.getAllTradeItemAdditionalTradeItem(Product_Id, Product_Hierarchy_id);
		for(TradeItemAdditionalTradeItem eleArtgId : lstArtgIds) {
			artg_details.setAdditionalTradeItemIdentificationTypeCode("ARTG_ID");
			artg_details.setValue(eleArtgId.getARTG_ID());
			if(eleArtgId.getARTG_ID()!=null)
				baseTradeItem.getAdditionalTradeItemIdentification().add(artg_details);
		}
		
		
		PartyInRoleType partyInRoleBrand = new PartyInRoleType();
		if(tradeItem.getLstTradeItemHierarchy().get(0).getBrand_Owner_GLN()!=null)
			partyInRoleBrand.setGln(tradeItem.getLstTradeItemHierarchy().get(0).getBrand_Owner_GLN());//;
		partyInRoleBrand.setPartyAddress(tradeItem.getLstTradeItemHierarchy().get(0).getBrand_Owner_Address());
		partyInRoleBrand.setPartyName(tradeItem.getLstTradeItemHierarchy().get(0).getBrand_Owner_Name());
		
		//System.out.println(isEmptyObject(partyInRoleBrand));
		baseTradeItem.setBrandOwner(partyInRoleBrand);
		
		
		
		
		
		PartyInRoleType partyInRoleIdentification = new PartyInRoleType();
		if(tradeItem.getLstTradeItemInformationProviderDetails().get(0).getInfo_Provider_GLN()!=null)
			partyInRoleIdentification.setGln(tradeItem.getLstTradeItemInformationProviderDetails().get(0).getInfo_Provider_GLN());//(Client_Id.toString());
		if(tradeItem.getLstTradeItemInformationProviderDetails().get(0).getInfo_Provider_Name()!=null)
			partyInRoleIdentification.setPartyName(tradeItem.getLstTradeItemInformationProviderDetails().get(0).getInfo_Provider_Name());		
		if(tradeItem.getLstTradeItemInformationProviderDetails().get(0).getInfo_Provider_Address()!=null)
			partyInRoleIdentification.setPartyAddress(tradeItem.getLstTradeItemInformationProviderDetails().get(0).getInfo_Provider_Address());
		
		baseTradeItem.setInformationProviderOfTradeItem(partyInRoleIdentification);
		
		
		PartyInRoleType partyInRoleManufacturer = new PartyInRoleType();
		if(tradeItem.getLstTradeItemHierarchy().get(0).getManufacturer_GLN()!=null)
			partyInRoleManufacturer.setGln(tradeItem.getLstTradeItemHierarchy().get(0).getManufacturer_GLN());
		partyInRoleManufacturer.setPartyName(tradeItem.getLstTradeItemHierarchy().get(0).getManufacturer_Name());		
		partyInRoleManufacturer.setPartyAddress(tradeItem.getLstTradeItemHierarchy().get(0).getManufacturer_Address());		
		baseTradeItem.getManufacturerOfTradeItem().add(partyInRoleManufacturer);
		
		List<TradeItemGPCAttributes> lstGPCAttributes = tradeItemGPCAttributesDAO.getAllTradeItemGPCAttributes(Product_Id, Product_Hierarchy_id);
		
		
		GDSNTradeItemClassificationType gdsn = new GDSNTradeItemClassificationType();
		gdsn.setGpcCategoryCode(prodHier.getGpc_Brick());
		for(TradeItemGPCAttributes gpcAttribute : lstGPCAttributes) {
			GDSNTradeItemClassificationAttributeType gpcattr = new GDSNTradeItemClassificationAttributeType();
			gpcattr.setGpcAttributeTypeCode(gpcAttribute.getGpc_Attr_Type_Code());
			gpcattr.setGpcAttributeValueCode(gpcAttribute.getGpc_Attr_Value_Code());
			gdsn.getGDSNTradeItemClassificationAttribute().add(gpcattr);
		}					
		
		List<TradeItemUNSPSCAttributes> lstUNSPSCAttributes = tradeItemUNSPSCAttributesDAO.getAllTradeItemUNSPSCAttributes(Product_Id);
		for(TradeItemUNSPSCAttributes eleUNSPSCAttribute : lstUNSPSCAttributes) {
			
			AdditionalTradeItemClassificationType additionalTradeItemClassfication = new AdditionalTradeItemClassificationType();
			AdditionalTradeItemClassificationCodeListCodeType addnlTradeItemSysCode = new AdditionalTradeItemClassificationCodeListCodeType();
			addnlTradeItemSysCode.setValue(eleUNSPSCAttribute.getAdditionalTradeItemClassificationSystemCode());
			additionalTradeItemClassfication.setAdditionalTradeItemClassificationSystemCode(addnlTradeItemSysCode);
			
			AdditionalTradeItemClassificationValueType addnTradeItemClassificationValue = new AdditionalTradeItemClassificationValueType();
			addnTradeItemClassificationValue.setAdditionalTradeItemClassificationCodeDescription(eleUNSPSCAttribute.getDescription());
			addnTradeItemClassificationValue.setAdditionalTradeItemClassificationVersion(eleUNSPSCAttribute.getUnspsc_Version());
			addnTradeItemClassificationValue.setAdditionalTradeItemClassificationCodeValue(eleUNSPSCAttribute.getCommodity_Id());
			
			additionalTradeItemClassfication.getAdditionalTradeItemClassificationValue().add(addnTradeItemClassificationValue);
			
			gdsn.getAdditionalTradeItemClassification().add(additionalTradeItemClassfication);
			
		
		}
		
		baseTradeItem.setGdsnTradeItemClassification(gdsn);
		
		if(!isLowestLevel) {
			NextLowerLevelTradeItemInformationType nextlowerLevelTradeItemInfo = new NextLowerLevelTradeItemInformationType();
			nextlowerLevelTradeItemInfo.setQuantityOfChildren(BigInteger.valueOf(1)); // Hardcoded as per email from Mark on 2nd Feb 21
			nextlowerLevelTradeItemInfo.setTotalQuantityOfNextLowerLevelTradeItem(BigInteger.valueOf(tradeItemChildProductDAO.getAllTradeItemChildProduct(Product_Id, strHierarchyIds, strCOOIds,tradeItem.getLstTradeItemHierarchy().get(0).getGtin()).get(0).getChild_Unit_Qty()));
			ChildTradeItemType nextLevelTradeItem = new ChildTradeItemType();
			nextLevelTradeItem.setGtin(String.valueOf(tradeItemChildProductDAO.getAllTradeItemChildProduct(Product_Id, strHierarchyIds, strCOOIds,tradeItem.getLstTradeItemHierarchy().get(0).getGtin()).get(0).getChild_Gtin()));
			nextLevelTradeItem.setQuantityOfNextLowerLevelTradeItem(BigInteger.valueOf(tradeItemChildProductDAO.getAllTradeItemChildProduct(Product_Id, strHierarchyIds, strCOOIds,tradeItem.getLstTradeItemHierarchy().get(0).getGtin()).get(0).getChild_Unit_Qty()));
			nextlowerLevelTradeItemInfo.getChildTradeItem().add(nextLevelTradeItem);
			baseTradeItem.setNextLowerLevelTradeItemInformation(nextlowerLevelTradeItemInfo);
		}
	
		
		for (TradeItemTargetMarket targetItem : tradeItem.getLstTradeItemTargetMarket()) {
			TargetMarketType targetMarket = new TargetMarketType();
			CountryCodeType coutryCodeType = new CountryCodeType();
			coutryCodeType.setValue(targetItem.getNumeric_Id());
			targetMarket.setTargetMarketCountryCode(coutryCodeType );
			baseTradeItem.getTargetMarket().add(targetMarket);
		}
		
		
		List<TradeItemReferencedTradeItem> lstRefTradeItems = tradeItemReferencedTradeItemDAO.getAllTradeItemReferencedTradeItem(Product_Id, strHierarchyIds);
		
		for(TradeItemReferencedTradeItem eleRefTradeItem : lstRefTradeItems) {
			ReferencedTradeItemType referencedTradeItem = new ReferencedTradeItemType();
			referencedTradeItem.setGtin(eleRefTradeItem.getGtin());
			ReferencedTradeItemTypeCodeType refTradeItemTypeCode = new ReferencedTradeItemTypeCodeType();
			refTradeItemTypeCode.setValue(eleRefTradeItem.getReference_Type_Code());
			if(eleRefTradeItem.getReference_Type_Code()!=null)
				referencedTradeItem.setReferencedTradeItemTypeCode(refTradeItemTypeCode);
			baseTradeItem.getReferencedTradeItem().add(referencedTradeItem);				
		}
		
		
		
		TradeItemInformationType tradeItemInfo = new TradeItemInformationType();
		try {
			if(isLowestLevel) { // should be assigned only for BASE level - email from Mark as per 02-02-2021
				tradeItemInfo.setProductionVariantEffectiveDateTime(getXMLGregorianCalendarNow());
			}
		} catch (DatatypeConfigurationException e2) {
			e2.printStackTrace();
		}
		Description70Type productionVariantDescription = new Description70Type();
		productionVariantDescription.setLanguageCode("en");
		productionVariantDescription.setValue("ORIGINAL");
		if(isLowestLevel) { // should be assigned only for BASE level - email from Mark as per 02-02-2021
			tradeItemInfo.getProductionVariantDescription().add(productionVariantDescription);
		}
		
		ExtensionType extension = new ExtensionType();
		
		NutritionalInformationModuleType nutritionalInformationModuleType = objectFactory.createNutritionalInformationModuleType();
		
		NutritionalInformationModule nutritionalInformationModule = new NutritionalInformationModule();
		if(isLowestLevel) {
			nutritionalInformationModule.setLstNutrient_Header(nutrient_HeaderDAO.getAllNutrient_Header(Product_Id));
			nutritionalInformationModule.setLstNutrient_Information(nutrient_InformationDAO.getAllNutrient_Information(Product_Id));
			nutritionalInformationModule.setLstNutrient_Serving_Size(nutrient_Serving_SizeDAO.getAllNutrient_Serving_Size(Product_Id));
			nutritionalInformationModule.setLstNutritional_Claim(nutritional_ClaimDAO.getAllNutritional_Claim(Product_Id));
			nutritionalInformationModule.setLstServing_Size_Desc(serving_Size_DescDAO.getAllServing_Size_Desc(Product_Id));
			
			for(Nutrient_Header eleNutrientHeader : nutritionalInformationModule.getLstNutrient_Header()) {
				
				
				NutrientHeaderType nutrientHeader = new NutrientHeaderType();
				MeasurementType nutrientBasisQty = new MeasurementType();
				nutrientBasisQty.setMeasurementUnitCode(eleNutrientHeader.getNutrient_Basis_Qty_Uom());
				if(eleNutrientHeader.getNutrient_Basis_Qty()!=null) {
					nutrientBasisQty.setValue(BigDecimal.valueOf(eleNutrientHeader.getNutrient_Basis_Qty()));
					nutrientHeader.setNutrientBasisQuantity(nutrientBasisQty);	
				}
				
				
				PreparationTypeCodeType prepStateCode = new PreparationTypeCodeType();
				prepStateCode.setValue(eleNutrientHeader.getPrep_State());
				if(eleNutrientHeader.getPrep_State()!=null)
					nutrientHeader.setPreparationStateCode(prepStateCode);
				
				Description500Type nutrientBasisDesc = new Description500Type();
				nutrientBasisDesc.setLanguageCode("en");
				nutrientBasisDesc.setValue(eleNutrientHeader.getNutrient_Basis_Qty_Desc());
				if(eleNutrientHeader.getNutrient_Basis_Qty_Desc()!=null)
					nutrientHeader.getNutrientBasisQuantityDescription().add(nutrientBasisDesc);
				
				NutrientBasisQuantityTypeCodeType nutrientBasisQtyType = new NutrientBasisQuantityTypeCodeType();
				nutrientBasisQtyType.setValue(eleNutrientHeader.getNutrient_Basis_Qty_Type());
				if(eleNutrientHeader.getNutrient_Basis_Qty_Type()!=null)
					nutrientHeader.setNutrientBasisQuantityTypeCode(nutrientBasisQtyType);
				
				for(Nutrient_Serving_Size eleNutrientServingSize : nutritionalInformationModule.getLstNutrient_Serving_Size()) {
					if(eleNutrientServingSize.getNutrient_Info_Id().equals(eleNutrientHeader.getNutrient_Info_Id())) {
						MeasurementType servingSize = new MeasurementType();
						servingSize.setMeasurementUnitCode(eleNutrientServingSize.getServing_Size_Uom());
						
						if(eleNutrientServingSize.getServing_Size()!=null) {
							servingSize.setValue(BigDecimal.valueOf(eleNutrientServingSize.getServing_Size()));
							nutrientHeader.getServingSize().add(servingSize);
						}
					}
					
				}
				
				for(Nutrient_Information eleNutrientDetail : nutritionalInformationModule.getLstNutrient_Information()) {
					
					if(eleNutrientDetail.getNutrient_Info_Id().equals(eleNutrientHeader.getNutrient_Info_Id())) {
						NutrientDetailType nutrientDetail = new NutrientDetailType();
						if(eleNutrientDetail.getDaily_Value_Intake_Pct()!=null)
							nutrientDetail.setDailyValueIntakePercent(BigDecimal.valueOf(eleNutrientDetail.getDaily_Value_Intake_Pct()));
						
						
						MeasurementPrecisionCodeType dvipMeasurement = new MeasurementPrecisionCodeType();
						dvipMeasurement.setValue(eleNutrientDetail.getDVIP_Measurement_Precision());
						if(eleNutrientDetail.getDVIP_Measurement_Precision()!=null)
							nutrientDetail.setDailyValueIntakePercentMeasurementPrecisionCode(dvipMeasurement);
						
						
						MeasurementPrecisionCodeType measurementPrecisionCode = new MeasurementPrecisionCodeType();
						measurementPrecisionCode.setValue(eleNutrientDetail.getNutrient_Qty_Measurement_Precision());
						if(eleNutrientDetail.getNutrient_Qty_Measurement_Precision()!=null)
							nutrientDetail.setMeasurementPrecisionCode(measurementPrecisionCode);
						
						NutrientTypeCodeType nutrientType = new NutrientTypeCodeType();
						nutrientType.setValue(eleNutrientDetail.getNutrient_Type());
						if(eleNutrientDetail.getNutrient_Type()!=null)
							nutrientDetail.setNutrientTypeCode(nutrientType);
						
						MeasurementType qtyContained = new MeasurementType();
						qtyContained.setMeasurementUnitCode(eleNutrientDetail.getQty_Contained_Uom());
						if(eleNutrientDetail.getQty_Contained()!=null) {
							qtyContained.setValue(BigDecimal.valueOf(eleNutrientDetail.getQty_Contained()));
							nutrientDetail.getQuantityContained().add(qtyContained);
						}
						nutrientHeader.getNutrientDetail().add(nutrientDetail);
					}
					
					
				}
				for(Serving_Size_Desc eleServingSizeDesc : nutritionalInformationModule.getLstServing_Size_Desc()) {
					Description500Type servingSizeDesc = new Description500Type();
					servingSizeDesc.setLanguageCode("en");
					servingSizeDesc.setValue(eleServingSizeDesc.getServing_Size_Desc());
					if(eleServingSizeDesc.getServing_Size_Desc()!=null)
						nutrientHeader.getServingSizeDescription().add(servingSizeDesc);
				}
				
				nutritionalInformationModuleType.getNutrientHeader().add(nutrientHeader);
			}
				
			for(Nutritional_Claim eleNutritionalClaim : nutritionalInformationModule.getLstNutritional_Claim()) {
				
				Description5000Type nutrientClaim = new Description5000Type();
				nutrientClaim.setLanguageCode("en");
				nutrientClaim.setValue(eleNutritionalClaim.getNutritional_Claim());
				if(eleNutritionalClaim.getNutritional_Claim()!=null)
					nutritionalInformationModuleType.getNutritionalClaim().add(nutrientClaim);
				
			}
			
		}
	
		nutritionalInformationModule.setLstNutritional_Claim_Detail(nutritional_Claim_DetailDAO.getAllNutritional_Claim_Detail(Product_Id,Product_Hierarchy_id)); // Only for Consumer
		
		gs1Export.setNutritionalInformationModule(nutritionalInformationModule);
		
		
		for( Nutritional_Claim_Detail eleNutritionalClaimDetail : nutritionalInformationModule.getLstNutritional_Claim_Detail()) {
			NutritionalClaimDetailType nutritionalClaimDetail = new NutritionalClaimDetailType();
			NutritionalClaimNutrientElementCodeType claimNutrientEle = new NutritionalClaimNutrientElementCodeType();
			claimNutrientEle.setValue(eleNutritionalClaimDetail.getNutritional_Claim_Element());
			if(eleNutritionalClaimDetail.getNutritional_Claim_Element()!=null)
				nutritionalClaimDetail.setNutritionalClaimNutrientElementCode(claimNutrientEle);
			
			NutritionalClaimTypeCodeType nutriClaimType = new NutritionalClaimTypeCodeType();
			nutriClaimType.setValue(eleNutritionalClaimDetail.getNutritional_Claim_Type());	
			if(eleNutritionalClaimDetail.getNutritional_Claim_Type()!=null)
				nutritionalClaimDetail.setNutritionalClaimTypeCode(nutriClaimType);
			
			nutritionalInformationModuleType.getNutritionalClaimDetail().add(nutritionalClaimDetail);
			
		}
		
		extension.getAny().add(nutritionalInformationModuleType);
		
		PharmaceuticalItemInformationModuleType pharmaceuticalItemInformationModuleType = objectFactory.createPharmaceuticalItemInformationModuleType();
		
		
		List<PharmaceuticalItemInformationModule> lstPharmaItemInfo = pharmaceuticalItemInformationModuleDAO.getAllPharmaceuticalItemInformationModule(Product_Id, Product_Hierarchy_id);
		for(PharmaceuticalItemInformationModule elePharmaItemInfo : lstPharmaItemInfo) {
			
			
			PharmaceuticalItemInformationType pharmaceuticalItemInformation = new PharmaceuticalItemInformationType(); 
			DosageType dosage = new DosageType();
			Description2500Type dosageRecommendation = new Description2500Type();
			dosageRecommendation.setLanguageCode("en");
			dosageRecommendation.setValue(elePharmaItemInfo.getDosage_Recommendation());
			dosage.getDosageRecommendation().add(dosageRecommendation);
			if(elePharmaItemInfo.getDosage_Recommendation()!=null)
				pharmaceuticalItemInformation.getDosage().add(dosage);
			
			
			Description2500Type drugInteraction = new Description2500Type();
			drugInteraction.setLanguageCode("en");
			drugInteraction.setValue(elePharmaItemInfo.getDrug_Interactions());
			if(elePharmaItemInfo.getDrug_Interactions()!=null)
				pharmaceuticalItemInformation.getDrugInteractions().add(drugInteraction);
			
			pharmaceuticalItemInformationModuleType.setPharmaceuticalItemInformation(pharmaceuticalItemInformation);
		}
		
		extension.getAny().add(pharmaceuticalItemInformationModuleType);
		
		AlcoholInformationModule alcoholInformationModule = alcoholInformationModuleDAO.getAllAlcoholInformationModule(Product_Id, Product_Hierarchy_id, strCOOIds, Client_Id, Org_Id);
		if(alcoholInformationModule!=null) {
			alcoholInformationModule.setVintner(vintnerDAO.getAllVintnersForProduct(Product_Id, Product_Hierarchy_id, Client_Id, Org_Id));
			gs1Export.setAlcoholInformationModule(alcoholInformationModule);
			
			AlcoholInformationType alcType = objectFactory.createAlcoholInformationType();
			AlcoholInformationModuleType alcohol_information = objectFactory.createAlcoholInformationModuleType();
			if(alcoholInformationModule.getPercentageOfAlcoholByVolume()!=null)
				alcType.setPercentageOfAlcoholByVolume(new BigDecimal(alcoholInformationModule.getPercentageOfAlcoholByVolume()));
			XMLGregorianCalendar vintage = null;
			try { 
				vintage = DatatypeFactory.newInstance().newXMLGregorianCalendar();
				if(alcoholInformationModule.getVintage()!=null) {
					vintage.setYear(BigInteger.valueOf(alcoholInformationModule.getVintage()));
					alcType.getVintage().add(vintage);
					}
			} catch (DatatypeConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}								
			
			for(Vintner v : alcoholInformationModule.getVintner()) {
				alcType.getVintner().add(v.getVintner());
			}
			alcohol_information.setAlcoholInformation(alcType);
			extension.getAny().add(alcohol_information);
		}
		
	
		
		
		
		List<ChemicalRegulationInformationModule> lstChemicalRegulationInformationModule = chemicalRegulationInformationModuleDAO.getAllChemicalRegulationInformationModule(Product_Id);
		if(lstChemicalRegulationInformationModule!=null && lstChemicalRegulationInformationModule.size()>0) {
			gs1Export.setLstChemicalRegulationInformationModule(lstChemicalRegulationInformationModule);
			ChemicalRegulationInformationModuleType chemicalRegulationInformationModule = objectFactory.createChemicalRegulationInformationModuleType();
			ChemicalRegulationInformationType chemicalRegulationInformation = new ChemicalRegulationInformationType();
			chemicalRegulationInformation.setChemicalRegulationAgency(lstChemicalRegulationInformationModule.get(0).getChem_Regl_Agency());
			ChemicalRegulationType chemicalRegulation = new ChemicalRegulationType();
			chemicalRegulation.setChemicalRegulationName(lstChemicalRegulationInformationModule.get(0).getChem_Regl_Name());
			RegulatedChemicalType regulatedChemical = new RegulatedChemicalType();
			ChemicalIngredientPropertyInformationType chemicalIngredientPropertyInformation = new ChemicalIngredientPropertyInformationType();
			ChemicalPhysicalStateCodeType chemicalPhysicalStateCode = new ChemicalPhysicalStateCodeType();
			chemicalPhysicalStateCode.setValue(lstChemicalRegulationInformationModule.get(0).getChem_Phys_State());
			chemicalIngredientPropertyInformation.setChemicalPhysicalStateCode(chemicalPhysicalStateCode);
			regulatedChemical.getChemicalIngredientPropertyInformation().add(chemicalIngredientPropertyInformation);
			chemicalRegulation.getRegulatedChemical().add(regulatedChemical);
			chemicalRegulationInformation.getChemicalRegulation().add(chemicalRegulation);								
			chemicalRegulationInformationModule.getChemicalRegulationInformation().add(chemicalRegulationInformation);
			extension.getAny().add(chemicalRegulationInformationModule);
			
		}
		
		
		
		
		
		List<DeliveryPurchasingInformationModule> lstDeliveryPurchasingInformationModule = deliveryPurchasingInformationModuleDAO.getAllDeliveryPurchasingInformationModule(Product_Id, Product_Hierarchy_id);
		if(lstDeliveryPurchasingInformationModule!=null) {
			DeliveryPurchasingInformationType deliveryPurchInfo = new DeliveryPurchasingInformationType();
			deliveryPurchInfo.setOrderingUnitOfMeasure(lstDeliveryPurchasingInformationModule.get(0).getOrder_Uom());
			OrderableReturnableInformationType orderableReturnableInfo = new OrderableReturnableInformationType();
			orderableReturnableInfo.setIsNonSoldTradeItemReturnable(Boolean.valueOf(lstDeliveryPurchasingInformationModule.get(0).getIs_Non_Sold_Prod_Returnable()));
			deliveryPurchInfo.setOrderableReturnableInformation(orderableReturnableInfo);
			TradeItemSynchronisationDatesType tradeItemSynchronisationDates = new TradeItemSynchronisationDatesType();
			List<Dates> lstDates = datesDAO.getAllDates(Product_Hierarchy_id, Product_Export_Hdr_Id, Client_Id);
			gs1Export.setLstDates(lstDates);
			for(Dates eleDate : lstDates) {
				deliveryPurchInfo.setConsumerFirstAvailabilityDateTime(getXMLGregorianDate(eleDate.getConsumer_First_Availability_Date()));
				deliveryPurchInfo.setFirstOrderDateTime(getXMLGregorianDate(eleDate.getFirst_Order_Date()));
				deliveryPurchInfo.setStartAvailabilityDateTime(getXMLGregorianDate(eleDate.getStart_Availability_Date()));
				deliveryPurchInfo.setEndAvailabilityDateTime(getXMLGregorianDate(eleDate.getEnd_Availability_Date()));				
			}
		
			// TODO -- getProduct_Export_Dtl_Id ????????
			List<LeadTimes> lstLeadTimes = leadTimesDAO.getAllLeadTimes(Product_Id, Product_Export_Hdr_Id, Client_Id );
			gs1Export.setLstLeadTimes(lstLeadTimes);
			
			for (LeadTimes eleLeadTime : lstLeadTimes) {
			
				DistributionDetailsType distrDetails = new DistributionDetailsType();
				MeasurementType orderLeadTime = new MeasurementType();
				orderLeadTime.setMeasurementUnitCode(eleLeadTime.getOrd_Lead_Time_Uom());
				if(eleLeadTime.getOrd_Lead_Time()!=null)
					orderLeadTime.setValue(BigDecimal.valueOf(eleLeadTime.getOrd_Lead_Time()));
				if(eleLeadTime.getOrd_Lead_Time()!=null) {
					distrDetails.getOrderingLeadTime().add(orderLeadTime);
					deliveryPurchInfo.getDistributionDetails().add(distrDetails);
				}
			}
			
			
			
			
			// TODO -- getProduct_Export_Dtl_Id ????????
			List<OrderQuantity> lstOrderQuantity = orderQuantityDAO.getAllOrderQuantity(Product_Id, Product_Hierarchy_id, Product_Export_Hdr_Id, Client_Id);
			gs1Export.setLstOrderQuantity(lstOrderQuantity);
			
			for(OrderQuantity eleOrderQty : lstOrderQuantity) {
			
				deliveryPurchInfo.setOrderQuantityMinimum(BigInteger.valueOf(eleOrderQty.getOrd_Qty_Min()));
				deliveryPurchInfo.setOrderQuantityMultiple(BigInteger.valueOf(eleOrderQty.getOrd_Qty_Multiple()));
			}
			
			
			
			
			DeliveryPurchasingInformationModuleType deliveryPurchasingInformationModule = new DeliveryPurchasingInformationModuleType();								
			deliveryPurchasingInformationModule.setDeliveryPurchasingInformation(deliveryPurchInfo);
			
			extension.getAny().add(deliveryPurchasingInformationModule);
		
		}
		
		
		List<DurableGoodsCharacteristicsModule> lstDurableGoodsCharacteristicsModule = durableGoodsCharacteristicsModuleDAO.getAllDurableGoodsCharacteristicsModule(Product_Hierarchy_id);
		if(lstDurableGoodsCharacteristicsModule!=null) {
			gs1Export.setLstDurableGoodsCharacteristicsModule(lstDurableGoodsCharacteristicsModule);	
			DurableGoodsCharacteristicsModuleType durableGoodsCharacteristicsModule = new DurableGoodsCharacteristicsModuleType();
			DurableGoodsCharacteristicsType durableGoodsCharacteristics = new DurableGoodsCharacteristicsType();
			Description70Type description70 = new Description70Type();
			description70.setLanguageCode("en");
			description70.setValue(lstDurableGoodsCharacteristicsModule.get(0).getProd_Finish_Desc());
			durableGoodsCharacteristics.getTradeItemFinishDescription().add(description70);
			durableGoodsCharacteristicsModule.setDurableGoodsCharacteristics(durableGoodsCharacteristics);
			extension.getAny().add(durableGoodsCharacteristicsModule);
			
		}
		
		List<DutyFeeTaxInformationModule> lstDutyFeeTaxInformationModule = dutyFeeTaxInformationModuleDAO.getAllDutyFeeTaxInformationModule(Product_Id, Target_Market);
		
		if(lstDutyFeeTaxInformationModule!=null) {
			gs1Export.setLstDutyFeeTaxInformationModule(lstDutyFeeTaxInformationModule);
			DutyFeeTaxInformationModuleType dutyFeeTaxInformationModule = objectFactory.createDutyFeeTaxInformationModuleType();
			
			for (DutyFeeTaxInformationModule eleDutyFeeTaxInfo : lstDutyFeeTaxInformationModule ) {
				DutyFeeTaxInformationType dutyFeeTaxInformation = new DutyFeeTaxInformationType();
				ResponsibleAgencyCodeType responsibleAgencyCode = new ResponsibleAgencyCodeType();
				responsibleAgencyCode.setValue(eleDutyFeeTaxInfo.getTax_Agency_Code());
				dutyFeeTaxInformation.setDutyFeeTaxAgencyCode(responsibleAgencyCode);
				
				dutyFeeTaxInformation.setDutyFeeTaxTypeCode(eleDutyFeeTaxInfo.getTax_Type());
				Description70Type description70 = new Description70Type();
				description70.setValue(eleDutyFeeTaxInfo.getTax_Type_Desc());
				description70.setLanguageCode("en");
				dutyFeeTaxInformation.getDutyFeeTaxTypeDescription().add(description70);
				DutyFeeTaxType dutyFeeTax = new DutyFeeTaxType();
				if(eleDutyFeeTaxInfo.getTax_Rate()!=null)
					dutyFeeTax.setDutyFeeTaxRate(BigDecimal.valueOf(eleDutyFeeTaxInfo.getTax_Rate()));
				
				PartyRoleCodeType partyRoleCode = new PartyRoleCodeType();
				partyRoleCode.setValue(eleDutyFeeTaxInfo.getExempt_Party_Code());
				dutyFeeTax.getDutyFeeTaxExemptPartyRoleCode().add(partyRoleCode);
				dutyFeeTaxInformation.getDutyFeeTax().add(dutyFeeTax);
			
				dutyFeeTaxInformationModule.getDutyFeeTaxInformation().add(dutyFeeTaxInformation);
			}
			
			extension.getAny().add(dutyFeeTaxInformationModule);
		}
			
		FarmingAndProcessingInformationModule farmingAndProcessingInformationModule = farmingAndProcessingInformationModuleDAO.getAllFarmingAndProcessingInformationModule(Product_Id);
		if(farmingAndProcessingInformationModule!=null) {
			farmingAndProcessingInformationModule.setLstGrowMethod(growMethodDAO.getAllGrow_Method(Product_Id));
			
			FarmingAndProcessingInformationModuleType farmingAndProcessingInformationModuleType = new FarmingAndProcessingInformationModuleType(); 
			FarmingAndProcessingInformationType farmingAndProcessingInformation = new FarmingAndProcessingInformationType();
			LevelOfContainmentCodeType levelOfContainmentCode = new LevelOfContainmentCodeType();
			levelOfContainmentCode.setValue(farmingAndProcessingInformationModule.getGm_Declaration_Code());
			farmingAndProcessingInformation.setGeneticallyModifiedDeclarationCode(levelOfContainmentCode);
			if(farmingAndProcessingInformationModule.getIrradiated_Code()!=null)								
				farmingAndProcessingInformation.setIrradiatedCode(NonBinaryLogicEnumerationType.fromValue(farmingAndProcessingInformationModule.getIrradiated_Code()));
			
			for(Grow_Method eleGrowMethod: farmingAndProcessingInformationModule.getLstGrowMethod()) {
				GrowingMethodCodeType growingMethodCode = new GrowingMethodCodeType();
				growingMethodCode.setValue(eleGrowMethod.getGrow_Method());
				farmingAndProcessingInformation.getGrowingMethodCode().add(growingMethodCode);	
			}									
			farmingAndProcessingInformationModuleType.setTradeItemFarmingAndProcessing(farmingAndProcessingInformation);									
			gs1Export.setFarmingAndProcessingInformationModule(farmingAndProcessingInformationModule);
			extension.getAny().add(farmingAndProcessingInformationModuleType);
		}
		
		MarketingInformationModule marketingInformationModule = marketingInformationModuleDAO.getAllMarketingInformationModule(Product_Id, Product_Hierarchy_id);
		if(marketingInformationModule!=null) {
			marketingInformationModule.setLstPA_Feature_Benefit(pa_Feature_BenefitDAO.getAllPA_Feature_Benefit(Product_Id));
			
			MarketingInformationModuleType marketingInformationModuleType = objectFactory.createMarketingInformationModuleType();
			MarketingInformationType marketingInformation = new MarketingInformationType();
			
			for (PA_Feature_Benefit eleFeatureBen: marketingInformationModule.getLstPA_Feature_Benefit()) {
				FormattedDescription250Type e = new FormattedDescription250Type();
				e.setLanguageCode("en");
				e.setValue(eleFeatureBen.getFeature_Benefit());
				marketingInformation.getTradeItemFeatureBenefit().add(e);											
			}
			
			CodeType featureCodeRef = new CodeType();
			featureCodeRef.setValue(marketingInformationModule.getFeature_Code_Ref());
			marketingInformation.getTradeItemFeatureCodeReference().add(featureCodeRef);
			
			FormattedDescription4000Type formattedDescription4000 = new FormattedDescription4000Type();
			formattedDescription4000.setLanguageCode("en");
			formattedDescription4000.setValue(marketingInformationModule.getProd_Marketing_Msg());
			marketingInformation.getTradeItemMarketingMessage().add(formattedDescription4000);
			
			SeasonType season = new SeasonType();
			Description70Type Description70 = new Description70Type();
			Description70.setLanguageCode("en");
			Description70.setValue(marketingInformationModule.getSeason_Name());									
			season.getSeasonName().add(Description70);
			

			season.setSeasonalAvailabilityEndDateTime(getXMLGregorianDate(marketingInformationModule.getSeasonal_Availability_End_Date()));
			season.setSeasonalAvailabilityStartDateTime(getXMLGregorianDate(marketingInformationModule.getSeasonal_Availability_Start_Date()));
			marketingInformation.getSeason().add(season);
			
			TargetConsumerType targetConsumer = new TargetConsumerType();
			TargetConsumerGenderCodeType targetConsumerGenderCode = new TargetConsumerGenderCodeType();
			targetConsumerGenderCode.setValue(marketingInformationModule.getTarget_Consumer_Gender());
			targetConsumer.setTargetConsumerGender(targetConsumerGenderCode);
			TargetConsumerAgeGroupCodeType targetConsumerAgeGroupCode = new TargetConsumerAgeGroupCodeType();
			if(marketingInformationModule.getTarget_Consumer_Age_Group_Code()!=null) {
				targetConsumerAgeGroupCode.setValue(marketingInformationModule.getTarget_Consumer_Age_Group_Code());									
				targetConsumer.getTargetConsumerAgeGroupCode().add(targetConsumerAgeGroupCode);									
				
			}
			
			marketingInformation.setTargetConsumer(targetConsumer);
			
			marketingInformationModuleType.setMarketingInformation(marketingInformation);
			gs1Export.setMarketingInformationModule(marketingInformationModule);
			extension.getAny().add(marketingInformationModuleType);
		}
		
		
		PackagingInformationModule packagingInformationModule = new PackagingInformationModule();
		packagingInformationModule.setLstPackaging(packagingDAO.getAllPackaging(Product_Id, Product_Hierarchy_id));
		packagingInformationModule.setLstPackaging_Deposit(packaging_DepositDAO.getAllPackaging_Deposit(Product_Id, Product_Hierarchy_id));
		packagingInformationModule.setLstPackaging_Function(packaging_FunctionDAO.getAllPackaging_Function(Product_Id, Product_Hierarchy_id));
		packagingInformationModule.setLstPackaging_Material(packaging_MaterialDAO.getAllPackaging_Material(Product_Id, Product_Hierarchy_id));
		packagingInformationModule.setLstPackaging_Recycle(packaging_RecycleDAO.getAllPackaging_Recycle(Product_Id, Product_Hierarchy_id));
		packagingInformationModule.setLstPackaging_Recycle_Scheme(packaging_Recycle_SchemeDAO.getAllPackaging_Recycle_Scheme(Product_Id, Product_Hierarchy_id));
		packagingInformationModule.setLstPackaging_Sustainability(packaging_SustainabilityDAO.getAllPackaging_Sustainability(Product_Id, Product_Hierarchy_id));
		gs1Export.setPackagingInformationModule(packagingInformationModule);
										
		PackagingInformationModuleType packagingInformationModuleType = objectFactory.createPackagingInformationModuleType(); 
		
		
		for(Packaging elePack : packagingInformationModule.getLstPackaging()) {
			
		
			
			
			PackagingType packaging = new PackagingType();
			packaging.setIsRadioFrequencyIDOnPackaging(Boolean.valueOf(elePack.getRfId_On_Packaging()));
			PackageTypeCodeType packageTypeCode = new PackageTypeCodeType();
			packageTypeCode.setValue(elePack.getPackaging_Type_Code());									
			packaging.setPackagingTypeCode(packageTypeCode);
			
			Description200Type description200 = new Description200Type();
			description200.setLanguageCode("en");
			description200.setValue(elePack.getPackaging_Type_Desc());
			if(elePack.getPackaging_Type_Desc()!=null)
				packaging.getPackagingTypeDescription().add(description200);
		
			
			PlatformTypeCodeType platformType = new PlatformTypeCodeType();
			if(elePack.getPlatform_Type()!=null) {
				platformType.setValue(elePack.getPlatform_Type());
				packaging.setPlatformTypeCode(platformType);
			}
			
			
			Description200Type shippingContQtyDesc = new Description200Type();
			shippingContQtyDesc.setLanguageCode("en");
			shippingContQtyDesc.setValue(elePack.getShipping_Container_Qty_Desc());
			if(elePack.getShipping_Container_Qty_Desc()!=null)
				packaging.getShippingContainerQuantityDescription().add(shippingContQtyDesc);
			
			
			for(Packaging_Function elePackageFunc : packagingInformationModule.getLstPackaging_Function()) {
				PackagingFunctionCodeType packagingFunctionCode = new PackagingFunctionCodeType();
				packagingFunctionCode.setValue(elePackageFunc.getPackaging_Function_Code());
				packaging.getPackagingFunctionCode().add(packagingFunctionCode);										
			}
			
			
			for(Packaging_Deposit elePackDep: packagingInformationModule.getLstPackaging_Deposit()) {
				PackageDepositType packageDeposit = new PackageDepositType(); 
				
				AmountType amt = new AmountType();
				amt.setCurrencyCode(elePackDep.getPackage_Deposit_Amnt_Curr());
				amt.setValue(elePackDep.getPackage_Deposit_Amnt());
				if(elePackDep.getPackage_Deposit_Amnt()!=null)
					packageDeposit.setReturnablePackageDepositAmount(amt);
				
				IdentifierType id = new IdentifierType();
				id.setValue(elePackDep.getPackage_Deposit_Id());
				if(elePackDep.getPackage_Deposit_Id()!=null)
					packageDeposit.setReturnablePackageDepositIdentification(id);
				
				TargetMarketType depRegion = new TargetMarketType();
				CountryCodeType cntryCode = new CountryCodeType();
				cntryCode.setValue(elePackDep.getNumeric_Id());
				if(elePackDep.getNumeric_Id()!=null)
					depRegion.setTargetMarketCountryCode(cntryCode);	
				CountrySubdivisionCodeType subDiv = new CountrySubdivisionCodeType();
				subDiv.setValue(elePackDep.getPackage_Deposit_Subdivision());
				if(elePackDep.getPackage_Deposit_Subdivision()!=null)
					depRegion.setTargetMarketSubdivisionCode(subDiv); // Check
				
				if(depRegion!=null)
					packageDeposit.getReturnablePackageDepositRegion().add(depRegion);
				
				
				if(elePackDep.getPackage_Deposit_End_Date()!=null) {
					String strEndDate = elePackDep.getPackage_Deposit_End_Date();												
					packageDeposit.setDepositValueEffectiveDateTime(getXMLGregorianDate(strEndDate));
				}
				  
					
				
			}
			
			for (Packaging_Material elePackMtrl: packagingInformationModule.getLstPackaging_Material()) {
				PackagingMaterialType packagingMaterial = new PackagingMaterialType();
				
				PackagingMaterialTypeCodeType packagingMaterialTypeCode = new PackagingMaterialTypeCodeType();
				packagingMaterialTypeCode.setValue(elePackMtrl.getPack_Mat_Type());
				packagingMaterial.setPackagingMaterialTypeCode(packagingMaterialTypeCode);
				
				MeasurementType measurement = new MeasurementType();
				measurement.setMeasurementUnitCode(elePackMtrl.getPack_Mat_Comp_Qty_Uom());
				if(elePackMtrl.getPack_Mat_Comp_Qty()!=null)
					measurement.setValue(BigDecimal.valueOf(elePackMtrl.getPack_Mat_Comp_Qty()));
				packagingMaterial.getPackagingMaterialCompositionQuantity().add(measurement);
				
				packaging.getPackagingMaterial().add(packagingMaterial);
			}
			
			
			for (Packaging_Recycle elePackRecycle : packagingInformationModule.getLstPackaging_Recycle()) {
				PackagingRecyclingProcessTypeCodeType packagingRecyclingProcessTypeCode = new PackagingRecyclingProcessTypeCodeType();
				packagingRecyclingProcessTypeCode.setValue(elePackRecycle.getPackaging_Recycle_Process());
				packaging.getPackagingRecyclingProcessTypeCode().add(packagingRecyclingProcessTypeCode);
			}
			
			for (Packaging_Recycle_Scheme elePackRecycleScheme : packagingInformationModule.getLstPackaging_Recycle_Scheme()) {
				PackagingRecyclingSchemeCodeType packagingRecyclingSchemeCode = new PackagingRecyclingSchemeCodeType();
				packagingRecyclingSchemeCode.setValue(elePackRecycleScheme.getPackaging_Recycle_Scheme());
				packaging.getPackagingRecyclingSchemeCode().add(packagingRecyclingSchemeCode);
				
			}
			
			for (Packaging_Sustainability elePackSust :  packagingInformationModule.getLstPackaging_Sustainability())
			{
				SustainabilityFeatureCodeType sustainabilityFeatureCode = new SustainabilityFeatureCodeType();
				sustainabilityFeatureCode.setValue(elePackSust.getPackaging_Sustainability_Feature());
				packaging.getPackagingSustainabilityFeatureCode().add(sustainabilityFeatureCode);
			}
			
			for(Packaging_Deposit elePackDepo : packagingInformationModule.getLstPackaging_Deposit()) {
			
				PackageDepositType packageDeposit = new PackageDepositType();
				AmountType depAmt = new AmountType();
				depAmt.setCurrencyCode(elePackDepo.getPackage_Deposit_Amnt_Curr());
				depAmt.setValue(elePackDepo.getPackage_Deposit_Amnt());
				packageDeposit.setReturnablePackageDepositAmount(depAmt);
				
				packageDeposit.setDepositValueEndDateTime(getXMLGregorianDate(elePackDepo.getPackage_Deposit_End_Date()));
				
				IdentifierType depoIdentification = new IdentifierType();
				depoIdentification.setValue(elePackDepo.getPackage_Deposit_Id());
				packageDeposit.setReturnablePackageDepositIdentification(depoIdentification);
				
				TargetMarketType regionDepo = new TargetMarketType();
				CountryCodeType countryCode = new CountryCodeType();
				countryCode.setValue(elePackDepo.getNumeric_Id());
				regionDepo.setTargetMarketCountryCode(countryCode);
				CountrySubdivisionCodeType subDiv = new CountrySubdivisionCodeType();
				subDiv.setValue(elePackDepo.getPackage_Deposit_Subdivision());
				regionDepo.setTargetMarketSubdivisionCode(subDiv);
				packageDeposit.getReturnablePackageDepositRegion().add(regionDepo);
				
				packaging.getPackageDeposit().add(packageDeposit);
				
			}
			
			
			packagingInformationModuleType.getPackaging().add(packaging);
			
		}
		extension.getAny().add(packagingInformationModuleType);

		List<Packaging_Marking> lstPackaging_Marking = packaging_MarkingDAO.getAllPackaging_Marking(Product_Id, Product_Hierarchy_id);
		List<Packaging_Date> lstPackaging_Date = packaging_DateDAO.getAllPackaging_Date(Product_Id, Product_Hierarchy_id);
		List<Packaging_Returnable> lstPackaging_Returnable = packaging_ReturnableDAO.getAllPackaging_Returnable(Product_Id, Product_Hierarchy_id);
		PackagingMarkingModuleType packagingMarkingModuleType = objectFactory.createPackagingMarkingModuleType();
		PackagingMarkingModule packagingMarkingModule = new PackagingMarkingModule();
		packagingMarkingModule.setLstPackaging_Marking(lstPackaging_Marking);
		packagingMarkingModule.setLstPackaging_Date(lstPackaging_Date);
		packagingMarkingModule.setLstPackaging_Returnable(lstPackaging_Returnable);
		gs1Export.setPackagingMarkingModule(packagingMarkingModule);
		
		PackagingMarkingType packagingMarking = new PackagingMarkingType();
		for (Packaging_Marking elePackagingMarking : lstPackaging_Marking) {
			packagingMarking.setHasBatchNumber(Boolean.valueOf(elePackagingMarking.getBatch_Num_Reqd()));
			
			Description5000Type warningDesc = new Description5000Type();
			warningDesc.setLanguageCode("en");
			warningDesc.setValue(elePackagingMarking.getWarning_Copy_Desc());
			packagingMarking.getWarningCopyDescription().add(warningDesc);
			
			PackagingMarkedLabelAccreditationCodeType packagingMarkedLabelAccreditationCode = new PackagingMarkedLabelAccreditationCodeType();
			packagingMarkedLabelAccreditationCode.setValue(elePackagingMarking.getPackaging_Label_Accreditation());
			packagingMarking.getPackagingMarkedLabelAccreditationCode().add(packagingMarkedLabelAccreditationCode);
			
		}
		
		
		for (Packaging_Date elePackagingDate : lstPackaging_Date) {
			PackagingDateType packagingDate = new PackagingDateType();
			TradeItemDateOnPackagingTypeCodeType tradeItemDateOnPackagingTypeCode = new TradeItemDateOnPackagingTypeCodeType();
			tradeItemDateOnPackagingTypeCode.setValue(elePackagingDate.getPackaging_Date_Type());
			packagingDate.setTradeItemDateOnPackagingTypeCode(tradeItemDateOnPackagingTypeCode);
			packagingMarking.getPackagingDate().add(packagingDate);									
		}
		
		for (Packaging_Returnable elePackaging_Returnable : lstPackaging_Returnable) {
			if(elePackaging_Returnable.getPackaging_Marked_Recyclable_Scheme()!=null)
				packagingMarking.getPackagingMarkedRecyclableScheme().add(elePackaging_Returnable.getPackaging_Marked_Recyclable_Scheme());
			if(elePackaging_Returnable.getPackaging_Marked_Returnable()!=null)
				packagingMarking.setIsPackagingMarkedReturnable(Boolean.valueOf(elePackaging_Returnable.getPackaging_Marked_Returnable()));
			if(elePackaging_Returnable.getIs_Price_On_Pack()!=null)
				packagingMarking.setIsPriceOnPack(Boolean.valueOf(elePackaging_Returnable.getIs_Price_On_Pack()));
			if(elePackaging_Returnable.getMarked_As_Recyclable()!=null)
				packagingMarking.setIsTradeItemMarkedAsRecyclable(Boolean.valueOf(elePackaging_Returnable.getMarked_As_Recyclable()));									
		}								
		if(packagingMarking!=null) {
			packagingMarkingModuleType.setPackagingMarking(packagingMarking);								
			extension.getAny().add(packagingMarkingModuleType);
		}
		PlaceOfItemActivityModule placeOfItemActivityModule = new PlaceOfItemActivityModule();
		placeOfItemActivityModule.setLstImport_Classification(import_ClassificationDAO.getAllImport_Classification(Product_Id));
		placeOfItemActivityModule.setLstPlaceOfProductActivity(placeOfProductActivityDAO.getAllPlaceOfProductActivity(Product_Id, Product_Hierarchy_id, strCOOIds));
		gs1Export.setPlaceOfItemActivityModule(placeOfItemActivityModule);

		PlaceOfItemActivityModuleType placeOfItemActivityModuleType = objectFactory.createPlaceOfItemActivityModuleType();
		PlaceOfProductActivityType placeOfProductActivity = new PlaceOfProductActivityType();
		
		for(Import_Classification eleImportClassification : placeOfItemActivityModule.getLstImport_Classification()) {
			ImportClassificationType importClassification = new ImportClassificationType();
			importClassification.setImportClassificationValue(eleImportClassification.getImport_Classification_Value());
			ImportClassificationTypeCodeType importClassificationTypeCode = new ImportClassificationTypeCodeType();
			importClassificationTypeCode.setValue(eleImportClassification.getImport_Classification_Type());
			importClassification.setImportClassificationTypeCode(importClassificationTypeCode);
			placeOfItemActivityModuleType.getImportClassification().add(importClassification);
			
			Description500Type prvnStmt = new Description500Type();
			prvnStmt.setLanguageCode("en");
			prvnStmt.setValue(eleImportClassification.getProvenance_Statement());	
			if(eleImportClassification.getProvenance_Statement()!=null)
				placeOfProductActivity.getProvenanceStatement().add(prvnStmt);
			
			ProductActivityDetailsType productActivityDetails = new ProductActivityDetailsType();
			ProductActivityTypeCodeType productActivityTypeCode = new ProductActivityTypeCodeType();
			productActivityTypeCode.setValue(eleImportClassification.getProd_Activity_Type());
			if(eleImportClassification.getProd_Activity_Type()!=null)
				productActivityDetails.setProductActivityTypeCode(productActivityTypeCode);
			
			Description500Type regDesc = new Description500Type();
			regDesc.setLanguageCode("en");
			regDesc.setValue(eleImportClassification.getProd_Activity_Region_Desc());
			if(eleImportClassification.getProd_Activity_Region_Desc()!=null)
				productActivityDetails.getProductActivityRegionDescription().add(regDesc);
			
			if(productActivityDetails!=null)
				placeOfProductActivity.getProductActivityDetails().add(productActivityDetails);
			
		}
		
		for (PlaceOfProductActivity elePlaceofProductActivity : placeOfItemActivityModule.getLstPlaceOfProductActivity()) {
			Description500Type cooStmt = new Description500Type();
			cooStmt.setLanguageCode("en");
			cooStmt.setValue(elePlaceofProductActivity.getCntry_Origin_Statement());
			if(elePlaceofProductActivity.getCntry_Origin_Statement()!=null)
				placeOfProductActivity.getCountryOfOriginStatement().add(cooStmt);
			
			
			CountryType cntry = new CountryType();
			CountryCodeType cntryCode = new CountryCodeType();
			cntryCode.setValue(elePlaceofProductActivity.getNumeric_Id());
			if(elePlaceofProductActivity.getNumeric_Id()!=null)
				cntry.setCountryCode(cntryCode);
			CountrySubdivisionCodeType subDivcode = new CountrySubdivisionCodeType();
			subDivcode.setValue(elePlaceofProductActivity.getState_Origin());
			if(elePlaceofProductActivity.getState_Origin()!=null)
				cntry.getCountrySubdivisionCode().add(subDivcode);
			
			if(cntry!=null)
				placeOfProductActivity.getCountryOfOrigin().add(cntry);
												
		}
			
		placeOfItemActivityModuleType.setPlaceOfProductActivity(placeOfProductActivity);
		extension.getAny().add(placeOfItemActivityModuleType);
		
		List<ProductCharacteristicsModule> lstProductCharacteristicsModule = productCharacteristicsModuleDAO.getAllProductCharacteristicsModule(Product_Id);
		gs1Export.setLstProductCharacteristicsModule(lstProductCharacteristicsModule);
		
		ProductCharacteristicsModuleType productCharacteristicsModule = objectFactory.createProductCharacteristicsModuleType();
		
		for (ProductCharacteristicsModule eleProductCharacteristicsModule : lstProductCharacteristicsModule) {
			ProductCharacteristicsType productCharacteristics = new ProductCharacteristicsType();
			ProductCharacteristicCodeType productCharacteristicCode = new ProductCharacteristicCodeType();
			productCharacteristicCode.setValue(eleProductCharacteristicsModule.getProd_Characteristic_Code());
			productCharacteristics.setProductCharacteristicCode(productCharacteristicCode);
			
			Description5000Type prodCharValDesc = new Description5000Type();
			prodCharValDesc.setLanguageCode("en");
			prodCharValDesc.setValue(eleProductCharacteristicsModule.getProd_Characteristic_Desc());
			if(eleProductCharacteristicsModule.getProd_Characteristic_Desc()!=null)
				productCharacteristics.getProductCharacteristicValueDescription().add(prodCharValDesc);
			
			
			if(eleProductCharacteristicsModule.getProd_Characteristic_Value_Boolean()!=null)
				productCharacteristics.getProductCharacteristicValueCode().add(String.valueOf(eleProductCharacteristicsModule.getProd_Characteristic_Value_Boolean()));
			
			productCharacteristicsModule.getProductCharacteristics().add(productCharacteristics);
		}
		
		extension.getAny().add(productCharacteristicsModule);
		
		if(isLowestLevel) {
			ReferencedFileDetailInformationModule referencedFileDetailInformationModule = new ReferencedFileDetailInformationModule();
			referencedFileDetailInformationModule.setLstReferencedFileHeader(referencedHeaderDAO.getAllReferencedFileDetailInformationModule(Product_Id));
			gs1Export.setReferencedFileDetailInformationModule(referencedFileDetailInformationModule);
			
			
			
			ReferencedFileDetailInformationModuleType referencedFileDetailInformationModuleType = objectFactory.createReferencedFileDetailInformationModuleType();
			
			for (ReferencedFileHeader eleReferenceFileHeader : referencedFileDetailInformationModule.getLstReferencedFileHeader()) {
				ReferencedFileHeaderType referencedFileHeader = new ReferencedFileHeaderType();
				referencedFileHeader.setFileFormatName(eleReferenceFileHeader.getFile_Format());
				referencedFileHeader.setFileEffectiveStartDateTime(getXMLGregorianDate(eleReferenceFileHeader.getEffective_Start_Date()));
				referencedFileHeader.setFileName(eleReferenceFileHeader.getFilename());
				referencedFileHeader.setUniformResourceIdentifier(eleReferenceFileHeader.getUrl());
				
				ReferencedFileTypeCodeType referencedFileTypeCode = new ReferencedFileTypeCodeType();
				referencedFileTypeCode.setValue(eleReferenceFileHeader.getFile_Type_Code());
				referencedFileHeader.setReferencedFileTypeCode(referencedFileTypeCode);	
				referencedFileDetailInformationModuleType.getReferencedFileHeader().add(referencedFileHeader);
			}
			extension.getAny().add(referencedFileDetailInformationModuleType);
		}
		SalesInformationModule salesInformationModule = new SalesInformationModule();
		salesInformationModule.setLstSelling_Price_Comparison(selling_Price_ComparisonDAO.getAllSelling_Price_Comparison(Product_Id, Product_Hierarchy_id));
		salesInformationModule.setLstSelling_Uom(selling_UomDAO.getAllSelling_Uom(Product_Id));		
		salesInformationModule.setLstSuggested_Price(suggested_PriceDAO.getAllSuggested_Price(Product_Id, Product_Hierarchy_id,  Client_Id,  Product_Export_Hdr_Id));
		gs1Export.setSalesInformationModule(salesInformationModule);
		
		
		
		SalesInformationModuleType salesInformationModuleType = objectFactory.createSalesInformationModuleType();
		SalesInformationType salesInformation = new SalesInformationType();
		for(Selling_Price_Comparison eleSellingPriceComp : salesInformationModule.getLstSelling_Price_Comparison()) {
			
			PriceComparisonContentTypeCodeType priceComparisonContentTypeCode = new PriceComparisonContentTypeCodeType();
			priceComparisonContentTypeCode.setValue(eleSellingPriceComp.getPrice_Comparison_Content_Type());
			if(eleSellingPriceComp.getPrice_Comparison_Content_Type()!=null)
				salesInformation.setPriceComparisonContentTypeCode(priceComparisonContentTypeCode);									
			
			MeasurementType msrType = new MeasurementType();
			msrType.setMeasurementUnitCode(eleSellingPriceComp.getPrice_Comparison_Measurement_Uom());
			if(eleSellingPriceComp.getPrice_Comparison_Measurement()!=null) {
				msrType.setValue(BigDecimal.valueOf(eleSellingPriceComp.getPrice_Comparison_Measurement()));
				salesInformation.getPriceComparisonMeasurement().add(msrType);
			}
		}
		
		for (Selling_Uom eleSellingUOM : salesInformationModule.getLstSelling_Uom()) {
			salesInformation.setSellingUnitOfMeasure(eleSellingUOM.getSelling_Uom());
		}
		
		if(salesInformation!=null)
			salesInformationModuleType.setSalesInformation(salesInformation);
		
		TradeItemPriceInformationType tradeItemPriceInformation = new TradeItemPriceInformationType();
		for ( Suggested_Price eleSuggestedPrice : salesInformationModule.getLstSuggested_Price()) {
			
			TradeItemPriceType tradeItemPrice = new TradeItemPriceType();
			
			AmountType amt = new AmountType();
			amt.setCurrencyCode(eleSuggestedPrice.getSuggested_Price_Curr());
			amt.setValue(eleSuggestedPrice.getSuggested_Price());
			if(eleSuggestedPrice.getSuggested_Price()!=null)
				tradeItemPrice.setTradeItemPrice(amt);
			
			QuantityType qty = new QuantityType();
			qty.setMeasurementUnitCode(eleSuggestedPrice.getSuggested_Price_Basis_Qty_Uom());
			if(eleSuggestedPrice.getSuggested_Price_Basis_Qty()!=null) {
				qty.setValue(BigDecimal.valueOf(eleSuggestedPrice.getSuggested_Price_Basis_Qty()));
				tradeItemPrice.setPriceBasisQuantity(qty);
			}
			
			
			tradeItemPrice.setPriceEffectiveStartDate(getXMLGregorianDate(eleSuggestedPrice.getSuggested_Price_Start_Date()));
			tradeItemPrice.setPriceEffectiveEndDate(getXMLGregorianDate(eleSuggestedPrice.getSuggested_Price_End_Date()));		
			
			tradeItemPriceInformation.getSuggestedRetailPrice().add(tradeItemPrice);
		}
		if(tradeItemPriceInformation!=null)
			salesInformationModuleType.setTradeItemPriceInformation(tradeItemPriceInformation);
		if(salesInformationModuleType!=null)
			extension.getAny().add(salesInformationModuleType);

		if(isLowestLevel) {
			gs1Export.setLstSecurityTagInformationModule(securityTagInformationModuleDAO.getAllSecurityTagInformationModule(Product_Id, Product_Hierarchy_id));
			SecurityTagInformationModuleType securityTagInformationModuleType = objectFactory.createSecurityTagInformationModuleType();
			for(SecurityTagInformationModule eleSecTagInfo : gs1Export.getLstSecurityTagInformationModule()) {
				SecurityTagInformationType securityTagInformation = new SecurityTagInformationType();
				SecurityTagTypeCodeType securityTagTypeCode = new SecurityTagTypeCodeType();
				securityTagTypeCode.setValue(eleSecTagInfo.getSecurity_Tag_Type());
				if(eleSecTagInfo.getSecurity_Tag_Type()!=null)
					securityTagInformation.setSecurityTagTypeCode(securityTagTypeCode);
				SecurityTagLocationCodeType securityTagLocationCode = new SecurityTagLocationCodeType();
				securityTagLocationCode.setValue(eleSecTagInfo.getSecurity_Tag_Loc());
				if(eleSecTagInfo.getSecurity_Tag_Loc()!=null)
					securityTagInformation.setSecurityTagLocationCode(securityTagLocationCode);
				if(securityTagInformation!=null)
					securityTagInformationModuleType.getSecurityTagInformation().add(securityTagInformation);
			}
			if(securityTagInformationModuleType!=null)
				extension.getAny().add(securityTagInformationModuleType);
		}
		
		
		TradeItemDescriptionModule tradeItemDescriptionModule = new TradeItemDescriptionModule();
		tradeItemDescriptionModule.setLstTradeItemDescriptionInformation(tradeItemDescriptionInformationDAO.getAllTradeItemDescriptionInformation(Product_Id, Product_Hierarchy_id));
		tradeItemDescriptionModule.setLstTradeItemVariant_Desc(tradeItemVariant_DescDAO.getAllTradeItemVariant_Desc(Product_Id));
		gs1Export.setTradeItemDescriptionModule(tradeItemDescriptionModule);
		
		TradeItemDescriptionModuleType tradeItemDescriptionModuleType = objectFactory.createTradeItemDescriptionModuleType();
		TradeItemDescriptionInformationType tradeItemDescriptionInformation = new TradeItemDescriptionInformationType();
		
		for (TradeItemDescriptionInformation eleTradeItemDesc : tradeItemDescriptionModule.getLstTradeItemDescriptionInformation()) {
			
			Description500Type addnTradeItemDesc = new Description500Type();
			addnTradeItemDesc.setLanguageCode("en");
			addnTradeItemDesc.setValue(eleTradeItemDesc.getAddl_Prod_Desc());
			tradeItemDescriptionInformation.getAdditionalTradeItemDescription().add(addnTradeItemDesc);
			
			ColourType color = new ColourType();
			ColourCodeType colorcode= new ColourCodeType();
			colorcode.setValue(eleTradeItemDesc.getColour_Code());
			colorcode.setColourCodeListCode(eleTradeItemDesc.getColour_Code_Provider());
			color.setColourCode(colorcode);
			Description80Type colorDesc = new Description80Type();
			colorDesc.setLanguageCode("en");
			colorDesc.setValue(eleTradeItemDesc.getColour_Desc());
			color.getColourDescription().add(colorDesc);
			tradeItemDescriptionInformation.getColour().add(color);
			
			BrandNameInformationType brandNameInfo = new BrandNameInformationType();
			brandNameInfo.setBrandName(eleTradeItemDesc.getBrand_Name());
			brandNameInfo.setSubBrand(eleTradeItemDesc.getSub_Brand_Name());
			tradeItemDescriptionInformation.setBrandNameInformation(brandNameInfo);
			
			CodeType groupIdCode = new CodeType();
			groupIdCode.setCodeDescription(eleTradeItemDesc.getProd_Group_Code_Desc());
			groupIdCode.setValue(eleTradeItemDesc.getProd_Group_Code());
			
			tradeItemDescriptionInformation.getTradeItemGroupIdentificationCodeReference().add(groupIdCode);
			
			Description35Type shortDesc = new Description35Type();
			shortDesc.setLanguageCode("en");
			shortDesc.setValue(eleTradeItemDesc.getShort_Desc());
			tradeItemDescriptionInformation.getDescriptionShort().add(shortDesc);
			
			Description35Type funcName = new Description35Type();
			funcName.setLanguageCode("en");
			funcName.setValue(eleTradeItemDesc.getFunctional_Name());
			tradeItemDescriptionInformation.getFunctionalName().add(funcName);
			
			Description500Type lblDesc = new Description500Type();
			lblDesc.setLanguageCode("en");
			lblDesc.setValue(eleTradeItemDesc.getLabel_Desc());
			tradeItemDescriptionInformation.getLabelDescription().add(lblDesc);
			
			Description200Type tradeItemDescription = new Description200Type();
			tradeItemDescription.setLanguageCode("en");
			tradeItemDescription.setValue(eleTradeItemDesc.getTradeItemDescription());
			if(eleTradeItemDesc.getTradeItemDescription()!=null)
				tradeItemDescriptionInformation.getTradeItemDescription().add(tradeItemDescription);
			
		}
		for(TradeItemVariant_Desc eleVariantDesc : tradeItemDescriptionModule.getLstTradeItemVariant_Desc()) {
			Description500Type varDesc = new Description500Type();
			varDesc.setLanguageCode("en");
			varDesc.setValue(eleVariantDesc.getVariant_Desc());
			tradeItemDescriptionInformation.getVariantDescription().add(varDesc);	
		}
		tradeItemDescriptionModuleType.setTradeItemDescriptionInformation(tradeItemDescriptionInformation);
		extension.getAny().add(tradeItemDescriptionModuleType);

		TradeItemHandlingModule tradeItemHandlingModule = new TradeItemHandlingModule();
		tradeItemHandlingModule.setLstTradeItemHandlingInformation(tradeItemHandlingInformationDAO.getAllTradeItemHandlingInformation(Product_Id, Product_Hierarchy_id));
		gs1Export.setTradeItemHandlingModule(tradeItemHandlingModule);
		
		TradeItemHandlingModuleType tradeItemHandlingModuleType = objectFactory.createTradeItemHandlingModuleType();
		TradeItemHandlingInformationType tradeItemHandlingInformation = new TradeItemHandlingInformationType();
		
		
		for(TradeItemHandlingInformation eleTradeItemHandlingInfo : tradeItemHandlingModule.getLstTradeItemHandlingInformation()) {
			CodeType code = new CodeType();
			code.setValue(eleTradeItemHandlingInfo.getHandling_Instr_Ref());
			if(eleTradeItemHandlingInfo.getHandling_Instr_Ref()!=null)
				tradeItemHandlingInformation.getHandlingInstructionsCodeReference().add(code);
		}
		if(tradeItemHandlingInformation!=null) {
			tradeItemHandlingModuleType.setTradeItemHandlingInformation(tradeItemHandlingInformation);				
			extension.getAny().add(tradeItemHandlingModuleType);
		}
		TradeItemHierarchyModule tradeItemHierarchyModule = new TradeItemHierarchyModule();
		tradeItemHierarchyModule.setLstTradeItemHierarchyQuantity(tradeItemHierarchyQuantityDAO.getAllTradeItemHierarchyQuantity(Product_Id, Product_Hierarchy_id));
		gs1Export.setTradeItemHierarchyModule(tradeItemHierarchyModule);
		
		TradeItemHierarchyModuleType tradeItemHierarchyModuleType = objectFactory.createTradeItemHierarchyModuleType();
		
		for (TradeItemHierarchyQuantity eleTradeItemHierarchy : tradeItemHierarchyModule.getLstTradeItemHierarchyQuantity()) {
			TradeItemHierarchyType tradeItemHierarachy = new TradeItemHierarchyType();
			if(eleTradeItemHierarchy.getQuantityOfTradeItemsPerPallet()!=null)
				tradeItemHierarachy.setQuantityOfTradeItemsPerPallet(BigInteger.valueOf(eleTradeItemHierarchy.getQuantityOfTradeItemsPerPallet()));
			
			if(eleTradeItemHierarchy.getQuantityOfCompleteLayersContainedInATradeItem()!=null)
				tradeItemHierarachy.setQuantityOfCompleteLayersContainedInATradeItem(BigInteger.valueOf(eleTradeItemHierarchy.getQuantityOfCompleteLayersContainedInATradeItem()));
			if(eleTradeItemHierarchy.getChild_Unit_Qty()!=null)
				tradeItemHierarachy.setQuantityOfNextLevelTradeItemWithinInnerPack(BigInteger.valueOf(eleTradeItemHierarchy.getChild_Unit_Qty()));
			if(eleTradeItemHierarchy.getCarton_Layer_Per_Pallet()!=null)
				tradeItemHierarachy.setQuantityOfLayersPerPallet(BigInteger.valueOf(eleTradeItemHierarchy.getCarton_Layer_Per_Pallet()));
			if(eleTradeItemHierarchy.getQuantityOfTradeItemsPerPalletLayer()!=null)
				tradeItemHierarachy.setQuantityOfTradeItemsPerPalletLayer(BigInteger.valueOf(eleTradeItemHierarchy.getQuantityOfTradeItemsPerPalletLayer()));
			
			
			tradeItemHierarchyModuleType.setTradeItemHierarchy(tradeItemHierarachy);
		}
		
		extension.getAny().add(tradeItemHierarchyModuleType);

		if(isLowestLevel) {
			TradeItemLifespanModule tradeItemLifespanModule = new TradeItemLifespanModule();
			tradeItemLifespanModule.setLstTradeItemLifespan(tradeItemLifespanDAO.getAllTradeItemLifespan(Product_Id));
			gs1Export.setTradeItemLifespanModule(tradeItemLifespanModule);
				
			TradeItemLifespanModuleType tradeItemLifespanModuleType = objectFactory.createTradeItemLifespanModuleType();
			
			for (TradeItemLifespan eleTradeItemLifespan : tradeItemLifespanModule.getLstTradeItemLifespan()) {
				TradeItemLifespanType tradeItemLifespan = new TradeItemLifespanType();
				if(eleTradeItemLifespan.getMin_Life_Arrival()!=null)
					tradeItemLifespan.setMinimumTradeItemLifespanFromTimeOfArrival(BigInteger.valueOf(eleTradeItemLifespan.getMin_Life_Arrival()));
				if(eleTradeItemLifespan.getMin_Life_Production()!=null)
					tradeItemLifespan.setMinimumTradeItemLifespanFromTimeOfProduction(BigInteger.valueOf(eleTradeItemLifespan.getMin_Life_Production()));
				if(eleTradeItemLifespan.getOpen_Prod_Lifespan()!=null)
					tradeItemLifespan.setOpenedTradeItemLifespan(BigInteger.valueOf(eleTradeItemLifespan.getOpen_Prod_Lifespan()));
				tradeItemLifespanModuleType.setTradeItemLifespan(tradeItemLifespan);
			}
			extension.getAny().add(tradeItemLifespanModuleType);
		}
		TradeItemMeasurementsModule tradeItemMeasurementsModule = new TradeItemMeasurementsModule();
		tradeItemMeasurementsModule.setLstTradeItemMeasurements(tradeItemMeasurementsDAO.getAllTradeItemMeasurements(Product_Id, Product_Hierarchy_id));
		tradeItemMeasurementsModule.setLstTradeItemNetContent(tradeItemNetContentDAO.getAllTradeItemNetContent(Product_Id, Product_Hierarchy_id));
		tradeItemMeasurementsModule.setLstTradeItemPegMeasurements(tradeItemPegMeasurementsDAO.getAllTradeItemPegMeasurements(Product_Id));
		gs1Export.setTradeItemMeasurementsModule(tradeItemMeasurementsModule);
		
		TradeItemMeasurementsModuleType tradeItemMeasurementsModuleType = objectFactory.createTradeItemMeasurementsModuleType();
		TradeItemMeasurementsType tradeItemMeasurements = new TradeItemMeasurementsType();
		
		for (TradeItemMeasurements eleTrade : tradeItemMeasurementsModule.getLstTradeItemMeasurements()) {
			
			MeasurementType depth = new MeasurementType();
			depth.setMeasurementUnitCode(eleTrade.getDepth_Uom());
			if(eleTrade.getDepth()!=null) {
				depth.setValue(BigDecimal.valueOf(eleTrade.getDepth()));
				tradeItemMeasurements.setDepth(depth);
			}
			FrontFaceTypeCodeType frontFaceTypeCode = new FrontFaceTypeCodeType();
			frontFaceTypeCode.setValue(eleTrade.getFront_Face_Type());
			if(eleTrade.getFront_Face_Type()!=null)
				tradeItemMeasurements.setFrontFaceTypeCode(frontFaceTypeCode);
			
			MeasurementType height = new MeasurementType();
			height.setMeasurementUnitCode(eleTrade.getHeight_Uom());
			if(eleTrade.getHeight()!=null) {
				height.setValue(BigDecimal.valueOf(eleTrade.getHeight()));
				tradeItemMeasurements.setHeight(height);
			}
			MeasurementType inBoxCubeDimension = new MeasurementType();
			inBoxCubeDimension.setMeasurementUnitCode(eleTrade.getIn_Box_Cube_Dim_Uom());
			if(eleTrade.getNet_Base_Volume()!=null) {
				inBoxCubeDimension.setValue(BigDecimal.valueOf(eleTrade.getNet_Base_Volume()));
			tradeItemMeasurements.setInBoxCubeDimension(inBoxCubeDimension);
			}
			MeasurementType width = new MeasurementType();
			width.setMeasurementUnitCode(eleTrade.getWidth_Uom());
			if(eleTrade.getWidth()!=null) {
				width.setValue(BigDecimal.valueOf(eleTrade.getWidth()));									
			tradeItemMeasurements.setWidth(width);
			}
			TradeItemWeightType tradeItemWeight = new TradeItemWeightType();
			
			MeasurementType netWeight = new MeasurementType();
			netWeight.setMeasurementUnitCode(eleTrade.getNet_Weight_Uom());
			if(eleTrade.getNet_Weight()!=null) {
				netWeight.setValue(BigDecimal.valueOf(eleTrade.getNet_Weight()));	
			tradeItemWeight.setNetWeight(netWeight);
			}
			MeasurementType grossWeight = new MeasurementType();
			grossWeight.setMeasurementUnitCode(eleTrade.getGross_Weight_Uom());
			if(eleTrade.getGross_Weight()!=null) {
				grossWeight.setValue(BigDecimal.valueOf(eleTrade.getGross_Weight()));	
			tradeItemWeight.setGrossWeight(grossWeight);
			}
			MeasurementType drainedWeight = new MeasurementType();
			drainedWeight.setMeasurementUnitCode(eleTrade.getDrained_Weight_Uom());
			if(eleTrade.getDrained_Weight()!=null) {
				drainedWeight.setValue(BigDecimal.valueOf(eleTrade.getDrained_Weight()));	
				tradeItemWeight.setDrainedWeight(drainedWeight);
			
			}
			if(tradeItemWeight!=null)
				tradeItemMeasurements.setTradeItemWeight(tradeItemWeight);
		}
		
		for (TradeItemNetContent eleNetContent : tradeItemMeasurementsModule.getLstTradeItemNetContent()) {
			MeasurementType netContent = new MeasurementType();
			netContent.setMeasurementUnitCode(eleNetContent.getNet_Content_Uom());
			if(eleNetContent.getNet_Content()!=null) {
				netContent.setValue(BigDecimal.valueOf(eleNetContent.getNet_Content()));
				tradeItemMeasurements.getNetContent().add(netContent);
			}
		}
		
		for(TradeItemPegMeasurements elePegMeas: tradeItemMeasurementsModule.getLstTradeItemPegMeasurements()) {
			
			PegMeasurementsType pegMeasType = new PegMeasurementsType();
			
			if(elePegMeas.getPeg_Hole_Num()!=null)
				pegMeasType.setPegHoleNumber(BigInteger.valueOf(elePegMeas.getPeg_Hole_Num()));
			MeasurementType pegHori = new MeasurementType();
			pegHori.setMeasurementUnitCode(elePegMeas.getPeg_Horizontal_Uom());
			if(elePegMeas.getPeg_Horizontal()!=null) {
				pegHori.setValue(elePegMeas.getPeg_Horizontal());
				pegMeasType.setPegHorizontal(pegHori);
			}
			
			MeasurementType pegVert = new MeasurementType();
			pegVert.setMeasurementUnitCode(elePegMeas.getPeg_Vertical_Uom());
			if(elePegMeas.getPeg_Vertical()!=null) {
				pegVert.setValue(elePegMeas.getPeg_Vertical());
				pegMeasType.setPegVertical(pegVert);
			}
			
			tradeItemMeasurements.getPegMeasurements().add(pegMeasType);
		}
		tradeItemMeasurementsModuleType.setTradeItemMeasurements(tradeItemMeasurements);								
		extension.getAny().add(tradeItemMeasurementsModuleType);
		
		VariableTradeItemInformationModuleType variableTradeItemInformationModule = objectFactory.createVariableTradeItemInformationModuleType(); 
		gs1Export.setLstVariableTradeItemInformation(variableTradeItemInformationDAO.getAllVariableTradeItemInformation(Product_Id));

		for (VariableTradeItemInformation eleVariableTradeItem : gs1Export.getLstVariableTradeItemInformation()) {
			VariableTradeItemInformationType variableTradeItemInfo = new VariableTradeItemInformationType();
			variableTradeItemInfo.setIsTradeItemAVariableUnit(Boolean.valueOf(eleVariableTradeItem.getIs_Variable_Unit()));
			variableTradeItemInformationModule.setVariableTradeItemInformation(variableTradeItemInfo);

		}
		extension.getAny().add(variableTradeItemInformationModule);

		TradeItemSizeModule tradeItemSizeModule = new TradeItemSizeModule();
		tradeItemSizeModule.setLstTradeItemSize(tradeItemSizeDAO.getAllTradeItemSize(Product_Id));
		gs1Export.setTradeItemSizeModule(tradeItemSizeModule);
		
		
		TradeItemSizeModuleType tradeItemSizeModuleType = objectFactory.createTradeItemSizeModuleType();
		
		for(TradeItemSize eleTradeItemSize : tradeItemSizeModule.getLstTradeItemSize()) {
			SizeType size = new SizeType();
			Description80Type descSize = new Description80Type();
			descSize.setLanguageCode("en");
			descSize.setValue(eleTradeItemSize.getDescriptive_Size());
			size.setDescriptiveSize(descSize);
			
			SizeCodeType sizeCode = new SizeCodeType();
			sizeCode.setSizeCodeListCode(eleTradeItemSize.getSize_Code_List());
			sizeCode.setValue(eleTradeItemSize.getSize_Code_List_Value());
			size.setSizeCode(sizeCode);
			
			tradeItemSizeModuleType.getSize().add(size);
			
		}
		extension.getAny().add(tradeItemSizeModuleType);
		
		
		TradeItemTemperatureInformationModule tradeItemTemperatureInformationModule = new TradeItemTemperatureInformationModule();
		tradeItemTemperatureInformationModule.setLstTradeItemTemperatureInformation(tradeItemTemperatureInformationDAO.getAllTradeItemTemperatureInformation(Product_Id));
		gs1Export.setTradeItemTemperatureInformationModule(tradeItemTemperatureInformationModule);
		
		TradeItemTemperatureInformationModuleType tradeItemTemperatureInformationModuleType = objectFactory.createTradeItemTemperatureInformationModuleType();
		for(TradeItemTemperatureInformation eleTradeItemTempInfo : tradeItemTemperatureInformationModule.getLstTradeItemTemperatureInformation()) {
			TradeItemTemperatureInformationType tradeItemTemperatureInformation = new TradeItemTemperatureInformationType();
		
			TemperatureMeasurementType maxTempMeasure = new TemperatureMeasurementType();
			maxTempMeasure.setTemperatureMeasurementUnitCode(eleTradeItemTempInfo.getMax_Temp_Uom());
			if(eleTradeItemTempInfo.getMax_Temp()!=null) {
				maxTempMeasure.setValue(BigDecimal.valueOf(eleTradeItemTempInfo.getMax_Temp()));
				tradeItemTemperatureInformation.setMaximumTemperature(maxTempMeasure);
					
			}
			
			TemperatureMeasurementType minTempMeasure = new TemperatureMeasurementType();
			minTempMeasure.setTemperatureMeasurementUnitCode(eleTradeItemTempInfo.getMin_Temp_Uom());
			
			if(eleTradeItemTempInfo.getMin_Temp()!=null) {
			minTempMeasure.setValue(BigDecimal.valueOf(eleTradeItemTempInfo.getMin_Temp()));
			tradeItemTemperatureInformation.setMinimumTemperature(minTempMeasure);
			}
			TemperatureQualifierCodeType temperatureQualifierCode = new TemperatureQualifierCodeType();
			temperatureQualifierCode.setValue(eleTradeItemTempInfo.getTemp_Qualifier_Code());
			tradeItemTemperatureInformation.setTemperatureQualifierCode(temperatureQualifierCode);
			
			tradeItemTemperatureInformationModuleType.getTradeItemTemperatureInformation().add(tradeItemTemperatureInformation);
			
			
		}
		extension.getAny().add(tradeItemTemperatureInformationModuleType);
		
		
		gs1Export.setLstTransportationHazardousClassification(transportationHazardousClassificationDAO.getAllTransportationHazardousClassification(Product_Id));
		
		TransportationHazardousClassificationModuleType transportationHazardousClassificationModule = objectFactory.createTransportationHazardousClassificationModuleType();
		
		
		for (TransportationHazardousClassification eleTransportationHazard : gs1Export.getLstTransportationHazardousClassification()) {
			TransportationClassificationType transportationClassification = new TransportationClassificationType();
			RegulatedTransportationModeType regulatedTransportationMode = new RegulatedTransportationModeType();
			HazardousInformationHeaderType hazardousInfoHeader = new HazardousInformationHeaderType();
			
			hazardousInfoHeader.setDangerousGoodsRegulationCode(eleTransportationHazard.getDg_Regulation_Code());
			MeasurementType flashPointTemp = new MeasurementType();
			flashPointTemp.setMeasurementUnitCode(eleTransportationHazard.getFlash_Point_Temp_Uom());
			if(eleTransportationHazard.getFlash_Point_Temp()!=null) {
			flashPointTemp.setValue(BigDecimal.valueOf(eleTransportationHazard.getFlash_Point_Temp()));
			hazardousInfoHeader.getFlashPointTemperature().add(flashPointTemp);
			}
			HazardousInformationDetailType hazardousInformationDetail = new HazardousInformationDetailType();
			hazardousInformationDetail.setClassOfDangerousGoods(eleTransportationHazard.getDg_Class());
			hazardousInformationDetail.getDangerousGoodsHazardousCode().add(eleTransportationHazard.getDg_Hazardous_Code());
			
			CodeType packingGrp = new CodeType();
			packingGrp.setValue(eleTransportationHazard.getDg_Packing_Group());
			hazardousInformationDetail.setDangerousGoodsPackingGroup(packingGrp);
			
			LanguageOptionalDescription1000Type shippingName = new LanguageOptionalDescription1000Type();
			shippingName.setLanguageCode("en");
			shippingName.setValue(eleTransportationHazard.getDg_Shipping_Name());
			hazardousInformationDetail.getDangerousGoodsShippingName().add(shippingName);
			if(eleTransportationHazard.getDg_Secondary_Sub_Div()!=null)
				hazardousInformationDetail.getDangerousGoodsSubsidiaryClass().add(eleTransportationHazard.getDg_Secondary_Sub_Div());
			hazardousInformationDetail.setUnitedNationsDangerousGoodsNumber(eleTransportationHazard.getUn_Dg_Num());
			Description1000Type dgTechName = new Description1000Type();
			dgTechName.setLanguageCode("en");
			dgTechName.setValue(eleTransportationHazard.getDg_Technical_Name());
			hazardousInformationDetail.getDangerousGoodsTechnicalName().add(dgTechName);
			
			hazardousInfoHeader.getHazardousInformationDetail().add(hazardousInformationDetail);									
			regulatedTransportationMode.getHazardousInformationHeader().add(hazardousInfoHeader);
			transportationClassification.getRegulatedTransportationMode().add(regulatedTransportationMode);
			transportationHazardousClassificationModule.getTransportationClassification().add(transportationClassification);
		
			
		}
		extension.getAny().add(transportationHazardousClassificationModule);

		
		gs1Export.setLstWarrantyInformation(warrantyInformationDAO.getAllWarrantyInformation(Product_Id));
		
		WarrantyInformationModuleType warrantyInformationModule = objectFactory.createWarrantyInformationModuleType();
		
		for (WarrantyInformation eleWarrantyInfo : gs1Export.getLstWarrantyInformation()) {
			WarrantyInformationType warrantyInformation = new WarrantyInformationType();
			
			Description2500Type warrantyDesc = new Description2500Type();
			warrantyDesc.setLanguageCode("en");
			warrantyDesc.setValue(eleWarrantyInfo.getWarranty_Desc());
			warrantyInformation.getWarrantyDescription().add(warrantyDesc);
			
			WarrantyConditionsType warrantyCondns = new WarrantyConditionsType();
			MeasurementType warrDur= new MeasurementType();
			warrDur.setMeasurementUnitCode(eleWarrantyInfo.getWarranty_Duration_Uom());
			if(eleWarrantyInfo.getWarranty_Duration()!=null) {
				warrDur.setValue(BigDecimal.valueOf(eleWarrantyInfo.getWarranty_Duration()));
				warrantyCondns.setWarrantyDuration(warrDur);	
			}
			
			warrantyInformation.getWarrantyConditions().add(warrantyCondns);
			
			
			warrantyInformationModule.getWarrantyInformation().add(warrantyInformation);
			
		}
		extension.getAny().add(warrantyInformationModule);
		
	
		
		
		if(isLowestLevel) {
			List<DietInformationModule> lstDietInformationModule = dietInformationModuleDAO.getAllDietInformationModule(Product_Id);
			gs1Export.setLstDietInformationModule(lstDietInformationModule);
			
			DietInformationModuleType dietInformationModule = objectFactory.createDietInformationModuleType();
			
			for(DietInformationModule eleDietInfo : lstDietInformationModule) {
			
				DietInformationType dietInfo = new DietInformationType();
				
				Description70Type dietTypeDesc = new Description70Type();
				dietTypeDesc.setLanguageCode("en");
				dietTypeDesc.setValue(eleDietInfo.getDiet_Type_Desc());
				dietInfo.getDietTypeDescription().add(dietTypeDesc);
				
				DietTypeInformationType dietTypeInfo = new DietTypeInformationType();
				
				DietTypeCodeType dietTypeCode = new DietTypeCodeType();
				dietTypeCode.setValue(eleDietInfo.getDiet_Type_Code());
				dietTypeInfo.setDietTypeCode(dietTypeCode);
				
				CertificationInformationType certInfo = new CertificationInformationType();
				certInfo.setCertificationAgency(eleDietInfo.getCert_Agency());
				CertificationType cert = new CertificationType();
				cert.setCertificationValue(eleDietInfo.getDiet_Cert_Value());
				certInfo.getCertification().add(cert);
				dietTypeInfo.setDietCertification(certInfo);
				dietInfo.getDietTypeInformation().add(dietTypeInfo);
				
				dietInformationModule.setDietInformation(dietInfo);
				
			}
			extension.getAny().add(dietInformationModule);
			
			List<AllergenInformationModule> lstAllergenInformationModule = allergenInformationModuleDAO.getAllAllergenInformationModule(Product_Id);
			gs1Export.setLstAllergenInformationModule(lstAllergenInformationModule);
			
			AllergenInformationModuleType allergenInformationModuleType = objectFactory.createAllergenInformationModuleType();
			
			
			for(AllergenInformationModule eleAllergenInfo : lstAllergenInformationModule) {
			
				AllergenRelatedInformationType allergenRelatedInfo = new AllergenRelatedInformationType();
				allergenRelatedInfo.setAllergenSpecificationAgency(eleAllergenInfo.getSpecification_Agency());
				allergenRelatedInfo.setAllergenSpecificationName(eleAllergenInfo.getSpecification_Name());
				
				Description1000Type allergenStmt = new Description1000Type();
				allergenStmt.setLanguageCode("en");
				allergenStmt.setValue(eleAllergenInfo.getAllergen_Statement());
				allergenRelatedInfo.getAllergenStatement().add(allergenStmt);
				
				AllergenType allergen = new AllergenType();
				AllergenTypeCodeType allergenTypeCode= new AllergenTypeCodeType();
				allergenTypeCode.setValue(eleAllergenInfo.getAllergen_Type_Code());
				allergen.setAllergenTypeCode(allergenTypeCode);
				
				LevelOfContainmentCodeType levelContainment = new LevelOfContainmentCodeType();
				levelContainment.setValue(eleAllergenInfo.getContainment_Level());
				allergen.setLevelOfContainmentCode(levelContainment);
				
				allergenRelatedInfo.getAllergen().add(allergen);
				
				
				allergenInformationModuleType.getAllergenRelatedInformation().add(allergenRelatedInfo);
				
			}
			extension.getAny().add(allergenInformationModuleType);
		}
		
		
		
		List<Animal_Nutrient_Detail> lstAnimal_Nutrient_Detail = animalFeedingModuleDAO.getAllAnimal_Nutrient_Detail(Product_Id);
		
		AnimalFeedingModuleType animalFeedingModuleType = objectFactory.createAnimalFeedingModuleType(); 		
		AnimalFeedingType animalFeeding = new AnimalFeedingType();		
		AnimalFeedingDetailType animalFeedingDtl = new AnimalFeedingDetailType();
		
		for(Animal_Nutrient_Detail eleAnimal_Nutrient_Detail : lstAnimal_Nutrient_Detail) {
		
			AnimalNutrientDetailType animalNutrientDtl = new AnimalNutrientDetailType();
			if(eleAnimal_Nutrient_Detail.getNutr_Exact_Pct()!=null)
				animalNutrientDtl.setAnimalNutrientExactPercentage(BigDecimal.valueOf(eleAnimal_Nutrient_Detail.getNutr_Exact_Pct()));
			if(eleAnimal_Nutrient_Detail.getNutr_Max_Pct()!=null)
				animalNutrientDtl.setAnimalNutrientMaximumPercentage(BigDecimal.valueOf(eleAnimal_Nutrient_Detail.getNutr_Max_Pct()));
			if(eleAnimal_Nutrient_Detail.getNutr_Min_Pct()!=null)
				animalNutrientDtl.setAnimalNutrientMinimumPercentage(BigDecimal.valueOf(eleAnimal_Nutrient_Detail.getNutr_Min_Pct()));
			
			NutrientTypeCodeType nutrientTypeCode = new NutrientTypeCodeType();
			nutrientTypeCode.setValue(eleAnimal_Nutrient_Detail.getNutr_Type());
			if(eleAnimal_Nutrient_Detail.getNutr_Type()!=null)
				animalNutrientDtl.setAnimalNutrientTypeCode(nutrientTypeCode);
			
			animalFeedingDtl.getAnimalNutrientDetail().add(animalNutrientDtl);
			
		}
		animalFeeding.getAnimalFeedingDetail().add(animalFeedingDtl);
		animalFeedingModuleType.getAnimalFeeding().add(animalFeeding);
		
		extension.getAny().add(animalFeedingModuleType);

		
		List<CertificationInformationModule> lstCertificationInformationModule = certificationInformationModuleDAO.getAllCertificationInformationModule(Product_Id);
		gs1Export.setLstCertificationInformationModule(lstCertificationInformationModule);
		
		CertificationInformationModuleType certificationInformationModuleType =  objectFactory.createCertificationInformationModuleType();
		
		for (CertificationInformationModule eleCertInfo : lstCertificationInformationModule) {
			CertificationInformationType certInfo = new CertificationInformationType();
		
			certInfo.setCertificationAgency(eleCertInfo.getCertification_Agency());
			certInfo.setCertificationStandard(eleCertInfo.getCertification_Std());
			CertificationType certType = new CertificationType();
			certType.setCertificationValue(eleCertInfo.getCertification_Value());
			certInfo.getCertification().add(certType);
			certificationInformationModuleType.getCertificationInformation().add(certInfo);
		}
		extension.getAny().add(certificationInformationModuleType);
		
		if(isLowestLevel) {
			List<ConsumerInstructionsModule> lstConsumerInstructionsModule = consumerInstructionsModuleDAO.getAllConsumerInstructionsModule(Product_Id);
			gs1Export.setLstConsumerInstructionsModule(lstConsumerInstructionsModule);
			
			ConsumerInstructionsModuleType consumerInstructionsModuleType = objectFactory.createConsumerInstructionsModuleType();
			
			for(ConsumerInstructionsModule eleConsumerInstr : lstConsumerInstructionsModule) {
				ConsumerInstructionsType consumerInstr = new ConsumerInstructionsType();
				
				Description5000Type consStorageInstr = new Description5000Type();
				consStorageInstr.setLanguageCode("en");
				consStorageInstr.setValue(eleConsumerInstr.getConsumer_Storage_Instr());
				consumerInstr.getConsumerStorageInstructions().add(consStorageInstr);
				
				Description5000Type usageInstr = new Description5000Type();
				usageInstr.setLanguageCode("en");
				usageInstr.setValue(eleConsumerInstr.getConsumer_Usage_Instr());
				consumerInstr.getConsumerUsageInstructions().add(usageInstr);
				
				consumerInstructionsModuleType.setConsumerInstructions(consumerInstr);
			}
			
			extension.getAny().add(consumerInstructionsModuleType);	
		}
									
		if(isLowestLevel) {			
		
			BatteryInformationModule batteryInformationModule = batteryInformationModuleDAO.getAllBatteryInformationModule(Product_Id);
			if(batteryInformationModule!=null) {
				batteryInformationModule.setBattery_Type(batteryTypeDAO.getAllBatteryTypes(Product_Id));
				gs1Export.setBatteryInformationModule(batteryInformationModule);
			
				BatteryInformationModuleType batteryInformationModuleType = objectFactory.createBatteryInformationModuleType();
				if(batteryInformationModule.getBatteries_Included()!=null)
					batteryInformationModuleType.setAreBatteriesIncluded(Boolean.valueOf(batteryInformationModule.getBatteries_Included()));
	
				for (Battery_Type eleBatteryType : batteryInformationModule.getBattery_Type()) {
					BatteryDetailType batteryDtl = new BatteryDetailType();
					BatteryTypeCodeType batteryTypeCode = new BatteryTypeCodeType();
					batteryTypeCode.setValue(eleBatteryType.getBattery_Type());
					batteryDtl.setBatteryTypeCode(batteryTypeCode);
					batteryInformationModuleType.getBatteryDetail().add(batteryDtl);
				}
	
				extension.getAny().add(batteryInformationModuleType);
			
			}
		
		}
		
		NonfoodIngredientModule nonfoodIngredientModule = nonfoodIngredientModuleDAO.getAllNonfoodIngredientModule(Product_Id, strHierarchyIds);
		if(nonfoodIngredientModule!=null) {
			gs1Export.setNonfoodIngredientModule(nonfoodIngredientModule);
			NonfoodIngredientModuleType nonfoodIngredientModuleType = objectFactory.createNonfoodIngredientModuleType();
			FormattedDescription5000Type nonFoodIngrdStmt = new FormattedDescription5000Type();
			nonFoodIngrdStmt.setLanguageCode("en");
			nonFoodIngrdStmt.setValue(nonfoodIngredientModule.getNon_Food_Ingredient_Statement());
			nonfoodIngredientModuleType.getNonfoodIngredientStatement().add(nonFoodIngrdStmt);
			
			extension.getAny().add(nonfoodIngredientModuleType);
		
		}

		if(isLowestLevel) {
			
		
			FoodAndBeverageIngredientModule foodAndBeverageIngredientModule = foodAndBeverageIngredientModuleDAO.getAllFoodAndBeverageIngredientModule(Product_Id);
			if(foodAndBeverageIngredientModule!=null) {
				foodAndBeverageIngredientModule.setLstAdditiveInformation(additiveInformationDAO.getAllAdditiveInformation(Product_Id));
				foodAndBeverageIngredientModule.setLstFoodAndBeverageIngredient(foodAndBeverageIngredientDAO.getAllFoodAndBeverageIngredient(Product_Id));
				gs1Export.setFoodAndBeverageIngredientModule(foodAndBeverageIngredientModule);	
				
				FoodAndBeverageIngredientModuleType foodAndBeverageIngredientModuleType = objectFactory.createFoodAndBeverageIngredientModuleType();
				
				Description5000Type ingrdStmt = new Description5000Type();
				ingrdStmt.setLanguageCode("en");
				ingrdStmt.setValue(foodAndBeverageIngredientModule.getIngr_Statement());
			
				foodAndBeverageIngredientModuleType.getIngredientStatement().add(ingrdStmt);							
				
				for (AdditiveInformation eleAdditiveInfo : foodAndBeverageIngredientModule.getLstAdditiveInformation()) {
					AdditiveInformationType addtvInfo = new AdditiveInformationType();
					addtvInfo.setAdditiveName(eleAdditiveInfo.getAdditiveName());
					LevelOfContainmentCodeType lvlContCode = new LevelOfContainmentCodeType();
					lvlContCode.setValue(eleAdditiveInfo.getContainment_Level());
					addtvInfo.setLevelOfContainmentCode(lvlContCode);									
					foodAndBeverageIngredientModuleType.getAdditiveInformation().add(addtvInfo);
				}
				
				for (FoodAndBeverageIngredient eleFoodBevIngrd : foodAndBeverageIngredientModule.getLstFoodAndBeverageIngredient()) {
					FoodAndBeverageIngredientType foodBevIng = new FoodAndBeverageIngredientType();
					Description70Type ingrdName = new Description70Type();
					ingrdName.setLanguageCode("en");
					ingrdName.setValue(eleFoodBevIngrd.getIngredient_Name());
					foodBevIng.getIngredientName().add(ingrdName);
					foodBevIng.setIngredientSequence(eleFoodBevIngrd.getIngredient_Seq());									
					foodAndBeverageIngredientModuleType.getFoodAndBeverageIngredient().add(foodBevIng);
				}
				if(isLowestLevel) {
					extension.getAny().add(foodAndBeverageIngredientModuleType);
				}
				
			}
			
			
			
			FoodAndBeveragePreparationServingModule foodAndBeveragePreparationServingModule = new FoodAndBeveragePreparationServingModule();
			foodAndBeveragePreparationServingModule.setLstPreparationServing(preparationServingDAO.getAllPreparationServing(Product_Id));
			foodAndBeveragePreparationServingModule.setLstServingQuantityInformation(servingQuantityInformationDAO.getAllServingQuantityInformation(Product_Id));
			gs1Export.setFoodAndBeveragePreparationServingModule(foodAndBeveragePreparationServingModule);	
			
			FoodAndBeveragePreparationServingModuleType foodAndBeveragePreparationServingModuleType = objectFactory.createFoodAndBeveragePreparationServingModuleType();
			
			for(PreparationServing elePrepServing : foodAndBeveragePreparationServingModule.getLstPreparationServing()) {
				
				PreparationServingType prepServ = new PreparationServingType();
				PreparationTypeCodeType prepType = new PreparationTypeCodeType();
				prepType.setValue(elePrepServing.getPrep_Type());
				prepServ.setPreparationTypeCode(prepType);		
				Description1000Type servSugsn = new Description1000Type();
				servSugsn.setLanguageCode("en");
				servSugsn.setValue(elePrepServing.getServing_Suggestion());
				prepServ.getServingSuggestion().add(servSugsn);
				
				Description1000Type consPrec = new Description1000Type();
				consPrec.setLanguageCode("en");
				consPrec.setValue(elePrepServing.getPrep_ConsumptionPrecautions());
				prepServ.getPreparationConsumptionPrecautions().add(consPrec );
				
				Description2500Type preInstr = new Description2500Type();
				preInstr.setLanguageCode("en");
				preInstr.setValue(elePrepServing.getPrep_Instr());
				prepServ.getPreparationInstructions().add(preInstr);
				foodAndBeveragePreparationServingModuleType.getPreparationServing().add(prepServ);
				
				
			}
			
			for (ServingQuantityInformation eleServQty :foodAndBeveragePreparationServingModule.getLstServingQuantityInformation() ) {
				ServingQuantityInformationType servQtyInfo = new ServingQuantityInformationType();
				if(eleServQty.getServings_Per_Pack()!=null) {
					servQtyInfo.setNumberOfServingsPerPackage(BigDecimal.valueOf(eleServQty.getServings_Per_Pack()));
					foodAndBeveragePreparationServingModuleType.setServingQuantityInformation(servQtyInfo);
				}
			}
			extension.getAny().add(foodAndBeveragePreparationServingModuleType);
	
			FoodAndBeveragePropertiesInformationModule foodAndBeveragePropertiesInformationModule = new FoodAndBeveragePropertiesInformationModule();
			foodAndBeveragePropertiesInformationModule.setLstMicrobiologicalInformation(microbiologicalInformationDAO.getAllMicrobiologicalInformation(Product_Id));
			foodAndBeveragePropertiesInformationModule.setLstPhysiochemicalCharacteristic(physiochemicalCharacteristicDAO.getAllPhysiochemicalCharacteristic(Product_Id));
			gs1Export.setFoodAndBeveragePropertiesInformationModule(foodAndBeveragePropertiesInformationModule);	
	
			FoodAndBeveragePropertiesInformationModuleType foodAndBeveragePropertiesInformationModuleType = objectFactory.createFoodAndBeveragePropertiesInformationModuleType();
			
			for(MicrobiologicalInformation eleMicrobesInfo : foodAndBeveragePropertiesInformationModule.getLstMicrobiologicalInformation()) {
			
				MicrobiologicalInformationType microbesInfo = new MicrobiologicalInformationType();
				MicrobiologicalOrganismCodeType microbeCode = new MicrobiologicalOrganismCodeType();
				microbeCode.setValue(eleMicrobesInfo.getOrganism_Code());
				microbesInfo.setMicrobiologicalOrganismCode(microbeCode);
			
				MeasurementType microbMax = new MeasurementType();
				microbMax.setMeasurementUnitCode(eleMicrobesInfo.getOrganism_Max_Value_Uom());
				if(eleMicrobesInfo.getOrganism_Max_Value()!=null) {
					microbMax.setValue(BigDecimal.valueOf(eleMicrobesInfo.getOrganism_Max_Value()));
					microbesInfo.getMicrobiologicalOrganismMaximumValue().add(microbMax);
				}
				foodAndBeveragePropertiesInformationModuleType.getMicrobiologicalInformation().add(microbesInfo);
			}
			
			for(PhysiochemicalCharacteristic elePhysiChemChar : foodAndBeveragePropertiesInformationModule.getLstPhysiochemicalCharacteristic() ) {
				
				PhysiochemicalCharacteristicType physChemChar = new PhysiochemicalCharacteristicType();
				PhysiochemicalCharacteristicCodeType physChemCode = new PhysiochemicalCharacteristicCodeType();
				physChemCode.setValue(elePhysiChemChar.getCharacteristic_Code());
				physChemChar.setPhysiochemicalCharacteristicCode(physChemCode);
				
				QuantityType charValue = new QuantityType();
				charValue.setMeasurementUnitCode(elePhysiChemChar.getCharacteristic_Value_Uom());
				if(elePhysiChemChar.getCharacteristic_Value()!=null) {
					charValue.setValue(BigDecimal.valueOf(elePhysiChemChar.getCharacteristic_Value()));
					physChemChar.getPhysiochemicalCharacteristicValue().add(charValue);
				}
				foodAndBeveragePropertiesInformationModuleType.getPhysiochemicalCharacteristic().add(physChemChar);
				
			}
			extension.getAny().add(foodAndBeveragePropertiesInformationModuleType);
		
		}

		
		
		tradeItemInfo.setExtension(extension);
		baseTradeItem.getTradeItemInformation().add(tradeItemInfo);	
		TradeItemSynchronisationDatesType tradeItemSynchronisationDates = new TradeItemSynchronisationDatesType();
		try {	
				tradeItemSynchronisationDates.setLastChangeDateTime(getXMLGregorianCalendarNow());
				
			} catch (DatatypeConfigurationException e) {				
				e.printStackTrace();
		}
		tradeItemSynchronisationDates.setCommunityVisibilityDateTime(getXMLGregorianDate(prodHier.getCommunity_Visibility_Date()));
		tradeItemSynchronisationDates.setEffectiveDateTime(getXMLGregorianDate(tradeItem.getLstTradeItemHierarchy().get(0).getEffective_Date()));
		tradeItemSynchronisationDates.setPublicationDateTime(getXMLGregorianDate(tradeItem.getLstTradeItemHierarchy().get(0).getPublication_Date()));
		baseTradeItem.setTradeItemSynchronisationDates(tradeItemSynchronisationDates);
		
		
		
		AVP avp = avpDAO.getAllAVP(Product_Id, Product_Hierarchy_id, strCOOIds, Client_Id, Org_Id);
		if(avp!=null) {
			avp.setaVPPallet(avpPalletDAO.getAllAVPPallet(Product_Hierarchy_id));
			avp.setLstAVPCustomsExcisableValue(avpCustomsExcisableValueDAO.getAllPackaging(Product_Id, Product_Hierarchy_id, strCOOIds, Target_Market));
			avp.setLstAVPHsnoApproval(avpHsnoApprovalDAO.getAllPackaging(Product_Id));
			avp.setLstAVPEnvironmental(avpEnvironmentalDAO.getAllPackaging(Product_Id));
			avp.setLstAVPHsnoClassification(avpHsnoClassificationDAO.getAllPackaging(Product_Id));
			
			gs1Export.setAvp(avp);
		}
		
	//	System.out.println(avp);
		
		
		GS1AttributeValuePairListType gs1Attr = new GS1AttributeValuePairListType();
		
		
		CompoundStringAttributeValuePairType compoundStrAVP = new CompoundStringAttributeValuePairType();
		compoundStrAVP.setAttributeCode(avp.getDec_Weight_Vol_Uom());
		compoundStrAVP.setAttributeName("declaredWeightVolumeAmount");
		compoundStrAVP.setValue(avp.getDec_Weight_Vol());		
		compoundStrAVP.setCodeListNameCode("MEASUREMENT_CODE");
		if(avp.getDec_Weight_Vol()!=null)
			gs1Attr.getCompoundStringAVP().add(compoundStrAVP);
		
		compoundStrAVP = new CompoundStringAttributeValuePairType();
		compoundStrAVP.setAttributeCode(avp.getHazardous_Unit_Vol_Uom());
		compoundStrAVP.setAttributeName("hazardousDangerousUnitVolume");
		compoundStrAVP.setValue(avp.getHazardous_Unit_Vol());		
		compoundStrAVP.setCodeListNameCode("MEASUREMENT_CODE");
		if(avp.getHazardous_Unit_Vol()!=null)
			gs1Attr.getCompoundStringAVP().add(compoundStrAVP);
		
		compoundStrAVP = new CompoundStringAttributeValuePairType();
		compoundStrAVP.setAttributeCode(avp.getHazardous_Unit_Size_Uom());
		compoundStrAVP.setAttributeName("hazardousUnitSize");
		compoundStrAVP.setValue(avp.getHazardous_Unit_Size());	
		compoundStrAVP.setCodeListNameCode("MEASUREMENT_CODE");
		if(avp.getHazardous_Unit_Size()!=null)
			gs1Attr.getCompoundStringAVP().add(compoundStrAVP);
		
		compoundStrAVP = new CompoundStringAttributeValuePairType();
		compoundStrAVP.setAttributeCode(avp.getNet_Volume_Uom());
		compoundStrAVP.setAttributeName("netVolume");
		compoundStrAVP.setValue(String.valueOf(avp.getNet_Volume()));	
		compoundStrAVP.setCodeListNameCode("MEASUREMENT_CODE");
		if(avp.getNet_Volume()!=null)
			gs1Attr.getCompoundStringAVP().add(compoundStrAVP);
		
		
		StringAttributeValuePairType strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("alcoholicStrengthDescription");
		strAVP.setValue(avp.getAlcohol_Strength());
		if(avp.getAlcohol_Strength()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("standardDrinks");
		strAVP.setValue(avp.getStd_Drinks());
		if(avp.getStd_Drinks()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("tGAWarning");
		strAVP.setValue(avp.getTga_Warning_Statement());
		if(avp.getTga_Warning_Statement()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("isTradeItemAPriorityFood");
		strAVP.setValue(avp.getPriority_Food());
		if(avp.getPriority_Food()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("cDSMaterialType");
		strAVP.setValue(avp.getCds_Mat_Type());
		if(avp.getCds_Mat_Type()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("champagneIndicator");
		strAVP.setValue(avp.getChampagne_Ind());
		if(avp.getChampagne_Ind()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("consumerRecommendedUse");
		strAVP.setValue(avp.getConsumer_Recommended_Use());
		if(avp.getConsumer_Recommended_Use()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("customsOrExcisableCode");
		strAVP.setValue(avp.getCe_Code());
		if(avp.getCe_Code()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("customsOrExcisableType");
		strAVP.setValue(avp.getCe_Type());
		if(avp.getCe_Type()!=null)
			gs1Attr.getStringAVP().add(strAVP);

		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("customsOrExcisableValue");
		if(avp.getLstAVPCustomsExcisableValue().size()>0) {
			strAVP.setValue(avp.getLstAVPCustomsExcisableValue().get(0).getCustomsOrExcisableValue());
			if(avp.getLstAVPCustomsExcisableValue().get(0).getCustomsOrExcisableValue()!=null)
				gs1Attr.getStringAVP().add(strAVP);
		}
		

		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("customsStatisticalCode");
		strAVP.setValue(avp.getCustom_Stats_Code());
		if(avp.getCustom_Stats_Code()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		
		for(AVPEnvironmental eleEnv : avp.getLstAVPEnvironmental()) {
			strAVP = new StringAttributeValuePairType();
			strAVP.setAttributeName("enviroClaims");
			strAVP.setValue(eleEnv.getEnvironmental_Claim());
			if(eleEnv.getEnvironmental_Claim()!=null)
				gs1Attr.getStringAVP().add(strAVP);
		}
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("finalBatchExpiryDate");
		strAVP.setValue(avp.getFinal_Batch_Expiry_Date());
		if(avp.getFinal_Batch_Expiry_Date()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("flashPointType");
		strAVP.setValue(avp.getFlash_Point_Type());
		if(avp.getFlash_Point_Type()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("fullTradeItemDescription");
		strAVP.setValue(avp.getFull_Desc());
		gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("gTINOfBaseUnit");
		strAVP.setValue(avp.getGtin());
		if(avp.getGtin()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("hasSafetyWarning");
		strAVP.setValue(avp.getHas_Safety_Warning());
		if(avp.getHas_Safety_Warning()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("hazardousDangerousPackagingType");
		strAVP.setValue(avp.getHazardous_Packaging_Type());
		if(avp.getHazardous_Packaging_Type()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("hazardousIdentifier");
		strAVP.setValue(avp.getHazardous_Identifier());
		if(avp.getHazardous_Identifier()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("hazardPackType");
		strAVP.setValue(avp.getHazard_Pack_Type());
		if(avp.getHazard_Pack_Type()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("healthStarRatingValue");
		strAVP.setValue(avp.getHealth_Star_Rating());
		if(avp.getHealth_Star_Rating()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		for( AVPHsnoApproval eleHsNo : avp.getLstAVPHsnoApproval()) {
			strAVP = new StringAttributeValuePairType();
			strAVP.setAttributeName("hSNOApprovalNumber");
			strAVP.setValue(eleHsNo.getHsno_Approval_Num());
			if(eleHsNo.getHsno_Approval_Num()!=null)
				gs1Attr.getStringAVP().add(strAVP);
		}
		
		for(  AVPHsnoClassification eleHsClass : avp.getLstAVPHsnoClassification()) {
			strAVP = new StringAttributeValuePairType();
			strAVP.setAttributeName("hSNOClassification");
			strAVP.setValue(eleHsClass.getHsno_Classification());
			if(eleHsClass.getHsno_Classification()!=null)
				gs1Attr.getStringAVP().add(strAVP);
		}
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("interpretiveTermOptionalNutrient");
		strAVP.setValue(avp.getInterpretive_Term_Opt_Nutrient());
		if(avp.getInterpretive_Term_Opt_Nutrient()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("interpretiveTermSaturatedFat");
		strAVP.setValue(avp.getInterpretive_Term_Sat_Fat());
		if(avp.getInterpretive_Term_Sat_Fat()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("interpretiveTermSodium");
		strAVP.setValue(avp.getInterpretive_Term_Sodium());
		if(avp.getInterpretive_Term_Sodium()!=null)
			gs1Attr.getStringAVP().add(strAVP);

		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("interpretiveTermSugars");
		strAVP.setValue(avp.getInterpretive_Term_Sugar());
		if(avp.getInterpretive_Term_Sugar()!=null)
			gs1Attr.getStringAVP().add(strAVP);	

		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("isTradeItemADangerousGood");
		strAVP.setValue(avp.getDangerous_Good());
		if(avp.getDangerous_Good()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("isTradeItemAHazardousGood");
		strAVP.setValue(avp.getHazardous_Good());
		if(avp.getHazardous_Good()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("labellingAdditionalPhrase");
		strAVP.setValue(avp.getLabelling_Addtl_Phrase());
		if(avp.getLabelling_Addtl_Phrase()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("labellingAustralianContentPercentage");
		strAVP.setValue(String.valueOf(avp.getLabelling_Aus_Content_Pct()));
		if(avp.getLabelling_Aus_Content_Pct()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("labellingClaims");
		strAVP.setValue(avp.getLabelling_Claims());
		if(avp.getLabelling_Claims()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("labellingCountryOfOrigin");
		strAVP.setValue(avp.getLabelling_Country_Origin());
		if(avp.getLabelling_Country_Origin()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("labellingGetDetails");
		strAVP.setValue(avp.getLabelling_Addtl_Dtls());
		if(avp.getLabelling_Addtl_Dtls()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("labellingIngredientStatement");
		strAVP.setValue(avp.getLabelling_Ingr_Statement());
		if(avp.getLabelling_Ingr_Statement()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("labellingLogos");
		strAVP.setValue(avp.getLabelling_Logos());
		if(avp.getLabelling_Logos()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("labellingPackedStatement");
		strAVP.setValue(avp.getLabelling_Packed_Statement());
		if(avp.getLabelling_Packed_Statement()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("labellingProductName");
		strAVP.setValue(avp.getLabelling_Prod_Name());
		if(avp.getLabelling_Prod_Name()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("liquorAge");
		strAVP.setValue(avp.getLiquor_Age());
		if(avp.getLiquor_Age()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("liquorMarketSegment");
		strAVP.setValue(avp.getLiquor_Mkt_Segment());
		if(avp.getLiquor_Mkt_Segment()!=null)
			gs1Attr.getStringAVP().add(strAVP);	
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("manufacturerInternalReference");
		strAVP.setValue(avp.getMfr_Internal_Ref());
		if(avp.getMfr_Internal_Ref()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("nesting");
		strAVP.setValue(avp.getNesting());
		if(avp.getNesting()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("numberOfBaseUnits");
		strAVP.setValue(avp.getBase_Unit_Qty());
		if(avp.getBase_Unit_Qty()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("optionalNutrientCode");
		strAVP.setValue(avp.getOptional_Nutrient_Code());
		if(avp.getOptional_Nutrient_Code()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		if(avp.getaVPPallet()!=null) {
			strAVP = new StringAttributeValuePairType();
			strAVP.setAttributeName("baseUnitsPerPallet");
			strAVP.setValue(String.valueOf(avp.getaVPPallet().getBaseUnitsPerPallet()));
			gs1Attr.getStringAVP().add(strAVP);
			
			strAVP = new StringAttributeValuePairType();
			strAVP.setAttributeName("palletDepth");
			strAVP.setValue(String.valueOf(avp.getaVPPallet().getPalletDepth()));
			gs1Attr.getStringAVP().add(strAVP);
			
			strAVP = new StringAttributeValuePairType();
			strAVP.setAttributeName("palletGrossWeight");
			strAVP.setValue(String.valueOf(avp.getaVPPallet().getPalletGrossWeight()));
			gs1Attr.getStringAVP().add(strAVP);
			
			strAVP = new StringAttributeValuePairType();
			strAVP.setAttributeName("palletHeight");
			strAVP.setValue(String.valueOf(avp.getaVPPallet().getPalletHeight()));
			gs1Attr.getStringAVP().add(strAVP);
			
		
			strAVP = new StringAttributeValuePairType();
			strAVP.setAttributeName("palletNetWeight");
			strAVP.setValue(String.valueOf(avp.getaVPPallet().getPalletNetWeight()));
			gs1Attr.getStringAVP().add(strAVP);
			
			strAVP = new StringAttributeValuePairType();
			strAVP.setAttributeName("palletPattern");
			strAVP.setValue(avp.getaVPPallet().getPallet_Build());
			gs1Attr.getStringAVP().add(strAVP);
			
			strAVP = new StringAttributeValuePairType();
			strAVP.setAttributeName("palletVolume");
			strAVP.setValue(String.valueOf(avp.getaVPPallet().getPalletVolume()));
			gs1Attr.getStringAVP().add(strAVP);
			
			strAVP = new StringAttributeValuePairType();
			strAVP.setAttributeName("palletWidth");
			strAVP.setValue(String.valueOf(avp.getaVPPallet().getPalletWidth()));
			gs1Attr.getStringAVP().add(strAVP);
		}
		
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("promotionalItemIndicator");
		strAVP.setValue(avp.getPromotional_Product());
		if(avp.getPromotional_Product()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("sdsIssueDate");
		strAVP.setValue(avp.getSds_Issue_Date());
		if(avp.getSds_Issue_Date()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("shelfLifeAfterThawedBack");
		strAVP.setValue(avp.getShelf_Life_After_Thaw());
		if(avp.getShelf_Life_After_Thaw()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("suitableFor");
		strAVP.setValue(avp.getSuitable_For());
		if(avp.getSuitable_For()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("sweetnessLevelIndicator");
		strAVP.setValue(avp.getSweetness_Level_Ind());
		if(avp.getSweetness_Level_Ind()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("tradeItemGramMeasureDeclaration");
		strAVP.setValue(avp.getGram_Measure_Dec());
		if(avp.getGram_Measure_Dec()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("tradeItemTotalEnergyDeclaration");
		strAVP.setValue(avp.getTotal_Energy_Dec());
		if(avp.getTotal_Energy_Dec()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("tradeMeasurementMethod");
		strAVP.setValue(avp.getTrade_Measurement_Method());
		if(avp.getTrade_Measurement_Method()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("unitsInDepthOfLayer");
		strAVP.setValue(avp.getChild_Layer_Unit_Depth());
		if(avp.getChild_Layer_Unit_Depth()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("unitsInWidthOfLayer");
		strAVP.setValue(avp.getChild_Layer_Unit_Width());
		if(avp.getChild_Layer_Unit_Width()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("warningStatementOnPackage");
		strAVP.setValue(avp.getWarning_On_Package());
		if(avp.getWarning_On_Package()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		strAVP = new StringAttributeValuePairType();
		strAVP.setAttributeName("woodedCode");
		strAVP.setValue(avp.getWooded_Code());
		if(avp.getWooded_Code()!=null)
			gs1Attr.getStringAVP().add(strAVP);
		
		baseTradeItem.setAvpList(gs1Attr);
		
		return baseTradeItem;
	}

	

	@RequestMapping(value = "/validateProduct", method = RequestMethod.GET, produces = "application/json")
	public Result getQuestions(@RequestParam(required = true) Integer Product_Id, 
							   @RequestParam(required = true) Integer Product_Hierarchy_Id,
							   @RequestParam(required = true) Integer Client_Id,
							   @RequestParam(required = false) Integer Org_Id
							   )

	{
		
		Integer Validation_Domain_Id = 15568;
		
		ProductCore product = productCoreDAO.getAllProductAttributes(Product_Id, Product_Hierarchy_Id, Client_Id, Org_Id);		
		List<GPCAttributes> lstGPCAttr =  GPCAttributeDAO.getAllGPCAttributesForProduct(Product_Id, Product_Hierarchy_Id, Client_Id, Org_Id);
		product.setLstGPCAttributes(lstGPCAttr);		
		List<GTINCountryOrigin> lstGTINCountryOrigin =  GTINCountryOriginDAO.getAllGTINCountryOriginAttributesForProduct(Product_Id, Product_Hierarchy_Id, Client_Id, Org_Id);
		product.setLstGTINCountryOrigin(lstGTINCountryOrigin);		
		String strCOOIds = "";// Check with Mark if this should be hardcoded? TODO
		String Target_Market = "AU"; // Check with Mark if this should be hardcoded? TODO -- Only in Hierarchy Rule - Update the flag to Hierarchy Rule
		Integer Product_Export_Hdr_Id = 1;// Check with Mark this should not be hardcoded? TODO -- Only in Hierarchy Rule - Update the flag to Hierarchy Rule
		for (GTINCountryOrigin eleGTINCountryOrigin:lstGTINCountryOrigin) {
			if(!strCOOIds.equals(""))
				strCOOIds+= ",";
			strCOOIds= strCOOIds + eleGTINCountryOrigin.getCountry_Origin_Id();
		}
		//strCOOIds = "5704";
		
		
		
		List<OrderQty> lstOrderQty =  orderQtyDAO.getAllOrderQtyForProduct(Product_Id, Product_Hierarchy_Id, Client_Id, Org_Id);
		product.setLstOrderQty(lstOrderQty);
		
		List<Duty> lstDuty =  dutyDAO.getAllDutyForProduct(Product_Id, Product_Hierarchy_Id, Client_Id, Org_Id);
		product.setLstDuty(lstDuty);
		
		List<PalletAttributes> lstPalletAttributes =  palletAttributesDAO.getAllPalletAttributesForProduct(Product_Id, Product_Hierarchy_Id, Client_Id, Org_Id);
		product.setLstPalletAttributes(lstPalletAttributes);
		
		List<UNSPSCAttributes> lstUNSPSCAttributes =  UNSPSCAttributesDAO.getAllUNSPSCAttributesForProduct(Product_Id, Product_Hierarchy_Id, Client_Id, Org_Id);
		product.setLstUNSPSCAttributes(lstUNSPSCAttributes);
		
		
		List<AlcoholPercentage> lstAlcoholPercentage =  alcoholPercentageDAO.getAllAlcoholPercentageForProduct(Product_Id, Product_Hierarchy_Id, Client_Id, Org_Id);
		product.setLstAlcoholPercentage(lstAlcoholPercentage);
		
		
		String Product_Hierarchy_id = Product_Hierarchy_Id.toString();
		List<Packaging> lstPackaging = packagingDAO.getAllPackaging(Product_Id, Product_Hierarchy_id);
		product.setLstPackaging(lstPackaging);
		
		List<Packaging_Material> lstPackagingMaterial = packaging_MaterialDAO.getAllPackaging_Material(Product_Id,Product_Hierarchy_id);
		product.setLstPackagingMaterial(lstPackagingMaterial);
		
		List<Packaging_Function> lstPackagingFunction = packaging_FunctionDAO.getAllPackaging_Function(Product_Id, Product_Hierarchy_id);
		product.setLstPackagingFunction(lstPackagingFunction);
		product.setLstPackaging_Deposit(packaging_DepositDAO.getAllPackaging_Deposit(Product_Id, Product_Hierarchy_id));
		product.setLstPackaging_Recycle(packaging_RecycleDAO.getAllPackaging_Recycle(Product_Id,Product_Hierarchy_id));
		product.setLstPackaging_Recycle_Scheme(packaging_Recycle_SchemeDAO.getAllPackaging_Recycle_Scheme(Product_Id,Product_Hierarchy_id));
		product.setLstPackaging_Sustainability(packaging_SustainabilityDAO.getAllPackaging_Sustainability(Product_Id, Product_Hierarchy_id));
	
		

		
//		AVP avp = avpDAO.getAllAVP(Product_Id, Product_Hierarchy_id, strCOOIds, Client_Id, Org_Id);
//		product.setAVP(avp);
		product.setaVPPallet(avpPalletDAO.getAllAVPPallet(Product_Hierarchy_id));
		product.setLstAVPCustomsExcisableValue(avpCustomsExcisableValueDAO.getAllPackaging(Product_Id, Product_Hierarchy_id, strCOOIds, Target_Market));
		product.setLstAVPHsnoApproval(avpHsnoApprovalDAO.getAllPackaging(Product_Id));
		product.setLstAVPEnvironmental(avpEnvironmentalDAO.getAllPackaging(Product_Id));
		product.setLstAVPHsnoClassification(avpHsnoClassificationDAO.getAllPackaging(Product_Id));
		

		product.setLstTradeItemMeasurements(tradeItemMeasurementsDAO.getAllTradeItemMeasurements(Product_Id, Product_Hierarchy_id));
		product.setLstTradeItemNetContent(tradeItemNetContentDAO.getAllTradeItemNetContent(Product_Id, Product_Hierarchy_id));
		product.setLstTradeItemPegMeasurements(tradeItemPegMeasurementsDAO.getAllTradeItemPegMeasurements(Product_Id));
		
		
		
		product.setLstTradeItemDescriptionInformation(tradeItemDescriptionInformationDAO.getAllTradeItemDescriptionInformation(Product_Id, Product_Hierarchy_id));
		product.setLstTradeItemVariant_Desc(tradeItemVariant_DescDAO.getAllTradeItemVariant_Desc(Product_Id));
	
		product.setLstDates(datesDAO.getAllDates( Product_Hierarchy_id, Product_Export_Hdr_Id,Client_Id));
		
		product.setLstTradeItemHierarchyQuantity(tradeItemHierarchyQuantityDAO.getAllTradeItemHierarchyQuantity(Product_Id, Product_Hierarchy_id));
		
		
		List<Packaging_Marking> lstPackaging_Marking = packaging_MarkingDAO.getAllPackaging_Marking(Product_Id, Product_Hierarchy_id);
		List<Packaging_Date> lstPackaging_Date = packaging_DateDAO.getAllPackaging_Date(Product_Id, Product_Hierarchy_id);
		List<Packaging_Returnable> lstPackaging_Returnable = packaging_ReturnableDAO.getAllPackaging_Returnable(Product_Id, Product_Hierarchy_id);
	
		product.setLstPackaging_Marking(lstPackaging_Marking);
		product.setLstPackaging_Date(lstPackaging_Date);
		product.setLstPackaging_Returnable(lstPackaging_Returnable);
		
		
		product.setLstTradeItemTemperatureInformation(tradeItemTemperatureInformationDAO.getAllTradeItemTemperatureInformation(Product_Id));
		
		product.setLstLeadTimes(leadTimesDAO.getAllLeadTimes(Product_Id,Product_Export_Hdr_Id,Client_Id));

		
		product.setLstNutrient_Header(nutrient_HeaderDAO.getAllNutrient_Header(Product_Id));
		product.setLstNutrient_Information(nutrient_InformationDAO.getAllNutrient_Information(Product_Id));
		product.setLstNutrient_Serving_Size(nutrient_Serving_SizeDAO.getAllNutrient_Serving_Size(Product_Id));
		product.setLstNutritional_Claim(nutritional_ClaimDAO.getAllNutritional_Claim(Product_Id));
		product.setLstNutritional_Claim_Detail(nutritional_Claim_DetailDAO.getAllNutritional_Claim_Detail(Product_Id, Product_Hierarchy_id));
		product.setLstServing_Size_Desc(serving_Size_DescDAO.getAllServing_Size_Desc(Product_Id));
		
		List<AllergenInformationModule> lstAllergenInformationModule = allergenInformationModuleDAO.getAllAllergenInformationModule(Product_Id);
		product.setLstAllergenInformationModule(lstAllergenInformationModule);
		
		
		product.setLstGrowMethod(growMethodDAO.getAllGrow_Method(Product_Id));

		product.setLstProductCharacteristicsModule(productCharacteristicsModuleDAO.getAllProductCharacteristicsModule(Product_Id));
		
		product.setLstReferencedFileHeader(referencedHeaderDAO.getAllReferencedFileDetailInformationModule(Product_Id));

		
		product.setLstBattery_Type(batteryTypeDAO.getAllBatteryTypes(Product_Id));

		
		
		System.out.println("Product Details : " + product.toString()); 
		rules.reload(Product_Id,Client_Id, Validation_Domain_Id, 0);
		KieSession kieSession = null;
		Result result = null;
		
		for (String ruleId : ReloadDroolsRulesService.strRuleIdsToExecute.split(",")) {
			if (ruleId != null) {
				result = new Result();
				kieSession = ReloadDroolsRulesService.kieContainer.newKieSession();
				rulesValidationService.validateProductAdditional(product, result, kieSession, ruleId);
			}

		}
		
		//System.out.println(result.getRuleIdsFailed());
    	ProductDetails productGrpdetails = productDetailsDAO.getProductGroupSectorValuesById(Product_Id);		
    	
    	ValidationFailure vf = new ValidationFailure();
		vf.setClient_Id(Client_Id);
		vf.setProduct_Hierarchy_Id(Product_Hierarchy_Id);
		vf.setProduct_Id(Product_Id);
		validationFailureDAO.deleteExistingRecords(vf);
		
    	
		for (Integer failedRuleID:result.getRuleIdsFailed()) {
			
			vf = new ValidationFailure();
			vf.setClient_Id(Client_Id);
			vf.setCreate_Date(new Date());
			vf.setProd_Group(productGrpdetails.getProd_Group_Id());
			vf.setProduct_Hierarchy_Id(Product_Hierarchy_Id);
			vf.setProduct_Id(Product_Id);
			vf.setValidation_Rule_Id(failedRuleID);
			validationFailureDAO.insertValidationRuleFailre(vf);
		}


		return result;
	}

	
	@RequestMapping(value = "/exportProductCIP", method = RequestMethod.GET, produces = "application/json")
	public String exportProductCIP(@RequestParam(required = true) Integer Product_Recipient_Id, @RequestParam(required = true) String operation)
	{
		Integer Product_Export_Hdr_Id = null;
		Boolean Is_CIHW = false;
		ProductRecipientHeader prodRecHeader = productRecipientHeaderDAO.getAllProductRecipientHeader(Product_Recipient_Id, Is_CIHW);
		if(prodRecHeader!=null) {
			Product_Export_Hdr_Id = prodRecHeader.getProduct_Export_Hdr_Id();
		}
		
		String strHierarchyIds = prodRecHeader.getStrHierarchyIds();
		String strCOOIds = prodRecHeader.getStrCOOIds();
		String strSender_ContentOwner = "";
		CatalogueItemPublicationMessageType catalogueItemPublicationMessageType = objectFactory.createCatalogueItemPublicationMessageType();
		uniqueIdentifier = UUID.randomUUID();
		String target_market= "";
	
			StandardBusinessDocumentHeader standardBusinessDocumentHeader = new StandardBusinessDocumentHeader() ;
			standardBusinessDocumentHeader.setHeaderVersion("1.0");
			DocumentIdentification documentIdentification = new DocumentIdentification();
			documentIdentification.setStandard("GS1");
			documentIdentification.setType("catalogueItemPublication");
			documentIdentification.setTypeVersion("3.1");
			documentIdentification.setInstanceIdentifier(uniqueIdentifier.toString());
			documentIdentification.setMultipleType(false);
			
			try {
				documentIdentification.setCreationDateAndTime(getXMLGregorianCalendarNow());
			} catch (DatatypeConfigurationException e1) {				
				e1.printStackTrace();
			}
			standardBusinessDocumentHeader.setDocumentIdentification(documentIdentification);
			
			List<CIPHeader> lstCIPHeader = CIPHeaderDAO.getAllCIPHeader(Product_Export_Hdr_Id);
			for(CIPHeader eleCIPHeader : lstCIPHeader) {
				target_market = eleCIPHeader.getTarget_Market();
				
				Partner sender = new Partner();
				PartnerIdentification senderIdentifier = new PartnerIdentification();
				senderIdentifier.setAuthority("GS1");
				senderIdentifier.setValue(eleCIPHeader.getSender_Content_Owner());
				sender.setIdentifier(senderIdentifier);
				standardBusinessDocumentHeader.getSender().add(sender);
				strSender_ContentOwner = eleCIPHeader.getSender_Content_Owner();
				
				Partner receiver = new Partner();
				PartnerIdentification receiverIdentifier = new PartnerIdentification();
				receiverIdentifier.setAuthority("GS1");
				receiverIdentifier.setValue(eleCIPHeader.getReceiver_sourceDataPool());
				receiver.setIdentifier(receiverIdentifier);
				standardBusinessDocumentHeader.getReceiver().add(receiver);
			}
			catalogueItemPublicationMessageType.setStandardBusinessDocumentHeader(standardBusinessDocumentHeader);
			
			TransactionType transaction = new TransactionType();
			
			EntityIdentificationType transactionIdentification = new EntityIdentificationType();
			transactionIdentification.setEntityIdentification(uniqueIdentifier.toString());
			PartyIdentificationType txnContentOwner = new PartyIdentificationType();
			txnContentOwner.setGln(strSender_ContentOwner);
			transactionIdentification.setContentOwner(txnContentOwner);
			transaction.setTransactionIdentification(transactionIdentification);
			
			
			EntityIdentificationType entityIdentification = new EntityIdentificationType() ;
			entityIdentification.setEntityIdentification(uniqueIdentifier.toString());
			PartyIdentificationType contentOwner = new PartyIdentificationType();
			contentOwner.setGln(strSender_ContentOwner);
			entityIdentification.setContentOwner(contentOwner);
			
			
			DocumentCommandType documentCommand = new DocumentCommandType();
			DocumentCommandHeaderType documentCommandHeader = new DocumentCommandHeaderType();					
			documentCommandHeader.setType(DocumentCommandEnumerationType.fromValue(operation));
			documentCommandHeader.setDocumentCommandIdentification(entityIdentification);
			documentCommand.setDocumentCommandHeader(documentCommandHeader);
			
			
			
			CatalogueItemPublicationType catalogueItemPublication = new CatalogueItemPublicationType();
			try {
				catalogueItemPublication.setLastUpdateDateTime(getXMLGregorianCalendarNow());
				catalogueItemPublication.setCreationDateTime(getXMLGregorianCalendarNow());
			} catch (DatatypeConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catalogueItemPublication.setDocumentStatusCode(DocumentStatusEnumerationType.ORIGINAL);
			catalogueItemPublication.setDocumentStructureVersion("3.1");
			catalogueItemPublication.setCatalogueItemPublicationIdentification(entityIdentification);
			
			List<CIPPublishToGLN> lstCIPPublishToGLN = CIPPublishToGLNDAO.getAllCIPPublishToGLN(Product_Export_Hdr_Id,Product_Recipient_Id);
			
			for(CIPPublishToGLN eleCIPPublishToGLN : lstCIPPublishToGLN) {
				catalogueItemPublication.setPublishToGLN(eleCIPPublishToGLN.getPublishToGLN());
			}
			
			List<CIPGTIN> lstCIPGTIN = CIPGTINDAO.getAllCIPGTIN(prodRecHeader.getProduct_Id(), strHierarchyIds, strCOOIds);
			CatalogueItemReferenceType catalogueItemRef = new CatalogueItemReferenceType();

			for(CIPGTIN eleCIPGTIN : lstCIPGTIN) {
				catalogueItemRef.setGtin(eleCIPGTIN.getGtin());
			}
			catalogueItemRef.setDataSource(strSender_ContentOwner);
			CountryCodeType targetMarketCountryCode = new CountryCodeType();
			targetMarketCountryCode.setValue(target_market);
			catalogueItemRef.setTargetMarketCountryCode(targetMarketCountryCode);
			catalogueItemPublication.setCatalogueItemReference(catalogueItemRef);			
			documentCommand.getListCatalogueItemPublication().add(catalogueItemPublication);			
			transaction.setDocumentCommand(documentCommand);			
			catalogueItemPublicationMessageType.getTransaction().add(transaction);
		
		
		JAXBElement<CatalogueItemPublicationMessageType> jaxbCatalogueItemNotificationMessage = objectFactory.createCatalogueItemPublicationMessage(catalogueItemPublicationMessageType); 
		StringWriter sw = new StringWriter();

		
		try {

			
			JAXBContext jaxbContext = JAXBContext.newInstance("com.ascention.GS1.schema");
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			jaxbMarshaller.marshal(jaxbCatalogueItemNotificationMessage, sw);
			
	// Logic to remove all the empty tags
			
			final String regex1 = "<([a-zA-Z0-9-\\:_]*)[^>]*/>";
		    final String regex2 = "<([a-zA-Z0-9-\\:_]*)[^>]*>\\s*</\\1>";

		    String xmlString = sw.toString();
		   // System.out.println(xmlString);

		    final Pattern pattern1 = Pattern.compile(regex1);
		    final Pattern pattern2 = Pattern.compile(regex2);

		    Matcher matcher1;
		    Matcher matcher2;
		    do { 
		        xmlString = xmlString.replaceAll(regex1, "").replaceAll(regex2, "");
		        matcher1 = pattern1.matcher(xmlString);
		        matcher2 = pattern2.matcher(xmlString);
		    } while (matcher1.find() || matcher2.find());


		    
		    
		    System.out.println(xmlString);
			
		    FileWriterWithEncoding fw = new FileWriterWithEncoding("C:\\Export\\Export_Product_CIP_For_Export_Hdr_Id_"+ Product_Export_Hdr_Id + ".xml",StandardCharsets.UTF_8);			
			fw.write(xmlString);
			fw.close();
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
				
		//System.out.println("GS1 Export Details : " + gs1Export.toString()); 
		return sw.toString();
	}
	
	
	
	@RequestMapping(value = "/exportProductCIHW", method = RequestMethod.GET, produces = "application/json")
	public String exportProductCIHW(@RequestParam(required = true) Integer Product_Recipient_Id, @RequestParam(required = true) String operation
							   )
	{
		
		Integer Product_Export_Hdr_Id = null;
		Boolean Is_CIHW = true;
		ProductRecipientHeader prodRecHeader = productRecipientHeaderDAO.getAllProductRecipientHeader(Product_Recipient_Id, Is_CIHW);
		if(prodRecHeader!=null) {
			Product_Export_Hdr_Id = prodRecHeader.getProduct_Export_Hdr_Id();
		}
		
		
		String strHierarchyIds = "";
		String strCOOIds = "";
		String strSender_ContentOwner = "";
		String strReceiver_sourceDataPool = "";
		CatalogueItemHierarchicalWithdrawalMessageType catalogueItemHierarchicalWithdrawalMessageType = objectFactory.createCatalogueItemHierarchicalWithdrawalMessageType();
		uniqueIdentifier = UUID.randomUUID();
		
		
			
		strHierarchyIds = prodRecHeader.getStrHierarchyIds();
		strCOOIds = prodRecHeader.getStrCOOIds();
		String target_market= "";
		
		StandardBusinessDocumentHeader standardBusinessDocumentHeader = new StandardBusinessDocumentHeader() ;
		standardBusinessDocumentHeader.setHeaderVersion("1.0");
		DocumentIdentification documentIdentification = new DocumentIdentification();
		documentIdentification.setStandard("GS1");
		documentIdentification.setType("catalogueItemHierarchicalWithdrawal");
		documentIdentification.setTypeVersion("3.1");
		documentIdentification.setInstanceIdentifier(uniqueIdentifier.toString());
		documentIdentification.setMultipleType(false);
		
		try {
			documentIdentification.setCreationDateAndTime(getXMLGregorianCalendarNow());
		} catch (DatatypeConfigurationException e1) {				
			e1.printStackTrace();
		}
		standardBusinessDocumentHeader.setDocumentIdentification(documentIdentification);
		
		List<CIPHeader> lstCIPHeader = CIPHeaderDAO.getAllCIPHeader(Product_Export_Hdr_Id);
		for(CIPHeader eleCIPHeader : lstCIPHeader) {
			target_market = eleCIPHeader.getTarget_Market();
			
			Partner sender = new Partner();
			PartnerIdentification senderIdentifier = new PartnerIdentification();
			senderIdentifier.setAuthority("GS1");
			senderIdentifier.setValue(eleCIPHeader.getSender_Content_Owner());
			sender.setIdentifier(senderIdentifier);
			standardBusinessDocumentHeader.getSender().add(sender);
			strSender_ContentOwner = eleCIPHeader.getSender_Content_Owner();
			
			Partner receiver = new Partner();
			PartnerIdentification receiverIdentifier = new PartnerIdentification();
			receiverIdentifier.setAuthority("GS1");
			receiverIdentifier.setValue(eleCIPHeader.getReceiver_sourceDataPool());
			strReceiver_sourceDataPool = eleCIPHeader.getReceiver_sourceDataPool();
			receiver.setIdentifier(receiverIdentifier);
			standardBusinessDocumentHeader.getReceiver().add(receiver);
		}
		catalogueItemHierarchicalWithdrawalMessageType.setStandardBusinessDocumentHeader(standardBusinessDocumentHeader);
		
		TransactionType transaction = new TransactionType();
		
		EntityIdentificationType transactionIdentification = new EntityIdentificationType();
		transactionIdentification.setEntityIdentification(uniqueIdentifier.toString());
		PartyIdentificationType txnContentOwner = new PartyIdentificationType();
		txnContentOwner.setGln(strSender_ContentOwner);
		transactionIdentification.setContentOwner(txnContentOwner);
		transaction.setTransactionIdentification(transactionIdentification);
		
		
		EntityIdentificationType entityIdentification = new EntityIdentificationType() ;
		entityIdentification.setEntityIdentification(uniqueIdentifier.toString());
		PartyIdentificationType contentOwner = new PartyIdentificationType();
		contentOwner.setGln(strSender_ContentOwner);
		entityIdentification.setContentOwner(contentOwner);
		
		
		DocumentCommandType documentCommand = new DocumentCommandType();
		DocumentCommandHeaderType documentCommandHeader = new DocumentCommandHeaderType();					
		documentCommandHeader.setType(DocumentCommandEnumerationType.fromValue(operation));
		documentCommandHeader.setDocumentCommandIdentification(entityIdentification);
		documentCommand.setDocumentCommandHeader(documentCommandHeader);
		
		
		
		CatalogueItemHierarchicalWithdrawalType catalogueItemHierarchicalWithdrawal = new CatalogueItemHierarchicalWithdrawalType();
		try {
			//catalogueItemHierarchicalWithdrawal.setLastUpdateDateTime(getXMLGregorianCalendarNow());
			catalogueItemHierarchicalWithdrawal.setCreationDateTime(getXMLGregorianCalendarNow());
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catalogueItemHierarchicalWithdrawal.setDocumentStatusCode(DocumentStatusEnumerationType.ORIGINAL);
		catalogueItemHierarchicalWithdrawal.setDocumentStructureVersion("3.1");
		EntityIdentificationType cihw_entityIdentification = new EntityIdentificationType() ;
		cihw_entityIdentification.setEntityIdentification("CIHW_"+uniqueIdentifier.toString());
		PartyIdentificationType cihw_contentOwner = new PartyIdentificationType();
		cihw_contentOwner.setGln(strSender_ContentOwner);
		cihw_entityIdentification.setContentOwner(cihw_contentOwner);
		
		catalogueItemHierarchicalWithdrawal.setCatalogueItemHierarchicalWithdrawalIdentification(cihw_entityIdentification);
		
		List<CIPPublishToGLN> lstCIPPublishToGLN = CIPPublishToGLNDAO.getAllCIPPublishToGLN(Product_Export_Hdr_Id,Product_Recipient_Id);
		
		for(CIPPublishToGLN eleCIPPublishToGLN : lstCIPPublishToGLN) {				
			PartyIdentificationType dataRecepient = new PartyIdentificationType();
			dataRecepient.setGln(eleCIPPublishToGLN.getPublishToGLN());
			catalogueItemHierarchicalWithdrawal.getDataRecipient().add(dataRecepient);
		}
		
		PartyIdentificationType srcDataPool = new PartyIdentificationType();
		srcDataPool.setGln(strReceiver_sourceDataPool);
		catalogueItemHierarchicalWithdrawal.setSourceDataPool(srcDataPool);
		
		List<CIPGTIN> lstCIPGTIN = CIPGTINDAO.getAllCIPGTIN(prodRecHeader.getProduct_Id(), strHierarchyIds, strCOOIds);
		CatalogueItemReferenceType catalogueItemRef = new CatalogueItemReferenceType();

		for(CIPGTIN eleCIPGTIN : lstCIPGTIN) {
			catalogueItemRef.setGtin(eleCIPGTIN.getGtin());
		}
		catalogueItemRef.setDataSource(strSender_ContentOwner);
		CountryCodeType targetMarketCountryCode = new CountryCodeType();
		targetMarketCountryCode.setValue(target_market);
		catalogueItemRef.setTargetMarketCountryCode(targetMarketCountryCode);
		catalogueItemHierarchicalWithdrawal.setCatalogueItemReference(catalogueItemRef);	
		
		catalogueItemHierarchicalWithdrawal.setCatalogueItemStateCode(CatalogueItemStateEnumerationType.REGISTERED);
		HierarchyDeletionReasonCodeType hierarchyDeletionReasonCode = new HierarchyDeletionReasonCodeType();
		hierarchyDeletionReasonCode.setValue("PUBLICATION_WITHDRAWAL");
		hierarchyDeletionReasonCode.setCodeListVersion("R1");
		catalogueItemHierarchicalWithdrawal.setHierarchyDeletionReasonCode(hierarchyDeletionReasonCode);
		documentCommand.getListCatalogueItemHierarchicalWithdrawal().add(catalogueItemHierarchicalWithdrawal);			
		transaction.setDocumentCommand(documentCommand);			
		catalogueItemHierarchicalWithdrawalMessageType.getTransaction().add(transaction);
	
		
		JAXBElement<CatalogueItemHierarchicalWithdrawalMessageType> jaxbCatalogueItemNotificationMessage = objectFactory.createCatalogueItemHierarchicalWithdrawalMessage(catalogueItemHierarchicalWithdrawalMessageType); 
		StringWriter sw = new StringWriter();

		
		try {

			
			//JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactoryCatalogueItemNotification.class);//"com.ascention.GS1.schema");
			JAXBContext jaxbContext = JAXBContext.newInstance("com.ascention.GS1.schema");
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			//jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.unece.org/cefact/namespaces/StandardBusinessDocumentHeader http://www.gs1globalregistry.net/3.1/schemas/sbdh/StandardBusinessDocumentHeader.xsd urn:gs1:gdsn:catalogue_item_notification:xsd:3 http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/CatalogueItemNotification.xsd urn:gs1:gdsn:alcohol_information:xsd:3 urn:gs1:gdsn:alcohol_information:xsd:3 http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/AlcoholInformationModule.xsd");
			//JAXBElement<AlcoholInformationModuleType> jaxbElementAlcoholInformationModule = alm;
			
			jaxbMarshaller.marshal(jaxbCatalogueItemNotificationMessage, sw);
			//jaxbMarshaller.marshal(jaxbCatalogueItemNotificationMessage, System.out);
			//sw.write(sw.toString().replace("ns38:document xsi:type\"catalogue_item_notification:catalogueItemNotification\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "catalogue_item_notification:catalogueItemNotification"));
			//sw.write(sw.toString().replace("/ns38:document", "/catalogue_item_notification:catalogueItemNotification"));
			
			// Logic to remove all the empty tags
			
			final String regex1 = "<([a-zA-Z0-9-\\:_]*)[^>]*/>";
		    final String regex2 = "<([a-zA-Z0-9-\\:_]*)[^>]*>\\s*</\\1>";

		    String xmlString = sw.toString();
		   // System.out.println(xmlString);

		    final Pattern pattern1 = Pattern.compile(regex1);
		    final Pattern pattern2 = Pattern.compile(regex2);

		    Matcher matcher1;
		    Matcher matcher2;
		    do { 
		        xmlString = xmlString.replaceAll(regex1, "").replaceAll(regex2, "");
		        matcher1 = pattern1.matcher(xmlString);
		        matcher2 = pattern2.matcher(xmlString);
		    } while (matcher1.find() || matcher2.find());

	    
		    System.out.println(xmlString);
		    
		    FileWriterWithEncoding fw = new FileWriterWithEncoding("C:\\Export\\Export_Product_CIHW_For_Export_Hdr_Id_"+ Product_Export_Hdr_Id + ".xml", StandardCharsets.UTF_8);			
			fw.write(xmlString);
			fw.close();
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
				
		//System.out.println("GS1 Export Details : " + gs1Export.toString()); 
		return sw.toString();
	}
	
	@RequestMapping(value = "/validateProductHierarchy", method = RequestMethod.GET, produces = "application/json")
	public Result getQuestions(@RequestParam(required = true) Integer Product_Export_Hdr_Id
							   )
	{
		Integer Validation_Domain_Id = 15568;
		ProductExport prodExport = new ProductExport();
		List<ProductExportHeader> lstProductExportHdr = productExportHeaderDAO.getAllProductExportHeader(Product_Export_Hdr_Id);
		prodExport.setLstProductExportHdr(lstProductExportHdr);

		String strHierarchyIds = "";
		String strCOOIds = "";
		String Target_Market ="";
		
		for(ProductExportHeader prodExportHdr: lstProductExportHdr ) {
			prodExport.setClient_Id(prodExportHdr.getClient_Id());
			prodExport.setOrg_Id(prodExportHdr.getOrg_Id());
			prodExport.setProduct_Id(prodExportHdr.getProduct_Id());
			if(strHierarchyIds.equals("")){
				strHierarchyIds = prodExportHdr.getProduct_Hierarchy_Id().toString();
				strCOOIds = prodExportHdr.getCountry_Origin_Id().toString();
			}else {
				strHierarchyIds += "," + prodExportHdr.getProduct_Hierarchy_Id().toString();
				strCOOIds += "," + prodExportHdr.getCountry_Origin_Id().toString();
			}
			Target_Market = prodExportHdr.getTarget_Market();
		}
		
		Integer Product_Id = prodExport.getProduct_Id();
		
		prodExport.setStrCOOIds(strCOOIds);
		prodExport.setStrHierarchyIds(strHierarchyIds);
		
		List<CountryActiveList> lstActiveCountries = countryActiveListDAO.getAllActiveCountriesList(strCOOIds);		
		if(lstActiveCountries!=null) {
			if(lstActiveCountries.size()>0) {
				prodExport.setIs_Country_In_CodeList("1");
			}else {
				prodExport.setIs_Country_In_CodeList("0");
			}			
		}else {
			prodExport.setIs_Country_In_CodeList("0");
		}
	
		
		
		List<ProductHierarchy> lstProductHierarchy = productHierarchyDAO.getAllProductHierachyAttributes(prodExport.getProduct_Id(), strHierarchyIds, prodExport.getClient_Id(), prodExport.getOrg_Id());
		prodExport.setLstProductHierarchy(lstProductHierarchy);
		
		prodExport.setHierarchyLevels(lstProductHierarchy);
		prodExport.setNumberOfLevels(lstProductHierarchy.size());
		
		List<GPCAttributes> lstGPCAttributes = hierarchyGPCAttributesDAO.getAllGPCAttributesForHierarchy(prodExport.getProduct_Id(),  prodExport.getClient_Id(), prodExport.getOrg_Id());
		prodExport.setLstGPCAttributes(lstGPCAttributes);
		
		List<HierarchyAlcoholPercentage> lstHierarchyAlcoholPercentage = hierarchyAlcoholPercentageDAO.getAllAlcoholPercentageForProduct(prodExport.getProduct_Id(), strCOOIds, prodExport.getClient_Id(), prodExport.getOrg_Id());
		prodExport.setLstHierarchyAlcoholPercentage(lstHierarchyAlcoholPercentage);
	
		
		List<HierarchyDuty> lstHierarchyDuty =  hierarchyDutyDAO.getAllHierarchyDutyForProduct(prodExport.getProduct_Id(), strHierarchyIds, prodExport.getClient_Id(), prodExport.getOrg_Id());
		prodExport.setLstHierarchyDuty(lstHierarchyDuty);
		
		List<HierarchyPalletAttributes> lstHierarchyPalletAttributes =  hierarchyPalletAttributesDAO.getAllHierarchyPalletAttributesForProduct(prodExport.getProduct_Id(), strHierarchyIds,prodExport.getClient_Id(), prodExport.getOrg_Id());
		prodExport.setLstHierarchyPalletAttributes(lstHierarchyPalletAttributes);	
		
		
		List<HierarchyUNSPSCAttributes> lstHierarchyUNSPSCAttributes =  hierarchyUNSPSCAttributesDAO.getAllHierarchyUNSPSCAttributesForProduct(prodExport.getProduct_Id(), prodExport.getClient_Id(), prodExport.getOrg_Id());
		prodExport.setLstHierarchyUNSPSCAttributes(lstHierarchyUNSPSCAttributes);
		
		List<TradeItemInformationProviderDetails> lstTradeItemInformationProviderDetails = tradeItemInformationProviderDetailsDAO.getAllTradeItemInformationProviderDetails(Product_Export_Hdr_Id);
		prodExport.setLstTradeItemInformationProviderDetails(lstTradeItemInformationProviderDetails);
		
		List<AVPHsnoClassification> lstAVPHsnoClassification = avpHsnoClassificationDAO.getAllPackaging(Product_Id);
		prodExport.setLstAVPHsnoClassification(lstAVPHsnoClassification);
		
		
		
		for(ProductHierarchy eleProductHierarchy : lstProductHierarchy) {
			
			eleProductHierarchy.setLstPackaging_Material(packaging_MaterialDAO.getAllPackaging_Material(Product_Id, eleProductHierarchy.getProduct_Hierarchy_Id().toString()));
			eleProductHierarchy.setLstHierarchyOrderQty(hierarchyOrderQtyDAO.getAllHierarchyOrderQtyForProduct(Product_Id, eleProductHierarchy.getProduct_Hierarchy_Id().toString(), prodExport.getClient_Id(), prodExport.getOrg_Id()));
			eleProductHierarchy.setLstLeadTimes(leadTimesDAO.getAllLeadTimes(Product_Id,Product_Export_Hdr_Id,prodExport.getClient_Id()));
			eleProductHierarchy.setLstHierarchyGTINCountryOrigin(hierarchyGTINCountryOriginDAO.getAllGTINCountryOriginAttributesForProduct(prodExport.getProduct_Id(), eleProductHierarchy.getProduct_Hierarchy_Id().toString(), strCOOIds, prodExport.getClient_Id(), prodExport.getOrg_Id()));
			eleProductHierarchy.setaVPPallet(avpPalletDAO.getAllAVPPallet(eleProductHierarchy.getProduct_Hierarchy_Id().toString()));
			//eleProductHierarchy.setAvp(avpDAO.getAllAVP(Product_Id, eleProductHierarchy.getProduct_Hierarchy_Id().toString(), strCOOIds, prodExport.getClient_Id(), prodExport.getOrg_Id()));
			eleProductHierarchy.setLstTradeItemHierarchyQuantity(tradeItemHierarchyQuantityDAO.getAllTradeItemHierarchyQuantity(Product_Id, eleProductHierarchy.getProduct_Hierarchy_Id().toString()));
			
			eleProductHierarchy.setLstTradeItemDescriptionInformation(tradeItemDescriptionInformationDAO.getAllTradeItemDescriptionInformation(Product_Id, eleProductHierarchy.getProduct_Hierarchy_Id().toString()));
			
			eleProductHierarchy.setLstDates(datesDAO.getAllDates(eleProductHierarchy.getProduct_Hierarchy_Id().toString(), Product_Export_Hdr_Id, prodExport.getClient_Id()));
			eleProductHierarchy.setLstTradeItemNetContent(tradeItemNetContentDAO.getAllTradeItemNetContent(Product_Id, eleProductHierarchy.getProduct_Hierarchy_Id().toString()));
			eleProductHierarchy.setAlcoholInformationModule(alcoholInformationModuleDAO.getAllAlcoholInformationModule(Product_Id, eleProductHierarchy.getProduct_Hierarchy_Id().toString(), strCOOIds, prodExport.getClient_Id(), prodExport.getOrg_Id()));
			
			
			eleProductHierarchy.setLstAVPCustomsExcisableValue(avpCustomsExcisableValueDAO.getAllPackaging(Product_Id, eleProductHierarchy.getProduct_Hierarchy_Id().toString(), strCOOIds, Target_Market));
			
			eleProductHierarchy.setLstNutritional_Claim_Detail(nutritional_Claim_DetailDAO.getAllNutritional_Claim_Detail(Product_Id,eleProductHierarchy.getProduct_Hierarchy_Id().toString()));
			
			eleProductHierarchy.setLstPackaging_Function(packaging_FunctionDAO.getAllPackaging_Function(Product_Id, eleProductHierarchy.getProduct_Hierarchy_Id().toString()));
			eleProductHierarchy.setLstPackaging_Deposit(packaging_DepositDAO.getAllPackaging_Deposit(Product_Id, eleProductHierarchy.getProduct_Hierarchy_Id().toString()));
			eleProductHierarchy.setLstPackaging_Date(packaging_DateDAO.getAllPackaging_Date(Product_Id, eleProductHierarchy.getProduct_Hierarchy_Id().toString()));
			
			
			eleProductHierarchy.setLstDeliveryPurchasingInformationModule(deliveryPurchasingInformationModuleDAO.getAllDeliveryPurchasingInformationModule(Product_Id, eleProductHierarchy.getProduct_Hierarchy_Id().toString()));
			
		}

		
//		prodExport.setLstPackaging(packagingDAO.getAllPackaging(Product_Id, strHierarchyIds));
//		prodExport.setLstPackaging_Deposit(packaging_DepositDAO.getAllPackaging_Deposit(Product_Id, strHierarchyIds));
//		prodExport.setLstPackaging_Function(packaging_FunctionDAO.getAllPackaging_Function(Product_Id, strHierarchyIds));
//		prodExport.setLstPackaging_Recycle(packaging_RecycleDAO.getAllPackaging_Recycle(Product_Id, strHierarchyIds));
//		prodExport.setLstPackaging_Recycle_Scheme(packaging_Recycle_SchemeDAO.getAllPackaging_Recycle_Scheme(Product_Id, strHierarchyIds));
//		prodExport.setLstPackaging_Sustainability(packaging_SustainabilityDAO.getAllPackaging_Sustainability(Product_Id, strHierarchyIds));
//		
		
		System.out.println("Product Hierarchy Details : " + prodExport.toString()); 
		rules.reload(prodExport.getProduct_Id(),prodExport.getClient_Id(), Validation_Domain_Id, 1);
		KieSession kieSession = null;
		Result result = null;
		
		for (String ruleId : ReloadDroolsRulesService.strRuleIdsToExecute.split(",")) {
			if (ruleId != null) {
				result = new Result();
				kieSession = ReloadDroolsRulesService.kieContainer.newKieSession();
				rulesValidationService.validateProductHierarchy(prodExport, result, kieSession, ruleId);
			}

		}
		
		//System.out.println(result.getRuleIdsFailed());
    	ProductDetails productGrpdetails = productDetailsDAO.getProductGroupSectorValuesById(prodExport.getProduct_Id());		
    	
    	ValidationFailure vf = new ValidationFailure();
		vf.setClient_Id(prodExport.getClient_Id());
		//vf.setProduct_Hierarchy_Id(Product_Hierarchy_Id);
		vf.setProduct_Id(prodExport.getProduct_Id());
		validationFailureDAO.deleteExistingRecords(vf);
		
    	
		for (Integer failedRuleID:result.getRuleIdsFailed()) {
			
			vf = new ValidationFailure();
			vf.setClient_Id(prodExport.getClient_Id());
			vf.setCreate_Date(new Date());
			vf.setProd_Group(productGrpdetails.getProd_Group_Id());
			//vf.setProduct_Hierarchy_Id(Product_Hierarchy_Id);
			vf.setProduct_Id(prodExport.getProduct_Id());
			vf.setValidation_Rule_Id(failedRuleID);
			validationFailureDAO.insertValidationRuleFailre(vf);
		}


		return result;
	}
	
	@RequestMapping(value = "/validateProductAdditionalWithJSONArray", method = RequestMethod.GET, produces = "application/json")
	public Result getQuestions(@RequestBody ProductAdditional[] productAdditionalObjects) {
		//rules.reload("Sellable", 3);
		KieSession kieSession = ReloadDroolsRulesService.kieContainer.newKieSession();

		Result result = new Result();
		List<ProductAdditional> lstProductAdditional = new ArrayList<ProductAdditional>();
		lstProductAdditional = Arrays.asList(productAdditionalObjects);
		ProductAdditionalArray pArr = new ProductAdditionalArray();
		pArr.setLstProductAdditional(lstProductAdditional);

		rulesValidationService.validateProductAdditionalObjectArray(pArr, result, kieSession);
		return result;
	}

	private boolean isEmptyObject(final Object o) {
		boolean allIsNullButId = Arrays.stream(o.getClass().getDeclaredFields()).allMatch(new Predicate<Field>() {
			public boolean test(Field f) {
			    try {
			        return ( f.get(o) != null);
			    } catch (IllegalAccessException e) {
			        return false;
			    }
			}
		});
		return allIsNullButId;
	}
	
}
