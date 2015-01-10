/*
 * Copyright (C) 2014 Trillian Mobile AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.robovm.apple.eventkitui;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.eventkit.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("EventKitUI") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/EKCalendarChooser/*</name>*/ 
    extends /*<extends>*/UIViewController/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class EKCalendarChooserPtr extends Ptr<EKCalendarChooser, EKCalendarChooserPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(EKCalendarChooser.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public EKCalendarChooser() {}
    protected EKCalendarChooser(SkipInit skipInit) { super(skipInit); }
    public EKCalendarChooser(EKCalendarChooserSelectionStyle selectionStyle, EKCalendarChooserDisplayStyle displayStyle, EKEventStore eventStore) { super((SkipInit) null); initObject(init(selectionStyle, displayStyle, eventStore)); }
    public EKCalendarChooser(EKCalendarChooserSelectionStyle style, EKCalendarChooserDisplayStyle displayStyle, EKEntityType entityType, EKEventStore eventStore) { super((SkipInit) null); initObject(init(style, displayStyle, entityType, eventStore)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "selectionStyle")
    public native EKCalendarChooserSelectionStyle getSelectionStyle();
    @Property(selector = "delegate")
    public native EKCalendarChooserDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(EKCalendarChooserDelegate v);
    @Property(selector = "showsDoneButton")
    public native boolean showsDoneButton();
    @Property(selector = "setShowsDoneButton:")
    public native void setShowsDoneButton(boolean v);
    @Property(selector = "showsCancelButton")
    public native boolean showsCancelButton();
    @Property(selector = "setShowsCancelButton:")
    public native void setShowsCancelButton(boolean v);
    @Property(selector = "selectedCalendars")
    public native NSSet<EKCalendar> getSelectedCalendars();
    @Property(selector = "setSelectedCalendars:")
    public native void setSelectedCalendars(NSSet<EKCalendar> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithSelectionStyle:displayStyle:eventStore:")
    protected native @Pointer long init(EKCalendarChooserSelectionStyle selectionStyle, EKCalendarChooserDisplayStyle displayStyle, EKEventStore eventStore);
    @Method(selector = "initWithSelectionStyle:displayStyle:entityType:eventStore:")
    protected native @Pointer long init(EKCalendarChooserSelectionStyle style, EKCalendarChooserDisplayStyle displayStyle, EKEntityType entityType, EKEventStore eventStore);
    /*</methods>*/
}
