
# Jenkins Dockerfile for Backend and Frontend

# === Jenkins Base Setup ===
FROM jenkins/jenkins:lts as jenkins

USER root

# Install Docker CLI for Jenkins pipelines to interact with Docker
RUN apt-get update && \
    apt-get install -y apt-transport-https ca-certificates curl software-properties-common && \
    curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add - && \
    add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/debian $(lsb_release -cs) stable" && \
    apt-get update && \
    apt-get install -y docker-ce-cli && \
    rm -rf /var/lib/apt/lists/*

# Switch back to Jenkins user
USER jenkins

# === Backend Stage (Spring Boot) ===
FROM eclipse-temurin:21-jdk as backend-builder

# Set working directory
WORKDIR /app

# Copy backend source code and build
COPY proyecto/ /app/

# Build the backend (Assuming Maven is used)
RUN ./mvnw clean package -DskipTests

# === Frontend Stage (Angular) ===
FROM node:20 as frontend-builder

# Set working directory
WORKDIR /app

# Copy frontend source code
COPY noSql/ /app/

# Install dependencies and build the frontend
RUN npm install && npm run build

# === Final Stage: Combine Backend and Frontend ===
FROM eclipse-temurin:21-jre

# Set working directory
WORKDIR /app

# Copy backend artifacts
COPY --from=backend-builder /app/target/proyecto-0.0.1-SNAPSHOT.jar /app/backend.jar

# Copy frontend build artifacts
COPY --from=frontend-builder /app/dist /app/static

# Expose the necessary port
EXPOSE 8080

# Run the backend
CMD ["java", "-jar", "/app/backend.jar"]