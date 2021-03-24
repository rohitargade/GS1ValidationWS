package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.WarrantyInformation;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.WarrantyInformationDAO;

@Repository
public class WarrantyInformationRepository implements WarrantyInformationDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<WarrantyInformation> getAllWarrantyInformation(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			String sql = "-- ************************************************************************************\r\n" + 
					"-- WarrantyInformationModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"\r\n" + 
					"SELECT		PWI.[Warranty_Desc]\r\n" + 
					"			,PWD.[Warranty_Duration]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Warranty_Duration_Uom]\r\n" + 
					"FROM		[pm].[PA_Warranty_Info] PWI\r\n" + 
					"LEFT JOIN	[pm].[PA_Warranty_Dtl] PWD						ON	PWD.[Warranty_Info_Id] = PWI.[Warranty_Info_Id]\r\n" + 
					"															AND	PWD.[Is_Active] = 1\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PWD.[Warranty_Duration_Uom]\r\n" + 
					"WHERE		PWI.[Product_Id] = :Product_Id\r\n" + 
					"AND			PWI.[Is_Active] = 1;";

			List<WarrantyInformation> lstWarrantyInformation = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(WarrantyInformation.class));

		    return lstWarrantyInformation;
		}
	
	
}

