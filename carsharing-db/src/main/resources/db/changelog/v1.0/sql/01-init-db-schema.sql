-- Таблица пользователей
CREATE TABLE "carsharing-service".db_user
(
    id          BIGSERIAL PRIMARY KEY,
    name            VARCHAR(255),
    username        VARCHAR(255) UNIQUE NOT NULL,
    password        VARCHAR(255)        NOT NULL,
    drivers_license BYTEA,
    email           VARCHAR(255)

);


CREATE TABLE "carsharing-service".db_user_roles
(
    db_user_use_id BIGINT      NOT NULL,
    role_list      VARCHAR(50) NOT NULL,
    FOREIGN KEY (db_user_use_id) REFERENCES "carsharing-service".db_user (id) ON DELETE CASCADE
);


CREATE TABLE "carsharing-service".db_card
(
    id     BIGSERIAL PRIMARY KEY,
    number INTEGER NOT NULL,
    cv     VARCHAR(10),
    name   VARCHAR(255),
    user_id BIGINT  NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "carsharing-service".db_user (id) ON DELETE CASCADE
);


CREATE TABLE "carsharing-service".db_car
(
    id        BIGSERIAL PRIMARY KEY,
    model     VARCHAR(255),
    win_code  VARCHAR(255) UNIQUE,
    class_car VARCHAR(255),
    latitude  DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    photo     BYTEA,
    price     NUMERIC(19, 2)

);