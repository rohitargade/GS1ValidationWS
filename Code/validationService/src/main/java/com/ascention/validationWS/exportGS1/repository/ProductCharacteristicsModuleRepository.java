package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.ProductCharacteristicsModule;
import com.ascention.validationWS.exportGS1.dao.ProductCharacteristicsModuleDAO;

@Repository
public class ProductCharacteristicsModuleRepository implements ProductCharacteristicsModuleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
	

		public List<ProductCharacteristicsModule> getAllProductCharacteristicsModule(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			
			String sql = "SELECT		RL001.[Reference_List_Name] AS [Prod_Characteristic_Code]\r\n" + 
					"			,PPC.[Prod_Characteristic_Desc]	\r\n" + 
					"			,PPC.[Prod_Characteristic_Value_Boolean]\r\n" + 
					"FROM		[pm].[PA_Prod_Characteristic] PPC\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PPC.[Prod_Characteristic_Code]\r\n" + 
					"WHERE		PPC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PPC.[Is_Active] = 1;" + 
					"";

			List<ProductCharacteristicsModule> lstProductCharacteristicsModule = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(ProductCharacteristicsModule.class));

		    return lstProductCharacteristicsModule;
		}
	
	
}

