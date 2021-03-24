package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.TradeItemLifespan;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.TradeItemLifespanDAO;

@Repository
public class TradeItemLifespanRepository implements TradeItemLifespanDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<TradeItemLifespan> getAllTradeItemLifespan(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		

			String sql = "SELECT		PC.[Min_Life_Arrival]\r\n" + 
					"			,PC.[Min_Life_Production]\r\n" + 
					"			,PFBT.[Open_Prod_Lifespan]\r\n" + 
					"FROM		[pm].[Product_Core] PC\r\n" + 
					"LEFT JOIN	[pm].[Product_Food_Beverage_Tobacco] PFBT		ON	PFBT.[Product_Id] = PC.[Product_Id]\r\n" + 
					"															AND	PFBT.[Is_Active] = 1\r\n" + 
					"WHERE		PC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PC.[Is_Active] = 1;";

			List<TradeItemLifespan> lstTradeItemLifespan = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(TradeItemLifespan.class));

		    return lstTradeItemLifespan;
		}
	
	
}

