package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.CIPGTIN;

public interface CIPGTINDAO {
	public List<CIPGTIN> getAllCIPGTIN(Integer Product_Id, String strHierarchyIds, String strCOOIds);

}
