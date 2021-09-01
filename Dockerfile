FROM openjdk:11-jre

COPY /target/*.jar /usr/share/app.jar

ENTRYPOINT ["java", "-jar", "/usr/share/app.jar"]
