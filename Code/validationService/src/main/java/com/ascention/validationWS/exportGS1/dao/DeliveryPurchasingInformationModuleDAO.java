package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.ConsumerInstructionsModule;
import com.ascention.validationWS.exportGS1.beans.DeliveryPurchasingInformationModule;

public interface DeliveryPurchasingInformationModuleDAO {
	public List<DeliveryPurchasingInformationModule> getAllDeliveryPurchasingInformationModule(Integer Product_Id, String strHierarchyIds);

}
