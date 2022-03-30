# First stage: complete build environment
FROM maven:3.8.4-openjdk-17 AS builder
COPY crawler/src /news-aggregator/crawler/src
COPY crawler/pom.xml /news-aggregator/crawler
COPY pom.xml /news-aggregator
ARG key
WORKDIR /news-aggregator/crawler
RUN mvn package -Dkey=${key}

# Second stage: minimal runtime environment
FROM openjdk:17-alpine
# copy jar from the first stage
COPY --from=builder /news-aggregator/crawler/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]