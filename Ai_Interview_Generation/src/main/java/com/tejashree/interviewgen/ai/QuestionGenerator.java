package com.tejashree.interviewgen.ai;
import com.tejashree.interviewgen.model.Question;
import java.util.*;

public class QuestionGenerator {
    private Random random = new Random();

    // Generate n questions
    public List<Question> generateQuestions(String role, String level, int count) {

        List<String> topics = RoleProfiles.getTopicsForRole(role);
        List<Question> questions = new ArrayList<>();

        // Safety check to avoid random.nextInt(0)
        if (topics == null || topics.isEmpty()) {
            throw new IllegalArgumentException("No topics found for role: " + role);
        }

        for (int i = 0; i < count; i++) {

            // Safely pick a topic
            int index = random.nextInt(topics.size());
            String topic = topics.get(index);

            // Decide type -> technical / behavioral / scenario
            String type = decideType(level, i);

            // Build question text
            String qText = createQuestionText(type, topic);

            // Prepare prompt for AI
            String aiPrompt = qText +
                    " Give a detailed interview-style answer with examples and real-life explanation.";

            // Get AI generated answer
            String answer = GeminiService.generateAnswer(aiPrompt);


            // Keywords for search/save
            String keywords = "keywords: " + topic;

            // Add full question object
            questions.add(new Question(qText, type, answer, keywords));
        }

        return questions;
    }

    // Decide question type based on level + index
    private String decideType(String level, int i) {

        if (level.equalsIgnoreCase("Junior")) {
            return (i % 3 == 0) ? "behavioral" : "technical";

        } else if (level.equalsIgnoreCase("Senior")) {
            return (i % 4 == 0) ? "scenario" : "technical";

        } else if (level.equalsIgnoreCase("Mid")) {
            return (i % 2 == 0) ? "technical" : "scenario";
        }

        return "technical"; // default
    }

    // Build question text depending on type
    private String createQuestionText(String type, String topic) {
        switch (type) {
            case "technical":
                return "Explain " + topic + " with an example.";

            case "behavioral":
                return "Describe a real experience where you applied " + topic + ".";

            case "scenario":
                return "How would you solve a real-life problem related to " + topic + "?";

            default:
                return "Explain " + topic + ".";
        }
    }
}