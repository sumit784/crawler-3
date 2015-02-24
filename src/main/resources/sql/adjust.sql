drop table branch;
CREATE TABLE `branch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(50) DEFAULT NULL,
  `logo` char(200) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table appraise_group;
CREATE TABLE `appraise_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_id` int(11) NOT NULL,
  `content` char(100) DEFAULT NULL,
  `positive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
