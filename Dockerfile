FROM gradle:8.1.0-jdk17
WORKDIR /app
COPY build.gradle .
COPY src ./src
RUN gradle build
COPY build/libs/companymanager-0.0.1.jar .
EXPOSE 8080
CMD ["java", "-jar", "companymanager-0.0.1.jar"]
