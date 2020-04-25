DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
    `user_id` int NOT NULL AUTO_INCREMENT,
    `account` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    PRIMARY KEY (`user_id`)
);


DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
    `id` int NOT NULL AUTO_INCREMENT,
    `account` varchar(255) NOT NULL,
    `follower_id` varchar(255) NOT NULL,
    `followee_id` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);
ALTER TABLE  `follow` ADD CONSTRAINT `FK_follower`
    FOREIGN KEY (`follower_id`) REFERENCES `user_info` (`user_id`)
        ON DELETE CASCADE;
ALTER TABLE  `follow` ADD CONSTRAINT `FK_followee`
    FOREIGN KEY (`followee_id`) REFERENCES `user_info` (`user_id`)
        ON DELETE CASCADE;


DROP TABLE IF EXISTS `timely_news`;
CREATE TABLE `timely_news` (
    `id` int NOT NULL AUTO_INCREMENT,
    `time` bigint(20) NOT NULL,
    `news` varchar(255) NOT NULL,
    `user_id` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);
ALTER TABLE  `timely_news` ADD CONSTRAINT `FK_user`
    FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
        ON DELETE CASCADE;
