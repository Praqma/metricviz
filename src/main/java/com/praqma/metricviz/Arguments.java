package com.praqma.metricviz;

/**
 * Holds program arguments.
 */
final class Arguments {

  private static final String KEY_INPUT = "--input=";
  private static final String KEY_OUTPUT = "--output=";
  private static final String USAGE = "Usage: metricviz --input=<input-file> --output=<output-file>";

  private String inputFilePath;
  private String outputFilePath;

  /**
   * Read command line arguments.
   */
  static Arguments get(String[] args) {
    Arguments arguments = new Arguments();
    for (String arg : args) {
      if (arg.startsWith(KEY_INPUT)) {
        arguments.inputFilePath = arg.substring(KEY_INPUT.length());
      } else if (arg.startsWith(KEY_OUTPUT)) {
        arguments.outputFilePath = arg.substring(KEY_OUTPUT.length());
      }
    }

    validate(arguments);
    return arguments;
  }

  private static void validate(Arguments arguments) {
    if (arguments.inputFilePath == null) {
      throw new IllegalArgumentException(USAGE);
    }
    if (arguments.outputFilePath == null) {
      throw new IllegalArgumentException(USAGE);
    }
  }

  String getInputFilePath() {
    return inputFilePath;
  }

  String getOutputFilePath() {
    return outputFilePath;
  }
  
}
