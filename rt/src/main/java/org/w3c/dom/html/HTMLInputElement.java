/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * Form control. Note. Depending upon the environmentthe page is being viewed, 
 * the value property may be read-only for thefile upload input type. For the 
 * "password" input type, the actual valuereturned may be masked to prevent 
 * unauthorized use. See the INPUT element definition in HTML 4.0.
 */
public interface HTMLInputElement extends HTMLElement {
  /**
   * Stores the initial control value (i.e., the initial value of
   * <code>value</code>). 
   */
  public String             getDefaultValue();
  public void               setDefaultValue(String defaultValue);
  /**
   * When <code>type</code> has the value "Radio" or "Checkbox", stores the 
   * initial value of the <code>checked</code> attribute. 
   */
  public boolean            getDefaultChecked();
  public void               setDefaultChecked(boolean defaultChecked);
  /**
   * Returns the <code>FORM</code> element containing this control.Returns 
   * null if this control is not within the context of a form. 
   */
  public HTMLFormElement    getForm();
  /**
   * A comma-separated list of content types that a server processing thisform 
   * will handle correctly. See the accept attribute definition in HTML 4.0.
   */
  public String             getAccept();
  public void               setAccept(String accept);
  /**
   * A single character access key to give access to the form control. See the 
   * accesskey attribute definition in HTML 4.0.
   */
  public String             getAccessKey();
  public void               setAccessKey(String accessKey);
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
   * Describes whether a radio or check box is checked, when<code>type</code> 
   * has the value "Radio" or "Checkbox".  The value isTRUE if explicitly 
   * set. Represents the current state of the checkboxor radio button. See 
   * the checked attribute definition in HTML 4.0.
   */
  public boolean            getChecked();
  public void               setChecked(boolean checked);
  /**
   * The control is unavailable in this context. See the disabled attribute 
   * definition in HTML 4.0.
   */
  public boolean            getDisabled();
  public void               setDisabled(boolean disabled);
  /**
   * Maximum number of characters for text fields, when <code>type</code>has 
   * the value "Text" or "Password". See the maxlength attribute definition 
   * in HTML 4.0.
   */
  public int                getMaxLength();
  public void               setMaxLength(int maxLength);
  /**
   * Form control or object name when submitted with a form. See the name 
   * attribute definition in HTML 4.0.
   */
  public String             getName();
  public void               setName(String name);
  /**
   * This control is read-only. When <code>type</code> has the value "text"or 
   * "password" only. See the readonly attribute definition in HTML 4.0.
   */
  public boolean            getReadOnly();
  public void               setReadOnly(boolean readOnly);
  /**
   * Size information. The precise meaning is specific to each type offield.  
   * See the size attribute definition in HTML 4.0.
   */
  public String             getSize();
  public void               setSize(String size);
  /**
   * When the <code>type</code> attribute has the value "Image", thisattribute 
   * specifies the location of the image to be used to decoratethe graphical 
   * submit button. See the src attribute definition in HTML 4.0.
   */
  public String             getSrc();
  public void               setSrc(String src);
  /**
   * Index that represents the element's position in the tabbing order. See 
   * the tabindex attribute definition in HTML 4.0.
   */
  public int                getTabIndex();
  public void               setTabIndex(int tabIndex);
  /**
   * The type of control created. See the type attribute definition in HTML 
   * 4.0.
   */
  public String             getType();
  /**
   * Use client-side image map. See the usemap attribute definition in HTML 
   * 4.0.
   */
  public String             getUseMap();
  public void               setUseMap(String useMap);
  /**
   * The current form control value. Used for radio buttons and check boxes. 
   * See the value attribute definition in HTML 4.0.
   */
  public String             getValue();
  public void               setValue(String value);
  /**
   * Removes keyboard focus from this element.
   */
  public void               blur();
  /**
   * Gives keyboard focus to this element.
   */
  public void               focus();
  /**
   * Select the contents of the text area. For <code>INPUT</code> elements
   * whose <code>type</code> attribute has one of the following values:
   * "Text", "File", or "Password".
   */
  public void               select();
  /**
   * Simulate a mouse-click. For <code>INPUT</code> elements whose
   * <code>type</code> attribute has one of the followingvalues: "Button", 
   * "Checkbox", "Radio", "Reset", or "Submit".
   */
  public void               click();
}

