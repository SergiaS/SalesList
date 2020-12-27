DELETE FROM user_roles;
DELETE FROM payouts;
DELETE FROM products;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 101;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@ukr.net', 'password'),
       ('Admin', 'admin@gmail.com', 'admin')
--        ('CAT66', 'cat@gmail.com', 'mew'),
--        ('JUV91', 'juv@gmail.com', 'fcjuv'),
--        ('SK88', 'sk88@gmail.com', 'password')
;

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 101),
       ('ADMIN', 102)
--        ('USER', 'CAT66'),
--        ('USER', 'JUV91'),
--        ('USER', 'SK88')
       ;


INSERT INTO products (user_id,
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
VALUES (101, '2020-02-14 08:47:00', 'Glasses', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 215, 0, 0, 0, 215, ''),
       (101, '2020-02-14 18:32:00', 'Glasses', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 215, 0, 0, 0, 215, ''),
       (101, '2020-02-15 21:40:00', 'Glasses', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 215, 0, 0, 0, 215, ''),
       (101, '2020-02-16 08:47:00', 'Glasses', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 215, 0, 0, 0, 215, ''),
       (101, '2020-02-19 11:22:00', 'Glasses', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 215, 0, 0, 0, 215, ''),
       (102, '2020-02-21 13:31:00', 'Omega', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 130, 0, 0, 0, 130, 'Карта клиента НП (+2 грн.)'),
       (102, '2020-02-25 13:21:00', 'Headphones', 'CONTACTS', 'COLLECTION_IN_PERSON', 'CASH', 'SUCCESS', 400, 0, 0, 0, 400, 'eBay')
;
