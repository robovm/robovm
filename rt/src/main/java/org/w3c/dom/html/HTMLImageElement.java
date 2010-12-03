/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * Embedded image. See the IMG element definition in HTML 4.0.
 */
public interface HTMLImageElement extends HTMLElement {
  /**
   * URI designating the source of this image, for low-resolution output. 
   */
  public String             getLowSrc();
  public void               setLowSrc(String lowSrc);
  /**
   * The name of the element (for backwards compatibility). 
   */
  public String             getName();
  public void               setName(String name);
  /**
   * Aligns this object (vertically or horizontally) with respect to its 
   * surrounding text. See the align attribute definition in HTML 4.0. This 
   * attribute is deprecated in HTML 4.0.
   */
  public String             getAlign();
  public void               setAlign(String align);
  /**
   * Alternate text for user agents not rendering the normal contentof this 
   * element. See the alt attribute definition in HTML 4.0.
   */
  public String             getAlt();
  public void               setAlt(String alt);
  /**
   * Width of border around image. See the border attribute definition in HTML 
   * 4.0. This attribute is deprecated in HTML 4.0.
   */
  public String             getBorder();
  public void               setBorder(String border);
  /**
   * Override height. See the height attribute definition in HTML 4.0.
   */
  public String             getHeight();
  public void               setHeight(String height);
  /**
   * Horizontal space to the left and right of this image. See the hspace 
   * attribute definition in HTML 4.0. This attribute is deprecated in HTML 
   * 4.0.
   */
  public String             getHspace();
  public void               setHspace(String hspace);
  /**
   * Use server-side image map. See the ismap attribute definition in HTML 4.0.
   */
  public boolean            getIsMap();
  public void               setIsMap(boolean isMap);
  /**
   * URI designating a long description of this image or frame. See the 
   * longdesc attribute definition in HTML 4.0.
   */
  public String             getLongDesc();
  public void               setLongDesc(String longDesc);
  /**
   * URI designating the source of this image. See the src attribute definition
   *  in HTML 4.0.
   */
  public String             getSrc();
  public void               setSrc(String src);
  /**
   * Use client-side image map. See the usemap attribute definition in HTML 
   * 4.0.
   */
  public String             getUseMap();
  public void               setUseMap(String useMap);
  /**
   * Vertical space above and below this image. See the vspace attribute 
   * definition in HTML 4.0. This attribute is deprecated in HTML 4.0.
   */
  public String             getVspace();
  public void               setVspace(String vspace);
  /**
   * Override width. See the width attribute definition in HTML 4.0.
   */
  public String             getWidth();
  public void               setWidth(String width);
}

