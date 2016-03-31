package com.praqma.metricviz.model;

/**
 * Describes the metric data. Used as the first row in a tree map's data table.
 */
public class Topic {

  private final String nodeId;
  private final String parentId;
  private final String sizeId;
  private final String colorId;

  public Topic(String nodeId, String parentId, String sizeId, String colorId) {
    this.nodeId = nodeId;
    this.parentId = parentId;
    this.sizeId = sizeId;
    this.colorId = colorId;
  }

  /**
   * The column title for nodes, for example 'Filename'.
   */
  public String getNodeId() {
    return nodeId;
  }

  /**
   * The column title for parent nodes, for example 'Parent'.
   */
  public String getParentId() {
    return parentId;
  }

  /**
   * The column title for node size, for example 'Size'.
   */
  public String getSizeId() {
    return sizeId;
  }

  /**
   * The column title for node color, for example 'Complexity'.
   */
  public String getColorId() {
    return colorId;
  }
  
}
