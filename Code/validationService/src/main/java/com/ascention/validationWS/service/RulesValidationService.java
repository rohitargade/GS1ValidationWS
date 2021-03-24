package com.ascention.validationWS.service;

import java.io.PrintStream;

import org.drools.core.base.RuleNameEndsWithAgendaFilter;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ascention.validationWS.beans.PricingCore;
import com.ascention.validationWS.beans.Product;
import com.ascention.validationWS.beans.ProductAdditional;
import com.ascention.validationWS.beans.ProductAdditionalArray;
import com.ascention.validationWS.beans.ProductCore;
import com.ascention.validationWS.beans.ProductExport;
import com.ascention.validationWS.beans.ProductHierarchy;
import com.ascention.validationWS.beans.Result;

@Service
public class RulesValidationService {

	private final KieContainer kieContainer;
	PrintStream out;

	@Autowired
	public RulesValidationService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}

	public Product getProductDiscount(Product product) {
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		kieSession.insert(product);

		kieSession.fireAllRules();
		kieSession.dispose();
		return product;
	}

	public Result validateProductAdditional(ProductCore product, Result result, KieSession kieSession, String ruleId) {

		kieSession.insert(product);

		kieSession.insert(result);

		int noOfRulesFired = kieSession.fireAllRules(new RuleNameEqualsAgendaFilter(ruleId));
		System.out
				.println(noOfRulesFired + "RulesFired:" + ruleId + " \n " + ReloadDroolsRulesService.lstAllRulesFailed);

		kieSession.dispose();

		return result;
	}

	
	public Result validatePrice(PricingCore price, Result result, KieSession kieSession, String ruleId) {

		kieSession.insert(price);

		kieSession.insert(result);

		int noOfRulesFired = kieSession.fireAllRules(new RuleNameEqualsAgendaFilter(ruleId));
		System.out
				.println(noOfRulesFired + "RulesFired:" + ruleId + " \n " + ReloadDroolsRulesService.lstAllRulesFailed);

		kieSession.dispose();

		return result;
	}
	
	
	
	public Result validateProductHierarchy(ProductExport product, Result result, KieSession kieSession, String ruleId) {

		kieSession.insert(product);

		kieSession.insert(result);

		int noOfRulesFired = kieSession.fireAllRules(new RuleNameEqualsAgendaFilter(ruleId));
//		System.out
//				.println(noOfRulesFired + "RulesFired:" + ruleId + " \n " + ReloadDroolsRulesService.lstAllRulesFailed);

		kieSession.dispose();

		return result;
	}
	
	public Result validateProductAdditionalObjectArray(ProductAdditionalArray productAdditionalObjectArray,
			Result result, KieSession kieSession) {

		// List<ProductDetails> lstDetails = productDetailsDAO.findAll();
		// System.out.println(lstDetails.toArray().toString());

		kieSession.insert(productAdditionalObjectArray);
		kieSession.insert(result);

		int noOfRulesFired = kieSession.fireAllRules(50);
		kieSession.dispose();

		System.out.println("noOfRulesFired:" + noOfRulesFired + " \n " + result.getRulesFailed());

		return result;
	}

//	public Result validateProductHierarchy(ProductHierarchy productHierarchy, Result result, ProductCore productCore) {
//		KieSession kieSession = kieContainer.newKieSession("rulesSessionHierarchy");
//		// kieSession.getAgenda().getAgendaGroup( "product_hierarchy_object"
//		// ).setFocus();
//		kieSession.insert(productCore);
//		kieSession.insert(productHierarchy);
//		kieSession.insert(result);
//
//		kieSession.fireAllRules();
//		kieSession.dispose();
//		return result;
//	}

}