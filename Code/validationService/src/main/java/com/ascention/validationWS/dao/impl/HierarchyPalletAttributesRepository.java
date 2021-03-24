package com.ascention.validationWS.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.beans.HierarchyPalletAttributes;
import com.ascention.validationWS.dao.HierarchyPalletAttributesDAO;

@Repository
public class HierarchyPalletAttributesRepository implements HierarchyPalletAttributesDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;


		public List<HierarchyPalletAttributes> getAllHierarchyPalletAttributesForProduct(Integer Product_Id,
				String strHierarchyIds, Integer Client_Id, Integer Org_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
			parameters.addValue("Org_Id", Org_Id);
			parameters.addValue("Client_Id", Client_Id);
			
			String sql = "-- ON PRODUCT	quantityOfTradeItemsPerPalletLayer, quantityOfLayersPerPallet, quantityOfTradeItemsPerPallet\r\n" + 
					"-- CALC	baseUnitsPerPallet, palletHeight, palletVolume, palletGrossWeight\r\n" + 
					"-- ON SHIP UNIT - palletWidth; palletDepth; ; palletNetWeight; \r\n" + 
					"-- Pallets EXAMPLE 191\r\n" + 
					"SELECT		:Product_Id AS [Product_Id]\r\n" + 
					"			,PHSS.[Product_Hierarchy_Id]\r\n" + 
					"			,PHSS.[Carton_Per_Ship_Unit]\r\n" + 
					"			,PHSS.[Carton_Layer_Per_Pallet]\r\n" + 
					"			,PHSS.[Carton_Per_Pallet_Layer]\r\n" + 
					"			,PH.[Height]\r\n" + 
					"			,PH.[Gross_Weight]\r\n" + 
					"			,PH1.[Net_Weight]\r\n" + 
					"FROM		[pm].[Product_Hierarchy] PH\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH1					ON PH1.[Product_Hierarchy_Id] = PH.[Base_Unit_Prod_Id]\r\n" + 
					"															AND PH1.[Is_Active] = 1\r\n" + 
					"JOIN		[pm].[P_H_Ship_Size] PHSS						ON	PHSS.[Product_Hierarchy_Id] = PH.[Product_Hierarchy_Id]\r\n" + 
					"															AND	PHSS.[Shipping_Unit_Id] = 1\r\n" + 
					"															AND	PHSS.[Is_Active] = 1\r\n" + 
					"JOIN		[ref].[Shipping_Unit] SU						ON	SU.[Shipping_Unit_Id] = PHSS.[Shipping_Unit_Id]\r\n" + 
					"															AND	SU.[Is_Active] = 1\r\n" + 
					"JOIN		[ref].[Reference_List] RL1						ON RL1.[Reference_List_Id] = SU.[Su_Type]\r\n" + 
					"WHERE		PH.[Product_Hierarchy_Id] IN ("+strHierarchyIds+")\r\n" + 
					"AND			PH.[Is_Active] = 1\r\n" + 
					";";

			List<HierarchyPalletAttributes> lstHierarchyPalletAttributes = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(HierarchyPalletAttributes.class));

		    return lstHierarchyPalletAttributes;
		}
	
	
}

