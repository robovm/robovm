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
/*</javadoc>*/
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLFileProperty/*</name>*/ 
    extends /*<extends>*/NSURLProperty/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSURLFileProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileProperty FileSize = new NSURLFileProperty("FileSizeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileProperty FileAllocatedSize = new NSURLFileProperty("FileAllocatedSizeValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileProperty TotalFileSize = new NSURLFileProperty("TotalFileSizeValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileProperty TotalFileAllocatedSize = new NSURLFileProperty("TotalFileAllocatedSizeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileProperty IsAliasFile = new NSURLFileProperty("IsAliasFileValue");
    
    private static NSURLFileProperty[] values = new NSURLFileProperty[] {FileSize, FileAllocatedSize, TotalFileSize, TotalFileAllocatedSize, IsAliasFile};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSURLFileProperty(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSURLFileProperty valueOf(NSString value) {
        for (NSURLFileProperty v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLFileSizeKey", optional=true)
    protected static native NSString FileSizeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLFileAllocatedSizeKey", optional=true)
    protected static native NSString FileAllocatedSizeValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLTotalFileSizeKey", optional=true)
    protected static native NSString TotalFileSizeValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLTotalFileAllocatedSizeKey", optional=true)
    protected static native NSString TotalFileAllocatedSizeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsAliasFileKey", optional=true)
    protected static native NSString IsAliasFileValue();
    /*</methods>*/
}
