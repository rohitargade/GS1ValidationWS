package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.ConsumerInstructionsModule;
import com.ascention.validationWS.exportGS1.beans.DeliveryPurchasingInformationModule;
import com.ascention.validationWS.exportGS1.beans.LeadTimes;

public interface LeadTimesDAO {
	public List<LeadTimes> getAllLeadTimes(Integer Product_Id, Integer Product_Export_Hdr_Id, Integer Client_Id);

}
