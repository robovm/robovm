/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
@Marshaler(/*<name>*/CFURLFileSystemProperty/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFURLFileSystemProperty/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CFURLFileSystemProperty/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFURLFileSystemProperty toObject(Class<CFURLFileSystemProperty> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFURLFileSystemProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFURLFileSystemProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFURLFileSystemProperty> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFURLFileSystemProperty> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CFURLFileSystemProperty.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFURLFileSystemProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFURLFileSystemProperty i : l) {
                array.add(i.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty Name = new CFURLFileSystemProperty("Name");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty LocalizedName = new CFURLFileSystemProperty("LocalizedName");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty IsRegularFile = new CFURLFileSystemProperty("IsRegularFile");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty IsDirectory = new CFURLFileSystemProperty("IsDirectory");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty IsSymbolicLink = new CFURLFileSystemProperty("IsSymbolicLink");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty IsVolume = new CFURLFileSystemProperty("IsVolume");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty IsPackage = new CFURLFileSystemProperty("IsPackage");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty IsSystemImmutable = new CFURLFileSystemProperty("IsSystemImmutable");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty IsUserImmutable = new CFURLFileSystemProperty("IsUserImmutable");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty IsHidden = new CFURLFileSystemProperty("IsHidden");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty HasHiddenExtension = new CFURLFileSystemProperty("HasHiddenExtension");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty CreationDate = new CFURLFileSystemProperty("CreationDate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty ContentAccessDate = new CFURLFileSystemProperty("ContentAccessDate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty ContentModificationDate = new CFURLFileSystemProperty("ContentModificationDate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty AttributeModificationDate = new CFURLFileSystemProperty("AttributeModificationDate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty LinkCount = new CFURLFileSystemProperty("LinkCount");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty ParentDirectoryURL = new CFURLFileSystemProperty("ParentDirectoryURL");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty TypeIdentifier = new CFURLFileSystemProperty("TypeIdentifier");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty LocalizedTypeDescription = new CFURLFileSystemProperty("LocalizedTypeDescription");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty LabelNumber = new CFURLFileSystemProperty("LabelNumber");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty LabelColor = new CFURLFileSystemProperty("LabelColor");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty LocalizedLabel = new CFURLFileSystemProperty("LocalizedLabel");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty EffectiveIcon = new CFURLFileSystemProperty("EffectiveIcon");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLFileSystemProperty CustomIcon = new CFURLFileSystemProperty("CustomIcon");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLFileSystemProperty FileResourceIdentifier = new CFURLFileSystemProperty("FileResourceIdentifier");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLFileSystemProperty PreferredIOBlockSize = new CFURLFileSystemProperty("PreferredIOBlockSize");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLFileSystemProperty IsReadable = new CFURLFileSystemProperty("IsReadable");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLFileSystemProperty IsWritable = new CFURLFileSystemProperty("IsWritable");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLFileSystemProperty IsExecutable = new CFURLFileSystemProperty("IsExecutable");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLFileSystemProperty FileSecurity = new CFURLFileSystemProperty("FileSecurity");
    /**
     * @since Available in iOS 5.1 and later.
     */
    public static final CFURLFileSystemProperty IsExcludedFromBackup = new CFURLFileSystemProperty("IsExcludedFromBackup");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CFURLFileSystemProperty Path = new CFURLFileSystemProperty("Path");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CFURLFileSystemProperty GenerationIdentifier = new CFURLFileSystemProperty("GenerationIdentifier");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CFURLFileSystemProperty DocumentIdentifier = new CFURLFileSystemProperty("DocumentIdentifier");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CFURLFileSystemProperty AddedToDirectoryDate = new CFURLFileSystemProperty("AddedToDirectoryDate");
    /*</constants>*/
    
    private static /*<name>*/CFURLFileSystemProperty/*</name>*/[] values = new /*<name>*/CFURLFileSystemProperty/*</name>*/[] {/*<value_list>*/Name, LocalizedName, IsRegularFile, IsDirectory, IsSymbolicLink, IsVolume, IsPackage, IsSystemImmutable, IsUserImmutable, IsHidden, HasHiddenExtension, CreationDate, ContentAccessDate, ContentModificationDate, AttributeModificationDate, LinkCount, ParentDirectoryURL, TypeIdentifier, LocalizedTypeDescription, LabelNumber, LabelColor, LocalizedLabel, EffectiveIcon, CustomIcon, FileResourceIdentifier, PreferredIOBlockSize, IsReadable, IsWritable, IsExecutable, FileSecurity, IsExcludedFromBackup, Path, GenerationIdentifier, DocumentIdentifier, AddedToDirectoryDate/*</value_list>*/};
    
    /*<name>*/CFURLFileSystemProperty/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CFURLFileSystemProperty/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CFURLFileSystemProperty/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFURLFileSystemProperty/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLNameKey", optional=true)
        public static native CFString Name();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLLocalizedNameKey", optional=true)
        public static native CFString LocalizedName();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLIsRegularFileKey", optional=true)
        public static native CFString IsRegularFile();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLIsDirectoryKey", optional=true)
        public static native CFString IsDirectory();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLIsSymbolicLinkKey", optional=true)
        public static native CFString IsSymbolicLink();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLIsVolumeKey", optional=true)
        public static native CFString IsVolume();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLIsPackageKey", optional=true)
        public static native CFString IsPackage();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLIsSystemImmutableKey", optional=true)
        public static native CFString IsSystemImmutable();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLIsUserImmutableKey", optional=true)
        public static native CFString IsUserImmutable();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLIsHiddenKey", optional=true)
        public static native CFString IsHidden();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLHasHiddenExtensionKey", optional=true)
        public static native CFString HasHiddenExtension();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLCreationDateKey", optional=true)
        public static native CFString CreationDate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLContentAccessDateKey", optional=true)
        public static native CFString ContentAccessDate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLContentModificationDateKey", optional=true)
        public static native CFString ContentModificationDate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLAttributeModificationDateKey", optional=true)
        public static native CFString AttributeModificationDate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLLinkCountKey", optional=true)
        public static native CFString LinkCount();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLParentDirectoryURLKey", optional=true)
        public static native CFString ParentDirectoryURL();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLTypeIdentifierKey", optional=true)
        public static native CFString TypeIdentifier();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLLocalizedTypeDescriptionKey", optional=true)
        public static native CFString LocalizedTypeDescription();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLLabelNumberKey", optional=true)
        public static native CFString LabelNumber();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLLabelColorKey", optional=true)
        public static native CFString LabelColor();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLLocalizedLabelKey", optional=true)
        public static native CFString LocalizedLabel();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLEffectiveIconKey", optional=true)
        public static native CFString EffectiveIcon();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLCustomIconKey", optional=true)
        public static native CFString CustomIcon();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLFileResourceIdentifierKey", optional=true)
        public static native CFString FileResourceIdentifier();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLPreferredIOBlockSizeKey", optional=true)
        public static native CFString PreferredIOBlockSize();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLIsReadableKey", optional=true)
        public static native CFString IsReadable();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLIsWritableKey", optional=true)
        public static native CFString IsWritable();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLIsExecutableKey", optional=true)
        public static native CFString IsExecutable();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLFileSecurityKey", optional=true)
        public static native CFString FileSecurity();
        /**
         * @since Available in iOS 5.1 and later.
         */
        @GlobalValue(symbol="kCFURLIsExcludedFromBackupKey", optional=true)
        public static native CFString IsExcludedFromBackup();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCFURLPathKey", optional=true)
        public static native CFString Path();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCFURLGenerationIdentifierKey", optional=true)
        public static native CFString GenerationIdentifier();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCFURLDocumentIdentifierKey", optional=true)
        public static native CFString DocumentIdentifier();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCFURLAddedToDirectoryDateKey", optional=true)
        public static native CFString AddedToDirectoryDate();
        /*</values>*/
    }
}
