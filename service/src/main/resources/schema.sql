DROP TABLE IF EXISTS articles;
CREATE TABLE articles (
      id INT NOT NULL AUTO_INCREMENT,
      author VARCHAR(100),
      title VARCHAR(500),
      description VARCHAR(1000),
      url VARCHAR(500),
      url_to_image VARCHAR(500),
      published_at DATETIME,
      content VARCHAR(1000),
      PRIMARY KEY (id)
);