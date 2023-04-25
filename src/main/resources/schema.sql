DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS comment;

CREATE TABLE member (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BIT NOT NULL DEFAULT 1,
    authority VARCHAR(50) NOT NULL DEFAULT 'USER',
    PRIMARY KEY (id),
    CONSTRAINT email_unique UNIQUE (email)
);

-- CREATE TABLE authorities (
--     email VARCHAR(255) NOT NULL,
--     authority VARCHAR(50) NOT NULL,
--     FOREIGN KEY (email) REFERENCES member(email)
-- );
--
-- CREATE UNIQUE INDEX ix_auth_email
--     on authorities (email,authority);

CREATE TABLE post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    writer VARCHAR(255),
    title VARCHAR(255) NOT NULL,
    content VARCHAR(255) NOT NULL,
    post_time TIMESTAMP
);

CREATE TABLE comment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    post_id BIGINT,
    writer VARCHAR(255),
    content VARCHAR(255) NOT NULL,
    created_date TIMESTAMP
);



