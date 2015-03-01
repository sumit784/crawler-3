create table shoppe(
  id int primary key auto_increment,
  name char(50),
  url char(200)
);

alter table shoppe add column branch_id int;