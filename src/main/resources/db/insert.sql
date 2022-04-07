use blog_db;
set foreign_key_checks = 0;


insert into app_user(id, date_created, email, first_name, last_name, phone, user_name)
values (1, current_timestamp, "jerrywise@gmail.com", "jerry", "wise", "08055667764", "jerrywise" ),
       (2, current_timestamp, "okorokelvin@gmail.com", "kelvin", "okoro", "09044556655", "kellis" );

set foreign_key_checks = 1;
