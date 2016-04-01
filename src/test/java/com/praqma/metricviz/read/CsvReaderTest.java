package com.praqma.metricviz.read;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import com.praqma.metricviz.model.FileMetric;
import com.praqma.metricviz.model.Topic;

public class CsvReaderTest {

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Rule
  public final TemporaryFolder folder = new TemporaryFolder();

  private CsvReader subject;

  @Before
  public void before() {
    subject = getDefaultReader();
  }

  @After
  public void after() throws Exception {
    assertNotNull("Subject not created by test case", subject);
    subject.close();
  }

  @Test
  public void testReadTopic() throws Exception {
    Topic topic = subject.readTopic();
    assertNotNull("Topic is null", topic);
    assertEquals("Invalid node ID", "Filename", topic.getNodeId());
    assertEquals("Invalid parent ID", "Parent", topic.getParentId());
    assertEquals("Invalid size ID", "Size", topic.getSizeId());
    assertEquals("Invalid color ID", "Metric", topic.getColorId());
  }

  @Test
  public void testReadTopicNotEnoughFields() throws Exception {
    subject = getReader("readTopicNotEnoughFields.csv");
    exception.expect(ReaderException.class);
    exception.expectMessage("parse error");
    exception.expectMessage("line: 1");
    subject.readTopic();
  }

  @Test
  public void testRead() throws Exception {
    subject.readTopic(); // Must be done first
    FileMetric fileMetric = subject.read();
    assertNotNull("File metric is null", fileMetric);
    assertEquals("Invalid filename", "my-project", fileMetric.getFilename());
    assertEquals("Invalid parent", "null", fileMetric.getParent());
    assertEquals("Invalid size", 0, fileMetric.getSize());
    assertEquals("Invalid metric", 0, fileMetric.getMetric());
  }

  @Test
  public void testReadNotEnoughFields() throws Exception {
    subject = getReader("readNotEnoughFields.csv");
    subject.readTopic(); // Must be done first
    exception.expect(ReaderException.class);
    exception.expectMessage("parse error");
    exception.expectMessage("line: 2");
    subject.read();
  }

  @Test
  public void testReadSizeNotInteger() throws Exception {
    subject = getReader("readSizeNotInteger.csv");
    subject.readTopic(); // Must be done first
    exception.expect(ReaderException.class);
    exception.expectMessage("size");
    exception.expectMessage("line: 2");
    subject.read();
  }

  @Test
  public void testReadMetricNotInteger() throws Exception {
    subject = getReader("readMetricNotInteger.csv");
    subject.readTopic(); // Must be done first
    exception.expect(ReaderException.class);
    exception.expectMessage("metric");
    exception.expectMessage("line: 2");
    subject.read();
  }

  /**
   * It is not OK to read metrics without having read the topic.
   */
  @Test
  public void testReadFileMetricBeforeTopic() throws Exception {
    exception.expect(NullPointerException.class); // Because underlying reader is null
    subject.read();
  }

  /**
   * It is OK to close a reader that has not read anything.
   */
  @Test
  public void testCloseBeforeRead() throws Exception {
    new CsvReader(new File("")).close();
  }

  /**
   * It is OK to close a reader more than once.
   */
  @Test
  public void testCloseIsIdempotent() throws Exception {
    subject.close();
    subject.close();
  }

  private static CsvReader getReader(String inputFileName) {
    File input = new File(CsvReaderTest.class.getResource("/csvReaderTest/" + inputFileName).getFile());
    return new CsvReader(input);
  }

  /** For sunshine tests or tests that do not depend on the file contents. */
  private static CsvReader getDefaultReader() {
    return getReader("input.csv");
  }

}
