FROM 		tomee:8-jre-7.0.5-plume
COPY 		./target/tomee-test-1.0.0.war /usr/local/tomee/webapps/tomee-test-1.0.0.war
CMD ["catalina.sh", "run"]

# build via docker build .
# start locally via docker run --rm -p 8888:8080 <fe37c5a33228>
# push to docker hub: docker push clemensx/tomeetest
# deploy to CF: cf push tomeetest --docker-image clemensx/tomeetest
# --> delete app if deploy not working
# call via: https://rest-cdi.cfapps.sap.hana.ondemand.com/tomee-test-1.0.0/greeting
# further links: https://docs.cloudfoundry.org/devguide/deploy-apps/push-docker.html
