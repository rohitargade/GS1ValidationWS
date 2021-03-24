package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.AdditiveInformation;
import com.ascention.validationWS.exportGS1.beans.Battery_Type;
import com.ascention.validationWS.exportGS1.beans.FoodAndBeverageIngredient;
import com.ascention.validationWS.exportGS1.beans.Grow_Method;
import com.ascention.validationWS.exportGS1.dao.AdditiveInformationDAO;
import com.ascention.validationWS.exportGS1.dao.BatteryTypeDAO;
import com.ascention.validationWS.exportGS1.dao.FoodAndBeverageIngredientDAO;
import com.ascention.validationWS.exportGS1.dao.GrowMethodDAO;

@Repository
public class FoodAndBeverageIngredientRepository implements FoodAndBeverageIngredientDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<FoodAndBeverageIngredient> getAllFoodAndBeverageIngredient(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			String sql = "SELECT		PIN.[Ingredient_Name]\r\n" + 
					"			,PIN.[Ingredient_Seq]\r\n" + 
					"FROM		[pm].[PFBT_Ingredient] PIN\r\n" + 
					"WHERE		PIN.[Product_Id] = :Product_Id\r\n" + 
					"AND			PIN.[Is_Active] = 1;";

			List<FoodAndBeverageIngredient> lstFoodAndBeverageIngredient = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(FoodAndBeverageIngredient.class));

		    return lstFoodAndBeverageIngredient;
		}
	
	
}

