package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.TradeItemMeasurements;
import com.ascention.validationWS.exportGS1.beans.TradeItemPegMeasurements;

public interface TradeItemPegMeasurementsDAO {
	public List<TradeItemPegMeasurements> getAllTradeItemPegMeasurements(Integer Product_Id);

}
