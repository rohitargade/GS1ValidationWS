package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.TransportationHazardousClassification;

public interface TransportationHazardousClassificationDAO {
	public List<TransportationHazardousClassification> getAllTransportationHazardousClassification(Integer Product_Id);

}
