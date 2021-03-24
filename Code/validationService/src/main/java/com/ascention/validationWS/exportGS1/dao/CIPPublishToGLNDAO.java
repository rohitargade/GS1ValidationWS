package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.CIPPublishToGLN;

public interface CIPPublishToGLNDAO {
	public List<CIPPublishToGLN> getAllCIPPublishToGLN(Integer Product_Export_Hdr_Id, Integer Product_Recipient_Id);

}
