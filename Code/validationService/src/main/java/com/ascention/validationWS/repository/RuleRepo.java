package com.ascention.validationWS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ascention.validationWS.beans.Rule;

public interface RuleRepo extends JpaRepository<Rule, Long> {
    Rule findByRuleKey(String ruleKey);
}
