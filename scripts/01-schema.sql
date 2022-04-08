CREATE TABLE IF NOT EXISTS articles (
      id INT NOT NULL AUTO_INCREMENT,
      author VARCHAR(200),
      title VARCHAR(500),
      description VARCHAR(2000),
      url TEXT,
      url_to_image TEXT,
      published_at DATETIME,
      content TEXT,
      PRIMARY KEY (id)
);