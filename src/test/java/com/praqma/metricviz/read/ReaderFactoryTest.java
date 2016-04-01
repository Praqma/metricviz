package com.praqma.metricviz.read;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

public class ReaderFactoryTest {

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Rule
  public final TemporaryFolder folder = new TemporaryFolder();

  @Test
  public void testCsvIsSupported() throws Exception {
    File file = folder.newFile("input.csv");
    Reader reader = ReaderFactory.getReader(file.getAbsolutePath());
    assertThat("Expected a CSV reader instance", reader, instanceOf(CsvReader.class));
  }

  @Test
  public void testXmlIsNotSupported() throws Exception {
    File file = folder.newFile("input.xml");
    exception.expect(ReaderException.class);
    exception.expectMessage("file extension");
    ReaderFactory.getReader(file.getAbsolutePath());
  }

  @Test
  public void testFileMustExist() throws Exception {
    exception.expect(ReaderException.class);
    exception.expectMessage("does not exist");
    ReaderFactory.getReader("betThisIsntARealFilename");
  }

  @Test
  public void testFileCannotBeDirectory() throws Exception {
    File subfolder = folder.newFolder();
    exception.expect(ReaderException.class);
    exception.expectMessage("is a directory");
    ReaderFactory.getReader(subfolder.getAbsolutePath());
  }

}
