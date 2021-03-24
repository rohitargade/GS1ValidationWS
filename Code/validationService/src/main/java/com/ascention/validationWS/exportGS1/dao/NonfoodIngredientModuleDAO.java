package com.ascention.validationWS.exportGS1.dao;

import com.ascention.validationWS.exportGS1.beans.NonfoodIngredientModule;

public interface NonfoodIngredientModuleDAO {
	public NonfoodIngredientModule getAllNonfoodIngredientModule(Integer Product_Id, String strHierarchyIds);

}
