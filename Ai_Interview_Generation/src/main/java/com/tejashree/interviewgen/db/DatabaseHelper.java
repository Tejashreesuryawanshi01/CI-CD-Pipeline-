package com.tejashree.interviewgen.db;
import java.sql.*;
public class DatabaseHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/interview_ai";
    private static final String USER = "root";    // your MySQL username
    private static final String PASSWORD = "Tejashree"; // your MySQL password

    // Connect to database
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //System.out.println("✔ Database connected successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error connecting to database: " + e.getMessage());
        }
        return connection;
    }

    

    // Save session and return generated session ID
    public static int saveSession(String userName, String role, String level) {
        String query = "INSERT INTO sessions (user_name, role, level) VALUES (?, ?, ?)";

        Connection con = getConnection();
        if (con == null) {
            // DB not available — return -1 to indicate not saved
            return -1;
        }

        try (PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, userName);
            ps.setString(2, role);
            ps.setString(3, level);
            int updated = ps.executeUpdate();
            if (updated == 0) {
                System.out.println("❌ No rows inserted for session.");
                return -1;
            }

            // Get generated session ID
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs != null && rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Error saving session: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try { con.close(); } catch (SQLException ignored) {}
        }

        return -1;
    }

    // Save each question
    public static void saveQuestion(int sessionId, String questionText, String type, String answer, String keywords) {
        String query = "INSERT INTO questions (session_id, question_text, question_type, answer, keywords) VALUES (?, ?, ?, ?, ?)";

        Connection con = getConnection();
        if (con == null) {
            return; // DB not available
        }

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, sessionId);
            ps.setString(2, questionText);
            ps.setString(3, type);
            ps.setString(4, answer);
            ps.setString(5, keywords);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error saving question: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try { con.close(); } catch (SQLException ignored) {}
        }
    }
}
