
drop database if exists crawler;
create database crawler;

use crawler;

create table proxy (
  id int primary key auto_increment,
  host char(50) not null,
  port int not null,
  type char(20) not null,
  speed int not null,
  unique(host, port)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table commodity (
  id bigint primary key auto_increment,
  name char(100) not null,
  url varchar(300) not null,
  price double not null,
  low_price double,
  original_price double,
  branch_id int,
  category_id int,
  on_shelf_time datetime
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table price_record (
  id bigint primary key auto_increment,
  commodity_id bigint,
  record_time datetime,
  price double,
  grab_time datetime
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
