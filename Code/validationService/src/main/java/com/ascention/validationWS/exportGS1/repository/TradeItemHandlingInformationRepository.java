package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.TradeItemHandlingInformation;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.TradeItemHandlingInformationDAO;

@Repository
public class TradeItemHandlingInformationRepository implements TradeItemHandlingInformationDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<TradeItemHandlingInformation> getAllTradeItemHandlingInformation(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
			

			String sql = "SELECT		RL001.[Reference_List_Name] AS [Handling_Instr_Ref]\r\n" + 
					"FROM		[pm].[Product_Hierarchy_Additional] PHA\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PHA.[Handling_Instr_Ref]\r\n" + 
					"WHERE		PHA.[Product_Hierarchy_Id] in ("+strHierarchyIds+")\r\n" + 
					"AND			PHA.[Is_Active] = 1";

			List<TradeItemHandlingInformation> lstTradeItemHandlingInformation = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(TradeItemHandlingInformation.class));

		    return lstTradeItemHandlingInformation;
		}
	
	
}

