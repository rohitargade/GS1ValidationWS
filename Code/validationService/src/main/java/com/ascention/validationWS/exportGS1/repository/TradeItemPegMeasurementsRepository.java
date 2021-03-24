package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.TradeItemPegMeasurements;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.TradeItemPegMeasurementsDAO;

@Repository
public class TradeItemPegMeasurementsRepository implements TradeItemPegMeasurementsDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<TradeItemPegMeasurements> getAllTradeItemPegMeasurements(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			String sql = "SELECT		PP.[Peg_Hole_Num]\r\n" + 
					"			,PP.[Peg_Horizontal]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Peg_Horizontal_Uom]\r\n" + 
					"			,PP.[Peg_Vertical]\r\n" + 
					"			,RL002.[Reference_List_Name] AS [Peg_Vertical_Uom]\r\n" + 
					"FROM		[pm].[PA_Peg] PP\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PP.[Peg_Horizontal_Uom]\r\n" + 
					"JOIN		[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PP.[Peg_Vertical_Uom]\r\n" + 
					"WHERE		PP.[Product_Id] = :Product_Id\r\n" + 
					"AND			PP.[Is_Active] = 1;";

			List<TradeItemPegMeasurements> lstTradeItemPegMeasurements = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(TradeItemPegMeasurements.class));

		    return lstTradeItemPegMeasurements;
		}
	
	
}

