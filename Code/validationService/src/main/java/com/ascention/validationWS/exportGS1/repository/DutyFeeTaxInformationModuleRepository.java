package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.DutyFeeTaxInformationModule;
import com.ascention.validationWS.exportGS1.beans.OrderQuantity;
import com.ascention.validationWS.exportGS1.dao.DutyFeeTaxInformationModuleDAO;
import com.ascention.validationWS.exportGS1.dao.OrderQuantityDAO;

@Repository
public class DutyFeeTaxInformationModuleRepository implements DutyFeeTaxInformationModuleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;



		public List<DutyFeeTaxInformationModule> getAllDutyFeeTaxInformationModule(Integer Product_Id, String Target_Market) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);			
			parameters.addValue("Target_Market", Target_Market);
			
			String sql = "\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"-- DutyFeeTaxInformationModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"SELECT		RL002.[Reference_List_Name] AS [Tax_Agency_Code]		\r\n" + 
					"			,CASE\r\n" + 
					"				WHEN T.Tax_Type = 'GST' THEN 'GST'\r\n" + 
					"				WHEN T.Tax_Type = 'Excise' AND T.[Tax_Type_Desc] = 'Wine Equalisation Tax' THEN 'WET'\r\n" + 
					"				ELSE NULL\r\n" + 
					"			END as Tax_Type\r\n" + 
					"			,T.[Tax_Type_Desc]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Exempt_Party_Code]\r\n" + 
					"			,TR.[Tax_Rate]\r\n" + 
					"FROM		[pm].[Product_Hierarchy] PH1\r\n" + 
					"JOIN		[pm].[P_H_Duty] PHD								ON	PHD.[Product_Hierarchy_Id] = PH1.[Product_Hierarchy_Id]\r\n" + 
					"															AND PHD.[Is_Active] = 1\r\n" + 
					"JOIN		[fin].[Tax] T									ON	T.[Tax_Id] = PHD.Tax_Id\r\n" + 
					"															AND T.[Is_Active] = 1				\r\n" + 
					"															AND	T.[Tax_Type_Desc] IN ('Goods and Services Tax','Wine Equalisation Tax')\r\n" + 
					"															AND T.[Country] = :Target_Market\r\n" + 
					"JOIN		[fin].[Tax_Rate] TR								ON	TR.[Tax_Id] = T.Tax_Id\r\n" + 
					"															AND	TR.Is_Active = 1\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PHD.[Exempt_Party_Code]\r\n" + 
					"JOIN		[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = T.[Tax_Agency_Code]\r\n" + 
					"WHERE		PH1.[Product_Id] = :Product_Id\r\n" + 
					"AND			PH1.[Is_Base_Unit] = 1\r\n" + 
					"AND			PH1.[Is_Active] = 1";

			List<DutyFeeTaxInformationModule> lstDutyFeeTaxInformationModule = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(DutyFeeTaxInformationModule.class));

		    return lstDutyFeeTaxInformationModule;
		}
	
	
}

