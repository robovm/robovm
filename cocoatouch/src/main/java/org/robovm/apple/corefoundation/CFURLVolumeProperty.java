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
@Marshaler(/*<name>*/CFURLVolumeProperty/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFURLVolumeProperty/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CFURLVolumeProperty/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFURLVolumeProperty toObject(Class<CFURLVolumeProperty> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFURLVolumeProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFURLVolumeProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFURLVolumeProperty> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFURLVolumeProperty> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CFURLVolumeProperty.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFURLVolumeProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFURLVolumeProperty i : l) {
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
    public static final CFURLVolumeProperty URL = new CFURLVolumeProperty("URL");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty Identifier = new CFURLVolumeProperty("Identifier");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLVolumeProperty LocalizedFormatDescription = new CFURLVolumeProperty("LocalizedFormatDescription");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLVolumeProperty TotalCapacity = new CFURLVolumeProperty("TotalCapacity");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLVolumeProperty AvailableCapacity = new CFURLVolumeProperty("AvailableCapacity");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLVolumeProperty ResourceCount = new CFURLVolumeProperty("ResourceCount");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLVolumeProperty SupportsPersistentIDs = new CFURLVolumeProperty("SupportsPersistentIDs");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLVolumeProperty SupportsSymbolicLinks = new CFURLVolumeProperty("SupportsSymbolicLinks");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLVolumeProperty SupportsHardLinks = new CFURLVolumeProperty("SupportsHardLinks");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLVolumeProperty SupportsJournaling = new CFURLVolumeProperty("SupportsJournaling");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLVolumeProperty IsJournaling = new CFURLVolumeProperty("IsJournaling");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLVolumeProperty SupportsSparseFiles = new CFURLVolumeProperty("SupportsSparseFiles");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLVolumeProperty SupportsZeroRuns = new CFURLVolumeProperty("SupportsZeroRuns");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLVolumeProperty SupportsCaseSensitiveNames = new CFURLVolumeProperty("SupportsCaseSensitiveNames");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFURLVolumeProperty SupportsCasePreservedNames = new CFURLVolumeProperty("SupportsCasePreservedNames");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty SupportsRootDirectoryDates = new CFURLVolumeProperty("SupportsRootDirectoryDates");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty SupportsVolumeSizes = new CFURLVolumeProperty("SupportsVolumeSizes");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty SupportsRenaming = new CFURLVolumeProperty("SupportsRenaming");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty SupportsAdvisoryFileLocking = new CFURLVolumeProperty("SupportsAdvisoryFileLocking");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty SupportsExtendedSecurity = new CFURLVolumeProperty("SupportsExtendedSecurity");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty IsBrowsable = new CFURLVolumeProperty("IsBrowsable");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty MaximumFileSize = new CFURLVolumeProperty("MaximumFileSize");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty IsEjectable = new CFURLVolumeProperty("IsEjectable");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty IsRemovable = new CFURLVolumeProperty("IsRemovable");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty IsInternal = new CFURLVolumeProperty("IsInternal");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty IsAutomounted = new CFURLVolumeProperty("IsAutomounted");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty IsLocal = new CFURLVolumeProperty("IsLocal");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty IsReadOnly = new CFURLVolumeProperty("IsReadOnly");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty CreationDate = new CFURLVolumeProperty("CreationDate");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty URLForRemounting = new CFURLVolumeProperty("URLForRemounting");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty UUIDString = new CFURLVolumeProperty("UUIDString");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty Name = new CFURLVolumeProperty("Name");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFURLVolumeProperty LocalizedName = new CFURLVolumeProperty("LocalizedName");
    /*</constants>*/
    
    private static /*<name>*/CFURLVolumeProperty/*</name>*/[] values = new /*<name>*/CFURLVolumeProperty/*</name>*/[] {/*<value_list>*/URL, Identifier, LocalizedFormatDescription, TotalCapacity, AvailableCapacity, ResourceCount, SupportsPersistentIDs, SupportsSymbolicLinks, SupportsHardLinks, SupportsJournaling, IsJournaling, SupportsSparseFiles, SupportsZeroRuns, SupportsCaseSensitiveNames, SupportsCasePreservedNames, SupportsRootDirectoryDates, SupportsVolumeSizes, SupportsRenaming, SupportsAdvisoryFileLocking, SupportsExtendedSecurity, IsBrowsable, MaximumFileSize, IsEjectable, IsRemovable, IsInternal, IsAutomounted, IsLocal, IsReadOnly, CreationDate, URLForRemounting, UUIDString, Name, LocalizedName/*</value_list>*/};
    
    /*<name>*/CFURLVolumeProperty/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CFURLVolumeProperty/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CFURLVolumeProperty/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFURLVolumeProperty/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeURLKey", optional=true)
        public static native CFString URL();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeIdentifierKey", optional=true)
        public static native CFString Identifier();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeLocalizedFormatDescriptionKey", optional=true)
        public static native CFString LocalizedFormatDescription();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeTotalCapacityKey", optional=true)
        public static native CFString TotalCapacity();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeAvailableCapacityKey", optional=true)
        public static native CFString AvailableCapacity();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeResourceCountKey", optional=true)
        public static native CFString ResourceCount();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeSupportsPersistentIDsKey", optional=true)
        public static native CFString SupportsPersistentIDs();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeSupportsSymbolicLinksKey", optional=true)
        public static native CFString SupportsSymbolicLinks();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeSupportsHardLinksKey", optional=true)
        public static native CFString SupportsHardLinks();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeSupportsJournalingKey", optional=true)
        public static native CFString SupportsJournaling();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeIsJournalingKey", optional=true)
        public static native CFString IsJournaling();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeSupportsSparseFilesKey", optional=true)
        public static native CFString SupportsSparseFiles();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeSupportsZeroRunsKey", optional=true)
        public static native CFString SupportsZeroRuns();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeSupportsCaseSensitiveNamesKey", optional=true)
        public static native CFString SupportsCaseSensitiveNames();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeSupportsCasePreservedNamesKey", optional=true)
        public static native CFString SupportsCasePreservedNames();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeSupportsRootDirectoryDatesKey", optional=true)
        public static native CFString SupportsRootDirectoryDates();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeSupportsVolumeSizesKey", optional=true)
        public static native CFString SupportsVolumeSizes();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeSupportsRenamingKey", optional=true)
        public static native CFString SupportsRenaming();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeSupportsAdvisoryFileLockingKey", optional=true)
        public static native CFString SupportsAdvisoryFileLocking();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeSupportsExtendedSecurityKey", optional=true)
        public static native CFString SupportsExtendedSecurity();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeIsBrowsableKey", optional=true)
        public static native CFString IsBrowsable();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeMaximumFileSizeKey", optional=true)
        public static native CFString MaximumFileSize();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeIsEjectableKey", optional=true)
        public static native CFString IsEjectable();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeIsRemovableKey", optional=true)
        public static native CFString IsRemovable();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeIsInternalKey", optional=true)
        public static native CFString IsInternal();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeIsAutomountedKey", optional=true)
        public static native CFString IsAutomounted();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeIsLocalKey", optional=true)
        public static native CFString IsLocal();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeIsReadOnlyKey", optional=true)
        public static native CFString IsReadOnly();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeCreationDateKey", optional=true)
        public static native CFString CreationDate();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeURLForRemountingKey", optional=true)
        public static native CFString URLForRemounting();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeUUIDStringKey", optional=true)
        public static native CFString UUIDString();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeNameKey", optional=true)
        public static native CFString Name();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCFURLVolumeLocalizedNameKey", optional=true)
        public static native CFString LocalizedName();
        /*</values>*/
    }
}
