package com.dev.ramon.runner;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Runner {
    public static void main(String[] args) {

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();

        // Load the named session defined in kmodule.xml
        KieSession kSession = kContainer.newKieSession("ksession");

        System.out.println("Starting process...");
        kSession.startProcess("demo.process");
        kSession.dispose();

        System.out.println("Finished!");
    }
}