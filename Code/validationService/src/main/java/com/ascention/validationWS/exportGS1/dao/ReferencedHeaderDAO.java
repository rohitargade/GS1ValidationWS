package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.MicrobiologicalInformation;
import com.ascention.validationWS.exportGS1.beans.ProductCharacteristicsModule;
import com.ascention.validationWS.exportGS1.beans.ReferencedFileHeader;

public interface ReferencedHeaderDAO {
	public List<ReferencedFileHeader> getAllReferencedFileDetailInformationModule(Integer Product_Id);

}
