INSERT INTO public.users (name, email, password, registered, enabled, profit_per_day)
VALUES ('Admin', 'admin@gmail.com', 'qwerty', '2019-08-07 10:00:00', TRUE, 500),
       ('JAG63', 'vovan@gmail.com', 'alco', '2019-08-10 10:00:00', TRUE, 300),
       ('CAT66', 'cat@gmail.com', 'mew', '2020-01-10 10:00:00', TRUE, 100),
       ('JUV91', 'juv@gmail.com', 'fcjuv', '2020-11-20 10:00:00', TRUE, 100),
       ('SK88', 'sk88@gmail.com', 'password', '2020-11-20 10:00:00', TRUE, 250);


INSERT INTO public.user_roles (user_id, role)
VALUES (100, 'ADMIN'),
       (101, 'JAG63'),
       (102, 'CAT66'),
       (103, 'JUV91'),
       (104, 'SK88');


INSERT INTO public.products (user_id,
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
VALUES
-- (101, '2019-08-07 10:00:00', 'Шоты/стопки/рюмки Jagermeister 6 шт. Черные (Новые!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 180, 6, 40, 72, 108, 'Покупка упаковки'),
-- (104, '2019-08-07 10:00:00', 'Велофара (свет) передняя Lezyne Micro Drive 450XL Black (как новая!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 710, 0, 0, 0, 710, ''),
-- (101, '2019-08-07 10:00:00', 'Очки Jagermeister Black (Новые!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 195, 6, 40, 78, 117, 'Покупка упаковки'),
-- (104, '2019-08-09 10:00:00', 'Защита колена и голени SixSixOne (661) Comp Knee, размер M, White', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 740, 0, 0, 0, 740, 'Покупка упаковки'),
-- (101, '2019-08-10 10:00:00', 'Очки Jagermeister Black (Новые!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 195, 6, 40, 78, 117, 'Покупка упаковки'),
-- (102, '2019-08-11 10:00:00', 'Now Foods Глицин 1000 мг 100 капсул (новая, закрытая банка)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 291, 6, 50, 145.5, 145.5, 'Покупка упаковки'),
-- (101, '2019-08-12 10:00:00', 'Очки Jagermeister Black (Новые!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 195, 6, 40, 78, 117, 'Покупка упаковки'),
-- (104, '2019-08-14 10:00:00', 'Смазка цепи для сухой погоды Muc-Off Dry Lube 120 мл (новая!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 286, 0, 0, 0, 286, 'Покупка упаковки'),
-- (104, '2019-08-15 10:00:00', 'Тестер воды Xiaomi Mi TDS Pen', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 160, 0, 0, 0, 160, 'Использована скидка НП (-6 грн.)'),
(101, '2020-12-13 15:24', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!) - 3 шт.', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,600,40,240,360,''),
(101, '2020-12-13 15:24', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,215,40,86,129,''),
(101, '2020-12-13 15:24', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,215,40,86,129,''),
(101, '2020-11-08 14:49', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,215,40,86,129,''),
(101, '2020-11-06 15:15', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,215,40,86,129,''),
(104, '2020-10-21 20:40', 'Медь Twinlab 2 мг 100 капсул (Новая!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,130,0,0,130,''),
(104, '2020-10-19 14:16', 'Джемпер мужской флисовый Pearl Izumi размер М Салатовый', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,350,0,0,350,''),
(101, '2020-09-25 16:55', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!) - 2 шт.', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,350,40,140,210,'Карта клиента НП (+2 грн.)'),
(101, '2020-09-20 16:52', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(101, '2020-09-20 16:51', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!) - 2 шт.', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,350,40,140,210,'Карта клиента НП (+2 грн.)'),
(104, '2020-09-17 16:41', 'Набор Велокомпьютер GPS с фарой Lezyne Mega XL Smart Loaded', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 3528.87,5500,0,0,1971.13,'eBay'),
(101, '2020-09-17 16:31', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(101, '2020-09-16 16:21', 'Солнцезащитные очки Jagermeister Green (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,135,40,54,81,'Карта клиента НП (+2 грн.)'),
(101, '2020-09-16 16:11', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(101, '2020-09-14 16:09', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(101, '2020-09-08 16:08', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(101, '2020-09-08 16:07', 'Солнцезащитные очки Jagermeister Green (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,135,40,54,81,''),
(101, '2020-09-07 16:06', 'Новые Стаканы (бокалы) Baileys с золотым лого - 7 шт.', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,350,40,140,210,'Карта клиента НП (+2 грн.)'),
(101, '2020-09-07 16:05', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(101, '2020-09-06 16:04', 'Солнцезащитные очки Jagermeister Green (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,135,40,54,81,''),
(101, '2020-09-06 16:03', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(104, '2020-09-03 16:02', 'Новые Фляги Elite SuperCorsa 750 мл Цена за 2 штуки!', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 290,320,0,0,30,''),
(101, '2020-09-02 16:01', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!) - 2 шт.', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,350,40,140,210,''),
(101, '2020-08-30 13:24', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!) - 5 шт.', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,750,40,300,450,'Карта клиента НП (+2 грн.)'),
(101, '2020-08-26 13:12', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(101, '2020-08-26 13:11', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,195,40,78,117,''),
(101, '2020-08-23 13:09', 'Новые Стаканы (бокалы) Baileys с золотым лого - 2 шт.', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,140,40,56,84,'Карта клиента НП (+2 грн.)'),
(101, '2020-08-21 13:08', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(101, '2020-08-18 13:07', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!) - 3 шт.', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,500,40,200,300,'Карта клиента НП (+2 грн.)'),
(101, '2020-08-09 13:07', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'DENIED', 0,0,0,0,0,'Чувак отказался, взял только зеленые'),
(101, '2020-08-09 13:06', 'Солнцезащитные очки Jagermeister Green (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,150,40,60,90,''),
(101, '2020-08-05 13:05', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(101, '2020-08-05 13:04', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,195,40,78,117,''),
(104, '2020-08-04 13:03', 'Новые Фляги Elite Corsa 550 мл Цена за 2 штуки!', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 245,270,0,0,25,''),
(104, '2020-07-30 13:41', 'Велоодежда джерси Eliel', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 155,198,0,0,43,'Карта клиента НП (+2 грн.)'),
(101, '2020-07-29 13:31', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(101, '2020-07-25 13:21', 'Новые фирменные кружки Captain Morgan - 2 шт.', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,100,40,40,60,''),
(101, '2020-07-23 13:11', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,190,40,76,114,'Карта клиента НП (+2 грн.)'),
(104, '2020-07-21 13:09', 'Новые Фляги Elite Super Corsa 750 мл Цена за 2 штуки!', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 295,300,0,0,5,''),
(101, '2020-07-16 13:08', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!) - 2 шт.', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,330,40,132,198,'Карта клиента НП (+2 грн.)'),
(101, '2020-07-16 13:07', 'Новые Шоты Рюмки Стопки Jose Cuervo', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,330,40,132,198,'Карта клиента НП (+2 грн.)'),
(101, '2020-07-16 13:06', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!) - 2 шт.', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,350,40,140,210,'Карта клиента НП (+2 грн.)'),
(101, '2020-07-15 13:05', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,190,40,76,114,''),
(101, '2020-07-06 13:04', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,190,40,76,114,''),
(101, '2020-07-04 13:03', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!) - 2 шт. + Солнцезащитные очки Jagermeister Green (Новые, в упаковке!) - 1 шт.', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,500,40,200,300,'Карта клиента НП (+2 грн.)'),
(101, '2020-07-03 13:02', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,190,40,76,114,''),
(101, '2020-07-02 13:01', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!) - 2 шт.', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,350,40,140,210,'Карта клиента НП (+2 грн.)'),
(101, '2020-06-30 12:59', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,190,40,76,114,'Карта клиента НП (+2 грн.)'),
(101, '2020-06-28 12:58', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,190,40,76,114,''),
(101, '2020-06-28 12:57', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,180,40,72,108,'Карта клиента НП (+2 грн.)'),
(104, '2020-06-22 12:56', 'Замковые эксцентрики PZ Racing CR5.3Q MTB Black б/у', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,340,0,0,340,''),
(101, '2020-06-11 12:55', 'Новые Стаканы для виски Johnnie Walker - 6 шт.', 'OLX', 'COLLECTION_IN_PERSON', 'CASH', 'SUCCESS', 0,350,40,140,210,'100 ПРОДАЖ'),
(101, '2020-06-09 12:54', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(101, '2020-06-09 12:53', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,195,40,78,117,''),
(101, '2020-06-08 12:52', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!) - 2 шт.', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,350,40,140,210,'Карта клиента НП (+2 грн.)'),
(101, '2020-06-08 12:51', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(101, '2020-06-05 12:50', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!) - 2 шт.', 'OLX', 'COLLECTION_IN_PERSON', 'CASH', 'SUCCESS', 0,350,40,140,210,''),
(101, '2020-05-02 12:50', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!) - 3 шт', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,500,40,200,300,'Карта клиента НП (+2 грн.)'),
(101, '2020-05-02 12:42', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!) - 3 шт', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,500,40,200,300,'Карта клиента НП (+2 грн.)'),
(104, '2020-05-02 12:40', 'Чехол GIYO для инструментов под флягодержатель Цена за 2 шт', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 200,250,0,0,50,'Карта клиента НП (+2 грн.)'),
(101, '2020-05-02 12:39', 'Новые Стаканы Johnnie Walker - 2 шт', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,160,40,64,96,''),
(101, '2020-05-02 12:38', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,195,40,78,117,''),
(101, '2020-05-02 12:37', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,195,40,78,117,''),
(104, '2020-05-02 12:34', 'Велокомпьютер/навигатор GPS Lezyne Mega XL Metallic Red', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 3728.44,4999,0,0,1270.56,'Карта клиента НП (+2 грн.); Amazon; Предоплата 100 грн.'),
(104, '2020-05-02 12:32', 'Умный задний вело фонарь фара Meroca', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 168.58,365,0,0,196.42,'Карта клиента НП (+2 грн.); Aliexpress; Реклама 34.3 грн.'),
(104, '2020-05-02 12:31', 'Умный задний вело фонарь фара Meroca', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 134.28,365,0,0,230.72,'Aliexpress'),
(104, '2020-05-02 12:31', 'Умный задний вело фонарь фара Meroca', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 134.28,365,0,0,230.72,'Aliexpress'),
(104, '2020-05-02 12:30', 'Умный задний вело фонарь фара Meroca', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 134.28,365,0,0,230.72,'Aliexpress'),
(104, '2020-05-02 12:28', 'Умный задний вело фонарь фара Meroca', 'OLX', 'COLLECTION_IN_PERSON', 'CASH', 'SUCCESS', 134.28,365,0,0,230.72,'Aliexpress'),
(104, '2020-05-02 12:27', 'Умный задний вело фонарь фара Meroca', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 134.28,365,0,0,230.72,'Aliexpress'),
(104, '2020-05-02 12:25', 'Умный задний вело фонарь фара Meroca', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 134.28,365,0,0,230.72,'Aliexpress'),
(104, '2020-05-02 12:12', 'Умный задний вело фонарь фара Meroca', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 134.28,365,0,0,230.72,'Aliexpress'),
(101, '2020-05-02 12:02', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,195,40,78,117,''),
(104, '2020-04-28 15:31', 'Умный задний вело фонарь фара Meroca', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 134.28,365,0,0,230.72,'Aliexpress'),
(101, '2020-04-24 13:15', 'Новые Стаканы для виски Johnnie Walker - 4 шт.', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,320,40,128,192,''),
(104, '2020-04-23 13:14', 'Новая Передняя фара (свет) Lezyne Lite Drive 1000XL Black', 'CONTACTS', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 1004.83,1444,0,0,439.17,'Карта клиента НП (+2 грн.); eBay'),
(104, '2020-04-23 13:13', 'Велокомпьютер/навигатор GPS Lezyne Super Pro + Front Mount', 'OLX', 'COLLECTION_IN_PERSON', 'CASH', 'SUCCESS', 1676.67,3000,0,0,1323.33,'Встреча'),
(104, '2020-04-23 13:12', 'Велокомпьютер/навигатор GPS Lezyne Super Pro', 'OLX', 'COLLECTION_IN_PERSON', 'CASH', 'SUCCESS', 1977.98,3000,0,0,1022.02,'Встреча'),
(101, '2020-04-23 13:10', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(101, '2020-04-18 13:09', 'Новые Стаканы Johnnie Walker - 5 шт.', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,325,40,130,195,'Карта клиента НП (+2 грн.)'),
(101, '2020-04-14 13:08', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!) - 2 шт.', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,350,40,140,210,'Карта клиента НП (+2 грн.)'),
(101, '2020-04-13 13:07', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(104, '2020-04-13 13:06', 'Комплект велофар Lezyne 800XL & Strip Drive 150 Pair', 'CONTACTS', 'COLLECTION_IN_PERSON', 'CASH', 'SUCCESS', 1244.02,1800,0,0,555.98,'eBay'),
(101, '2020-04-11 13:06', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!) - 2 шт.', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,350,40,140,210,'Карта клиента НП (+2 грн.)'),
(101, '2020-04-10 13:06', 'Солнцезащитные очки Jagermeister Green (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'PARTIAL_PREPAYMENT', 'SUCCESS', 0,160,40,64,96,'Карта клиента НП (+2 грн.); Предоплата 100 грн'),
(101, '2020-04-10 13:05', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!) - 2 шт.', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,350,40,140,210,'Карта клиента НП (+2 грн.)'),
(101, '2020-04-05 13:04', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,195,40,78,117,''),
(101, '2020-04-02 13:03', 'Новые Стаканы Johnnie Walker - 4 шт.', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,260,40,104,156,''),
(104, '2020-04-02 13:01', 'Велокомпьютер Lezyne Macro GPS Black', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 1248.7,1500,0,0,251.3,'eBay'),
(104, '2020-04-01 13:01', 'Велокомпьютер Lezyne Macro GPS Black', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'DENIED', 0,0,0,0,0,'Чувак завтыкал'),
(101, '2020-03-01 14:30', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(101, '2020-03-01 14:28', 'Новые Стаканы Johnnie Walker - 5 шт.', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,250,40,100,150,''),
(101, '2020-03-01 14:25', 'Новые Стаканы Johnnie Walker - 12 шт.', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,600,40,240,360,'Карта клиента НП (+2 грн.)'),
(104, '2020-03-01 14:22', 'Регулируемое зеркало заднего вида на шлем', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,95,0,0,95,'Карта клиента НП (+2 грн.)'),
(101, '2020-03-01 14:20', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(101, '2020-03-01 14:19', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(101, '2020-03-01 14:18', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,195,40,78,117,'Карта клиента НП (+2 грн.)'),
(104, '2020-03-01 14:17', 'Датчик каденса и скорости для Garmin, Lezyne, Bryton', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,650,0,0,650,'Карта клиента НП (+2 грн.)'),
(101, '2020-03-01 14:16', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,195,40,78,117,''),
(101, '2020-03-01 14:15', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,195,40,78,117,''),
(101, '2020-03-01 14:10', 'Солнцезащитные очки Jagermeister Black-Orange (Новые, в упаковке!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,195,40,78,117,''),
(104, '2020-03-01 14:09', 'Подседельная сумка Topeak Aero Wedge Large', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,400,0,0,400,''),
(104, '2020-03-01 14:09', 'Новые Флягодержатели Elite Custom Race Black-Red 2 шт.', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 380,500,0,0,120,'Карта клиента НП (+2 грн.); BikeInn'),
(101, '2020-02-26 14:08', 'Футболка Eighty Eight (88) New York XL Вишневая', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,130,40,52,78,'Карта клиента НП (+2 грн.)'),
(104, '2020-02-22 14:06', 'Новая Передняя фара (свет) Lezyne Lite Drive 800XL Black', 'CONTACTS', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 1060.33,1100,0,0,39.67,''),
(104, '2020-02-14 14:04', 'Новая Передняя фара (свет) Lezyne Lite Drive 800XL Black', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 1060.33,1150,0,0,89.67,'Карта клиента НП (+2 грн.)'),
(101, '2020-01-19 13:00', 'Новые Стаканы Johnnie Walker - 5 шт.', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,350,40,140,210,'Карта клиента НП (+2 грн.)'),
(104, '2020-01-18 13:00', 'Новый Велокомпьютер GPS Garmin Edge 520 Plus + Чехол + Стекло!', 'OLX', 'COLLECTION_IN_PERSON', 'CASH', 'SUCCESS', 5945.23,6000,0,0,54.77,'eBay'),
(104, '2020-01-13 13:00', 'Съемник каретки для велосипеда Bike Hand YC-26BB (новый!)', 'OLX', 'COLLECTION_IN_PERSON', 'CASH', 'SUCCESS', 0,150,0,0,150,''),
(101, '2019-12-31 12:00', 'Новые фирменные кружки Captain Morgan', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,250,40,100,150,''),
(101, '2019-12-30 12:00', 'Новые Стаканы Captain Morgan Captain & Cola', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,300,40,120,180,'Карта клиента НП (+2 грн.)'),
(101, '2019-12-25 12:00', 'Новые фирменные кружки Captain Morgan', 'OLX', 'COLLECTION_IN_PERSON', 'CASH', 'SUCCESS', 0,60,40,24,36,''),
(104, '2019-12-19 12:00', 'Смазки цепи Muc-Off для сухой/мокрой погоды Dry/Wet Lube 120 мл', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 147.02,265,0,0,117.98,'Wiggle 29.09.2019'),
(104, '2019-12-18 12:00', 'Комплект велофар Lezyne Macro 800XL & Micro Drive Pair Black', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 1376.33,1777,0,0,400.67,''),
(104, '2019-12-15 12:00', 'Новая Передняя фара (свет) Lezyne Lite Drive 800XL Black', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 1060.33,1150,0,0,89.67,''),
(104, '2019-11-11 12:02', 'Смазки цепи Muc-Off для сухой/мокрой погоды Dry/Wet Lube 120 мл', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 147.02,245,0,0,97.98,'Использована скидка НП (-6 грн.); Wiggle 29.09.2019'),
(103, '2019-11-11 12:00', 'СРОЧНО! Футболка Ecko Premio 15, размер М, Темно-синяя', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,150,40,60,90,'Карта клиента НП (+2 грн.)'),
(101, '2019-10-31 12:00', 'Солнцезащитные очки Jagermeister Green (Новые, в упаковке!) 2 шт.', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,270,40,108,162,'Карта клиента НП (+2 грн.)'),
(101, '2019-10-25 13:00', 'Новые Шоты Рюмки Стопки Текила Sauza (Сауза) 3 шт.', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,165,40,66,99,'Использована скидка НП (-6 грн.)'),
(101, '2019-10-21 13:00', 'Стаканы (бокалы) Baileys с золотым лого (Новые!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,50,40,20,30,''),
(102, '2019-10-11 14:00', 'СРОЧНО! Сканер Epson Perfection 1270 USB А4', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,415,40,166,249,''),
(104, '2019-10-11 13:00', 'Съемник шатунов для велосипеда Bike Hand YC-215CB (б/у)', 'OLX', 'COLLECTION_IN_PERSON', 'CASH', 'SUCCESS', 0,108,0,0,108,''),
(101, '2019-10-08 13:00', 'Набор бокалов для вина Pasabahce Service Line Casablanka 51258 12 шт.', 'OLX', 'COLLECTION_IN_PERSON', 'CASH', 'SUCCESS', 0,300,40,120,180,''),
(101, '2019-10-06 13:00', 'Стаканы (бокалы) Baileys с золотым лого (Новые!) 12 шт.', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,700,40,280,420,''),
(101, '2019-09-30 15:00', 'Очки Jagermeister Black-Orange (Новые!)', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,150,40,60,90,'Тот же заказ'),
(101, '2019-09-30 13:00', 'Очки Jagermeister Black-Orange (Новые!)', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,166,40,66.4,99.6,'Карта клиента НП (+2 грн.)'),
(104, '2019-09-25 13:00', 'СРОЧНО! Велоформа (джерси + трусы с лямками) Monton Pro Sky, размер L', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 480,1100,0,0,620,'Покупал форму 5 лет назад'),
(104, '2019-09-24 13:00', 'СРОЧНО! Велокомпьютер Lezyne Mega C GPS Black', 'OLX', 'NOVA_POST', 'PARTIAL_PREPAYMENT', 'SUCCESS', 3764.92,4650,0,0,885.08,'Карта клиента НП (+2 грн.); Предоплата 200 грн; eBay'),
(104, '2019-09-19 14:00', 'Новые зауженные мужские джинсы 16 Revolution 88 M8315 W34 x L32', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,200,0,0,200,'Карта клиента НП (+2 грн.)'),
(104, '2019-09-19 13:00', 'Б/У Велоштаны демисезонные Cannondale с ветрозащитой, размер L', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,170,0,0,170,'Карта клиента НП (+2 грн.)'),
(103, '2019-09-16 13:00', 'Теодор Драйзер - Финансист, Сестра Керри (Цена за 2 книги!)', 'OLX', 'COLLECTION_IN_PERSON', 'CASH', 'SUCCESS', 0,50,0,0,50,''),
(101, '2019-09-13 13:00', 'Очки Jagermeister Black-Orange (Новые!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,172,40,68.8,103.2,'Карта клиента НП (+2 грн.)'),
(101, '2019-09-10 13:00', 'Очки Jagermeister Black-Orange (Новые!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,172,40,68.8,103.2,''),
(101, '2019-09-08 13:00', 'Стаканы (бокалы) Baileys с золотым лого 6 шт. в упаковке (Новые!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,350,40,140,210,'Карта клиента НП (+2 грн.)'),
(101, '2019-09-07 13:00', 'Очки Jagermeister Black-Orange (Новые!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,160,40,64,96,''),
(104, '2019-08-31 13:00', 'Силиконовый чехол для GoPro Hero5/6/7 Blue (Новый в пакете!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,95,0,0,95,'Карта клиента НП (+2 грн.)'),
(104, '2019-08-27 13:00', 'СРОЧНО! Задний переключатель Shimano ACERA RD-M390 Black (б/у)', 'OLX', 'COLLECTION_IN_PERSON', 'CASH', 'SUCCESS', 0,300,0,0,300,'Покупка упаковки'),
(101, '2019-08-22 13:00', 'Очки Jagermeister Black-Orange (Новые!)', 'OLX', 'COLLECTION_IN_PERSON', 'CASH', 'SUCCESS', 0,176,40,70.4,105.6,'Покупка упаковки'),
(101, '2019-08-20 13:00', 'Очки Jagermeister Black-Orange (Новые!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,196,40,78.4,117.6,'Карта клиента НП (+2 грн.)'),
(104, '2019-08-15 13:00', 'Тестер воды Xiaomi Mi TDS Pen', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,160,0,0,160,'Использована скидка НП (-6 грн.)'),
(104, '2019-08-14 13:00', 'Смазка цепи для сухой погоды Muc-Off Dry Lube 120 мл (новая!)', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,286,0,0,286,'Покупка упаковки'),
(101, '2019-08-12 13:00', 'Очки Jagermeister Black (Новые!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 6,195,40,75.6,113.4,'Покупка упаковки'),
(104, '2019-08-11 13:00', 'Now Foods Глицин 1000 мг 100 капсул (новая, закрытая банка)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 6,291,0,0,285,'Покупка упаковки'),
(101, '2019-08-10 13:00', 'Очки Jagermeister Black (Новые!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 6,195,40,75.6,113.4,'Покупка упаковки'),
(104, '2019-08-09 13:00', 'Защита колена и голени SixSixOne (661) Comp Knee, размер M, White', 'OLX', 'NOVA_POST', 'CASH_ON_DELIVERY', 'SUCCESS', 0,740,0,0,740,'Покупка упаковки'),
(101, '2019-08-07 16:03', 'Очки Jagermeister Black (Новые!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 6,180,40,69.6,104.4,'Покупка упаковки'),
(104, '2019-08-07 13:00', 'Велофара (свет) передняя Lezyne Micro Drive 450XL Black (как новая!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 0,710,0,0,710,''),
(101, '2019-08-07 12:41', 'Шоты/стопки/рюмки Jagermeister 6 шт. Черные (Новые!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 8,418,40,164,246,'Покупка упаковки'),
(101, '2019-08-07 12:17', 'Очки Jagermeister Black-Orange (Новые!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 6,185,40,71.6,107.4,'Покупка упаковки'),
(101, '2019-08-06 15:08', 'Очки Jagermeister Глянцевые Черные (старого образца)', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,106,40,42.4,63.6,'Карта клиента НП (+2 грн.)'),
(103, '2019-08-05 16:42', 'Doctors Best Гиалуроновая кислота и сульфат хондроитина 180 капсул', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0,310,0,0,310,'Карта клиента НП (+2 грн.)'),
(101, '2019-08-04 14:55', 'Очки Jagermeister Black-Orange (Новые!)', 'OLX', 'NOVA_POST', 'OLX_DELIVERY', 'SUCCESS', 6,185,40,71.6,107.4,'Покупка упаковки')
;
