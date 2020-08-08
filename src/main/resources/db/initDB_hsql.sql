
CREATE SEQUENCE IF NOT EXISTS GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE IF NOT EXISTS restaurants
(
    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL UNIQUE,
    address             VARCHAR(255),
    date_of_last_updating   TIMESTAMP    NOT NULL,
    description VARCHAR(255) NOT NULL,
    rating    INT          NOT NULL,
    user_id     INTEGER      NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL,
    email            VARCHAR(255)            NOT NULL,
    password         VARCHAR(255)            NOT NULL,
    favorite_restaurant VARCHAR(255),
    last_voting_date       TIMESTAMP,
    is_admin        BOOLEAN   DEFAULT FALSE  NOT NULL,
    CONSTRAINT user_id_and_restaurant_idx UNIQUE (id, favorite_restaurant),
    FOREIGN KEY (favorite_restaurant) REFERENCES RESTAURANTS (name) ON DELETE CASCADE,
    FOREIGN KEY (id) REFERENCES RESTAURANTS(user_id) ON DELETE CASCADE

);

CREATE UNIQUE INDEX IF NOT EXISTS users_unique_email_idx
    ON USERS (email);

CREATE TABLE IF NOT EXISTS user_roles
(
    user_id INTEGER NOT NULL PRIMARY KEY,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX IF NOT EXISTS restaurants_unique_name_idx
    ON restaurants (name);

CREATE TABLE IF NOT EXISTS meals
(
    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    meal VARCHAR(255) NOT NULL ,
    price INTEGER NOT NULL,
    restaurant_id INTEGER NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS (id) ON DELETE CASCADE
)