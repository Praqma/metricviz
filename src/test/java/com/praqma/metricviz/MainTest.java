package com.praqma.metricviz;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * End to end acceptance test.
 */
public class MainTest {

  @Rule
  public final TemporaryFolder folder = new TemporaryFolder();

  @Test
  public void testMain() throws Exception {
    File inputFile = new File(MainTest.class.getResource("/input.csv").getFile());
    File outputFile = folder.newFile("output.html");
    String[] args = {
        "--input=" + inputFile.getAbsolutePath(),
        "--output=" + outputFile.getAbsolutePath() };
    Main.main(args);

    // Validate that it is HTML, resembling something expected
    Document htmlDoc = Jsoup.parse(outputFile, "UTF-8");
    assertNotNull("Null HTML document", htmlDoc);
    String outerHtml = htmlDoc.outerHtml();

    String header;
    try (InputStream stream = Resources.getHtmlHeader()) {
      header = IOUtils.toString(stream);
    }
    String footer;
    try (InputStream stream = Resources.getHtmlFooter()) {
      footer = IOUtils.toString(stream);
    }

    outerHtml = removeWhitespace(outerHtml);
    header = removeWhitespace(header);
    footer = removeWhitespace(footer);

    assertThat("Header not found in output", outerHtml, containsString(header));
    assertThat("Footer not found in output", outerHtml, containsString(footer));
  }

  private static String removeWhitespace(String str) {
    return str.replaceAll("\\s+", "");
  }

}
