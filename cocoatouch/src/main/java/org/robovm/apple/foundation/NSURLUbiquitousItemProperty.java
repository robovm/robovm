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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLUbiquitousItemProperty/*</name>*/ 
    extends /*<extends>*/NSURLProperty/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSURLUbiquitousItemProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLUbiquitousItemProperty IsUbiquitousItem = new NSURLUbiquitousItemProperty("IsUbiquitousItemValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLUbiquitousItemProperty HasUnresolvedConflicts = new NSURLUbiquitousItemProperty("HasUnresolvedConflictsValue");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final NSURLUbiquitousItemProperty IsDownloaded = new NSURLUbiquitousItemProperty("IsDownloadedValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLUbiquitousItemProperty IsDownloading = new NSURLUbiquitousItemProperty("IsDownloadingValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLUbiquitousItemProperty IsUploaded = new NSURLUbiquitousItemProperty("IsUploadedValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLUbiquitousItemProperty IsUploading = new NSURLUbiquitousItemProperty("IsUploadingValue");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    public static final NSURLUbiquitousItemProperty PercentDownloaded = new NSURLUbiquitousItemProperty("PercentDownloadedValue");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    public static final NSURLUbiquitousItemProperty PercentUploaded = new NSURLUbiquitousItemProperty("PercentUploadedValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSURLUbiquitousItemProperty DownloadingStatus = new NSURLUbiquitousItemProperty("DownloadingStatusValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSURLUbiquitousItemProperty DownloadingError = new NSURLUbiquitousItemProperty("DownloadingErrorValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSURLUbiquitousItemProperty UploadingError = new NSURLUbiquitousItemProperty("UploadingErrorValue");
    
    private static NSURLUbiquitousItemProperty[] values = new NSURLUbiquitousItemProperty[] {IsUbiquitousItem, HasUnresolvedConflicts, IsDownloading, IsUploaded, IsUploading, PercentDownloaded, 
        PercentUploaded, IsDownloaded, DownloadingStatus, DownloadingError, UploadingError};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSURLUbiquitousItemProperty(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSURLUbiquitousItemProperty valueOf(NSString value) {
        for (NSURLUbiquitousItemProperty v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSURLUbiquitousItemProperty/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsUbiquitousItemKey", optional=true)
    protected static native NSString IsUbiquitousItemValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemHasUnresolvedConflictsKey", optional=true)
    protected static native NSString HasUnresolvedConflictsValue();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSURLUbiquitousItemIsDownloadedKey", optional=true)
    protected static native NSString IsDownloadedValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemIsDownloadingKey", optional=true)
    protected static native NSString IsDownloadingValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemIsUploadedKey", optional=true)
    protected static native NSString IsUploadedValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemIsUploadingKey", optional=true)
    protected static native NSString IsUploadingValue();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSURLUbiquitousItemPercentDownloadedKey", optional=true)
    protected static native NSString PercentDownloadedValue();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSURLUbiquitousItemPercentUploadedKey", optional=true)
    protected static native NSString PercentUploadedValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingStatusKey", optional=true)
    protected static native NSString DownloadingStatusValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingErrorKey", optional=true)
    protected static native NSString DownloadingErrorValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemUploadingErrorKey", optional=true)
    protected static native NSString UploadingErrorValue();
    /*</methods>*/
}
