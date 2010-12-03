/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * Style information. A more detailed style sheet object model is planned to be
 * defined in a separate document. See the STYLE element definition in HTML 
 * 4.0.
 */
public interface HTMLStyleElement extends HTMLElement {
  /**
   * Enables/disables the style sheet. 
   */
  public boolean            getDisabled();
  public void               setDisabled(boolean disabled);
  /**
   * Designed for use with one or more target media. See the media attribute 
   * definition in HTML 4.0.
   */
  public String             getMedia();
  public void               setMedia(String media);
  /**
   * The style sheet language (Internet media type). See the type attribute 
   * definition in HTML 4.0.
   */
  public String             getType();
  public void               setType(String type);
}

