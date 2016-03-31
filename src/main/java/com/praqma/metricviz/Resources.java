package com.praqma.metricviz;

import java.io.InputStream;

/**
 * Utility to load resources from the jar.
 */
public final class Resources {

  /**
   * Get the HTML header snippet used before the treemap data.
   *
   * @return An input stream which the caller is expected to close.
   */
  public static InputStream getHtmlHeader() {
    InputStream stream = Resources.class.getResourceAsStream("/header.html.partial");
    return stream;
  }

  /**
   * Get the HTML footer snippet used after the treemap data.
   *
   * @return An input stream which the caller is expected to close.
   */
  public static InputStream getHtmlFooter() {
    InputStream stream = Resources.class.getResourceAsStream("/footer.html.partial");
    return stream;
  }

}
