package com.dev.ramon.jbpm.config;

import javax.annotation.PostConstruct;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KieConfig {

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

        System.out.println("=== DEBUG KIE ===");
        System.out.println("KieBases: " + kc.getKieBaseNames());

        kc.getKieBaseNames().forEach(base ->
                System.out.println("Base: " + base +
                        " | Sessions: " + kc.getKieSessionNamesInKieBase(base))
        );
    }
}