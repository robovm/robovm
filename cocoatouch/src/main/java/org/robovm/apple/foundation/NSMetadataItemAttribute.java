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
@Marshaler(NSMetadataItemAttribute.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMetadataItemAttribute/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static NSMetadataItemAttribute toObject(Class<NSMetadataItemAttribute> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSMetadataItemAttribute.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSMetadataItemAttribute o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSMetadataItemAttribute> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSMetadataItemAttribute> list = new ArrayList<>();
            for (NSString str : o) {
                list.add(NSMetadataItemAttribute.valueOf(str));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSMetadataItemAttribute> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSString> array = new NSMutableArray<>();
            for (NSMetadataItemAttribute i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSMetadataItemAttribute.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute FSName = new NSMetadataItemAttribute("FSNameValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute DisplayName = new NSMetadataItemAttribute("DisplayNameValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute URL = new NSMetadataItemAttribute("URLValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute Path = new NSMetadataItemAttribute("PathValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute FSSize = new NSMetadataItemAttribute("FSSizeValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute FSCreationDate = new NSMetadataItemAttribute("FSCreationDateValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute FSContentChangeDate = new NSMetadataItemAttribute("FSContentChangeDateValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute IsUbiquitous = new NSMetadataItemAttribute("IsUbiquitousValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute HasUnresolvedConflicts = new NSMetadataItemAttribute("HasUnresolvedConflictsValue");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final NSMetadataItemAttribute IsDownloaded = new NSMetadataItemAttribute("IsDownloadedValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSMetadataItemAttribute DownloadingStatus = new NSMetadataItemAttribute("DownloadingStatusValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute IsDownloading = new NSMetadataItemAttribute("IsDownloadingValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute IsUploaded = new NSMetadataItemAttribute("IsUploadedValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute IsUploading = new NSMetadataItemAttribute("IsUploadingValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute PercentDownloaded = new NSMetadataItemAttribute("PercentDownloadedValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute PercentUploaded = new NSMetadataItemAttribute("PercentUploadedValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSMetadataItemAttribute DownloadingError = new NSMetadataItemAttribute("DownloadingErrorValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSMetadataItemAttribute UploadingError = new NSMetadataItemAttribute("UploadingErrorValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute ContentRelevance = new NSMetadataItemAttribute("ContentRelevanceValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSMetadataItemAttribute DownloadRequested = new NSMetadataItemAttribute("DownloadRequestedValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSMetadataItemAttribute IsExternalDocument = new NSMetadataItemAttribute("IsExternalDocumentValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSMetadataItemAttribute ContainerDisplayName = new NSMetadataItemAttribute("ContainerDisplayNameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSMetadataItemAttribute URLInLocalContainer = new NSMetadataItemAttribute("URLInLocalContainerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSMetadataItemAttribute ContentType = new NSMetadataItemAttribute("ContentTypeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSMetadataItemAttribute ContentTypeTree = new NSMetadataItemAttribute("ContentTypeTreeValue");
    
    private static NSMetadataItemAttribute[] values = new NSMetadataItemAttribute[] {FSName, DisplayName, URL, Path, FSSize, FSCreationDate, FSContentChangeDate, IsUbiquitous, 
        HasUnresolvedConflicts, IsDownloaded, IsDownloading, IsUploaded, IsUploading, PercentDownloaded, PercentUploaded, ContentRelevance, DownloadingStatus, DownloadingError, 
        UploadingError, DownloadRequested, IsExternalDocument, ContainerDisplayName, URLInLocalContainer, ContentType, ContentTypeTree};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSMetadataItemAttribute(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSMetadataItemAttribute valueOf(NSString value) {
        for (NSMetadataItemAttribute v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSMetadataItemAttribute/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemFSNameKey", optional=true)
    protected static native NSString FSNameValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemDisplayNameKey", optional=true)
    protected static native NSString DisplayNameValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemURLKey", optional=true)
    protected static native NSString URLValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemPathKey", optional=true)
    protected static native NSString PathValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemFSSizeKey", optional=true)
    protected static native NSString FSSizeValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemFSCreationDateKey", optional=true)
    protected static native NSString FSCreationDateValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemFSContentChangeDateKey", optional=true)
    protected static native NSString FSContentChangeDateValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemContentTypeKey", optional=true)
    protected static native NSString ContentTypeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemContentTypeTreeKey", optional=true)
    protected static native NSString ContentTypeTreeValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemIsUbiquitousKey", optional=true)
    protected static native NSString IsUbiquitousValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemHasUnresolvedConflictsKey", optional=true)
    protected static native NSString HasUnresolvedConflictsValue();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsDownloadedKey", optional=true)
    protected static native NSString IsDownloadedValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusKey", optional=true)
    protected static native NSString DownloadingStatusValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsDownloadingKey", optional=true)
    protected static native NSString IsDownloadingValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsUploadedKey", optional=true)
    protected static native NSString IsUploadedValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsUploadingKey", optional=true)
    protected static native NSString IsUploadingValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemPercentDownloadedKey", optional=true)
    protected static native NSString PercentDownloadedValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemPercentUploadedKey", optional=true)
    protected static native NSString PercentUploadedValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingErrorKey", optional=true)
    protected static native NSString DownloadingErrorValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemUploadingErrorKey", optional=true)
    protected static native NSString UploadingErrorValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadRequestedKey", optional=true)
    protected static native NSString DownloadRequestedValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsExternalDocumentKey", optional=true)
    protected static native NSString IsExternalDocumentValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemContainerDisplayNameKey", optional=true)
    protected static native NSString ContainerDisplayNameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemURLInLocalContainerKey", optional=true)
    protected static native NSString URLInLocalContainerValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryResultContentRelevanceAttribute", optional=true)
    protected static native NSString ContentRelevanceValue();
    /*</methods>*/
}
