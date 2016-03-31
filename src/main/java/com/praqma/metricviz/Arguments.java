package com.praqma.metricviz;

/**
 * Holds program arguments.
 */
final class Arguments {

  private final String inputFilePath;

  private Arguments(String inputFilePath) {
    this.inputFilePath = inputFilePath;
  }

  /**
   * Read command line arguments.
   */
  static Arguments get(String[] args) {
    if (args.length < 1) {
      throw new IllegalArgumentException("Usage: metricviz <input-file>");
    }
    String inputFilePath = args[0];
//    logger.info("Input file path: {}", inputFilePath);
    return new Arguments(inputFilePath);
  }

  String getInputFilePath() {
    return inputFilePath;
  }

}
