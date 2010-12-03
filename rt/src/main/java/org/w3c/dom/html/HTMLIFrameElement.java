/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * Inline subwindows. See the IFRAME element definition in HTML 4.0.
 */
public interface HTMLIFrameElement extends HTMLElement {
  /**
   * Aligns this object (vertically or horizontally) with respect to its 
   * surrounding text. See the align attribute definition in HTML 4.0. This 
   * attribute is deprecated in HTML 4.0.
   */
  public String             getAlign();
  public void               setAlign(String align);
  /**
   * Request frame borders. See the frameborder attribute definition in HTML 
   * 4.0.
   */
  public String             getFrameBorder();
  public void               setFrameBorder(String frameBorder);
  /**
   * Frame height. See the height attribute definition in HTML 4.0.
   */
  public String             getHeight();
  public void               setHeight(String height);
  /**
   * URI designating a long description of this image or frame. See the 
   * longdesc attribute definition in HTML 4.0.
   */
  public String             getLongDesc();
  public void               setLongDesc(String longDesc);
  /**
   * Frame margin height, in pixels. See the marginheight attribute definition 
   * in HTML 4.0.
   */
  public String             getMarginHeight();
  public void               setMarginHeight(String marginHeight);
  /**
   * Frame margin width, in pixels. See the marginwidth attribute definition 
   * in HTML 4.0.
   */
  public String             getMarginWidth();
  public void               setMarginWidth(String marginWidth);
  /**
   * The frame name (object of the <code>target</code> attribute). See the 
   * name attribute definition in HTML 4.0.
   */
  public String             getName();
  public void               setName(String name);
  /**
   * Specify whether or not the frame should have scrollbars. See the 
   * scrolling attribute definition in HTML 4.0.
   */
  public String             getScrolling();
  public void               setScrolling(String scrolling);
  /**
   * A URI designating the initial frame contents. See the src attribute 
   * definition in HTML 4.0.
   */
  public String             getSrc();
  public void               setSrc(String src);
  /**
   * Frame width. See the width attribute definition in HTML 4.0.
   */
  public String             getWidth();
  public void               setWidth(String width);
}

