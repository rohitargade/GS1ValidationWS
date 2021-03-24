package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.TradeItemAdditionalTradeItem;

public interface TradeItemAdditionalTradeItemDAO {
	public List<TradeItemAdditionalTradeItem> getAllTradeItemAdditionalTradeItem(Integer Product_Id, String strHierarchyIds);

}
