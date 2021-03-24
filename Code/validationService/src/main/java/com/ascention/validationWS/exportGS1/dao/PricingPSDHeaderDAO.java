package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging;
import com.ascention.validationWS.exportGS1.beans.PricingPSDHeader;

public interface PricingPSDHeaderDAO {
	public List<PricingPSDHeader> getAllPriceBasisQuantity( Integer Price_Export, Integer Product_Export_Dtl_Id, Integer Recipient_Id, Integer Price_Relationship_Id);

}
