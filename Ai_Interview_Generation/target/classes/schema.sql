-- Auto-created schema for AI Interview app
CREATE DATABASE IF NOT EXISTS interview_ai;
USE interview_ai;

CREATE TABLE IF NOT EXISTS sessions (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_name VARCHAR(200) NOT NULL,
  role VARCHAR(200) NOT NULL,
  level VARCHAR(50) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS questions (
  id INT AUTO_INCREMENT PRIMARY KEY,
  session_id INT,
  question_text TEXT,
  question_type VARCHAR(100),
  answer LONGTEXT,
  keywords VARCHAR(500),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (session_id) REFERENCES sessions(id) ON DELETE CASCADE
);
