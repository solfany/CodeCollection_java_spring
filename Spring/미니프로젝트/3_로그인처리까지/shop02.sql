drop database shop;
create database shop default character set utf8 collate utf8_general_ci;
show databases;
commit;

create database BOARD;
USE BOARD;

# User(user_email[PK], user_password, user_nickname,user_phone_number,user_address,user_address_detail, user_profile)
create table User(
	user_email varchar(50) PRIMARY KEY,
	user_password VARCHAR(20) NOT NULL,
	user_nickname VARCHAR(30) NOT NULL,
      user_phone_number VARCHAR(15) NOT NULL,
	user_address TEXT NOT NULL,
	user_profile TEXT
);




CREATE TABLE Board(
	board_number INT PRIMARY KEY AUTO_INCREMENT,
	board_title VARCHAR(200) NOT NULL,
	board_content TEXT NOT NULL,
	board_image TEXT,
	board_video TEXT,
	board_file TEXT,
	board_writer_email VARCHAR(50) NOT NULL,
	board_writer_profile TEXT,
	board_writer_nickname VARCHAR(30) NOT NULL,
	board_write_date DATE NOT NULL,
	board_click_count INT DEFAULT 0,
	board_like_count INT DEFAULT 0,
	board_comment_count INT DEFAULT 0
);

CREATE TABLE PopularSerarch(
	popular_term varchar(200) PRIMARY KEY,
	popular_search_count INT DEFAULT 1
);

# like테이블이 안만들어진다
CREATE TABLE Liky(
	like_id INT AUTO_INCREMENT PRIMARY KEY,
	board_number INT NOT NULL,
	user_email VARCHAR(50) NOT NULL,
	like_user_profile TEXT,
	like_user_nickname VARCHAR(30) NOT NULL
);

CREATE TABLE Comment(
	comment_id INT AUTO_INCREMENT PRIMARY KEY,
	board_number INT NOT NULL,
	user_email VARCHAR(50) NOT NULL,
	comment_user_profile TEXT,
	comment_user_nickname VARCHAR(30) NOT NULL,
	comment_write_date Date NOt NULL,
	comment_content TEXT NOT NULL
);

drop table liky;
drop table comment;

INSERT INTO User (user_email, user_password, user_nickname, user_phone_number, user_address, user_profile) VALUES ('cita@cita', 'cita', 'nickname', '010-1234-5678', 'Seoul, Korea', null);

desc user;
select * from user;
delete from user where user_email = 'cita@cita';

Drop table User;

create table User(
	user_email varchar(50) PRIMARY KEY,
	user_password VARCHAR(200) NOT NULL,  
	user_nickname VARCHAR(30) NOT NULL,
      user_phone_number VARCHAR(15) NOT NULL,
	user_address TEXT NOT NULL,
	user_profile TEXT
);
# user_password VARCHAR(200)으로 만들어준다







