package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PricingPSDSuperseded;
import com.ascention.validationWS.exportGS1.dao.PricingPSDSupersededDAO;

@Repository
public class PricingPSDSupersededRepository implements PricingPSDSupersededDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	

		public List<PricingPSDSuperseded> getAllPricingPSDSuperseded(Integer Price_Export) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Price_Export", Price_Export);
			
			
			String sql = "SELECT	PH.[Superseded_Price_Hdr_Id]\r\n" + 
					"			,PE.[Recipient_Id]\r\n" + 
					"			,PE.[Product_Export_Dtl_Id]\r\n" + 
					"			,PE.[Price_Relationship_Id]\r\n" + 
					"FROM		[dm].[Price_Export] PE\r\n" + 
					"JOIN		[sm].[Price_Hdr] PH		ON	PH.[Price_Hdr_Id] = PE.[Price_Hdr_Id]\r\n" + 
					"JOIN		[sm].[Price_Hdr] PH1	ON	PH1.[Price_Hdr_Id] = PH.[Superseded_Price_Hdr_Id]\r\n" + 
					"									AND	PH1.[Effective_End_Date] > GETDATE()\r\n" + 
					"WHERE		PE.[Price_Export_Id] = :Price_Export\r\n" + 
					"AND			PE.[Is_Active] = 1;" ;

			List<PricingPSDSuperseded> lstPricingPSDSuperseded = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(PricingPSDSuperseded.class));

		    return lstPricingPSDSuperseded;
		}
	
	
}

