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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSDateIntervalFormatter/*</name>*/ 
    extends /*<extends>*/NSFormatter/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSDateIntervalFormatterPtr extends Ptr<NSDateIntervalFormatter, NSDateIntervalFormatterPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSDateIntervalFormatter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSDateIntervalFormatter() {}
    protected NSDateIntervalFormatter(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "locale")
    public native NSLocale getLocale();
    @Property(selector = "setLocale:")
    public native void setLocale(NSLocale v);
    @Property(selector = "calendar")
    public native NSCalendar getCalendar();
    @Property(selector = "setCalendar:")
    public native void setCalendar(NSCalendar v);
    @Property(selector = "timeZone")
    public native NSTimeZone getTimeZone();
    @Property(selector = "setTimeZone:")
    public native void setTimeZone(NSTimeZone v);
    @Property(selector = "dateTemplate")
    public native String getDateTemplate();
    @Property(selector = "setDateTemplate:")
    public native void setDateTemplate(String v);
    @Property(selector = "dateStyle")
    public native NSDateIntervalFormatterStyle getDateStyle();
    @Property(selector = "setDateStyle:")
    public native void setDateStyle(NSDateIntervalFormatterStyle v);
    @Property(selector = "timeStyle")
    public native NSDateIntervalFormatterStyle getTimeStyle();
    @Property(selector = "setTimeStyle:")
    public native void setTimeStyle(NSDateIntervalFormatterStyle v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "stringFromDate:toDate:")
    public native String format(NSDate fromDate, NSDate toDate);
    /*</methods>*/
}
