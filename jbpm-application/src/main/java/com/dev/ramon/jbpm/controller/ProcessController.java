package com.dev.ramon.jbpm.controller;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process")
public class ProcessController {

    private static final Logger log = LogManager.getLogger(ProcessController.class);
    private final KieSession kieSession;

    public ProcessController(KieSession kieSession) {
        this.kieSession = kieSession;
    }

    @PostMapping("/start")
    public String startProcess(@RequestBody Map<String, Object> params) {

        log.info(">>> Received request: {}", params);

        kieSession.startProcess("CustomerCardProcess", params);

        return "Process started with parameters: " + params;
    }
}
