package com.ascention.validationWS;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.web.bind.annotation.CrossOrigin;

import com.ascention.validationWS.config.DroolsConfig;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The main class, which Spring Boot uses to bootstrap the application.
 *
 * @author Rohit Argade
 */
@SpringBootApplication
@Import({DroolsConfig.class})
@EnableAsync
//@EnableSwagger2
@EntityScan(basePackages = {"com.ascention.validationWS.beans"})
//@CrossOrigin(origins="*")
public class ValidationApp  {

	private static Logger log = LoggerFactory.getLogger(ValidationApp.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ValidationApp.class, args); 

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);

        StringBuilder sb = new StringBuilder("Application beans:\n");
        for (String beanName : beanNames) {
            sb.append(beanName + "\n");
        }
        log.info(sb.toString());
    }
    
    /**
     * By defining the {@link KieContainer} as a bean here, we ensure that
     * Drools will hunt out the kmodule.xml and rules on application startup.
     * Those can be found in <code>src/main/resources</code>.
     * 
     * @return The {@link KieContainer}.
     */
//    @Bean
//    public KieContainer kieContainer() {
//        return KieServices.Factory.get().getKieClasspathContainer();
//    }

}
