package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.TradeItemVariant_Desc;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.TradeItemVariant_DescDAO;

@Repository
public class TradeItemVariant_DescRepository implements TradeItemVariant_DescDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<TradeItemVariant_Desc> getAllTradeItemVariant_Desc(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
	
			
			String sql = "SELECT		PVD.[Variant_Desc]	\r\n" + 
					"FROM		[pm].[P_Variant_Desc] PVD\r\n" + 
					"WHERE		PVD.[Product_Id] = :Product_Id\r\n" + 
					"AND			PVD.[Is_Active] = 1;";

			List<TradeItemVariant_Desc> lstTradeItemVariant_Desc = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(TradeItemVariant_Desc.class));

		    return lstTradeItemVariant_Desc;
		}
	
	
}

