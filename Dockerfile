FROM maven:3.8-jdk-11 AS builder
WORKDIR /app
COPY pom.xml system.properties ./
COPY src src
RUN --mount=type=cache,target=/root/.m2,rw mvn clean package

FROM openjdk:11-slim
COPY --from=builder /app /app
ENTRYPOINT ["java", "-Xmx300m", "-Xss512k", "-XX:CICompilerCount=2", "-Dfile.encoding=UTF-8", \
            "-cp", "/app/target/classes:/app/target/dependency/*", \
            "ru.tayrinn.telegram.coopdance.CoopDanceBotApplication"]


