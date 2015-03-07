drop table if exists user_log;
create table user_log (
  id int primary key auto_increment,
  user_id int not null,
  action char(200) not null,
  log_time datetime not null
) engine=innoDB default charset=utf8;
