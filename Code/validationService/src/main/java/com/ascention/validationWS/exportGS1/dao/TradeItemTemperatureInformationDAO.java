package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.TradeItemTemperatureInformation;

public interface TradeItemTemperatureInformationDAO {
	public List<TradeItemTemperatureInformation> getAllTradeItemTemperatureInformation(Integer Product_Id);

}
