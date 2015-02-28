alter table commodity modify column url char(200);
alter table commodity modify column buy_url char(200);
alter table commodity add unique(serial_number);
alter table commodity add unique(show_id);
