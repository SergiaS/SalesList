CREATE SCHEMA `sales_list` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `sales_list`.`sales` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_time` DATETIME NOT NULL,
  `title` VARCHAR(200) NOT NULL,
  `market_place` VARCHAR(45) NOT NULL,
  `delivery_service` VARCHAR(45) NOT NULL,
  `payment_method` VARCHAR(45) NOT NULL,
  `is_my_packing` TINYINT NOT NULL,
  `is_own` TINYINT NOT NULL,
  `notes` VARCHAR(200) NULL,
  `order_status` VARCHAR(45) NOT NULL,
  `sold_at_price` DOUBLE NOT NULL,
  `sold_including_expenses` DOUBLE NOT NULL,
  `payout_for_cooperation` DOUBLE NULL,
  `payout_percentage` DOUBLE NULL,
  `my_profit` DOUBLE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
