DELETE
FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User1', 'user1@yandex.ru', 'password1'),
       ('User2', 'user2@yandex.ru', 'password2'),
       ('User3', 'user3@yandex.ru', 'password3'),
       ('User4', 'user4@yandex.ru', 'password4'),
       ('User5', 'user5@yandex.ru', 'password5');