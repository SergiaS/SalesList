DROP TABLE IF EXISTS copy.users_bu CASCADE;
DROP TABLE IF EXISTS copy.payments_bu CASCADE;
DROP TABLE IF EXISTS copy.products_bu CASCADE;
DROP TABLE IF EXISTS copy.users_bu CASCADE;

DROP SCHEMA IF EXISTS copy CASCADE;


CREATE SCHEMA copy;

-- CREATE TABLE copy.users_bu (LIKE public.users INCLUDING ALL);
-- INSERT INTO copy.users_bu SELECT * FROM public.users;

CREATE TABLE copy.users_bu AS SELECT * FROM public.users;
CREATE TABLE copy.user_roles_bu AS SELECT * FROM public.user_roles;
CREATE TABLE copy.payouts_bu AS SELECT * FROM public.payouts;
CREATE TABLE copy.products_bu AS SELECT * FROM public.products;
