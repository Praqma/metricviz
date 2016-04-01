package com.praqma.metricviz;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.praqma.metricviz.model.FileMetric;
import com.praqma.metricviz.model.Topic;
import com.praqma.metricviz.read.Reader;
import com.praqma.metricviz.write.Writer;

public class ConverterTest {

  class TestReader implements Reader {
    private Topic topic;
    private FileMetric[] fileMetrics;
    private int index;

    TestReader(Topic topic, FileMetric[] fileMetrics) {
      this.topic = topic;
      this.fileMetrics = fileMetrics;
      this.index = 0;
    }

    @Override
    public Topic readTopic() {
      return topic;
    }

    @Override
    public FileMetric read() {
      if (index == fileMetrics.length) {
        return null;
      }
      return fileMetrics[index++];
    }

    @Override
    public void close() {
      // Do nothing
    }
  }

  class TestWriter implements Writer {
    Topic topic = null;
    List<FileMetric> fileMetrics = new ArrayList<FileMetric>();

    @Override
    public void writeHeader() {
      // Do nothing
    }

    @Override
    public void writeTopic(Topic topic) {
      this.topic = topic;
    }

    @Override
    public void write(FileMetric fileMetric) {
      fileMetrics.add(fileMetric);
    }

    @Override
    public void writeFooter() {
      // Do nothing
    }

    @Override
    public void close() {
      // Do nothing
    }
  }

  /**
   * A converter gives the writer all the data of the reader.
   */
  @Test
  public void testConvert() throws Exception {
    Topic topic = new Topic("nodeId", "parentId", "sizeId", "colorId");
    FileMetric[] fileMetrics = {
        new FileMetric("one", "parentOne", 12, 34),
        new FileMetric("two", "parentTwo", 56, 78)
    };
    Reader reader = new TestReader(topic, fileMetrics);
    TestWriter writer = new TestWriter();

    Converter subject = new Converter(reader, writer);
    subject.convert();

    assertEquals("Topic", topic, writer.topic);
    assertEquals("File metrics", Arrays.asList(fileMetrics), writer.fileMetrics);
  }

}
