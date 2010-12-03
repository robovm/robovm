/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * The HTML document body. This element is always present in the DOM API,even 
 * if the tags are not present in the source document. See the BODY element 
 * definition in HTML 4.0.
 */
public interface HTMLBodyElement extends HTMLElement {
  /**
   * Color of active links (after mouse-button down, but beforemouse-button 
   * up). See the alink attribute definition in HTML 4.0. This attribute is 
   * deprecated in HTML 4.0.
   */
  public String             getALink();
  public void               setALink(String aLink);
  /**
   * URI of the background texture tile image. See the background attribute 
   * definition in HTML 4.0. This attribute is deprecated in HTML 4.0.
   */
  public String             getBackground();
  public void               setBackground(String background);
  /**
   * Document background color. See the bgcolor attribute definition in HTML 
   * 4.0. This attribute is deprecated in HTML 4.0.
   */
  public String             getBgColor();
  public void               setBgColor(String bgColor);
  /**
   * Color of links that are not active and unvisited. See the link attribute 
   * definition in HTML 4.0. This attribute is deprecated in HTML 4.0.
   */
  public String             getLink();
  public void               setLink(String link);
  /**
   * Document text color. See the text attribute definition in HTML 4.0. This 
   * attribute is deprecated in HTML 4.0.
   */
  public String             getText();
  public void               setText(String text);
  /**
   * Color of links that have been visited by the user. See the vlink 
   * attribute definition in HTML 4.0. This attribute is deprecated in HTML 
   * 4.0.
   */
  public String             getVLink();
  public void               setVLink(String vLink);
}

