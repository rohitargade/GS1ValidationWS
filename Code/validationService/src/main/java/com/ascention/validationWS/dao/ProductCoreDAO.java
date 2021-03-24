package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.ProductCore;

public interface ProductCoreDAO {
	public ProductCore getAllProductAttributes(Integer Product_Id, Integer Product_Hierarchy_Id, Integer Client_Id, Integer Org_Id);

}
