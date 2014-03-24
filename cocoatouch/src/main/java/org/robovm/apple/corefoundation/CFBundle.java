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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
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
    @GlobalValue(symbol="kCFBundleInfoDictionaryVersionKey")
    public static native CFString KeyInfoDictionaryVersion();
    @GlobalValue(symbol="kCFBundleExecutableKey")
    public static native CFString KeyExecutable();
    @GlobalValue(symbol="kCFBundleIdentifierKey")
    public static native CFString KeyIdentifier();
    @GlobalValue(symbol="kCFBundleVersionKey")
    public static native CFString KeyVersion();
    @GlobalValue(symbol="kCFBundleDevelopmentRegionKey")
    public static native CFString KeyDevelopmentRegion();
    @GlobalValue(symbol="kCFBundleNameKey")
    public static native CFString KeyName();
    @GlobalValue(symbol="kCFBundleLocalizationsKey")
    public static native CFString KeyLocalizations();
    
    @Bridge(symbol="CFBundleGetMainBundle")
    public static native CFBundle getMainBundle();
    @Bridge(symbol="CFBundleGetBundleWithIdentifier")
    public static native CFBundle getBundleWithIdentifier(CFString bundleID);
    @Bridge(symbol="CFBundleGetAllBundles")
    public static native CFArray getAllBundles();
    @Bridge(symbol="CFBundleGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFBundleCreate")
    public static native CFBundle create(CFAllocator allocator, CFURL bundleURL);
    @Bridge(symbol="CFBundleCreateBundlesFromDirectory")
    public static native CFArray createBundlesFromDirectory(CFAllocator allocator, CFURL directoryURL, CFString bundleType);
    @Bridge(symbol="CFBundleCopyBundleURL")
    public native CFURL copyBundleURL();
    @Bridge(symbol="CFBundleGetValueForInfoDictionaryKey")
    public native CFType getValueForInfoDictionaryKey(CFString key);
    @Bridge(symbol="CFBundleGetInfoDictionary")
    public native CFDictionary getInfoDictionary();
    @Bridge(symbol="CFBundleGetLocalInfoDictionary")
    public native CFDictionary getLocalInfoDictionary();
    @Bridge(symbol="CFBundleGetPackageInfo")
    public native void getPackageInfo(IntPtr packageType, IntPtr packageCreator);
    @Bridge(symbol="CFBundleGetIdentifier")
    public native CFString getIdentifier();
    @Bridge(symbol="CFBundleGetVersionNumber")
    public native int getVersionNumber();
    @Bridge(symbol="CFBundleGetDevelopmentRegion")
    public native CFString getDevelopmentRegion();
    @Bridge(symbol="CFBundleCopySupportFilesDirectoryURL")
    public native CFURL copySupportFilesDirectoryURL();
    @Bridge(symbol="CFBundleCopyResourcesDirectoryURL")
    public native CFURL copyResourcesDirectoryURL();
    @Bridge(symbol="CFBundleCopyPrivateFrameworksURL")
    public native CFURL copyPrivateFrameworksURL();
    @Bridge(symbol="CFBundleCopySharedFrameworksURL")
    public native CFURL copySharedFrameworksURL();
    @Bridge(symbol="CFBundleCopySharedSupportURL")
    public native CFURL copySharedSupportURL();
    @Bridge(symbol="CFBundleCopyBuiltInPlugInsURL")
    public native CFURL copyBuiltInPlugInsURL();
    @Bridge(symbol="CFBundleCopyInfoDictionaryInDirectory")
    public static native CFDictionary copyInfoDictionaryInDirectory(CFURL bundleURL);
    @Bridge(symbol="CFBundleGetPackageInfoInDirectory")
    public static native boolean getPackageInfoInDirectory(CFURL url, IntPtr packageType, IntPtr packageCreator);
    @Bridge(symbol="CFBundleCopyResourceURL")
    public native CFURL copyResourceURL(CFString resourceName, CFString resourceType, CFString subDirName);
    @Bridge(symbol="CFBundleCopyResourceURLsOfType")
    public native CFArray copyResourceURLsOfType(CFString resourceType, CFString subDirName);
    @Bridge(symbol="CFBundleCopyLocalizedString")
    public native CFString copyLocalizedString(CFString key, CFString value, CFString tableName);
    @Bridge(symbol="CFBundleCopyResourceURLInDirectory")
    public static native CFURL copyResourceURLInDirectory(CFURL bundleURL, CFString resourceName, CFString resourceType, CFString subDirName);
    @Bridge(symbol="CFBundleCopyResourceURLsOfTypeInDirectory")
    public static native CFArray copyResourceURLsOfTypeInDirectory(CFURL bundleURL, CFString resourceType, CFString subDirName);
    @Bridge(symbol="CFBundleCopyBundleLocalizations")
    public native CFArray copyBundleLocalizations();
    @Bridge(symbol="CFBundleCopyPreferredLocalizationsFromArray")
    public static native CFArray copyPreferredLocalizationsFromArray(CFArray locArray);
    @Bridge(symbol="CFBundleCopyLocalizationsForPreferences")
    public static native CFArray copyLocalizationsForPreferences(CFArray locArray, CFArray prefArray);
    @Bridge(symbol="CFBundleCopyResourceURLForLocalization")
    public native CFURL copyResourceURLForLocalization(CFString resourceName, CFString resourceType, CFString subDirName, CFString localizationName);
    @Bridge(symbol="CFBundleCopyResourceURLsOfTypeForLocalization")
    public native CFArray copyResourceURLsOfTypeForLocalization(CFString resourceType, CFString subDirName, CFString localizationName);
    @Bridge(symbol="CFBundleCopyInfoDictionaryForURL")
    public static native CFDictionary copyInfoDictionaryForURL(CFURL url);
    @Bridge(symbol="CFBundleCopyLocalizationsForURL")
    public static native CFArray copyLocalizationsForURL(CFURL url);
    @Bridge(symbol="CFBundleCopyExecutableArchitecturesForURL")
    public static native CFArray copyExecutableArchitecturesForURL(CFURL url);
    @Bridge(symbol="CFBundleCopyExecutableURL")
    public native CFURL copyExecutableURL();
    @Bridge(symbol="CFBundleCopyExecutableArchitectures")
    public native CFArray copyExecutableArchitectures();
    @Bridge(symbol="CFBundlePreflightExecutable")
    public native boolean preflightExecutable(CFError.CFErrorPtr error);
    @Bridge(symbol="CFBundleLoadExecutableAndReturnError")
    public native boolean loadExecutableAndReturnError(CFError.CFErrorPtr error);
    @Bridge(symbol="CFBundleLoadExecutable")
    public native boolean loadExecutable();
    @Bridge(symbol="CFBundleIsExecutableLoaded")
    public native boolean isExecutableLoaded();
    @Bridge(symbol="CFBundleUnloadExecutable")
    public native void unloadExecutable();
    @Bridge(symbol="CFBundleGetFunctionPointerForName")
    public native VoidPtr getFunctionPointerForName(CFString functionName);
    @Bridge(symbol="CFBundleGetFunctionPointersForNames")
    public native void getFunctionPointersForNames(CFArray functionNames, VoidPtr.VoidPtrPtr ftbl);
    @Bridge(symbol="CFBundleGetDataPointerForName")
    public native VoidPtr getDataPointerForName(CFString symbolName);
    @Bridge(symbol="CFBundleGetDataPointersForNames")
    public native void getDataPointersForNames(CFArray symbolNames, VoidPtr.VoidPtrPtr stbl);
    @Bridge(symbol="CFBundleCopyAuxiliaryExecutableURL")
    public native CFURL copyAuxiliaryExecutableURL(CFString executableName);
    @Bridge(symbol="CFBundleGetPlugIn")
    public native CFBundle getPlugIn();
    @Bridge(symbol="CFBundleOpenBundleResourceMap")
    public native short openBundleResourceMap();
    @Bridge(symbol="CFBundleOpenBundleResourceFiles")
    public native int openBundleResourceFiles(ShortPtr refNum, ShortPtr localizedRefNum);
    @Bridge(symbol="CFBundleCloseBundleResourceMap")
    public native void closeBundleResourceMap(short refNum);
    /*</methods>*/
}
