package com.praqma.metricviz.write;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.praqma.metricviz.model.FileMetric;
import com.praqma.metricviz.model.Topic;

public class HtmlWriterTest {

  @Rule
  public final TemporaryFolder folder = new TemporaryFolder();

  private String outputFilePath;
  private HtmlWriter subject;

  @Before
  public void before() throws Exception {
    File outputFile = folder.newFile("output.html");
    outputFilePath = outputFile.getAbsolutePath();
    subject = new HtmlWriter(outputFilePath);
  }

  @Test
  public void testWriteTopic() throws Exception {
    Topic topic = new Topic("nodeId", "parentId", "sizeId", "colorId");
    subject.writeTopic(topic);
    subject.close();

    List<String> lines = Files.readAllLines(Paths.get(outputFilePath));
    assertEquals("Line count", 1, lines.size());
    assertEquals("Topic", "['nodeId', 'parentId', 'sizeId', 'colorId'],", lines.get(0));
  }

  @Test
  public void testWrite() throws Exception {
    FileMetric fileMetric = new FileMetric("main.c", "/", 123, 45);
    subject.write(fileMetric);
    subject.close();

    List<String> lines = Files.readAllLines(Paths.get(outputFilePath));
    assertEquals("Line count", 1, lines.size());
    assertEquals("File metric", "['main.c', '/', 123, 45],", lines.get(0));
  }

  @Test
  public void testWriteParentEmpty() throws Exception {
    FileMetric fileMetric = new FileMetric("main.c", "", 123, 45);
    subject.write(fileMetric);
    subject.close();

    List<String> lines = Files.readAllLines(Paths.get(outputFilePath));
    assertEquals("Line count", 1, lines.size());
    assertEquals("File metric", "['main.c', null, 123, 45],", lines.get(0));
  }

  @Test
  public void testWriteParentNull() throws Exception {
    FileMetric fileMetric = new FileMetric("main.c", "null", 123, 45);
    subject.write(fileMetric);
    subject.close();

    List<String> lines = Files.readAllLines(Paths.get(outputFilePath));
    assertEquals("Line count", 1, lines.size());
    assertEquals("File metric", "['main.c', null, 123, 45],", lines.get(0));
  }

}
