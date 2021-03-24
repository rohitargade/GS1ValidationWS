package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.ReferencedFileHeader;
import com.ascention.validationWS.exportGS1.dao.ReferencedHeaderDAO;

@Repository
public class ReferencedFileHeaderRepository implements ReferencedHeaderDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
	

		public List<ReferencedFileHeader> getAllReferencedFileDetailInformationModule(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);

			
			String sql = "SELECT		RL001.[Reference_List_Name] AS [File_Type_Code]\r\n" + 
					"			,PRF.[Effective_Start_Date]	\r\n" + 
					"			,RL002.[Reference_List_Name] AS [File_Format]\r\n" + 
					"			,PRF.[Filename]\r\n" + 
					"			,PRF.[Url]\r\n" + 
					"FROM		[pm].[PA_Ref_File] PRF\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PRF.[File_Type_Code]\r\n" + 
					"\r\n" + 
					"JOIN		[ref].[Reference_List] RL002					ON	RL002.[Reference_List_Id] = PRF.[File_Format]\r\n" + 
					"WHERE		PRF.[Product_Id] = :Product_Id\r\n" + 
					"AND			PRF.[Is_Active] = 1\r\n" + 
					"";

			List<ReferencedFileHeader> lstReferencedFileDetailInformationModule = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(ReferencedFileHeader.class));

		    return lstReferencedFileDetailInformationModule;
		}
	
	
}

