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
    public static native CFBundle getBundleWithIdentifier(String bundleID);
    @Bridge(symbol="CFBundleGetAllBundles", optional=true)
    public static native CFArray getAllBundles();
    @Bridge(symbol="CFBundleGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFBundleCreate", optional=true)
    public static native CFBundle create(CFAllocator allocator, CFURL bundleURL);
    @Bridge(symbol="CFBundleCreateBundlesFromDirectory", optional=true)
    public static native CFArray createBundlesFromDirectory(CFAllocator allocator, CFURL directoryURL, String bundleType);
    @Bridge(symbol="CFBundleCopyBundleURL", optional=true)
    public native CFURL copyBundleURL();
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
    public native CFURL copySupportFilesDirectoryURL();
    @Bridge(symbol="CFBundleCopyResourcesDirectoryURL", optional=true)
    public native CFURL copyResourcesDirectoryURL();
    @Bridge(symbol="CFBundleCopyPrivateFrameworksURL", optional=true)
    public native CFURL copyPrivateFrameworksURL();
    @Bridge(symbol="CFBundleCopySharedFrameworksURL", optional=true)
    public native CFURL copySharedFrameworksURL();
    @Bridge(symbol="CFBundleCopySharedSupportURL", optional=true)
    public native CFURL copySharedSupportURL();
    @Bridge(symbol="CFBundleCopyBuiltInPlugInsURL", optional=true)
    public native CFURL copyBuiltInPlugInsURL();
    @Bridge(symbol="CFBundleCopyInfoDictionaryInDirectory", optional=true)
    public static native CFDictionary copyInfoDictionaryInDirectory(CFURL bundleURL);
    @Bridge(symbol="CFBundleGetPackageInfoInDirectory", optional=true)
    public static native boolean getPackageInfoInDirectory(CFURL url, IntPtr packageType, IntPtr packageCreator);
    @Bridge(symbol="CFBundleCopyResourceURL", optional=true)
    public native CFURL copyResourceURL(String resourceName, String resourceType, String subDirName);
    @Bridge(symbol="CFBundleCopyResourceURLsOfType", optional=true)
    public native CFArray copyResourceURLsOfType(String resourceType, String subDirName);
    @Bridge(symbol="CFBundleCopyLocalizedString", optional=true)
    public native String copyLocalizedString(String key, String value, String tableName);
    @Bridge(symbol="CFBundleCopyResourceURLInDirectory", optional=true)
    public static native CFURL copyResourceURLInDirectory(CFURL bundleURL, String resourceName, String resourceType, String subDirName);
    @Bridge(symbol="CFBundleCopyResourceURLsOfTypeInDirectory", optional=true)
    public static native CFArray copyResourceURLsOfTypeInDirectory(CFURL bundleURL, String resourceType, String subDirName);
    @Bridge(symbol="CFBundleCopyBundleLocalizations", optional=true)
    public native CFArray copyBundleLocalizations();
    @Bridge(symbol="CFBundleCopyPreferredLocalizationsFromArray", optional=true)
    public static native CFArray copyPreferredLocalizationsFromArray(CFArray locArray);
    @Bridge(symbol="CFBundleCopyLocalizationsForPreferences", optional=true)
    public static native CFArray copyLocalizationsForPreferences(CFArray locArray, CFArray prefArray);
    @Bridge(symbol="CFBundleCopyResourceURLForLocalization", optional=true)
    public native CFURL copyResourceURLForLocalization(String resourceName, String resourceType, String subDirName, String localizationName);
    @Bridge(symbol="CFBundleCopyResourceURLsOfTypeForLocalization", optional=true)
    public native CFArray copyResourceURLsOfTypeForLocalization(String resourceType, String subDirName, String localizationName);
    @Bridge(symbol="CFBundleCopyInfoDictionaryForURL", optional=true)
    public static native CFDictionary copyInfoDictionaryForURL(CFURL url);
    @Bridge(symbol="CFBundleCopyLocalizationsForURL", optional=true)
    public static native CFArray copyLocalizationsForURL(CFURL url);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFBundleCopyExecutableArchitecturesForURL", optional=true)
    public static native CFArray copyExecutableArchitecturesForURL(CFURL url);
    @Bridge(symbol="CFBundleCopyExecutableURL", optional=true)
    public native CFURL copyExecutableURL();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFBundleCopyExecutableArchitectures", optional=true)
    public native CFArray copyExecutableArchitectures();
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
    public native CFURL copyAuxiliaryExecutableURL(String executableName);
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
