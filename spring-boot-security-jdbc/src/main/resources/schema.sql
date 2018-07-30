
drop table if exists Users;
create table Users(id bigint auto_increment, username varchar(255), password varchar(255));

drop table if exists Roles;
create table Roles(username varchar(255),authority  varchar(255), UNIQUE(username,authority));
