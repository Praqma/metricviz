package com.praqma.metricviz;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.praqma.metricviz.write.HtmlWriter;

/**
 * Entry point for the metricviz application.
 */
public class Main {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) throws Exception {
    logger.info("metricviz - started");

    if (args.length < 1) {
      logger.error("Usage: metricviz <input-file>");
      return;
    }
    String inputFilePath = args[0];
    logger.info("Input file path: {}", inputFilePath);

    try {
      Path outputPath = Paths.get("output.html");
      HtmlWriter.write(outputPath);
    } catch (Exception e) {
      logger.error("metricviz - Error", e);
    }

    logger.info("metricviz - done");
  }

}
