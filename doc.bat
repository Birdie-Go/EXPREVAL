@echo off
cd src
javadoc -private -author -version -d ..\doc -classpath ..\bin parser\*.java scanner\*.java token\*.java parser\expr\*.java scanner\dfa\*.java
cd ..
pause
@echo on
