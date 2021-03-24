package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.AVPHsnoApproval;

public interface AVPHsnoApprovalDAO {
	public List<AVPHsnoApproval> getAllPackaging(Integer Product_Id);

}
