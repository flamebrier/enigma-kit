CREATE TABLE IF NOT EXISTS role
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS usr
(
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100),
    password CHAR(68),
    rating INT,
    photo_link CHAR(255)
);

INSERT INTO role(name) VALUES('ROLE_USER'), ('ROLE_MODER'), ('ROLE_ADMIN');

INSERT INTO usr(username, password)
VALUES('admin', '{bcrypt}$2a$10$4rKC6F6abCXj.vrjVnjuIuEnsbhBnHrChgAYto.Ut4e/XbnW.uwcq'),
('moder', '{bcrypt}$2a$10$KbzS0t3cCuUXxSTIJp2dqeAb1rpCdOTP6SYeRAJO6743Ci3oNZH1S');

INSERT INTO usr_roles(users_id, roles_id) VALUES(1, 3), (2, 2);
