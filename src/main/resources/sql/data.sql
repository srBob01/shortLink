-- Вставка пользователей
INSERT INTO users (username, email, password, firstname, lastname, role)
VALUES ('user1', 'user1@example.com', '{noop}1', '1_f', '1_l', 'USER'),
       ('user2', 'user2@example.com', '{noop}2', '2_f', '2_l', 'USER'),
       ('user3', 'user3@example.com', '{noop}3', '3_f', '3_l', 'USER'),
       ('user4', 'user4@example.com', '{noop}4', '4_f', '4_l', 'USER'),
       ('user5', 'user5@example.com', '{noop}5', '5_f', '5_l', 'USER');

-- Вставка категорий
INSERT INTO category (title)
VALUES ('Technology'),
       ('Health'),
       ('Education'),
       ('Finance'),
       ('Travel'),
       ('Entertainment'),
       ('Food'),
       ('Sports'),
       ('Fashion'),
       ('Science');


INSERT INTO links (short_link, long_link, link_name, id_category)
VALUES ('short1a', 'https://example.com/1a', '1', 1),
       ('short1b', 'https://example.com/1b', '1', 1),
       ('short2a', 'https://example.com/2a', '1', 2),
       ('short2b', 'https://example.com/2b', '1', 2),
       ('short3a', 'https://example.com/3a', '1', 3),
       ('short3b', 'https://example.com/3b', '1', 3),
       ('short4a', 'https://example.com/4a', '1', 4),
       ('short4b', 'https://example.com/4b', '1', 4),
       ('short5a', 'https://example.com/5a', '1', 5),
       ('short5b', 'https://example.com/5b', '1', 5);


-- Вставка связей между пользователями и ссылками
INSERT INTO user_link (id_link, id_user, remove_time)
VALUES (1, 1, '2024-12-31 23:59:59'),
       (2, 1, '2024-12-31 23:59:59'),
       (3, 2, '2024-12-31 23:59:59'),
       (4, 2, '2024-12-31 23:59:59'),
       (5, 3, '2024-12-31 23:59:59'),
       (6, 3, '2024-12-31 23:59:59'),
       (7, 4, '2024-12-31 23:59:59'),
       (8, 4, '2024-12-31 23:59:59'),
       (9, 5, '2024-12-31 23:59:59'),
       (10, 5, '2024-12-31 23:59:59');
