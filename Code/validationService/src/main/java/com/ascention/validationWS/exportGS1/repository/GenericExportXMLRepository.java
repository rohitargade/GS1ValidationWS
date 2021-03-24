package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.beans.Duty;
import com.ascention.validationWS.beans.GPCAttributes;
import com.ascention.validationWS.beans.GTINCountryOrigin;
import com.ascention.validationWS.beans.OrderQty;
import com.ascention.validationWS.dao.DutyDAO;
import com.ascention.validationWS.dao.GPCAttributesDAO;
import com.ascention.validationWS.dao.GTINCountryOriginDAO;
import com.ascention.validationWS.dao.OrderQtyDAO;
import com.ascention.validationWS.exportGS1.beans.AlcoholInformationModule;
import com.ascention.validationWS.exportGS1.beans.GenericExportXML;
import com.ascention.validationWS.exportGS1.dao.AlcoholInformationModuleDAO;
import com.ascention.validationWS.exportGS1.dao.GenericExportXMLDAO;

@Repository
public class GenericExportXMLRepository implements GenericExportXMLDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	    public GenericExportXML getAllChannel_Publish_IdDetails(Integer Channel_Publish_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Channel_Publish_Id", Channel_Publish_Id);			
			
			
			String sql = "SELECT        CP.[Channel_Publish_Id]\r\n" + 
					"                     ,CP.[Is_Active]\r\n" + 
					"                     ,CP.[Create_Date]\r\n" + 
					"                     ,CP.[Last_Publish_Date]\r\n" + 
					"                     ,RL001.[Display_Name] AS [File_Type]\r\n" + 
					"                     ,RL002.[Display_Name] AS [Operation]\r\n" + 
					"                     ,CASE RL001.[Display_Name]\r\n" + 
					"                           WHEN 'CIN'    THEN '[dm].[Product_Export_Hdr]'\r\n" + 
					"                           WHEN 'CIP'    THEN '[pm].[Product_Recipient]'\r\n" + 
					"                           WHEN 'CIHW'   THEN '[pm].[Product_Recipient]'\r\n" + 
					"                           WHEN 'PSR'    THEN '[sm].[Price_Relationship]'\r\n" + 
					"                           WHEN 'PSD'    THEN '[dm].[Price_Export]'\r\n" + 
					"                           ELSE 'Unknown File Type'\r\n" + 
					"                     END AS Table_Name\r\n" + 
					"                     ,CP.[Record_Id]\r\n" + 
					"                     ,S001.[Display_Name] AS [Status_Code] \r\n" + 
					"FROM          [dm].[Channel_Publish] CP\r\n" + 
					"LEFT JOIN     [ref].[Reference_List] RL001              ON RL001.[Reference_List_Id] = CP.[File_Type]\r\n" + 
					"LEFT JOIN     [ref].[Reference_List] RL002              ON RL002.[Reference_List_Id] = CP.[Operation]\r\n" + 
					"LEFT JOIN     [ref].[Status] S001                             ON S001.[Status_Id] = CP.[Status]"
					+ "WHERE CP.[Channel_Publish_Id]=:Channel_Publish_Id";

			GenericExportXML genExport = null;
			try {
				genExport = (GenericExportXML) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(GenericExportXML.class));
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    return genExport;
		}
	
	
}

