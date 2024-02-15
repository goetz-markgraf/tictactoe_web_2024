FROM gradle:latest AS builder
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

FROM openjdk:latest
WORKDIR /app
COPY --from=builder /app/build/libs/tictactoe_web_2024-0.0.1-SNAPSHOT.jar game.jar
EXPOSE 8080
CMD ["java", "-jar", "game.jar"]