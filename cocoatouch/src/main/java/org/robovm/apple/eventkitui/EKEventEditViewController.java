/*
 * Copyright (C) 2013-2015 RoboVM AB
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/EKEventEditViewController/*</name>*/ 
    extends /*<extends>*/UINavigationController/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class EKEventEditViewControllerPtr extends Ptr<EKEventEditViewController, EKEventEditViewControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(EKEventEditViewController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public EKEventEditViewController() {}
    protected EKEventEditViewController(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "editViewDelegate")
    public native EKEventEditViewDelegate getEditViewDelegate();
    @Property(selector = "setEditViewDelegate:", strongRef = true)
    public native void setEditViewDelegate(EKEventEditViewDelegate v);
    @Property(selector = "eventStore")
    public native EKEventStore getEventStore();
    @Property(selector = "setEventStore:")
    public native void setEventStore(EKEventStore v);
    @Property(selector = "event")
    public native EKEvent getEvent();
    @Property(selector = "setEvent:")
    public native void setEvent(EKEvent v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "cancelEditing")
    public native void cancelEditing();
    /*</methods>*/
}
