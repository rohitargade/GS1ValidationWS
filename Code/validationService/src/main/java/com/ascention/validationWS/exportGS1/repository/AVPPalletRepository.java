package com.ascention.validationWS.exportGS1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.AVPPallet;
import com.ascention.validationWS.exportGS1.dao.AVPPalletDAO;

@Repository
public class AVPPalletRepository implements AVPPalletDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;



		public AVPPallet getAllAVPPallet( String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			
			String sql = "SELECT		PHSS.[Carton_Per_Ship_Unit]*PH.[Base_Unit_Qty] AS [baseUnitsPerPallet]\r\n" + 
					"			,SU.[Depth] AS [palletDepth]\r\n" + 
					"			,SU.[Width] AS [palletWidth]\r\n" + 
					"		 ,CASE RL001.Display_Name\r\n" + 
					"			WHEN 'PALLET' THEN \r\n" + 
					"				CASE SU.Fixed_Height_Su\r\n" + 
					"					WHEN 1 THEN SU.Height\r\n" + 
					"					ELSE	(\r\n" + 
					"							SU.Height + -- Pallet in mm\r\n" + 
					"							(CASE RL002.Reference_List_Name\r\n" + 
					"								WHEN 'CMT' THEN PH.[Height] * 10\r\n" + 
					"								WHEN 'MMT' THEN PH.[Height]\r\n" + 
					"								WHEN 'MTR' THEN PH.[Height] * 1000\r\n" + 
					"							END * PHSS.Carton_Layer_Per_Pallet ) + -- PRODUCT in mm\r\n" + 
					"							10 -- 10mm for wrapping/pack inconsistencies\r\n" + 
					"							-- HOW ESTIMATE??\r\n" + 
					"							) --* 1.005\r\n" + 
					"				END\r\n" + 
					"			ELSE NULL\r\n" + 
					"		 END AS [palletHeight]\r\n" + 
					"		 ,CASE RL001.Display_Name\r\n" + 
					"			WHEN 'PALLET' THEN \r\n" + 
					"				SU.Depth *\r\n" + 
					"				SU.Width *\r\n" + 
					"				(CASE SU.Fixed_Height_Su\r\n" + 
					"					WHEN 1 THEN SU.Height\r\n" + 
					"					ELSE	(\r\n" + 
					"							SU.Height +\r\n" + 
					"							(CASE RL002.Reference_List_Name\r\n" + 
					"								WHEN 'CMT' THEN PH.[Height] * 10\r\n" + 
					"								WHEN 'MMT' THEN PH.[Height]\r\n" + 
					"								WHEN 'MTR' THEN PH.[Height] * 1000\r\n" + 
					"							END * PHSS.Carton_Layer_Per_Pallet) +\r\n" + 
					"							10 -- 10mm for wrapping -- HOW ESTIMATE??\r\n" + 
					"							)\r\n" + 
					"				END) \r\n" + 
					"			ELSE NULL\r\n" + 
					"		 END AS [palletVolume] -- WHICH METRIC\r\n" + 
					"		 ,CASE RL001.Display_Name\r\n" + 
					"			WHEN 'PALLET' THEN \r\n" + 
					"				(PH.Base_Unit_Qty*PHSS.Carton_Per_Ship_Unit) *\r\n" + 
					"				CASE RL003.Reference_List_Name\r\n" + 
					"					WHEN 'GRM' THEN PH1.[Net_Weight] * 1000\r\n" + 
					"					WHEN 'KGM' THEN PH1.[Net_Weight]\r\n" + 
					"				END \r\n" + 
					"			ELSE NULL\r\n" + 
					"		END AS [palletNetWeight]\r\n" + 
					"		,CASE RL001.Display_Name\r\n" + 
					"			WHEN 'PALLET' THEN \r\n" + 
					"				(PHSS.Carton_Per_Ship_Unit * \r\n" + 
					"				CASE RL004.Reference_List_Name\r\n" + 
					"					WHEN 'GRM' THEN PH.[Gross_Weight] * 1000\r\n" + 
					"					WHEN 'KGM' THEN PH.[Gross_Weight]\r\n" + 
					"				END ) +\r\n" + 
					"				SU.Weight +\r\n" + 
					"				0.5 -- 0.5kg for wrapping -- HOW ESTIMATE??\r\n" + 
					"			ELSE NULL\r\n" + 
					"		END AS [palletGrossWeight]\r\n" + 
					"\r\n" + 
					"		,RL005.[Reference_List_Name] AS [Pallet_Build]\r\n" + 
					"FROM		[pm].[Product_Hierarchy] PH\r\n" + 
					"\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH1					ON	PH1.[Product_Hierarchy_Id] = PH.[Base_Unit_Prod_Id]\r\n" + 
					"JOIN		[pm].[P_H_Ship_Size] PHSS						ON	PHSS.[Product_Hierarchy_Id] = PH.[Product_Hierarchy_Id]\r\n" + 
					"															AND	PHSS.[Shipping_Unit_Id] = 1\r\n" + 
					"															AND	PHSS.[Is_Active] = 1\r\n" + 
					"JOIN		[ref].[Shipping_Unit] SU						ON	SU.[Shipping_Unit_Id] = PHSS.[Shipping_Unit_Id]\r\n" + 
					"															AND	SU.[Is_Active] = 1\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON RL001.[Reference_List_Id] = SU.[Su_Type]\r\n" + 
					"JOIN		[ref].[Reference_List] RL002					ON RL002.[Reference_List_Id] = PH.[Height_Uom]\r\n" + 
					"JOIN		[ref].[Reference_List] RL003					ON RL003.[Reference_List_Id] = PH1.[Net_Weight_Uom]\r\n" + 
					"JOIN		[ref].[Reference_List] RL004					ON RL004.[Reference_List_Id] = PH.[Gross_Weight_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL005					ON RL005.[Reference_List_Id] = PHSS.[Pallet_Build]			-- Updated 2021/02/04\r\n" + 
					"WHERE		PH.[Product_Hierarchy_Id] in ("+strHierarchyIds+") \r\n" + 
					"AND			PH.[Is_Despatch_Unit] = 1																					-- Updated 2021/02/04\r\n" + 
					"AND			PH.[Is_Active] = 1";

			AVPPallet AVPPallet = null;
			try {
				AVPPallet = (AVPPallet) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(AVPPallet.class));
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    return AVPPallet;
		}
	
	
}

