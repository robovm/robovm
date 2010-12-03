/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * Force a line break. See the BR element definition in HTML 4.0.
 */
public interface HTMLBRElement extends HTMLElement {
  /**
   * Control flow of text around floats. See the clear attribute definition in 
   * HTML 4.0. This attribute is deprecated in HTML 4.0.
   */
  public String             getClear();
  public void               setClear(String clear);
}

