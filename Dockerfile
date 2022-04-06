FROM openjdk:11
ENTRYPOINT ["cd" "backend"]
ENTRYPOINT ["chomod" "600" "gradlew"]
ENTRYPOINT ["./gradlew" "clean" "build"]
ARG JAR_FILE=./build/libs/TNT-0.0.1-SNAPSHOT.jar
copy ${JAR_FILE} tnt.jar
ENTRYPOINT ["java", "-jar", "tnt.jar"]
EXPOSE 9999