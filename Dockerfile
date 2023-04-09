FROM amazoncorretto:17
CMD ["./gradlew", "clean", "build"]
ARG JAR_FILE_PATH=build/libs/*SNAPSHOT.jar
COPY ${JAR_FILE_PATH} app.jar

ENV TZ="Asia/Seoul"

ENTRYPOINT ["java", "-jar", "app.jar"]