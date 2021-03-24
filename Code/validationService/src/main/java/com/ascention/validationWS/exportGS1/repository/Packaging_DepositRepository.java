package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.Packaging_Deposit;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.Packaging_DepositDAO;

@Repository
public class Packaging_DepositRepository implements Packaging_DepositDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	
		public List<Packaging_Deposit> getAllPackaging_Deposit(Integer Product_Id, String strHierarchyIds) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
	
			
			String sql = "SELECT		PPD.[Package_Deposit_Amnt]\r\n" + 
					"			,RL001.[Reference_List_Name] AS [Package_Deposit_Amnt_Curr]\r\n" + 
					"			,PPD.[Package_Deposit_Id]\r\n" + 
					"			,PPD.[Package_Deposit_End_Date]\r\n" + 
					"-- BEGIN ARRAY - Deposit Region\r\n" + 
					"			,C.[Numeric_Id]\r\n" + 
					"			,CASE\r\n" + 
					"				WHEN PPDR.[Package_Deposit_Subdivision] LIKE '%-All' THEN NULL\r\n" + 
					"				ELSE PPDR.[Package_Deposit_Subdivision]\r\n" + 
					"			END AS Package_Deposit_Subdivision\r\n" + 
					"-- END ARRAY - Deposit Region\r\n" + 
					"FROM		[pm].[PA_Packaging_Deposit] PPD\r\n" + 
					"JOIN		[pm].[Product_Hierarchy] PH						ON	PH.[Product_Id] = PPD.[Product_Id]			-- ADDED 2021/02/02\r\n" + 
					"															AND	PH.[Is_Active] = 1							-- ADDED 2021/02/02\r\n" + 
					"															AND	PH.[Product_Hierarchy_Id] in ("+strHierarchyIds+") -- ADDED 2021/02/02\r\n" + 
					"															AND (	PH.[Is_Base_Unit] = 1					-- UPDATE 2021/02/02\r\n" + 
					"																OR	PH.[Is_Consumer_Unit] = 1)				-- ADDED 2021/02/03\r\n" + 
					"LEFT JOIN	[pm].[PA_Packaging_Deposit_Region] PPDR			ON	PPDR.Packaging_Deposit_Id = PPD.[Packaging_Deposit_Id]\r\n" + 
					"															AND	PPDR.[Is_Active] = 1\r\n" + 
					"JOIN		[ref].[Reference_List] RL001					ON	RL001.[Reference_List_Id] = PPD.[Package_Deposit_Amnt_Curr]\r\n" + 
					"JOIN		[ref].[Country] C								ON	C.[Alpha_2] = PPDR.[Package_Deposit_Country]\r\n" + 
					"WHERE		PPD.[Product_Id] = :Product_Id\r\n" + 
					"AND			PPD.[Is_Active] = 1;";

			List<Packaging_Deposit> lstPackaging_Deposit = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Packaging_Deposit.class));

		    return lstPackaging_Deposit;
		}
	
	
}

