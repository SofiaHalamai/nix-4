@echo off
@echo ---------Setting maven---------
call setmaven.bat
@echo .
@echo ---------MVN project---------
@echo .
@echo ---------Delete build and create jar file---------
@echo .
call mvn clean install
@echo .
@echo ---------Run program---------
@echo .
java -jar target/test-1.0-SNAPSHOT.jar
pause
