package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.ChemicalRegulationInformationModule;

public interface ChemicalRegulationInformationModuleDAO {
	public List<ChemicalRegulationInformationModule> getAllChemicalRegulationInformationModule(Integer Product_Id);

}
