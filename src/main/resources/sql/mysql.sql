create database IF NOT EXISTS `test`;

use test;

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id`  INT NOT NULL,
  `user_name` VARCHAR(300),
  PRIMARY KEY (`user_id`)
);

DROP TABLE IF EXISTS `t_order_0`;
CREATE TABLE `t_order_0` (
  `order_id` INT NOT NULL,
  `user_id`  INT NOT NULL,
  `order_amount` INT NOT NULL default 0,
  PRIMARY KEY (`order_id`)
);

DROP TABLE IF EXISTS `t_order_1`;
CREATE TABLE `t_order_1` (
  `order_id` INT NOT NULL,
  `user_id`  INT NOT NULL,
  `order_amount` INT NOT NULL default 0,
  PRIMARY KEY (`order_id`)
);

DROP TABLE IF EXISTS `t_order_item_0`;
CREATE TABLE `t_order_item_0` (
  `item_id`  INT NOT NULL,
  `order_id` INT NOT NULL,
  `user_id`  INT NOT NULL,
  PRIMARY KEY (`item_id`)
);

DROP TABLE IF EXISTS `t_order_item_1`;
CREATE TABLE `t_order_item_1` (
  `item_id`  INT NOT NULL,
  `order_id` INT NOT NULL,
  `user_id`  INT NOT NULL,
  PRIMARY KEY (`item_id`)
);
