package com.dev.ramon.jbpm;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkflowProcessInstance;

public class DemoProcessTest {

    @Test
    public void testDemoProcess() {

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession");

        Map<String, Object> params = new HashMap<>();
        params.put("name", "John Doe");
        params.put("age", 20);  // Try 17 or 20

        System.out.println(">>> STARTING PROCESS WITH INPUT age=" + params.get("age"));

        ProcessInstance pi = kSession.startProcess("demo.process", params);

        assertEquals(ProcessInstance.STATE_COMPLETED, pi.getState());

        // Correct way to get variables
        WorkflowProcessInstance wpi = (WorkflowProcessInstance) pi;
        Object isAdult = wpi.getVariable("isAdult");

        System.out.println(">>> FINAL RESULT FROM PROCESS: isAdult = " + isAdult);

        assertNotNull("DMN did not return isAdult!", isAdult);
        assertTrue("isAdult must be a boolean", isAdult instanceof Boolean);
    }
}
