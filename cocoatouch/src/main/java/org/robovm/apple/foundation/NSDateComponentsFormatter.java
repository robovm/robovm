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
import org.robovm.apple.coredata.*;
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSDateComponentsFormatter/*</name>*/ 
    extends /*<extends>*/NSFormatter/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSDateComponentsFormatterPtr extends Ptr<NSDateComponentsFormatter, NSDateComponentsFormatterPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSDateComponentsFormatter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSDateComponentsFormatter() {}
    protected NSDateComponentsFormatter(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "unitsStyle")
    public native NSDateComponentsFormatterUnitsStyle getUnitsStyle();
    @Property(selector = "setUnitsStyle:")
    public native void setUnitsStyle(NSDateComponentsFormatterUnitsStyle v);
    @Property(selector = "allowedUnits")
    public native NSCalendarUnit getAllowedUnits();
    @Property(selector = "setAllowedUnits:")
    public native void setAllowedUnits(NSCalendarUnit v);
    @Property(selector = "zeroFormattingBehavior")
    public native NSDateComponentsFormatterZeroFormattingBehavior getZeroFormattingBehavior();
    @Property(selector = "setZeroFormattingBehavior:")
    public native void setZeroFormattingBehavior(NSDateComponentsFormatterZeroFormattingBehavior v);
    @Property(selector = "calendar")
    public native NSCalendar getCalendar();
    @Property(selector = "setCalendar:")
    public native void setCalendar(NSCalendar v);
    @Property(selector = "allowsFractionalUnits")
    public native boolean isAllowsFractionalUnits();
    @Property(selector = "setAllowsFractionalUnits:")
    public native void setAllowsFractionalUnits(boolean v);
    @Property(selector = "maximumUnitCount")
    public native @MachineSizedSInt long getMaximumUnitCount();
    @Property(selector = "setMaximumUnitCount:")
    public native void setMaximumUnitCount(@MachineSizedSInt long v);
    @Property(selector = "collapsesLargestUnit")
    public native boolean isCollapsesLargestUnit();
    @Property(selector = "setCollapsesLargestUnit:")
    public native void setCollapsesLargestUnit(boolean v);
    @Property(selector = "includesApproximationPhrase")
    public native boolean isIncludesApproximationPhrase();
    @Property(selector = "setIncludesApproximationPhrase:")
    public native void setIncludesApproximationPhrase(boolean v);
    @Property(selector = "includesTimeRemainingPhrase")
    public native boolean isIncludesTimeRemainingPhrase();
    @Property(selector = "setIncludesTimeRemainingPhrase:")
    public native void setIncludesTimeRemainingPhrase(boolean v);
    @Property(selector = "formattingContext")
    public native NSFormattingContext getFormattingContext();
    @Property(selector = "setFormattingContext:")
    public native void setFormattingContext(NSFormattingContext v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "stringForObjectValue:")
    public native String format(NSObject obj);
    @Method(selector = "stringFromDateComponents:")
    public native String format(NSDateComponents components);
    @Method(selector = "stringFromDate:toDate:")
    public native String format(NSDate startDate, NSDate endDate);
    @Method(selector = "stringFromTimeInterval:")
    public native String format(double ti);
    /*</methods>*/
}
