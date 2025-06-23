FROM maven:3.9.6-eclipse-temurin-17 as base

WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:go-offline
CMD ["/bin/bash"]
