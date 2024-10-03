FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .

RUN chmod +x mvnw

COPY . .

RUN ./mvnw -U clean package -DskipTests

EXPOSE 8080
CMD ["java", "-jar", "target/desafioSpringBoot-0.0.1-SNAPSHOT.jar"]
