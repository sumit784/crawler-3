create table hot_search_word (
  id int primary key auto_increment,
  content char(50) not null,
  category_id int,
  search_count int,
  last_time datetime
) engine=InnoDB default charset=utf8;
