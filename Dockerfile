
FROM eclipse-temurin:21-jdk

WORKDIR /app


COPY . .

RUN find . -name "*.java" > sources.txt && javac @sources.txt


CMD ["java", "-cp", "src", "com.mycompany.shadowhackers1.Server"]
