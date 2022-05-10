@echo off
cd src
javac -d ..\bin -classpath ..\bin parser\*.java scanner\*.java token\*.java parser\expr\*.java scanner\dfa\*.java
cd ..
cd bin
java ExprEval
cd ..
@echo on
