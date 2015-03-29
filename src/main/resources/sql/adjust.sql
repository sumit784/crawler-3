create table commodity_crawl_log(
  id int primary key auto_increment,
  commodity_id int not null,
  action char(200) not null,
  log_time datetime not null,
  success boolean not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
