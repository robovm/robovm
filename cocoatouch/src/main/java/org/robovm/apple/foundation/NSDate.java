/*
 * Copyright (C) 2014 Trillian AB
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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSDate/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSDatePtr extends Ptr<NSDate, NSDatePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSDate.class); }/*</bind>*/
    /*<constants>*/
    public static final double TimeIntervalSince1970 = 978307200.0;
    /*</constants>*/
    /*<constructors>*/
    public NSDate() {}
    protected NSDate(SkipInit skipInit) { super(skipInit); }
    public NSDate(double secs) { super((SkipInit) null); initObject(initWithTimeIntervalSince1970$(secs)); }
    /*</constructors>*/
    
    public NSDate(Date date) {
        this(date.getTime() / 1000.0);
    }
    
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSSystemClockDidChangeNotification")
    public static native String NotificationSystemClockDidChange();
    
    @Method(selector = "timeIntervalSinceReferenceDate")
    public native double timeIntervalSinceReferenceDate();
    @Method(selector = "timeIntervalSinceDate:")
    public native double timeIntervalSinceDate$(NSDate anotherDate);
    @Method(selector = "timeIntervalSinceNow")
    public native double timeIntervalSinceNow();
    @Method(selector = "timeIntervalSince1970")
    public native double timeIntervalSince1970();
    @Method(selector = "addTimeInterval:")
    public native NSObject addTimeInterval$(double seconds);
    @Method(selector = "dateByAddingTimeInterval:")
    public native NSObject dateByAddingTimeInterval$(double ti);
    @Method(selector = "earlierDate:")
    public native NSDate earlierDate$(NSDate anotherDate);
    @Method(selector = "laterDate:")
    public native NSDate laterDate$(NSDate anotherDate);
    @Method(selector = "compare:")
    public native NSComparisonResult compare$(NSDate other);
    @Method(selector = "isEqualToDate:")
    public native boolean isEqualToDate$(NSDate otherDate);
    @Method(selector = "description")
    public native String description();
    @Method(selector = "descriptionWithLocale:")
    public native String descriptionWithLocale$(NSObject locale);
    @Method(selector = "initWithTimeIntervalSince1970:")
    protected native @Pointer long initWithTimeIntervalSince1970$(double secs);
    @Method(selector = "date")
    public static native NSDate date();
    @Method(selector = "dateWithTimeIntervalSinceNow:")
    public static native NSDate dateWithTimeIntervalSinceNow$(double secs);
    @Method(selector = "dateWithTimeIntervalSinceReferenceDate:")
    public static native NSDate dateWithTimeIntervalSinceReferenceDate$(double ti);
    @Method(selector = "dateWithTimeIntervalSince1970:")
    public static native NSDate dateWithTimeIntervalSince1970$(double secs);
    @Method(selector = "dateWithTimeInterval:sinceDate:")
    public static native NSDate dateWithTimeInterval$sinceDate$(double secsToBeAdded, NSDate date);
    @Method(selector = "distantFuture")
    public static native NSObject distantFuture();
    @Method(selector = "distantPast")
    public static native NSObject distantPast();
    /*</methods>*/
}
