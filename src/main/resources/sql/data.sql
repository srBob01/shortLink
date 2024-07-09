-- Вставка пользователей
INSERT INTO users (username, email, password, role)
VALUES ('user1', 'user1@example.com', '1', 'USER'),
       ('user2', 'user2@example.com', '1', 'USER'),
       ('user3', 'user3@example.com', '1', 'USER'),
       ('user4', 'user4@example.com', '1', 'USER'),
       ('user5', 'user5@example.com', '1', 'USER');

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


INSERT INTO links (short_link, long_link, id_category)
VALUES ('short1a', 'https://example.com/1a', 1),
       ('short1b', 'https://example.com/1b', 1),
       ('short2a', 'https://example.com/2a', 2),
       ('short2b', 'https://example.com/2b', 2),
       ('short3a', 'https://example.com/3a', 3),
       ('short3b', 'https://example.com/3b', 3),
       ('short4a', 'https://example.com/4a', 4),
       ('short4b', 'https://example.com/4b', 4),
       ('short5a', 'https://example.com/5a', 5),
       ('short5b', 'https://example.com/5b', 5);


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
