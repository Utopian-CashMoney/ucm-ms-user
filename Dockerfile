#UserMS Dockerfile
FROM openjdk:16-alpine3.13 as compile

# Build the jar using maven
RUN apk add maven
COPY . /app/
WORKDIR /app
RUN mvn -f pom.xml clean package -DskipTests

FROM openjdk:11-jre
# Copy the packaged jar app file to a smaller JRE base image
COPY --from=compile /app/target/*.jar /usr/share/app.jar

ENTRYPOINT ["java", "-jar", "/usr/share/app.jar"]
