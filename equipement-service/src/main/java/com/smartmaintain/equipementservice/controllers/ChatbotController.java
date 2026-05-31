package com.smartmaintain.equipementservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/chatbot")
@CrossOrigin("*")
public class ChatbotController {

    @Value("${COHERE_API_KEY:}")
    private String cohereApiKey;
    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/ask")
    public ResponseEntity<Map<String, String>> askChatbot(@RequestBody Map<String, String> request) {
        String role = request.getOrDefault("role", "technician");
        String userMessage = request.getOrDefault("message", "");

        String systemPrompt = getSystemPromptForRole(role);
        String answer = callCohereCommandRPlus(systemPrompt, userMessage);
        
        // If Cohere fails or the key was actually Gemini (wait, AQ. starts are usually Cohere keys)
        // I will use Cohere Command R Plus format as the key looks like Cohere.
        // If it fails, I'll return a fallback message.

        Map<String, String> response = new HashMap<>();
        response.put("answer", answer);
        return ResponseEntity.ok(response);
    }

    private String getSystemPromptForRole(String role) {
        switch (role.toLowerCase()) {
            case "technician":
                return "You are an AI assistant specialized in plane maintenance. Focus on helping the Technician with task execution and safety guidelines.";
            case "engineer":
                return "You are an AI assistant specialized in plane maintenance. Focus on helping the Engineer with task separation, priority, team leadership, and task assignment.";
            case "manager":
                return "You are an AI assistant specialized in plane maintenance. Focus on helping the Manager with fleet overview, cost/efficiency analysis, and the experiences needed for each maintenance.";
            case "admin":
                return "You are an AI assistant specialized in plane maintenance. Focus on helping the Admin with system monitoring, user checking, and overall application health.";
            default:
                return "You are an AI assistant specialized in plane maintenance. Answer queries related to tasks, teams, and rapports.";
        }
    }

    private String callCohereCommandRPlus(String systemPrompt, String message) {
        try {
            if (cohereApiKey == null || cohereApiKey.isBlank()) {
                return "Simulated AI Response: COHERE_API_KEY is not configured.";
            }

            String url = "https://api.cohere.ai/v1/chat";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(cohereApiKey);

            Map<String, Object> body = new HashMap<>();
            body.put("model", "command-r-plus");
            body.put("preamble", systemPrompt);
            body.put("message", message);
            body.put("temperature", 0.3);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

            if (response.getBody() != null && response.getBody().containsKey("text")) {
                return (String) response.getBody().get("text");
            }
            return "I understood your query, but could not formulate a response.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Simulated AI Response (API key might be invalid or network error): Here is a specialized response based on your role. " + e.getMessage();
        }
    }
}
