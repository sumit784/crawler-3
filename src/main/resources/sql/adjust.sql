drop table if exists hot_search_word;
create table hot_search_word (
  id int primary key auto_increment,
  content char(50) not null,
  category_id int not null,
  ranking int not null unique,
  hot boolean not null default false
) engine=InnoDB default charset=utf8;
