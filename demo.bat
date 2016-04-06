@echo off

:: This script uses the Gradle wrapper to run a simple demo.
:: You can also run standalone like this:
:: > gradlew.bat installDist
:: > build\install\metricviz\bin\metricviz.bat --input=doc\input-example.csv --output=output.html

gradlew.bat run -Pinput=doc\input-example.csv -Poutput=output.html
