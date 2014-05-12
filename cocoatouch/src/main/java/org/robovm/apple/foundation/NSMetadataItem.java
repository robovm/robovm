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
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMetadataItem/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSMetadataItemPtr extends Ptr<NSMetadataItem, NSMetadataItemPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSMetadataItem.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMetadataItem() {}
    protected NSMetadataItem(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemFSNameKey", optional=true)
    public static native NSString KeyFSName();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemDisplayNameKey", optional=true)
    public static native NSString KeyDisplayName();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemURLKey", optional=true)
    public static native NSString KeyURL();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemPathKey", optional=true)
    public static native NSString KeyPath();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemFSSizeKey", optional=true)
    public static native NSString KeyFSSize();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemFSCreationDateKey", optional=true)
    public static native NSString KeyFSCreationDate();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemFSContentChangeDateKey", optional=true)
    public static native NSString KeyFSContentChangeDate();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemIsUbiquitousKey", optional=true)
    public static native NSString KeyIsUbiquitous();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemHasUnresolvedConflictsKey", optional=true)
    public static native NSString KeyUbiquitousHasUnresolvedConflicts();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsDownloadedKey", optional=true)
    public static native NSString KeyUbiquitousIsDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusKey", optional=true)
    public static native NSString KeyUbiquitousDownloadingStatus();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusNotDownloaded", optional=true)
    public static native NSString UbiquitousDownloadingStatusNotDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusDownloaded", optional=true)
    public static native NSString UbiquitousDownloadingStatusDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusCurrent", optional=true)
    public static native NSString UbiquitousDownloadingStatusCurrent();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsDownloadingKey", optional=true)
    public static native NSString KeyUbiquitousIsDownloading();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsUploadedKey", optional=true)
    public static native NSString KeyUbiquitousIsUploaded();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsUploadingKey", optional=true)
    public static native NSString KeyUbiquitousIsUploading();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemPercentDownloadedKey", optional=true)
    public static native NSString KeyUbiquitousPercentDownloaded();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemPercentUploadedKey", optional=true)
    public static native NSString KeyUbiquitousPercentUploaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingErrorKey", optional=true)
    public static native NSString KeyUbiquitousDownloadingError();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemUploadingErrorKey", optional=true)
    public static native NSString KeyUbiquitousUploadingError();
    
    @Method(selector = "valueForAttribute:")
    public native NSObject valueForAttribute$(String key);
    @Method(selector = "valuesForAttributes:")
    public native NSDictionary<?, ?> valuesForAttributes$(NSArray<?> keys);
    @Method(selector = "attributes")
    public native NSArray<?> attributes();
    /*</methods>*/
}
