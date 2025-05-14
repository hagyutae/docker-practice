FROM eclipse-temurin:17-jdk-jammy as build
WORKDIR /workspace/app

COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .

RUN ./gradlew dependencies

COPY src src
RUN ./gradlew build -x test

FROM eclipse-temurin:17-jre-jammy
VOLUME /tmp
COPY --from=build /workspace/app/build/libs/*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=dev
ENV DB_HOST=postgres
ENV DB_PORT=5432
ENV DB_NAME=contactdb
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=postgres

ENTRYPOINT ["java","-jar","/app.jar"]
