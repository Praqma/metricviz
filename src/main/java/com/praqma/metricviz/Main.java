package com.praqma.metricviz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.praqma.metricviz.read.Reader;
import com.praqma.metricviz.read.ReaderFactory;
import com.praqma.metricviz.write.HtmlWriter;
import com.praqma.metricviz.write.Writer;

/**
 * Entry point for the metricviz application.
 */
public class Main {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) throws Exception {
    logger.info("metricviz - started");

    try {
      Arguments arguments = Arguments.get(args);

      try (Reader reader = ReaderFactory.getReader(arguments.getInputFilePath());
          Writer writer = new HtmlWriter(arguments.getOutputFilePath())) {
        Converter converter = new Converter(reader, writer);
        converter.convert();
      }

    } catch (Exception e) {
      logger.error("metricviz - Error", e);
    }

    logger.info("metricviz - done");
  }

}
