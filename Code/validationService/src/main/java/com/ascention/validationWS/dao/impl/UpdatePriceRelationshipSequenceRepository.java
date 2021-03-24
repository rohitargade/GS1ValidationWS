package com.ascention.validationWS.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ascention.validationWS.beans.UpdatePriceRelationshipSequence;
import com.ascention.validationWS.dao.UpdatePriceRelationshipSequenceDAO;

@Repository
public class UpdatePriceRelationshipSequenceRepository implements UpdatePriceRelationshipSequenceDAO  {

	    @Autowired 
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 


		@Override
		public int updatePriceRelationshipSequence(UpdatePriceRelationshipSequence updateSeq) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("Price_Relationship_Id", updateSeq.getPrice_Relationship_Id());
		
			String sql = "UPDATE [sm].[Price_Relationship]  SET [Relationship_Seq_Id] = [Relationship_Seq_Id] + 1 WHERE [Price_Relationship_Id] = :Price_Relationship_Id";

			int noofRowsAffected = jdbcTemplate.update(sql, parameters);

		    return noofRowsAffected;
		}
	
	
}

