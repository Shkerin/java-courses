-- create table client
CREATE TABLE client (
  uid  SERIAL PRIMARY KEY,
  name VARCHAR(200)
);

CREATE TABLE pet (
  uid       SERIAL PRIMARY KEY,
  client_id INT NOT NULL REFERENCES client (uid),
  nick      VARCHAR(200)
);

-- add new client
INSERT INTO client (name) VALUES ('petr');

-- add new pet
INSERT INTO pet (client_id, nick) VALUES (1, 'sparky');

INSERT INTO pet (client_id, nick) VALUES (1, 'boby');

-- select pet with client
SELECT
  pet.nick,
  client.name
FROM pet
  INNER JOIN client ON client.uid = pet.client_id;

-- select client by name
SELECT *
FROM client
WHERE client.name = 'petr';

-- update pet
UPDATE pet
SET nick = 'bob'
WHERE pet.nick = 'boby';

-- delete pet by nick
DELETE FROM pet
WHERE pet.nick = 'bob';

-- create table role
CREATE TABLE roles (
  uid  SERIAL PRIMARY KEY,
  name VARCHAR(200)
);

-- create table users
CREATE TABLE users (
  uid     SERIAL PRIMARY KEY,
  login   VARCHAR(200),
  email   VARCHAR(200),
  role_id INT NOT NULL REFERENCES roles (uid)
);

-- create table messages
CREATE TABLE messages (
  uid     SERIAL PRIMARY KEY,
  text    CHARACTER VARYING,
  user_id INT NOT NULL REFERENCES users (uid)
);
