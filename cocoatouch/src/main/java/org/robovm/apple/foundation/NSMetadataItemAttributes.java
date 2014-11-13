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
/**
 * @since Available in iOS 7.0 and later.
 */
@Marshaler(NSMetadataItemAttributes.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class NSMetadataItemAttributes 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static NSMetadataItemAttributes toObject(Class<NSMetadataItemAttributes> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSMetadataItemAttributes(o);
        }
        @MarshalsPointer
        public static long toNative(NSMetadataItemAttributes o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected NSMetadataItemAttributes(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(NSMetadataItemAttributes.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public NSObject get(String attribute) {
        return data.get(new NSString(attribute));
    }
    public NSObject get(NSMetadataItemAttribute attribute) {
        return data.get(attribute.value());
    }
    public boolean contains(String attribute) {
        return data.containsKey(new NSString(attribute));
    }
    public boolean contains(NSMetadataItemAttribute attribute) {
        return data.containsKey(attribute.value());
    }
    
    
    /**
     * @since Available in iOS 5.0 and later.
     */
    public String getFSName() {
        if (contains(NSMetadataItemAttribute.FSName)) {
            NSString val = (NSString)get(NSMetadataItemAttribute.FSName);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public String getDisplayName() {
        if (contains(NSMetadataItemAttribute.DisplayName)) {
            NSString val = (NSString)get(NSMetadataItemAttribute.DisplayName);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSURL getURL() {
        if (contains(NSMetadataItemAttribute.URL)) {
            NSURL val = (NSURL)get(NSMetadataItemAttribute.URL);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public String getPath() {
        if (contains(NSMetadataItemAttribute.Path)) {
            NSString val = (NSString)get(NSMetadataItemAttribute.Path);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public long getFSSize() {
        if (contains(NSMetadataItemAttribute.FSSize)) {
            NSNumber val = (NSNumber)get(NSMetadataItemAttribute.FSSize);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSDate getFSCreationDate() {
        if (contains(NSMetadataItemAttribute.FSCreationDate)) {
            NSDate val = (NSDate)get(NSMetadataItemAttribute.FSCreationDate);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSDate getFSContentChangeDate() {
        if (contains(NSMetadataItemAttribute.FSContentChangeDate)) {
            NSDate val = (NSDate)get(NSMetadataItemAttribute.FSContentChangeDate);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isUbiquitous() {
        if (contains(NSMetadataItemAttribute.IsUbiquitous)) {
            NSNumber val = (NSNumber)get(NSMetadataItemAttribute.IsUbiquitous);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean hasUnresolvedConflicts() {
        if (contains(NSMetadataItemAttribute.HasUnresolvedConflicts)) {
            NSNumber val = (NSNumber)get(NSMetadataItemAttribute.HasUnresolvedConflicts);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public boolean isDownloaded() {
        if (contains(NSMetadataItemAttribute.IsDownloaded)) {
            NSNumber val = (NSNumber)get(NSMetadataItemAttribute.IsDownloaded);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSMetadataItemDownloadingStatus getDownloadingStatus() {
        if (contains(NSMetadataItemAttribute.DownloadingStatus)) {
            NSString val = (NSString)get(NSMetadataItemAttribute.DownloadingStatus);
            return NSMetadataItemDownloadingStatus.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isDownloading() {
        if (contains(NSMetadataItemAttribute.IsDownloading)) {
            NSNumber val = (NSNumber)get(NSMetadataItemAttribute.IsDownloading);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isUploaded() {
        if (contains(NSMetadataItemAttribute.IsUploaded)) {
            NSNumber val = (NSNumber)get(NSMetadataItemAttribute.IsUploaded);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isUploading() {
        if (contains(NSMetadataItemAttribute.IsUploading)) {
            NSNumber val = (NSNumber)get(NSMetadataItemAttribute.IsUploading);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public double getPercentDownloaded() {
        if (contains(NSMetadataItemAttribute.PercentDownloaded)) {
            NSNumber val = (NSNumber)get(NSMetadataItemAttribute.PercentDownloaded);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public double getPercentUploaded() {
        if (contains(NSMetadataItemAttribute.PercentUploaded)) {
            NSNumber val = (NSNumber)get(NSMetadataItemAttribute.PercentUploaded);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSError getDownloadingError() {
        if (contains(NSMetadataItemAttribute.DownloadingError)) {
            NSError val = (NSError)get(NSMetadataItemAttribute.DownloadingError);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSError getUploadingError() {
        if (contains(NSMetadataItemAttribute.UploadingError)) {
            NSError val = (NSError)get(NSMetadataItemAttribute.UploadingError);
            return val;
        }
        return null;
    }
    
    /**
     * Only used on query result items. 
     * @since Available in iOS 5.0 and later.
     * @return the relevance of the content of a result item.
     */
    public double getContentRelevance() {
        if (contains(NSMetadataItemAttribute.ContentRelevance)) {
            NSNumber val = (NSNumber)get(NSMetadataItemAttribute.ContentRelevance);
            return val.doubleValue();
        }
        return 0;
    }
    
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean isDownloadRequested() {
        if (contains(NSMetadataItemAttribute.DownloadRequested)) {
            NSNumber val = (NSNumber)get(NSMetadataItemAttribute.DownloadRequested);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean isExternalDocument() {
        if (contains(NSMetadataItemAttribute.IsExternalDocument)) {
            NSNumber val = (NSNumber)get(NSMetadataItemAttribute.IsExternalDocument);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getContainerDisplayName() {
        if (contains(NSMetadataItemAttribute.ContainerDisplayName)) {
            NSString val = (NSString)get(NSMetadataItemAttribute.ContainerDisplayName);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public NSURL getURLInLocalContainer() {
        if (contains(NSMetadataItemAttribute.URLInLocalContainer)) {
            NSURL val = (NSURL)get(NSMetadataItemAttribute.URLInLocalContainer);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getContentType() {
        if (contains(NSMetadataItemAttribute.ContentType)) {
            NSString val = (NSString)get(NSMetadataItemAttribute.ContentType);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @SuppressWarnings("unchecked")
    public List<String> getContentTypeTree() {
        if (contains(NSMetadataItemAttribute.ContentTypeTree)) {
            NSArray<NSString> val = (NSArray<NSString>)get(NSMetadataItemAttribute.ContentTypeTree);
            return val.asStringList();
        }
        return null;
    }
    /*<methods>*/
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
