@echo off
@echo ---------Project compile from terminal WITHOUT ANT and MAVEN---------
@echo .
@echo ---------Run compile from terminal---------
@echo .
javac -sourcepath ./src -d build/classes -cp ./libs/json-cdc-1.0.jar;./libs/commons-lang3-3.11.jar src/code/other/First.java src/code/other/Second.java src/code/Main.java
@echo .
@echo ---------Run program from terminal---------
@echo .
java -cp build/classes/;./libs/json-cdc-1.0.jar;./libs/commons-lang3-3.11.jar;. code.Main
pause