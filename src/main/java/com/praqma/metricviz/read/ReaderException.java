package com.praqma.metricviz.read;

/**
 * Indicates that an error happened while reading input files.
 */
public class ReaderException extends Exception {

  private static final long serialVersionUID = 1;

  ReaderException(String message) {
    super(message);
  }

  ReaderException(String message, Throwable cause) {
    super(message, cause);
  }

}
