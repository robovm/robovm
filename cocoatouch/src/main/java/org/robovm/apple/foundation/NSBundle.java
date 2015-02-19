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
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSBundle/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        public static NSObject observeDidLoad(NSBundle object, final VoidBlock2<NSBundle, List<String>> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidLoadNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @SuppressWarnings("unchecked")
                @Override
                public void invoke(NSNotification a) {
                    List<String> classes = null;
                    NSDictionary<NSString, NSObject> data = a.getUserInfo();
                    if (data.containsKey(LoadedClassesKey())) {
                        NSArray<NSString> val = (NSArray<NSString>)data.get(LoadedClassesKey());
                        classes = val.asStringList();
                    }
                    
                    block.invoke((NSBundle)a.getObject(), classes);
                }
            });
        }
    }
    
    /*<ptr>*/public static class NSBundlePtr extends Ptr<NSBundle, NSBundlePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSBundle.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSBundle() {}
    protected NSBundle(SkipInit skipInit) { super(skipInit); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSBundle(NSURL url) { super((SkipInit) null); initObject(init(url)); }
    /*</constructors>*/
    
    public NSBundle(File file) {
        super((SkipInit) null);
        initObject(init(file.getAbsolutePath()));
    }
    
    /*<properties>*/
    @Property(selector = "isLoaded")
    public native boolean isLoaded();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "bundleURL")
    public native NSURL getBundleURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "resourceURL")
    public native NSURL getResourceURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "executableURL")
    public native NSURL getExecutableURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "privateFrameworksURL")
    public native NSURL getPrivateFrameworksURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "sharedFrameworksURL")
    public native NSURL getSharedFrameworksURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "sharedSupportURL")
    public native NSURL getSharedSupportURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "builtInPlugInsURL")
    public native NSURL getBuiltInPlugInsURL();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "appStoreReceiptURL")
    public native NSURL getAppStoreReceiptURL();
    @Property(selector = "bundlePath")
    public native String getBundlePath();
    @Property(selector = "resourcePath")
    public native String getResourcePath();
    @Property(selector = "executablePath")
    public native String getExecutablePath();
    @Property(selector = "privateFrameworksPath")
    public native String getPrivateFrameworksPath();
    @Property(selector = "sharedFrameworksPath")
    public native String getSharedFrameworksPath();
    @Property(selector = "sharedSupportPath")
    public native String getSharedSupportPath();
    @Property(selector = "builtInPlugInsPath")
    public native String getBuiltInPlugInsPath();
    @Property(selector = "bundleIdentifier")
    public native String getBundleIdentifier();
    @Property(selector = "infoDictionary")
    public native NSDictionary<?, ?> getInfoDictionary();
    @Property(selector = "localizedInfoDictionary")
    public native NSDictionary<?, ?> getLocalizedInfoDictionary();
    @Property(selector = "principalClass")
    public native Class<?> getPrincipalClass();
    @Property(selector = "preferredLocalizations")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getPreferredLocalizations();
    @Property(selector = "localizations")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getLocalizations();
    @Property(selector = "developmentLocalization")
    public native String getDevelopmentLocalization();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "executableArchitectures")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSBundleExecutableArchitectureType.AsListMarshaler.class) List<NSBundleExecutableArchitectureType> getExecutableArchitectures();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    /* UIKit extensions */
    public NSArray<?> loadNib(String name, NSObject owner, UINibLoadingOptions options) {
        return NSBundleExtensions.loadNib(this, name, owner, options);
    }
    
    /*<methods>*/
    @GlobalValue(symbol="NSBundleDidLoadNotification", optional=true)
    public static native NSString DidLoadNotification();
    @GlobalValue(symbol="NSLoadedClasses", optional=true)
    protected static native NSString LoadedClassesKey();
    
    @Method(selector = "initWithPath:")
    protected native @Pointer long init(String path);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "initWithURL:")
    protected native @Pointer long init(NSURL url);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean preflight() throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = preflight(ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "preflightAndReturnError:")
    private native boolean preflight(NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean load() throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = load(ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "loadAndReturnError:")
    private native boolean load(NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLForAuxiliaryExecutable:")
    public native NSURL findAuxiliaryExecutableURL(String executableName);
    @Method(selector = "pathForAuxiliaryExecutable:")
    public native String findAuxiliaryExecutablePath(String executableName);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLForResource:withExtension:")
    public native NSURL findResourceURL(String name, String ext);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLForResource:withExtension:subdirectory:")
    public native NSURL findResourceURL(String name, String ext, String subpath);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLForResource:withExtension:subdirectory:localization:")
    public native NSURL findResourceURL(String name, String ext, String subpath, String localizationName);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLsForResourcesWithExtension:subdirectory:")
    public native NSArray<NSURL> findResourceURLs(String ext, String subpath);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLsForResourcesWithExtension:subdirectory:localization:")
    public native NSArray<NSURL> findResourceURLs(String ext, String subpath, String localizationName);
    @Method(selector = "pathForResource:ofType:")
    public native String findResourcePath(String name, String ext);
    @Method(selector = "pathForResource:ofType:inDirectory:")
    public native String findResourcePath(String name, String ext, String subpath);
    @Method(selector = "pathForResource:ofType:inDirectory:forLocalization:")
    public native String findResourcePath(String name, String ext, String subpath, String localizationName);
    @Method(selector = "pathsForResourcesOfType:inDirectory:")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> findResourcesPaths(String ext, String subpath);
    @Method(selector = "pathsForResourcesOfType:inDirectory:forLocalization:")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> findResourcesPaths(String ext, String subpath, String localizationName);
    @Method(selector = "localizedStringForKey:value:table:")
    public native String getLocalizedString(String key, String value, String tableName);
    @Method(selector = "objectForInfoDictionaryKey:")
    public native NSObject getInfoDictionaryObject(String key);
    @Method(selector = "classNamed:")
    public native Class<?> getClassNamed(String className);
    @Method(selector = "mainBundle")
    public static native NSBundle getMainBundle();
    @Method(selector = "bundleForClass:")
    public static native NSBundle getBundle(Class<?> aClass);
    @Method(selector = "bundleWithIdentifier:")
    public static native NSBundle getBundle(String identifier);
    @Method(selector = "allBundles")
    public static native NSArray<NSBundle> getAllBundles();
    @Method(selector = "allFrameworks")
    public static native NSArray<NSBundle> getAllFrameworks();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLForResource:withExtension:subdirectory:inBundleWithURL:")
    public static native NSURL findResourceURLInBundle(String name, String ext, String subpath, NSURL bundleURL);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLsForResourcesWithExtension:subdirectory:inBundleWithURL:")
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> findResourceURLsInBundle(String ext, String subpath, NSURL bundleURL);
    @Method(selector = "pathForResource:ofType:inDirectory:")
    public static native String findResourcePathInBundle(String name, String ext, String bundlePath);
    @Method(selector = "pathsForResourcesOfType:inDirectory:")
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> findResourcesPathsInBundle(String ext, String bundlePath);
    @Method(selector = "preferredLocalizationsFromArray:")
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getPreferredLocalizations(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> localizationsArray);
    @Method(selector = "preferredLocalizationsFromArray:forPreferences:")
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getPreferredLocalizations(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> localizationsArray, @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> preferencesArray);
    /*</methods>*/
}
