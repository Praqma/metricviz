#!/bin/bash

## This script uses the Gradle wrapper to run the program.
## You can also run standalone like this:
## $ ./gradlew installApp
## $ build/install/metricviz/bin/metricviz --input=doc/input-example.csv --output=output.html

./gradlew run -Pinput=doc/input-example.csv -Poutput=output.html
