package com.ascention.validationWS.dao;

import com.ascention.validationWS.beans.ValidationFailure;

public interface ValidationFailureDAO {
	public int deleteExistingRecords(ValidationFailure vf);
	public int insertValidationRuleFailre(ValidationFailure vf);
}
