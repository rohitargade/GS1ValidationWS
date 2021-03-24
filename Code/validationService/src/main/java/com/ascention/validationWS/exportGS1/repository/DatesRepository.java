package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.Dates;
import com.ascention.validationWS.exportGS1.dao.DatesDAO;

@Repository
public class DatesRepository implements DatesDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;



		public List<Dates> getAllDates( String strHierarchyIds, Integer Product_Export_Hdr_Id, Integer Client_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Export_Hdr_Id", Product_Export_Hdr_Id);
			parameters.addValue("Client_Id", Client_Id);

			String sql = "-- Dates - Default WHERE [Recipient_Id] is NULL ELSE CLIENT SPECIFIC\r\n" + 
					"SELECT		NULL As [Recipient_Id]\r\n" + 
					"			,PHC.[Consumer_First_Availability_Date]\r\n" + 
					"			,PHC.[End_Availability_Date]\r\n" + 
					"			,PHC.[First_Order_Date]\r\n" + 
					"			,PHC.[Start_Availability_Date]\r\n" + 
					"FROM		[pm].[Product_Hierarchy_Client] PHC\r\n" + 
					"WHERE		PHC.[Product_Hierarchy_Id] in ("+strHierarchyIds+")\r\n" + 
					"AND			PHC.[Active] = 1 AND			PHC.[Client_Id] = :Client_Id 		-- ADDED 01/02/21\r\n" + 
					"UNION\r\n" + 
					"SELECT		PR.[Recipient_Id]\r\n" + 
					"			,PR.[Consumer_First_Availability_Date]\r\n" + 
					"			,PR.[End_Availability_Date]\r\n" + 
					"			,PR.[First_Order_Date]\r\n" + 
					"			,PR.[Start_Availability_Date]\r\n" + 
					"FROM		[pm].[Product_Recipient] PR\r\n" + 
					"WHERE		PR.[Product_Export_Hdr_Id] = :Product_Export_Hdr_Id\r\n" + 
					"AND			PR.[Is_Active] = 1";

			List<Dates> lstDates = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Dates.class));

		    return lstDates;
		}
	
	
}

