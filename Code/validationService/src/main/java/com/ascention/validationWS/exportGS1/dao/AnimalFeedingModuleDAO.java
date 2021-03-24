package com.ascention.validationWS.exportGS1.dao;

import java.util.List;


import com.ascention.validationWS.exportGS1.beans.Animal_Nutrient_Detail;

public interface AnimalFeedingModuleDAO {
	public List<Animal_Nutrient_Detail> getAllAnimal_Nutrient_Detail(Integer Product_Id);

}
