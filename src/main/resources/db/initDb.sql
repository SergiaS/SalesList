DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    nickname        VARCHAR                           NOT NULL,
    password        VARCHAR                           NOT NULL,
    registered      TIMESTAMP           DEFAULT now() NOT NULL,
    profited        BOOL                DEFAULT TRUE  NOT NULL,
    profits_per_day INTEGER             DEFAULT 500   NOT NULL
);
CREATE UNIQUE INDEX users_unique_nickname_idx ON users (nickname);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE products
(
    id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id           INTEGER   NOT NULL,
    date_time         TIMESTAMP NOT NULL,
    title             VARCHAR   NOT NULL,
    market_place      VARCHAR   NOT NULL,
    delivery_service  VARCHAR   NOT NULL,
    payment_method    VARCHAR   NOT NULL,
    order_status      VARCHAR   NOT NULL,
    sold_at_price     DECIMAL   NOT NULL,
    spent             DECIMAL   NOT NULL,
    payout_percentage INTEGER   NOT NULL,
    payout_currency   DECIMAL   NOT NULL,
    profit            DECIMAL   NOT NULL,
    notes             VARCHAR,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX products_unique_user_datetime_idx ON products (user_id, date_time);
