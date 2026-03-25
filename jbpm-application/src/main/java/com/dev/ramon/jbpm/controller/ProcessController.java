package com.dev.ramon.jbpm.controller;

import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process")
public class ProcessController {

    private final KieSession kieSession;

    public ProcessController(KieSession kieSession) {
        this.kieSession = kieSession;
    }

    @PostMapping("/start")
    public String startProcess() {
        System.out.println("Starting jBPM process...");
        kieSession.startProcess("demo.process");
        return "Process demo.process started!";
    }
}
