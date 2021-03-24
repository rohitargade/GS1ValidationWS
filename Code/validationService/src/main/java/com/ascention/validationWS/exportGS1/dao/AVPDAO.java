package com.ascention.validationWS.exportGS1.dao;

import com.ascention.validationWS.exportGS1.beans.AVP;

public interface AVPDAO {
	public AVP getAllAVP(Integer Product_Id, String strHierarchyIds, String strCOOIds, Integer Client_Id, Integer Org_Id);

}
