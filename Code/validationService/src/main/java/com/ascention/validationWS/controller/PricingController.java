package com.ascention.validationWS.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ascention.GS1.pricing.schema.*;
import com.ascention.validationWS.beans.PricingCore;
import com.ascention.validationWS.beans.Result;
import com.ascention.validationWS.beans.UpdatePriceRelationshipSequence;
import com.ascention.validationWS.dao.UpdatePriceRelationshipSequenceDAO;
import com.ascention.validationWS.exportGS1.beans.PricingPSDDetail;
import com.ascention.validationWS.exportGS1.beans.PricingPSDHeader;

import com.ascention.validationWS.exportGS1.beans.PricingPSDShipTo;
import com.ascention.validationWS.exportGS1.beans.PricingPSDSuperseded;
import com.ascention.validationWS.exportGS1.beans.PricingPSDSupersededPriceExport;
import com.ascention.validationWS.exportGS1.beans.PricingPSR;
import com.ascention.validationWS.exportGS1.beans.PricingTierRule;
import com.ascention.validationWS.exportGS1.dao.PricingPSDDetailDAO;
import com.ascention.validationWS.exportGS1.dao.PricingPSDHeaderDAO;
import com.ascention.validationWS.exportGS1.dao.PricingPSDShipToDAO;
import com.ascention.validationWS.exportGS1.dao.PricingPSDSupersededDAO;
import com.ascention.validationWS.exportGS1.dao.PricingPSDSupersededPriceExportDAO;
import com.ascention.validationWS.exportGS1.dao.PricingPSRDAO;
import com.ascention.validationWS.exportGS1.dao.PricingTierRuleDAO;
import com.ascention.validationWS.service.ReloadDroolsRulesService;
import com.ascention.validationWS.service.RulesValidationService;

@RestController
public class PricingController {

	private static final ObjectFactory pricingObjFactory = new ObjectFactory();
	private final RulesValidationService rulesValidationService;
	
	@Resource
	private ReloadDroolsRulesService rules;
	private UUID uniqueIdentifier = UUID.randomUUID();
	@Autowired
	private PricingPSDDetailDAO pricingPSDDetailDAO;
	@Autowired
	private PricingPSDHeaderDAO pricingPSDHeaderDAO;
	
	@Autowired
	private PricingPSDShipToDAO pricingPSDShipToDAO;
	@Autowired
	private PricingPSRDAO pricingPSRDAO;
	@Autowired
	private PricingTierRuleDAO pricingTierRuleDAO;
	@Autowired
	private PricingPSDSupersededDAO pricingPSDSupersededDAO;
	@Autowired
	private PricingPSDSupersededPriceExportDAO pricingPSDSupersededPriceExportDAO;
	@Autowired
	private UpdatePriceRelationshipSequenceDAO updatePriceRelationshipSequenceDAO;
	
	@Value("${server.port}")
	private int serverPort;
	@Autowired
	public PricingController(RulesValidationService rulesValidationService) {
		this.rulesValidationService = rulesValidationService;
	}
	
	
	@RequestMapping(value = "/exportPSRXML", produces = "application/json")
	public String exportPSRData(@RequestParam(required = false) Integer Price_Relationship, @RequestParam(required = true) String operation) {
		uniqueIdentifier = UUID.randomUUID();
		PriceSynchronisationDocumentMessageType priceSynchronisationDocumentMessageType = pricingObjFactory
				.createPriceSynchronisationDocumentMessageType();
		
		List<PricingPSR> lstPSR = pricingPSRDAO.getAllPricingPSR( Price_Relationship);
		//List<PricingPSDHeader> lstPSDHeader = pricingPSDHeaderDAO.getAllPriceBasisQuantity(Price_Export);

		for (PricingPSR elePSR : lstPSR) {

			
			StandardBusinessDocumentHeader standardBusinessDocHeader = new StandardBusinessDocumentHeader();
			standardBusinessDocHeader.setHeaderVersion("1.0");

			Partner sender = new Partner();
			PartnerIdentification senderId = new PartnerIdentification();
			senderId.setAuthority("GS1");
			senderId.setValue(elePSR.getContentOwner());
			sender.setIdentifier(senderId);
			standardBusinessDocHeader.getSender().add(sender);

			Partner receiver = new Partner();
			PartnerIdentification receiverId = new PartnerIdentification();
			receiverId.setAuthority("GS1");
			receiverId.setValue(elePSR.getReceiver());
			receiver.setIdentifier(receiverId);
			standardBusinessDocHeader.getReceiver().add(receiver);

			DocumentIdentification documentIdentification = new DocumentIdentification();

			try {
				documentIdentification.setCreationDateAndTime(getXMLGregorianCalendarNow());
			} catch (DatatypeConfigurationException e1) {
				e1.printStackTrace();
			}
			documentIdentification.setInstanceIdentifier(uniqueIdentifier.toString());
			documentIdentification.setMultipleType(false);
			documentIdentification.setStandard("GS1");
			documentIdentification.setType("priceSynchronisationDocument");
			documentIdentification.setTypeVersion("3.1");

			standardBusinessDocHeader.setDocumentIdentification(documentIdentification);
			priceSynchronisationDocumentMessageType.setStandardBusinessDocumentHeader(standardBusinessDocHeader);

			TransactionType transaction = new TransactionType();
			DocumentCommandType docCmdType = new DocumentCommandType();
			DocumentCommandHeaderType docCmdHdr = new DocumentCommandHeaderType();
			docCmdHdr.setType(DocumentCommandEnumerationType.fromValue(operation));
			EntityIdentificationType docCmdIdentification = new EntityIdentificationType();
			docCmdIdentification.setEntityIdentification("DCX-" + uniqueIdentifier.toString());
			PartyIdentificationType contentOwner = new PartyIdentificationType();
			contentOwner.setGln(elePSR.getContentOwner());
			docCmdIdentification.setContentOwner(contentOwner);
			docCmdHdr.setDocumentCommandIdentification(docCmdIdentification);
			docCmdType.setDocumentCommandHeader(docCmdHdr);
			PriceSynchronisationDocumentType priceSyncDocument = pricingObjFactory
					.createPriceSynchronisationDocumentType();
			try {
				priceSyncDocument.setCreationDateTime(getXMLGregorianCalendarNow());
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
			priceSyncDocument.setDocumentStatusCode(DocumentStatusEnumerationType.ORIGINAL);
			priceSyncDocument.setDocumentStructureVersion("3.1");
			try {
				priceSyncDocument.setLastUpdateDateTime(getXMLGregorianCalendarNow());
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
			EntityIdentificationType priceSynchDocIdentification = new EntityIdentificationType();
			Random rand = new Random();
			// Obtain a number between [0 - 49].
			int randomInt = rand.nextInt(50);
			randomInt += 1;
			
			priceSynchDocIdentification.setEntityIdentification(String.valueOf(randomInt));
			priceSynchDocIdentification.setContentOwner(contentOwner);
			priceSyncDocument.setPriceSynchronisationDocumentIdentification(priceSynchDocIdentification);

			EntityIdentificationType priceSynchRelnId = new EntityIdentificationType();
			priceSynchRelnId.setContentOwner(contentOwner);
			priceSynchRelnId.setEntityIdentification(elePSR.getRelationship_Code());
			priceSyncDocument.setPriceSynchronisationRelationshipIdentification(priceSynchRelnId);

			priceSyncDocument.setInformationProvider(elePSR.getContentOwner());
			priceSyncDocument.setPartyReceivingPrivateData(elePSR.getPartyReceivingPrivateData());
			PriceDocumentTypeCodeType priceDocType = new PriceDocumentTypeCodeType();
			priceDocType.setValue("INITIAL_LOAD");
			priceSyncDocument.setPriceDocumentType(priceDocType);

			
			PriceSynchronisationRelationshipType PSRType = new PriceSynchronisationRelationshipType();
			EntityIdentificationType PSRIdentification = new EntityIdentificationType();
			PSRIdentification.setContentOwner(contentOwner);
			PSRIdentification.setEntityIdentification(elePSR.getRelationship_Code());
			PSRType.setPriceSynchronisationRelationshipIdentification(PSRIdentification);
			
			SegmentActionCodeType relnActionCode = new SegmentActionCodeType();
			relnActionCode.setValue("ADD");
			PSRType.setRelationshipActionCode(relnActionCode);
			
			CurrencyCodeType currencyCode = new CurrencyCodeType();
			currencyCode.setValue(elePSR.getCurrency_Id());
			PSRType.setRelationshipCurrencyCode(currencyCode);
			
			PSRType.setRelationshipEffectiveEndDateTime(getXMLGregorianDate(elePSR.getEffective_End_Date()));
			
			PSRType.setRelationshipEffectiveStartDateTime(getXMLGregorianDate(elePSR.getEffective_Start_Date()));
			
			try {
				PSRType.setRelationshipLastChangedDateTime(getXMLGregorianCalendarNow());
			} catch (DatatypeConfigurationException e) {				
				e.printStackTrace();
			}
			
			CountryCodeType countryCode = new CountryCodeType();
			countryCode.setValue(elePSR.getNumeric_Id());
			PSRType.setTargetMarketCountryCode(countryCode);
			
			PartyIdentificationType businessLoc = new PartyIdentificationType();
			businessLoc.setGln(elePSR.getPartyReceivingPrivateData());
			PSRType.setBusinessLocation(businessLoc);
			
			PartyIdentificationType infoProvider = new PartyIdentificationType();
			infoProvider.setGln(elePSR.getContentOwner());
			PSRType.setInformationProvider(infoProvider);
			
			PartyIdentificationType partyRcvngPvtData = new PartyIdentificationType();
			partyRcvngPvtData.setGln(elePSR.getPartyReceivingPrivateData());
			PSRType.setPartyReceivingPrivateData(partyRcvngPvtData);
			
			priceSyncDocument.setPriceSynchronisationRelationship(PSRType);			
			docCmdType.getListPriceSynchronisationDocument().add(priceSyncDocument);
			transaction.setDocumentCommand(docCmdType);
			
			TradeChannelCodeType tradeChannel = new TradeChannelCodeType();
			tradeChannel.setValue("UNSPECIFIED");
			PSRType.setRelationshipTradeChannel(tradeChannel);
			
			EntityIdentificationType txnIdentification = new EntityIdentificationType();
			txnIdentification.setEntityIdentification("TRX-"+uniqueIdentifier.toString());
			txnIdentification.setContentOwner(contentOwner);
			transaction.setTransactionIdentification(txnIdentification);
			priceSynchronisationDocumentMessageType.getTransaction().add(transaction);
			
			

		}

		JAXBElement<PriceSynchronisationDocumentMessageType> jaxbPriceSynchronisationDocumentMessage = pricingObjFactory
				.createPriceSynchronisationDocumentMessage(priceSynchronisationDocumentMessageType);
		StringWriter sw = new StringWriter();
		 String xmlString = "";
		try {

		
			JAXBContext jaxbContext = JAXBContext.newInstance("com.ascention.GS1.pricing.schema");
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			jaxbMarshaller.marshal(jaxbPriceSynchronisationDocumentMessage, sw);
		
			
			// Logic to remove all the empty tags
			
			final String regex1 = "<([a-zA-Z0-9-\\:_]*)[^>]*/>";
		    final String regex2 = "<([a-zA-Z0-9-\\:_]*)[^>]*>\\s*</\\1>";

		    xmlString = sw.toString();
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
		    
		    
			//System.out.println(sw.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		
		FileWriterWithEncoding fw;
		try {
			fw = new FileWriterWithEncoding("C:\\Export\\Export_PSR_For_Price_Relationship_"+ Price_Relationship + ".xml",StandardCharsets.UTF_8);
			fw.write(xmlString);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return sw.toString();
		
	}
	
	@RequestMapping(value = "/validatePricing", produces = "application/json")
	public Result validatePricing(@RequestParam(required = true) Integer Price_Export, @RequestParam(required = true) Integer Client_Id) {
		Integer Validation_Domain_Id = 15569;
		
		PricingCore pricingCore = new PricingCore();
		List<PricingPSDSuperseded> lstPricingPSDSuperseded = pricingPSDSupersededDAO.getAllPricingPSDSuperseded(Price_Export);
		Integer Product_Export_Dtl_Id = null;
		Integer  Recipient_Id = null;
		Integer Price_Relationship_Id = null;
		
		for(PricingPSDSuperseded elePricingPSDSuperseded : lstPricingPSDSuperseded) {
			Product_Export_Dtl_Id = elePricingPSDSuperseded.getPrice_Relationship_Id();
			Recipient_Id =	elePricingPSDSuperseded.getRecipient_Id();
			Price_Relationship_Id  = elePricingPSDSuperseded.getPrice_Relationship_Id();
			
		}
		
		List<PricingPSDHeader> lstPSDHeader = pricingPSDHeaderDAO.getAllPriceBasisQuantity(Price_Export, Product_Export_Dtl_Id, Recipient_Id, Price_Relationship_Id);
		
		pricingCore.setLstPricingPSDHeader(lstPSDHeader);
		
		List<PricingPSDDetail> lstPricingPSDDetail = pricingPSDDetailDAO.getAllPricingPSDDetail(Price_Export);
		pricingCore.setLstPricingPSDDetail(lstPricingPSDDetail);
		
		pricingCore.setLstPricingTierRule(pricingTierRuleDAO.getAllPricingTierRule(Price_Export));
		
		pricingCore.setLstPricingPSDShipTo(pricingPSDShipToDAO.getAllPricingPSDShipTo(Price_Export));
	
		
		
		System.out.println("Pricing Details : " + pricingCore.toString()); 

		rules.reload(Price_Export,Client_Id, Validation_Domain_Id, 0);
		KieSession kieSession = null;
		Result result = null;
		
		for (String ruleId : ReloadDroolsRulesService.strRuleIdsToExecute.split(",")) {
			if (ruleId != null) {
				result = new Result();
				kieSession = ReloadDroolsRulesService.kieContainer.newKieSession();
				rulesValidationService.validatePrice(pricingCore, result, kieSession, ruleId);
			}

		}
		
		return result;
	}
	
	@RequestMapping(value = "/exportPSDXML", produces = "application/json")
	public String exportPSDData(@RequestParam(required = true) Integer Price_Export, @RequestParam(required = true) String operation) {
		uniqueIdentifier = UUID.randomUUID();
		PriceSynchronisationDocumentMessageType priceSynchronisationDocumentMessageType = pricingObjFactory
				.createPriceSynchronisationDocumentMessageType();

		List<PricingPSDSuperseded> lstPricingPSDSuperseded = pricingPSDSupersededDAO.getAllPricingPSDSuperseded(Price_Export);
		Integer Product_Export_Dtl_Id = null;
		Integer  Recipient_Id = null;
		Integer Price_Relationship_Id = null;
		
		for(PricingPSDSuperseded elePricingPSDSuperseded : lstPricingPSDSuperseded) {
			Product_Export_Dtl_Id = elePricingPSDSuperseded.getPrice_Relationship_Id();
			Recipient_Id =	elePricingPSDSuperseded.getRecipient_Id();
			Price_Relationship_Id  = elePricingPSDSuperseded.getPrice_Relationship_Id();
			
		}
		
		List<PricingPSDHeader> lstPSDHeader = pricingPSDHeaderDAO.getAllPriceBasisQuantity(Price_Export, Product_Export_Dtl_Id, Recipient_Id, Price_Relationship_Id);

		for (PricingPSDHeader psdHeader : lstPSDHeader) {

			StandardBusinessDocumentHeader standardBusinessDocHeader = new StandardBusinessDocumentHeader();
			standardBusinessDocHeader.setHeaderVersion("1.0");

			Partner sender = new Partner();
			PartnerIdentification senderId = new PartnerIdentification();
			senderId.setAuthority("GS1");
			senderId.setValue(psdHeader.getSender_and_Content_Owner());
			sender.setIdentifier(senderId);
			standardBusinessDocHeader.getSender().add(sender);

			Partner receiver = new Partner();
			PartnerIdentification receiverId = new PartnerIdentification();
			receiverId.setAuthority("GS1");
			receiverId.setValue(psdHeader.getReceiver());
			receiver.setIdentifier(receiverId);
			standardBusinessDocHeader.getReceiver().add(receiver);

			DocumentIdentification documentIdentification = new DocumentIdentification();

			try {
				documentIdentification.setCreationDateAndTime(getXMLGregorianCalendarNow());
			} catch (DatatypeConfigurationException e1) {
				e1.printStackTrace();
			}
			documentIdentification.setInstanceIdentifier(uniqueIdentifier.toString());
			documentIdentification.setMultipleType(false);
			documentIdentification.setStandard("GS1");
			documentIdentification.setType("priceSynchronisationDocument");
			documentIdentification.setTypeVersion("3.1");

			standardBusinessDocHeader.setDocumentIdentification(documentIdentification);
			priceSynchronisationDocumentMessageType.setStandardBusinessDocumentHeader(standardBusinessDocHeader);

			TransactionType transaction = new TransactionType();
			DocumentCommandType docCmdType = new DocumentCommandType();
			DocumentCommandHeaderType docCmdHdr = new DocumentCommandHeaderType();
			docCmdHdr.setType(DocumentCommandEnumerationType.fromValue("CHANGE_BY_REFRESH"));
			EntityIdentificationType docCmdIdentification = new EntityIdentificationType();
			docCmdIdentification.setEntityIdentification(uniqueIdentifier.toString());
			PartyIdentificationType contentOwner = new PartyIdentificationType();
			contentOwner.setGln(psdHeader.getSender_and_Content_Owner());
			docCmdIdentification.setContentOwner(contentOwner);
			docCmdHdr.setDocumentCommandIdentification(docCmdIdentification);
			docCmdType.setDocumentCommandHeader(docCmdHdr);
			PriceSynchronisationDocumentType priceSyncDocument = pricingObjFactory
					.createPriceSynchronisationDocumentType();
			try {
				priceSyncDocument.setCreationDateTime(getXMLGregorianCalendarNow());
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
			priceSyncDocument.setDocumentStatusCode(DocumentStatusEnumerationType.ORIGINAL);
			priceSyncDocument.setDocumentStructureVersion("3.1");
			try {
				priceSyncDocument.setLastUpdateDateTime(getXMLGregorianCalendarNow());
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
			EntityIdentificationType priceSynchDocIdentification = new EntityIdentificationType();
			//priceSynchDocIdentification.setEntityIdentification(uniqueIdentifier.toString());
//			Random rand = new Random();
			// Obtain a number between [0 - 49].
//			int randomInt = rand.nextInt(50);
//			randomInt += 1;
			
			priceSynchDocIdentification.setEntityIdentification(String.valueOf(psdHeader.getPrice_Relationship_Id()));
			priceSynchDocIdentification.setContentOwner(contentOwner);
			priceSyncDocument.setPriceSynchronisationDocumentIdentification(priceSynchDocIdentification);

			EntityIdentificationType priceSynchRelnId = new EntityIdentificationType();
			priceSynchRelnId.setContentOwner(contentOwner);
			priceSynchRelnId.setEntityIdentification(psdHeader.getRelationship_Code());
			priceSyncDocument.setPriceSynchronisationRelationshipIdentification(priceSynchRelnId);

			priceSyncDocument.setInformationProvider(psdHeader.getSender_and_Content_Owner());
			priceSyncDocument.setPartyReceivingPrivateData(psdHeader.getPartyReceivingPrivateData());
			PriceDocumentTypeCodeType priceDocType = new PriceDocumentTypeCodeType();
			priceDocType.setValue("INITIAL_LOAD");
			priceSyncDocument.setPriceDocumentType(priceDocType);

			
		
			List<PricingPSDDetail> lstPricingPSDDetail = pricingPSDDetailDAO.getAllPricingPSDDetail(Price_Export);
			
			
			for (PricingPSDDetail elePricingPSDDetail : lstPricingPSDDetail) {
				ItemDepictionQualifierType itemDepictionQualifier = new ItemDepictionQualifierType();

				CatalogueItemReferenceType catItemRef = new CatalogueItemReferenceType();
				catItemRef.setDataSource(psdHeader.getSender_and_Content_Owner());
				catItemRef.setGtin(psdHeader.getGtin());
				CountryCodeType countryCode = new CountryCodeType();
				countryCode.setValue(psdHeader.getNumeric_Id());
				catItemRef.setTargetMarketCountryCode(countryCode);
				itemDepictionQualifier.setCatalogueItemReference(catItemRef);

				ItemPriceTypeType itemPrice = new ItemPriceTypeType();
				EntityIdentificationType itemPriceTypeSegmentation = new EntityIdentificationType();
				itemPriceTypeSegmentation.setEntityIdentification(String.valueOf(elePricingPSDDetail.getPrice_Dtl_Id()));
				itemPriceTypeSegmentation.setContentOwner(contentOwner);
				itemPrice.setItemPriceTypeSegmentation(itemPriceTypeSegmentation);

				EntityIdentificationType targetPriceType = new EntityIdentificationType();
				if(elePricingPSDDetail.getTarget_Price_Id()!=null) {
					targetPriceType.setEntityIdentification(String.valueOf(elePricingPSDDetail.getTarget_Price_Id()));
					targetPriceType.setContentOwner(contentOwner);
					itemPrice.setTargetPriceType(targetPriceType);
				}
				DistributionMethodCodeType distributionMethodCode = new DistributionMethodCodeType();
				distributionMethodCode.setValue(psdHeader.getDist_Method());
				itemPrice.setDistributionMethodCode(distributionMethodCode);

				SegmentActionCodeType priceActionCode = new SegmentActionCodeType();
				priceActionCode.setValue(operation.toUpperCase());
				itemPrice.setPriceActionCode(priceActionCode);

				MeasurementType priceBasisQty = new MeasurementType();
				if (psdHeader.getPrice_Basis_Qty() != null) {
					priceBasisQty.setValue(BigDecimal.valueOf(psdHeader.getPrice_Basis_Qty()));
					priceBasisQty.setMeasurementUnitCode(psdHeader.getPrice_Basis_Qty_Uom());
					itemPrice.setPriceBasisQuantity(priceBasisQty);
				}

				//elePricingPSDDetail.getPrice_Dtl_Id();
				//elePricingPSDDetail.getTarget_Price_Id();
				
				
				if (elePricingPSDDetail.getPrice_Type_App_Seq() != null)
					itemPrice.setPriceTypeApplicationSequence(
							BigInteger.valueOf(elePricingPSDDetail.getPrice_Type_App_Seq()));
				PriceTypeCodeType priceType = new PriceTypeCodeType();
				priceType.setValue(elePricingPSDDetail.getPrice_Type());
				itemPrice.setPriceTypeCode(priceType);
				try {
					itemPrice.setPriceTypeLastChangedDateTime(getXMLGregorianCalendarNow());
				} catch (DatatypeConfigurationException e) {
					e.printStackTrace();
				}

				if (elePricingPSDDetail.getPrice_Value() != null) {
					QuantityType priceVal = new QuantityType();
					priceVal.setValue(elePricingPSDDetail.getPrice_Value());
					
					itemPrice.setPriceValue(priceVal);
				}

				ComponentValueTypeCodeType priceValueType = new ComponentValueTypeCodeType();
				priceValueType.setValue(elePricingPSDDetail.getPrice_Value_Type());
				itemPrice.setPriceValueType(priceValueType);

				itemPrice.setAlternateLocationGrouping(elePricingPSDDetail.getAlt_Loc_Grouping());

				itemPrice.setPriceTypeDescription(elePricingPSDDetail.getPrice_Type_Subcat());

				PriceActionReasonCodeType priceActionReason = new PriceActionReasonCodeType();
				priceActionReason.setValue(psdHeader.getPrice_Action()); 
				itemPrice.setPriceActionReason(priceActionReason);

				
				CountrySubdivisionCodeType countrySubDiv = new CountrySubdivisionCodeType();
				countrySubDiv.setValue(elePricingPSDDetail.getPrice_Region());
				if(elePricingPSDDetail.getPrice_Region()!=null)
					itemPrice.getPriceTargetMarketSubdivision().add(countrySubDiv);
				
				
			
//				List<PricingPSDRegion> lstPriceRegions = pricingPSDRegionDAO.getAllPricingPSDRegion(Price_Export);
//				for (PricingPSDRegion elePriceRegion : lstPriceRegions) {
//					CountrySubdivisionCodeType countrySubDiv = new CountrySubdivisionCodeType();
//					countrySubDiv.setValue(elePriceRegion.getPrice_Region());
//					itemPrice.getPriceTargetMarketSubdivision().add(countrySubDiv);
//				}

				itemPrice.getShipFrom().add(psdHeader.getSender_and_Content_Owner());
				//itemPrice.getShipTo().add(psdHeader.getReceiver());
				List<PricingPSDShipTo> lstPricingPSDShipTo= pricingPSDShipToDAO.getAllPricingPSDShipTo(Price_Export);
				for(PricingPSDShipTo elePricingPSDShipTo:lstPricingPSDShipTo) {
					itemPrice.getShipTo().add(elePricingPSDShipTo.getOrganisation_Address_GLN());
				}
				ReferenceDocumentInformationType refDocInfo = new ReferenceDocumentInformationType();
				if(elePricingPSDDetail.getRef_Doc_Identifier()!=null)
					refDocInfo.setReferenceDocumentIdentifier(elePricingPSDDetail.getRef_Doc_Identifier());
				Description70Type refDocDescr = new Description70Type();
				refDocDescr.setLanguageCode("en");
				refDocDescr.setValue(elePricingPSDDetail.getRef_Doc_Desc());
				if (elePricingPSDDetail.getRef_Doc_Desc() != null)
					refDocInfo.setReferenceDocumentDescription(refDocDescr);

				
				List<PricingTierRule> lstPricingTierRule = pricingTierRuleDAO.getAllPricingTierRule(Price_Export);
				
				for(PricingTierRule elepricingTierRule : lstPricingTierRule) {					
					
					if(elePricingPSDDetail.getPrice_Dtl_Id().equals(elepricingTierRule.getPrice_Dtl_Id())) {
						BracketQualifierType brackQualifier = new BracketQualifierType();
						
						//elepricingTierRule.getTier_Rule_Id();
						//elepricingTierRule.getTier_Rule_Seq();
						
						CriteriaJoinTypeCodeType bracketOperator = new CriteriaJoinTypeCodeType();
						bracketOperator.setValue(elepricingTierRule.getRule_Operator());
						brackQualifier.setBracketOperator(bracketOperator);
						
						BracketRangeQualifierCodeType bracketRangeQualifierCode = new BracketRangeQualifierCodeType();
						bracketRangeQualifierCode.setValue(elepricingTierRule.getRule_Range_Qualifier());						
						brackQualifier.setBracketRangeQualifierCode(bracketRangeQualifierCode);
						
						QuantityType bracketTierMax = new QuantityType();
						bracketTierMax.setMeasurementUnitCode(elepricingTierRule.getRule_Max_Uom());
						bracketTierMax.setValue(BigDecimal.valueOf(elepricingTierRule.getRule_Max()));
						brackQualifier.setBracketTierMaximum(bracketTierMax);
						
						
						QuantityType bracketTierMin = new QuantityType();
						bracketTierMin.setMeasurementUnitCode(elepricingTierRule.getRule_Min_Uom());
						bracketTierMin.setValue(BigDecimal.valueOf(elepricingTierRule.getRule_Min()));
						brackQualifier.setBracketTierMinimum(bracketTierMin);
						
						itemPrice.getBracketQualifier().add(brackQualifier);
					}
					
				}
				
				itemPrice.getReferenceDocumentInformation().add(refDocInfo);
				

				SegmentEffectiveEndDateInformationType effectiveEndDate = new SegmentEffectiveEndDateInformationType();
				EffectiveEndDateContextCodeType effectiveEndDateCtxtCode = new EffectiveEndDateContextCodeType();
				effectiveEndDateCtxtCode.setValue(psdHeader.getEffective_End_Date_Context());
				effectiveEndDate.setEffectiveEndDateContextCode(effectiveEndDateCtxtCode);
				effectiveEndDate.setEffectiveEndDateTime(getXMLGregorianDate(psdHeader.getEffective_End_Date()));
				itemPrice.getPriceTypeEffectiveEndDate().add(effectiveEndDate);

				SegmentEffectiveStartDateInformationType effectiveStartDate = new SegmentEffectiveStartDateInformationType();
				EffectiveStartDateContextCodeType effectiveStartDateCtxtCode = new EffectiveStartDateContextCodeType();
				effectiveStartDateCtxtCode.setValue(psdHeader.getEffective_Start_Date_Context());
				effectiveStartDate.setEffectiveStartDateContextCode(effectiveStartDateCtxtCode);
				effectiveStartDate.setEffectiveStartDateTime(getXMLGregorianDate(psdHeader.getEffective_Start_Date()));
				itemPrice.getPriceTypeEffectiveStartDate().add(effectiveStartDate);

				itemDepictionQualifier.getItemPriceType().add(itemPrice);
				priceSyncDocument.getItemDepictionQualifier().add(itemDepictionQualifier);
				

			}
			lstPricingPSDSuperseded = pricingPSDSupersededDAO.getAllPricingPSDSuperseded(Price_Export);
			
			for(PricingPSDSuperseded elePricingPSDSuperseded : lstPricingPSDSuperseded) {
				Product_Export_Dtl_Id = elePricingPSDSuperseded.getPrice_Relationship_Id();
				Recipient_Id =	elePricingPSDSuperseded.getRecipient_Id();
				Price_Relationship_Id  = elePricingPSDSuperseded.getPrice_Relationship_Id();
				
			}
			Integer Superseded_Price_Export = null;
			for(PricingPSDSuperseded elePricingPSDSuperseded : lstPricingPSDSuperseded) {				
				// Code to get value of superseded export
				List<PricingPSDSupersededPriceExport> lstPricingPSDSupersededPriceExport = pricingPSDSupersededPriceExportDAO.getAllPricingPSDSupersededPriceExport(elePricingPSDSuperseded.getSuperseded_Price_Hdr_Id(), elePricingPSDSuperseded.getProduct_Export_Dtl_Id(), elePricingPSDSuperseded.getRecipient_Id());
				for(PricingPSDSupersededPriceExport elePricingPSDSupersededPriceExport : lstPricingPSDSupersededPriceExport) {
					Superseded_Price_Export = elePricingPSDSupersededPriceExport.getPrice_Export_Id();
				}
			}
			List<PricingPSDHeader> lstSupersededPSDHeader = pricingPSDHeaderDAO.getAllPriceBasisQuantity(Superseded_Price_Export, Product_Export_Dtl_Id, Recipient_Id, Price_Relationship_Id);

	

			for(PricingPSDHeader eleSupersededPSDHeader : lstSupersededPSDHeader) {
				if(Superseded_Price_Export!=null) {
					List<PricingPSDDetail> lstSupersededPricingPSDDetail = pricingPSDDetailDAO.getAllPricingPSDDetail(Superseded_Price_Export);
					
					
					for (PricingPSDDetail elePricingPSDDetail : lstSupersededPricingPSDDetail) {
						ItemDepictionQualifierType itemDepictionQualifier = new ItemDepictionQualifierType();

						CatalogueItemReferenceType catItemRef = new CatalogueItemReferenceType();
						catItemRef.setDataSource(eleSupersededPSDHeader.getSender_and_Content_Owner());
						catItemRef.setGtin(eleSupersededPSDHeader.getGtin());
						CountryCodeType countryCode = new CountryCodeType();
						countryCode.setValue(eleSupersededPSDHeader.getNumeric_Id());
						catItemRef.setTargetMarketCountryCode(countryCode);
						itemDepictionQualifier.setCatalogueItemReference(catItemRef);

						ItemPriceTypeType itemPrice = new ItemPriceTypeType();
						EntityIdentificationType itemPriceTypeSegmentation = new EntityIdentificationType();
						itemPriceTypeSegmentation.setEntityIdentification(String.valueOf(elePricingPSDDetail.getPrice_Dtl_Id()));
						itemPriceTypeSegmentation.setContentOwner(contentOwner);
						itemPrice.setItemPriceTypeSegmentation(itemPriceTypeSegmentation);

						EntityIdentificationType targetPriceType = new EntityIdentificationType();
						if(elePricingPSDDetail.getTarget_Price_Id()!=null) {
							targetPriceType.setEntityIdentification(String.valueOf(elePricingPSDDetail.getTarget_Price_Id()));
							targetPriceType.setContentOwner(contentOwner);
							itemPrice.setTargetPriceType(targetPriceType);
						}
						DistributionMethodCodeType distributionMethodCode = new DistributionMethodCodeType();
						distributionMethodCode.setValue(eleSupersededPSDHeader.getDist_Method());
						itemPrice.setDistributionMethodCode(distributionMethodCode);

						SegmentActionCodeType priceActionCode = new SegmentActionCodeType();
						priceActionCode.setValue("CHANGE_BY_REFRESH");
						itemPrice.setPriceActionCode(priceActionCode);

						MeasurementType priceBasisQty = new MeasurementType();
						if (eleSupersededPSDHeader.getPrice_Basis_Qty() != null) {
							priceBasisQty.setValue(BigDecimal.valueOf(eleSupersededPSDHeader.getPrice_Basis_Qty()));
							priceBasisQty.setMeasurementUnitCode(eleSupersededPSDHeader.getPrice_Basis_Qty_Uom());
							itemPrice.setPriceBasisQuantity(priceBasisQty);
						}

						//elePricingPSDDetail.getPrice_Dtl_Id();
						//elePricingPSDDetail.getTarget_Price_Id();
						
						
						if (elePricingPSDDetail.getPrice_Type_App_Seq() != null)
							itemPrice.setPriceTypeApplicationSequence(
									BigInteger.valueOf(elePricingPSDDetail.getPrice_Type_App_Seq()));
						PriceTypeCodeType priceType = new PriceTypeCodeType();
						priceType.setValue(elePricingPSDDetail.getPrice_Type());
						itemPrice.setPriceTypeCode(priceType);
						try {
							itemPrice.setPriceTypeLastChangedDateTime(getXMLGregorianCalendarNow());
						} catch (DatatypeConfigurationException e) {
							e.printStackTrace();
						}

						if (elePricingPSDDetail.getPrice_Value() != null) {
							QuantityType priceVal = new QuantityType();
							priceVal.setValue(elePricingPSDDetail.getPrice_Value());
							
							itemPrice.setPriceValue(priceVal);
						}

						ComponentValueTypeCodeType priceValueType = new ComponentValueTypeCodeType();
						priceValueType.setValue(elePricingPSDDetail.getPrice_Value_Type());
						itemPrice.setPriceValueType(priceValueType);

						itemPrice.setAlternateLocationGrouping(elePricingPSDDetail.getAlt_Loc_Grouping());

						itemPrice.setPriceTypeDescription(elePricingPSDDetail.getPrice_Type_Subcat());

						PriceActionReasonCodeType priceActionReason = new PriceActionReasonCodeType();
						priceActionReason.setValue(eleSupersededPSDHeader.getPrice_Action());
						itemPrice.setPriceActionReason(priceActionReason);

						
						CountrySubdivisionCodeType countrySubDiv = new CountrySubdivisionCodeType();
						countrySubDiv.setValue(elePricingPSDDetail.getPrice_Region());
						if(elePricingPSDDetail.getPrice_Region()!=null)
							itemPrice.getPriceTargetMarketSubdivision().add(countrySubDiv);
						
						
					
//						List<PricingPSDRegion> lstPriceRegions = pricingPSDRegionDAO.getAllPricingPSDRegion(Price_Export);
//						for (PricingPSDRegion elePriceRegion : lstPriceRegions) {
//							CountrySubdivisionCodeType countrySubDiv = new CountrySubdivisionCodeType();
//							countrySubDiv.setValue(elePriceRegion.getPrice_Region());
//							itemPrice.getPriceTargetMarketSubdivision().add(countrySubDiv);
//						}

						itemPrice.getShipFrom().add(eleSupersededPSDHeader.getSender_and_Content_Owner());
						//itemPrice.getShipTo().add(eleSupersededPSDHeader.getReceiver());
						List<PricingPSDShipTo> lstPricingPSDShipTo= pricingPSDShipToDAO.getAllPricingPSDShipTo(Superseded_Price_Export);
						for(PricingPSDShipTo elePricingPSDShipTo:lstPricingPSDShipTo) {
							itemPrice.getShipTo().add(elePricingPSDShipTo.getOrganisation_Address_GLN());
						}
						ReferenceDocumentInformationType refDocInfo = new ReferenceDocumentInformationType();
						if(elePricingPSDDetail.getRef_Doc_Identifier()!=null)
							refDocInfo.setReferenceDocumentIdentifier(elePricingPSDDetail.getRef_Doc_Identifier());
						Description70Type refDocDescr = new Description70Type();
						refDocDescr.setLanguageCode("en");
						refDocDescr.setValue(elePricingPSDDetail.getRef_Doc_Desc());
						if (elePricingPSDDetail.getRef_Doc_Desc() != null)
							refDocInfo.setReferenceDocumentDescription(refDocDescr);

						
						List<PricingTierRule> lstPricingTierRule = pricingTierRuleDAO.getAllPricingTierRule(Superseded_Price_Export);
						
						for(PricingTierRule elepricingTierRule : lstPricingTierRule) {					
							
							if(elePricingPSDDetail.getPrice_Dtl_Id().equals(elepricingTierRule.getPrice_Dtl_Id())) {
								BracketQualifierType brackQualifier = new BracketQualifierType();
								
								//elepricingTierRule.getTier_Rule_Id();
								//elepricingTierRule.getTier_Rule_Seq();
								
								CriteriaJoinTypeCodeType bracketOperator = new CriteriaJoinTypeCodeType();
								bracketOperator.setValue(elepricingTierRule.getRule_Operator());
								brackQualifier.setBracketOperator(bracketOperator);
								
								BracketRangeQualifierCodeType bracketRangeQualifierCode = new BracketRangeQualifierCodeType();
								bracketRangeQualifierCode.setValue(elepricingTierRule.getRule_Range_Qualifier());						
								brackQualifier.setBracketRangeQualifierCode(bracketRangeQualifierCode);
								
								QuantityType bracketTierMax = new QuantityType();
								bracketTierMax.setMeasurementUnitCode(elepricingTierRule.getRule_Max_Uom());
								bracketTierMax.setValue(BigDecimal.valueOf(elepricingTierRule.getRule_Max()));
								brackQualifier.setBracketTierMaximum(bracketTierMax);
								
								
								QuantityType bracketTierMin = new QuantityType();
								bracketTierMin.setMeasurementUnitCode(elepricingTierRule.getRule_Min_Uom());
								bracketTierMin.setValue(BigDecimal.valueOf(elepricingTierRule.getRule_Min()));
								brackQualifier.setBracketTierMinimum(bracketTierMin);
								
								itemPrice.getBracketQualifier().add(brackQualifier);
							}
							
						}
						
						itemPrice.getReferenceDocumentInformation().add(refDocInfo);
						

						SegmentEffectiveEndDateInformationType effectiveEndDate = new SegmentEffectiveEndDateInformationType();
						EffectiveEndDateContextCodeType effectiveEndDateCtxtCode = new EffectiveEndDateContextCodeType();
						effectiveEndDateCtxtCode.setValue(eleSupersededPSDHeader.getEffective_End_Date_Context());
						effectiveEndDate.setEffectiveEndDateContextCode(effectiveEndDateCtxtCode);
						effectiveEndDate.setEffectiveEndDateTime(getXMLGregorianDate(eleSupersededPSDHeader.getEffective_End_Date()));
						itemPrice.getPriceTypeEffectiveEndDate().add(effectiveEndDate);

						SegmentEffectiveStartDateInformationType effectiveStartDate = new SegmentEffectiveStartDateInformationType();
						EffectiveStartDateContextCodeType effectiveStartDateCtxtCode = new EffectiveStartDateContextCodeType();
						effectiveStartDateCtxtCode.setValue(eleSupersededPSDHeader.getEffective_Start_Date_Context());
						effectiveStartDate.setEffectiveStartDateContextCode(effectiveStartDateCtxtCode);
						effectiveStartDate.setEffectiveStartDateTime(getXMLGregorianDate(eleSupersededPSDHeader.getEffective_Start_Date()));
						itemPrice.getPriceTypeEffectiveStartDate().add(effectiveStartDate);

						itemDepictionQualifier.getItemPriceType().add(itemPrice);
						priceSyncDocument.getItemDepictionQualifier().add(itemDepictionQualifier);
						

					}
					
				}
			}
			
		
	
			docCmdType.getListPriceSynchronisationDocument().add(priceSyncDocument);
			transaction.setDocumentCommand(docCmdType);
			EntityIdentificationType txnIdentification = new EntityIdentificationType();
			txnIdentification.setEntityIdentification(uniqueIdentifier.toString());
			txnIdentification.setContentOwner(contentOwner);
			transaction.setTransactionIdentification(txnIdentification);
			priceSynchronisationDocumentMessageType.getTransaction().add(transaction);

			
			UpdatePriceRelationshipSequence updatePriceSeq = new UpdatePriceRelationshipSequence();
			updatePriceSeq.setPrice_Relationship_Id(psdHeader.getPrice_Relationship_Id());
			updatePriceRelationshipSequenceDAO.updatePriceRelationshipSequence(updatePriceSeq);
		}

		JAXBElement<PriceSynchronisationDocumentMessageType> jaxbPriceSynchronisationDocumentMessage = pricingObjFactory
				.createPriceSynchronisationDocumentMessage(priceSynchronisationDocumentMessageType);
		StringWriter sw = new StringWriter();

		try {

			// JAXBContext jaxbContext =
			// JAXBContext.newInstance(ObjectFactoryCatalogueItemNotification.class);//"com.ascention.GS1.schema");
			JAXBContext jaxbContext = JAXBContext.newInstance("com.ascention.GS1.pricing.schema");
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
			// "http://www.unece.org/cefact/namespaces/StandardBusinessDocumentHeader
			// http://www.gs1globalregistry.net/3.1/schemas/sbdh/StandardBusinessDocumentHeader.xsd
			// urn:gs1:gdsn:catalogue_item_notification:xsd:3
			// http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/CatalogueItemNotification.xsd
			// urn:gs1:gdsn:alcohol_information:xsd:3 urn:gs1:gdsn:alcohol_information:xsd:3
			// http://www.gs1globalregistry.net/3.1/schemas/gs1/gdsn/AlcoholInformationModule.xsd");
			// JAXBElement<AlcoholInformationModuleType> jaxbElementAlcoholInformationModule
			// = alm;

			jaxbMarshaller.marshal(jaxbPriceSynchronisationDocumentMessage, sw);
			// jaxbMarshaller.marshal(jaxbCatalogueItemNotificationMessage, System.out);
			// sw.write(sw.toString().replace("ns38:document
			// xsi:type\"catalogue_item_notification:catalogueItemNotification\"
			// xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"",
			// "catalogue_item_notification:catalogueItemNotification"));
			// sw.write(sw.toString().replace("/ns38:document",
			// "/catalogue_item_notification:catalogueItemNotification"));
			
			
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
		    
		   
		    
		    FileWriterWithEncoding fw;
			try {
				fw = new FileWriterWithEncoding("C:\\Export\\Export_PSD_For_Price_Export_"+ Price_Export + ".xml",StandardCharsets.UTF_8);
				fw.write(xmlString);
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			
			//System.out.println(sw.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return sw.toString();
	}

	private XMLGregorianCalendar getXMLGregorianDate(String strDate) {
		try {
			Date packDepEnd;
		
			try {
				
				if(strDate!=null) {
					if(strDate.contains(" ")) {
						strDate =	strDate.replaceAll("\\s+", "T");
						strDate += "Z";
					}else {
						strDate += "T00:00:00.000Z";
						
					}
				
						
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

}
