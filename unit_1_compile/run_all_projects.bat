@echo off
cd compile_from_terminal
call run.bat
cd ..\ant_projects
call run_ant.bat
cd ..\maven_projects\test
call run_mvn.bat
cd ..
