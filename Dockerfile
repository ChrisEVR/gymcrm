FROM tomcat:10.1.15-jdk21
ADD ./target/gymcrm.war /usr/local/tomcat/webapps/ROOT.war
COPY ./reports_server.xml /usr/local/tomcat/conf/server.xml
CMD ["catalina.sh", "run"]
