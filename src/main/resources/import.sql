-- Inserting initial/test data

-- USERS TABLE
-- Password of "admin@example.com" is "password1"
-- Password of "user@example.com" is "password1"
DELETE FROM USERS;
INSERT INTO USERS(ID, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, ROLE, CREATED_AT, UPDATED_AT) VALUES (1, 'admin@example.com', 'John', 'Doe', '$2a$10$lpwG0zVGaPq1Iqt7XB5rfOwMb17niqarR6JmqD.PgMkdmtBUc6E0S', 'ADMIN', '2015-03-01 18:00:00.294', '2015-03-01 18:00:00.294');
INSERT INTO USERS(ID, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, ROLE, CREATED_AT, UPDATED_AT) VALUES (2, 'user@example.com', 'Foo', 'Bar', '$2a$10$lpwG0zVGaPq1Iqt7XB5rfOwMb17niqarR6JmqD.PgMkdmtBUc6E0S', 'USER', '2015-03-01 18:00:00.294', '2015-03-01 18:00:00.294');

-- SEASONS TABLE
DELETE FROM SEASONS;
INSERT INTO SEASONS(ID, NAME, START_AT, END_AT) VALUES (1, 'Saison 2014', '2014-02-10 12:00:00.294', '2014-09-01 12:00:00.294');
INSERT INTO SEASONS(ID, NAME, START_AT, END_AT) VALUES (2, 'Saison 2015', '2015-03-01 00:00:00.294', '2015-10-01 12:00:00.294');

-- TEAMS TABLE
DELETE FROM TEAMS;
INSERT INTO TEAMS(ID, NAME, DESCRIPTION, PATH_LOGO) VALUES (1, 'ESAIP', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras feugiat leo nibh, ac dignissim est molestie quis. Morbi consequat diam id ultricies luctus. Curabitur libero lacus, ultrices id finibus euismod, eleifend nec arcu. Quisque nec ligula vel tellus porttitor aliquet quis at risus. Phasellus vitae risus vitae tellus fermentum vehicula. Aliquam nec commodo purus. Morbi imperdiet quam in varius maximus.', '');
INSERT INTO TEAMS(ID, NAME, DESCRIPTION, PATH_LOGO) VALUES (2, 'Les Experts', 'Equipe nationale France A Masculine', '');
INSERT INTO TEAMS(ID, NAME, DESCRIPTION, PATH_LOGO) VALUES (3, 'Les Femmes de Défis', 'Equipe nationale France A Féminine', '');
INSERT INTO TEAMS(ID, NAME, DESCRIPTION, PATH_LOGO) VALUES (4, 'France Cadet(te)s', '', '');

-- MATCHS TABLE
DELETE FROM MATCHS;
INSERT INTO MATCHS(ID, ID_TEAM_DOM, ID_TEAM_EXT, SCORE_DOM, SCORE_EXT, PLAYED_AT) VALUES (1, 1, 2, 23, 21, '2015-03-05 19:30:00.294');
INSERT INTO MATCHS(ID, ID_TEAM_DOM, ID_TEAM_EXT, SCORE_DOM, SCORE_EXT, PLAYED_AT) VALUES (2, 1, 3, 10, 30, '2015-03-02 14:00:00.294');

