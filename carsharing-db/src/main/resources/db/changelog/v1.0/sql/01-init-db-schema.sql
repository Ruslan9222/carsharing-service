
CREATE TABLE "carsharing-service".car
(
    id        BIGSERIAL PRIMARY KEY,
    model     VARCHAR(255),
    win_code  VARCHAR(255) UNIQUE,
    class_car VARCHAR(255),
    latitude  DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    photo     BYTEA
);