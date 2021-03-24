package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.Nutrient_Header;
import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.dao.Nutrient_HeaderDAO;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;

@Repository
public class Nutrient_HeaderRepository implements Nutrient_HeaderDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<Nutrient_Header> getAllNutrient_Header(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);

			
			String sql = "SELECT		PNI.Nutrient_Info_Id\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Prep_State]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Nutrient_Basis_Qty_Type]\r\n" + 
					"			,PNI.[Nutrient_Basis_Qty]\r\n" + 
					"			,RL003.[Reference_List_Name] AS [Nutrient_Basis_Qty_Uom]\r\n" + 
					"			,PNI.[Nutrient_Basis_Qty_Desc]\r\n" + 
					"FROM		[pm].[PFBT_Nutrient_Info] PNI\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PNI.[Prep_State]\r\n" + 
					"JOIN		[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PNI.[Nutrient_Basis_Qty_Type]\r\n" + 
					"JOIN		[ref].[Reference_List] RL003					ON	RL003.[Reference_List_Id] = PNI.[Nutrient_Basis_Qty_Uom]\r\n" + 
					"--JOIN		[ref].[Reference_List] RL004					ON	RL004.[Reference_List_Id] = PNI.[Nutrient_Basis_Qty_Type]\r\n" + 
					"WHERE		PNI.Product_Id = :Product_Id\r\n" + 
					"AND			PNI.Is_Active = 1;";

			List<Nutrient_Header> lstNutrient_Header = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Nutrient_Header.class));

		    return lstNutrient_Header;
		}
	
	
}

