package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.OrderQuantity;

public interface OrderQuantityDAO {
	public List<OrderQuantity> getAllOrderQuantity(Integer Product_Id, String strHierarchyIds, Integer Product_Export_Hdr_Id, Integer Client_Id);

}
