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
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSBundle/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSBundlePtr extends Ptr<NSBundle, NSBundlePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSBundle.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSBundle() {}
    protected NSBundle(SkipInit skipInit) { super(skipInit); }
    public NSBundle(NSURL url) { super((SkipInit) null); initObject(initWithURL$(url)); }
    /*</constructors>*/
    
    public NSBundle(File file) {
        super((SkipInit) null);
        initObject(initWithPath$(file.getAbsolutePath()));
    }
    
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSBundleDidLoadNotification", optional=true)
    public static native String NotificationDidLoad();
    @GlobalValue(symbol="NSLoadedClasses", optional=true)
    public static native NSString KeyLoadedClasses();
    
    @Method(selector = "initWithPath:")
    protected native @Pointer long initWithPath$(String path);
    @Method(selector = "initWithURL:")
    protected native @Pointer long initWithURL$(NSURL url);
    @Method(selector = "preflightAndReturnError:")
    public native boolean preflight(NSError.NSErrorPtr error);
    @Method(selector = "loadAndReturnError:")
    public native boolean load(NSError.NSErrorPtr error);
    @Method(selector = "bundleURL")
    public native NSURL getBundleURL();
    @Method(selector = "resourceURL")
    public native NSURL getResourceURL();
    @Method(selector = "executableURL")
    public native NSURL getExecutableURL();
    @Method(selector = "URLForAuxiliaryExecutable:")
    public native NSURL findAuxiliaryExecutableURL(String executableName);
    @Method(selector = "privateFrameworksURL")
    public native NSURL getPrivateFrameworksURL();
    @Method(selector = "sharedFrameworksURL")
    public native NSURL getSharedFrameworksURL();
    @Method(selector = "sharedSupportURL")
    public native NSURL getSharedSupportURL();
    @Method(selector = "builtInPlugInsURL")
    public native NSURL getBuiltInPlugInsURL();
    @Method(selector = "appStoreReceiptURL")
    public native NSURL getAppStoreReceiptURL();
    @Method(selector = "bundlePath")
    public native String getBundlePath();
    @Method(selector = "resourcePath")
    public native String getResourcePath();
    @Method(selector = "executablePath")
    public native String getExecutablePath();
    @Method(selector = "pathForAuxiliaryExecutable:")
    public native String findAuxiliaryExecutablePath(String executableName);
    @Method(selector = "privateFrameworksPath")
    public native String getPrivateFrameworksPath();
    @Method(selector = "sharedFrameworksPath")
    public native String getSharedFrameworksPath();
    @Method(selector = "sharedSupportPath")
    public native String getSharedSupportPath();
    @Method(selector = "builtInPlugInsPath")
    public native String getBuiltInPlugInsPath();
    @Method(selector = "URLForResource:withExtension:")
    public native NSURL findResourceURLInSubPath(String name, String ext);
    @Method(selector = "URLForResource:withExtension:subdirectory:")
    public native NSURL findResourceURLInSubPath(String name, String ext, String subpath);
    @Method(selector = "URLForResource:withExtension:subdirectory:localization:")
    public native NSURL findResourceURLInSubPath(String name, String ext, String subpath, String localizationName);
    @Method(selector = "URLsForResourcesWithExtension:subdirectory:")
    public native NSArray<NSURL> findResourceURLsInSubPath(String ext, String subpath);
    @Method(selector = "URLsForResourcesWithExtension:subdirectory:localization:")
    public native NSArray<NSURL> findResourceURLsInSubPath(String ext, String subpath, String localizationName);
    @Method(selector = "pathForResource:ofType:")
    public native String findResourcePath(String name, String ext);
    @Method(selector = "pathForResource:ofType:inDirectory:")
    public native String findResourcePathInSubPath(String name, String ext, String subpath);
    @Method(selector = "pathForResource:ofType:inDirectory:forLocalization:")
    public native String findResourcePathInSubPath(String name, String ext, String subpath, String localizationName);
    @Method(selector = "pathsForResourcesOfType:inDirectory:")
    public native NSArray<NSString> findResourcesPathsInSubPath(String ext, String subpath);
    @Method(selector = "pathsForResourcesOfType:inDirectory:forLocalization:")
    public native NSArray<NSString> findResourcesPathsInSubPath(String ext, String subpath, String localizationName);
    @Method(selector = "localizedStringForKey:value:table:")
    public native String getLocalizedString(String key, String value, String tableName);
    @Method(selector = "bundleIdentifier")
    public native String getBundleIdentifier();
    @Method(selector = "infoDictionary")
    public native NSDictionary<?, ?> getInfoDictionary();
    @Method(selector = "localizedInfoDictionary")
    public native NSDictionary<?, ?> getLocalizedInfoDictionary();
    @Method(selector = "objectForInfoDictionaryKey:")
    public native NSObject getInfoDictionaryObject(String key);
    @Method(selector = "classNamed:")
    public native ObjCClass getClassNamed(String className);
    @Method(selector = "principalClass")
    public native ObjCClass getPrincipalClass();
    @Method(selector = "preferredLocalizations")
    public native NSArray<NSString> getPreferredLocalizations();
    @Method(selector = "localizations")
    public native NSArray<NSString> getLocalizations();
    @Method(selector = "developmentLocalization")
    public native String getDevelopmentLocalization();
    @Method(selector = "executableArchitectures")
    public native NSArray<NSNumber> getExecutableArchitectures();
    @Method(selector = "mainBundle")
    public static native NSBundle getMainBundle();
    @Method(selector = "bundleForClass:")
    public static native NSBundle getBundle(ObjCClass aClass);
    @Method(selector = "bundleWithIdentifier:")
    public static native NSBundle getBundle(String identifier);
    @Method(selector = "allBundles")
    public static native NSArray<NSBundle> getAllBundles();
    @Method(selector = "allFrameworks")
    public static native NSArray<NSBundle> getAllFrameworks();
    @Method(selector = "URLForResource:withExtension:subdirectory:inBundleWithURL:")
    public static native NSURL findResourceURLInBundleURL(String name, String ext, String subpath, NSURL bundleURL);
    @Method(selector = "URLsForResourcesWithExtension:subdirectory:inBundleWithURL:")
    public static native NSArray<NSString> findResourceURLsInBundleURL(String ext, String subpath, NSURL bundleURL);
    @Method(selector = "pathForResource:ofType:inDirectory:")
    public static native String findResourcePathInBundlePath(String name, String ext, String bundlePath);
    @Method(selector = "pathsForResourcesOfType:inDirectory:")
    public static native NSArray<NSString> findResourcesPathsInBundlePath(String ext, String bundlePath);
    @Method(selector = "preferredLocalizationsFromArray:")
    public static native NSArray<NSString> getPreferredLocalizations(NSArray<NSString> localizationsArray);
    @Method(selector = "preferredLocalizationsFromArray:forPreferences:")
    public static native NSArray<NSString> getPreferredLocalizations(NSArray<NSString> localizationsArray, NSArray<NSString> preferencesArray);
    /*</methods>*/
}
