package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.TradeItemMeasurements;

public interface TradeItemMeasurementsDAO {
	public List<TradeItemMeasurements> getAllTradeItemMeasurements(Integer Product_Id, String strHierarchyIds);

}
