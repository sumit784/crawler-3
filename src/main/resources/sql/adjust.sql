create table seo_keyword (
  id int primary key auto_increment,
  url char(200) unique not null,
  word varchar(800) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
