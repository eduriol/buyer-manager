FROM maven:3.6.1-jdk-8
VOLUME /tmp
ADD . / buyer-manager/
EXPOSE 8081
CMD (cd buyer-manager/; mvn spring-boot:run -Denvironment=compose;)
