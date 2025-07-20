package com.harishverma.financial.assistant.service;

import com.harishverma.financial.assistant.dto.CohereRequest;
import com.harishverma.financial.assistant.dto.CohereResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CohereService {

    @Value("${cohere.api.key}")
    private String apiKey;

    @Value("${cohere.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getSummaryFromCohere(String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        CohereRequest request = CohereRequest.builder()
                .message("Summarize and categorize the following financial text :\n\n "+ text)
                .model("command-r-plus")
                .build();

        HttpEntity<CohereRequest> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<CohereResponse> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    entity,
                    CohereResponse.class
            );

            CohereResponse responseBody = response.getBody();
            if (responseBody != null) {
                return responseBody.getText();
            }
            return "Failed to summarize";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
