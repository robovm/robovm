/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * A row in a table. See the TR element definition in HTML 4.0.
 */
public interface HTMLTableRowElement extends HTMLElement {
  /**
   * The index of this row, relative to the entire table. 
   */
  public int                getRowIndex();
  public void               setRowIndex(int rowIndex);
  /**
   * The index of this row, relative to the current section(<code>THEAD</code>
   * , <code>TFOOT</code>, or <code>TBODY</code>). 
   */
  public int                getSectionRowIndex();
  public void               setSectionRowIndex(int sectionRowIndex);
  /**
   * The collection of cells in this row. 
   */
  public HTMLCollection     getCells();
  public void               setCells(HTMLCollection cells);
  /**
   * Horizontal alignment of data within cells of this row. See the align 
   * attribute definition in HTML 4.0.
   */
  public String             getAlign();
  public void               setAlign(String align);
  /**
   * Background color for rows. See the bgcolor attribute definition in HTML 
   * 4.0. This attribute is deprecated in HTML 4.0.
   */
  public String             getBgColor();
  public void               setBgColor(String bgColor);
  /**
   * Alignment character for cells in a column. See the char attribute 
   * definition in HTML 4.0.
   */
  public String             getCh();
  public void               setCh(String ch);
  /**
   * Offset of alignment character. See the charoff attribute definition in 
   * HTML 4.0.
   */
  public String             getChOff();
  public void               setChOff(String chOff);
  /**
   * Vertical alignment of data within cells of this row. See the valign 
   * attribute definition in HTML 4.0.
   */
  public String             getVAlign();
  public void               setVAlign(String vAlign);
  /**
   * Insert an empty <code>TD</code> cell into this row.
   * @param index The place to insert the cell.
   * @return The newly created cell.
   */
  public HTMLElement        insertCell(int index);
  /**
   * Delete a cell from the current row.
   * @param index The index of the cell to delete.
   */
  public void               deleteCell(int index);
}

