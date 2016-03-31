package com.praqma.metricviz;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.praqma.metricviz.model.FileMetric;
import com.praqma.metricviz.model.Topic;
import com.praqma.metricviz.read.Reader;
import com.praqma.metricviz.read.ReaderException;
import com.praqma.metricviz.write.Writer;

/**
 * Converts an input file to an output file.
 */
class Converter {

  private static final Logger logger = LoggerFactory.getLogger(Converter.class);

  private final Reader reader;
  private final Writer writer;

  Converter(Reader reader, Writer writer) {
    this.reader = reader;
    this.writer = writer;
  }

  void convert() throws IOException, ReaderException {
    writer.writeHeader();

    Topic topic = reader.readTopic();
    if (topic == null) {
      logger.warn("Input file seems to be empty. Data and footer not written.");
      return;
    }
    writer.writeTopic(topic);

    FileMetric fileMetric = reader.read();
    while (fileMetric != null) {
      writer.write(fileMetric);
      fileMetric = reader.read();
    }

    writer.writeFooter();
  }

}
