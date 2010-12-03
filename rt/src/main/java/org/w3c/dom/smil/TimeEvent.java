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
 */
package org.w3c.dom.smil;

import org.w3c.dom.events.Event;
import org.w3c.dom.views.AbstractView;

/**
 * The <code>TimeEvent</code> interface provides specific contextual
 * information associated with Time events.
 * 
 * @see <a href="http://www.w3.org/TR/2000/WD-smil-animation-20000731/">SMIL Animation</a>.
 */
public interface TimeEvent extends Event {

    /**
     * The <code>view</code> attribute identifies the <code>AbstractView</code>
     * from which the event was generated.
     */
    public AbstractView getView();
    
    /**
     * Specifies some detail information about the <code>Event</code>,
     * depending on the type of event.
     */
    public int getDetail();
    
    /**
     * The <code>initTimeEvent</code> method is used to initialize the value of
     * a <code>TimeEvent</code> created through the <code>DocumentEvent</code>
     * interface. This method may only be called before the
     * <code>TimeEvent</code> has been dispatched via the
     * <code>dispatchEvent</code> method, though it may be called multiple
     * times during that phase if necessary. If called multiple times, the
     * final invocation takes precedence.
     *
     * <p>The different types of events that can occur are:</p>
     *
     * <dl>
     * <dt><b>begin</b></dt>
     * <dd>This event is raised when the element local timeline begins to play.
     * It will be raised each time the element begins the active duration (i.e.
     * when it restarts, but not when it repeats). It may be raised both in the
     * course of normal (i.e. scheduled or interactive) timeline play, as well
     * as in the case that the element was begun with the <code>
     * beginElement()</code> or <code> beginElementAt()</code> methods. Note
     * that if an element is restarted while it is currently playing, the
     * element will raise an end event and another begin event, as the element
     * restarts.
     * <ul>
     * <li>Bubbles: No</li>
     * <li>Cancelable: No</li>
     * <li>Context Info: None</li>
     * </ul>
     * </dd>
     * <dt><b>end</b></dt>
     * <dd>This event is raised at the active end of the element. Note that
     * this event is not raised at the simple end of each repeat. This event
     * may be raised both in the course of normal (i.e. scheduled or
     * interactive) timeline play, as well as in the case that the element was
     * ended with the <code> endElement()</code> or <code>
     * endElementAt()</code> methods.  Note that if an element is restarted
     * while it is currently playing, the element will raise an end event and
     * another begin event, as the element restarts.
     * <ul>
     * <li>Bubbles: No</li>
     * <li>Cancelable: No</li>
     * <li>Context Info: None</li>
     * </ul>
     * </dd>
     * <dt><b>repeat</b></dt>
     * <dd>This event is raised when the element local timeline repeats. It
     * will be raised each time the element repeats, after the first
     * iteration.<br> The event provides a numerical indication of which repeat
     * iteration is beginning. The value is a 0-based integer, but the repeat
     * event is not raised for the first iteration and so the observed values
     * of the detail attribute will be &gt;= 1.
     * <ul>
     * <li>Bubbles: No</li>
     * <li>Cancelable: No</li>
     * <li>Context Info: detail (current iteration)</li>
     * </ul>
     * </dd>
     * </dl>
     *
     * @param typeArg Specifies the event type.
     * @param viewArg Specifies the <code>Event</code>'s
     *                <code>AbstractView</code>.
     * @param detailArg Specifies the <code>Event</code>'s detail.  */
    public void initTimeEvent(String typeArg, 
			      AbstractView viewArg, 
			      int detailArg);    
}
