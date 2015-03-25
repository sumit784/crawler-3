drop table if exists category_branch;
create table category_branch (
  id int primary key auto_increment,
  category_id int not null,
  branch_id int not null,
  ranking int not null,
  unique(category_id, branch_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
