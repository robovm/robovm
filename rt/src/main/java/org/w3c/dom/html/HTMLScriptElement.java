/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * Script statements. See the SCRIPT element definition in HTML 4.0.
 */
public interface HTMLScriptElement extends HTMLElement {
  /**
   * The script content of the element. 
   */
  public String             getText();
  public void               setText(String text);
  /**
   * Reserved for future use. 
   */
  public String             getHtmlFor();
  public void               setHtmlFor(String htmlFor);
  /**
   * Reserved for future use. 
   */
  public String             getEvent();
  public void               setEvent(String event);
  /**
   * The character encoding of the linked resource. See the charset attribute 
   * definition in HTML 4.0.
   */
  public String             getCharset();
  public void               setCharset(String charset);
  /**
   * Indicates that the user agent can defer processing of the script.  See 
   * the defer attribute definition in HTML 4.0.
   */
  public boolean            getDefer();
  public void               setDefer(boolean defer);
  /**
   * URI designating an external script. See the src attribute definition in 
   * HTML 4.0.
   */
  public String             getSrc();
  public void               setSrc(String src);
  /**
   * The content type of the script language. See the type attribute definition
   *  in HTML 4.0.
   */
  public String             getType();
  public void               setType(String type);
}

