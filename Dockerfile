FROM openjdk:17

ARG JAR_FILE=./build/libs/spring-boot-board.jar

COPY ${JAR_FILE} .

ENV TZ=Asia/Seoul

EXPOSE 8080

CMD ["java", "-jar", "spring-boot-board.jar"]
