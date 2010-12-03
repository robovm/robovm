/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * Client-side image map. See the MAP element definition in HTML 4.0.
 */
public interface HTMLMapElement extends HTMLElement {
  /**
   * The list of areas defined for the image map. 
   */
  public HTMLCollection     getAreas();
  /**
   * Names the map (for use with <code>usemap</code>). See the name attribute 
   * definition in HTML 4.0.
   */
  public String             getName();
  public void               setName(String name);
}

