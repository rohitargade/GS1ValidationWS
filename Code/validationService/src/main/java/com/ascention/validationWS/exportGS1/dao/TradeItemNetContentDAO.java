package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.TradeItemMeasurements;
import com.ascention.validationWS.exportGS1.beans.TradeItemNetContent;

public interface TradeItemNetContentDAO {
	public List<TradeItemNetContent> getAllTradeItemNetContent(Integer Product_Id, String strHierarchyIds);

}
