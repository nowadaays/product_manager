FROM adoptopenjdk:21-jdk-hotspot AS build

ENV GRADLE_USER_HOME=/home/gradle/cache

COPY build.gradle .
COPY settings.gradle .
COPY gradle.properties .
COPY gradlew .

COPY src/ src/

RUN ./gradlew build

FROM adoptopenjdk:21-jdk-hotspot

WORKDIR /app

COPY --from=build build/libs/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]