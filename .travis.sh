#!/bin/bash
set -e
mvn install -DskipTests=true -Dmaven.javadoc.skip=true -B -V
mvn test -B

# Line of code stats
cloc --quiet --xml --out=build/reports/cloc.xml $(find -type d -name main | grep src/main)