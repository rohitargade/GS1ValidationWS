package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.DurableGoodsCharacteristicsModule;
import com.ascention.validationWS.exportGS1.dao.DurableGoodsCharacteristicsModuleDAO;

@Repository
public class DurableGoodsCharacteristicsModuleRepository implements DurableGoodsCharacteristicsModuleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;

	public List<DurableGoodsCharacteristicsModule> getAllDurableGoodsCharacteristicsModule(
				String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			
			String sql = "-- ************************************************************************************\r\n" + 
					"-- DurableGoodsCharacteristicsModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"SELECT		PH.[Prod_Finish_Desc]\r\n" + 
					"FROM		[pm].[Product_Hierarchy] PH\r\n" + 
					"WHERE		[Product_Hierarchy_Id] in ( "+strHierarchyIds+")\r\n" + 
					"AND			PH.[Is_Active] = 1";

			List<DurableGoodsCharacteristicsModule> lstDurableGoodsCharacteristicsModule = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(DurableGoodsCharacteristicsModule.class));

		    return lstDurableGoodsCharacteristicsModule;
		}
	
	
}

