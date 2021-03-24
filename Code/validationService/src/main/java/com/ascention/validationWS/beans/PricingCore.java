package com.ascention.validationWS.beans;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.ascention.validationWS.exportGS1.beans.PricingPSDDetail;
import com.ascention.validationWS.exportGS1.beans.PricingPSDHeader;
import com.ascention.validationWS.exportGS1.beans.PricingPSDShipTo;
import com.ascention.validationWS.exportGS1.beans.PricingTierRule;

public class PricingCore {

	private List<PricingPSDDetail> lstPricingPSDDetail;
	private List<PricingPSDHeader> lstPricingPSDHeader;
	private List<PricingTierRule> lstPricingTierRule;
	private List<PricingPSDShipTo> lstPricingPSDShipTo;
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

	
	
	public List<PricingPSDDetail> getLstPricingPSDDetail() {
		return lstPricingPSDDetail;
	}



	public void setLstPricingPSDDetail(List<PricingPSDDetail> lstPricingPSDDetail) {
		this.lstPricingPSDDetail = lstPricingPSDDetail;
	}



	public List<PricingPSDHeader> getLstPricingPSDHeader() {
		return lstPricingPSDHeader;
	}



	public void setLstPricingPSDHeader(List<PricingPSDHeader> lstPricingPSDHeader) {
		this.lstPricingPSDHeader = lstPricingPSDHeader;
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
			System.out.println("Comparing Expected::>" + expected.toString() + " Actual::>" + actual.toString());
			System.out.println("Returning " + actual.containsAll(expected));
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

	public Integer getdayOftheWeek(String dateToCompare) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s", Locale.ENGLISH);

		try {
			Date date = formatter.parse(dateToCompare);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date); 
			return cal.get(Calendar.DAY_OF_WEEK);
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public boolean hasAltGrpWithRegionShipTo(PricingCore pricingCore) {
		
		for (PricingPSDDetail elePricingPSDDetail : pricingCore.getLstPricingPSDDetail()) {
			if (elePricingPSDDetail.getAlt_Loc_Grouping()!=null) {				
				for(PricingPSDShipTo elePricingPSDShipTo:pricingCore.getLstPricingPSDShipTo()) {
					if(elePricingPSDShipTo.getOrganisation_Address_GLN()!=null) {
						return true;
					}
				}
				if(elePricingPSDDetail.getPrice_Region()!=null) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean hasConsistentAltGrpAllLevels(PricingCore pricingCore) {
		for (PricingPSDDetail elePricingParentPSDDetail : pricingCore.getLstPricingPSDDetail()) {
			for (PricingPSDDetail elePricingChildPSDDetail : pricingCore.getLstPricingPSDDetail()) {
				
				if(elePricingChildPSDDetail.getTarget_Price_Id()!= null ) {
					if (elePricingChildPSDDetail.getTarget_Price_Id().equals(elePricingParentPSDDetail.getPrice_Dtl_Id())) {
						if (elePricingChildPSDDetail.getPrice_Type().equalsIgnoreCase("ALLOWANCE")
								|| elePricingChildPSDDetail.getPrice_Type().equalsIgnoreCase("CHARGE")) {
							if (elePricingParentPSDDetail.getAlt_Loc_Grouping() != null) {
								if (!elePricingParentPSDDetail.getAlt_Loc_Grouping()
										.equals(elePricingChildPSDDetail.getAlt_Loc_Grouping()))
									return true;
							} else {
								if (elePricingChildPSDDetail.getAlt_Loc_Grouping() != null) {
									return true;
								}
							}
						}

					}
				}
			
			}
	}

		return false;
	}
	
	public boolean hasAllowanceTargetPriceAsListPrice(PricingCore pricingCore) {
		
		for (PricingPSDDetail elePricingParentPSDDetail : pricingCore.getLstPricingPSDDetail()) {
			for (PricingPSDDetail elePricingChildPSDDetail : pricingCore.getLstPricingPSDDetail()) {
				
				if(elePricingChildPSDDetail.getTarget_Price_Id()!= null ) {
					if (elePricingChildPSDDetail.getTarget_Price_Id().equals(elePricingParentPSDDetail.getPrice_Dtl_Id())) {
						if (elePricingChildPSDDetail.getPrice_Type().equalsIgnoreCase("ALLOWANCE")
								|| elePricingChildPSDDetail.getPrice_Type().equalsIgnoreCase("CHARGE")) {
							if(!elePricingParentPSDDetail.getPrice_Type().equalsIgnoreCase("LIST_PRICE")) {
								return true;
							}
						}

					}
				}
			
			}
	}
		
		
		
		
		return false;
		
		
	}
	
	
	public boolean hasMatchingTransactionListPrice(PricingCore pricingCore) {

		PricingPSDDetail listPriceDetail = null;
		PricingPSDDetail txnPriceDetail = null;
		
		for (PricingPSDDetail elePricingPSDDetail : pricingCore.getLstPricingPSDDetail()) {
			if(elePricingPSDDetail.getPrice_Type().equalsIgnoreCase("LIST_PRICE")) {
				listPriceDetail = elePricingPSDDetail;
			}
			if(elePricingPSDDetail.getPrice_Type().equalsIgnoreCase("TRANSACTION_PRICE")) {
				txnPriceDetail = elePricingPSDDetail;
			}
		}

		if(listPriceDetail==null || txnPriceDetail==null) {
			return true; // failure if one of the price types is missing
		}else {
			if(listPriceDetail.getAlt_Loc_Grouping()==null || txnPriceDetail.getAlt_Loc_Grouping()==null) {
				if(listPriceDetail.getAlt_Loc_Grouping()!=null || txnPriceDetail.getAlt_Loc_Grouping()!=null) {
					return true;
				}
			}else if(!listPriceDetail.getAlt_Loc_Grouping().equals(txnPriceDetail.getAlt_Loc_Grouping())) {
				return true;
			}
			
			if(listPriceDetail.getPrice_Value_Type()==null || txnPriceDetail.getPrice_Value_Type()==null) {
				if(listPriceDetail.getPrice_Value_Type()!=null || txnPriceDetail.getPrice_Value_Type()!=null) {
					return true;
				}
			}else if(!listPriceDetail.getPrice_Value_Type().equals(txnPriceDetail.getPrice_Value_Type())) {
				return true;
			}
			
			
			if(listPriceDetail.getRef_Doc_Desc()==null || txnPriceDetail.getRef_Doc_Desc()==null) {
				if(listPriceDetail.getRef_Doc_Desc()!=null || txnPriceDetail.getRef_Doc_Desc()!=null) {
					return true;
				}
			}else if(!listPriceDetail.getRef_Doc_Desc().equals(txnPriceDetail.getRef_Doc_Desc())) {
				return true;
			}
			
			if(listPriceDetail.getRef_Doc_Identifier()==null || txnPriceDetail.getRef_Doc_Identifier()==null) {
				if(listPriceDetail.getRef_Doc_Identifier()!=null || txnPriceDetail.getRef_Doc_Identifier()!=null) {
					return true;
				}
			}else if(!listPriceDetail.getRef_Doc_Identifier().equals(txnPriceDetail.getRef_Doc_Identifier())) {
				return true;
			}
			
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



	public List<PricingTierRule> getLstPricingTierRule() {
		return lstPricingTierRule;
	}



	public void setLstPricingTierRule(List<PricingTierRule> lstPricingTierRule) {
		this.lstPricingTierRule = lstPricingTierRule;
	}



	public List<PricingPSDShipTo> getLstPricingPSDShipTo() {
		return lstPricingPSDShipTo;
	}



	public void setLstPricingPSDShipTo(List<PricingPSDShipTo> lstPricingPSDShipTo) {
		this.lstPricingPSDShipTo = lstPricingPSDShipTo;
	}

}

