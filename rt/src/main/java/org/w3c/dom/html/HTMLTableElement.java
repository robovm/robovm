/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * The create* and delete* methods on the table allow authors to constructand 
 * modify tables. HTML 4.0 specifies that only one of each of the 
 * <code>CAPTION</code>, <code>THEAD</code>, and <code>TFOOT</code>elements 
 * may exist in a table. Therefore, if one exists, and thecreateTHead() or 
 * createTFoot() method is called, the method returnsthe existing THead or 
 * TFoot element. See the TABLE element definition in HTML 4.0.
 */
public interface HTMLTableElement extends HTMLElement {
  /**
   * Returns the table's <code>CAPTION</code>, or void if none exists. 
   */
  public HTMLTableCaptionElement getCaption();
  public void               setCaption(HTMLTableCaptionElement caption);
  /**
   * Returns the table's <code>THEAD</code>, or <code>null</code> if none 
   * exists. 
   */
  public HTMLTableSectionElement getTHead();
  public void               setTHead(HTMLTableSectionElement tHead);
  /**
   * Returns the table's <code>TFOOT</code>, or <code>null</code> if none 
   * exists. 
   */
  public HTMLTableSectionElement getTFoot();
  public void               setTFoot(HTMLTableSectionElement tFoot);
  /**
   * Returns a collection of all the rows in the table, including all in 
   * <code>THEAD</code>, <code>TFOOT</code>, all <code>TBODY</code> elements. 
   */
  public HTMLCollection     getRows();
  /**
   * Returns a collection of the defined table bodies. 
   */
  public HTMLCollection     getTBodies();
  /**
   * Specifies the table's position with respect to the rest of the document. 
   * See the align attribute definition in HTML 4.0. This attribute is 
   * deprecated in HTML 4.0.
   */
  public String             getAlign();
  public void               setAlign(String align);
  /**
   * Cell background color. See the bgcolor attribute definition in HTML 4.0. 
   * This attribute is deprecated in HTML 4.0.
   */
  public String             getBgColor();
  public void               setBgColor(String bgColor);
  /**
   * The width of the border around the table. See the border attribute 
   * definition in HTML 4.0.
   */
  public String             getBorder();
  public void               setBorder(String border);
  /**
   * Specifies the horizontal and vertical space between cell content andcell 
   * borders. See the cellpadding attribute definition in HTML 4.0.
   */
  public String             getCellPadding();
  public void               setCellPadding(String cellPadding);
  /**
   * Specifies the horizontal and vertical separation between cells. See the 
   * cellspacing attribute definition in HTML 4.0.
   */
  public String             getCellSpacing();
  public void               setCellSpacing(String cellSpacing);
  /**
   * Specifies which external table borders to render. See the frame attribute 
   * definition in HTML 4.0.
   */
  public String             getFrame();
  public void               setFrame(String frame);
  /**
   * Specifies which internal table borders to render. See the rules attribute 
   * definition in HTML 4.0.
   */
  public String             getRules();
  public void               setRules(String rules);
  /**
   * Supplementary description about the purpose or structureof a table. See 
   * the summary attribute definition in HTML 4.0.
   */
  public String             getSummary();
  public void               setSummary(String summary);
  /**
   * Specifies the desired table width. See the width attribute definition in 
   * HTML 4.0.
   */
  public String             getWidth();
  public void               setWidth(String width);
  /**
   * Create a table header row or return an existing one.
   * @return A new table header element (<code>THEAD</code>).
   */
  public HTMLElement        createTHead();
  /**
   * Delete the header from the table, if one exists.
   */
  public void               deleteTHead();
  /**
   * Create a table footer row or return an existing one.
   * @return A footer element (<code>TFOOT</code>).
   */
  public HTMLElement        createTFoot();
  /**
   * Delete the footer from the table, if one exists.
   */
  public void               deleteTFoot();
  /**
   * Create a new table caption object or return an existing one.
   * @return A <code>CAPTION</code> element.
   */
  public HTMLElement        createCaption();
  /**
   * Delete the table caption, if one exists.
   */
  public void               deleteCaption();
  /**
   * Insert a new empty row in the table.Note. A table row cannot be empty
   * according to HTML 4.0 Recommendation. 
   * @param index The row number where to insert a new row.
   * @return The newly created row.
   */
  public HTMLElement        insertRow(int index);
  /**
   * Delete a table row.
   * @param index The index of the row to be deleted.
   */
  public void               deleteRow(int index);
}

