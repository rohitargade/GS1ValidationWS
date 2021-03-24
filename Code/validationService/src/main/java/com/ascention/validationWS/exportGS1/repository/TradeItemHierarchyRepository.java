package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.TradeItemHierarchy;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.TradeItemHierarchyDAO;

@Repository
public class TradeItemHierarchyRepository implements TradeItemHierarchyDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<TradeItemHierarchy> getAllTradeItemHierarchy(Integer Product_Id, String strHierarchyIds,
				String strCOOIds, Integer Client_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
			
			parameters.addValue("Client_Id", Client_Id);
			
			String sql = "SELECT		PC.[Product_Id]\r\n" + 
					"			,PH.[Product_Hierarchy_Id]\r\n" + 
					"			,PHCO.[Gtin]\r\n" + 
					"			,PH.[Is_Base_Unit]\r\n" + 
					"			,PH.[Is_Consumer_Unit]\r\n" + 
					"			,PH.[Is_Despatch_Unit]\r\n" + 
					"			,PH.[Is_Invoice_Unit]\r\n" + 
					"			,PH.[Is_Orderable_Unit]\r\n" + 
					"			,isnull(PC.[Is_Service],0) AS [Is_Service]\r\n" + 
					"			,isnull(PC.[Is_Non_Physical],0) AS [Is_Non_Physical]\r\n" + 
					"			,PC.[Is_Recalled]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Prod_Unit_Desc]\r\n" + 
					"			,O01.[Organisation_GLN] AS [Brand_Owner_GLN]\r\n" + 
					"			,O01.[Organisation_LegalName] AS [Brand_Owner_Name]\r\n" + 
					"			,CASE \r\n" + 
					"				WHEN O01.[Organisation_GLN] IS NULL THEN NULL\r\n" + 
					"				ELSE	CONCAT(	OA01.[Organisation_Address_Unit],' '\r\n" + 
					"								,OA01.[Organisation_Address_Number],' '\r\n" + 
					"								,OA01.[Organisation_Address_StreetName],' '\r\n" + 
					"								,OA01.[Organisation_Address_Suburb],' '\r\n" + 
					"								,OA01.[Organisation_Address_County],' '\r\n" + 
					"								,CS01.[Display_Name] ,' '\r\n" + 
					"								,OA01.[Organisation_Address_PostCode],' '\r\n" + 
					"								,C01.[Display_Name] )\r\n" + 
					"			END AS [Brand_Owner_Address]\r\n" + 
					"			,O02.[Organisation_GLN] AS [Manufacturer_GLN]\r\n" + 
					"			,O02.[Organisation_LegalName] AS [Manufacturer_Name]\r\n" + 
					"			,CASE \r\n" + 
					"				WHEN O02.[Organisation_GLN] IS NULL THEN NULL\r\n" + 
					"				ELSE	CONCAT(	OA02.[Organisation_Address_Unit],' '\r\n" + 
					"								,OA02.[Organisation_Address_Number],' '\r\n" + 
					"								,OA02.[Organisation_Address_StreetName],' '\r\n" + 
					"								,OA02.[Organisation_Address_Suburb],' '\r\n" + 
					"								,OA02.[Organisation_Address_County],' '\r\n" + 
					"								,CS02.[Display_Name] ,' '\r\n" + 
					"								,OA02.[Organisation_Address_PostCode],' '\r\n" + 
					"								,C02.[Display_Name] ) \r\n" + 
					"			END AS [Manufacturer_Address]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Display_Ready_Packaging]\r\n" + 
					"			,PC.[Gpc_Brick]\r\n" + 
					"			-- gpcCategoryDefinition - NEEDED?\r\n" + 
					"			-- gpcCategoryName - NEEDED?\r\n" + 
					"			,PH.[Community_Visibility_Date]\r\n" + 
					"			,PHC.Effective_Date\r\n" + 
					"			,PHC.Publication_Date\r\n" + 
					"FROM		[pm].[Product_Core] PC\r\n" + 
					"-- ************************************************************************************************************************\r\n" + 
					"-- Non Repeated Tables\r\n" + 
					"-- ************************************************************************************************************************\r\n" + 
					"JOIN		[pm].[Brand] B									ON	B.Brand_Id = PC.Brand_Id\r\n" + 
					"															AND B.[Is_Active] = 1\r\n" + 
					"LEFT JOIN	[pm].[Brand_Organisation] BO					ON	BO.[Brand_Id] = B.[Brand_Id]\r\n" + 
					"															AND BO.[Is_Active] = 1\r\n" + 
					"															AND (\r\n" + 
					"																(B.[Ownership_Taken] = 1 AND BO.[Organisation_Type] = 14580)\r\n" + 
					"																OR\r\n" + 
					"																(B.[Ownership_Taken] = 0 AND BO.[Organisation_Type] = 14580 AND B.[Info_Provider_Id] != BO.[Organisation_Id] )\r\n" + 
					"																)\r\n" + 
					"\r\n" + 
					"LEFT JOIN	[pm].[Sub_Brand] SB								ON	SB.[Sub_Brand_Id] = PC.[Sub_Brand_Id]\r\n" + 
					"															AND SB.[Is_Active] = 1\r\n" + 
					"LEFT JOIN	[pm].[Product_Additional] PA					ON	PA.[Product_Id] = PC.[Product_Id]\r\n" + 
					"															AND	PA.[Is_Active] = 1\r\n" + 
					"\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH						ON	PH.[Product_Id] = PC.[Product_Id]\r\n" + 
					"															AND	PH.[Is_Active] = 1\r\n" + 
					"LEFT JOIN	[pm].[Product_Hierarchy_Additional] PHA			ON	PHA.Product_Hierarchy_Id = PH.Product_Hierarchy_Id\r\n" + 
					"															AND	PHA.[Is_Active] = 1\r\n" + 
					"JOIN		[pm].[P_H_Country_Origin] PHCO					ON PHCO.[Product_Hierarchy_Id] = PH.[Product_Hierarchy_Id]\r\n" + 
					"															AND	PHCO.[Is_Active] = 1\r\n" + 
					"															AND PHCO.[Country_Origin_Id] in ("+strCOOIds+")\r\n" + 
					"LEFT JOIN	[pm].[Product_Hierarchy_Client] PHC				ON	PHC.[Product_Hierarchy_Id] = PH.[Product_Hierarchy_Id]\r\n" + 
					"															AND	PHC.[Active] = 1\r\n" + 
					"															AND PHC.Client_Id = :Client_Id\r\n" + 
					"LEFT JOIN	[cm].[Organisation] O01							ON	O01.[Organisation_Id] = BO.[Organisation_Id]\r\n" + 
					"															AND O01.[Organisation_GLN] IS NOT NULL\r\n" + 
					"LEFT JOIN	[cm].[Organisation] O02							ON	O02.[Organisation_Id] = PH.[Manufacturer_Id] \r\n" + 
					"															AND O02.[Organisation_GLN] IS NOT NULL\r\n" + 
					"LEFT JOIN	[cm].[Organisation_Address]	OA01				ON	OA01.[Organisation_Id] = O01.[Organisation_Id] \r\n" + 
					"															AND OA01.[Is_Active] = 1\r\n" + 
					"															AND	OA01.[Organisation_Address_IsPrimary] = 1\r\n" + 
					"LEFT JOIN	[cm].[Organisation_Address]	OA02				ON	OA02.[Organisation_Id] = O02.[Organisation_Id]\r\n" + 
					"															AND OA02.[Is_Active] = 1\r\n" + 
					"															AND	OA02.[Organisation_Address_IsPrimary] = 1\r\n" + 
					"-- ************************************************************************************************************************\r\n" + 
					"-- Reference Data Lists\r\n" + 
					"-- ************************************************************************************************************************\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PH.[Prod_Unit_Desc]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PHA.[Display_Ready_Packaging]\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"LEFT JOIN	[ref].[Country] C01								ON	C01.[Alpha_2] = OA01.[Organisation_Address_Country]\r\n" + 
					"LEFT JOIN	[ref].[Country] C02								ON	C02.[Alpha_2] = OA01.[Organisation_Address_Country]\r\n" + 
					"LEFT JOIN	[ref].[Country_Subdivision] CS01				ON	CS01.Subdivision_Code = OA01.[Organisation_Address_State]\r\n" + 
					"LEFT JOIN	[ref].[Country_Subdivision] CS02				ON	CS02.Subdivision_Code = OA01.[Organisation_Address_State]\r\n" + 
					"\r\n" + 
					"WHERE		PC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PC.[Is_Active] = 1\r\n" + 
					"AND			PH.[Product_Hierarchy_Id] in ("+strHierarchyIds+");";

			List<TradeItemHierarchy> lstTradeItemHierarchy = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(TradeItemHierarchy.class));

		    return lstTradeItemHierarchy;
		}
	
	
}

