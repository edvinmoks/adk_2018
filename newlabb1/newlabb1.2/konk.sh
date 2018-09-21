#!/bin/bash
#give permission: chmod +x myscript.sh
#run: ./auto.sh
word=$1
javac tKonkordans.java
java tKonkordans ${word}
set ff=unix

