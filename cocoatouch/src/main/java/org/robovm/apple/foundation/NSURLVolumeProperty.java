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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLVolumeProperty/*</name>*/ 
    extends /*<extends>*/NSURLProperty/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSURLVolumeProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty LocalizedFormatDescription = new NSURLVolumeProperty("LocalizedFormatDescriptionValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty TotalCapacity = new NSURLVolumeProperty("TotalCapacityValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty AvailableCapacity = new NSURLVolumeProperty("AvailableCapacityValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty ResourceCount = new NSURLVolumeProperty("ResourceCountValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty SupportsPersistentIDs = new NSURLVolumeProperty("SupportsPersistentIDsValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty SupportsSymbolicLinks = new NSURLVolumeProperty("SupportsSymbolicLinksValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty SupportsHardLinks = new NSURLVolumeProperty("SupportsHardLinksValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty SupportsJournaling = new NSURLVolumeProperty("SupportsJournalingValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty IsJournaling = new NSURLVolumeProperty("IsJournalingValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty SupportsSparseFiles = new NSURLVolumeProperty("SupportsSparseFilesValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty SupportsZeroRuns = new NSURLVolumeProperty("SupportsZeroRunsValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty SupportsCaseSensitiveNames = new NSURLVolumeProperty("SupportsCaseSensitiveNamesValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSURLVolumeProperty SupportsCasePreservedNames = new NSURLVolumeProperty("SupportsCasePreservedNamesValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty SupportsRootDirectoryDates = new NSURLVolumeProperty("SupportsRootDirectoryDatesValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty SupportsVolumeSizes = new NSURLVolumeProperty("SupportsVolumeSizesValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty SupportsRenaming = new NSURLVolumeProperty("SupportsRenamingValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty SupportsAdvisoryFileLocking = new NSURLVolumeProperty("SupportsAdvisoryFileLockingValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty SupportsExtendedSecurity = new NSURLVolumeProperty("SupportsExtendedSecurityValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty IsBrowsable = new NSURLVolumeProperty("IsBrowsableValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty MaximumFileSize = new NSURLVolumeProperty("MaximumFileSizeValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty IsEjectable = new NSURLVolumeProperty("IsEjectableValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty IsRemovable = new NSURLVolumeProperty("IsRemovableValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty IsInternal = new NSURLVolumeProperty("IsInternalValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty IsAutomounted = new NSURLVolumeProperty("IsAutomountedValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty IsLocal = new NSURLVolumeProperty("IsLocalValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty IsReadOnly = new NSURLVolumeProperty("IsReadOnlyValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty CreationDate = new NSURLVolumeProperty("CreationDateValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty URLForRemounting = new NSURLVolumeProperty("URLForRemountingValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty UUIDString = new NSURLVolumeProperty("UUIDStringValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty Name = new NSURLVolumeProperty("NameValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSURLVolumeProperty LocalizedName = new NSURLVolumeProperty("LocalizedNameValue");
    
    private static NSURLVolumeProperty[] values = new NSURLVolumeProperty[]{LocalizedFormatDescription, TotalCapacity, AvailableCapacity, ResourceCount, SupportsPersistentIDs, SupportsSymbolicLinks, 
        SupportsHardLinks, SupportsJournaling, IsJournaling, SupportsSparseFiles, SupportsZeroRuns, SupportsCaseSensitiveNames, SupportsCasePreservedNames, SupportsRootDirectoryDates, SupportsVolumeSizes,
        SupportsRenaming, SupportsAdvisoryFileLocking, SupportsExtendedSecurity, IsBrowsable, MaximumFileSize, IsEjectable, IsRemovable, IsInternal, IsAutomounted, IsLocal, IsReadOnly, CreationDate, 
        URLForRemounting, UUIDString, Name, LocalizedName};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSURLVolumeProperty(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSURLVolumeProperty valueOf(NSString value) {
        for (NSURLVolumeProperty v : values) {
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
    @GlobalValue(symbol="NSURLVolumeLocalizedFormatDescriptionKey", optional=true)
    protected static native NSString LocalizedFormatDescriptionValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeTotalCapacityKey", optional=true)
    protected static native NSString TotalCapacityValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeAvailableCapacityKey", optional=true)
    protected static native NSString AvailableCapacityValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeResourceCountKey", optional=true)
    protected static native NSString ResourceCountValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsPersistentIDsKey", optional=true)
    protected static native NSString SupportsPersistentIDsValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsSymbolicLinksKey", optional=true)
    protected static native NSString SupportsSymbolicLinksValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsHardLinksKey", optional=true)
    protected static native NSString SupportsHardLinksValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsJournalingKey", optional=true)
    protected static native NSString SupportsJournalingValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsJournalingKey", optional=true)
    protected static native NSString IsJournalingValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsSparseFilesKey", optional=true)
    protected static native NSString SupportsSparseFilesValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsZeroRunsKey", optional=true)
    protected static native NSString SupportsZeroRunsValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsCaseSensitiveNamesKey", optional=true)
    protected static native NSString SupportsCaseSensitiveNamesValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsCasePreservedNamesKey", optional=true)
    protected static native NSString SupportsCasePreservedNamesValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsRootDirectoryDatesKey", optional=true)
    protected static native NSString SupportsRootDirectoryDatesValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsVolumeSizesKey", optional=true)
    protected static native NSString SupportsVolumeSizesValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsRenamingKey", optional=true)
    protected static native NSString SupportsRenamingValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsAdvisoryFileLockingKey", optional=true)
    protected static native NSString SupportsAdvisoryFileLockingValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsExtendedSecurityKey", optional=true)
    protected static native NSString SupportsExtendedSecurityValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsBrowsableKey", optional=true)
    protected static native NSString IsBrowsableValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeMaximumFileSizeKey", optional=true)
    protected static native NSString MaximumFileSizeValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsEjectableKey", optional=true)
    protected static native NSString IsEjectableValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsRemovableKey", optional=true)
    protected static native NSString IsRemovableValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsInternalKey", optional=true)
    protected static native NSString IsInternalValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsAutomountedKey", optional=true)
    protected static native NSString IsAutomountedValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsLocalKey", optional=true)
    protected static native NSString IsLocalValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsReadOnlyKey", optional=true)
    protected static native NSString IsReadOnlyValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeCreationDateKey", optional=true)
    protected static native NSString CreationDateValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeURLForRemountingKey", optional=true)
    protected static native NSString URLForRemountingValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeUUIDStringKey", optional=true)
    protected static native NSString UUIDStringValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeNameKey", optional=true)
    protected static native NSString NameValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeLocalizedNameKey", optional=true)
    protected static native NSString LocalizedNameValue();
    /*</methods>*/
}
