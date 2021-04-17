@echo off
@echo .
@echo ---------MVN project (my version java - 11)---------
@echo .
@echo ---------Delete build and create jar file---------
@echo .
call mvn clean install
@echo .
@echo ---------Run program---------
@echo .
java -jar app_library_presentation/target/app_library_presentation-1.0-SNAPSHOT.jar
pause