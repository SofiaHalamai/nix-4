@echo off
@echo .
@echo ---------MVN project (my version java - 11)---------
@echo .
@echo ---------Delete build and create jar file---------
@echo .
call mvn clean install
cd ./app_reverse_string
@echo .
@echo ---------Run program---------
@echo .
java -jar target/app_reverse_string-1.0-SNAPSHOT.jar
pause