package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.DietInformationModule;
import com.ascention.validationWS.exportGS1.beans.OrderQuantity;
import com.ascention.validationWS.exportGS1.dao.DietInformationModuleDAO;
import com.ascention.validationWS.exportGS1.dao.OrderQuantityDAO;

@Repository
public class DietInformationModuleRepository implements DietInformationModuleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;





		public List<DietInformationModule> getAllDietInformationModule(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);			
			
			String sql = "-- ************************************************************************************\r\n" + 
					"-- DietInformationModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"SELECT		PFBT.[Diet_Type_Desc]\r\n" + 
					"-- BEGIN ARRAY - Diet_Type\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Diet_Type_Code]\r\n" + 
					"			,PDT.[Cert_Agency]\r\n" + 
					"	-- BEGIN ARRAY - Diet_Cert\r\n" + 
					"			,PDC.Diet_Cert_Value\r\n" + 
					"	-- END ARRAY - Diet_Cert\r\n" + 
					"-- END ARRAY - Diet_Type\r\n" + 
					"FROM		[pm].[Product_Food_Beverage_Tobacco] PFBT\r\n" + 
					"LEFT JOIN	[pm].[PFBT_Diet_Type] PDT						ON	PDT.[Product_Id] = PFBT.[Product_Id]\r\n" + 
					"															AND	PDT.[Is_Active] = 1\r\n" + 
					"LEFT JOIN	[pm].[PFBT_Diet_Cert] PDC						ON	PDC.[Diet_Type_Id] = PDT.[Diet_Type_Id]\r\n" + 
					"															AND	PDC.[Is_Active] = 1\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PDT.[Diet_Type_Code]\r\n" + 
					"WHERE		PFBT.[Product_Id] = :Product_Id\r\n" + 
					"AND			PFBT.[Is_Active] = 1;";

			List<DietInformationModule> lstDietInformationModule = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(DietInformationModule.class));

		    return lstDietInformationModule;
		}
	
	
}

