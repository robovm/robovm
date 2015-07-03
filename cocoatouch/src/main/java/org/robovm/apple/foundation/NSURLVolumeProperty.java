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
@Marshaler(/*<name>*/NSURLVolumeProperty/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLVolumeProperty/*</name>*/ 
    extends /*<extends>*/NSURLProperty/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSURLVolumeProperty/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSURLVolumeProperty toObject(Class<NSURLVolumeProperty> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSURLVolumeProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSURLVolumeProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSURLVolumeProperty> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSURLVolumeProperty> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSURLVolumeProperty.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSURLVolumeProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSURLVolumeProperty o : l) {
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
    public static final NSURLVolumeProperty LocalizedFormatDescription = new NSURLVolumeProperty("LocalizedFormatDescription");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty TotalCapacity = new NSURLVolumeProperty("TotalCapacity");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty AvailableCapacity = new NSURLVolumeProperty("AvailableCapacity");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty ResourceCount = new NSURLVolumeProperty("ResourceCount");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty SupportsPersistentIDs = new NSURLVolumeProperty("SupportsPersistentIDs");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty SupportsSymbolicLinks = new NSURLVolumeProperty("SupportsSymbolicLinks");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty SupportsHardLinks = new NSURLVolumeProperty("SupportsHardLinks");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty SupportsJournaling = new NSURLVolumeProperty("SupportsJournaling");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty IsJournaling = new NSURLVolumeProperty("IsJournaling");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty SupportsSparseFiles = new NSURLVolumeProperty("SupportsSparseFiles");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty SupportsZeroRuns = new NSURLVolumeProperty("SupportsZeroRuns");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty SupportsCaseSensitiveNames = new NSURLVolumeProperty("SupportsCaseSensitiveNames");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty SupportsCasePreservedNames = new NSURLVolumeProperty("SupportsCasePreservedNames");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty SupportsRootDirectoryDates = new NSURLVolumeProperty("SupportsRootDirectoryDates");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty SupportsVolumeSizes = new NSURLVolumeProperty("SupportsVolumeSizes");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty SupportsRenaming = new NSURLVolumeProperty("SupportsRenaming");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty SupportsAdvisoryFileLocking = new NSURLVolumeProperty("SupportsAdvisoryFileLocking");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty SupportsExtendedSecurity = new NSURLVolumeProperty("SupportsExtendedSecurity");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty IsBrowsable = new NSURLVolumeProperty("IsBrowsable");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty MaximumFileSize = new NSURLVolumeProperty("MaximumFileSize");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty IsEjectable = new NSURLVolumeProperty("IsEjectable");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty IsRemovable = new NSURLVolumeProperty("IsRemovable");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty IsInternal = new NSURLVolumeProperty("IsInternal");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty IsAutomounted = new NSURLVolumeProperty("IsAutomounted");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty IsLocal = new NSURLVolumeProperty("IsLocal");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty IsReadOnly = new NSURLVolumeProperty("IsReadOnly");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty CreationDate = new NSURLVolumeProperty("CreationDate");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty URLForRemounting = new NSURLVolumeProperty("URLForRemounting");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty UUIDString = new NSURLVolumeProperty("UUIDString");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty Name = new NSURLVolumeProperty("Name");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty LocalizedName = new NSURLVolumeProperty("LocalizedName");
    /*</constants>*/
    
    private static /*<name>*/NSURLVolumeProperty/*</name>*/[] values = new /*<name>*/NSURLVolumeProperty/*</name>*/[] {/*<value_list>*/LocalizedFormatDescription, TotalCapacity, AvailableCapacity, ResourceCount, SupportsPersistentIDs, SupportsSymbolicLinks, SupportsHardLinks, SupportsJournaling, IsJournaling, SupportsSparseFiles, SupportsZeroRuns, SupportsCaseSensitiveNames, SupportsCasePreservedNames, SupportsRootDirectoryDates, SupportsVolumeSizes, SupportsRenaming, SupportsAdvisoryFileLocking, SupportsExtendedSecurity, IsBrowsable, MaximumFileSize, IsEjectable, IsRemovable, IsInternal, IsAutomounted, IsLocal, IsReadOnly, CreationDate, URLForRemounting, UUIDString, Name, LocalizedName/*</value_list>*/};
    
    /*<name>*/NSURLVolumeProperty/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSURLVolumeProperty/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSURLVolumeProperty/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSURLVolumeProperty/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeLocalizedFormatDescriptionKey", optional=true)
        public static native NSString LocalizedFormatDescription();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeTotalCapacityKey", optional=true)
        public static native NSString TotalCapacity();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeAvailableCapacityKey", optional=true)
        public static native NSString AvailableCapacity();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeResourceCountKey", optional=true)
        public static native NSString ResourceCount();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeSupportsPersistentIDsKey", optional=true)
        public static native NSString SupportsPersistentIDs();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeSupportsSymbolicLinksKey", optional=true)
        public static native NSString SupportsSymbolicLinks();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeSupportsHardLinksKey", optional=true)
        public static native NSString SupportsHardLinks();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeSupportsJournalingKey", optional=true)
        public static native NSString SupportsJournaling();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeIsJournalingKey", optional=true)
        public static native NSString IsJournaling();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeSupportsSparseFilesKey", optional=true)
        public static native NSString SupportsSparseFiles();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeSupportsZeroRunsKey", optional=true)
        public static native NSString SupportsZeroRuns();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeSupportsCaseSensitiveNamesKey", optional=true)
        public static native NSString SupportsCaseSensitiveNames();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeSupportsCasePreservedNamesKey", optional=true)
        public static native NSString SupportsCasePreservedNames();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeSupportsRootDirectoryDatesKey", optional=true)
        public static native NSString SupportsRootDirectoryDates();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeSupportsVolumeSizesKey", optional=true)
        public static native NSString SupportsVolumeSizes();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeSupportsRenamingKey", optional=true)
        public static native NSString SupportsRenaming();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeSupportsAdvisoryFileLockingKey", optional=true)
        public static native NSString SupportsAdvisoryFileLocking();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeSupportsExtendedSecurityKey", optional=true)
        public static native NSString SupportsExtendedSecurity();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeIsBrowsableKey", optional=true)
        public static native NSString IsBrowsable();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeMaximumFileSizeKey", optional=true)
        public static native NSString MaximumFileSize();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeIsEjectableKey", optional=true)
        public static native NSString IsEjectable();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeIsRemovableKey", optional=true)
        public static native NSString IsRemovable();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeIsInternalKey", optional=true)
        public static native NSString IsInternal();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeIsAutomountedKey", optional=true)
        public static native NSString IsAutomounted();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeIsLocalKey", optional=true)
        public static native NSString IsLocal();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeIsReadOnlyKey", optional=true)
        public static native NSString IsReadOnly();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeCreationDateKey", optional=true)
        public static native NSString CreationDate();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeURLForRemountingKey", optional=true)
        public static native NSString URLForRemounting();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeUUIDStringKey", optional=true)
        public static native NSString UUIDString();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeNameKey", optional=true)
        public static native NSString Name();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="NSURLVolumeLocalizedNameKey", optional=true)
        public static native NSString LocalizedName();
        /*</values>*/
    }
}
