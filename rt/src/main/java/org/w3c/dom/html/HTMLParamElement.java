/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * Parameters fed to the <code>OBJECT</code> element. See the PARAM element 
 * definition in HTML 4.0.
 */
public interface HTMLParamElement extends HTMLElement {
  /**
   * The name of a run-time parameter. See the name attribute definition in 
   * HTML 4.0.
   */
  public String             getName();
  public void               setName(String name);
  /**
   * Content type for the <code>value</code> attribute when
   * <code>valuetype</code> has the value "ref". See the type attribute 
   * definition in HTML 4.0.
   */
  public String             getType();
  public void               setType(String type);
  /**
   * The value of a run-time parameter. See the value attribute definition in 
   * HTML 4.0.
   */
  public String             getValue();
  public void               setValue(String value);
  /**
   * Information about the meaning of the <code>value</code> attributevalue. 
   * See the valuetype attribute definition in HTML 4.0.
   */
  public String             getValueType();
  public void               setValueType(String valueType);
}

