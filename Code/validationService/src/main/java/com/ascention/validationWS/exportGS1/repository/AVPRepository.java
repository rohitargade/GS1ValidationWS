package com.ascention.validationWS.exportGS1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.AVP;
import com.ascention.validationWS.exportGS1.dao.AVPDAO;

@Repository
public class AVPRepository implements AVPDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;



		public AVP getAllAVP(Integer Product_Id, String strHierarchyIds, String strCOOIds, Integer Client_Id,
				Integer Org_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);			
			
			String sql =  
					"-- ************************************************************************************\r\n" + 
					"-- AVP\r\n" + 
					"-- ************************************************************************************\r\n" +					 
					"SELECT		CASE PH.[Is_Base_Unit]							-- UPDATED 02/02/2021\r\n" + 
					"				WHEN 1 THEN PC.[Full_Desc]					-- UPDATED 02/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 02/02/2021\r\n" + 
					"			END AS [Full_Desc]								-- UPDATED 02/02/2021\r\n" + 
					"			,PC.[Final_Batch_Expiry_Date]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Flash_Point_Type]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Hazardous_Identifier]\r\n" + 
					"			,RL003.[Reference_List_Name] AS [Dangerous_Good]\r\n" + 
					"			,RL004.[Reference_List_Name] AS [Hazardous_Good]\r\n" + 
					"			,PA.[Sds_Issue_Date]\r\n" + 
					"			,CASE 											-- AMENDED 02/02/2021\r\n" + 
					"				WHEN PH.[Is_Base_Unit] = 1 OR PH.[Is_Consumer_Unit] = 1\r\n" + 
					"				THEN RL005.[Reference_List_Name]			-- AMENDED 03/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 02/02/2021\r\n" + 
					"			END AS [Cds_Mat_Type]							-- UPDATED 02/02/2021\r\n" + 
					"			,RL006.[Reference_List_Name] AS [Has_Safety_Warning]\r\n" + 
					"\r\n" + 
					"			,CASE PH.[Is_Base_Unit]							-- UPDATED 05/02/2021\r\n" + 
					"				WHEN 1 THEN PA.[Labelling_Claims]			-- UPDATED 05/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 05/02/2021\r\n" + 
					"			END AS [Labelling_Claims]						-- UPDATED 05/02/2021\r\n" + 
					"			,CASE PH.[Is_Base_Unit]							-- UPDATED 05/02/2021\r\n" + 
					"				WHEN 1 THEN PA.[Consumer_Recommended_Use]	-- UPDATED 05/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 05/02/2021\r\n" + 
					"			END AS [Consumer_Recommended_Use]				-- UPDATED 05/02/2021\r\n" + 
					"			,PA.[Suitable_For]\r\n" + 
					"			,PA.[Custom_Stats_Code]\r\n" + 
					"			,PA.[Ce_Code]\r\n" + 
					"			,RL007.[Reference_List_Name] AS [Ce_Type]\r\n" + 
					"			,RL008.[Reference_List_Name] AS [Alcohol_Strength]\r\n" + 
					"			,RL009.[Reference_List_Name] AS [Champagne_Ind]\r\n" + 
					"			,PL.[Liquor_Age]\r\n" + 
					"			,RL010.[Reference_List_Name] AS [Liquor_Mkt_Segment]\r\n" + 
					"			,CASE PH.[Is_Base_Unit]							-- UPDATED 02/02/2021\r\n" + 
					"				WHEN 1 THEN PL.[Std_Drinks]					-- UPDATED 02/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 02/02/2021\r\n" + 
					"			END AS [Std_Drinks]								-- UPDATED 02/02/2021\r\n" + 
					"			,RL011.[Reference_List_Name] AS [Sweetness_Level_Ind]\r\n" + 
					"			,RL012.[Reference_List_Name] AS [Wooded_Code]\r\n" + 
					"\r\n" + 
					"			,CASE PH.[Is_Base_Unit]							-- UPDATED 05/02/2021\r\n" + 
					"				WHEN 1 THEN PFBT.[Shelf_Life_After_Thaw]	-- UPDATED 05/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 05/02/2021\r\n" + 
					"			END AS [Shelf_Life_After_Thaw]					-- UPDATED 05/02/2021\r\n" + 
					"\r\n" + 
					"			,CASE PH.[Is_Consumer_Unit]						-- UPDATED 05/02/2021\r\n" + 
					"				WHEN 1 THEN PFBT.[Gram_Measure_Dec]			-- UPDATED 05/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 05/02/2021\r\n" + 
					"			END AS [Gram_Measure_Dec]						-- UPDATED 05/02/2021 \r\n" + 
					"			,CASE PH.[Is_Consumer_Unit]						-- UPDATED 05/02/2021\r\n" + 
					"				WHEN 1 THEN PFBT.[Total_Energy_Dec]			-- UPDATED 05/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 05/02/2021\r\n" + 
					"			END AS [Total_Energy_Dec]						-- UPDATED 05/02/2021 	\r\n" + 
					"			\r\n" + 
					"			,CASE PH.[Is_Base_Unit]							-- UPDATED 05/02/2021\r\n" + 
					"				WHEN 1 THEN PFBT.[Health_Star_Rating] 		-- UPDATED 05/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 05/02/2021\r\n" + 
					"			END AS [Health_Star_Rating]						-- UPDATED 05/02/2021\r\n" + 
					"\r\n" + 
					"			,CASE PH.[Is_Base_Unit]							-- UPDATED 05/02/2021\r\n" + 
					"				WHEN 1 THEN RL013.[Reference_List_Name]		-- UPDATED 05/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 05/02/2021\r\n" + 
					"			END AS [Interpretive_Term_Opt_Nutrient]			-- UPDATED 05/02/2021\r\n" + 
					"			,CASE PH.[Is_Base_Unit]							-- UPDATED 05/02/2021\r\n" + 
					"				WHEN 1 THEN RL014.[Reference_List_Name]		-- UPDATED 05/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 05/02/2021\r\n" + 
					"			END AS [Interpretive_Term_Sat_Fat]				-- UPDATED 05/02/2021\r\n" + 
					"			,CASE PH.[Is_Base_Unit]							-- UPDATED 05/02/2021\r\n" + 
					"				WHEN 1 THEN RL015.[Reference_List_Name]		-- UPDATED 05/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 05/02/2021\r\n" + 
					"			END AS [Interpretive_Term_Sodium]				-- UPDATED 05/02/2021\r\n" + 
					"			,CASE PH.[Is_Base_Unit]							-- UPDATED 05/02/2021\r\n" + 
					"				WHEN 1 THEN RL016.[Reference_List_Name]		-- UPDATED 05/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 05/02/2021\r\n" + 
					"			END AS [Interpretive_Term_Sugar]				-- UPDATED 05/02/2021\r\n" + 
					"			,CASE PH.[Is_Base_Unit]							-- UPDATED 05/02/2021\r\n" + 
					"				WHEN 1 THEN PFBT.[Optional_Nutrient_Code]	-- UPDATED 05/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 05/02/2021\r\n" + 
					"			END AS [Optional_Nutrient_Code]					-- UPDATED 05/02/2021\r\n" + 
					"\r\n" + 
					"			,CASE PH.[Is_Base_Unit]							-- UPDATED 02/02/2021\r\n" + 
					"				WHEN 1 THEN RL017.[Reference_List_Name]		-- UPDATED 02/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 02/02/2021\r\n" + 
					"			END AS [Labelling_Logos] 						-- UPDATED 02/02/2021\r\n" + 
					"			,CASE PH.[Is_Base_Unit]							-- UPDATED 02/02/2021\r\n" + 
					"				WHEN 1 THEN RL018.[Reference_List_Name]		-- UPDATED 02/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 02/02/2021\r\n" + 
					"			END AS [Labelling_Country_Origin]				-- UPDATED 02/02/2021\r\n" + 
					"			,CASE PH.[Is_Base_Unit]							-- UPDATED 02/02/2021\r\n" + 
					"				WHEN 1 THEN RL019.[Reference_List_Name]		-- UPDATED 02/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 02/02/2021\r\n" + 
					"			END AS [Labelling_Ingr_Statement] 				-- UPDATED 02/02/2021\r\n" + 
					"			,CASE PH.[Is_Base_Unit]							-- UPDATED 02/02/2021\r\n" + 
					"				WHEN 1 THEN RL020.[Reference_List_Name]		-- UPDATED 02/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 02/02/2021\r\n" + 
					"			END AS [Labelling_Packed_Statement]				-- UPDATED 02/02/2021\r\n" + 
					"			,CASE PH.[Is_Base_Unit]							-- UPDATED 02/02/2021\r\n" + 
					"				WHEN 1 THEN PFBT.[Labelling_Aus_Content_Pct]-- UPDATED 02/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 02/02/2021\r\n" + 
					"			END AS [Labelling_Aus_Content_Pct]				-- UPDATED 02/02/2021\r\n" + 
					"			,CASE PH.[Is_Base_Unit]							-- UPDATED 02/02/2021\r\n" + 
					"				WHEN 1 THEN PFBT.[Labelling_Prod_Name]		-- UPDATED 02/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 02/02/2021\r\n" + 
					"			END AS [Labelling_Prod_Name]					-- UPDATED 02/02/2021\r\n" + 
					"			,CASE PH.[Is_Base_Unit]							-- UPDATED 02/02/2021\r\n" + 
					"				WHEN 1 THEN PFBT.[Labelling_Addtl_Phrase]	-- UPDATED 02/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 02/02/2021\r\n" + 
					"			END AS [Labelling_Addtl_Phrase]					-- UPDATED 02/02/2021\r\n" + 
					"			,CASE PH.[Is_Base_Unit]							-- UPDATED 02/02/2021\r\n" + 
					"				WHEN 1 THEN PFBT.[Labelling_Addtl_Dtls]		-- UPDATED 02/02/2021		\r\n" + 
					"				ELSE NULL									-- UPDATED 02/02/2021\r\n" + 
					"			END AS [Labelling_Addtl_Dtls]					-- UPDATED 02/02/2021\r\n" + 
					"			,RL031.[Reference_List_Name] AS [Priority_Food]\r\n" + 
					"			,CASE PH.[Is_Base_Unit]\r\n" + 
					"				WHEN 1 THEN PHC.[Tga_Warning_Statement]\r\n" + 
					"				ELSE NULL\r\n" + 
					"			END AS [Tga_Warning_Statement]\r\n" + 
					"			,PHCO1.[gtin]\r\n" + 
					"			,PH.[Is_Base_Unit]									\r\n" + 
					"			,CASE PH.[Is_Base_Unit]\r\n" + 
					"				WHEN 1 THEN NULL\r\n" + 
					"				ELSE PH.[Base_Unit_Qty]\r\n" + 
					"			END AS [Base_Unit_Qty]\r\n" + 
					"			,PH.[Child_Layer_Unit_Depth]\r\n" + 
					"			,PH.[Child_Layer_Unit_Width]\r\n" + 
					"			,PH.[Net_Volume]\r\n" + 
					"			,RL021.[Reference_List_Name] AS [Net_Volume_Uom]\r\n" + 
					"			,RL022.[Reference_List_Name] AS [Trade_Measurement_Method]\r\n" + 
					"			,PH.[Dec_Weight_Vol]\r\n" + 
					"			,RL023.[Reference_List_Name] AS [Dec_Weight_Vol_Uom]\r\n" + 
					"			,RL024.[Reference_List_Name] AS [Warning_On_Package]\r\n" + 
					"			,cast(PHA.[Nesting] as decimal(7,3)) AS [Nesting]\r\n" + 
					"			,RL025.[Reference_List_Name] AS [Hazard_Pack_Type]\r\n" + 
					"			,RL026.[Reference_List_Name] AS [Hazardous_Packaging_Type]\r\n" + 
					"			,CASE PH.[Is_Base_Unit]\r\n" + 
					"				WHEN 1 THEN PHA.[Hazardous_Unit_Vol]\r\n" + 
					"				ELSE PHA1.[Hazardous_Unit_Vol] * PH.Base_Unit_Qty\r\n" + 
					"			 END AS [Hazardous_Unit_Vol]\r\n" + 
					"			,CASE PH.[Is_Base_Unit]\r\n" + 
					"				WHEN 1 THEN RL027.[Reference_List_Name]\r\n" + 
					"				ELSE RL030.[Reference_List_Name]\r\n" + 
					"			 END AS [Hazardous_Unit_Vol_Uom]\r\n" + 
					"			,PHA.[Hazardous_Unit_Size]\r\n" + 
					"			,RL028.[Reference_List_Name] AS [Hazardous_Unit_Size_Uom]\r\n" + 
					"			,RL029.[Reference_List_Name] AS [Promotional_Product]\r\n" + 
					"			,PHA.[Mfr_Internal_Ref]\r\n" + 
					"\r\n" + 
					"FROM		[pm].[Product_Core] PC\r\n" + 
					"LEFT JOIN	[pm].[Product_Additional] PA					ON	PA.Product_Id = PC.Product_Id\r\n" + 
					"															AND	PA.Is_Active = 1\r\n" + 
					"LEFT JOIN	[pm].[Product_Liquor] PL						ON	PL.Product_Id = PC.Product_Id\r\n" + 
					"															AND	PL.Is_Active = 1\r\n" + 
					"LEFT JOIN	[pm].[Product_Food_Beverage_Tobacco] PFBT		ON	PFBT.Product_Id = PC.Product_Id\r\n" + 
					"															AND	PFBT.Is_Active = 1\r\n" + 
					"LEFT JOIN	[pm].[Product_Healthcare] PHC					ON	PHC.Product_Id = PC.Product_Id\r\n" + 
					"															AND	PHC.Is_Active = 1\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH						ON	PH.Product_Id = PC.Product_Id\r\n" + 
					"															AND	PH.[Product_Hierarchy_Id] in ("+strHierarchyIds+") \r\n" + 
					"															AND	PH.Is_Active = 1\r\n" + 
					"LEFT JOIN	[pm].[Product_Hierarchy] PH1					ON	PH1.[Product_Hierarchy_Id] = PH.[Base_Unit_Prod_Id]\r\n" + 
					"															AND PH1.[Is_Active] = 1\r\n" + 
					"JOIN		[pm].[P_H_Country_Origin] PHCO					ON	PHCO.[Product_Hierarchy_Id] = PH.[Product_Hierarchy_Id]\r\n" + 
					"															AND	PHCO.[Country_Origin_Id] in ("+strCOOIds+") \r\n" + 
					"LEFT JOIN	[pm].[P_H_Country_Origin] PHCO1					ON	PHCO1.[Product_Hierarchy_Id] = PH1.[Product_Hierarchy_Id]\r\n" + 
					"															AND	PHCO1.[Country_Origin] = PHCO.[Country_Origin]\r\n" + 
					"															AND	PHCO1.[State_Origin] = PHCO.[State_Origin]\r\n" + 
					"LEFT JOIN	[pm].[Product_Hierarchy_Additional] PHA			ON	PHA.Product_Hierarchy_Id = PH.Product_Hierarchy_Id\r\n" + 
					"															AND	PHA.Is_Active = 1\r\n" + 
					"LEFT JOIN	[pm].[Product_Hierarchy_Additional] PHA1		ON	PHA1.Product_Hierarchy_Id = PH1.Product_Hierarchy_Id\r\n" + 
					"															AND	PHA1.Is_Active = 1\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PA.[Flash_Point_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PA.[Hazardous_Identifier]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL003					ON	RL003.[Reference_List_Id] = PA.[Dangerous_Good]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL004					ON	RL004.[Reference_List_Id] = PA.[Hazardous_Good]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL005					ON	RL005.[Reference_List_Id] = PA.[Cds_Mat_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL006					ON	RL006.[Reference_List_Id] = PA.[Has_Safety_Warning]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL007					ON	RL007.[Reference_List_Id] = PA.[Ce_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL008					ON	RL008.[Reference_List_Id] = PL.[Alcohol_Strength]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL009					ON	RL009.[Reference_List_Id] = PL.[Champagne_Ind]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL010					ON	RL010.[Reference_List_Id] = PL.[Liquor_Mkt_Segment]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL011					ON	RL011.[Reference_List_Id] = PL.[Sweetness_Level_Ind]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL012					ON	RL012.[Reference_List_Id] = PL.[Wooded_Code]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL013					ON	RL013.[Reference_List_Id] = PFBT.[Interpretive_Term_Opt_Nutrient]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL014					ON	RL014.[Reference_List_Id] = PFBT.[Interpretive_Term_Sat_Fat]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL015					ON	RL015.[Reference_List_Id] = PFBT.[Interpretive_Term_Sodium]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL016					ON	RL016.[Reference_List_Id] = PFBT.[Interpretive_Term_Sugar]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL017					ON	RL017.[Reference_List_Id] = PFBT.[Labelling_Logos]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL018					ON	RL018.[Reference_List_Id] = PFBT.[Labelling_Country_Origin]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL019					ON	RL019.[Reference_List_Id] = PFBT.[Labelling_Ingr_Statement]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL020					ON	RL020.[Reference_List_Id] = PFBT.[Labelling_Packed_Statement]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL021					ON	RL021.[Reference_List_Id] = PH.[Net_Volume_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL022					ON	RL022.[Reference_List_Id] = PH.[Trade_Measurement_Method]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL023					ON	RL023.[Reference_List_Id] = PH.[Dec_Weight_Vol_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL024					ON	RL024.[Reference_List_Id] = PHA.[Warning_On_Package]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL025					ON	RL025.[Reference_List_Id] = PHA.[Hazard_Pack_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL026					ON	RL026.[Reference_List_Id] = PHA.[Hazardous_Packaging_Type]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL027					ON	RL027.[Reference_List_Id] = PHA.[Hazardous_Unit_Vol_Uom]\r\n" + 
					"\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL028					ON	RL028.[Reference_List_Id] = PHA.[Hazardous_Unit_Size_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL029					ON	RL029.[Reference_List_Id] = PHA.[Promotional_Product]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL030					ON	RL030.[Reference_List_Id] = PHA1.[Hazardous_Unit_Vol_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL031					ON	RL031.[Reference_List_Id] = PFBT.[Priority_Food]\r\n" + 
					"WHERE		PC.[Product_Id] = :Product_Id \r\n" + 
					"AND			PC.[Is_Active] = 1\r\n" + 
					"";
			AVP avp = null;
			try {
				avp = (AVP) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(AVP.class));
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    return avp;
		}
	
	
}

