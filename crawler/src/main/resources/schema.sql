DROP TABLE IF EXISTS articles;
CREATE TABLE articles
(
    id INT NOT NULL AUTO_INCREMENT,
    author VARCHAR(100),
    title VARCHAR(250) NOT NULL,
    description VARCHAR(250) NOT NULL,
    url VARCHAR(250) NOT NULL,
    url_to_image VARCHAR(250) NOT NULL,
    published_at VARCHAR(50) NOT NULL,
    content VARCHAR(1000) NOT NULL,
    PRIMARY KEY (id)
);