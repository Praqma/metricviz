package com.praqma.metricviz;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.InputStream;

import org.junit.Test;

/**
 * Test that resources can be read from jar.
 */
public class ResourcesTest {

  @Test
  public void testGetHtmlHeader() throws Exception {
    try (InputStream stream = Resources.getHtmlHeader()) {
      assertNotNull("HTML header stream not found", stream);
    }
  }

  @Test
  public void testGetHtmlFooter() throws Exception {
    try (InputStream stream = Resources.getHtmlFooter()) {
      assertNotNull("HTML footer stream not found", stream);
    }
  }

}
