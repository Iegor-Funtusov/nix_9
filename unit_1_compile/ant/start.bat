call ..\apache-ant\setantenv.bat

cd .\project

call ant clean
call ant compile
call ant jar
call ant run

rem call ant clean compile jar run