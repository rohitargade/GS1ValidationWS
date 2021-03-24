package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.DurableGoodsCharacteristicsModule;

public interface DurableGoodsCharacteristicsModuleDAO {
	public List<DurableGoodsCharacteristicsModule> getAllDurableGoodsCharacteristicsModule( String strHierarchyIds);

}
