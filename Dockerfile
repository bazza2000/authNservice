FROM java:8
WORKDIR /
ADD target/authNservice-1.0.0.jar .
EXPOSE 8080
CMD java -jar authNservice-1.0.0.jar
