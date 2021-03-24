package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.TradeItemLifespan;

public interface TradeItemLifespanDAO {
	public List<TradeItemLifespan> getAllTradeItemLifespan(Integer Product_Id);

}
