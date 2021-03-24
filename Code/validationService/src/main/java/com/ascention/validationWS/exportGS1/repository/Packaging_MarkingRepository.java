package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging_Marking;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.Packaging_MarkingDAO;

@Repository
public class Packaging_MarkingRepository implements Packaging_MarkingDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<Packaging_Marking> getAllPackaging_Marking(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			String sql = "-- ************************************************************************************\r\n" + 
					"-- PackagingMarkingModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"\r\n" + 
					"SELECT		CASE PH.[Is_Base_Unit]				-- UPDATED 02/02/2021\r\n" + 
					"				WHEN 1 THEN PC.[Batch_Num_Reqd]	-- UPDATED 02/02/2021		\r\n" + 
					"				ELSE NULL						-- UPDATED 02/02/2021\r\n" + 
					"			END AS [Batch_Num_Reqd]				-- UPDATED 02/02/2021\r\n" + 
					"			,PA.[Warning_Copy_Desc]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Packaging_Label_Accreditation]\r\n" + 
					"FROM		[pm].[Product_Core] PC\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH						ON	PH.[Product_Id] = PC.[Product_Id]			-- ADDED 2021/02/02\r\n" + 
					"															AND	PH.[Is_Active] = 1							-- ADDED 2021/02/02\r\n" + 
					"															AND	PH.[Product_Hierarchy_Id] in ("+strHierarchyIds+")		-- ADDED 2021/02/02\r\n" + 
					"															AND PH.[Is_Base_Unit] = 1						-- ADDED 2021/02/02\r\n" + 
					"LEFT JOIN	[pm].[Product_Additional] PA					ON	PA.[Product_Id] = PC.[Product_Id]\r\n" + 
					"															AND	PA.[Is_Active] = 1\r\n" + 
					"LEFT JOIN	[pm].[Product_Food_Beverage_Tobacco] PFBT		ON	PFBT.[Product_Id] = PC.[Product_Id]\r\n" + 
					"															AND	PFBT.[Is_Active] = 1\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PFBT.[Packaging_Label_Accreditation]\r\n" + 
					"WHERE		PC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PC.[Is_Active] = 1;";

			List<Packaging_Marking> lstPackaging_Marking = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Packaging_Marking.class));

		    return lstPackaging_Marking;
		}
	
	
}

