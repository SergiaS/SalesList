# 1
INSERT INTO sales_list.sales (date_time,
                              title,
                              market_place,
                              delivery_service,
                              payment_method,
                              order_status,
                              spent,
                              sold_at_price,
                              payout_percentage,
                              payout_currency,
                              profit,
                              notes)
VALUES ('2021-12-12 00:00:00',
        'BOOM',
        'OLX',
        'NOVA_POST',
        'COMPLETE_PREPAYMENT',
        'SUCCESS',
        0,
        200,
        0,
        0,
        0,
        'Карта клиента НП (+2 грн.)');

# 2
INSERT INTO sales_list.sales
                            (date_time,
                             title,
                             market_place,
                             delivery_service,
                             payment_method,
                             order_status,
                             spent,
                             sold_at_price,
                             payout_percentage,
                             payout_currency,
                             profit,
                             notes)
VALUES
    ('2021-12-12 00:00:00', 'BOOM1', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0, 200, 0, 0, 0, 'Карта клиента НП (+2 грн.)'),
    ('2021-12-12 00:00:00', 'BOOM2', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0, 200, 0, 0, 0, 'Карта клиента НП (+2 грн.)'),
    ('2021-12-12 00:00:00', 'BOOM3', 'OLX', 'NOVA_POST', 'COMPLETE_PREPAYMENT', 'SUCCESS', 0, 200, 0, 0, 0, 'Карта клиента НП (+2 грн.)')
       ;
