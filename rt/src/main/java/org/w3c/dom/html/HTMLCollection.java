/*
 * Copyright (c) 1998 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 */

package org.w3c.dom.html;

import org.w3c.dom.*;

/**
 * An <code>HTMLCollection</code> is a list of nodes. An individual nodemay be 
 * accessed by either ordinal index or the node's<code>name</code> or 
 * <code>id</code> attributes. Note:Collections in the HTML DOM are assumed 
 * to be live meaningthat they are automatically updated when the underlying 
 * document ischanged. 
 */
public interface HTMLCollection {
  /**
   * This attribute specifies the length or size of the list. 
   */
  public int                getLength();
  /**
   * This method retrieves a node specified by ordinal index. Nodes are 
   * numbered in tree order (depth-first traversal order).
   * @param index The index of the node to be fetched. The index origin is 0.
   * @return The <code>Node</code> at the corresponding position upon success. 
   *   A value of <code>null</code> is returned if the index is out of range. 
   */
  public Node               item(int index);
  /**
   * This method retrieves a <code>Node</code> using a name. It first searches 
   * for a <code>Node</code> with a matching <code>id</code> attribute. If it 
   * doesn't find one, it then searches for a <code>Node</code> with a 
   * matching <code>name</code> attribute, but only on those elements that 
   * are allowed a name attribute. 
   * @param name The name of the <code>Node</code> to be fetched.
   * @return The <code>Node</code> with a <code>name</code> or <code>id</code> 
   *   attribute whose value corresponds to the specified string. Upon 
   *   failure (e.g., no node with this name exists), returns 
   *   <code>null</code>.
   */
  public Node               namedItem(String name);
}

