package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PricingPSDDetail;
import com.ascention.validationWS.exportGS1.dao.PricingPSDDetailDAO;

@Repository
public class PricingPSDDetailRepository implements PricingPSDDetailDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	


		public List<PricingPSDDetail> getAllPricingPSDDetail(Integer Price_Export) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Price_Export", Price_Export);
			
			
			String sql = "SELECT		 PD.[Price_Dtl_Id] -- ADDED 7/12\r\n" + 
					"			,PD.[Price_Type_App_Seq]\r\n" + 
					"			,HL001.[Hierarchy_List_Code] AS [Price_Type]\r\n" + 
					"			,HL002.[Hierarchy_List_Code] AS [Price_Type_Subcat]\r\n" + 
					"			,PD.[Price_Value]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Price_Value_Type]\r\n" + 
					"			,PD.[Target_Price_Id] -- ADDED 7/12\r\n" + 
					"--			,RL002.[Reference_List_Name] AS [Alt_Loc_Grouping]			-- Removed 22/02\r\n" + 
					"			,PD.[Alt_Loc_Grouping]	AS [Alt_Loc_Grouping]	     		-- ADDED 22/02\r\n" + 
					"			,PD.[Ref_Doc_Identifier]\r\n" + 
					"			,PD.[Ref_Doc_Desc]\r\n" + 
					"			,CASE														-- ADDED 22/02\r\n" + 
					"				WHEN	PD.[Price_Region] LIKE '%-All'	THEN NULL		-- ADDED 22/02\r\n" + 
					"				WHEN	PD.[Price_Region] = 'AU-ACT'	THEN 'AU-CT'	-- ADDED 22/02\r\n" + 
					"				WHEN	PD.[Price_Region] = 'AU-NSW'	THEN 'AU-NS'	-- ADDED 22/02\r\n" + 
					"				WHEN	PD.[Price_Region] = 'AU-QLD'	THEN 'AU-QL'	-- ADDED 22/02\r\n" + 
					"				WHEN	PD.[Price_Region] = 'AU-TAS'	THEN 'AU-TS'	-- ADDED 22/02\r\n" + 
					"				WHEN	PD.[Price_Region] = 'AU-VIC'	THEN 'AU-VI'	-- ADDED 22/02\r\n" + 
					"				ELSE	PD.[Price_Region]								-- ADDED 22/02\r\n" + 
					"			END	AS [Price_Region]										-- ADDED 22/02	\r\n" + 
					"FROM		[sm].[Price_Dtl] PD	\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PD.[Price_Value_Type]\r\n" + 
					"--LEFT JOIN	[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PD.[Alt_Loc_Grouping] 	-- Removed 22/02\r\n" + 
					"JOIN		[ref].[Hierarchy_List] HL001					ON	HL001.[Hierarchy_List_Id] = PD.[Price_Type]\r\n" + 
					"LEFT JOIN		[ref].[Hierarchy_List] HL002				ON	HL002.[Hierarchy_List_Id] = PD.[Price_Type_Subcat]\r\n" + 
					"WHERE		PD.[Price_Hdr_Id] = (SELECT	PE.Price_Hdr_Id \r\n" + 
					"					FROM		[dm].[Price_Export] PE \r\n" + 
					"					WHERE		PE.[Price_Export_Id] = :Price_Export \r\n" + 
					"					AND			PE.[Is_Active] = 1) \r\n" + 
					"AND			PD.[Is_Active] = 1;\r\n"; 
				
			List<PricingPSDDetail> lstPricingPSDDetail = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(PricingPSDDetail.class));

		    return lstPricingPSDDetail;
		}
	
	
}

