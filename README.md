# Code Metrics Visualization POC

A tool for generating HTML visualizations of code metrics. As a proof of concept, the
first visualization is to create treemaps of CSV data like in this example:

![Example metrics](http://www.josra.org/images/metrics.png)

See also the description in [Josra](http://www.josra.org/sow/codeviz.html)


## Deliverables

The proof of concept implementation will include:

* A program to generate the correct HTML/JavaScript page for a given CSV file
* The output will be suitable for static websites and for publishing in Jenkins
* Usage documentation
* Unit tests
* A continuous delivery pipeline for the software


## Input formats

The proof of concept supports a CSV input file as described in
[docs/input-format.md](docs/input-format.md). The tool may later be extended to support
other structured formats like JSON or XML. The support may be driven by the choice of
code analysis tools used to generate data.
