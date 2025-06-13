package controller;

import com.google.gson.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LlmService {
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";
    private final String apiKey = "tu-api-key";
    private final String model = "mistralai/mistral-7b-instruct:free";
    
    public String generarRespuesta(String prompt) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        
        JsonObject body = new JsonObject();
        body.addProperty("model", model);
        
        JsonArray messages = new JsonArray();
        JsonObject message = new JsonObject();
        message.addProperty("role", "user");
        message.addProperty("content", prompt);
        messages.add(message);
        
        body.add("messages", messages);
        body.addProperty("temperature", 0.7);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Error API: " + response.body());
        }

        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        return jsonResponse.getAsJsonArray("choices")
                         .get(0).getAsJsonObject()
                         .getAsJsonObject("message")
                         .get("content").getAsString();
    }
}