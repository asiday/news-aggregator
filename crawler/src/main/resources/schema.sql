DROP TABLE IF EXISTS articles;
CREATE TABLE articles
(
    id INT NOT NULL AUTO_INCREMENT,
    author VARCHAR(100),
    title VARCHAR(250),
    description VARCHAR(500),
    url VARCHAR(250),
    url_to_image VARCHAR(250),
    published_at VARCHAR(50) NOT NULL,
    content VARCHAR(1000),
    PRIMARY KEY (id)
);