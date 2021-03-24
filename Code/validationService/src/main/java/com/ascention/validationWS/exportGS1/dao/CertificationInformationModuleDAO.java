package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.CertificationInformationModule;

public interface CertificationInformationModuleDAO {
	public List<CertificationInformationModule> getAllCertificationInformationModule(Integer Product_Id);

}
