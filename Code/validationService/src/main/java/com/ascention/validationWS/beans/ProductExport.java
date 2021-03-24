package com.ascention.validationWS.beans;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.ascention.validationWS.exportGS1.beans.AVPHsnoClassification;
import com.ascention.validationWS.exportGS1.beans.Packaging;
import com.ascention.validationWS.exportGS1.beans.Packaging_Deposit;
import com.ascention.validationWS.exportGS1.beans.Packaging_Function;
import com.ascention.validationWS.exportGS1.beans.Packaging_Material;
import com.ascention.validationWS.exportGS1.beans.Packaging_Recycle;
import com.ascention.validationWS.exportGS1.beans.Packaging_Recycle_Scheme;
import com.ascention.validationWS.exportGS1.beans.Packaging_Sustainability;
import com.ascention.validationWS.exportGS1.beans.TradeItemHierarchyQuantity;
import com.ascention.validationWS.exportGS1.beans.TradeItemInformationProviderDetails;
import com.ascention.validationWS.exportGS1.beans.TradeItemNetContent;

public class ProductExport {
	private List<ProductExportHeader> lstProductExportHdr;
	private List<ProductHierarchy> lstProductHierarchy;
	private List<GPCAttributes> lstGPCAttributes;
	private List<HierarchyAlcoholPercentage> lstHierarchyAlcoholPercentage;	
	private List<HierarchyGTINCountryOrigin>  lstHierarchyGTINCountryOrigin;
	private List<HierarchyOrderQty>  lstHierarchyOrderQty;
	private List<HierarchyDuty>  lstHierarchyDuty;
	private List<HierarchyPalletAttributes>  lstHierarchyPalletAttributes;
	private List<HierarchyUNSPSCAttributes>  lstHierarchyUNSPSCAttributes;

	private List<Packaging> lstPackaging;
	private List<Packaging_Function> lstPackaging_Function;
	private List<Packaging_Recycle> lstPackaging_Recycle;
	private List<Packaging_Recycle_Scheme> lstPackaging_Recycle_Scheme;
	private List<Packaging_Sustainability> lstPackaging_Sustainability;
	private List<Packaging_Material> lstPackaging_Material;
	private List<Packaging_Deposit> lstPackaging_Deposit;
	private List<TradeItemInformationProviderDetails> lstTradeItemInformationProviderDetails;
	private List<AVPHsnoClassification> lstAVPHsnoClassification;
	
	public List<Packaging> getLstPackaging() {
		return lstPackaging;
	}


	public void setLstPackaging(List<Packaging> lstPackaging) {
		this.lstPackaging = lstPackaging;
	}


	public List<Packaging_Function> getLstPackaging_Function() {
		return lstPackaging_Function;
	}


	public void setLstPackaging_Function(List<Packaging_Function> lstPackaging_Function) {
		this.lstPackaging_Function = lstPackaging_Function;
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


	public List<Packaging_Material> getLstPackaging_Material() {
		return lstPackaging_Material;
	}


	public void setLstPackaging_Material(List<Packaging_Material> lstPackaging_Material) {
		this.lstPackaging_Material = lstPackaging_Material;
	}


	public List<Packaging_Deposit> getLstPackaging_Deposit() {
		return lstPackaging_Deposit;
	}


	public void setLstPackaging_Deposit(List<Packaging_Deposit> lstPackaging_Deposit) {
		this.lstPackaging_Deposit = lstPackaging_Deposit;
	}


	private Integer Product_Id;
	private Integer Client_Id;
	private Integer Org_Id;
	private String strHierarchyIds;
	private String strCOOIds;
	private Integer numberOfLevels;
	private String Is_Country_In_CodeList;
	
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

	
	public boolean hasAllElementsInList(List<Object> lstToValidate, String strAllValuesToBePresent, String classname,
			String fieldname) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//// System.out.println("Called ProductHierarchy::hasAllElements:" + strAllValuesToBePresent);
		List<String> expected = new CopyOnWriteArrayList<String>();
		List<String> actual = new CopyOnWriteArrayList<String>();
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
			//// System.out.println("Comparing Expected::>" + expected.toString() + " Actual::>" + actual.toString());
			//// System.out.println("Returning " + actual.containsAll(expected));
		} catch (Exception e) {

			e.printStackTrace();
		}
		return actual.containsAll(expected);

	}

	public boolean hasKeyValueInList(List<Object> lstToValidate,String classname, String keyField, String valueField, String keyToCompare, String valueToCompare) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//// System.out.println("Called ProductHierarchy::hasKeyValueInList");
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
		//// System.out.println("Called ProductHierarchy::hasValueInList");
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

	
	public boolean calculateVolumeInHierarchyLevels(ProductExport prodExport, Double multiplicationFactor, String comparison){
	
		HashMap<Integer,Double> hmapLevelVolume = new HashMap<Integer,Double>();
		HashMap<Integer,Integer> hmapLevelChildQty = new HashMap<Integer,Integer>();
		boolean flagValid = false;
		List<ProductHierarchy> lstHierarchy = prodExport.getLstProductHierarchy();
		lstHierarchy.sort(new Comparator<ProductHierarchy>() {
			public int compare(ProductHierarchy s1, ProductHierarchy s2) {
				return s1.getLevel()-s2.getLevel();
			}
		});
	
		for(ProductHierarchy ph : lstHierarchy) {
			// System.out.println("Level:" + ph.getLevel() + " Prod_Unit_Desc:" + ph.getProd_Unit_Desc() + " Comparison Mode:"+comparison + " MultiplicationFactor:" + multiplicationFactor  );
			// System.out.println("Depth:" + ph.getDepth());
			// System.out.println("Height:" + ph.getHeight());
			// System.out.println("Width:" + ph.getWidth());
			// System.out.println("Volume:" + ph.getDepth()*ph.getHeight()*ph.getWidth());
			
			hmapLevelVolume.put(ph.getLevel(), (double) (ph.getDepth()*ph.getHeight()*ph.getWidth()));
			hmapLevelChildQty.put(ph.getLevel(),ph.getChild_Unit_Qty());
			// System.out.println("Volumes" + hmapLevelVolume);
			// System.out.println("Qty" + hmapLevelChildQty);
		}
		
		if(hmapLevelVolume.size()==2) {
			
			if(comparison.equalsIgnoreCase("LESS_OR_EQUAL")) {
				// System.out.println("Comparing:" + hmapLevelVolume.get(1) + " with: " + multiplicationFactor*hmapLevelVolume.get(2)*hmapLevelChildQty.get(1));
				if(hmapLevelVolume.get(1) <= multiplicationFactor*hmapLevelVolume.get(2)*hmapLevelChildQty.get(1)) {
					// System.out.println("Returning TRUE");
					flagValid = true;
				}
			}else if(comparison.equalsIgnoreCase("GREATER_OR_EQUAL")) {
				// System.out.println("Comparing:" + hmapLevelVolume.get(1) + " with: " + multiplicationFactor*hmapLevelVolume.get(2)*hmapLevelChildQty.get(1));
				if(hmapLevelVolume.get(1) >= multiplicationFactor*hmapLevelVolume.get(2)*hmapLevelChildQty.get(1)) {
					// System.out.println("Returning TRUE");
					flagValid = true;
				}
			}
			
			
		}else if(hmapLevelVolume.size()==3){
			
			if(comparison.equalsIgnoreCase("LESS_OR_EQUAL")) {
				// System.out.println("Level 1 vs Level 2 Comparing:" + hmapLevelVolume.get(1) + " with: " + multiplicationFactor*hmapLevelVolume.get(2)*hmapLevelChildQty.get(1));
				// System.out.println("Level 2 vs Level 3 Comparing:" + hmapLevelVolume.get(2) + " with: " + multiplicationFactor*hmapLevelVolume.get(3)*hmapLevelChildQty.get(2));
				if(hmapLevelVolume.get(1) <= multiplicationFactor*hmapLevelVolume.get(2)*hmapLevelChildQty.get(1) || hmapLevelVolume.get(2) <= multiplicationFactor*hmapLevelVolume.get(3)*hmapLevelChildQty.get(2)) {
					// System.out.println("Returning TRUE");
					flagValid = true;
				}
				
			}else if(comparison.equalsIgnoreCase("GREATER_OR_EQUAL"))  {
				// System.out.println("Level 1 vs Level 2 Comparing:" + hmapLevelVolume.get(1) + " with: " + multiplicationFactor*hmapLevelVolume.get(2)*hmapLevelChildQty.get(1));
				// System.out.println("Level 2 vs Level 3 Comparing:" + hmapLevelVolume.get(2) + " with: " + multiplicationFactor*hmapLevelVolume.get(3)*hmapLevelChildQty.get(2));
				if(hmapLevelVolume.get(1) >= multiplicationFactor*hmapLevelVolume.get(2)*hmapLevelChildQty.get(1) || hmapLevelVolume.get(2) >= multiplicationFactor*hmapLevelVolume.get(3)*hmapLevelChildQty.get(2)) {
					// System.out.println("Returning TRUE");
					flagValid = true;
				}
			}
			
		} 
		
	
		return flagValid;
	}
	
	
	public boolean compareNetWeightsAcrossHierarchyAtLevel(ProductExport prodExport, String levelName) {
		
		
		BigDecimal netWeightChild;
		BigDecimal netWeightParent;
		
			for (ProductHierarchy eleParentProductHierarchy : prodExport.getLstProductHierarchy()) {				
				
					if(eleParentProductHierarchy.getProd_Unit_Desc().equalsIgnoreCase(levelName)) {
						for (ProductHierarchy eleChildProductHierarchy : prodExport.getLstProductHierarchy()) {
							//// System.out.println("eleChildProductHierarchy.getProduct_Hierarchy_Id()::>" + eleChildProductHierarchy.getProduct_Hierarchy_Id());
							//// System.out.println("eleParentProductHierarchy.getChild_Unit_Prod_Id()::>" + eleParentProductHierarchy.getChild_Unit_Prod_Id());
							if(eleChildProductHierarchy.getProduct_Hierarchy_Id().equals(eleParentProductHierarchy.getChild_Unit_Prod_Id())) {
								// Found the child
								netWeightChild = new BigDecimal(eleParentProductHierarchy.getChild_Unit_Qty() * eleChildProductHierarchy.getNet_Weight());
								netWeightParent = new BigDecimal(eleParentProductHierarchy.getNet_Weight());
								netWeightChild = netWeightChild.setScale(1, BigDecimal.ROUND_HALF_EVEN);
								netWeightParent = netWeightParent.setScale(1, BigDecimal.ROUND_HALF_EVEN);
								if(netWeightParent.compareTo(netWeightChild)!=0)
									return true;
							}
						}
					}	
				
									
			}
		
		return false;
	}
	
	
	public boolean containsEAAsNetContent(ProductExport prodExport) {
		
		boolean flagCheckifEAExistsAtEachLevel = true;
		
		for (ProductHierarchy eleProductHierarchy : prodExport.getLstProductHierarchy()) {	
			if(eleProductHierarchy.getIs_Base_Unit().equalsIgnoreCase("1")) {
				for (TradeItemNetContent eleNetContent : eleProductHierarchy.getLstTradeItemNetContent()) {
					if(eleNetContent.getNet_Content_Uom().equalsIgnoreCase("MLT") || eleNetContent.getNet_Content_Uom().equalsIgnoreCase("LTR")) {
						for (TradeItemNetContent eleNetContentCheckEA : eleProductHierarchy.getLstTradeItemNetContent()) {
							if(eleNetContentCheckEA.getNet_Content_Uom().equalsIgnoreCase("EA")) {
								flagCheckifEAExistsAtEachLevel = false;
							}
						}						
					}else {
						flagCheckifEAExistsAtEachLevel = false;
					}
				}
			}
			
		}
		
		return flagCheckifEAExistsAtEachLevel;
	}
	
	public boolean containsMLTLTRPackageDepositRegionState(ProductExport prodExport) {
		boolean flagCheckifMLTLTRExists = false;
		boolean flagSubdivisionNotNull = false;
		
		for (ProductHierarchy eleProductHierarchy : prodExport.getLstProductHierarchy()) {
			if(eleProductHierarchy.getIs_Base_Unit().equalsIgnoreCase("1")) {
				for(Packaging_Deposit elePackagingDep : eleProductHierarchy.getLstPackaging_Deposit()) {
					if(elePackagingDep.getPackage_Deposit_Subdivision()!=null) {
						flagSubdivisionNotNull = true;
					}
				}
				for (TradeItemNetContent eleNetContent : eleProductHierarchy.getLstTradeItemNetContent()) {
					if (eleNetContent.getNet_Content_Uom().equalsIgnoreCase("MLT") || eleNetContent.getNet_Content_Uom().equalsIgnoreCase("LTR")) {
						flagCheckifMLTLTRExists = true;
					}
				}
			}
		}
		
		if(flagSubdivisionNotNull==true && flagCheckifMLTLTRExists==false) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
	public boolean dimensionsExceed2Meters(ProductExport prodExport) {
		
		
		for (ProductHierarchy eleParentProductHierarchy : prodExport.getLstProductHierarchy()) {	
			Integer quantityOfLayersPerPallet = 0;
			for(TradeItemHierarchyQuantity eleTradeItemHierarchyQuantity:eleParentProductHierarchy.getLstTradeItemHierarchyQuantity()) {
				if(eleTradeItemHierarchyQuantity.getCarton_Layer_Per_Pallet()!=null)
					quantityOfLayersPerPallet = eleTradeItemHierarchyQuantity.getCarton_Layer_Per_Pallet();
			}
			float minimumValue = 0;
			
			
			if(eleParentProductHierarchy.getWidth_Uom().equalsIgnoreCase("MMT") && eleParentProductHierarchy.getHeight_Uom().equalsIgnoreCase("MMT") && eleParentProductHierarchy.getDepth_Uom().equalsIgnoreCase("MMT")) {
				if(quantityOfLayersPerPallet!=0) {
					 minimumValue = eleParentProductHierarchy.getWidth();
					
					 if(minimumValue>eleParentProductHierarchy.getHeight()) {
						 minimumValue = eleParentProductHierarchy.getHeight();
					 }
					 if(minimumValue>eleParentProductHierarchy.getDepth()) {
						 minimumValue = eleParentProductHierarchy.getDepth();
					 }
					 
					 if(minimumValue*quantityOfLayersPerPallet>2000)
						 return true;
					 
				}
			} 
		}
		
		return false;
	}
	
	
public boolean surfaceAreaExceedsQtyOfTradeItems(ProductExport prodExport) {
		
		
		for (ProductHierarchy eleParentProductHierarchy : prodExport.getLstProductHierarchy()) {	
			Integer quantityOfTradeItemsPerPalletLayer = 0;
			for(TradeItemHierarchyQuantity eleTradeItemHierarchyQuantity:eleParentProductHierarchy.getLstTradeItemHierarchyQuantity()) {
				if(eleTradeItemHierarchyQuantity.getQuantityOfTradeItemsPerPalletLayer()!=null)
					quantityOfTradeItemsPerPalletLayer = eleTradeItemHierarchyQuantity.getQuantityOfTradeItemsPerPalletLayer();
			}
			float minimumValue = 0;
			float maximumValue = 0;
			float sumHeightWidthDepth = 0;
			
			if(eleParentProductHierarchy.getWidth_Uom().equalsIgnoreCase("MMT") && eleParentProductHierarchy.getHeight_Uom().equalsIgnoreCase("MMT") && eleParentProductHierarchy.getDepth_Uom().equalsIgnoreCase("MMT")) {
				if(quantityOfTradeItemsPerPalletLayer!=0) {
					 minimumValue = eleParentProductHierarchy.getWidth();
					 maximumValue = eleParentProductHierarchy.getWidth();
					 
					 if(minimumValue>eleParentProductHierarchy.getHeight()) {
						 minimumValue = eleParentProductHierarchy.getHeight();
					 }
					 if(minimumValue>eleParentProductHierarchy.getDepth()) {
						 minimumValue = eleParentProductHierarchy.getDepth();
					 }
					 
					 if(maximumValue<eleParentProductHierarchy.getHeight()) {
						 maximumValue = eleParentProductHierarchy.getHeight();
					 }
					 
					 if(maximumValue<eleParentProductHierarchy.getDepth()) {
						 maximumValue = eleParentProductHierarchy.getDepth();
					 }
					 
					 sumHeightWidthDepth = eleParentProductHierarchy.getWidth() + eleParentProductHierarchy.getHeight() + eleParentProductHierarchy.getDepth() ;
					 
					 if(minimumValue*(sumHeightWidthDepth-minimumValue-maximumValue)*quantityOfTradeItemsPerPalletLayer < 1357225) {
						 return true;
					 }
					 
				}
			} 
		}
		
		return false;
	}


	public boolean compareWeightsAcrossHierarchy(ProductExport prodExport, String fieldName) {
		
		BigDecimal grossWeightChild;
		BigDecimal grossWeightParent;
		if(fieldName.equalsIgnoreCase("GrossWeight")) {
			for (ProductHierarchy eleParentProductHierarchy : prodExport.getLstProductHierarchy()) {				
					for (ProductHierarchy eleChildProductHierarchy : prodExport.getLstProductHierarchy()) {
						//// System.out.println("eleChildProductHierarchy.getProduct_Hierarchy_Id()::>" + eleChildProductHierarchy.getProduct_Hierarchy_Id());
						//// System.out.println("eleParentProductHierarchy.getChild_Unit_Prod_Id()::>" + eleParentProductHierarchy.getChild_Unit_Prod_Id());
						if(eleChildProductHierarchy.getProduct_Hierarchy_Id().equals(eleParentProductHierarchy.getChild_Unit_Prod_Id())) {
							// Found the child
							grossWeightChild = new BigDecimal(eleParentProductHierarchy.getChild_Unit_Qty() * eleChildProductHierarchy.getGross_Weight());
							grossWeightParent = new BigDecimal(eleParentProductHierarchy.getGross_Weight());
							grossWeightChild = grossWeightChild.setScale(1, BigDecimal.ROUND_HALF_EVEN);
							grossWeightParent = grossWeightParent.setScale(1, BigDecimal.ROUND_HALF_EVEN);
							if(grossWeightParent.compareTo(grossWeightChild)==-1)
								return true;
						}
					}				
			}
		}
	

		BigDecimal netWeightChild;
		BigDecimal netWeightParent;
		if(fieldName.equalsIgnoreCase("NetWeight")) {
			for (ProductHierarchy eleParentProductHierarchy : prodExport.getLstProductHierarchy()) {				
					for (ProductHierarchy eleChildProductHierarchy : prodExport.getLstProductHierarchy()) {
						//// System.out.println("eleChildProductHierarchy.getProduct_Hierarchy_Id()::>" + eleChildProductHierarchy.getProduct_Hierarchy_Id());
						//// System.out.println("eleParentProductHierarchy.getChild_Unit_Prod_Id()::>" + eleParentProductHierarchy.getChild_Unit_Prod_Id());
						if(eleChildProductHierarchy.getProduct_Hierarchy_Id().equals(eleParentProductHierarchy.getChild_Unit_Prod_Id())) {
							// Found the child
							netWeightChild = new BigDecimal(eleParentProductHierarchy.getChild_Unit_Qty() * eleChildProductHierarchy.getNet_Weight());
							netWeightParent = new BigDecimal(eleParentProductHierarchy.getNet_Weight());
							netWeightChild = netWeightChild.setScale(1, BigDecimal.ROUND_HALF_EVEN);
							netWeightParent = netWeightParent.setScale(1, BigDecimal.ROUND_HALF_EVEN);
							if(netWeightParent.compareTo(netWeightChild)==-1)
								return true;
						}
					}				
			}
		}

	
		return false;
	}
	
	public boolean compareHeightWidthDepthInHierarchyLevels	(ProductExport prodExport){
		Class<?> c;		
		Method valueMethod ;
		Field f;
		boolean flagComparison = false;
		
		String classname = "ProductHierarchy";
		String valueFieldsToCheck = "Width,Depth,Height";
		
		for(String valueField : valueFieldsToCheck.split(",")) {
			try {
				try {
					c = Class.forName("com.ascention.validationWS.beans." + classname);
				} catch (Exception e) {
					c = Class.forName("com.ascention.validationWS.exportGS1.beans." + classname);	 
				}
				valueMethod = c.getDeclaredMethod("get" + valueField);
				f = c.getDeclaredField(valueField);
				List<ProductHierarchy> lstHierarchy = prodExport.getLstProductHierarchy();
				lstHierarchy.sort(new Comparator<ProductHierarchy>() {
					public int compare(ProductHierarchy s1, ProductHierarchy s2) {
						return s1.getLevel()-s2.getLevel();
					}
				});
			
				Float curVal = -1f;
				for(ProductHierarchy ph : lstHierarchy) {
					if(curVal == -1f ) {
						curVal = (Float)valueMethod.invoke(ph);
						flagComparison = false;
					}else if(curVal >= (Float) valueMethod.invoke(ph)) {
						curVal = (Float)valueMethod.invoke(ph);
						flagComparison = true;
					}
				}
				
				if(!flagComparison)
					return false;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}
		
		

		return flagComparison;
	}
	
	
	public boolean compareValuesInHierarchyLevels(ProductExport prodExport,String classname, String valueField, String dataType){
		//// System.out.println("Called ProductHierarchy::compareValuesInHierarchyLevels");
		Class<?> c;		
		Method valueMethod ;
		Field f;
		boolean flagComparison = false;
		try {
			try {
				c = Class.forName("com.ascention.validationWS.beans." + classname);
			} catch (Exception e) {
				c = Class.forName("com.ascention.validationWS.exportGS1.beans." + classname);	 
			}
			valueMethod = c.getDeclaredMethod("get" + valueField);
			f = c.getDeclaredField(valueField);
			List<ProductHierarchy> lstHierarchy = prodExport.getLstProductHierarchy();
			lstHierarchy.sort(new Comparator<ProductHierarchy>() {
				public int compare(ProductHierarchy s1, ProductHierarchy s2) {
					return s1.getLevel()-s2.getLevel();
				}
			});
		
			
			
			if(dataType.equalsIgnoreCase("Integer")) {
				Integer curVal = -1;
				for(ProductHierarchy ph : lstHierarchy) {
					if(curVal == -1 ) {
						curVal = (Integer)valueMethod.invoke(ph);
						flagComparison = false;
					}else if(curVal >= (Integer) valueMethod.invoke(ph)) {
						curVal = (Integer)valueMethod.invoke(ph);
						flagComparison = true;
					}
				}
			}else if(dataType.equalsIgnoreCase("Float")) {
				Float curVal = -1f;
				for(ProductHierarchy ph : lstHierarchy) {
					if(curVal == -1f ) {
						curVal = (Float)valueMethod.invoke(ph);
						flagComparison = false;
					}else if(curVal >= (Float) valueMethod.invoke(ph)) {
						curVal = (Float)valueMethod.invoke(ph);
						flagComparison = true;
					}
				}
			}else if(dataType.equalsIgnoreCase("String")){
				String curVal = "";
				for(ProductHierarchy ph : lstHierarchy) {
					if(curVal.equals("")) {
						curVal = (String) valueMethod.invoke(ph);
						if(curVal==null)
							curVal="";
						flagComparison = false;
					}else if(!curVal.equalsIgnoreCase((String) valueMethod.invoke(ph))) {
						
						curVal = (String) valueMethod.invoke(ph);
						if(curVal==null)
							curVal="";
						flagComparison = true;
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//// System.out.println("Result ProductHierarchy::compareValuesInHierarchyLevels::>" + flagComparison);

		return flagComparison;

	}

	
	
	public boolean hasValueAtAllHierarchyLevels(List<ProductHierarchy> lstToValidate,String classname, String valueField) {
		Class<?> c;		
		Method valueMethod ;
		Method methodToGetList ;
		
		boolean flagComparison = false;
		try {
			try {
				c = Class.forName("com.ascention.validationWS.beans." + classname);
			} catch (Exception e) {
				c = Class.forName("com.ascention.validationWS.exportGS1.beans." + classname);	 
			}			valueMethod = c.getDeclaredMethod("get" + valueField);
						
			methodToGetList = ProductHierarchy.class.getDeclaredMethod("getLst"+classname);
			for (ProductHierarchy eleProductHierarchy : lstToValidate) {	
					
					for(Object ele : (List) methodToGetList.invoke(eleProductHierarchy)) {
						if(valueMethod.invoke(ele)!=null) {
							flagComparison = true;
						}else {
							return false;
						}
					}
				}
				
				
		
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		//// System.out.println("Result ProductHierarchy::compareValuesInHierarchyLevels::>" + flagComparison);

		return flagComparison;
	}
	
	public boolean compareValues(List<ProductHierarchy> lstToValidate,String classname, String valueField, String dataType){
		//// System.out.println("Called ProductHierarchy::compareValuesInHierarchyLevels");
		Class<?> c;		
		Method valueMethod ;
		Method methodToGetList ;
		Field f;
		boolean flagComparison = false;
		Object valueToCompare = null;
		try {
			try {
				c = Class.forName("com.ascention.validationWS.beans." + classname);
			} catch (Exception e) {
				c = Class.forName("com.ascention.validationWS.exportGS1.beans." + classname);	 
			}
			valueMethod = c.getDeclaredMethod("get" + valueField);
			f = c.getDeclaredField(valueField);
						
			methodToGetList = ProductHierarchy.class.getDeclaredMethod("getLst"+classname);
			
			if(dataType.equalsIgnoreCase("String")) {
				String curVal = "";
				for (ProductHierarchy eleProductHierarchy : lstToValidate) {	
					if(curVal.equals("")) {
						for(Object ele : (List) methodToGetList.invoke(eleProductHierarchy)) {
							curVal = (String) valueMethod.invoke(ele);
							break;
						}						
						flagComparison = true;
					}
					for(Object ele : (List) methodToGetList.invoke(eleProductHierarchy)) {
						if(curVal.equalsIgnoreCase((String) valueMethod.invoke(ele))) {
							flagComparison = true;
						}else {
							return false;
						}
					}
				}
				
				
		
			}else if(dataType.equalsIgnoreCase("Float")) {
//				Float curVal = -1f;
//				for(ProductHierarchy ph : lstHierarchy) {
//					if(curVal == -1f ) {
//						curVal = (Float)valueMethod.invoke(ph);
//						flagComparison = false;
//					}else if(curVal >= (Float) valueMethod.invoke(ph)) {
//						curVal = (Float)valueMethod.invoke(ph);
//						flagComparison = true;
//					}
//				}
			}else if(dataType.equalsIgnoreCase("String")){
//				String curVal = "";
//				for(ProductHierarchy ph : lstHierarchy) {
//					if(curVal.equals("")) {
//						curVal = (String) valueMethod.invoke(ph);
//						flagComparison = false;
//					}else if(!curVal.equalsIgnoreCase((String) valueMethod.invoke(ph))) {
//						curVal = (String) valueMethod.invoke(ph);
//						flagComparison = true;
//					}
//				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//// System.out.println("Result ProductHierarchy::compareValuesInHierarchyLevels::>" + flagComparison);

		return flagComparison;

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

	public List<GPCAttributes> getLstGPCAttributes() {
		return lstGPCAttributes;
	}

	public void setLstGPCAttributes(List<GPCAttributes> lstGPCAttributes) {
		this.lstGPCAttributes = lstGPCAttributes;
	}

	public List<HierarchyAlcoholPercentage> getLstHierarchyAlcoholPercentage() {
		return lstHierarchyAlcoholPercentage;
	}

	public void setLstHierarchyAlcoholPercentage(List<HierarchyAlcoholPercentage> lstHierarchyAlcoholPercentage) {
		this.lstHierarchyAlcoholPercentage = lstHierarchyAlcoholPercentage;
	}

	public List<HierarchyGTINCountryOrigin> getLstHierarchyGTINCountryOrigin() {
		return lstHierarchyGTINCountryOrigin;
	}

	public void setLstHierarchyGTINCountryOrigin(List<HierarchyGTINCountryOrigin> lstHierarchyGTINCountryOrigin) {
		this.lstHierarchyGTINCountryOrigin = lstHierarchyGTINCountryOrigin;
	}

	public List<HierarchyOrderQty> getLstHierarchyOrderQty() {
		return lstHierarchyOrderQty;
	}

	public void setLstHierarchyOrderQty(List<HierarchyOrderQty> lstHierarchyOrderQty) {
		this.lstHierarchyOrderQty = lstHierarchyOrderQty;
	}

	public List<HierarchyDuty> getLstHierarchyDuty() {
		return lstHierarchyDuty;
	}

	public void setLstHierarchyDuty(List<HierarchyDuty> lstHierarchyDuty) {
		this.lstHierarchyDuty = lstHierarchyDuty;
	}

	public List<HierarchyPalletAttributes> getLstHierarchyPalletAttributes() {
		return lstHierarchyPalletAttributes;
	}

	public void setLstHierarchyPalletAttributes(List<HierarchyPalletAttributes> lstHierarchyPalletAttributes) {
		this.lstHierarchyPalletAttributes = lstHierarchyPalletAttributes;
	}

	public List<HierarchyUNSPSCAttributes> getLstHierarchyUNSPSCAttributes() {
		return lstHierarchyUNSPSCAttributes;
	}

	public void setLstHierarchyUNSPSCAttributes(List<HierarchyUNSPSCAttributes> lstHierarchyUNSPSCAttributes) {
		this.lstHierarchyUNSPSCAttributes = lstHierarchyUNSPSCAttributes;
	}


	public Integer getNumberOfLevels() {
		return numberOfLevels;
	}


	public void setNumberOfLevels(Integer numberOfLevels) {
		this.numberOfLevels = numberOfLevels;
	}


	public String getIs_Country_In_CodeList() {
		return Is_Country_In_CodeList;
	}


	public void setIs_Country_In_CodeList(String is_Country_In_CodeList) {
		Is_Country_In_CodeList = is_Country_In_CodeList;
	}


	public List<TradeItemInformationProviderDetails> getLstTradeItemInformationProviderDetails() {
		return lstTradeItemInformationProviderDetails;
	}


	public void setLstTradeItemInformationProviderDetails(List<TradeItemInformationProviderDetails> lstTradeItemInformationProviderDetails) {
		this.lstTradeItemInformationProviderDetails = lstTradeItemInformationProviderDetails;
	}


	public List<AVPHsnoClassification> getLstAVPHsnoClassification() {
		return lstAVPHsnoClassification;
	}


	public void setLstAVPHsnoClassification(List<AVPHsnoClassification> lstAVPHsnoClassification) {
		this.lstAVPHsnoClassification = lstAVPHsnoClassification;
	}




	
}
