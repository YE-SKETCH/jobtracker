\
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
COPY src ./src

RUN chmod +x mvnw && ./mvnw -DskipTests package

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/jobtracker-0.0.1-SNAPSHOT.jar"]
