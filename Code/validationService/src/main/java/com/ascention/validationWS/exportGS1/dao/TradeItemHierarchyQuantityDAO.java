package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.TradeItemHierarchyQuantity;

public interface TradeItemHierarchyQuantityDAO {
	public List<TradeItemHierarchyQuantity> getAllTradeItemHierarchyQuantity(Integer Product_Id, String strHierarchyIds);

}
