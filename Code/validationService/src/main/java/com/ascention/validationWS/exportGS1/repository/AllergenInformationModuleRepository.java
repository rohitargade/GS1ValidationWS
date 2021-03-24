package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.beans.Duty;
import com.ascention.validationWS.beans.GPCAttributes;
import com.ascention.validationWS.beans.GTINCountryOrigin;
import com.ascention.validationWS.beans.OrderQty;
import com.ascention.validationWS.dao.DutyDAO;
import com.ascention.validationWS.dao.GPCAttributesDAO;
import com.ascention.validationWS.dao.GTINCountryOriginDAO;
import com.ascention.validationWS.dao.OrderQtyDAO;
import com.ascention.validationWS.exportGS1.beans.AlcoholInformationModule;
import com.ascention.validationWS.exportGS1.beans.AllergenInformationModule;
import com.ascention.validationWS.exportGS1.dao.AlcoholInformationModuleDAO;
import com.ascention.validationWS.exportGS1.dao.AllergenInformationModuleDAO;

@Repository
public class AllergenInformationModuleRepository implements AllergenInformationModuleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 


		public List<AllergenInformationModule> getAllAllergenInformationModule(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);			
		
			
			String sql = "-- ************************************************************************************\r\n" + 
					"-- AllergenInformationModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"SELECT		PA.[Specification_Agency]\r\n" + 
					"			,PA.[Specification_Name]\r\n" + 
					"			,PA.[Allergen_Statement]\r\n" + 
					"-- BEGIN ARRAY - Allergen Type\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Allergen_Type_Code]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Containment_Level]\r\n" + 
					"-- END ARRAY - Allergen Type\r\n" + 
					"FROM		[pm].[PFBT_Allergen] PA\r\n" + 
					"JOIN		[pm].[PFBT_Allergen_Type] PAT					ON	PAT.[Allergen_Id] = PA.[Allergen_Id]\r\n" + 
					"															AND	PAT.[Is_Active] = 1\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PAT.[Allergen_Type_Code]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PAT.[Containment_Level]\r\n" + 
					"WHERE		PA.[Product_Id] = :Product_Id\r\n" + 
					"AND			PA.[Is_Active] = 1";

			List<AllergenInformationModule> lstAllergenInformationModule = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(AllergenInformationModule.class));

		    return lstAllergenInformationModule;
		}
	
	
}

