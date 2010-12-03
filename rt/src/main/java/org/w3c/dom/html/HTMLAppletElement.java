/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * An embedded Java applet. See the APPLET element definition in HTML 4.0. 
 * This element is deprecated in HTML 4.0.
 */
public interface HTMLAppletElement extends HTMLElement {
  /**
   * Aligns this object (vertically or horizontally) with respect to its 
   * surrounding text. See the align attribute definition in HTML 4.0. This 
   * attribute is deprecated in HTML 4.0.
   */
  public String             getAlign();
  public void               setAlign(String align);
  /**
   * Alternate text for user agents not rendering the normal contentof this 
   * element. See the alt attribute definition in HTML 4.0. This attribute is 
   * deprecated in HTML 4.0.
   */
  public String             getAlt();
  public void               setAlt(String alt);
  /**
   * Comma-separated archive list. See the archive attribute definition in 
   * HTML 4.0. This attribute is deprecated in HTML 4.0.
   */
  public String             getArchive();
  public void               setArchive(String archive);
  /**
   * Applet class file.  See the code attribute definition in HTML 4.0. This 
   * attribute is deprecated in HTML 4.0.
   */
  public String             getCode();
  public void               setCode(String code);
  /**
   * Optional base URI for applet. See the codebase attribute definition in 
   * HTML 4.0. This attribute is deprecated in HTML 4.0.
   */
  public String             getCodeBase();
  public void               setCodeBase(String codeBase);
  /**
   * Override height. See the height attribute definition in HTML 4.0. This 
   * attribute is deprecated in HTML 4.0.
   */
  public String             getHeight();
  public void               setHeight(String height);
  /**
   * Horizontal space to the left and right of this image, applet, or object. 
   * See the hspace attribute definition in HTML 4.0. This attribute is 
   * deprecated in HTML 4.0.
   */
  public String             getHspace();
  public void               setHspace(String hspace);
  /**
   * The name of the applet. See the name attribute definition in HTML 4.0. 
   * This attribute is deprecated in HTML 4.0.
   */
  public String             getName();
  public void               setName(String name);
  /**
   * Serialized applet file. See the object attribute definition in HTML 4.0. 
   * This attribute is deprecated in HTML 4.0.
   */
  public String             getObject();
  public void               setObject(String object);
  /**
   * Vertical space above and below this image, applet, or object. See the 
   * vspace attribute definition in HTML 4.0. This attribute is deprecated in 
   * HTML 4.0.
   */
  public String             getVspace();
  public void               setVspace(String vspace);
  /**
   * Override width. See the width attribute definition in HTML 4.0. This 
   * attribute is deprecated in HTML 4.0.
   */
  public String             getWidth();
  public void               setWidth(String width);
}

