@echo off
@echo .
@echo ---------MVN project (my version java - 11)---------
@echo .
@echo ---------DELETE BUILD AND CREATE JAR FILE---------
@echo .
call mvn clean install
@echo .
@echo ---------RUN PROGRAM (!!!ANSI encoding is used!!!)---------
@echo .
java -jar target/module_2-1.0-SNAPSHOT.jar
pause