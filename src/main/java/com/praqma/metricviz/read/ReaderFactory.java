package com.praqma.metricviz.read;

import java.io.File;

/**
 * Creates input file readers.
 */
public final class ReaderFactory {

  /**
   * Create an input file reader based on the file extension.
   *
   * @param inputFilePath The input file
   * @return A reader
   * @throws ReaderException if the input file extension is not supported
   */
  public static Reader getReader(String inputFilePath) throws ReaderException {
    File file = new File(inputFilePath);
    if (!file.exists()) {
      throw new ReaderException("Input file " + inputFilePath + " does not exist.");
    }
    if (file.isDirectory()) {
      throw new ReaderException("Input file " + inputFilePath + " is a directory.");
    }

    if (inputFilePath.endsWith(".csv")) {
      return new CsvReader(file);
    } else {
      throw new ReaderException("Input file " + inputFilePath +
          " does not have .csv file extension.");
    }
  }
}
