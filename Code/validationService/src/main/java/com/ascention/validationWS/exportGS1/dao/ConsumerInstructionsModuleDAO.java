package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.ConsumerInstructionsModule;

public interface ConsumerInstructionsModuleDAO {
	public List<ConsumerInstructionsModule> getAllConsumerInstructionsModule(Integer Product_Id);

}
