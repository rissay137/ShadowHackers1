FROM openjdk:17-jdk-slim
COPY src /app/src
WORKDIR /app
RUN javac src/com/mycompany/shadowhackers1/*.java
CMD ["java", "-cp", "src", "com.mycompany.shadowhackers1.Server"]
