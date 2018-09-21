@echo off
title Konkordans
set CLASSPATH=.
:start
set arg1=%1
javac tBuild.java
java tBuild
javac tKonkordans.java
java tKonkordans %arg1%
pause
goto start
