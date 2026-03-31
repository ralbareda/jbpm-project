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

| Component     | Version            |
|---------------|--------------------|
| Java          | **17**             |
| Maven         | 3.8+               |
| Spring Boot   | 2.7.18             |
| jBPM / Drools | 7.64.0.Final       |

YOu can use the Apache KIE BPMN Editor for VS Code, 
which provides a user-friendly interface for designing and managing BPMN processes. 
It allows you to create, edit, and visualize your BPMN workflows directly within the VS Code environment, 
making it easier to develop and maintain your jBPM processes.

-------------------
1. Build everything:
--------------------
mvn clean install -DskipTests


🚀 Testing the Process via REST:
---------------------------------
-- REST Request:
curl -X POST http://localhost:8080/process/start \
  -H "Content-Type: application/json" \
  -d '{"name":"John", "age":25}'

-- REST Response:
isAdult: true >>> VENDER VISA ORO
otherwise >>> REGALAR CARNET_JOVE

4. What Happens at Runtime?
-----------------------------
Your BPMN calls the DMN model directly:
age (from main BPMN)
   ↓
DMN Engine evaluates age (BPMN Subprocess)
   ↓
isAdult decision (true/false)
   ↓
flow continues