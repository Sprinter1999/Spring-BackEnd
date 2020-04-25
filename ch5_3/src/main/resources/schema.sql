DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`
(
    `id`       bigint(20) NOT NULL AUTO_INCREMENT,
    `in_stock` bit(1)     NOT NULL,
    `name`     varchar(255)   DEFAULT NULL,
    `price`    decimal(19, 2) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`
(
    `sid`       bigint(20) NOT NULL AUTO_INCREMENT,
    `uid`       bigint(20) NOT NULL,
    `nickname`     varchar(255)   DEFAULT NULL,
    `content`     varchar(255)   DEFAULT NULL,
    PRIMARY KEY (`sid`)
);
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `uid`       bigint(20) NOT NULL AUTO_INCREMENT,
    `nickname`     varchar(255)   DEFAULT NULL,
    `password`    varchar(255) DEFAULT NULL,
    PRIMARY KEY (`uid`)
);
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend`
(
    `pid`       bigint(20) NOT NULL AUTO_INCREMENT,
    `uid`       bigint(20) DEFAULT NULL,
    `fid`       bigint(20) DEFAULT NULL,
    PRIMARY KEY (`pid`)
);
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT,
    `text`       varchar(255) DEFAULT NULL,
    `product_id` bigint(20)   DEFAULT NULL,
    PRIMARY KEY (`id`)
);
ALTER TABLE `comment`
    ADD CONSTRAINT `FK_product`
        FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
            ON DELETE CASCADE;