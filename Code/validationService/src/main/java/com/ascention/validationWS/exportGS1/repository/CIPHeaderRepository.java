package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.CIPHeader;
import com.ascention.validationWS.exportGS1.dao.CIPHeaderDAO;

@Repository
public class CIPHeaderRepository implements CIPHeaderDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	

		public List<CIPHeader> getAllCIPHeader(Integer Product_Export_Hdr_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Export_Hdr_Id", Product_Export_Hdr_Id);
			
			
			String sql = "SELECT		O.[Organisation_GLN] AS [Sender_Content_Owner]\r\n" + 
					"			,C.[Recipient_Ref_Id] AS [Receiver_sourceDataPool]\r\n" + 
					"			,CN.Numeric_Id AS [Target_Market]\r\n" + 
					"FROM		[dm].[Product_Export_Hdr] PEH\r\n" + 
					"JOIN		[ref].[Country] CN								ON	CN.[Alpha_2] = PEH.[Target_Market]\r\n" + 
					"JOIN		[cm].[Client_Organisation] CO					ON	CO.[Client_Organisation_Id] = PEH.[Client_Id]\r\n" + 
					"															AND CO.[Is_Active] = 1\r\n" + 
					"JOIN		[cm].[Organisation] O							ON	O.[Organisation_Id] = CO.[Organisation_Id] \r\n" + 
					"															AND O.[Organisation_GLN] IS NOT NULL\r\n" + 
					"JOIN		[dm].[Client_Target_Channel] CTC				ON	CTC.[Client_Target_Channel_Id] = PEH.[Target_Channel_Id]\r\n" + 
					"															AND	CTC.[Is_Active] = 1\r\n" + 
					"															AND CTC.[Channel_Id] = 2\r\n" + 
					"JOIN		[dm].[Connection] C								ON	C.[Connection_Id] = CTC.[Connection_Id]\r\n" + 
					"															AND	C.[Is_Active] = 1\r\n" + 
					"WHERE		PEH.[Product_Export_Hdr_Id] =  :Product_Export_Hdr_Id\r\n" + 
					"AND			PEH.[Is_Active] = 1;";

			List<CIPHeader> lstCIPHeader = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(CIPHeader.class));

		    return lstCIPHeader;
		}
	
	
}

