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
@Marshaler(NSURLFileSystemProperty.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLFileSystemProperty/*</name>*/ 
    extends /*<extends>*/NSURLProperty/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static NSURLFileSystemProperty toObject(Class<NSURLFileSystemProperty> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSURLFileSystemProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSURLFileSystemProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSURLFileSystemProperty> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSURLFileSystemProperty> list = new ArrayList<>();
            for (NSString str : o) {
                list.add(NSURLFileSystemProperty.valueOf(str));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSURLFileSystemProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSString> array = new NSMutableArray<>();
            for (NSURLFileSystemProperty i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSURLFileSystemProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty Name = new NSURLFileSystemProperty("NameValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty LocalizedName = new NSURLFileSystemProperty("LocalizedNameValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty IsRegularFile = new NSURLFileSystemProperty("IsRegularFileValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty IsDirectory = new NSURLFileSystemProperty("IsDirectoryValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty IsSymbolicLink = new NSURLFileSystemProperty("IsSymbolicLinkValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty IsVolume = new NSURLFileSystemProperty("IsVolumeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty IsPackage = new NSURLFileSystemProperty("IsPackageValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty IsSystemImmutable = new NSURLFileSystemProperty("IsSystemImmutableValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty IsUserImmutable = new NSURLFileSystemProperty("IsUserImmutableValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty IsHidden = new NSURLFileSystemProperty("IsHiddenValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty HasHiddenExtension = new NSURLFileSystemProperty("HasHiddenExtensionValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty CreationDate = new NSURLFileSystemProperty("CreationDateValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty ContentAccessDate = new NSURLFileSystemProperty("ContentAccessDateValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty ContentModificationDate = new NSURLFileSystemProperty("ContentModificationDateValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty AttributeModificationDate = new NSURLFileSystemProperty("AttributeModificationDateValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty LinkCount = new NSURLFileSystemProperty("LinkCountValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty ParentDirectoryURL = new NSURLFileSystemProperty("ParentDirectoryURLValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty VolumeURL = new NSURLFileSystemProperty("VolumeURLValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty TypeIdentifier = new NSURLFileSystemProperty("TypeIdentifierValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty LocalizedTypeDescription = new NSURLFileSystemProperty("LocalizedTypeDescriptionValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty LabelNumber = new NSURLFileSystemProperty("LabelNumberValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty LabelColor = new NSURLFileSystemProperty("LabelColorValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty LocalizedLabel = new NSURLFileSystemProperty("LocalizedLabelValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty EffectiveIcon = new NSURLFileSystemProperty("EffectiveIconValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty CustomIcon = new NSURLFileSystemProperty("CustomIconValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileSystemProperty FileResourceIdentifier = new NSURLFileSystemProperty("FileResourceIdentifierValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileSystemProperty VolumeIdentifier = new NSURLFileSystemProperty("VolumeIdentifierValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileSystemProperty PreferredIOBlockSize = new NSURLFileSystemProperty("PreferredIOBlockSizeValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileSystemProperty IsReadable = new NSURLFileSystemProperty("IsReadableValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileSystemProperty IsWritable = new NSURLFileSystemProperty("IsWritableValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileSystemProperty IsExecutable = new NSURLFileSystemProperty("IsExecutableValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileSystemProperty FileSecurity = new NSURLFileSystemProperty("FileSecurityValue");
    /**
     * @since Available in iOS 5.1 and later.
     */
    public static final NSURLFileSystemProperty IsExcludedFromBackup = new NSURLFileSystemProperty("IsExcludedFromBackupValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSURLFileSystemProperty Path = new NSURLFileSystemProperty("PathValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileSystemProperty IsMountTrigger = new NSURLFileSystemProperty("IsMountTriggerValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileSystemProperty FileResourceType = new NSURLFileSystemProperty("FileResourceTypeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSURLFileSystemProperty GenerationIdentifier = new NSURLFileSystemProperty("GenerationIdentifierValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSURLFileSystemProperty DocumentIdentifier = new NSURLFileSystemProperty("DocumentIdentifierValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSURLFileSystemProperty AddedToDirectoryDate = new NSURLFileSystemProperty("AddedToDirectoryDateValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSURLFileSystemProperty ThumbnailDictionary = new NSURLFileSystemProperty("ThumbnailDictionaryValue");
    
    
    private static NSURLFileSystemProperty[] values = new NSURLFileSystemProperty[] {Name, LocalizedName, IsRegularFile, IsDirectory, IsSymbolicLink, IsVolume, IsPackage, IsSystemImmutable, 
        IsUserImmutable, IsHidden, HasHiddenExtension, CreationDate, ContentAccessDate, ContentModificationDate, AttributeModificationDate, LinkCount, ParentDirectoryURL, VolumeURL, TypeIdentifier, 
        LocalizedTypeDescription, LabelNumber, LabelNumber, LabelColor, LocalizedLabel, EffectiveIcon, CustomIcon, FileResourceIdentifier, VolumeIdentifier, PreferredIOBlockSize, IsReadable, 
        IsWritable, IsExecutable, FileSecurity, IsMountTrigger, FileResourceType, IsExcludedFromBackup, Path, GenerationIdentifier, DocumentIdentifier, AddedToDirectoryDate, ThumbnailDictionary};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSURLFileSystemProperty(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSURLFileSystemProperty valueOf(NSString value) {
        for (NSURLFileSystemProperty v : values) {
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
    @GlobalValue(symbol="NSURLNameKey", optional=true)
    protected static native NSString NameValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLocalizedNameKey", optional=true)
    protected static native NSString LocalizedNameValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsRegularFileKey", optional=true)
    protected static native NSString IsRegularFileValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsDirectoryKey", optional=true)
    protected static native NSString IsDirectoryValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsSymbolicLinkKey", optional=true)
    protected static native NSString IsSymbolicLinkValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsVolumeKey", optional=true)
    protected static native NSString IsVolumeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsPackageKey", optional=true)
    protected static native NSString IsPackageValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsSystemImmutableKey", optional=true)
    protected static native NSString IsSystemImmutableValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsUserImmutableKey", optional=true)
    protected static native NSString IsUserImmutableValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsHiddenKey", optional=true)
    protected static native NSString IsHiddenValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLHasHiddenExtensionKey", optional=true)
    protected static native NSString HasHiddenExtensionValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLCreationDateKey", optional=true)
    protected static native NSString CreationDateValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLContentAccessDateKey", optional=true)
    protected static native NSString ContentAccessDateValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLContentModificationDateKey", optional=true)
    protected static native NSString ContentModificationDateValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLAttributeModificationDateKey", optional=true)
    protected static native NSString AttributeModificationDateValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLinkCountKey", optional=true)
    protected static native NSString LinkCountValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLParentDirectoryURLKey", optional=true)
    protected static native NSString ParentDirectoryURLValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeURLKey", optional=true)
    protected static native NSString VolumeURLValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLTypeIdentifierKey", optional=true)
    protected static native NSString TypeIdentifierValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLocalizedTypeDescriptionKey", optional=true)
    protected static native NSString LocalizedTypeDescriptionValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLabelNumberKey", optional=true)
    protected static native NSString LabelNumberValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLabelColorKey", optional=true)
    protected static native NSString LabelColorValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLocalizedLabelKey", optional=true)
    protected static native NSString LocalizedLabelValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLEffectiveIconKey", optional=true)
    protected static native NSString EffectiveIconValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLCustomIconKey", optional=true)
    protected static native NSString CustomIconValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceIdentifierKey", optional=true)
    protected static native NSString FileResourceIdentifierValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIdentifierKey", optional=true)
    protected static native NSString VolumeIdentifierValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLPreferredIOBlockSizeKey", optional=true)
    protected static native NSString PreferredIOBlockSizeValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsReadableKey", optional=true)
    protected static native NSString IsReadableValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsWritableKey", optional=true)
    protected static native NSString IsWritableValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsExecutableKey", optional=true)
    protected static native NSString IsExecutableValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileSecurityKey", optional=true)
    protected static native NSString FileSecurityValue();
    /**
     * @since Available in iOS 5.1 and later.
     */
    @GlobalValue(symbol="NSURLIsExcludedFromBackupKey", optional=true)
    protected static native NSString IsExcludedFromBackupValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSURLPathKey", optional=true)
    protected static native NSString PathValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsMountTriggerKey", optional=true)
    protected static native NSString IsMountTriggerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSURLGenerationIdentifierKey", optional=true)
    protected static native NSString GenerationIdentifierValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSURLDocumentIdentifierKey", optional=true)
    protected static native NSString DocumentIdentifierValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSURLAddedToDirectoryDateKey", optional=true)
    protected static native NSString AddedToDirectoryDateValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeKey", optional=true)
    protected static native NSString FileResourceTypeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSURLThumbnailDictionaryKey", optional=true)
    protected static native NSString ThumbnailDictionaryValue();
    /*</methods>*/
}
