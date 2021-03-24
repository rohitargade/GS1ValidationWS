package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Import_Classification;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.Import_ClassificationDAO;

@Repository
public class Import_ClassificationRepository implements Import_ClassificationDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<Import_Classification> getAllImport_Classification(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			
			String sql = "SELECT		RL001.[Reference_List_Name] AS [Import_Classification_Type]\r\n" + 
					"			,PA.[Import_Classification_Value]\r\n" + 
					"			,PL.[Provenance_Statement]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Prod_Activity_Type]\r\n" + 
					"			,PFBT.[Prod_Activity_Region_Desc]\r\n" + 
					"FROM		[pm].[Product_Core] PC\r\n" + 
					"LEFT JOIN	[pm].[Product_Additional] PA					ON	PA.[Product_Id] = PC.[Product_Id]\r\n" + 
					"															AND	PA.[Is_Active] = 1\r\n" + 
					"LEFT JOIN	[pm].[Product_Liquor] PL						ON	PL.[Product_Id] = PC.[Product_Id]\r\n" + 
					"															AND	PL.[Is_Active] = 1\r\n" + 
					"LEFT JOIN	[pm].[Product_Food_Beverage_Tobacco] PFBT		ON	PFBT.[Product_Id] = PC.[Product_Id]\r\n" + 
					"															AND	PFBT.[Is_Active] = 1\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PA.[Import_Classification_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PFBT.[Prod_Activity_Type]\r\n" + 
					"WHERE		PC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PC.[Is_Active] = 1;";

			List<Import_Classification> lstImport_Classification = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Import_Classification.class));

		    return lstImport_Classification;
		}
	
	
}

