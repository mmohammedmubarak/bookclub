FROM maven:3.9.0-eclipse-termurin-17-alphine AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:19
COPY --from=build /target/bookclub.jar bookclub.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/bookclub.jar"]