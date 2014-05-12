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

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSUserDefaults/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSUserDefaultsPtr extends Ptr<NSUserDefaults, NSUserDefaultsPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSUserDefaults.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSUserDefaults() {}
    protected NSUserDefaults(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSGlobalDomain", optional=true)
    public static native String DomainGlobal();
    @GlobalValue(symbol="NSArgumentDomain", optional=true)
    public static native String DomainArgument();
    @GlobalValue(symbol="NSRegistrationDomain", optional=true)
    public static native String DomainRegistration();
    @GlobalValue(symbol="NSUserDefaultsDidChangeNotification", optional=true)
    public static native String NotificationDidChange();
    
    @Method(selector = "objectForKey:")
    public native NSObject objectForKey$(String defaultName);
    @Method(selector = "setObject:forKey:")
    public native void setObject$forKey$(NSObject value, String defaultName);
    @Method(selector = "removeObjectForKey:")
    public native void removeObjectForKey$(String defaultName);
    @Method(selector = "stringForKey:")
    public native String stringForKey$(String defaultName);
    @Method(selector = "arrayForKey:")
    public native NSArray<?> arrayForKey$(String defaultName);
    @Method(selector = "dictionaryForKey:")
    public native NSDictionary<?, ?> dictionaryForKey$(String defaultName);
    @Method(selector = "dataForKey:")
    public native NSData dataForKey$(String defaultName);
    @Method(selector = "stringArrayForKey:")
    public native NSArray<?> stringArrayForKey$(String defaultName);
    @Method(selector = "integerForKey:")
    public native @MachineSizedSInt long integerForKey$(String defaultName);
    @Method(selector = "floatForKey:")
    public native float floatForKey$(String defaultName);
    @Method(selector = "doubleForKey:")
    public native double doubleForKey$(String defaultName);
    @Method(selector = "boolForKey:")
    public native boolean boolForKey$(String defaultName);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLForKey:")
    public native NSURL URLForKey$(String defaultName);
    @Method(selector = "setInteger:forKey:")
    public native void setInteger$forKey$(@MachineSizedSInt long value, String defaultName);
    @Method(selector = "setFloat:forKey:")
    public native void setFloat$forKey$(float value, String defaultName);
    @Method(selector = "setDouble:forKey:")
    public native void setDouble$forKey$(double value, String defaultName);
    @Method(selector = "setBool:forKey:")
    public native void setBool$forKey$(boolean value, String defaultName);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "setURL:forKey:")
    public native void setURL$forKey$(NSURL url, String defaultName);
    @Method(selector = "registerDefaults:")
    public native void registerDefaults$(NSDictionary<?, ?> registrationDictionary);
    @Method(selector = "addSuiteNamed:")
    public native void addSuiteNamed$(String suiteName);
    @Method(selector = "removeSuiteNamed:")
    public native void removeSuiteNamed$(String suiteName);
    @Method(selector = "dictionaryRepresentation")
    public native NSDictionary<?, ?> dictionaryRepresentation();
    @Method(selector = "volatileDomainNames")
    public native NSArray<?> volatileDomainNames();
    @Method(selector = "volatileDomainForName:")
    public native NSDictionary<?, ?> volatileDomainForName$(String domainName);
    @Method(selector = "setVolatileDomain:forName:")
    public native void setVolatileDomain$forName$(NSDictionary<?, ?> domain, String domainName);
    @Method(selector = "removeVolatileDomainForName:")
    public native void removeVolatileDomainForName$(String domainName);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "persistentDomainNames")
    public native NSArray<?> persistentDomainNames();
    @Method(selector = "persistentDomainForName:")
    public native NSDictionary<?, ?> persistentDomainForName$(String domainName);
    @Method(selector = "setPersistentDomain:forName:")
    public native void setPersistentDomain$forName$(NSDictionary<?, ?> domain, String domainName);
    @Method(selector = "removePersistentDomainForName:")
    public native void removePersistentDomainForName$(String domainName);
    @Method(selector = "synchronize")
    public native boolean synchronize();
    @Method(selector = "objectIsForcedForKey:")
    public native boolean objectIsForcedForKey$(String key);
    @Method(selector = "objectIsForcedForKey:inDomain:")
    public native boolean objectIsForcedForKey$inDomain$(String key, String domain);
    @Method(selector = "standardUserDefaults")
    public static native NSUserDefaults standardUserDefaults();
    @Method(selector = "resetStandardUserDefaults")
    public static native void resetStandardUserDefaults();
    /*</methods>*/
}
