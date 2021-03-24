package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.PalletAttributes;

public interface PalletAttributesDAO {
	public List<PalletAttributes> getAllPalletAttributesForProduct(Integer Product_Id, Integer Product_Hierarchy_Id, Integer Client_Id, Integer Org_Id);

}
