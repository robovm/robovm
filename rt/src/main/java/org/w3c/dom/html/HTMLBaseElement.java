/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * Document base URI. See the BASE element definition in HTML 4.0.
 */
public interface HTMLBaseElement extends HTMLElement {
  /**
   * The base URI See the href attribute definition in HTML 4.0.
   */
  public String             getHref();
  public void               setHref(String href);
  /**
   * The default target frame. See the target attribute definition in HTML 4.0.
   */
  public String             getTarget();
  public void               setTarget(String target);
}

