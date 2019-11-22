@echo off

javac -d ..\bin ua\nure\chekhunov\practice1\*.java

java -cp ..\bin ua.demo.Demo

del ..\bin\ua\nure\chekhunov\practice1\*.class
