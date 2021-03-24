package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.TradeItemHierarchyQuantity;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.TradeItemHierarchyQuantityDAO;

@Repository
public class TradeItemHierarchyQuantityRepository implements TradeItemHierarchyQuantityDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<TradeItemHierarchyQuantity> getAllTradeItemHierarchyQuantity(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		

			String sql = "SELECT		PHSS.[Carton_Per_Ship_Unit] AS quantityOfTradeItemsPerPallet\r\n" + 
					"			,PHSS.[Carton_Layer_Per_Pallet] \r\n" + 
					"			,PHSS.[Carton_Per_Pallet_Layer] AS quantityOfTradeItemsPerPalletLayer\r\n" + 
					"			,PH.[Child_Unit_Qty]\r\n" + 
					"			,CASE	\r\n" + 
					"				WHEN	PH.[Base_Unit_Prod_Id] IS NOT NULL\r\n" + 
					"						AND PH.[Child_Unit_Prod_Id] IS NOT NULL \r\n" + 
					"						AND PH.[Base_Unit_Prod_Id] = PH.[Child_Unit_Prod_Id]\r\n" + 
					"				THEN	PH.Child_Layer_Cnt\r\n" + 
					"				WHEN	PH.[Base_Unit_Prod_Id] IS NOT NULL\r\n" + 
					"						AND PH.[Child_Unit_Prod_Id] IS NOT NULL \r\n" + 
					"						AND PH.[Base_Unit_Prod_Id] != PH.[Child_Unit_Prod_Id]\r\n" + 
					"				THEN	ISNULL(PH.Child_Layer_Cnt,1) \r\n" + 
					"						*\r\n" + 
					"						(SELECT	ISNULL(Child_Layer_Cnt,1)\r\n" + 
					"						FROM	[pm].[Product_Hierarchy]\r\n" + 
					"						WHERE	[Product_Id] = :Product_Id\r\n" + 
					"						AND		[Product_Hierarchy_Id] = PH.[Child_Unit_Prod_Id]\r\n" + 
					"						AND		[Is_Active] = 1)\r\n" + 
					"				ELSE	NULL\r\n" + 
					"			END AS [quantityOfCompleteLayersContainedInATradeItem]\r\n" + 
					"FROM		[pm].[Product_Hierarchy] PH\r\n" + 
					"LEFT JOIN	[pm].[P_H_Ship_Size] PHSS						ON	PHSS.[Product_Hierarchy_Id] = PH.[Product_Hierarchy_Id]\r\n" + 
					"															AND	PHSS.[Shipping_Unit_Id] = 1		\r\n" + 
					"															AND	PHSS.[Is_Active] = 1\r\n" + 
					"LEFT JOIN	[ref].[Shipping_Unit] SU						ON	SU.[Shipping_Unit_Id] = PHSS.[Shipping_Unit_Id]\r\n" + 
					"															AND	SU.[Is_Active] = 1\r\n" + 
					"left JOIN	[ref].[Reference_List] RL1						ON RL1.[Reference_List_Id] = SU.[Su_Type] \r\n" + 
					"WHERE		PH.[Product_Hierarchy_Id] in ("+strHierarchyIds+")\r\n" + 
					"AND			PH.[Is_Active] = 1\r\n" + 
					"AND			PH.[Is_Base_Unit] = 0;\r\n" + 
					"";

			List<TradeItemHierarchyQuantity> lstTradeItemHierarchyQuantity = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(TradeItemHierarchyQuantity.class));

		    return lstTradeItemHierarchyQuantity;
		}
	
	
}

