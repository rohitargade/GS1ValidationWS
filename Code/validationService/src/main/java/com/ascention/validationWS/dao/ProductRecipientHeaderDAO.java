package com.ascention.validationWS.dao;

import java.util.List;

import com.ascention.validationWS.beans.ProductRecipientHeader;


public interface ProductRecipientHeaderDAO {
	public ProductRecipientHeader getAllProductRecipientHeader(Integer Product_Recipient_Id, Boolean Is_CIHW);

}
