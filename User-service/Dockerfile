FROM openjdk:17-oracle

WORKDIR /app

COPY target/User-service-0.0.1.jar User_service.jar

ENTRYPOINT ["java", "-jar","User_service.jar"]

EXPOSE 8081