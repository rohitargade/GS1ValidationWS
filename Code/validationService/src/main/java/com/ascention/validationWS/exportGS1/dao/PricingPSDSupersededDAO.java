package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging;
import com.ascention.validationWS.exportGS1.beans.PricingPSDHeader;
import com.ascention.validationWS.exportGS1.beans.PricingPSDSuperseded;

public interface PricingPSDSupersededDAO {
	public List<PricingPSDSuperseded> getAllPricingPSDSuperseded( Integer Price_Export);

}
