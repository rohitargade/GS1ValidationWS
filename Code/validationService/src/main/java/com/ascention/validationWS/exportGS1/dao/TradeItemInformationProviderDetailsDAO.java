package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging;
import com.ascention.validationWS.exportGS1.beans.TradeItemInformationProviderDetails;

public interface TradeItemInformationProviderDetailsDAO {
	public List<TradeItemInformationProviderDetails> getAllTradeItemInformationProviderDetails( Integer Product_Export_Hdr_Id);

}
