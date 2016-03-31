package com.praqma.metricviz;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.praqma.metricviz.write.HtmlWriter;

/**
 * Entry point for the metricviz application.
 */
public class Main {

  public static void main(String[] args) throws Exception {
    System.out.println("metricviz - started");

    try {
      Path outputPath = Paths.get("output.html");
      System.out.println("Writing output to " + outputPath);
      HtmlWriter.write(outputPath);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("metricviz - Error: " + e.getMessage());
    }

    System.out.println("metricviz - done");
  }

}
