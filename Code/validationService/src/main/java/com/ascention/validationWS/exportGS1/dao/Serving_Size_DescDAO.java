package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.Serving_Size_Desc;

public interface Serving_Size_DescDAO {
	public List<Serving_Size_Desc> getAllServing_Size_Desc(Integer Product_Id);

}
