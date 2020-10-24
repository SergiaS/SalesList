DROP TABLE IF EXISTS backup.sales_copy;
DROP TABLE IF EXISTS backup.payouts_copy;

DROP SCHEMA IF EXISTS backup;


CREATE SCHEMA backup DEFAULT CHARACTER SET utf8;

CREATE TABLE backup.sales_copy
    LIKE sales_list.sales;
INSERT backup.sales_copy
SELECT *
FROM sales_list.sales;

CREATE TABLE backup.payouts_copy
    LIKE sales_list.payouts;
INSERT backup.payouts_copy
SELECT *
FROM sales_list.payouts;
