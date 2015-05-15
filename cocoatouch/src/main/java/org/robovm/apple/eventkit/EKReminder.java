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
package org.robovm.apple.eventkit;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.addressbook.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("EventKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/EKReminder/*</name>*/ 
    extends /*<extends>*/EKCalendarItem/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class EKReminderPtr extends Ptr<EKReminder, EKReminderPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(EKReminder.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public EKReminder() {}
    protected EKReminder(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "startDateComponents")
    public native NSDateComponents getStartDateComponents();
    @Property(selector = "setStartDateComponents:")
    public native void setStartDateComponents(NSDateComponents v);
    @Property(selector = "dueDateComponents")
    public native NSDateComponents getDueDateComponents();
    @Property(selector = "setDueDateComponents:")
    public native void setDueDateComponents(NSDateComponents v);
    @Property(selector = "isCompleted")
    public native boolean isCompleted();
    @Property(selector = "setCompleted:")
    public native void setCompleted(boolean v);
    @Property(selector = "completionDate")
    public native NSDate getCompletionDate();
    @Property(selector = "setCompletionDate:")
    public native void setCompletionDate(NSDate v);
    @Property(selector = "priority")
    public native @MachineSizedSInt long getPriority();
    @Property(selector = "setPriority:")
    public native void setPriority(@MachineSizedSInt long v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "reminderWithEventStore:")
    public static native EKReminder create(EKEventStore eventStore);
    /*</methods>*/
}
