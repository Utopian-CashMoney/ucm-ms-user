FROM openjdk:11-jre

COPY /target/*.jar /usr/share/app.jar
EXPOSE 8000

ENTRYPOINT ["java", "-jar", "/usr/share/app.jar"]
