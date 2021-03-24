package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.TradeItemDescriptionInformation;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.TradeItemDescriptionInformationDAO;

@Repository
public class TradeItemDescriptionInformationRepository implements TradeItemDescriptionInformationDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<TradeItemDescriptionInformation> getAllTradeItemDescriptionInformation(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			String sql = "SELECT		PC.[Addl_Prod_Desc]\r\n" + 
					"			,PC.[Short_Desc]\r\n" + 
					"			,PC.[Functional_Name]\r\n" + 
					"			,PA.[Label_Desc]\r\n" + 
					"			,CONCAT	(PC.[Full_Desc] -- B.[Brand_Name], SB.[Sub_Brand_Name], PC.[Functional_Name], PHNC.[Net_Content], PHNC.[Net_Content_Uom]\r\n" + 
					"					,CASE	\r\n" + 
					"						WHEN	PH.[Base_Unit_Prod_Id] IS NOT NULL\r\n" + 
					"								AND PH.[Child_Unit_Prod_Id] IS NOT NULL \r\n" + 
					"								AND PH.[Base_Unit_Prod_Id] = PH.[Child_Unit_Prod_Id]\r\n" + 
					"						THEN	CONCAT	(' x '\r\n" + 
					"										,PH.[Child_Unit_Qty])\r\n" + 
					"						WHEN	PH.[Base_Unit_Prod_Id] IS NOT NULL\r\n" + 
					"								AND PH.[Child_Unit_Prod_Id] IS NOT NULL \r\n" + 
					"								AND PH.[Base_Unit_Prod_Id] != PH.[Child_Unit_Prod_Id]\r\n" + 
					"						THEN	CONCAT	(' x '\r\n" + 
					"										,	(SELECT	[Child_Unit_Qty]\r\n" + 
					"											FROM	[pm].[Product_Hierarchy]\r\n" + 
					"											WHERE	[Product_Id] = :Product_Id\r\n" + 
					"											AND		[Product_Hierarchy_Id] = PH.[Child_Unit_Prod_Id]\r\n" + 
					"											AND		[Is_Active] = 1)\r\n" + 
					"										,' x ',PH.[Child_Unit_Qty])\r\n" + 
					"						ELSE	NULL\r\n" + 
					"					END) AS [TradeItemDescription]\r\n" + 
					"			,PC.[Prod_Group_Code]\r\n" + 
					"			,PC.[Prod_Group_Code_Desc]\r\n" + 
					"			,B.[Brand_Name]\r\n" + 
					"			,SB.[Sub_Brand_Name]\r\n" + 
					"			,PA.[Colour_Code]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Colour_Code_Provider]\r\n" + 
					"			,PA.[Colour_Desc]\r\n" + 
					"FROM		[pm].[Product_Core] PC\r\n" + 
					"LEFT JOIN	[pm].[Product_Additional] PA					ON	PA.[Product_Id] = PC.[Product_Id]\r\n" + 
					"															AND	PA.[Is_Active] = 1\r\n" + 
					"JOIN		[pm].[Brand] B									ON	B.[Brand_Id] = PC.[Brand_Id]\r\n" + 
					"															AND B.[Is_Active] = 1\r\n" + 
					"LEFT JOIN	[pm].[Sub_Brand] SB								ON	SB.[Sub_Brand_Id] = PC.[Sub_Brand_Id]\r\n" + 
					"															AND SB.[Is_Active] = 1\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH						ON	PH.[Product_Hierarchy_Id] in ("+strHierarchyIds+")\r\n" + 
					"															AND	PH.Is_Active = 1\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PA.[Colour_Code_Provider]\r\n" + 
					"WHERE		PC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PC.[Is_Active] = 1;";

			List<TradeItemDescriptionInformation> lstTradeItemDescriptionInformation = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(TradeItemDescriptionInformation.class));

		    return lstTradeItemDescriptionInformation;
		}
	
	
}

