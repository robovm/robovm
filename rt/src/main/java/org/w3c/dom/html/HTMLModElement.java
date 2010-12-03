/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * Notice of modification to part of a document. See the  INS  and DEL  
 * element definitions in HTML 4.0. 
 */
public interface HTMLModElement extends HTMLElement {
  /**
   * A URI designating a document that describes the reason forthe change. See 
   * the cite attribute definition in HTML 4.0.
   */
  public String             getCite();
  public void               setCite(String cite);
  /**
   * The date and time of the change. See the datetime attribute definition in 
   * HTML 4.0.
   */
  public String             getDateTime();
  public void               setDateTime(String dateTime);
}

