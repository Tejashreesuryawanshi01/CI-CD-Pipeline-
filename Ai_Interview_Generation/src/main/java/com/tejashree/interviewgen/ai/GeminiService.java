package com.tejashree.interviewgen.ai;

import okhttp3.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class GeminiService {

    private static final String API_KEY = "AIzaSyDPw16jutPyyykAaDNPPTdhhwskNuzVH40";

    // ✅ Correct URL (Without space)
    private static final String API_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent";

    // ✅ OkHttp Client with Timeout settings
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(120, java.util.concurrent.TimeUnit.SECONDS)  // Increased to avoid timeout
            .build();


    public static String generateAnswer(String question) {
        try {
            // Build request structure
            JsonObject requestBody = new JsonObject();
            JsonArray contents = new JsonArray();
            JsonObject content = new JsonObject();
            JsonArray parts = new JsonArray();

            JsonObject part = new JsonObject();
            part.addProperty("text", question);
            parts.add(part);

            content.add("parts", parts);
            contents.add(content);
            requestBody.add("contents", contents);

            RequestBody body = RequestBody.create(
                    requestBody.toString(),
                    MediaType.parse("application/json")
            );

            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-goog-api-key", API_KEY)
                    .build();

            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                return "AI Error (HTTP): " + response.code();
            }

            String json = response.body().string();
            response.close();

            return extractAnswer(json);

        } catch (Exception e) {
            e.printStackTrace();
            return "AI Error: " + e.getMessage();
        }
    }


    private static String extractAnswer(String json) {
        try {
            JsonObject jsonResponse = JsonParser.parseString(json).getAsJsonObject();

            if (jsonResponse.has("error")) {
                return "AI Error: " + jsonResponse.getAsJsonObject("error").get("message").getAsString();
            }

            JsonArray candidates = jsonResponse.getAsJsonArray("candidates");
            JsonObject candidate = candidates.get(0).getAsJsonObject();
            JsonObject content = candidate.getAsJsonObject("content");
            JsonArray parts = content.getAsJsonArray("parts");
            JsonObject part = parts.get(0).getAsJsonObject();

            return part.get("text").getAsString();

        } catch (Exception e) {
            return "Unable to extract answer";
        }
    }
}
