FROM tomcat:10.0.7-jdk16-openjdk

COPY target/unit_16_servlets.war /usr/local/tomcat/webapps/servlets.war

EXPOSE 8080
CMD ["catalina.sh", "run"]