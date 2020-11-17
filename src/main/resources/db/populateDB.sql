DELETE FROM user_roles;
DELETE FROM products;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (nickname, password)
VALUES ('User', 'password'),
       ('Admin', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO products (date_time, title, market_place, delivery_service, payment_method, order_status, sold_at_price, spent, payout_percentage, payout_currency, profit, notes, user_id)
VALUES ('2020-10-31 8:18:00', 'Glasses', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 100, 0, 40, 40, 60, '', 100000),
       ('2020-10-31 10:43:00', 'Chain', 'FACEBOOK', 'JUSTIN', 'COMPLETE_PREPAYMENT', 'SUCCESS', 350, 150, 0, 0, 200, '', 100000),
       ('2020-10-31 13:24:00', 'Glasses', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 400, 0, 40, 160, 240, '', 100000),
       ('2020-11-01 7:51:00', 'Glasses', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 100, 0, 40, 40, 60, '', 100000),
       ('2020-11-01 9:17:00', 'Bottles', 'SITE', 'NOVA_POST', 'SAVE_SERVICE', 'SUCCESS', 600, 270, 0, 0, 330, '', 100000),
       ('2020-11-01 14:30:00', 'Glasses', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 100, 0, 40, 40, 60, '', 100000),
       ('2020-11-01 10:30:00', 'Admin - glasses', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 100, 0, 40, 40, 60, '', 100001),
       ('2020-11-01 17:30:00', 'Admin - glasses', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 100, 0, 40, 40, 60, '', 100001);
