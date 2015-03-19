drop table if exists index_logo;
create table index_logo(
  id int primary key auto_increment,
  path char(100) not null,
  link char(50),
  ranking int unique
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
