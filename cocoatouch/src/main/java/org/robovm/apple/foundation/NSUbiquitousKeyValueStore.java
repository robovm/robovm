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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSUbiquitousKeyValueStore/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSUbiquitousKeyValueStorePtr extends Ptr<NSUbiquitousKeyValueStore, NSUbiquitousKeyValueStorePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSUbiquitousKeyValueStore.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSUbiquitousKeyValueStore() {}
    protected NSUbiquitousKeyValueStore(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "objectForKey:")
    public native NSObject objectForKey$(String aKey);
    @Method(selector = "setObject:forKey:")
    public native void setObject$forKey$(NSObject anObject, String aKey);
    @Method(selector = "removeObjectForKey:")
    public native void removeObjectForKey$(String aKey);
    @Method(selector = "stringForKey:")
    public native String stringForKey$(String aKey);
    @Method(selector = "arrayForKey:")
    public native NSArray<?> arrayForKey$(String aKey);
    @Method(selector = "dictionaryForKey:")
    public native NSDictionary<?, ?> dictionaryForKey$(String aKey);
    @Method(selector = "dataForKey:")
    public native NSData dataForKey$(String aKey);
    @Method(selector = "longLongForKey:")
    public native long longLongForKey$(String aKey);
    @Method(selector = "doubleForKey:")
    public native double doubleForKey$(String aKey);
    @Method(selector = "boolForKey:")
    public native boolean boolForKey$(String aKey);
    @Method(selector = "setString:forKey:")
    public native void setString$forKey$(String aString, String aKey);
    @Method(selector = "setData:forKey:")
    public native void setData$forKey$(NSData aData, String aKey);
    @Method(selector = "setArray:forKey:")
    public native void setArray$forKey$(NSArray<?> anArray, String aKey);
    @Method(selector = "setDictionary:forKey:")
    public native void setDictionary$forKey$(NSDictionary<?, ?> aDictionary, String aKey);
    @Method(selector = "setLongLong:forKey:")
    public native void setLongLong$forKey$(long value, String aKey);
    @Method(selector = "setDouble:forKey:")
    public native void setDouble$forKey$(double value, String aKey);
    @Method(selector = "setBool:forKey:")
    public native void setBool$forKey$(boolean value, String aKey);
    @Method(selector = "dictionaryRepresentation")
    public native NSDictionary<?, ?> dictionaryRepresentation();
    @Method(selector = "synchronize")
    public native boolean synchronize();
    @Method(selector = "defaultStore")
    public static native NSUbiquitousKeyValueStore defaultStore();
    /*</methods>*/
}
