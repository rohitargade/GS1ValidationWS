package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.TradeItemReferencedTradeItem;

public interface TradeItemReferencedTradeItemDAO {
	public List<TradeItemReferencedTradeItem> getAllTradeItemReferencedTradeItem(Integer Product_Id, String strHierarchyIds);

}
