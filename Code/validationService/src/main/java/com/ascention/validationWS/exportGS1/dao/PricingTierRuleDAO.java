package com.ascention.validationWS.exportGS1.dao;

import java.util.List;

import com.ascention.validationWS.exportGS1.beans.PricingTierRule;

public interface PricingTierRuleDAO {
	public List<PricingTierRule> getAllPricingTierRule(Integer Price_Export);

}
