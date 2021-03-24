package com.ascention.validationWS.beans;


import java.util.Set;

import com.ascention.validationWS.service.ReloadDroolsRulesService;

public class Result {

	
//	String output = "PASS";
//
//	public String getOutput() {
//		return output;
//	}

	public void setOutput(String output, Integer valFailureID) {
//		this.output = output;
		ReloadDroolsRulesService.lstAllRulesFailed.add(output);
		ReloadDroolsRulesService.lstAllRulesFailedIDs.add(valFailureID);
	}
	
	public Set<String> getRulesFailed() {
		return ReloadDroolsRulesService.lstAllRulesFailed;
	}
	
	public Set<Integer> getRuleIdsFailed() {
		return ReloadDroolsRulesService.lstAllRulesFailedIDs;
	}
}
