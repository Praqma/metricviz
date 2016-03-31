package com.praqma.metricviz.read;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.praqma.metricviz.model.FileMetric;
import com.praqma.metricviz.model.Topic;

/**
 * Reads code metrics from CSV files.
 */
class CsvReader implements Reader {

  private static final Logger logger = LoggerFactory.getLogger(CsvReader.class);

  private final File file;

  /** Underlying reader. */
  private BufferedReader reader;

  private int lineNumber;

  CsvReader(File file) {
    this.file = file;
    logger.info("Reading input {}", file);
  }

  @Override
  public void close() throws IOException {
    if (reader != null) {
      reader.close();
    }
  }

  @Override
  public Topic readTopic() throws IOException, ReaderException {
    reader = Files.newBufferedReader(file.toPath());
    String line = reader.readLine();
    ++lineNumber;
    if (line == null) {
      return null;
    }
    Topic topic = parseTopic(line);
    return topic;
  }

  @Override
  public FileMetric read() throws IOException, ReaderException {
    String line = reader.readLine();
    ++lineNumber;
    if (line == null) {
      return null;
    }
    FileMetric fileMetric = parse(line);
    return fileMetric;
  }

  private Topic parseTopic(String line) throws ReaderException {
    String[] parts = parseParts(line);
    String nodeId = parts[0].trim();
    String parentId = parts[1].trim();
    String sizeId = parts[2].trim();
    String colorId = parts[3].trim();
    return new Topic(nodeId, parentId, sizeId, colorId);
  }

  private FileMetric parse(String line) throws ReaderException {
    String[] parts = parseParts(line);
    String filename = parts[0].trim();
    String parent = parts[1].trim();

    int size;
    try {
      size = Integer.parseInt(parts[2].trim());
    } catch (NumberFormatException e) {
      throw new ReaderException(
          "CSV parse error: Invalid size value, line: " + lineNumber, e);
    }

    int metric;
    try {
      metric = Integer.parseInt(parts[3].trim());
    } catch (NumberFormatException e) {
      throw new ReaderException(
          "CSV parse error: Invalid metric value, line: " + lineNumber, e);
    }

    return new FileMetric(filename, parent, size, metric);
  }

  private String[] parseParts(String line) throws ReaderException {
    String[] parts = line.split(",");
    if (parts.length < 4) {
      throw new ReaderException("CSV parse error, line: " + lineNumber);
    }
    return parts;
  }

}
