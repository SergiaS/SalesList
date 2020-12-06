DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS payouts;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;


DROP SEQUENCE IF EXISTS user_seq;
CREATE SEQUENCE user_seq START WITH 100;

CREATE TABLE users
(
    id             INTEGER PRIMARY KEY DEFAULT nextval('user_seq'),
    name           VARCHAR                           NOT NULL,
    email          VARCHAR UNIQUE                    NOT NULL,
    password       VARCHAR                           NOT NULL,
    registered     TIMESTAMP           DEFAULT now() NOT NULL,
    enabled        BOOL                DEFAULT TRUE  NOT NULL,
    profit_per_day INTEGER             DEFAULT 500   NOT NULL
);


CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


DROP SEQUENCE IF EXISTS products_seq;
CREATE SEQUENCE products_seq;

CREATE TABLE products
(
    id                INTEGER PRIMARY KEY DEFAULT nextval('products_seq'),
    user_id           INTEGER                           NOT NULL,
    date_time         TIMESTAMP           DEFAULT now() NOT NULL,
    title             VARCHAR                           NOT NULL,
    market_place      VARCHAR                           NOT NULL,
    delivery_service  VARCHAR                           NOT NULL,
    payment_method    VARCHAR                           NOT NULL,
    order_status      VARCHAR                           NOT NULL,
    sold_at_price     DOUBLE PRECISION    DEFAULT 0     NOT NULL,
    spent             DOUBLE PRECISION    DEFAULT 0     NOT NULL,
    payout_percentage INTEGER             DEFAULT 0     NOT NULL,
    payout_currency   DOUBLE PRECISION    DEFAULT 0     NOT NULL,
    profit            DOUBLE PRECISION    DEFAULT 0     NOT NULL,
    notes             VARCHAR,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


DROP SEQUENCE IF EXISTS payouts_seq;
CREATE SEQUENCE payouts_seq START WITH 1000;

CREATE TABLE payouts
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('payouts_seq'),
    user_id    INTEGER                           NOT NULL,
    product_id INTEGER UNIQUE,
    date_time  TIMESTAMP           DEFAULT now() NOT NULL,
    amount     DOUBLE PRECISION    DEFAULT 0     NOT NULL,
    notes      VARCHAR                           NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);
