package com.tejashree.interviewgen.api;

import static spark.Spark.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tejashree.interviewgen.ai.QuestionGenerator;
import com.tejashree.interviewgen.model.Question;

import java.util.List;

public class ApiServer {

    // Gson instance for JSON parse/serialize
    private static final Gson gson = new Gson();

    public static void start() {

        // Start server on port 8080
        port(8080);
        get("/", (req, res) -> "Server is running!");

        // CORS FIX
        options("/*", (req, res) -> {
            String accessControlRequestHeaders = req.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                res.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = req.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                res.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
            res.header("Access-Control-Allow-Headers", "Content-Type,Authorization");
        });

        // HEALTH CHECK (quick)
        get("/ping", (req, res) -> {
            res.type("text/plain");
            return "pong";
        });

        /**
         * POST /generate
         * Expected JSON body:
         * {
         *   "role": "Java Developer",
         *   "experience": "Fresher",
         *   "count": 5
         * }
         *
         * Response JSON:
         * {
         *   "questions": [
         *     { "text": "...", "type": "...", "modelAnswer": "...", "keywords": "..." },
         *     ...
         *   ]
         * }
         */
        post("/generate", (req, res) -> {
            res.type("application/json");

            try {
                // parse incoming JSON
                JsonObject body = JsonParser.parseString(req.body()).getAsJsonObject();

                String role = body.has("role") ? body.get("role").getAsString() : "";
                String experience = body.has("experience") ? body.get("experience").getAsString() : "";
                int count = body.has("count") ? body.get("count").getAsInt() : 3;

                if (role.isEmpty()) {
                    // you may choose to return 400; here we fallback to a default role
                    role = "General";
                }

                // call your QuestionGenerator
                QuestionGenerator generator = new QuestionGenerator();
                List<Question> questions = generator.generateQuestions(role, experience, count);

                // wrap into an object and return as JSON
                JsonObject out = new JsonObject();
                out.add("questions", gson.toJsonTree(questions));

                return gson.toJson(out);

            } catch (Exception e) {
                e.printStackTrace();
                // return error JSON
                res.status(500);
                JsonObject err = new JsonObject();
                err.addProperty("error", "Server error: " + e.getMessage());
                return gson.toJson(err);
            }
        });

        // Server started
        System.out.println("🔥 API Server running on http://localhost:8080");
    }
}
