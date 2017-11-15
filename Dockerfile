FROM java:8
VOLUME /tmp
ADD /target/51alp-backend-0.0.1.war app.war
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS  -jar /app.war