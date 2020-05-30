INSERT INTO user_type (name) VALUE ('GUEST');
INSERT INTO user_type (name) VALUE ('USER');
INSERT INTO user_type (name) VALUE ('ADMIN');
INSERT INTO user_type (name) VALUE ('SUPER_ADMIN');
INSERT INTO user (email, name, role_id) VALUES ('jjdd9dreamteam@gmail.com', 'Dream Team', 4);
INSERT INTO author (name) VALUE ('Ignacy Krasicki');
INSERT INTO book (isbn, fragment, title, audio) VALUES ('9788328452411', 'Winszuj, ojcze — rzekł Tair — w dobrym jestem stanie.',  'Abuzei i Tair' , true);
INSERT INTO authorship (book_id, author_id) VALUES (1, 1);
INSERT INTO kind (name) VALUE ('Epika');
INSERT INTO book_kind (kind_id, book_id) VALUE (1, 1);
INSERT INTO epoch (name) VALUE ('Oświecenie');
INSERT INTO book_epoch (epoch_id, book_id) VALUE (1, 1);
INSERT INTO genre (name) VALUE ('Bajka');
INSERT INTO genre (name) VALUE ('Przypowieść');
INSERT INTO book_genre (genre_id, book_id) VALUE (1, 1);
INSERT INTO book_genre (genre_id, book_id) VALUE (2, 1);
INSERT INTO translator (name) VALUE ('Przykładowy Tłumacz');
INSERT INTO book_translator ( book_id, translator_id) VALUE (1, 1);

# cover
# 'https://wolnelektury.pl/media/book/cover_api_thumb/abuzei-i-tair_UXHO0j3.jpg',