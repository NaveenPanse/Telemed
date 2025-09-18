package com.Navpaksa.Navpaksa.service;


import org.springframework.http.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CometChatService {
	private final String appId = "276877073d884bab";
    private final String apiKey = "b2053b32382cbd77d978c5150e908f926e12369f";
    private final String region = "in"; // change if not in India
    private final String baseUrl = "https://" + appId + ".api-" + region + ".cometchat.io/v3/users";

    public void createCometChatUser(String uid, String name) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("appid", appId);
        headers.set("apiKey", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = String.format("""
            {
                "uid": "%s",
                "name": "%s"
            }
        """, uid, name);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);
            System.out.println("CometChat user creation response: " + response.getBody());
        } catch (Exception e) {
            System.err.println("CometChat user creation failed: " + e.getMessage());
        }
    }
}
