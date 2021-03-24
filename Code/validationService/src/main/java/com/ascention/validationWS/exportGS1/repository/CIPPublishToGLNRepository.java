package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.CIPPublishToGLN;
import com.ascention.validationWS.exportGS1.dao.CIPPublishToGLNDAO;

@Repository
public class CIPPublishToGLNRepository implements CIPPublishToGLNDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	


		public List<CIPPublishToGLN> getAllCIPPublishToGLN(Integer Product_Export_Hdr_Id, Integer Product_Recipient_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Export_Hdr_Id", Product_Export_Hdr_Id);
			parameters.addValue("Product_Recipient_Id", Product_Recipient_Id);
			
			
			String sql = "SELECT		O.[Organisation_GLN] AS [publishToGLN]\r\n" + 
					"FROM		[pm].[Product_Recipient] PR\r\n" + 
					"\r\n" + 
					"JOIN		[cm].[Client_Organisation] CO					ON	CO.[Client_Organisation_Id] = PR.[Recipient_Id]\r\n" + 
					"															AND CO.[Is_Active] = 1\r\n" + 
					"JOIN		[cm].[Organisation] O							ON	O.[Organisation_Id] = CO.[Organisation_Id] \r\n" + 
					"															AND O.[Organisation_GLN] IS NOT NULL\r\n" + 
					"WHERE		PR.[Product_Export_Hdr_Id] = :Product_Export_Hdr_Id \r\n"
					+ "AND			PR.[Product_Recipient_Id] =	:Product_Recipient_Id ; ";

			List<CIPPublishToGLN> lstCIPPublishToGLN = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(CIPPublishToGLN.class));

		    return lstCIPPublishToGLN;
		}
	
	
}

