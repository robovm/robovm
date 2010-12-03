/*
 * Copyright (c) 2009 World Wide Web Consortium,
 *
 * (Massachusetts Institute of Technology, European Research Consortium for
 * Informatics and Mathematics, Keio University). All Rights Reserved. This
 * work is distributed under the W3C(r) Software License [1] in the hope that
 * it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * [1] http://www.w3.org/Consortium/Legal/2002/copyright-software-20021231
 */

package org.w3c.dom;

/**
 * The <code>ElementTraversal</code> interface is a set of read-only attributes
 * which allow an author to easily navigate between elements in a document.
 * <p>In conforming implementations of Element Traversal, all objects that
 * implement {@link Element} must also implement the
 * <code>ElementTraversal</code> interface. Four of the methods,
 * {@link #getFirstElementChild}, {@link #getLastElementChild},
 * {@link #getPreviousElementSibling}, and {@link #getNextElementSibling},
 * each return a live reference to another element with the defined
 * relationship to the current element, if the related element exists. The
 * fifth method, {@link #getChildElementCount}, exposes the number of child
 * elements of an element, for preprocessing before navigation.
 * <p>See also the
 * <a href='http://www.w3.org/TR/ElementTraversal/'><cite>Element Traversal Specification</cite></a>.
 */
public interface ElementTraversal {

    /**
     * Returns the first child element node of this element. <code>null</code>
     * if this element has no child elements.
     */
    Element getFirstElementChild();

    /**
     * Returns the last child element node of this element. <code>null</code>
     * if this element has no child elements.
     */
    Element getLastElementChild();

    /**
     * Returns the previous sibling element node of this element.
     * <code>null</code> if this element has no element sibling nodes that
     * come before this one in the document tree.
     */
    Element getPreviousElementSibling();

    /**
     * Returns the next sibling element node of this element.
     * <code>null</code> if this element has no element sibling nodes that
     * come after this one in the document tree.
     */
    Element getNextElementSibling();

    /**
     * Returns the current number of element nodes that are children of this
     * element. <code>0</code> if this element has no child nodes that are of
     * <code>nodeType</code> <code>1</code>.
     */
    int getChildElementCount();
}
