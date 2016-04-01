package com.praqma.metricviz;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ArgumentsTest {

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  /**
   * Arguments can be parsed.
   */
  @Test
  public void testSunshine() {
    String[] args = { "--input=king", "--output=julian" };
    Arguments arguments = Arguments.get(args);
    assertEquals("Invalid input file path", "king", arguments.getInputFilePath());
    assertEquals("Invalid output file path", "julian", arguments.getOutputFilePath());
  }

  @Test
  public void testInputIsRequired() {
    String[] args = { "--output=solomio" };
    exception.expect(IllegalArgumentException.class);
    Arguments.get(args);
  }

  @Test
  public void testOutputIsRequired() {
    String[] args = { "--input=solomio" };
    exception.expect(IllegalArgumentException.class);
    Arguments.get(args);
  }

}
