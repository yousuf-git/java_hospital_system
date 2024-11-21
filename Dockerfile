# Use an explicit JDK 21 base image  
FROM openjdk:21-jdk-slim  

# Set working directory  
WORKDIR /app  

# Copy maven/gradle wrapper and source code  
COPY .mvn .mvn  
COPY mvnw .  
COPY pom.xml .  
COPY src ./src  

# Grant permissions to mvnw  
RUN chmod +x mvnw  

# Build the application  
RUN ./mvnw clean package -DskipTests  

# Find the exact JAR file name  
RUN ls target/*.jar

# Expose port  
EXPOSE 8080  

# Run the application  
ENTRYPOINT ["java", "-jar", "target/*.jar"]