DROP TABLE IF EXISTS users_roles;
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
