INSERT INTO sales_db.public.products SELECT * FROM copy.products_bu;

INSERT INTO sales_db.public.payouts SELECT * FROM copy.payouts_bu;
