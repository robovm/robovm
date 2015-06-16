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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/NSMetadataItemAttribute/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMetadataItemAttribute/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSMetadataItemAttribute/*</name>*/.class); }

    /*<marshalers>*/
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
            for (int i = 0; i < o.size(); i++) {
                list.add(NSMetadataItemAttribute.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSMetadataItemAttribute> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSMetadataItemAttribute o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute FSName = new NSMetadataItemAttribute("FSName");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute DisplayName = new NSMetadataItemAttribute("DisplayName");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute URL = new NSMetadataItemAttribute("URL");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute Path = new NSMetadataItemAttribute("Path");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute FSSize = new NSMetadataItemAttribute("FSSize");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute FSCreationDate = new NSMetadataItemAttribute("FSCreationDate");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute FSContentChangeDate = new NSMetadataItemAttribute("FSContentChangeDate");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSMetadataItemAttribute ContentType = new NSMetadataItemAttribute("ContentType");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSMetadataItemAttribute ContentTypeTree = new NSMetadataItemAttribute("ContentTypeTree");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute IsUbiquitous = new NSMetadataItemAttribute("IsUbiquitous");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute HasUnresolvedConflicts = new NSMetadataItemAttribute("HasUnresolvedConflicts");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final NSMetadataItemAttribute IsDownloaded = new NSMetadataItemAttribute("IsDownloaded");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSMetadataItemAttribute DownloadingStatus = new NSMetadataItemAttribute("DownloadingStatus");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute IsDownloading = new NSMetadataItemAttribute("IsDownloading");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute IsUploaded = new NSMetadataItemAttribute("IsUploaded");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute IsUploading = new NSMetadataItemAttribute("IsUploading");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute PercentDownloaded = new NSMetadataItemAttribute("PercentDownloaded");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute PercentUploaded = new NSMetadataItemAttribute("PercentUploaded");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSMetadataItemAttribute DownloadingError = new NSMetadataItemAttribute("DownloadingError");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final NSMetadataItemAttribute UploadingError = new NSMetadataItemAttribute("UploadingError");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSMetadataItemAttribute DownloadRequested = new NSMetadataItemAttribute("DownloadRequested");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSMetadataItemAttribute IsExternalDocument = new NSMetadataItemAttribute("IsExternalDocument");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSMetadataItemAttribute ContainerDisplayName = new NSMetadataItemAttribute("ContainerDisplayName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSMetadataItemAttribute URLInLocalContainer = new NSMetadataItemAttribute("URLInLocalContainer");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSMetadataItemAttribute ContentRelevance = new NSMetadataItemAttribute("ContentRelevance");
    /*</constants>*/
    
    private static /*<name>*/NSMetadataItemAttribute/*</name>*/[] values = new /*<name>*/NSMetadataItemAttribute/*</name>*/[] {/*<value_list>*/FSName, DisplayName, URL, Path, FSSize, FSCreationDate, FSContentChangeDate, ContentType, ContentTypeTree, IsUbiquitous, HasUnresolvedConflicts, IsDownloaded, DownloadingStatus, IsDownloading, IsUploaded, IsUploading, PercentDownloaded, PercentUploaded, DownloadingError, UploadingError, DownloadRequested, IsExternalDocument, ContainerDisplayName, URLInLocalContainer, ContentRelevance/*</value_list>*/};
    
    /*<name>*/NSMetadataItemAttribute/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSMetadataItemAttribute/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSMetadataItemAttribute/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSMetadataItemAttribute/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSMetadataItemFSNameKey", optional=true)
        public static native NSString FSName();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSMetadataItemDisplayNameKey", optional=true)
        public static native NSString DisplayName();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSMetadataItemURLKey", optional=true)
        public static native NSString URL();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSMetadataItemPathKey", optional=true)
        public static native NSString Path();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSMetadataItemFSSizeKey", optional=true)
        public static native NSString FSSize();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSMetadataItemFSCreationDateKey", optional=true)
        public static native NSString FSCreationDate();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSMetadataItemFSContentChangeDateKey", optional=true)
        public static native NSString FSContentChangeDate();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="NSMetadataItemContentTypeKey", optional=true)
        public static native NSString ContentType();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="NSMetadataItemContentTypeTreeKey", optional=true)
        public static native NSString ContentTypeTree();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSMetadataItemIsUbiquitousKey", optional=true)
        public static native NSString IsUbiquitous();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSMetadataUbiquitousItemHasUnresolvedConflictsKey", optional=true)
        public static native NSString HasUnresolvedConflicts();
        /**
         * @since Available in iOS 5.0 and later.
         * @deprecated Deprecated in iOS 7.0.
         */
        @Deprecated
        @GlobalValue(symbol="NSMetadataUbiquitousItemIsDownloadedKey", optional=true)
        public static native NSString IsDownloaded();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusKey", optional=true)
        public static native NSString DownloadingStatus();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSMetadataUbiquitousItemIsDownloadingKey", optional=true)
        public static native NSString IsDownloading();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSMetadataUbiquitousItemIsUploadedKey", optional=true)
        public static native NSString IsUploaded();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSMetadataUbiquitousItemIsUploadingKey", optional=true)
        public static native NSString IsUploading();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSMetadataUbiquitousItemPercentDownloadedKey", optional=true)
        public static native NSString PercentDownloaded();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSMetadataUbiquitousItemPercentUploadedKey", optional=true)
        public static native NSString PercentUploaded();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingErrorKey", optional=true)
        public static native NSString DownloadingError();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSMetadataUbiquitousItemUploadingErrorKey", optional=true)
        public static native NSString UploadingError();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadRequestedKey", optional=true)
        public static native NSString DownloadRequested();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="NSMetadataUbiquitousItemIsExternalDocumentKey", optional=true)
        public static native NSString IsExternalDocument();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="NSMetadataUbiquitousItemContainerDisplayNameKey", optional=true)
        public static native NSString ContainerDisplayName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="NSMetadataUbiquitousItemURLInLocalContainerKey", optional=true)
        public static native NSString URLInLocalContainer();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSMetadataQueryResultContentRelevanceAttribute", optional=true)
        public static native NSString ContentRelevance();
        /*</values>*/
    }
}
