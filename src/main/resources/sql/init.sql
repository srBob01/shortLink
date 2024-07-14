CREATE TABLE users
(
    id                 SERIAL PRIMARY KEY,
    firstname          TEXT        NOT NULL,
    lastname           TEXT        NOT NULL,
    username           TEXT UNIQUE NOT NULL,
    email              TEXT UNIQUE NOT NULL,
    password           TEXT        NOT NULL,
    create_date        TIMESTAMP   NOT NULL DEFAULT now(),
    last_modified_date TIMESTAMP,
    role               TEXT        NOT NULL DEFAULT 'USER'
);

CREATE TABLE category
(
    id    SMALLSERIAL PRIMARY KEY,
    title TEXT
);

CREATE TABLE links
(
    id                 BIGSERIAL PRIMARY KEY,
    link_name          TEXT                 NOT NULL,
    short_link         CHAR(7) UNIQUE       NOT NULL,
    long_link          TEXT                 NOT NULL,
    create_date        TIMESTAMP            NOT NULL DEFAULT now(),
    last_modified_date TIMESTAMP,
    remove_date        TIMESTAMP            NOT NULL DEFAULT now(),
    id_category        SMALLINT REFERENCES category (id)
        ON DELETE SET NULL ON UPDATE CASCADE,
    id_user            INT REFERENCES users (id)
        ON DELETE CASCADE ON UPDATE CASCADE NOT NULL
);