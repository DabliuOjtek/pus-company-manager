FROM gradle:8.1.0-jdk17 AS builder
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle build

FROM openjdk:17.0.1-jdk-slim
COPY --from=builder app/build/libs/*.jar companymanager-0.0.1.jar
EXPOSE 8080
CMD ["java", "-jar", "companymanager-0.0.1.jar"]
