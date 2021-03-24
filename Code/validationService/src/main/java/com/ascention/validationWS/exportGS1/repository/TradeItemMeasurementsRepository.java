package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.TradeItemMeasurements;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.TradeItemMeasurementsDAO;

@Repository
public class TradeItemMeasurementsRepository implements TradeItemMeasurementsDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<TradeItemMeasurements> getAllTradeItemMeasurements(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);

			String sql = "SELECT		PH.[Depth]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Depth_Uom]\r\n" + 
					"			,PH.[Height]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Height_Uom]\r\n" + 
					"			,PH.[Width]\r\n" + 
					"			,RL003.[Reference_List_Name] AS [Width_Uom]\r\n" + 
					"			,PH.[Net_Weight]\r\n" + 
					"			,RL004.[Reference_List_Name] AS [Net_Weight_Uom]\r\n" + 
					"			,PH.[Gross_Weight]\r\n" + 
					"			,RL005.[Reference_List_Name] AS [Gross_Weight_Uom]\r\n" + 
					"			,PH.[Drained_Weight]\r\n" + 
					"			,RL006.[Reference_List_Name] AS [Drained_Weight_Uom]\r\n" + 
					"			,PH.[Net_Volume]\r\n" + 
					"			,RL007.[Reference_List_Name] AS [In_Box_Cube_Dim_Uom] \r\n" + 
					"			,PH.[Net_Base_Volume]\r\n" + 
					"			,RL008.[Reference_List_Name] AS [Front_Face_Type]\r\n" + 
					"FROM		[pm].[Product_Core] PC\r\n" + 
					"LEFT JOIN	[pm].[Product_Additional] PA					ON	PA.Product_Id = PC.Product_Id\r\n" + 
					"															AND	PA.Is_Active = 1\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH						ON	PH.Product_Id = PC.Product_Id\r\n" + 
					"															AND	PH.Is_Active = 1\r\n" + 
					"LEFT JOIN	[pm].[Product_Hierarchy_Additional] PHA			ON	PHA.Product_Hierarchy_Id = PH.Product_Hierarchy_Id\r\n" + 
					"															AND	PHA.Is_Active = 1\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PH.[Depth_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PH.[Height_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL003					ON	RL003.[Reference_List_Id] = PH.[Width_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL004					ON	RL004.[Reference_List_Id] = PH.[Net_Weight_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL005					ON	RL005.[Reference_List_Id] = PH.[Gross_Weight_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL006					ON	RL006.[Reference_List_Id] = PH.[Drained_Weight_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL007					ON	RL007.[Reference_List_Id] = PH.[In_Box_Cube_Dim_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL008					ON	RL008.[Reference_List_Id] = PHA.[Front_Face_Type]\r\n" + 
					"WHERE		PC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PC.[Is_Active] = 1\r\n" + 
					"AND			PH.[Product_Hierarchy_Id] in ("+strHierarchyIds+");\r\n" + 
					"";

			List<TradeItemMeasurements> lstTradeItemMeasurements = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(TradeItemMeasurements.class));

		    return lstTradeItemMeasurements;
		}
	
	
}

