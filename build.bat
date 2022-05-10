@echo off
cd src
javac -d ..\bin -classpath ..\bin parser\*.java scanner\*.java token\*.java parser\expr\*.java scanner\dfa\*.java
cd ..
pause
@echo on
