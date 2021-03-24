package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.ChemicalRegulationInformationModule;
import com.ascention.validationWS.exportGS1.dao.ChemicalRegulationInformationModuleDAO;

@Repository
public class ChemicalRegulationInformationModuleRepository implements ChemicalRegulationInformationModuleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;

		
		public List<ChemicalRegulationInformationModule> getAllChemicalRegulationInformationModule(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);			

			
			String sql = "-- ************************************************************************************\r\n" + 
					"-- ChemicalRegulationInformationModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"SELECT		PA.[Chem_Regl_Agency]\r\n" + 
					"			,PA.[Chem_Regl_Name]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Chem_Phys_State]\r\n" + 
					"\r\n" + 
					"FROM		[pm].[Product_Additional] PA\r\n" + 
					"LEFT JOIN	[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PA.[Chem_Phys_State]\r\n" + 
					"WHERE		PA.[Product_Id] = :Product_Id\r\n" + 
					"AND			PA.[Is_Active] = 1";

			List<ChemicalRegulationInformationModule> lstChemicalRegulationInformationModule = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(ChemicalRegulationInformationModule.class));

		    return lstChemicalRegulationInformationModule;
		}
	
	
}

