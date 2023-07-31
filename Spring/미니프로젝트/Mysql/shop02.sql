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

select * from user;
delete from user where user_email = 'cita@cita';
---------------------------------------------------------------------------------------------

Drop table User;

create table User(
	user_email varchar(50) PRIMARY KEY,
	user_password VARCHAR(200) NOT NULL,  
	user_nickname VARCHAR(30) NOT NULL,
      user_phone_number VARCHAR(15) NOT NULL,
	user_address TEXT NOT NULL,
	user_profile TEXT
);

select * from user;
# user_password VARCHAR(200)으로 만들어준다
-------------------------------------------------------------------------------------
# 문자열 비교를 위한 collation을 "utf8_general_ci"로 설정합니다.
create database shop01 default character set utf8 collate utf8_general_ci;
# 'shop'이라는 이름의 새 데이터베이스를 생성하고, 이 데이터베이스에서 사용할 기본 문자셋을 UTF-8로 설정하며,
#  문자열 비교 시에 대소문자를 구별하지 않도록 설정하는 것을 의미합니다
drop database shop01;

show databases;
use shop01;
SELECT * FROM User;
SELECT * FROM item;
drop table item;
-----------------------------------------------------------------
# CREATE DATABASE shop01;
show databases;
use shop01;

desc item;
select * from item;
commit;

select * from member;
------------------------------------------------------------------------------------------






