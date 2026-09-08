# Multi-stage build for Maven + Tomcat 11
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage: Tomcat 11 with JDK 21 (Jakarta EE 10 / Servlet 6.0)
FROM tomcat:11.0-jdk21-temurin
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/webapp_w2.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

# Adapt to Render's dynamic PORT environment variable if provided, default to 8080
CMD sed -i "s/port=\"8080\"/port=\"${PORT:-8080}\"/" /usr/local/tomcat/conf/server.xml && catalina.sh run
