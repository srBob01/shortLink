CREATE TABLE users
(
    id                 SERIAL PRIMARY KEY,
    firstname          TEXT        NOT NULL,
    lastname           TEXT        NOT NULL,
    username           TEXT UNIQUE NOT NULL,
    email              TEXT UNIQUE NOT NULL,
    password           TEXT        NOT NULL,
    create_date        TIMESTAMP,
    last_modified_date TIMESTAMP,
    enabled            boolean DEFAULT false,
    account_locked     boolean DEFAULT false,
    role               TEXT        NOT NULL
);

CREATE TABLE category
(
    id    SMALLSERIAL PRIMARY KEY,
    title TEXT UNIQUE
);

CREATE TABLE links
(
    id                 BIGSERIAL PRIMARY KEY,
    link_name          TEXT                 NOT NULL,
    short_link         CHAR(7) UNIQUE       NOT NULL,
    long_link          TEXT                 NOT NULL,
    create_date        TIMESTAMP            NOT NULL,
    last_modified_date TIMESTAMP,
    remove_date        TIMESTAMP            NOT NULL,
    id_category        SMALLINT REFERENCES category (id)
        ON DELETE SET NULL ON UPDATE CASCADE,
    id_user            INT REFERENCES users (id)
        ON DELETE CASCADE ON UPDATE CASCADE NOT NULL
);

CREATE TABLE tokens
(
    id           SERIAL PRIMARY KEY,
    token        CHAR(6) UNIQUE             NOT NULL,
    created_at   TIMESTAMP,
    expires_at   TIMESTAMP,
    validated_at TIMESTAMP,
    id_user      INT REFERENCES users (id)
        ON DELETE CASCADE ON UPDATE CASCADE NOT NULL
);