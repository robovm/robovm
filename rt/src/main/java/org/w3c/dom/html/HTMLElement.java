/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * All HTML element interfaces derive from this class. Elements that only 
 * exposethe HTML core attributes are represented by the base
 * <code>HTMLElement</code> interface. These elements are as follows:HEAD
 * special: SUB, SUP, SPAN, BDOfont: TT, I, B, U, S, STRIKE, BIG, SMALL
 * phrase: EM, STRONG, DFN, CODE, SAMP, KBD, VAR, CITE, ACRONYM, ABBRlist: 
 * DD, DTNOFRAMES, NOSCRIPTADDRESS, CENTER
 * Note. The <code>style</code> attribute for thisinterface is reserved for 
 * future usage. 
 */
public interface HTMLElement extends Element {
  /**
   * The element's identifier. See the id attribute definition in HTML 4.0.
   */
  public String             getId();
  public void               setId(String id);
  /**
   * The element's advisory title. See the title attribute definition in HTML 
   * 4.0.
   */
  public String             getTitle();
  public void               setTitle(String title);
  /**
   * Language code defined in RFC 1766. See the lang attribute definition in 
   * HTML 4.0.
   */
  public String             getLang();
  public void               setLang(String lang);
  /**
   * Specifies the base direction of directionally neutraltext and the 
   * directionality of tables. See the dir attribute definition in HTML 4.0.
   */
  public String             getDir();
  public void               setDir(String dir);
  /**
   * The class attribute of the element. This attribute has been renameddue to 
   * conflicts with the "class" keyword exposed by many languages. See the 
   * class attribute definition in HTML 4.0.
   */
  public String             getClassName();
  public void               setClassName(String className);
}

