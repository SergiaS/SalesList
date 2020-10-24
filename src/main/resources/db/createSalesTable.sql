CREATE SCHEMA `sales_list` DEFAULT CHARACTER SET utf8;

CREATE TABLE `sales_list`.`sales`
(
    `id`                int(11)      NOT NULL AUTO_INCREMENT,
    `date_time`         datetime     NOT NULL,
    `title`             varchar(200) NOT NULL,
    `market_place`      varchar(45)  NOT NULL,
    `delivery_service`  varchar(45)  NOT NULL,
    `payment_method`    varchar(45)  NOT NULL,
    `order_status`      varchar(45)  NOT NULL,
    `spent`             double       NOT NULL DEFAULT '0',
    `sold_at_price`     double       NOT NULL,
    `payout_percentage` int(11)               DEFAULT NULL,
    `payout_currency`   double                DEFAULT NULL,
    `profit`            double                DEFAULT NULL,
    `notes`             varchar(200)          DEFAULT NULL,
    `is_payout_paid`    tinyint(1)   NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8;


CREATE TABLE `payouts`
(
    `id`         int(11)   NOT NULL AUTO_INCREMENT,
    `product_id` int(11)        DEFAULT NULL,
    `date_time`  timestamp NULL DEFAULT NULL,
    `amount`     double         DEFAULT NULL,
    `notes`      varchar(200)   DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `product_id` (`product_id`),
    CONSTRAINT `payouts_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `sales` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8;
