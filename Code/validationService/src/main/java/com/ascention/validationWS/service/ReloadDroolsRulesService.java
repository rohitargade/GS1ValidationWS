package com.ascention.validationWS.service;

import org.kie.api.runtime.KieContainer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.IOUtils;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import com.ascention.validationWS.beans.PriceDetails;
import com.ascention.validationWS.beans.ProductCore;
import com.ascention.validationWS.beans.ProductDetails;
import com.ascention.validationWS.beans.Rule;
import com.ascention.validationWS.beans.ValidationRule;
import com.ascention.validationWS.dao.PriceDetailsDAO;
import com.ascention.validationWS.dao.ProductDetailsDAO;
import com.ascention.validationWS.dao.ValidationRuleDAO;
import com.ascention.validationWS.repository.RuleRepo;

@Service
public class ReloadDroolsRulesService {
	public static KieContainer kieContainer;
	@Autowired
	private ProductDetailsDAO productDetailsDAO;
	
	@Autowired
	private PriceDetailsDAO priceDetailsDAO;
	
	public static Set<String> lstAllRulesFailed;
	public static Set<Integer> lstAllRulesFailedIDs;
	public static List<ProductCore> lstAllProducts;
	public static String strRuleIdsToExecute = "";
	@Autowired
	private ValidationRuleDAO validationRuleDAO;
	
    @Autowired
    private RuleRepo ruleRepository;

    public  void reload(Integer product_id, Integer client_id, Integer Validation_Domain_Id, Integer Hierarchy_Rule){
        //KieContainer kieContainerLocal = null;
    	
		try {
			lstAllRulesFailed = new TreeSet<String>();
			lstAllRulesFailedIDs = new TreeSet<Integer>();
			strRuleIdsToExecute = "";
			this.kieContainer=reloadKieContainer(product_id, client_id, Validation_Domain_Id, Hierarchy_Rule);
		} catch (IOException e) {
			
			e.printStackTrace();
		}//loadContainerFromString(loadRules());
		
        
    }

    private List<Rule>  loadRules(){
        List<Rule> rules=ruleRepository.findAll();
//        System.out.println(rules.toString());
        return rules;
    }

    private  KieContainer loadContainerFromString(List<Rule> rules) {
        long startTime = System.currentTimeMillis();
        KieServices ks = KieServices.Factory.get();
        KieRepository kr = ks.getRepository();
        KieFileSystem kfs = ks.newKieFileSystem();

        for (Rule rule:rules) {
            String  drl=rule.getContent();
            kfs.write("src/main/resources/" + drl.hashCode() + ".drl", drl);
        }

        KieBuilder kb = ks.newKieBuilder(kfs);

        kb.buildAll();
        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:\n" + kb.getResults().toString());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time to build rules : " + (endTime - startTime)  + " ms" );
        startTime = System.currentTimeMillis();
        KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());
        endTime = System.currentTimeMillis();
        System.out.println("Time to load container: " + (endTime - startTime)  + " ms" );
        return kContainer;
    }
    
    public KieContainer reloadKieContainer(Integer product_id, Integer client_id, Integer Validation_Domain_Id, Integer Hierarchy_Rule) throws IOException {
		KieServices ks = KieServices.Factory.get();
		final KieRepository kr = ks.getRepository();
		kr.addKieModule(new KieModule() {
			public ReleaseId getReleaseId() {
				return kr.getDefaultReleaseId();
			}
		});
		KieFileSystem kfs = ks.newKieFileSystem();	
		Resource[] files = listRules();		
		kfs.write("src/main/resources/"+"sd.drl",  getRules(product_id, client_id, Validation_Domain_Id, Hierarchy_Rule));
		KieBuilder kb = ks.newKieBuilder(kfs);
		kb.buildAll(); // kieModule is automatically deployed to KieRepository if successfully built.
		
		Results results = kb.getResults();

		if (results.hasMessages(Message.Level.ERROR)) {
			StringBuffer buf=new StringBuffer();
			for (Message message : results.getMessages(Message.Level.ERROR)) {
				buf.append("ERROR: "+message.toString().trim()+"/r/n");
			}
		}

		this.kieContainer = ks.newKieContainer(kr.getDefaultReleaseId());

		return kieContainer;
	}
    
    private String getRules(Integer product_id, Integer client_id, Integer Validation_Domain_Id, Integer Hierarchy_Rule) {
    	String strAllRulesToFire = "package com.ascention.validationWS.rules \n" + 
    			" \n" + 
    			"import com.ascention.validationWS.beans.*; import java.util.List;" + " \n" +
    			"import com.ascention.validationWS.exportGS1.beans.*; " + " \n" ; 
    	
    	
    	if(Validation_Domain_Id==15569) {
    		PriceDetails priceDetails =  priceDetailsDAO.getPriceGroupSectorValuesById(product_id);
    		System.out.println(priceDetails);		
    				
    		List<ValidationRule> lstRules =  validationRuleDAO.findAllPricingRules(priceDetails.getProd_Group(), priceDetails.getSector(), client_id);
    		lstRules =  Collections.synchronizedList(lstRules);
    		
    		for (ValidationRule v: lstRules) {
    			strRuleIdsToExecute += v.getInternal_Rule_Id() + ",";
    			strAllRulesToFire += v.getValidation_code() + "\n";
    		}
    		
    		//System.out.println(strAllRulesToFire);		
    				
    				
    	}else{
    		ProductDetails productGrpdetails = productDetailsDAO.getProductGroupSectorValuesById(product_id);		
    		System.out.println(productGrpdetails.toString());		
    		
    		
    		List<ValidationRule> lstRules =  validationRuleDAO.findAll(productGrpdetails.getProd_Group(), productGrpdetails.getSector(), client_id, Validation_Domain_Id, Hierarchy_Rule);
    		lstRules =  Collections.synchronizedList(lstRules);
    		
    		for (ValidationRule v: lstRules) {
    			strRuleIdsToExecute += v.getInternal_Rule_Id() + ",";
    			strAllRulesToFire += v.getValidation_code() + "\n";
    		}
    		
    		//System.out.println(strAllRulesToFire);
    		
    		
    	}
    	
    
		
	    try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Export\\drools.drl"));
			writer.write(strAllRulesToFire);	    
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		    
		return strAllRulesToFire;	
    }
    
    
    private Resource[] listRules() throws IOException {
		PathMatchingResourcePatternResolver pmrs = new PathMatchingResourcePatternResolver();
		Resource[] resources = pmrs.getResources("classpath*:com/ascention/**/*.drl");
		return resources;
	}
}
