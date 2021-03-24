package com.ascention.validationWS.exportGS1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.exportGS1.beans.PA_Feature_Benefit;
import com.ascention.validationWS.exportGS1.beans.AVPHsnoApproval;
import com.ascention.validationWS.exportGS1.dao.PA_Feature_BenefitDAO;
import com.ascention.validationWS.exportGS1.dao.AVPHsnoApprovalDAO;

@Repository
public class AVPHsnoApprovalRepository implements AVPHsnoApprovalDAO {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	

		public List<AVPHsnoApproval> getAllPackaging(Integer Product_Id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Product_Id", Product_Id);
	
			
			String sql = "SELECT		PHA.[Hsno_Approval_Num]\r\n" + 
					"FROM		[pm].[PA_Hsno_Approval] PHA\r\n" + 
					"WHERE		PHA.[Product_Id] = :Product_Id\r\n" + 
					"AND			PHA.[Is_Active] = 1;";

			List<AVPHsnoApproval> lstAVPHsnoApproval = jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(AVPHsnoApproval.class));

		    return lstAVPHsnoApproval;
		}
	
	
}

