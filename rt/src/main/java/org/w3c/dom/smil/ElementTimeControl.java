/*
 * Copyright (c) 2001 World Wide Web Consortium,
 * (Massachusetts Institute of Technology, Institut National de
 * Recherche en Informatique et en Automatique, Keio University). All
 * Rights Reserved. This program is distributed under the W3C's Software
 * Intellectual Property License. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.
 * See W3C License http://www.w3.org/Consortium/Legal/ for more details.
 *
 */
package org.w3c.dom.smil;

import org.w3c.dom.DOMException;

/**
 * <p><a href='http://www.w3.org/TR/2000/WD-smil-animation-20000731/'>SMILAnimation</a>
 * supports several methods for controlling the behavior of animation:
 * <code>beginElement()</code> and <code>endElement()</code>, et al.  These
 * methods are used to begin and end an animation that has declared the timing
 * to respond to the DOM, using the following syntax:</p>
 * <pre>&lt;animate begin="indefinite" end="indefinite" .../&gt;</pre>
 *
 *  <p>Note that only one of <code>begin</code> or <code>end</code> need be
 *  specified - either or both can be used.  The <code>beginElement()</code>
 *  and <code>beginElementAt()</code> methods must do nothing if the animation
 *  is not explicitly set with the <code>begin="indefinite"</code> syntax
 *  above. The <code>endElement()</code> and <code>endElementAt()</code>
 *  methods must do nothing if the animation is not explicitly set with the
 *  <code>end</code><code>="indefinite"</code> syntax above.</p>
 *
 * <p>Calling <code>beginElement()</code> causes the animation to begin in much
 * the same way that an animation with event-based begin timing begins. The
 * effective begin time is the current presentation time at the time of the DOM
 * method call. Note that <code>beginElement()</code> is subject to the
 * <code>restart</code> attribute in the same manner that event-based begin
 * timing is. If an animation is specified to disallow restarting at a given
 * point, <code>beginElement()</code> methods calls must fail. Refer also to
 * the section <a href="http://www.w3.org/TR/2000/WD-smil-animation-20000731/#Restart">Restarting
 * animations</a>.</p>
 *
 * <p>Calling <code>beginElementAt()</code> has the same effect as
 * <code>beginElement()</code>, except that the effective begin time is offset
 * from the current presentation time by an amount specified as a parameter.
 * Passing a negative value for the offset causes the element to begin as for
 * <code>beginElement()</code>, but has the effect that the element begins at
 * the specified offset into its active duration. The
 * <code>beginElementAt()</code> method must also respect the
 * <code>restart</code> attribute. The restart semantics for a
 * <code>beginElementAt()</code> method call are evaluated at the time of the
 * method call, and not at the effective begin time specified by the offset
 * parameter.</p>
 *
 * <p>Calling <code>endElement()</code> causes an animation to end the active
 * duration, just as <code>end</code> does. Depending upon the value of the
 * <code>fill</code> attribute, the animation effect may no longer be applied,
 * or it may be frozen at the current effect. Refer also to the section <a
 * href="#Fill">Freezing animations</a>. If an animation is not currently
 * active (i.e. if it has not yet begun or if it is frozen), the
 * <code>endElement()</code> method will fail.</p>
 *
 * <p>Calling <code>endElementAt()</code> causes an animation to end the active
 * duration, just as <code>endElement()</code> does, but allows the caller to
 * specify a positive offset, to cause the element to end at a point in the
 * future. Other than delaying when the end actually happens, the semantics are
 * identical to those for <code>endElement()</code>. If
 * <code>endElementAt()</code> is called more than once while an element is
 * active, the end time specified by the last method call will determine the
 * end behavior. </p>
 *
 * <p>The expectation of the following interface is that an instance of the
 * ElementTimeControl interface can be obtained by using binding-specific
 * casting methods on an instance of an animate element. A DOM application can
 * use the <code>hasFeature</code> method of the <a
 * href="http://www.w3.org/TR/2000/REC-DOM-Level-2-Core-20001113/#ID-5CED94D7">DOMImplementation</a>
 * interface to determine whether the <a
 * href="http://www.w3.org/TR/2000/WD-smil-animation-20000731/#ElementTimeControl"><code>ElementTimeControl</code></a> interface is
 * supported or not. The feature string for this interface is
 * <code>"TimeControl"</code>.</p>
 *
 * @see <a href="http://www.w3.org/TR/2000/WD-smil-animation-20000731/">SMIL Animation</a>.
 */
public interface ElementTimeControl {
    /**
     * Causes this element to begin the local timeline (subject to restart constraints).
     * @return <code>true</code> if the method call was successful and the
     *         element was begun. <code>false</code> if the method call
     *         failed. Possible reasons for failure include:
     * <ul>
     * <li>The element does not support the <code>beginElement</code>
     * method. The <code>begin</code> attribute is not set to
     * <code>"indefinite"</code>.</li>
     * <li>The element is already active and cannot be restarted when it is
     * active. The <code>restart</code> attribute is set to
     * <code>"whenNotActive"</code>.</li>
     * <li>The element is active or has been active and cannot be
     * restarted. The <code>restart</code> attribute is set to
     * <code>"never"</code>.</li>
     * </ul>
     * @raise DOMException <code>SYNTAX_ERR</code>: The element was not defined
     * with the appropriate syntax to allow <code>beginElement</code> calls.
     */
    public boolean beginElement()
	throws DOMException;
    
    /**
     * Causes this element to begin the local timeline (subject to restart
     * constraints), at the passed offset from the current time when the method
     * is called. If the offset is &gt;= 0, the semantics are equivalent to an
     * event-base begin with the specified offset. If the offset is &lt; 0, the
     * semantics are equivalent to beginElement(), but the element active
     * duration is evaluated as though the element had begun at the passed
     * (negative) offset from the current time when the method is called.
     *
     * @param offset The offset in seconds at which to begin the element.
     * @return <code>true</code> if the method call was successful and the element was begun.
     * <code>false</code> if the method call failed.
     * Possible reasons for failure include:
     * <ul>
     * <li>The element does not support the
     * <code>beginElementAt</code> method. The
     * <code>begin</code> attribute is not set to
     * <code>"indefinite"</code>.</li>
     * <li>The element is already active and cannot be
     * restarted when it is active. The
     * <code>restart</code> attribute is set to
     * <code>"whenNotActive"</code>.</li>
     * <li>The element is active or has been active and
     * cannot be restarted. The <code>restart</code>
     * attribute is set to <code>"never"</code>.</li>
     * </ul>     
     * @raise DOMException SYNTAX_ERR: The element was not defined with the appropriate
     * syntax to allow <code>beginElementAt</code> calls.
     */
    public boolean beginElementAt(float offset)
	throws DOMException;
    
    /**
     * Causes this element to end the local timeline.
     *
     * @return <code>true</code> if the method call was
     * successful and the element was ended.
     * <code>false</code> if method call failed. Possible
     * reasons for failure include:
     * <ul>
     * <li>The element does not support the
     * <code>endElement</code> method. The
     * <code>end</code> attribute is not set to
     * <code>"indefinite"</code>.</li>
     * <li>The element is not active.</li>
     * </ul>
     * @raise DOMException SYNTAX_ERR: The element was not defined with the
     *                     appropriate syntax to allow <code>endElement</code>
     *                     calls.
     */    
    public boolean endElement()
	throws DOMException;
    
    /**
     * Causes this element to end the local timeline at the specified offset
     * from the current time when the method is called
     *
     * @param offset The offset in seconds at which to end the element.
     *               Must be <code>&gt;= 0</code>.
     * @return <code>true</code> if the method call was
     * successful and the element was ended.
     * <code>false</code> if method call failed. Possible
     * reasons for failure include:
     * <ul>
     * <li>The element does not support the
     * <code>endElementAt</code> method. The
     * <code>end</code> attribute is not set to
     * <code>"indefinite"</code>.</li>
     * <li>The element is not active.</li>
     * </ul>
     * @raise DOMException SYNTAX_ERR: The element was not defined with the
     * appropriate syntax to allow
     * <code>endElementAt</code> calls.
     */
    public boolean endElementAt(float offset)
	throws DOMException;
    
}
