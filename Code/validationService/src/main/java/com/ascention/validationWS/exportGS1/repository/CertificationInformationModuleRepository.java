package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.CertificationInformationModule;
import com.ascention.validationWS.exportGS1.dao.CertificationInformationModuleDAO;

@Repository
public class CertificationInformationModuleRepository implements CertificationInformationModuleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;

		public List<CertificationInformationModule> getAllCertificationInformationModule(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);			
		
			
			String sql = "-- ************************************************************************************\r\n" + 
					"-- CertificationInformationModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"SELECT		PC.[Certification_Agency]\r\n" + 
					"			,PC.[Certification_Std]\r\n" + 
					"			,PC.[Certification_Value]\r\n" + 
					"FROM		[pm].[PA_Certification] PC\r\n" + 
					"WHERE		PC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PC.[Is_Active] = 1";

			List<CertificationInformationModule> lstCertificationInformationModule = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(CertificationInformationModule.class));

		    return lstCertificationInformationModule;
		}
	
	
}

