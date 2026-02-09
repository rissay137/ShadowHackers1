
FROM eclipse-temurin:21-jdk


WORKDIR /app


COPY . .


RUN mkdir -p out

RUN find . -name "*.java" > sources.txt && javac -d out @sources.txt

CMD ["java", "-cp", "out", "com.mycompany.shadowhackers1.Server"]
