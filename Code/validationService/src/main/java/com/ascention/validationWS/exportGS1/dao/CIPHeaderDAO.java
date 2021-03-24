package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.CIPHeader;

public interface CIPHeaderDAO {
	public List<CIPHeader> getAllCIPHeader( Integer Product_Export_Hdr_Id);

}
