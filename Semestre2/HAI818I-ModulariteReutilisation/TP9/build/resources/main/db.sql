CREATE TABLE users(
    first_name varchar(30),
    last_name varchar(30),
    email varchar(80),
    phone_number varchar(20),
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password varchar(100) NOT NULL,
    enabled boolean NOT NULL DEFAULT false
);

CREATE TABLE authorities(
    authority_id serial primary key,
    username varchar(50) NOT NULL REFERENCES users (username),
    authority varchar(50) NOT NULL DEFAULT 'ROLE_USER'
);

CREATE TABLE persistent_logins(
    username varchar(50) NOT NULL REFERENCES users (username),
    series varchar(64) PRIMARY KEY,
    token varchar(64) NOT NULL,
    last_used timestamp NOT NULL
);

CREATE TABLE verif_tokens(
    token varchar(50) PRIMARY KEY,
    username varchar(50) NOT NULL REFERENCES users (username),
    expiry_date timestamp NOT NULL
);