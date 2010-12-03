/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * Create a frame. See the FRAME element definition in HTML 4.0.
 */
public interface HTMLFrameElement extends HTMLElement {
  /**
   * Request frame borders. See the frameborder attribute definition in HTML 
   * 4.0.
   */
  public String             getFrameBorder();
  public void               setFrameBorder(String frameBorder);
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
   * When true, forbid user from resizing frame. See the noresize attribute 
   * definition in HTML 4.0.
   */
  public boolean            getNoResize();
  public void               setNoResize(boolean noResize);
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
}

