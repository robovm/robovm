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
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSUserDefaults/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        public static NSObject observeDidChange(NSUserDefaults object, final VoidBlock1<NSUserDefaults> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidChangeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NSUserDefaults)a.getObject());
                }
            });
        }
    }
    
    /*<ptr>*/public static class NSUserDefaultsPtr extends Ptr<NSUserDefaults, NSUserDefaultsPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSUserDefaults.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSUserDefaults() {}
    protected NSUserDefaults(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "volatileDomainNames")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getVolatileDomainNames();
    /*</properties>*/
    /*<members>*//*</members>*/
    public void put(String defaultName, NSObject value) {
        setObject(value, defaultName);
    }
    public void put(String defaultName, String value) {
        setObject(new NSString(value), defaultName);
    }
    public void put(String defaultName, @MachineSizedSInt long value) {
        setInteger(value, defaultName);
    }
    public void put(String defaultName, float value) {
        setFloat(value, defaultName);
    }
    public void put(String defaultName, double value) {
        setDouble(value, defaultName);
    }
    public void put(String defaultName, boolean value) {
        setBool(value, defaultName);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public void put(String defaultName, NSURL url) {
        setURL(url, defaultName);
    }
    
    public void setVolatileDomain(String domainName, NSDictionary<?, ?> domain) {
        setVolatileDomain(domain, domainName);
    }
    public void setVolatileDomain(NSUserDefaultsDomain domainName, NSDictionary<?, ?> domain) {
        setVolatileDomain(domain, domainName.value());
    } 
    public void setPersistentDomain(String domainName, NSDictionary<?, ?> domain) {
        setPersistentDomain(domain, domainName);
    }
    public void setPersistentDomain(NSUserDefaultsDomain domainName, NSDictionary<?, ?> domain) {
        setPersistentDomain(domain, domainName.value());
    }
    
    public NSDictionary<?, ?> getVolatileDomain(NSUserDefaultsDomain domainName) {
        return getVolatileDomain(domainName.value());
    }
    public void removeVolatileDomain(NSUserDefaultsDomain domainName) {
        removeVolatileDomain(domainName.value());
    }
    public NSDictionary<?, ?> getPersistentDomain(NSUserDefaultsDomain domainName) {
        return getPersistentDomain(domainName.value());
    }
    public void removePersistentDomain(NSUserDefaultsDomain domainName) {
        removePersistentDomain(domainName.value());
    }
    public boolean isObjectForced(String key, NSUserDefaultsDomain domain) {
        return isObjectForced(key, domain.value());
    }
    /*<methods>*/
    @GlobalValue(symbol="NSUserDefaultsDidChangeNotification", optional=true)
    public static native NSString DidChangeNotification();
    
    @Method(selector = "objectForKey:")
    public native NSObject get(String defaultName);
    @Method(selector = "setObject:forKey:")
    protected native void setObject(NSObject value, String defaultName);
    @Method(selector = "removeObjectForKey:")
    public native void remove(String defaultName);
    @Method(selector = "stringForKey:")
    public native String getString(String defaultName);
    @Method(selector = "arrayForKey:")
    public native NSArray<?> getArray(String defaultName);
    @Method(selector = "dictionaryForKey:")
    public native NSDictionary<?, ?> getDictionary(String defaultName);
    @Method(selector = "dataForKey:")
    public native NSData getData(String defaultName);
    @Method(selector = "stringArrayForKey:")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getStringArray(String defaultName);
    @Method(selector = "integerForKey:")
    public native @MachineSizedSInt long getInteger(String defaultName);
    @Method(selector = "floatForKey:")
    public native float getFloat(String defaultName);
    @Method(selector = "doubleForKey:")
    public native double getDouble(String defaultName);
    @Method(selector = "boolForKey:")
    public native boolean getBoolean(String defaultName);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLForKey:")
    public native NSURL getURL(String defaultName);
    @Method(selector = "setInteger:forKey:")
    protected native void setInteger(@MachineSizedSInt long value, String defaultName);
    @Method(selector = "setFloat:forKey:")
    protected native void setFloat(float value, String defaultName);
    @Method(selector = "setDouble:forKey:")
    protected native void setDouble(double value, String defaultName);
    @Method(selector = "setBool:forKey:")
    protected native void setBool(boolean value, String defaultName);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "setURL:forKey:")
    protected native void setURL(NSURL url, String defaultName);
    @Method(selector = "registerDefaults:")
    public native void registerDefaults(NSDictionary<?, ?> registrationDictionary);
    @Method(selector = "addSuiteNamed:")
    public native void addSuite(String suiteName);
    @Method(selector = "removeSuiteNamed:")
    public native void removeSuite(String suiteName);
    @Method(selector = "dictionaryRepresentation")
    public native NSDictionary<?, ?> asDictionary();
    @Method(selector = "volatileDomainForName:")
    public native NSDictionary<?, ?> getVolatileDomain(String domainName);
    @Method(selector = "setVolatileDomain:forName:")
    protected native void setVolatileDomain(NSDictionary<?, ?> domain, String domainName);
    @Method(selector = "removeVolatileDomainForName:")
    public native void removeVolatileDomain(String domainName);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "persistentDomainNames")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getPersistentDomainNames();
    @Method(selector = "persistentDomainForName:")
    public native NSDictionary<?, ?> getPersistentDomain(String domainName);
    @Method(selector = "setPersistentDomain:forName:")
    protected native void setPersistentDomain(NSDictionary<?, ?> domain, String domainName);
    @Method(selector = "removePersistentDomainForName:")
    public native void removePersistentDomain(String domainName);
    @Method(selector = "synchronize")
    public native boolean synchronize();
    @Method(selector = "objectIsForcedForKey:")
    public native boolean isObjectForced(String key);
    @Method(selector = "objectIsForcedForKey:inDomain:")
    public native boolean isObjectForced(String key, String domain);
    @Method(selector = "standardUserDefaults")
    public static native NSUserDefaults getStandardUserDefaults();
    @Method(selector = "resetStandardUserDefaults")
    public static native void resetStandardUserDefaults();
    /*</methods>*/
}
