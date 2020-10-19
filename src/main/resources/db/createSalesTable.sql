CREATE SCHEMA `sales_list` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `sales_list`.`sales` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_time` DATETIME NOT NULL,
  `title` VARCHAR(200) NOT NULL,
  `market_place` VARCHAR(45) NOT NULL,
  `delivery_service` VARCHAR(45) NOT NULL,
  `payment_method` VARCHAR(45) NOT NULL,
  `order_status` VARCHAR(45) NOT NULL,
  `spent` INT DEFAULT 0 NOT NULL,
  `sold_at_price` DOUBLE NOT NULL,
  `payout_percentage` DOUBLE NULL,
  `profit` DOUBLE DEFAULT 0 NOT NULL,
  `notes` VARCHAR(200) NULL,
  `is_payout_paid` BOOLEAN DEFAULT FALSE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
