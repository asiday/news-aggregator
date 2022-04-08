# News-aggregator
News aggregator contains of three parts:
+ crawler 
  + scheduled task with get request from news-api:
    <https://newsapi.org/v2/top-headlines?country=ch&apiKey=XXX>
  + requested json files are saved in mariadb as converted entities
+ service 
  + get entities from existing mariadb
  + return them as json files under the link
    + localhost:8080/articles
+ mariadb

## Instructions to Run application.
The application uses docker-compose.yml file to run 
three images of crawler/service/mariadb as separate docker containers.
+ Add environment variables in Compose file
  + Save all sensitive information such as api key in .env file:
    ```console
      news-aggregator: ~$ cat .env 
    
      API_KEY=XXX 
      MARIADB_USER=user
      MARIADB_PASSWORD=password
      ```
  + Put placeholders at the approprate places:
    ```console
      news-aggregator: ~$ cat docker-compose.yml
    
      version: "3.7"
       services:
      ...
         environment:
          KEY: "${API_KEY}"
          SPRING_DATASOURCE_USERNAME: "${MARIADB_USER}"
          SPRING_DATASOURCE_PASSWORD: "${MARIADB_PASSWORD}"
      ...
        maria_db:
         environment:
          MARIADB_USER: "${MARIADB_USER}"
          MARIADB_PASSWORD: "${MARIADB_PASSWORD}"
      ...
      ```
  + Verify this with the config command, which prints your resolved application config to the terminal:
      ```console
      news-aggregator: ~$ docker-compose config
    
      version: "3.7"
       services:
      ...
         environment:
          KEY: XXX
          SPRING_DATASOURCE_USERNAME: user
          SPRING_DATASOURCE_PASSWORD: password
      ...
        maria_db:
         environment:
          MARIADB_USER: user
          MARIADB_PASSWORD: password
      ...
      ```
+ Run the docker compose with following command. You do not need to mention .env file.
  + $ docker-compose up -d
