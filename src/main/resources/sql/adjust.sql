create table index_logo(
  id int primary key auto_increment,
  path char(100) not null,
  link char(50),
  rank int
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
