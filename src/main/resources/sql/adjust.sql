create table app_config_foot_link (
  id int primary key auto_increment,
  text char(50) not null,
  link char(200) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table app_config_foot_link add column ranking int unique not null;
