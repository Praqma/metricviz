# Code Metrics Visualization POC

A tool for generating HTML visualizations of code metrics. As a proof of concept, the
first visualization is to create treemaps of CSV data like in this example:

![Example metrics](http://www.josra.org/images/metrics.png)

See also the description in [Josra](http://www.josra.org/sow/codeviz.html)


## Quick start

```sh
$ ./run.sh
```

or

```sh
> run.bat
```

The `run` script runs the tool with [doc/input-example.csv](doc/input-example.csv) as
input and produces output in `output.html`.


## Usage

The tool is built and run using Gradle. You do not need to install Gradle, because the
Gradle wrapper is used.

The example commands in this section use shell scripts, but there are Windows batch
equivalents (with `.bat` extension).

### Building

```sh
$ ./gradlew installDist
```

Produces an executable in `build/install/metricviz`.

### Running

```sh
$ cd build/install/metricviz/bin/
$ ./metricviz --input=<input-file.csv> --output=<output-file.html>
```


## Input format

The proof of concept supports a CSV input file as described in
[doc/input-format.md](doc/input-format.md). The tool may later be extended to support
other structured formats like JSON or XML. The support may be driven by the choice of
code analysis tools used to generate data.


## Output format

The tool writes an HTML file that uses
[Google Treemaps](https://developers.google.com/chart/interactive/docs/gallery/treemap#overview)
to create a drill-down treemap visualization of the input.


## Javadoc

```sh
$ ./gradlew javadoc
$ cd build/docs/javadoc/
```
