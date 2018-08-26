FROM 		tomee:8-jre-7.0.5-plume
COPY 		./target/tomee-test-1.0.0.war /usr/local/tomee/webapps/tomee-test-1.0.0.war
CMD ["catalina.sh", "run"]

# build via docker build .
# start via docker run --rm -p 8888:8080 fe37c5a33228
# call via: http://localhost:8888/tomee-test-1.0.0/greeting/
# stop via docker stop festive_edison