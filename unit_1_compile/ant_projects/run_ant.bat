@echo off
@echo ---------Setting ant---------
call setantenv.bat
@echo .
@echo ---------ANT project---------
@echo .
@echo ---------Delete build---------
@echo .
call ant clean
@echo .
@echo ---------Run compile---------
@echo .
call ant compile
@echo .
@echo ---------Create jar---------
@echo .
call ant jar
@echo .
@echo ---------Run program---------
@echo .
call ant run
pause