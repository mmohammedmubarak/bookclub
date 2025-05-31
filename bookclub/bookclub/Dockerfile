FROM maven:3.9.0-eclipse-temurin-17-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

# Use a lightweight JDK image to run the application
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/bookclub.jar /app/bookclub.jar

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/bookclub.jar"]
