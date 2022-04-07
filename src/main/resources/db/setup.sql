create database if not exists blog_db;

create user if not exists 'blog_user'@'localhost' identified by "password";

grant all privileges on blog_db.* to 'blog_user'@'localhost';

flush privileges;