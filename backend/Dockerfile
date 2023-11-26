FROM bellsoft/liberica-openjdk-alpine:17 as builder
ARG APPDIR=/app

WORKDIR /app

# Слои с gradlew
COPY gradle gradle
COPY gradlew ./
RUN ./gradlew --version

# Слои с файлами для сборки
COPY build.gradle.kts settings.gradle.kts ./
COPY backend backend

RUN ./gradlew clean build


FROM bellsoft/liberica-openjdk-alpine:17

# Set the working directory
WORKDIR /app

# Copy the built application from the builder stage
COPY --from=builder /app/backend/build/libs/backend-1.0-SNAPSHOT.jar app.jar

RUN mkdir logs

# Expose the application port
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]

