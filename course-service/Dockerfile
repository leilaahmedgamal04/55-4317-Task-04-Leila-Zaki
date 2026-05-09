FROM maven:3-eclipse-temurin-25 AS build
WORKDIR /src
COPY pom.xml .
COPY course-service/pom.xml       course-service/
COPY enrollment-service/pom.xml   enrollment-service/
COPY notification-service/pom.xml notification-service/
COPY course-service/src           course-service/src
RUN mvn -B -q -pl course-service -am package -DskipTests

FROM eclipse-temurin:25.0.2_10-jdk
WORKDIR /app
COPY --from=build /src/course-service/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.jar"]
