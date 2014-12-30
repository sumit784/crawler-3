
drop database if exists crawler;
create database crawler;

use crawler;

create table proxy (
  id serial primary key,
  host char(50) not null,
  port int not null,
  type char(20) default 'http',
  speed int,
  unique(host, port)
);
