alter table commodity drop column low_price;
alter table commodity drop column original_price;
alter table commodity add column in_low_price boolean not null default true;
/*delete from price_record where record_time='2015-03-27 00:00:00';*/
