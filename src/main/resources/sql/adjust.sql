drop table if exists app_config;
create table app_config (
  id int primary key auto_increment,
  detail_text varchar(30000) not null,
  detail_images varchar(5000) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into app_config(detail_text, detail_images) values('', '');
