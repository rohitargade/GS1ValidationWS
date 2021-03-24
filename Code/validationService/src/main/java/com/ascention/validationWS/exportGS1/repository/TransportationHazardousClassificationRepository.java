package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.TransportationHazardousClassification;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.TransportationHazardousClassificationDAO;

@Repository
public class TransportationHazardousClassificationRepository implements TransportationHazardousClassificationDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<TransportationHazardousClassification> getAllTransportationHazardousClassification(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);

			
			String sql = "-- ************************************************************************************\r\n" + 
					"-- TransportationHazardousClassificationModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"\r\n" + 
					"SELECT		RL001.[Reference_List_Name] AS [Dg_Regulation_Code]\r\n" + 
					"			,PA.[Flash_Point_Temp]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Flash_Point_Temp_Uom]\r\n" + 
					"			,HL003.[Hierarchy_List_Code] AS [Dg_Class]\r\n" + 
					"			,PA.[Dg_Hazardous_Code]\r\n" + 
					"			,RL003.[Reference_List_Name] AS [Dg_Packing_Group]\r\n" + 
					"			,PA.[Dg_Shipping_Name]\r\n" + 
					"			,PA.[Dg_Technical_Name]\r\n" + 
					"			,HL002.[Hierarchy_List_Code] AS [Un_Dg_Num]\r\n" + 
					"			,HL001.[Hierarchy_List_Code] AS [Dg_Secondary_Sub_Div]\r\n" + 
					"FROM		[pm].[Product_Additional] PA\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PA.[Dg_Regulation_Code]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PA.[Flash_Point_Temp_Uom]\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL003					ON	RL003.[Reference_List_Id] = PA.[Dg_Packing_Group]\r\n" + 
					"LEFT JOIN	[ref].[Hierarchy_List] HL001					ON	HL001.[Hierarchy_List_Id] = PA.[Dg_Secondary_Sub_Div]\r\n" + 
					"LEFT JOIN	[ref].[Hierarchy_List] HL002					ON	HL002.[Hierarchy_List_Id] = PA.[Un_Dg_Num]\r\n" + 
					"LEFT JOIN	[ref].[Hierarchy_List] HL003					ON	HL003.[Hierarchy_List_Id] = PA.[Dg_Subsidiary_Class]\r\n" + 
					"WHERE		PA.[Product_Id] = :Product_Id \r\n" + 
					"AND			PA.[Is_Active] = 1;\r\n" + 
					"";

			List<TransportationHazardousClassification> lstTransportationHazardousClassification = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(TransportationHazardousClassification.class));

		    return lstTransportationHazardousClassification;
		}
	
	
}

