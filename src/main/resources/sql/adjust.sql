create table user(
  id int primary key auto_increment,
  username char(50) not null,
  password char(50) not null,
  role char(100)
);