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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corelocation.*;
import org.robovm.apple.addressbook.*;
import org.robovm.apple.mapkit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("EventKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/EKCalendar/*</name>*/ 
    extends /*<extends>*/EKObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class EKCalendarPtr extends Ptr<EKCalendar, EKCalendarPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(EKCalendar.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public EKCalendar() {}
    protected EKCalendar(SkipInit skipInit) { super(skipInit); }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public EKCalendar(EKEntityType entityType, EKEventStore eventStore) { super(create(entityType, eventStore)); retain(getHandle()); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "source")
    public native EKSource getSource();
    @Property(selector = "setSource:")
    public native void setSource(EKSource v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "calendarIdentifier")
    public native String getCalendarIdentifier();
    @Property(selector = "title")
    public native String getTitle();
    @Property(selector = "setTitle:")
    public native void setTitle(String v);
    @Property(selector = "type")
    public native EKCalendarType getType();
    @Property(selector = "allowsContentModifications")
    public native boolean allowsContentModifications();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "isSubscribed")
    public native boolean isSubscribed();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "isImmutable")
    public native boolean isImmutable();
    @WeaklyLinked
    @Property(selector = "CGColor")
    public native CGColor getCGColor();
    @WeaklyLinked
    @Property(selector = "setCGColor:")
    public native void setCGColor(CGColor v);
    @Property(selector = "supportedEventAvailabilities")
    public native EKCalendarEventAvailabilityMask getSupportedEventAvailabilities();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "allowedEntityTypes")
    public native EKEntityMask getAllowedEntityTypes();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "calendarForEntityType:eventStore:")
    private static native @Pointer long create(EKEntityType entityType, EKEventStore eventStore);
    /*</methods>*/
}
