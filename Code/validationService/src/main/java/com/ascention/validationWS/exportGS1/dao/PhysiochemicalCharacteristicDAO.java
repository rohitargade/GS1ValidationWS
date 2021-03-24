package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PhysiochemicalCharacteristic;

public interface PhysiochemicalCharacteristicDAO {
	public List<PhysiochemicalCharacteristic> getAllPhysiochemicalCharacteristic(Integer Product_Id);

}
