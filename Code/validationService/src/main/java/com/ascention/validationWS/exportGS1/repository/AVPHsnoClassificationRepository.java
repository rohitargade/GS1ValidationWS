package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.AVPHsnoClassification;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.AVPHsnoClassificationDAO;

@Repository
public class AVPHsnoClassificationRepository implements AVPHsnoClassificationDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	

		public List<AVPHsnoClassification> getAllPackaging(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);

			
			String sql = "SELECT		PHC.[Hsno_Classification]\r\n" + 
					"FROM		[pm].[PA_Hsno_Classification] PHC\r\n" + 
					"WHERE		PHC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PHC.[Is_Active] = 1;";

			List<AVPHsnoClassification> lstAVPHsnoClassification = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(AVPHsnoClassification.class));

		    return lstAVPHsnoClassification;
		}
	
	
}

