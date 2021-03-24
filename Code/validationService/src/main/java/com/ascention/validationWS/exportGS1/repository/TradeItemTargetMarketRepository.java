package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.TradeItemTargetMarket;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.TradeItemTargetMarketDAO;

@Repository
public class TradeItemTargetMarketRepository implements TradeItemTargetMarketDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<TradeItemTargetMarket> getAllTradeItemTargetMarket(Integer Product_Export_Hdr_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();

			parameters.addValue("Product_Export_Hdr_Id", Product_Export_Hdr_Id);

			String sql = "-- Target Market\r\n" + 
					"SELECT		C.Numeric_Id\r\n" + 
					"FROM		[dm].[Product_Export_Hdr] PEH\r\n" + 
					"JOIN		[ref].[Country] C								ON	C.[Alpha_2] = PEH.[Target_Market]\r\n" + 
					"WHERE		PEH.[Product_Export_Hdr_Id] = :Product_Export_Hdr_Id";

			List<TradeItemTargetMarket> lstTradeItemTargetMarket = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(TradeItemTargetMarket.class));

		    return lstTradeItemTargetMarket;
		}
	
	
}

