package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PricingPSDSupersededPriceExport;
import com.ascention.validationWS.exportGS1.dao.PricingPSDSupersededPriceExportDAO;

@Repository
public class PricingPSDSupersededPriceExportRepository implements PricingPSDSupersededPriceExportDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;

		@Override
		public List<PricingPSDSupersededPriceExport> getAllPricingPSDSupersededPriceExport(Integer PRICE_SUPERCEDED_ID,
				Integer PRODUCT_EXPORT_DTL_ID, Integer PRICE_RECIPIENT_ID) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("PRICE_SUPERCEDED_ID", PRICE_SUPERCEDED_ID);
			parameters.addValue("PRODUCT_EXPORT_DTL_ID", PRODUCT_EXPORT_DTL_ID);
			parameters.addValue("PRICE_RECIPIENT_ID", PRICE_RECIPIENT_ID);
			
			
			String sql = "SELECT        PE.Price_Export_Id AS Price_Export_Id\r\n" + 
					"FROM          [dm].[Price_Export] PE\r\n" + 
					"WHERE         PE.[Price_Hdr_Id] = :PRICE_SUPERCEDED_ID\r\n" + 
					"AND           PE.[Product_Export_Dtl_Id] = :PRODUCT_EXPORT_DTL_ID\r\n" + 
					"AND           PE.[Recipient_Id] = :PRICE_RECIPIENT_ID;" ;

			List<PricingPSDSupersededPriceExport> lstPricingPSDSupersededPriceExport = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(PricingPSDSupersededPriceExport.class));
			return lstPricingPSDSupersededPriceExport;
		}
	
	
}

