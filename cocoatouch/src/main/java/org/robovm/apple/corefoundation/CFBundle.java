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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFBundle/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<?> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            return o.toList(CFBundle.class);
        }
        @MarshalsPointer
        public static long toNative(List<? extends CFType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray o = null;
            if (l instanceof CFArray) {
                o = (CFArray) l;
            } else {
                o = CFArray.create((List<? extends CFType>) l);
            }
            return CFType.Marshaler.toNative(o, flags);
        }
    }
    
    /*<ptr>*/public static class CFBundlePtr extends Ptr<CFBundle, CFBundlePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFBundle.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFBundle() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFBundleGetMainBundle", optional=true)
    public static native CFBundle getMainBundle();
    @Bridge(symbol="CFBundleGetBundleWithIdentifier", optional=true)
    public static native CFBundle getBundle(String bundleID);
    @Bridge(symbol="CFBundleGetAllBundles", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFBundle.AsListMarshaler.class) List<CFBundle> getAllBundles();
    @Bridge(symbol="CFBundleGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFBundleCreate", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFBundle create(CFAllocator allocator, CFURL bundleURL);
    @Bridge(symbol="CFBundleCreateBundlesFromDirectory", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFBundle.AsListMarshaler.class) List<CFBundle> createBundlesFromDirectory(CFAllocator allocator, CFURL directoryURL, String bundleType);
    @Bridge(symbol="CFBundleCopyBundleURL", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFURL getBundleURL();
    @Bridge(symbol="CFBundleGetValueForInfoDictionaryKey", optional=true)
    public native CFType getValueForInfoDictionaryKey(String key);
    @Bridge(symbol="CFBundleGetInfoDictionary", optional=true)
    public native CFDictionary getInfoDictionary();
    @Bridge(symbol="CFBundleGetLocalInfoDictionary", optional=true)
    public native CFDictionary getLocalInfoDictionary();
    @Bridge(symbol="CFBundleGetPackageInfo", optional=true)
    public native void getPackageInfo(IntPtr packageType, IntPtr packageCreator);
    @Bridge(symbol="CFBundleGetIdentifier", optional=true)
    public native String getIdentifier();
    @Bridge(symbol="CFBundleGetVersionNumber", optional=true)
    public native int getVersionNumber();
    @Bridge(symbol="CFBundleGetDevelopmentRegion", optional=true)
    public native String getDevelopmentRegion();
    @Bridge(symbol="CFBundleCopySupportFilesDirectoryURL", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFURL getSupportFilesDirectoryURL();
    @Bridge(symbol="CFBundleCopyResourcesDirectoryURL", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFURL getResourcesDirectoryURL();
    @Bridge(symbol="CFBundleCopyPrivateFrameworksURL", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFURL getPrivateFrameworksURL();
    @Bridge(symbol="CFBundleCopySharedFrameworksURL", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFURL getSharedFrameworksURL();
    @Bridge(symbol="CFBundleCopySharedSupportURL", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFURL getSharedSupportURL();
    @Bridge(symbol="CFBundleCopyBuiltInPlugInsURL", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFURL getBuiltInPlugInsURL();
    @Bridge(symbol="CFBundleCopyInfoDictionaryInDirectory", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFDictionary getInfoDictionaryInDirectory(CFURL bundleURL);
    @Bridge(symbol="CFBundleGetPackageInfoInDirectory", optional=true)
    public static native boolean getPackageInfoInDirectory(CFURL url, IntPtr packageType, IntPtr packageCreator);
    @Bridge(symbol="CFBundleCopyResourceURL", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFURL getResourceURL(String resourceName, String resourceType, String subDirName);
    @Bridge(symbol="CFBundleCopyResourceURLsOfType", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFArray getResourceURLsOfType(String resourceType, String subDirName);
    @Bridge(symbol="CFBundleCopyLocalizedString", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getLocalizedString(String key, String value, String tableName);
    @Bridge(symbol="CFBundleCopyResourceURLInDirectory", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFURL getResourceURLInDirectory(CFURL bundleURL, String resourceName, String resourceType, String subDirName);
    @Bridge(symbol="CFBundleCopyResourceURLsOfTypeInDirectory", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFArray getResourceURLsOfTypeInDirectory(CFURL bundleURL, String resourceType, String subDirName);
    @Bridge(symbol="CFBundleCopyBundleLocalizations", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFArray getBundleLocalizations();
    @Bridge(symbol="CFBundleCopyPreferredLocalizationsFromArray", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFArray getPreferredLocalizationsFromArray(CFArray locArray);
    @Bridge(symbol="CFBundleCopyLocalizationsForPreferences", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFArray getLocalizationsForPreferences(CFArray locArray, CFArray prefArray);
    @Bridge(symbol="CFBundleCopyResourceURLForLocalization", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFURL getResourceURLForLocalization(String resourceName, String resourceType, String subDirName, String localizationName);
    @Bridge(symbol="CFBundleCopyResourceURLsOfTypeForLocalization", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFArray getResourceURLsOfTypeForLocalization(String resourceType, String subDirName, String localizationName);
    @Bridge(symbol="CFBundleCopyInfoDictionaryForURL", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFDictionary getInfoDictionaryForURL(CFURL url);
    @Bridge(symbol="CFBundleCopyLocalizationsForURL", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFArray getLocalizationsForURL(CFURL url);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFBundleCopyExecutableArchitecturesForURL", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFArray getExecutableArchitecturesForURL(CFURL url);
    @Bridge(symbol="CFBundleCopyExecutableURL", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFURL getExecutableURL();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFBundleCopyExecutableArchitectures", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFArray getExecutableArchitectures();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFBundlePreflightExecutable", optional=true)
    public native boolean preflightExecutable(CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFBundleLoadExecutableAndReturnError", optional=true)
    public native boolean loadExecutableAndReturnError(CFError.CFErrorPtr error);
    @Bridge(symbol="CFBundleLoadExecutable", optional=true)
    public native boolean loadExecutable();
    @Bridge(symbol="CFBundleIsExecutableLoaded", optional=true)
    public native boolean isExecutableLoaded();
    @Bridge(symbol="CFBundleUnloadExecutable", optional=true)
    public native void unloadExecutable();
    @Bridge(symbol="CFBundleGetFunctionPointerForName", optional=true)
    public native VoidPtr getFunctionPointerForName(String functionName);
    @Bridge(symbol="CFBundleGetFunctionPointersForNames", optional=true)
    public native void getFunctionPointersForNames(CFArray functionNames, VoidPtr.VoidPtrPtr ftbl);
    @Bridge(symbol="CFBundleGetDataPointerForName", optional=true)
    public native VoidPtr getDataPointerForName(String symbolName);
    @Bridge(symbol="CFBundleGetDataPointersForNames", optional=true)
    public native void getDataPointersForNames(CFArray symbolNames, VoidPtr.VoidPtrPtr stbl);
    @Bridge(symbol="CFBundleCopyAuxiliaryExecutableURL", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFURL getAuxiliaryExecutableURL(String executableName);
    @Bridge(symbol="CFBundleGetPlugIn", optional=true)
    public native CFPlugIn getPlugIn();
    @Bridge(symbol="CFBundleOpenBundleResourceMap", optional=true)
    public native short openBundleResourceMap();
    @Bridge(symbol="CFBundleOpenBundleResourceFiles", optional=true)
    public native int openBundleResourceFiles(ShortPtr refNum, ShortPtr localizedRefNum);
    @Bridge(symbol="CFBundleCloseBundleResourceMap", optional=true)
    public native void closeBundleResourceMap(short refNum);
    /*</methods>*/
}
