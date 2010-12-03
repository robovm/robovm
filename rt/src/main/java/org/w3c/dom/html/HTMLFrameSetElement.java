/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * Create a grid of frames. See the FRAMESET element definition in HTML 4.0.
 */
public interface HTMLFrameSetElement extends HTMLElement {
  /**
   * The number of columns of frames in the frameset. See the cols attribute 
   * definition in HTML 4.0.
   */
  public String             getCols();
  public void               setCols(String cols);
  /**
   * The number of rows of frames in the frameset. See the rows attribute 
   * definition in HTML 4.0.
   */
  public String             getRows();
  public void               setRows(String rows);
}

