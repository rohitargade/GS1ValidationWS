package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.OrderQty;

public interface OrderQtyDAO {
	public List<OrderQty> getAllOrderQtyForProduct(Integer Product_Id, Integer Product_Hierarchy_Id, Integer Client_Id, Integer Org_Id);

}
