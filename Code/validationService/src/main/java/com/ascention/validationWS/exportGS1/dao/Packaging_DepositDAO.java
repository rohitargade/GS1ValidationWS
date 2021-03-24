package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.Packaging_Deposit;

public interface Packaging_DepositDAO {
	public List<Packaging_Deposit> getAllPackaging_Deposit(Integer Product_Id, String strHierarchyIds);

}
