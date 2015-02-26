create table category (
  id int primary key auto_increment,
  name char(50) not null,
  parent_id int
) engine=InnoDB default charset=utf8;
