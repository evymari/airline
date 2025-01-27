INSERT INTO countries (id_country, name) VALUES (default, 'USA');
INSERT INTO countries (id_country, name) VALUES (default, 'France');
INSERT INTO countries (id_country, name) VALUES (default, 'Ucrania');
INSERT INTO countries (id_country, name) VALUES (default, 'Italy');
INSERT INTO countries (id_country, name) VALUES (default, 'Canada');


/* Roles */
INSERT INTO roles (id_role, name) VALUES (default, 'ROLE_USER');
INSERT INTO roles (id_role, name) VALUES (default, 'ROLE_ADMIN');

/* Users */
INSERT INTO users (id_user, email, password) VALUES (default, 'pepe@gmail.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (id_user, email, password) VALUES (default, 'pepa@gmail.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');

/* Profiles */
INSERT INTO profiles (id_profile,email, address, user_id, country_id) VALUES (default,'pepe@mail.com', 'portal 1',1,2);
INSERT INTO profiles (id_profile,email, address, user_id, country_id) VALUES (default,'pepa@mail.com', 'portal 1',2,3);

/* Roles Users */
INSERT INTO roles_users (role_id, user_id) VALUES (1, 1);
INSERT INTO roles_users (role_id, user_id) VALUES (2, 2);
/* Inserting Airports */
INSERT INTO airport (id_airport, name, city, codigo) VALUES (default, 'Aeropuerto Internacional de Los Ángeles', 'Los Ángeles', 'LAX');
INSERT INTO airport (id_airport, name, city, codigo) VALUES (default, 'Aeropuerto Charles de Gaulle', 'París', 'CDG');
INSERT INTO airport (id_airport, name, city, codigo) VALUES (default, 'Aeropuerto Internacional de Boryspil', 'Kiev', 'KBP');
INSERT INTO airport (id_airport, name, city, codigo) VALUES (default, 'Aeropuerto Internacional de Roma-Fiumicino', 'Roma', 'FCO');
INSERT INTO airport (id_airport, name, city, codigo) VALUES (default, 'Aeropuerto Internacional de Toronto Pearson', 'Toronto', 'YYZ');

/* Insertar vuelos de prueba */

INSERT INTO flights (origin, destination, departure_date, available_seats, status)
VALUES ('Madrid', 'Barcelona', '2025-02-01T10:00:00', 100, true),
       ('Londres', 'París', '2025-02-02T15:00:00', 150, true),
       ('Nueva York', 'Los Ángeles', '2025-02-05T12:00:00', 200, true);

