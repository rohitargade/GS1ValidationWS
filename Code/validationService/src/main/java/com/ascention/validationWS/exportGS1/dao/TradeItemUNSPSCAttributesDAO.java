package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging;
import com.ascention.validationWS.exportGS1.beans.TradeItemGPCAttributes;
import com.ascention.validationWS.exportGS1.beans.TradeItemHierarchy;
import com.ascention.validationWS.exportGS1.beans.TradeItemInformationProviderDetails;
import com.ascention.validationWS.exportGS1.beans.TradeItemTargetMarket;
import com.ascention.validationWS.exportGS1.beans.TradeItemUNSPSCAttributes;

public interface TradeItemUNSPSCAttributesDAO {
	public List<TradeItemUNSPSCAttributes> getAllTradeItemUNSPSCAttributes(Integer Product_Id);

}
