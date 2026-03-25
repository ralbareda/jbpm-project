# jbpm-project
A multi‑module **jBPM 7** + **Spring Boot** project demonstrating how to package BPMN2 processes inside a **KJAR module** and execute them from a REST API.

This project contains:

## 🚀 Overview

- build a **KJAR** containing BPMN2 workflows
- load the KJAR using a **ReleaseId**
- bootstrap jBPM inside a Spring Boot application
- expose a REST endpoint to start a process
- run jBPM using **Java 17**
- structure multi‑module builds correctly

## 🛠 Requirements

| Component        | Version            |
|------------------|--------------------|
| Java             | **17**             |
| Maven            | 3.8+               |
| Spring Boot      | 2.7.18             |
| jBPM / Drools    | 7.74.1.Final       |

⚠️ **Java 21 is NOT supported by jBPM 7.x**  
(`java.lang.Compiler` was removed → runtime failure)

## 🧱 Project Structure

Build Structure Diagram
jbpm-project
├── pom.xml                 (parent)
├── jbpm-kjar/
│   ├── pom.xml
│   └── processes/...
└── jbpm-application/
├── pom.xml
└── src/main/java/...

### **1. Parent Project (`jbpm-project/`)**
Holds:
- multi‑module aggregator
- build properties
- shared configuration
- `jbpm.version` property

### **2. jbpm-kjar**
Contains:
- BPMN2 processes
- `META-INF/kmodule.xml`
- packaged as a **KJAR** using `kie-maven-plugin`

### **3. jbpm-application**
A Spring Boot 2.7.x application that:
- loads the KJAR using KIE APIs
- creates a `KieContainer` using ReleaseId
- exposes REST endpoint

## ⚙️ KieConfig

The Spring Boot app loads the KJAR explicitly using a ReleaseId:

```java
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

BPMN Process Definition
File: jbpm-kjar/src/main/resources/processes/DemoProcess.bpmn2

REST Endpoint
The REST API for launching the process:
POST /process/start

Running the Application
1. Build everything
Shellmvn clean installShow more lines
This builds:

the KJAR module
the Spring Boot fat jar

2. Run the Spring Boot app
Shellcd jbpm-applicationmvn spring-boot:run``Show more lines
Expected console output:
KieBases: [kbase]
Sessions: [ksession]
💡 Hello from jBPM!


🚀 Starting the Process via REST
Use cURL:
Shellcurl -X POST http://localhost:8080/process/start``Show more lines
Response:
Process demo.process started!