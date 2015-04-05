/*alter table app_config add column related_commodity_size smallint not null;*/
create table app_config_detail_image(
  id int primary key auto_increment,
  path char(200) not null,
  link char(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
