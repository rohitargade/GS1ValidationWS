package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.PharmaceuticalItemInformationModule;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.PharmaceuticalItemInformationModuleDAO;

@Repository
public class PharmaceuticalItemInformationModuleRepository implements PharmaceuticalItemInformationModuleDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<PharmaceuticalItemInformationModule> getAllPharmaceuticalItemInformationModule(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
		
			
			String sql = "-- ************************************************************************************\r\n" + 
					"-- PharmaceuticalItemInformationModule.xsd\r\n" + 
					"-- ************************************************************************************\r\n" + 
					"\r\n" + 
					"SELECT		PHC.[Dosage_Recommendation]\r\n" + 
					"			,PHC.[Drug_Interactions]\r\n" + 
					"FROM		[pm].[Product_Core] PC\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH						ON	PH.[Product_Id] = PC.[Product_Id]\r\n" + 
					"															AND	PH.[Is_Active] = 1\r\n" + 
					"															AND	PH.[Product_Hierarchy_Id] in ("+strHierarchyIds+")\r\n" + 
					"															AND PH.[Is_Base_Unit] = 1\r\n" + 
					"JOIN		[pm].[Product_Healthcare] PHC					ON	PHC.[Product_Id] = PC.[Product_Id]\r\n" + 
					"															AND	PHC.[Is_Active] = 1\r\n" + 
					"WHERE		PC.[Product_Id] = :Product_Id\r\n" + 
					"AND			PC.[Is_Active] = 1";

			List<PharmaceuticalItemInformationModule> lstPharmaceuticalItemInformationModule = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(PharmaceuticalItemInformationModule.class));

		    return lstPharmaceuticalItemInformationModule;
		}
	
	
}

