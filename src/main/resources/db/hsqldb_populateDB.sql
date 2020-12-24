DELETE FROM PUBLIC_DB.user_roles;
DELETE FROM PUBLIC_DB.payouts;
DELETE FROM PUBLIC_DB.products;
DELETE FROM PUBLIC_DB.users;
ALTER SEQUENCE PUBLIC_DB.global_seq RESTART WITH 100000;

INSERT INTO PUBLIC_DB.users (name, email, password, registered, enabled, profit_per_day)
VALUES ('User', 'user@ukr.net', 'password', '2019-08-10 10:00:00', TRUE, 300),
       ('Admin', 'admin@gmail.com', 'qwerty', '2019-08-07 10:00:00', TRUE, 500)
--        ('CAT66', 'cat@gmail.com', 'mew', '2020-01-10 10:00:00', TRUE, 100),
--        ('JUV91', 'juv@gmail.com', 'fcjuv', '2020-11-20 10:00:00', TRUE, 100),
--        ('SK88', 'sk88@gmail.com', 'password', '2020-11-20 10:00:00', TRUE, 250)
;


INSERT INTO PUBLIC_DB.user_roles (user_id, role)
VALUES (100000, 'ADMIN'),
       (100001, 'JAG63'),
       (100002, 'CAT66'),
       (100003, 'JUV91'),
       (100004, 'SK88');


INSERT INTO PUBLIC_DB.products (user_id,
                                date_time,
                                title,
                                market_place,
                                delivery_service,
                                payment_method,
                                order_status,
                                sold_at_price,
                                spent,
                                payout_percentage,
                                payout_currency,
                                profit,
                                notes)
VALUES (100001, '2020-02-14 08:47:00', 'Glasses', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 215, 0, 40, 89, 129, ''),
       (100001, '2020-02-14 18:32:00', 'Glasses', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 215, 0, 40, 89, 129, ''),
       (100001, '2020-02-15 21:40:00', 'Glasses', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 215, 0, 40, 89, 129, ''),
       (100001, '2020-02-16 08:47:00', 'Glasses', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 215, 0, 40, 89, 129, ''),
       (100001, '2020-02-19 11:22:00', 'Glasses', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 215, 0, 40, 89, 129, ''),
       (100004, '2020-02-21 13:31:00', 'Omega', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 130, 0, 0, 0, 130, 'Карта клиента НП (+2 грн.)'),
       (100004, '2020-02-25 13:21:00', 'Headphones', 'CONTACTS', 'CASH', 'OLX_DELIVERY', 'SUCCESS', 400, 0, 0, 0, 400, 'eBay')
;
