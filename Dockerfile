FROM maven:latest
RUN mkdir /testorder
WORKDIR /testorder
COPY . .
EXPOSE 8080
CMD [ "mvn", "spring-boot:run" ]