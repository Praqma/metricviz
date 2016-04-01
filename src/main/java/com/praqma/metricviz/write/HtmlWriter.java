package com.praqma.metricviz.write;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.praqma.metricviz.Resources;
import com.praqma.metricviz.model.FileMetric;
import com.praqma.metricviz.model.Topic;

/**
 * Writes HTML output.
 */
public class HtmlWriter implements Writer {

  private static final Logger logger = LoggerFactory.getLogger(HtmlWriter.class);

  private final String outputPath;

  /** Underlying writer. */
  private PrintWriter writer;

  public HtmlWriter(String outputPath) {
    this.outputPath = outputPath;
    logger.info("Writing output to {}", outputPath);
  }

  @Override
  public void close() {
    if (writer != null) {
      writer.close();
    }
  }

  @Override
  public void writeHeader() throws IOException {
    openWriterIfNeeded();
    InputStream header = Resources.getHtmlHeader();
    IOUtils.copy(header, writer);
  }

  @Override
  public void writeTopic(Topic topic) throws IOException {
    openWriterIfNeeded();
    String data = String.format("['%s', '%s', '%s', '%s'],",
        topic.getNodeId(),
        topic.getParentId(),
        topic.getSizeId(),
        topic.getColorId());
    writer.println(data);
  }

  @Override
  public void write(FileMetric fileMetric) throws IOException {
    openWriterIfNeeded();
    String data = String.format("['%s', %s, %d, %d],",
        fileMetric.getFilename(),
        formatParent(fileMetric),
        fileMetric.getSize(),
        fileMetric.getMetric());
    writer.println(data);
  }

  private String formatParent(FileMetric fileMetric) {
    String parent = fileMetric.getParent();
    if (parent.isEmpty() || parent.equals("null")) {
      return "null";
    }
    return String.format("'%s'", parent);
  }

  @Override
  public void writeFooter() throws IOException {
    openWriterIfNeeded();
    InputStream footer = Resources.getHtmlFooter();
    IOUtils.copy(footer, writer);
  }

  private void openWriterIfNeeded() throws IOException {
    if (writer == null) {
      writer = new PrintWriter(Files.newBufferedWriter(Paths.get(outputPath)));
    }
  }

}
