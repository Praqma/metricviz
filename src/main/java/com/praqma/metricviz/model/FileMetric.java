package com.praqma.metricviz.model;

/**
 * Code metrics for a source file.
 */
public class FileMetric {

  private final String filename;
  private final String parent;
  private final int size;
  private final int metric;

  public FileMetric(String filename, String parent, int size, int metric) {
    this.filename = filename;
    this.parent = parent;
    this.size = size;
    this.metric = metric;
  }

  public String getFilename() {
    return filename;
  }

  public String getParent() {
    return parent;
  }

  public int getSize() {
    return size;
  }

  public int getMetric() {
    return metric;
  }

}
