CREATE TABLE users
(
    id        SERIAL PRIMARY KEY,
    firstname TEXT        NOT NULL,
    lastname  TEXT        NOT NULL,
    username  TEXT UNIQUE NOT NULL,
    email     TEXT UNIQUE NOT NULL,
    password  TEXT        NOT NULL,
    role      TEXT        NOT NULL DEFAULT 'USER'
);

CREATE TABLE category
(
    id    SMALLSERIAL PRIMARY KEY,
    title TEXT
);

CREATE TABLE links
(
    id          BIGSERIAL PRIMARY KEY,
    short_link  CHAR(10)                          NOT NULL,
    long_link   TEXT                              NOT NULL,
    id_category SMALLINT REFERENCES category (id) NOT NULL
);

CREATE TABLE user_link
(
    id           BIGSERIAL PRIMARY KEY,
    id_link      BIGINT REFERENCES links (id) ON DELETE CASCADE ON UPDATE CASCADE,
    id_user      INT REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    created_time TIMESTAMP NOT NULL DEFAULT now(),
    remove_time  TIMESTAMP NOT NULL,
    UNIQUE (id_user, id_link)
);

CREATE VIEW user_link_main_info AS
SELECT ul.id,
       u.username,
       l.short_link,
       c.title,
       ul.created_time,
       ul.remove_time,
       c.id AS "id_category",
       u.id AS "id_user"
FROM user_link ul
         JOIN links l on ul.id_link = l.id
         JOIN public.category c on c.id = l.id_category
         JOIN public.users u on u.id = ul.id_user;