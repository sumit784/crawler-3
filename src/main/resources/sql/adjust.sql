alter table category add column ranking not null;
update category set ranking=id;
alter table category add unique(ranking);
alter table category_branch add unique(ranking);
