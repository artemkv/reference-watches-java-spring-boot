FROM openjdk:8-jre

RUN mkdir -p /usr/local/bin/watches/logs

WORKDIR /usr/local/bin/watches

ARG JAR_FILE

COPY target/${JAR_FILE} /usr/local/bin/watches/watches-service.jar

EXPOSE 8500

ENV SERVER_ADDRESS=0.0.0.0
ENV SERVER_PORT=8500
ENV LOGGING_PATH=/usr/local/bin/watches/logs
ENV JAVA_OPTIONS -Xmx256m -Xms256m

CMD java $JAVA_OPTIONS -jar /usr/local/bin/watches/watches-service.jar