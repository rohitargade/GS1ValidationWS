package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging;
import com.ascention.validationWS.exportGS1.beans.TradeItemHandlingInformation;
import com.ascention.validationWS.exportGS1.beans.TradeItemInformationProviderDetails;
import com.ascention.validationWS.exportGS1.beans.TradeItemTargetMarket;

public interface TradeItemHandlingInformationDAO {
	public List<TradeItemHandlingInformation> getAllTradeItemHandlingInformation(Integer Product_Id, String strHierarchyIds);

}
