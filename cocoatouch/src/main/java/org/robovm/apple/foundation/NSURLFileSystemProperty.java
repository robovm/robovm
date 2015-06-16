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
@Marshaler(/*<name>*/NSURLFileSystemProperty/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLFileSystemProperty/*</name>*/ 
    extends /*<extends>*/NSURLProperty/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSURLFileSystemProperty/*</name>*/.class); }

    /*<marshalers>*/
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
            for (int i = 0; i < o.size(); i++) {
                list.add(NSURLFileSystemProperty.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSURLFileSystemProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSURLFileSystemProperty o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty Name = new NSURLFileSystemProperty("Name");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty LocalizedName = new NSURLFileSystemProperty("LocalizedName");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty IsRegularFile = new NSURLFileSystemProperty("IsRegularFile");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty IsDirectory = new NSURLFileSystemProperty("IsDirectory");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty IsSymbolicLink = new NSURLFileSystemProperty("IsSymbolicLink");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty IsVolume = new NSURLFileSystemProperty("IsVolume");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty IsPackage = new NSURLFileSystemProperty("IsPackage");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty IsSystemImmutable = new NSURLFileSystemProperty("IsSystemImmutable");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty IsUserImmutable = new NSURLFileSystemProperty("IsUserImmutable");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty IsHidden = new NSURLFileSystemProperty("IsHidden");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty HasHiddenExtension = new NSURLFileSystemProperty("HasHiddenExtension");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty CreationDate = new NSURLFileSystemProperty("CreationDate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty ContentAccessDate = new NSURLFileSystemProperty("ContentAccessDate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty ContentModificationDate = new NSURLFileSystemProperty("ContentModificationDate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty AttributeModificationDate = new NSURLFileSystemProperty("AttributeModificationDate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty LinkCount = new NSURLFileSystemProperty("LinkCount");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty ParentDirectoryURL = new NSURLFileSystemProperty("ParentDirectoryURL");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty VolumeURL = new NSURLFileSystemProperty("VolumeURL");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty TypeIdentifier = new NSURLFileSystemProperty("TypeIdentifier");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty LocalizedTypeDescription = new NSURLFileSystemProperty("LocalizedTypeDescription");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty LabelNumber = new NSURLFileSystemProperty("LabelNumber");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty LabelColor = new NSURLFileSystemProperty("LabelColor");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty LocalizedLabel = new NSURLFileSystemProperty("LocalizedLabel");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty EffectiveIcon = new NSURLFileSystemProperty("EffectiveIcon");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLFileSystemProperty CustomIcon = new NSURLFileSystemProperty("CustomIcon");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileSystemProperty FileResourceIdentifier = new NSURLFileSystemProperty("FileResourceIdentifier");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileSystemProperty VolumeIdentifier = new NSURLFileSystemProperty("VolumeIdentifier");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileSystemProperty PreferredIOBlockSize = new NSURLFileSystemProperty("PreferredIOBlockSize");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileSystemProperty IsReadable = new NSURLFileSystemProperty("IsReadable");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileSystemProperty IsWritable = new NSURLFileSystemProperty("IsWritable");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileSystemProperty IsExecutable = new NSURLFileSystemProperty("IsExecutable");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileSystemProperty FileSecurity = new NSURLFileSystemProperty("FileSecurity");
    /**
     * @since Available in iOS 5.1 and later.
     */
    public static final NSURLFileSystemProperty IsExcludedFromBackup = new NSURLFileSystemProperty("IsExcludedFromBackup");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSURLFileSystemProperty Path = new NSURLFileSystemProperty("Path");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileSystemProperty IsMountTrigger = new NSURLFileSystemProperty("IsMountTrigger");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSURLFileSystemProperty GenerationIdentifier = new NSURLFileSystemProperty("GenerationIdentifier");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSURLFileSystemProperty DocumentIdentifier = new NSURLFileSystemProperty("DocumentIdentifier");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSURLFileSystemProperty AddedToDirectoryDate = new NSURLFileSystemProperty("AddedToDirectoryDate");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLFileSystemProperty FileResourceType = new NSURLFileSystemProperty("FileResourceType");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final NSURLFileSystemProperty ThumbnailDictionary = new NSURLFileSystemProperty("ThumbnailDictionary");
    /*</constants>*/
    
    private static /*<name>*/NSURLFileSystemProperty/*</name>*/[] values = new /*<name>*/NSURLFileSystemProperty/*</name>*/[] {/*<value_list>*/Name, LocalizedName, IsRegularFile, IsDirectory, IsSymbolicLink, IsVolume, IsPackage, IsSystemImmutable, IsUserImmutable, IsHidden, HasHiddenExtension, CreationDate, ContentAccessDate, ContentModificationDate, AttributeModificationDate, LinkCount, ParentDirectoryURL, VolumeURL, TypeIdentifier, LocalizedTypeDescription, LabelNumber, LabelColor, LocalizedLabel, EffectiveIcon, CustomIcon, FileResourceIdentifier, VolumeIdentifier, PreferredIOBlockSize, IsReadable, IsWritable, IsExecutable, FileSecurity, IsExcludedFromBackup, Path, IsMountTrigger, GenerationIdentifier, DocumentIdentifier, AddedToDirectoryDate, FileResourceType, ThumbnailDictionary/*</value_list>*/};
    
    /*<name>*/NSURLFileSystemProperty/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSURLFileSystemProperty/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSURLFileSystemProperty/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSURLFileSystemProperty/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLNameKey", optional=true)
        public static native NSString Name();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLLocalizedNameKey", optional=true)
        public static native NSString LocalizedName();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLIsRegularFileKey", optional=true)
        public static native NSString IsRegularFile();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLIsDirectoryKey", optional=true)
        public static native NSString IsDirectory();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLIsSymbolicLinkKey", optional=true)
        public static native NSString IsSymbolicLink();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLIsVolumeKey", optional=true)
        public static native NSString IsVolume();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLIsPackageKey", optional=true)
        public static native NSString IsPackage();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLIsSystemImmutableKey", optional=true)
        public static native NSString IsSystemImmutable();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLIsUserImmutableKey", optional=true)
        public static native NSString IsUserImmutable();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLIsHiddenKey", optional=true)
        public static native NSString IsHidden();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLHasHiddenExtensionKey", optional=true)
        public static native NSString HasHiddenExtension();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLCreationDateKey", optional=true)
        public static native NSString CreationDate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLContentAccessDateKey", optional=true)
        public static native NSString ContentAccessDate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLContentModificationDateKey", optional=true)
        public static native NSString ContentModificationDate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLAttributeModificationDateKey", optional=true)
        public static native NSString AttributeModificationDate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLLinkCountKey", optional=true)
        public static native NSString LinkCount();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLParentDirectoryURLKey", optional=true)
        public static native NSString ParentDirectoryURL();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeURLKey", optional=true)
        public static native NSString VolumeURL();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLTypeIdentifierKey", optional=true)
        public static native NSString TypeIdentifier();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLLocalizedTypeDescriptionKey", optional=true)
        public static native NSString LocalizedTypeDescription();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLLabelNumberKey", optional=true)
        public static native NSString LabelNumber();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLLabelColorKey", optional=true)
        public static native NSString LabelColor();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLLocalizedLabelKey", optional=true)
        public static native NSString LocalizedLabel();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLEffectiveIconKey", optional=true)
        public static native NSString EffectiveIcon();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLCustomIconKey", optional=true)
        public static native NSString CustomIcon();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLFileResourceIdentifierKey", optional=true)
        public static native NSString FileResourceIdentifier();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeIdentifierKey", optional=true)
        public static native NSString VolumeIdentifier();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLPreferredIOBlockSizeKey", optional=true)
        public static native NSString PreferredIOBlockSize();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLIsReadableKey", optional=true)
        public static native NSString IsReadable();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLIsWritableKey", optional=true)
        public static native NSString IsWritable();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLIsExecutableKey", optional=true)
        public static native NSString IsExecutable();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLFileSecurityKey", optional=true)
        public static native NSString FileSecurity();
        /**
         * @since Available in iOS 5.1 and later.
         */
        @GlobalValue(symbol="NSURLIsExcludedFromBackupKey", optional=true)
        public static native NSString IsExcludedFromBackup();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="NSURLPathKey", optional=true)
        public static native NSString Path();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLIsMountTriggerKey", optional=true)
        public static native NSString IsMountTrigger();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="NSURLGenerationIdentifierKey", optional=true)
        public static native NSString GenerationIdentifier();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="NSURLDocumentIdentifierKey", optional=true)
        public static native NSString DocumentIdentifier();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="NSURLAddedToDirectoryDateKey", optional=true)
        public static native NSString AddedToDirectoryDate();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLFileResourceTypeKey", optional=true)
        public static native NSString FileResourceType();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="NSURLThumbnailDictionaryKey", optional=true)
        public static native NSString ThumbnailDictionary();
        /*</values>*/
    }
}
