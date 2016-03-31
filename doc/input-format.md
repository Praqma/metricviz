# Input file format

The input file must be a CSV file with lines having at least these colums:

* Filename
  * File, folder or project name
* Parent
  * Name of parent folder
  * Files and folders either belong to the project or a folder
  * Set parent to `null` for the project entry, which has no parent
* Size
  * A number determining the size of the item's box in the treemap
  * For example lines of code
* Metric
  * A code metric represented as a number
  * Determines the item's color
  * For example code complexity or number of duplicated lines

## Example

An example CSV file is here: [input-example.csv](input-example.csv).
