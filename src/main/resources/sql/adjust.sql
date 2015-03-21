update branch set logo=replace(logo, '/var/ftp/','/var/www/html/images/');
update branch set square_logo=replace(square_logo, '/var/ftp/','/var/www/html/images/');
update branch set poster=replace(poster, '/var/ftp/','/var/www/html/images/');
update commodity_picture set url=replace(url, '/var/ftp/','/var/www/html/images/');
update index_logo set path=replace(path, '/var/ftp/','/var/www/html/images/');

/*
update branch set logo=replace(logo, '/var/www/html/images','/var/www/html/images/');
update branch set square_logo=replace(square_logo, '/var/www/html/images', '/var/www/html/images/');
update branch set poster=replace(poster, '/var/www/html/images','/var/www/html/images/');
update commodity_picture set url=replace(url,'/var/www/html/images' ,'/var/www/html/images/');
*/
