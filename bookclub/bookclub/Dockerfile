# Use Maven to build inside the Docker container
FROM maven:3.9.0-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use Java runtime to run the application
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar /app/bookclub.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/bookclub.jar"]