package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.ProductDetails;

public interface ProductDetailsDAO {
	public List<ProductDetails> findAll();

	public ProductDetails getProductGroupSectorValuesById(Integer id);
}
