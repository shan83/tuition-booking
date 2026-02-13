package com.example.search.service;

import com.example.search.dto.SearchFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    @Value("${gemini.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public SearchFilter extractFilters(String query) throws Exception {

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3-flash-preview:generateContent?key=" + apiKey;

        String prompt = """
        Extract search filters from the following text.
        Return only JSON in this format:
        {
          "subject": "",
          "level": "",
          "district": "",
          "day": ""
        }

        Text: %s
        """.formatted(query);

        Map<String, Object> request = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        Map response = restTemplate.postForObject(url, request, Map.class);

        List candidates = (List) response.get("candidates");
        Map firstCandidate = (Map) candidates.get(0);
        Map content = (Map) firstCandidate.get("content");
        List parts = (List) content.get("parts");
        Map part = (Map) parts.get(0);

        String jsonText = (String) part.get("text");

        // Remove markdown if Gemini adds ```json
        jsonText = jsonText.replace("```json", "")
                           .replace("```", "")
                           .trim();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonText, SearchFilter.class);
    }
}
