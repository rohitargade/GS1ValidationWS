package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.TradeItemInformationProviderDetails;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.TradeItemInformationProviderDetailsDAO;

@Repository
public class TradeItemInformationProviderDetailsRepository implements TradeItemInformationProviderDetailsDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<TradeItemInformationProviderDetails> getAllTradeItemInformationProviderDetails( Integer Product_Export_Hdr_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
		
			parameters.addValue("Product_Export_Hdr_Id", Product_Export_Hdr_Id);
			
			String sql = "SELECT		O.[Organisation_GLN] AS [Info_Provider_GLN]\r\n" + 
					"			,O.[Organisation_LegalName] AS [Info_Provider_Name]\r\n" + 
					"			,CONCAT(	OA.[Organisation_Address_Unit],' '\r\n" + 
					"						,OA.[Organisation_Address_Number],' '\r\n" + 
					"						,OA.[Organisation_Address_StreetName],' '\r\n" + 
					"						,OA.[Organisation_Address_Suburb],' '\r\n" + 
					"						,OA.[Organisation_Address_County],' '\r\n" + 
					"						,CS.[Display_Name] ,' '\r\n" + 
					"						,OA.[Organisation_Address_PostCode],' '\r\n" + 
					"						,C.[Display_Name] ) AS [Info_Provider_Address]\r\n" + 
					"FROM		[dm].[Product_Export_Hdr] PEH\r\n" + 
					"JOIN		[cm].[Client_Organisation] CO					ON	CO.[Client_Organisation_Id] = PEH.[Client_Id]\r\n" + 
					"															AND CO.[Is_Active] = 1\r\n" + 
					"JOIN		[cm].[Organisation] O							ON	O.[Organisation_Id] = CO.[Organisation_Id] \r\n" + 
					"															AND O.[Organisation_GLN] IS NOT NULL\r\n" + 
					"LEFT JOIN	[cm].[Organisation_Address]	OA					ON	OA.[Organisation_Id] = CO.[Organisation_Id] \r\n" + 
					"															AND OA.[Is_Active] = 1\r\n" + 
					"															AND	OA.[Organisation_Address_IsPrimary] = 1\r\n" + 
					"LEFT JOIN	[ref].[Country_Subdivision] CS					ON	CS.Subdivision_Code = OA.[Organisation_Address_State]\r\n" + 
					"LEFT JOIN	[ref].[Country] C								ON	C.[Alpha_2] = OA.[Organisation_Address_Country]\r\n" + 
					"WHERE		PEH.[Product_Export_Hdr_Id] = :Product_Export_Hdr_Id";

			List<TradeItemInformationProviderDetails> lstTradeItemInformationProviderDetails = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(TradeItemInformationProviderDetails.class));

		    return lstTradeItemInformationProviderDetails;
		}
	
	
}

