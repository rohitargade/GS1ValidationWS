package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.Nutritional_Claim_Detail;
import com.ascention.validationWS.exportGS1.dao.Nutritional_Claim_DetailDAO;

@Repository
public class Nutritional_Claim_DetailRepository implements Nutritional_Claim_DetailDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
		public List<Nutritional_Claim_Detail> getAllNutritional_Claim_Detail(Integer Product_Id, String Product_Hierarchy_id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
//			String sql = "SELECT		RL001.[Reference_List_Name] AS [Nutritional_Claim_Type]\r\n" + 
//					"			,RL002.[Reference_List_Name] AS [Nutritional_Claim_Element]\r\n" + 
//					"FROM		[pm].[PFBT_Nutritional_Claim_Type] PNCT\r\n" + 
//					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PNCT.[Nutritional_Claim_Type]\r\n" + 
//					"JOIN		[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PNCT.[Nutritional_Claim_Element]\r\n" + 
//					"WHERE		PNCT.Product_Id = :Product_Id\r\n" + 
//					"AND			PNCT.Is_Active = 1;";
			
			String sql ="SELECT        RL001.[Reference_List_Name] AS [Nutritional_Claim_Type]   -- Consumer ONLY\r\n" + 
			"                     ,RL002.[Reference_List_Name] AS [Nutritional_Claim_Element]-- Consumer ONLY\r\n" + 
			"FROM          [pm].[PFBT_Nutritional_Claim_Type] PNCT\r\n" + 
			"JOIN          [ref].[Reference_List] RL001                                  ON       RL001.[Reference_List_Id] = PNCT.[Nutritional_Claim_Type]\r\n" + 
			"JOIN          [ref].[Reference_List] RL002                                  ON       RL002.[Reference_List_Id] = PNCT.[Nutritional_Claim_Element]\r\n" + 
			"WHERE         PNCT.Product_Id = :Product_Id\r\n" + 
			"AND                  PNCT.Is_Active = 1\r\n" + 
			"AND                  (SELECT PH.Is_Consumer_Unit FROM pm.Product_Hierarchy PH WHERE PH.Product_Hierarchy_Id in ("+Product_Hierarchy_id+")) = 1 -- ADDED 05/02/2021\r\n" + 
			"";

			List<Nutritional_Claim_Detail> lstNutritional_Claim_Detail = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Nutritional_Claim_Detail.class));

		    return lstNutritional_Claim_Detail;
		}
	
	
}

