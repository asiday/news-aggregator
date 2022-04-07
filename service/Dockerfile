# First stage: complete build environment
FROM maven:3.8.4-openjdk-17 AS builder
COPY service/src /news-aggregator/service/src
COPY service/pom.xml /news-aggregator/service
COPY pom.xml /news-aggregator
WORKDIR /news-aggregator/service
RUN mvn package

# Second stage: minimal runtime environment
FROM openjdk:17-alpine
# copy jar from the first stage
COPY --from=builder /news-aggregator/service/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]