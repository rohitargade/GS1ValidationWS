package com.ascention.validationWS.exportGS1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.NonfoodIngredientModule;
import com.ascention.validationWS.exportGS1.dao.NonfoodIngredientModuleDAO;

@Repository
public class NonfoodIngredientModuleRepository implements NonfoodIngredientModuleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	


		public NonfoodIngredientModule getAllNonfoodIngredientModule(Integer Product_Id,String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);			
		
			
			String sql = "-- ************************************************************************************\r\n" + 
					"-- NonfoodIngredientModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"\r\n" + 
					"SELECT		PA.[Non_Food_Ingredient_Statement]	\r\n" + 
					"FROM		[pm].[Product_Additional] PA\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH						ON	PH.[Product_Id] = PA.[Product_Id]			-- ADDED 2021/02/02\r\n" + 
					"															AND	PH.[Is_Active] = 1							-- ADDED 2021/02/02\r\n" + 
					"															AND	PH.[Product_Hierarchy_Id] in ("+strHierarchyIds+")		-- ADDED 2021/02/02\r\n" + 
					"															AND PH.[Is_Base_Unit] = 1						-- ADDED 2021/02/02\r\n" + 
					"WHERE		PA.[Product_Id] = :Product_Id\r\n" + 
					"AND			PA.[Is_Active] = 1;";

			NonfoodIngredientModule nonfoodIngredientModule = null;
			try {
				nonfoodIngredientModule = (NonfoodIngredientModule) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(NonfoodIngredientModule.class));
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    return nonfoodIngredientModule;
		}
	
	
}

