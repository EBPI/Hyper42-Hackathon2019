FROM openjdk:11-jre
ARG APP_FILE=specified_in_pom.jar
ENV APPLICATION $APP_FILE
ENV APP_HOME /app
ENV SPRING_CONFIG_LOCATION classpath:/config/,file:./config/
ENV SPRING_CONFIG_NAME application,infra,logback.xml
VOLUME $APP_HOME/config
ADD src/test/resources/infra.properties $APP_HOME/config/
ADD target/credentials/user1 $APP_HOME/config/credentials/user1
EXPOSE 7090
COPY target/$APP_FILE $APP_HOME/
WORKDIR /app
CMD /usr/bin/java -Djavax.net.ssl.trustStore=/crypto-config/hyper42.jks -Djavax.net.ssl.trustStorePassword=changeit -jar $APPLICATION