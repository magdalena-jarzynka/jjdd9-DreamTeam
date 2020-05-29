INSERT INTO user_type (name) VALUE ('GUEST');
INSERT INTO user_type (name) VALUE ('USER');
INSERT INTO user_type (name) VALUE ('ADMIN');
INSERT INTO user_type (name) VALUE ('SUPER_ADMIN');
INSERT INTO user (email, name, role_id) VALUES ('jjdd9dreamteam@gmail.com', 'Dream Team', 4);
INSERT INTO author (name) VALUE ('Ignacy Krasicki');
INSERT INTO book (fragment, cover, title, audio) VALUES ('Winszuj, ojcze — rzekł Tair — w dobrym jestem stanie.', 'https://wolnelektury.pl/media/book/cover_api_thumb/abuzei-i-tair_UXHO0j3.jpg', 'Abuzei i Tair' , true);
INSERT INTO authorship (book_id, author_id) VALUES (1, 1);
