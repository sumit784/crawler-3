
use crawler;
alter table price_record add column grab_time datetime not null;
update price_record set grab_time=now();