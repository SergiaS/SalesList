DROP TABLE PUBLIC_DB.user_roles IF EXISTS;
DROP TABLE PUBLIC_DB.payouts IF EXISTS;
DROP TABLE PUBLIC_DB.products IF EXISTS;
DROP TABLE PUBLIC_DB.users IF EXISTS;
DROP SEQUENCE PUBLIC_DB.global_seq IF EXISTS;
DROP SCHEMA PUBLIC_DB;

-- CREATE SCHEMA PUBLIC_DB;

CREATE SEQUENCE PUBLIC_DB.global_seq AS INTEGER START WITH 100000;

CREATE TABLE PUBLIC_DB.users
(
    id               INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL,
    email            VARCHAR(255)            NOT NULL,
    password         VARCHAR(255)            NOT NULL,
    registered       TIMESTAMP DEFAULT now() NOT NULL,
    enabled          BOOLEAN   DEFAULT TRUE  NOT NULL,
    profit_per_day  INTEGER   DEFAULT 2000  NOT NULL
);
CREATE UNIQUE INDEX PUBLIC_DB.users_unique_email_idx ON users (email);

CREATE TABLE PUBLIC_DB.user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE PUBLIC_DB.products
(
    id                INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    user_id           INTEGER                    NOT NULL,
    date_time         TIMESTAMP                  NOT NULL,
    title             VARCHAR(255)               NOT NULL,
    market_place      VARCHAR(255)               NOT NULL,
    delivery_service  VARCHAR(255)               NOT NULL,
    payment_method    VARCHAR(255)               NOT NULL,
    order_status      VARCHAR(255)               NOT NULL,
    sold_at_price     DOUBLE PRECISION DEFAULT 0 NOT NULL,
    spent             DOUBLE PRECISION DEFAULT 0 NOT NULL,
    payout_percentage INTEGER          DEFAULT 0 NOT NULL,
    payout_currency   DOUBLE PRECISION DEFAULT 0 NOT NULL,
    profit            DOUBLE PRECISION DEFAULT 0 NOT NULL,
    notes             VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX PUBLIC_DB.products_unique_user_datetime_idx ON products (user_id, date_time);

CREATE TABLE PUBLIC_DB.payouts
(
    id         INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
    user_id    INTEGER                    NOT NULL,
    product_id INTEGER UNIQUE,
    date_time  TIMESTAMP                  NOT NULL,
    amount     DOUBLE PRECISION DEFAULT 0 NOT NULL,
    notes      VARCHAR(255)               NOT NULL,
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);
-- CREATE UNIQUE INDEX PUBLIC_DB.payouts_unique_user_date