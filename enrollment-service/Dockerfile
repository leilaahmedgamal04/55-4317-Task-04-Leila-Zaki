FROM maven:3-eclipse-temurin-25 AS build
WORKDIR /src
COPY pom.xml .
COPY course-service/pom.xml       course-service/
COPY enrollment-service/pom.xml   enrollment-service/
COPY notification-service/pom.xml notification-service/
COPY enrollment-service/src       enrollment-service/src
RUN mvn -B -q -pl enrollment-service -am package -DskipTests

FROM eclipse-temurin:25.0.2_10-jdk
WORKDIR /app
COPY --from=build /src/enrollment-service/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/app.jar"]
