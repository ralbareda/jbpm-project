package com.dev.ramon.jbpm.config;

import javax.annotation.PostConstruct;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KieConfig {

    private static final Logger log = LoggerFactory.getLogger(KieConfig.class);

    @Bean
    public KieContainer kieContainer() {
        KieServices ks = KieServices.Factory.get();
        ReleaseId releaseId = ks.newReleaseId("com.dev.ramon", "jbpm-kjar", "1.0.0");
        return ks.newKieContainer(releaseId);
    }

    @Bean
    public KieSession kieSession(KieContainer kieContainer) {
        return kieContainer.newKieSession("ksession");
    }

    @PostConstruct
    public void debugKie() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();

        log.info("=== DEBUG KIE ===");
        log.info("KieBases: {}", kc.getKieBaseNames());

        kc.getKieBaseNames().forEach(base ->
                log.info("Base: {} | Sessions: {}", base, kc.getKieSessionNamesInKieBase(base))
        );
    }
}