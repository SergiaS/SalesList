DELETE FROM sales_db.public.user_roles;
DELETE FROM sales_db.public.users;
DELETE FROM sales_db.public.payouts;
DELETE FROM sales_db.public.products;
ALTER SEQUENCE sales_db.public.user_seq RESTART WITH 104;
ALTER SEQUENCE sales_db.public.payouts_seq RESTART WITH 1500;
ALTER SEQUENCE sales_db.public.products_seq RESTART WITH 250;

INSERT INTO sales_db.public.users SELECT * FROM copy.users_bu;
INSERT INTO sales_db.public.user_roles SELECT * FROM copy.user_roles_bu;
INSERT INTO sales_db.public.products SELECT * FROM copy.products_bu;
INSERT INTO sales_db.public.payouts SELECT * FROM copy.payouts_bu;
