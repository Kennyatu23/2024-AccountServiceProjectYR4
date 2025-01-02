FROM eclipse-temurin:17-alpine

#Set working directory inside the container
WORKDIR /app

#Copy Jar file into the Container
COPY target/AccountService-0.0.1-SNAPSHOT.jar /app

#Expose port that the Spring Boot application will run on
EXPOSE 8081

#Command to run the application
CMD ["java", "-jar", "AccountService-0.0.1-SNAPSHOT.jar"]