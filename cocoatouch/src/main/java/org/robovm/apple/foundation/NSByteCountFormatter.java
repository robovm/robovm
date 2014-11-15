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
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSByteCountFormatter/*</name>*/ 
    extends /*<extends>*/NSFormatter/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSByteCountFormatterPtr extends Ptr<NSByteCountFormatter, NSByteCountFormatterPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSByteCountFormatter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSByteCountFormatter() {}
    protected NSByteCountFormatter(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "allowedUnits")
    public native NSByteCountFormatterUnits getAllowedUnits();
    @Property(selector = "setAllowedUnits:")
    public native void setAllowedUnits(NSByteCountFormatterUnits v);
    @Property(selector = "countStyle")
    public native NSByteCountFormatterCountStyle getCountStyle();
    @Property(selector = "setCountStyle:")
    public native void setCountStyle(NSByteCountFormatterCountStyle v);
    @Property(selector = "allowsNonnumericFormatting")
    public native boolean isAllowsNonnumericFormatting();
    @Property(selector = "setAllowsNonnumericFormatting:")
    public native void setAllowsNonnumericFormatting(boolean v);
    @Property(selector = "includesUnit")
    public native boolean isIncludesUnit();
    @Property(selector = "setIncludesUnit:")
    public native void setIncludesUnit(boolean v);
    @Property(selector = "includesCount")
    public native boolean isIncludesCount();
    @Property(selector = "setIncludesCount:")
    public native void setIncludesCount(boolean v);
    @Property(selector = "includesActualByteCount")
    public native boolean isIncludesActualByteCount();
    @Property(selector = "setIncludesActualByteCount:")
    public native void setIncludesActualByteCount(boolean v);
    @Property(selector = "isAdaptive")
    public native boolean isAdaptive();
    @Property(selector = "setAdaptive:")
    public native void setAdaptive(boolean v);
    @Property(selector = "zeroPadsFractionDigits")
    public native boolean isZeroPadsFractionDigits();
    @Property(selector = "setZeroPadsFractionDigits:")
    public native void setZeroPadsFractionDigits(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "formattingContext")
    public native NSFormattingContext getFormattingContext();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setFormattingContext:")
    public native void setFormattingContext(NSFormattingContext v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "stringFromByteCount:")
    public native String format(long byteCount);
    @Method(selector = "stringFromByteCount:countStyle:")
    public static native String format(long byteCount, NSByteCountFormatterCountStyle countStyle);
    /*</methods>*/
}
