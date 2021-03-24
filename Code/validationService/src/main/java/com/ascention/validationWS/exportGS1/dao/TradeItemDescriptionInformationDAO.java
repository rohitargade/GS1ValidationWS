package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.TradeItemDescriptionInformation;

public interface TradeItemDescriptionInformationDAO {
	public List<TradeItemDescriptionInformation> getAllTradeItemDescriptionInformation(Integer Product_Id, String strHierarchyIds);

}
