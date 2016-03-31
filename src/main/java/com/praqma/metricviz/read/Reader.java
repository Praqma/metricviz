package com.praqma.metricviz.read;

import java.io.IOException;

import com.praqma.metricviz.model.FileMetric;
import com.praqma.metricviz.model.Topic;

/**
 * Reads input files containing code metrics.
 */
public interface Reader extends AutoCloseable {

  /**
   * Reads the topic of the input data. Must be called before {@code read}.
   *
   * @return The topic containing titles of the data values.
   * @throws IOException if the input file cannot be read
   * @throws ReaderException if a parse error occurs
   */
  Topic readTopic() throws IOException, ReaderException;

  /**
   * Reads the next code metric.
   *
   * @return The next metric, or {@code null} if the end of the input has been reached.
   * @throws IOException if the input file cannot be read
   * @throws ReaderException if a parse error occurs
   */
  FileMetric read() throws IOException, ReaderException;

}
