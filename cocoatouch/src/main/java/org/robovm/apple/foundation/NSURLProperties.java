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
@Marshaler(NSURLProperties.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class NSURLProperties 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static NSURLProperties toObject(Class<NSURLProperties> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSURLProperties(o);
        }
        @MarshalsPointer
        public static long toNative(NSURLProperties o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected NSURLProperties(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public NSURLProperties() {
    	data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(NSURLProperties.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public NSObject get(String property) {
        return data.get(new NSString(property));
    }
    public NSObject get(NSURLFileSystemProperty property) {
        return data.get(property.value());
    }
    public NSObject get(NSURLFileProperty property) {
        return data.get(property.value());
    }
    public NSObject get(NSURLVolumeProperty property) {
        return data.get(property.value());
    }
    public NSObject get(NSURLUbiquitousItemProperty property) {
        return data.get(property.value());
    }
    
    public boolean contains(String property) {
        return data.containsKey(new NSString(property));
    }
    public boolean contains(NSURLFileSystemProperty property) {
        return data.containsKey(property.value());
    }
    public boolean contains(NSURLFileProperty property) {
        return data.containsKey(property.value());
    }
    public boolean contains(NSURLVolumeProperty property) {
        return data.containsKey(property.value());
    }
    public boolean contains(NSURLUbiquitousItemProperty property) {
        return data.containsKey(property.value());
    }
    
    public NSURLProperties put(String property, NSObject value) {
        data.put(new NSString(property), value);
        return this;
    }
    public NSURLProperties put(NSURLFileSystemProperty property, NSObject value) {
        data.put(property.value(), value);
        return this;
    }
    public NSURLProperties put(NSURLFileProperty property, NSObject value) {
        data.put(property.value(), value);
        return this;
    }
    public NSURLProperties put(NSURLVolumeProperty property, NSObject value) {
        data.put(property.value(), value);
        return this;
    }
    public NSURLProperties put(NSURLUbiquitousItemProperty property, NSObject value) {
        data.put(property.value(), value);
        return this;
    }
    
    
    /* NSURLFileSystemProperty */
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getName() {
        if (contains(NSURLFileSystemProperty.Name)) {
            NSString val = (NSString)get(NSURLFileSystemProperty.Name);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSURLProperties setName(String name) {
        put(NSURLFileSystemProperty.Name, new NSString(name));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getLocalizedName() {
        if (contains(NSURLFileSystemProperty.LocalizedName)) {
            NSString val = (NSString)get(NSURLFileSystemProperty.LocalizedName);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isRegularFile() {
        if (contains(NSURLFileSystemProperty.IsRegularFile)) {
            NSNumber val = (NSNumber)get(NSURLFileSystemProperty.IsRegularFile);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isDirectory() {
        if (contains(NSURLFileSystemProperty.IsDirectory)) {
            NSNumber val = (NSNumber)get(NSURLFileSystemProperty.IsDirectory);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isSymbolicLink() {
        if (contains(NSURLFileSystemProperty.IsSymbolicLink)) {
            NSNumber val = (NSNumber)get(NSURLFileSystemProperty.IsSymbolicLink);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isVolume() {
        if (contains(NSURLFileSystemProperty.IsVolume)) {
            NSNumber val = (NSNumber)get(NSURLFileSystemProperty.IsVolume);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isPackage() {
        if (contains(NSURLFileSystemProperty.IsPackage)) {
            NSNumber val = (NSNumber)get(NSURLFileSystemProperty.IsPackage);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public NSURLProperties setPackage(boolean isPackage) {
        put(NSURLFileSystemProperty.IsPackage, NSNumber.valueOf(isPackage));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isSystemImmutable() {
        if (contains(NSURLFileSystemProperty.IsSystemImmutable)) {
            NSNumber val = (NSNumber)get(NSURLFileSystemProperty.IsSystemImmutable);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSURLProperties setSystemImmutable(boolean immutable) {
        put(NSURLFileSystemProperty.IsSystemImmutable, NSNumber.valueOf(immutable));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isUserImmutable() {
        if (contains(NSURLFileSystemProperty.IsUserImmutable)) {
            NSNumber val = (NSNumber)get(NSURLFileSystemProperty.IsUserImmutable);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSURLProperties setUserImmutable(boolean immutable) {
        put(NSURLFileSystemProperty.IsUserImmutable, NSNumber.valueOf(immutable));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isHidden() {
        if (contains(NSURLFileSystemProperty.IsHidden)) {
            NSNumber val = (NSNumber)get(NSURLFileSystemProperty.IsHidden);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSURLProperties setHidden(boolean hidden) {
        put(NSURLFileSystemProperty.IsHidden, NSNumber.valueOf(hidden));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean hasHiddenExtension() {
        if (contains(NSURLFileSystemProperty.HasHiddenExtension)) {
            NSNumber val = (NSNumber)get(NSURLFileSystemProperty.HasHiddenExtension);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSURLProperties setHasHiddenExtension(boolean hiddenExtension) {
        put(NSURLFileSystemProperty.HasHiddenExtension, NSNumber.valueOf(hiddenExtension));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSDate getCreationDate() {
        if (contains(NSURLFileSystemProperty.CreationDate)) {
            NSDate val = (NSDate)get(NSURLFileSystemProperty.CreationDate);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSURLProperties setCreationDate(NSDate date) {
        put(NSURLFileSystemProperty.CreationDate, date);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSDate getContentAccessDate() {
        if (contains(NSURLFileSystemProperty.ContentAccessDate)) {
            NSDate val = (NSDate)get(NSURLFileSystemProperty.ContentAccessDate);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSDate getContentModificationDate() {
        if (contains(NSURLFileSystemProperty.ContentModificationDate)) {
            NSDate val = (NSDate)get(NSURLFileSystemProperty.ContentModificationDate);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSURLProperties setContentModificationDate(NSDate date) {
        put(NSURLFileSystemProperty.ContentModificationDate, date);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSDate getAttributeModificationDate() {
        if (contains(NSURLFileSystemProperty.AttributeModificationDate)) {
            NSDate val = (NSDate)get(NSURLFileSystemProperty.AttributeModificationDate);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSURLProperties setAtttributeModificationDate(NSDate date) {
        put(NSURLFileSystemProperty.AttributeModificationDate, date);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getLinkCount() {
        if (contains(NSURLFileSystemProperty.LinkCount)) {
            NSNumber val = (NSNumber)get(NSURLFileSystemProperty.LinkCount);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSURL getParentDirectoryURL() {
        if (contains(NSURLFileSystemProperty.ParentDirectoryURL)) {
            NSURL val = (NSURL)get(NSURLFileSystemProperty.ParentDirectoryURL);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSURL getVolumeURL() {
        if (contains(NSURLFileSystemProperty.VolumeURL)) {
            NSURL val = (NSURL)get(NSURLFileSystemProperty.VolumeURL);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getTypeIdentifier() {
        if (contains(NSURLFileSystemProperty.TypeIdentifier)) {
            NSString val = (NSString)get(NSURLFileSystemProperty.TypeIdentifier);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getLocalizedTypeDescription() {
        if (contains(NSURLFileSystemProperty.LocalizedTypeDescription)) {
            NSString val = (NSString)get(NSURLFileSystemProperty.LocalizedTypeDescription);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getLabelNumber() {
        if (contains(NSURLFileSystemProperty.LabelNumber)) {
            NSNumber val = (NSNumber)get(NSURLFileSystemProperty.LabelNumber);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSObject getFileResourceIdentifier() {
        if (contains(NSURLFileSystemProperty.FileResourceIdentifier)) {
            NSObject val = get(NSURLFileSystemProperty.FileResourceIdentifier);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSObject getVolumeIdentifier() {
        if (contains(NSURLFileSystemProperty.VolumeIdentifier)) {
            NSObject val = get(NSURLFileSystemProperty.VolumeIdentifier);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isReadable() {
        if (contains(NSURLFileSystemProperty.IsReadable)) {
            NSNumber val = (NSNumber)get(NSURLFileSystemProperty.IsReadable);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isWritable() {
        if (contains(NSURLFileSystemProperty.IsWritable)) {
            NSNumber val = (NSNumber)get(NSURLFileSystemProperty.IsWritable);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isExecutable() {
        if (contains(NSURLFileSystemProperty.IsExecutable)) {
            NSNumber val = (NSNumber)get(NSURLFileSystemProperty.IsExecutable);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.1 and later.
     */
    public boolean isExcludedFromBackup() {
        if (contains(NSURLFileSystemProperty.IsExcludedFromBackup)) {
            NSNumber val = (NSNumber)get(NSURLFileSystemProperty.IsExcludedFromBackup);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.1 and later.
     */
    public NSURLProperties setExcludedFromBackground(boolean excludeFromBackground) {
        put(NSURLFileSystemProperty.IsExcludedFromBackup, NSNumber.valueOf(excludeFromBackground));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public String getPath() {
        if (contains(NSURLFileSystemProperty.Path)) {
            NSString val = (NSString)get(NSURLFileSystemProperty.Path);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isMountTrigger() {
        if (contains(NSURLFileSystemProperty.IsMountTrigger)) {
            NSNumber val = (NSNumber)get(NSURLFileSystemProperty.IsMountTrigger);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public NSObject getGenerationIdentifier() {
        if (contains(NSURLFileSystemProperty.GenerationIdentifier)) {
            NSObject val = get(NSURLFileSystemProperty.GenerationIdentifier);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public long getDocumentIdentifier() {
        if (contains(NSURLFileSystemProperty.DocumentIdentifier)) {
            NSNumber val = (NSNumber)get(NSURLFileSystemProperty.DocumentIdentifier);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public NSDate getAddedToDirectoryDate() {
        if (contains(NSURLFileSystemProperty.AddedToDirectoryDate)) {
            NSDate val = (NSDate)get(NSURLFileSystemProperty.AddedToDirectoryDate);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSURLFileResourceType getFileResourceType() {
        if (contains(NSURLFileSystemProperty.FileResourceType)) {
            NSString val = (NSString)get(NSURLFileSystemProperty.FileResourceType);
            return NSURLFileResourceType.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSURLThumbnail getThumbnail() {
        if (contains(NSURLFileSystemProperty.ThumbnailDictionary)) {
            NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>)get(NSURLFileSystemProperty.ThumbnailDictionary);
            return new NSURLThumbnail(val);
        }
        return null;
    }

    /* NSURLFileProperty */
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getFileSize() {
        if (contains(NSURLFileProperty.FileSize)) {
            NSNumber val = (NSNumber)get(NSURLFileProperty.FileSize);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getAllocatedSize() {
        if (contains(NSURLFileProperty.FileAllocatedSize)) {
            NSNumber val = (NSNumber)get(NSURLFileProperty.FileAllocatedSize);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public long getTotalFileSize() {
        if (contains(NSURLFileProperty.TotalFileSize)) {
            NSNumber val = (NSNumber)get(NSURLFileProperty.TotalFileSize);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public long getTotalAllocatedSize() {
        if (contains(NSURLFileProperty.TotalFileAllocatedSize)) {
            NSNumber val = (NSNumber)get(NSURLFileProperty.TotalFileAllocatedSize);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isAliasFile() {
        if (contains(NSURLFileProperty.IsAliasFile)) {
            NSNumber val = (NSNumber)get(NSURLFileProperty.IsAliasFile);
            return val.booleanValue();
        }
        return false;
    }
    
    /* NSURLVolumeProperty */
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getVolumeLocalizedFormatDescription() {
        if (contains(NSURLVolumeProperty.LocalizedFormatDescription)) {
            NSString val = (NSString)get(NSURLVolumeProperty.LocalizedFormatDescription);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getVolumeTotalCapacity() {
        if (contains(NSURLVolumeProperty.TotalCapacity)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.TotalCapacity);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getVolumeAvailableCapacity() {
        if (contains(NSURLVolumeProperty.AvailableCapacity)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.AvailableCapacity);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getVolumeResourceCount() {
        if (contains(NSURLVolumeProperty.ResourceCount)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.ResourceCount);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean volumeSupportsPersistentIDs() {
        if (contains(NSURLVolumeProperty.SupportsPersistentIDs)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.SupportsPersistentIDs);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean volumeSupportsSymbolicLinks() {
        if (contains(NSURLVolumeProperty.SupportsSymbolicLinks)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.SupportsSymbolicLinks);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean volumeSupportsHardLinks() {
        if (contains(NSURLVolumeProperty.SupportsHardLinks)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.SupportsHardLinks);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean volumeSupportsJournaling() {
        if (contains(NSURLVolumeProperty.SupportsJournaling)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.SupportsJournaling);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isVolumeJournaling() {
        if (contains(NSURLVolumeProperty.IsJournaling)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.IsJournaling);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean volumeSupportsSparseFiles() {
        if (contains(NSURLVolumeProperty.SupportsSparseFiles)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.SupportsSparseFiles);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean volumeSupportsZeroRuns() {
        if (contains(NSURLVolumeProperty.SupportsZeroRuns)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.SupportsZeroRuns);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean volumeSupportsCaseSensitiveNames() {
        if (contains(NSURLVolumeProperty.SupportsCaseSensitiveNames)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.SupportsCaseSensitiveNames);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean volumeSupportsCasePreservedNames() {
        if (contains(NSURLVolumeProperty.SupportsCasePreservedNames)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.SupportsCasePreservedNames);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean volumeSupportsRootDirectoryDates() {
        if (contains(NSURLVolumeProperty.SupportsRootDirectoryDates)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.SupportsRootDirectoryDates);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean volumeSupportsVolumeSizes() {
        if (contains(NSURLVolumeProperty.SupportsVolumeSizes)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.SupportsVolumeSizes);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean volumeSupportsRenaming() {
        if (contains(NSURLVolumeProperty.SupportsRenaming)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.SupportsRenaming);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean volumeSupportsAdvisoryFileLocking() {
        if (contains(NSURLVolumeProperty.SupportsAdvisoryFileLocking)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.SupportsAdvisoryFileLocking);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean volumeSupportsExtendedSecurity() {
        if (contains(NSURLVolumeProperty.SupportsExtendedSecurity)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.SupportsExtendedSecurity);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isVolumeBrowsable() {
        if (contains(NSURLVolumeProperty.IsBrowsable)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.IsBrowsable);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public long getVolumeMaximueFileSize() {
        if (contains(NSURLVolumeProperty.MaximumFileSize)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.MaximumFileSize);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isVolumeEjectable() {
        if (contains(NSURLVolumeProperty.IsEjectable)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.IsEjectable);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isVolumeRemovable() {
        if (contains(NSURLVolumeProperty.IsRemovable)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.IsRemovable);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isVolumeInternal() {
        if (contains(NSURLVolumeProperty.IsInternal)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.IsInternal);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isVolumeAutomounted() {
        if (contains(NSURLVolumeProperty.IsAutomounted)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.IsAutomounted);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isVolumeLocal() {
        if (contains(NSURLVolumeProperty.IsLocal)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.IsLocal);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isVolumeReadOnly() {
        if (contains(NSURLVolumeProperty.IsReadOnly)) {
            NSNumber val = (NSNumber)get(NSURLVolumeProperty.IsReadOnly);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSDate getVolumeCreationDate() {
        if (contains(NSURLVolumeProperty.CreationDate)) {
            NSDate val = (NSDate)get(NSURLVolumeProperty.CreationDate);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSURL getVolumeURLForRemounting() {
        if (contains(NSURLVolumeProperty.URLForRemounting)) {
            NSURL val = (NSURL)get(NSURLVolumeProperty.URLForRemounting);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public String getVolumeUUIDString() {
        if (contains(NSURLVolumeProperty.UUIDString)) {
            NSString val = (NSString)get(NSURLVolumeProperty.UUIDString);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public String getVolumeName() {
        if (contains(NSURLVolumeProperty.Name)) {
            NSString val = (NSString)get(NSURLVolumeProperty.Name);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSURLProperties setVolumeName(String name) {
        put(NSURLVolumeProperty.Name, new NSString(name));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public String getVolumeLocalizedName() {
        if (contains(NSURLVolumeProperty.LocalizedName)) {
            NSString val = (NSString)get(NSURLVolumeProperty.LocalizedName);
            return val.toString();
        }
        return null;
    }
    
    /* NSURLUbiquitousItemProperty */
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isUbiquitousItem() {
        if (contains(NSURLUbiquitousItemProperty.IsUbiquitousItem)) {
            NSNumber val = (NSNumber)get(NSURLUbiquitousItemProperty.IsUbiquitousItem);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean hasUnresolvedConflicts() {
        if (contains(NSURLUbiquitousItemProperty.HasUnresolvedConflicts)) {
            NSNumber val = (NSNumber)get(NSURLUbiquitousItemProperty.HasUnresolvedConflicts);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isDownloading() {
        if (contains(NSURLUbiquitousItemProperty.IsDownloading)) {
            NSNumber val = (NSNumber)get(NSURLUbiquitousItemProperty.IsDownloading);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isUploaded() {
        if (contains(NSURLUbiquitousItemProperty.IsUploaded)) {
            NSNumber val = (NSNumber)get(NSURLUbiquitousItemProperty.IsUploaded);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isUploading() {
        if (contains(NSURLUbiquitousItemProperty.IsUploading)) {
            NSNumber val = (NSNumber)get(NSURLUbiquitousItemProperty.IsUploading);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSURLUbiquitousItemDownloadingStatus getDownloadingStatus() {
        if (contains(NSURLUbiquitousItemProperty.DownloadingStatus)) {
            NSString val = (NSString)get(NSURLUbiquitousItemProperty.DownloadingStatus);
            return NSURLUbiquitousItemDownloadingStatus.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSError getDownloadingError() {
        if (contains(NSURLUbiquitousItemProperty.DownloadingError)) {
            NSError val = (NSError)get(NSURLUbiquitousItemProperty.DownloadingError);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSError getUploadingError() {
        if (contains(NSURLUbiquitousItemProperty.UploadingError)) {
            NSError val = (NSError)get(NSURLUbiquitousItemProperty.UploadingError);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean isDownloadRequested() {
        if (contains(NSURLUbiquitousItemProperty.DownloadRequested)) {
            NSNumber val = (NSNumber)get(NSURLUbiquitousItemProperty.DownloadRequested);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getContainerDisplayName() {
        if (contains(NSURLUbiquitousItemProperty.ContainerDisplayName)) {
            NSString val = (NSString)get(NSURLUbiquitousItemProperty.ContainerDisplayName);
            return val.toString();
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
