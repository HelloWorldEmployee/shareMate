-- Active: 1730881291264@@127.0.0.1@3306@sharemate

CREATE TABLE user (
user_id varchar(50) primary key,
user_password varchar(60) not null,
user_name varchar(50) not null,
user_email varchar(100) not null,
user_role varchar(100) DEFAULT 'user'
);

CREATE TABLE study (
study_id INT AUTO_INCREMENT PRIMARY KEY,
study_name VARCHAR(255),
study_content TEXT,
user_id VARCHAR(50),
count INT, -- 조회수
complain INT, -- 신고수
CONSTRAINT fk_study_user FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
);

CREATE TABLE study_comment (
study_comment_id INT AUTO_INCREMENT PRIMARY KEY,
study_comment_content TEXT,
study_id INT,
user_id VARCHAR(50),
CONSTRAINT fk_study_comment_study FOREIGN KEY (study_id) REFERENCES study (study_id) ON DELETE CASCADE,
CONSTRAINT fk_study_comment_user FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
);

CREATE TABLE competition (
comp_id INT AUTO_INCREMENT PRIMARY KEY,
comp_title VARCHAR(255) not null,
comp_content TEXT not null,
user_id VARCHAR(50),
count INT, -- 조회수
CONSTRAINT fk_comp_user FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
);

CREATE Table comp_join (
join_id INT AUTO_INCREMENT PRIMARY KEY,
comp_id INT,
user_id VARCHAR(50),
CONSTRAINT fk_comp_join_competition FOREIGN KEY (comp_id) REFERENCES competition (comp_id) ON DELETE CASCADE,
CONSTRAINT fk_comp_join_user FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
);
