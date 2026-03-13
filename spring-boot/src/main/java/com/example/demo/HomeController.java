package com.example.demo; 
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.google.gson.Gson;

@RestController
@CrossOrigin
@RequestMapping("/")
public class HomeController {

    EmbedProperties embedProperties;
    @GetMapping("getEmbedConfig")
    public Map<String, String> getEmbedConfig() throws IOException {
        ClassPathResource resource = new ClassPathResource("embedConfig.json");
        byte[] jsonBytes = StreamUtils.copyToByteArray(resource.getInputStream());
        String jsonContent = new String(jsonBytes, StandardCharsets.UTF_8);

        Gson gson = new Gson();
        this.embedProperties = gson.fromJson(jsonContent, EmbedProperties.class);

        // Create a Map with the desired properties
        Map<String, String> result = new HashMap<>();
        result.put("dashboardId", embedProperties.getDashboardId());
        result.put("serverUrl", embedProperties.getServerUrl());
        result.put("embedType", embedProperties.getEmbedType());
        result.put("environment", embedProperties.getEnvironment());
        result.put("siteIdentifier", embedProperties.getSiteIdentifier());
        return result;
    }

    @PostMapping("tokengeneration")
    public String Tokengeneration() throws Exception {
        // Ensure embedProperties is loaded (read embedConfig.json if needed)
        if (this.embedProperties == null) {
            ClassPathResource resource = new ClassPathResource("embedConfig.json");
            byte[] jsonBytes = StreamUtils.copyToByteArray(resource.getInputStream());
            String jsonContent = new String(jsonBytes, StandardCharsets.UTF_8);
            Gson gson = new Gson();
            this.embedProperties = gson.fromJson(jsonContent, EmbedProperties.class);
        }

        // Build JSON payload matching the C# TokenGeneration example
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", embedProperties.getUserEmail());
        payload.put("serverurl", embedProperties.getServerUrl());
        payload.put("siteidentifier", embedProperties.getSiteIdentifier());
        payload.put("embedsecret", embedProperties.getEmbedSecret());
        Map<String, String> dashboardMap = new HashMap<>();
        dashboardMap.put("id", embedProperties.getDashboardId());
        payload.put("dashboard", dashboardMap);

        RestTemplate restTemplate = new RestTemplate();

        String requestUrl = embedProperties.getServerUrl() + "/api/" + embedProperties.getSiteIdentifier() + "/embed/authorize";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        String result = restTemplate.postForObject(requestUrl, request, String.class);

        // Parse response and extract Data.access_token
        Gson gson = new Gson();
        Map<?, ?> resultMap = gson.fromJson(result, Map.class);
        if (resultMap != null && resultMap.get("Data") instanceof Map) {
            Map<?, ?> data = (Map<?, ?>) resultMap.get("Data");
            Object tokenObj = data.get("access_token");
            if (tokenObj != null) {
                return tokenObj.toString();
            }
        }
        return null;
    }
}

