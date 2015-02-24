create table appraise_group(
id int primary key auto_increment,
commodity_id int not null,
content char(100)
);

alter table commodity_picture add column detail boolean default false;