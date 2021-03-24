package com.ascention.validationWS.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
//import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.Message;

import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.ascention.validationWS.beans.RuleRepository;
import com.ascention.validationWS.beans.RuleTable;



/**
 * Created by Rohit 
 */
@Configuration
@ComponentScan(basePackages = {"com.ascention.validationWS"})
public class DroolsConfig {
	PrintStream out;
	@Autowired
	private RuleRepository discountRepository;
	
	private Resource[] listRules() throws IOException {
		PathMatchingResourcePatternResolver pmrs = new PathMatchingResourcePatternResolver();
		Resource[] resources = pmrs.getResources("classpath*:com/ascention/**/*.drl");
		return resources;
	}

	@Bean
	public KieContainer kieContainer() throws IOException {
		KieServices ks = KieServices.Factory.get();
		final KieRepository kr = ks.getRepository();
		kr.addKieModule(new KieModule() {
			public ReleaseId getReleaseId() {
				return kr.getDefaultReleaseId();
			}
		});
		KieFileSystem kfs = ks.newKieFileSystem();
		Resource[] files = listRules();
		String myString = "";
		for(Resource file : files) {
			myString = IOUtils.toString(file.getInputStream(), "UTF-8");
			kfs.write("src/main/resources/"+ file.getFilename(), myString);
		}
		//kfs.write("C:\\Rohit\\Projects\\GS1\\SpringToolSuiteWorkspace\\validationService\\src\\main\\resources\\com\\ascention\\validationWS\\rules\\sd.drl",  myString);
		//String myString = IOUtils.toString(file.getInputStream(), "UTF-8");
		//kfs.write("C:\\Rohit\\Projects\\GS1\\SpringToolSuiteWorkspace\\validationService\\src\\main\\resources\\com\\ascention\\validationWS\\rules\\sd.drl",  getRule());
		//kfs.write("D:/workspace/git-projects/spring-boot-drools/src/main/resources/com/mindzen/drools/rules/sd.drl", getTemplateRule());
		KieBuilder kb = ks.newKieBuilder(kfs);
		kb.buildAll(); // kieModule is automatically deployed to KieRepository if successfully built.
		
		Results results = kb.getResults();

		if (results.hasMessages(Message.Level.ERROR)) {
			StringBuffer buf=new StringBuffer();
			for (Message message : results.getMessages(Message.Level.ERROR)) {
				buf.append("ERROR: "+message.toString().trim()+"/r/n");
			}
		}

		KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());
//		KieScanner kScanner = ks.newKieScanner( kContainer );
//		kScanner.start( 10000L );
//        KieSession kSession = kContainer.newKieSession();
//        kSession.setGlobal("out", out);
//
//        kSession.insert(new com.ascention.validationWS.Message("Dave", "Hello, Rohit. Do you read me, Rohit?"));
//        kSession.fireAllRules();
		return kContainer;
	}

	@Bean
	public KieBase kieBase() throws IOException {
		KieBase kieBase = kieContainer().getKieBase();
		return kieBase;
	}
	
//	public String getTemplateRule(){
//		String drl = "";
//		Iterable<RuleTable> iterable = null;
//		List<RuleTable> list = new ArrayList<RuleTable>();
//		try {
//			iterable = discountRepository.findAll();
//			iterable.forEach(list::add);
//			System.out.println("list: "+list);
//			ObjectDataCompiler converter = new ObjectDataCompiler();
//			drl = converter.compile(list, getRulesStream());
//			System.out.println("Generated drl file:\n"+drl);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		return drl;
//	}

	private static InputStream getRulesStream(){
		InputStream inputStream = null;
		try{
			inputStream = new FileInputStream("C:\\Rohit\\Projects\\GS1\\SpringToolSuiteWorkspace\\validationService\\src\\main\\resources\\com\\ascention\\validationWS\\rules\\rule_template.drt");
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		return inputStream;
	}
	private static String getRule() {
        String s = "" +
                   "package com.ascention.validationWS \n\n" +
                   "import com.ascention.validationWS.Message \n\n" +
                   "global java.io.PrintStream out \n\n" +
                   "rule \"rule 1\" when \n" +
                   "    m : Message( ) \n" +
                   "then \n" +
                   "    out.println( m.getName() + \": \" +  m.getText() ); \n" +
                   "end \n" +
                   "rule \"rule 2\" when \n" +
                   "    Message( text == \"Hello, HAL. Do you read me, HAL?\" ) \n" +
                   "then \n" +
                   "    insert( new Message(\"HAL\", \"Dave. I read you.\" ) ); \n" +
                   "end";

        return s;
    }
}