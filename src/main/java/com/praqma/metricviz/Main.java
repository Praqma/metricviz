package com.praqma.metricviz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Entry point for the metricviz application.
 */
public class Main {

  private static final String DATA =
      "['Filename',       'Parent', 'Lines of code', 'Complexity']," +
      "['my-project',             null,           0,            0]," +

      "['/',              'my-project',           0,            0]," +
      "['http',           'my-project',           0,            0]," +
      "['util',           'my-project',           0,            0]," +
      "['xml',            'my-project',           0,            0]," +

      "['main.c',         '/',                   11,           10]," +
      "['api.h',          '/',                   52,           31]," +
      "['api.c',          '/',                   24,           12]," +
      "['controller.c',   '/',                   16,          -23]," +
      "['httpReader.h',   'http',                42,          -11]," +
      "['httpReader.c',   'http',                31,           -2]," +
      "['httpRequest.h',  'http',                22,          -13]," +
      "['httpWriter.h',   'http',                17,            4]," +
      "['httpWriter.c',   'http',                21,           -5]," +
      "['fileUtil.c',     'util',                36,            4]," +
      "['ioUtil.c',       'util',                20,          -12]," +
      "['constants.h',    'util',                40,           63]," +
      "['versions.h',     'util',                 4,           34]," +
      "['xmlParser.h',    'xml',                  1,           -5]," +
      "['xmlParser.c',    'xml',                 12,           24]," +
      "['xmlDsig.h',      'xml',                 18,           13]," +
      "['xmlDsig.c',      'xml',                 11,          -52]," +
      "['saxParser.c',    'xml',                 21,            0]," +
      "['domParser.c',    'xml',                 30,           43],";

  public static void main(String[] args) throws Exception {
    System.out.println("metricviz - started");

    try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get("output.html")))) {
      InputStream header = Main.class.getResourceAsStream("/header.html.partial");
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(header))) {
        String line = reader.readLine();
        while (line != null) {
          writer.println(line);
          line = reader.readLine();
        }
      }

      writer.println(DATA);

      InputStream footer = Main.class.getResourceAsStream("/footer.html.partial");
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(footer))) {
        String line = reader.readLine();
        while (line != null) {
          writer.println(line);
          line = reader.readLine();
        }
      }
    }

    System.out.println("metricviz - done");
  }

}
