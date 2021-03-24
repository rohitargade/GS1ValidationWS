package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PricingPSDShipTo;
import com.ascention.validationWS.exportGS1.dao.PricingPSDShipToDAO;

@Repository
public class PricingPSDShipToRepository implements PricingPSDShipToDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	

		public List<PricingPSDShipTo> getAllPricingPSDShipTo( Integer Price_Export) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Price_Export", Price_Export);
			
			
			String sql = "-- NEW SHIP TO LOGIC\r\n" + 
					"SELECT OA.[Organisation_Address_GLN]\r\n" + 
					"FROM   [dm].[Price_Export] PE\r\n" + 
					"JOIN   [sm].[Price_Hdr] PH               ON       PH.[Price_Hdr_Id] = PE.[Price_Hdr_Id]\r\n" + 
					"                                         AND PH.[Is_Active] = 1\r\n" + 
					"JOIN   [cm].[Client_Organisation] CO     ON       CO.[Client_Organisation_Id] = PE.[Recipient_Id]          \r\n" + 
					"                                         AND CO.[Is_Active] = 1                                                                \r\n" + 
					"JOIN   [sm].[PR_Price_ShipTo] PS         ON       PS.[Price_Hdr_Id] = PH.[Price_Hdr_Id]\r\n" + 
					"                                         AND PS.[Is_Active] = 1\r\n" + 
					"                                         AND       PS.[Ship_To_Org] = CO.[Organisation_Id]\r\n" + 
					"JOIN   [cm].[Organisation_Address] OA    ON       OA.[Organisation_Address_Id] = PS.[Ship_To]\r\n" + 
					"                                         AND OA.[Is_Active] = 1\r\n" + 
					"                                         AND       OA.[Organisation_Address_GLN] IS NOT NULL\r\n" + 
					"WHERE  PE.[Price_Export_Id] = :Price_Export \r\n" + 
					"AND    PE.[Is_Active] = 1;\r\n" + 
					"";

			List<PricingPSDShipTo> lstPricingPSDShipTo = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(PricingPSDShipTo.class));

		    return lstPricingPSDShipTo;
		}
	
	
}

