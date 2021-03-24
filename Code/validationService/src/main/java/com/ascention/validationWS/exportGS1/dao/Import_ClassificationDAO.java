package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.Import_Classification;

public interface Import_ClassificationDAO {
	public List<Import_Classification> getAllImport_Classification(Integer Product_Id);

}
