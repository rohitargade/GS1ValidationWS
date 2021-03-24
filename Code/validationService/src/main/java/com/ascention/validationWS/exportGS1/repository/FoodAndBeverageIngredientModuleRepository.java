package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.DutyFeeTaxInformationModule;
import com.ascention.validationWS.exportGS1.beans.FarmingAndProcessingInformationModule;
import com.ascention.validationWS.exportGS1.beans.FoodAndBeverageIngredientModule;
import com.ascention.validationWS.exportGS1.beans.OrderQuantity;
import com.ascention.validationWS.exportGS1.dao.DutyFeeTaxInformationModuleDAO;
import com.ascention.validationWS.exportGS1.dao.FarmingAndProcessingInformationModuleDAO;
import com.ascention.validationWS.exportGS1.dao.FoodAndBeverageIngredientModuleDAO;
import com.ascention.validationWS.exportGS1.dao.OrderQuantityDAO;

@Repository
public class FoodAndBeverageIngredientModuleRepository implements FoodAndBeverageIngredientModuleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;

		

		public FoodAndBeverageIngredientModule getAllFoodAndBeverageIngredientModule(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);			
			
			String sql = "-- ************************************************************************************\r\n" + 
					"-- FoodAndBeverageIngredientModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"SELECT		PFBT.[Ingr_Statement]\r\n" + 
					"FROM		[pm].[Product_Food_Beverage_Tobacco] PFBT\r\n" + 
					"WHERE		PFBT.[Product_Id] = :Product_Id\r\n" + 
					"AND			PFBT.[Is_Active] = 1;";

			FoodAndBeverageIngredientModule foodAndBeverageIngredientModule = null;
			try {
				foodAndBeverageIngredientModule = (FoodAndBeverageIngredientModule) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(FoodAndBeverageIngredientModule.class));
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    return foodAndBeverageIngredientModule;
		}
	
	
}

