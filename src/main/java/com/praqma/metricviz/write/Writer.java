package com.praqma.metricviz.write;

import java.io.IOException;

import com.praqma.metricviz.model.FileMetric;
import com.praqma.metricviz.model.Topic;

/**
 * Writes an output file.
 */
public interface Writer extends AutoCloseable {

  /**
   * Writes header content before code metric data.
   */
  void writeHeader() throws IOException;

  /**
   * Writes an initial row of titles in the code metric data table.
   */
  void writeTopic(Topic topic) throws IOException;

  /**
   * Writes a code metric.
   *
   * @param fileMetric Code metric to write
   */
  void write(FileMetric fileMetric) throws IOException;

  /**
   * Writes footer content after code metric data.
   */
  void writeFooter() throws IOException;

}
