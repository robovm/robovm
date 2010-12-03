/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * This contains generic meta-information about the document. See the META 
 * element definition in HTML 4.0.
 */
public interface HTMLMetaElement extends HTMLElement {
  /**
   * Associated information. See the content attribute definition in HTML 4.0.
   */
  public String             getContent();
  public void               setContent(String content);
  /**
   * HTTP response header name. See the http-equiv attribute definition in 
   * HTML 4.0.
   */
  public String             getHttpEquiv();
  public void               setHttpEquiv(String httpEquiv);
  /**
   * Meta information name. See the name attribute definition in HTML 4.0.
   */
  public String             getName();
  public void               setName(String name);
  /**
   * Select form of content. See the scheme attribute definition in HTML 4.0.
   */
  public String             getScheme();
  public void               setScheme(String scheme);
}

