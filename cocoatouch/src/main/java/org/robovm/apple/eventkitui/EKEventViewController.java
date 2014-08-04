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
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("EventKitUI") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/EKEventViewController/*</name>*/ 
    extends /*<extends>*/UIViewController/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class EKEventViewControllerPtr extends Ptr<EKEventViewController, EKEventViewControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(EKEventViewController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public EKEventViewController() {}
    protected EKEventViewController(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Property(selector = "delegate")
    public native EKEventViewDelegate getDelegate();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(EKEventViewDelegate v);
    @Property(selector = "event")
    public native EKEvent getEvent();
    @Property(selector = "setEvent:")
    public native void setEvent(EKEvent v);
    @Property(selector = "allowsEditing")
    public native boolean isAllowsEditing();
    @Property(selector = "setAllowsEditing:")
    public native void setAllowsEditing(boolean v);
    @Property(selector = "allowsCalendarPreview")
    public native boolean isAllowsCalendarPreview();
    @Property(selector = "setAllowsCalendarPreview:")
    public native void setAllowsCalendarPreview(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
