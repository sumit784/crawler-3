create table category_poster (
  id int primary key auto_increment,
  category_id int not null,
  path char(200) not null,
  link char(100),
  ranking int unique not null
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
