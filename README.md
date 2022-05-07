# News-aggregator

## About

This application is my so called "pet project" with a main goal to learn modern Java Enterprise development.

The idea of the application is to collect the news into a database on the one side and on the other side to make this collected news available for requesting by some REST service. The REST service is ready to be used as a backend for e.g some web or mobile application.

This application and all the collected data are used only for the personal learning purposes and are not a subject for commercial use.

## Application components

This application consists of two modules: the crawler itself and the REST service. Both are built as a Spring Boot services and use shared database instance, in this case it is MariaDB.

The crawler is responsible for running a scheduler, that is searching through news sources (e.g. [News API](https://newsapi.org/)). The retrieved articles are persisted in the database.

The REST WebService retrieves news articles from the database using Spring Data and returns them in JSON format.

## Technical description

### Overview 
As mentioned above, the news aggregator contains of three parts:
+ crawler 
  + scheduled task with get request from news-api:
    <https:newsapi.org/v2/top-headlines?country=ch&apiKey=XXX>
  + requested json files are saved in mariadb as converted entities
+ service 
  + reads entities from the database
  + provides API to request the news articles, e.g. under ***\<service-url\>:\<service-posrt\>/articles***
+ MariaDB
  + the database instance

The crawler is supposed to work with different news sources. In the first version, it uses the News API which requires an [API key](https://newsapi.org/register). Please, make sure to register for an Api KEY to be able to start this application properly.

### Docker images 

I use Docker to deploy and run my applications, so I create docker images of my Spring Boot application and make this images available on the Docker Hub.
+ The crawler image on Docker Hub: [news-crawler](https://hub.docker.com/repository/docker/asiday/news-crawler)
+ The REST Service on Docker Hub:  [news-service](https://hub.docker.com/repository/docker/asiday/news-service)


### How to run the application

The application uses docker-compose.yml file to run all three components (the crawler, the service and the MariaDB) as docker containers. The ***.env*** environment file with a configuration properies is required.

Add environment variables in Compose file. Save all sensitive information such as api key in .env file:

```console
      news-aggregator: ~$ cat .env 
    
      API_KEY=XXX 
      MARIADB_USER=username
      MARIADB_PASSWORD=password
      MARIADB_ROOT_PASSWORD=rootpassword
```

The above values configured in the ***.env*** file are set at the approprate places in the ***docker-compose.yml*** at the start time:

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

You can verify this with the config command, which prints your resolved application config to the terminal:

```console
      news-aggregator: ~$ docker-compose config
    
      version: "3.7"
       services:
      ...
         environment:
          KEY: XXX
          SPRING_DATASOURCE_USERNAME: username
          SPRING_DATASOURCE_PASSWORD: password
      ...
        maria_db:
         environment:
          MARIADB_USER: username
          MARIADB_PASSWORD: password
      ...
```

By default, the REST service runs on the local port ```8080```. You can override this behaviour in the ```docker-compose.yml``` in the service section:

```console
  service:
    image: "asiday/news-service:0.0.1"
    ports:
      - "[put your host port here]:8080"
```

To start the application, just run the docker compose as follows:
```console
      news-aggregator: ~$ docker-compose up -d
```
