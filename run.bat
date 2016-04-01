@echo off

:: This script uses the Gradle wrapper. The Java program can also be run with arguments
:: like this (but then you need to set the classpath):
:: java -jar build\libs\metricviz-0.1.0.jar --input=doc\input-example.csv --output=output.html

gradlew.bat run -Pinput=doc\input-example.csv -Poutput=output.html
