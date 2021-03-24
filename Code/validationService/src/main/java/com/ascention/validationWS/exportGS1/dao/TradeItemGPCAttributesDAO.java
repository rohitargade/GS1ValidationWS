package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging;
import com.ascention.validationWS.exportGS1.beans.TradeItemGPCAttributes;
import com.ascention.validationWS.exportGS1.beans.TradeItemHierarchy;
import com.ascention.validationWS.exportGS1.beans.TradeItemInformationProviderDetails;
import com.ascention.validationWS.exportGS1.beans.TradeItemTargetMarket;

public interface TradeItemGPCAttributesDAO {
	public List<TradeItemGPCAttributes> getAllTradeItemGPCAttributes(Integer Product_Id, String strHierarchyIds);

}
