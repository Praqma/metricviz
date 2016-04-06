#!/bin/bash

## This script uses the Gradle wrapper to run a simple demo.
## You can also run standalone like this:
## $ ./gradlew installDist
## $ build/install/metricviz/bin/metricviz --input=doc/input-example.csv --output=output.html

./gradlew run -Pinput=doc/input-example.csv -Poutput=output.html
