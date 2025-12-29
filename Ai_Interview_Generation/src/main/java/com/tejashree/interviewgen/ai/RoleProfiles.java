package com.tejashree.interviewgen.ai;
import java.util.*;

public class RoleProfiles {
    private static final Map<String, List<String>> roleTopics = new HashMap<>();

    static {

        // -------------------------------
        // Java Developer Topics
        // -------------------------------
        roleTopics.put("Java Developer", Arrays.asList(
                "OOP Concepts",
                "Collections Framework",
                "JDBC",
                "Exception Handling",
                "Multithreading",
                "Design Patterns",
                "Generics"
        ));

        // -------------------------------
        // Python Developer Topics
        // -------------------------------
        roleTopics.put("Python Developer", Arrays.asList(
                "Data Types",
                "OOP in Python",
                "File Handling",
                "Modules & Packages",
                "Error Handling",
                "List Comprehension"
        ));

        // -------------------------------
        // Data Analyst Topics
        // -------------------------------
        roleTopics.put("Data Analyst", Arrays.asList(
                "SQL Basics",
                "SQL Joins",
                "Statistics Fundamentals",
                "Data Cleaning",
                "Data Visualization",
                "Dashboards"
        ));

        // -------------------------------
        // Software Tester Topics
        // -------------------------------
        roleTopics.put("Software Tester", Arrays.asList(
                "Manual Testing",
                "Automation Testing",
                "SDLC & STLC",
                "Bug Life Cycle",
                "Test Cases",
                "Selenium Basics"
        ));

        // -------------------------------
        // Full-Stack Developer Topics
        // -------------------------------
        roleTopics.put("Full Stack Developer", Arrays.asList(
                "HTML/CSS",
                "JavaScript",
                "React Basics",
                "REST APIs",
                "Database Basics",
                "Backend Logic"
        ));
    }

    // Safe getter for topics
    public static List<String> getTopicsForRole(String role) {
        return roleTopics.getOrDefault(role, Collections.emptyList());
    }
}
