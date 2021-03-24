package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging;
import com.ascention.validationWS.exportGS1.beans.PricingPSDHeader;
import com.ascention.validationWS.exportGS1.beans.PricingPSDSuperseded;
import com.ascention.validationWS.exportGS1.beans.PricingPSDSupersededPriceExport;

public interface PricingPSDSupersededPriceExportDAO {
	public List<PricingPSDSupersededPriceExport> getAllPricingPSDSupersededPriceExport( Integer PRICE_SUPERCEDED_ID, Integer PRODUCT_EXPORT_DTL_ID, Integer PRICE_RECIPIENT_ID);

}
