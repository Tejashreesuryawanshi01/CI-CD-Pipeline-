package com.tejashree.interviewgen.ui;
import com.tejashree.interviewgen.ai.GeminiService;
import com.tejashree.interviewgen.ai.QuestionGenerator;
import com.tejashree.interviewgen.model.Question;
import com.tejashree.interviewgen.db.DatabaseHelper;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        com.tejashree.interviewgen.api.ApiServer.start();
    }

//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("===== AI Interview Q&A Generator =====");
//
//        // User details
//        System.out.print("Enter your name: ");
//        String userName = sc.nextLine().trim();
//
//        System.out.print("Enter job role (Java Developer / Python Developer / Data Analyst): ");
//        String role = sc.nextLine().trim();
//
//        System.out.print("Enter level (Junior / Mid / Senior): ");
//        String level = sc.nextLine().trim();
//
//        System.out.print("How many questions do you want to generate?: ");
//        int count = sc.nextInt();
//        sc.nextLine();  // Clear leftover newline
//
//        System.out.println("\nGenerating interview questions using AI... Please wait...\n");
//
//        // Generate questions
//        QuestionGenerator generator = new QuestionGenerator();
//        List<Question> questions = generator.generateQuestions(role, level, count);
//
//        // Save session in DB
//        int sessionId = DatabaseHelper.saveSession(userName, role, level);
//
//        if (sessionId == -1) {
//            System.out.println("⚠️ Warning: Session was NOT saved. Questions will not be linked.");
//        } else {
//            System.out.println("✔ Session saved successfully with ID: " + sessionId);
//        }
//
//        // Display questions & save answers
//        int index = 1;
//        for (Question q : questions) {
//            System.out.println(index + ") " + q.getText());
//            System.out.println("Type     : " + q.getType());
//            System.out.println("Answer   : " + q.getModelAnswer());
//            System.out.println("Keywords : " + q.getKeywords());
//            System.out.println("--------------------------------------");
//
//            // Save to DB if available
//            if (sessionId != -1) {
//                DatabaseHelper.saveQuestion(
//                        sessionId,
//                        q.getText(),
//                        q.getType(),
//                        q.getModelAnswer(),
//                        q.getKeywords()
//                );
//            }
//
//            index++;
//        }
//
//        System.out.println("✨ Generation complete!");
//        System.out.println("Session ID: " + sessionId + " (or -1 if not saved)");
//
//        // Interactive follow-up loop
//        System.out.println("\nYou can now ask follow-up questions about any answer, or type 'exit' to quit.");
//        System.out.println("Examples: 'Explain more about question 2', 'Why is X important?', 'exit'");
//
//        while (true) {
//            System.out.print("[ask]> ");
//            String userQuery = sc.nextLine().trim();
//            if (userQuery.equalsIgnoreCase("exit") || userQuery.equalsIgnoreCase("quit")) {
//                break;
//            }
//
//            // If user asks about a specific question by number, attach that answer as context
//            String context = "";
//            try {
//                if (userQuery.toLowerCase().matches(".*\\bquestion\\s+\\d+\\b.*")) {
//                    String[] tokens = userQuery.split("\\s+");
//                    for (int i = 0; i < tokens.length; i++) {
//                        if (tokens[i].equalsIgnoreCase("question") && i + 1 < tokens.length) {
//                            int qnum = Integer.parseInt(tokens[i + 1]);
//                            if (qnum >= 1 && qnum <= questions.size()) {
//                                Question ref = questions.get(qnum - 1);
//                                context = "Context: Question: " + ref.getText() + "\nAnswer: " + ref.getModelAnswer() + "\n";
//                            }
//                        }
//                    }
//                }
//            } catch (Exception ignored) {}
//
//            String prompt = context + userQuery + "\nProvide a detailed, interview-style answer with examples and follow-up tips.";
//            String answer = GeminiService.generateAnswer(prompt);
//            System.out.println("\nAI Reply:\n" + answer + "\n");
//        }
//
//        sc.close();
//
//        System.out.println("Goodbye — thanks for using the AI Interview Q&A Generator.");
//    }
}