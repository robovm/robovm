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

    public static class Notifications {
        /**
         * @since Available in iOS 5.0 and later.
         */
        public static NSObject observeDidChangeExternally(NSUbiquitousKeyValueStore object, final VoidBlock3<NSUbiquitousKeyValueStore, String, List<String>> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidChangeExternallyNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    String reason = null;
                    List<String> keys = null;
                    NSDictionary<NSString, NSObject> data = a.getUserInfo();
                    if (data.containsKey(ChangeReasonKey())) {
                        NSString val = (NSString)data.get(ChangeReasonKey());
                        reason = val.toString();
                    }
                    if (data.containsKey(ChangedKeysKey())) {
                        @SuppressWarnings("unchecked")
                        NSArray<NSString> val = (NSArray<NSString>)data.get(ChangedKeysKey());
                        keys = val.asStringList();
                    }
                    block.invoke((NSUbiquitousKeyValueStore)a.getObject(), reason, keys);
                }
            });
        }
    }
    
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
    public void put(String key, NSObject object) {
        setObject(object, key);
    }
    public void put(String key, String string) {
        setString(string, key);
    }
    public void put(String key, NSData data) {
        setData(data, key);
    }
    public void put(String key, NSArray<?> array) {
        setArray(array, key);
    }
    public void put(String key, NSDictionary<?, ?> dictionary) {
        setDictionary(dictionary, key);
    }
    public void put(String key, long value) {
        setLongLong(value, key);
    }
    public void put(String key, double value) {
        setDouble(value, key);
    }
    public void put(String key, boolean value) {
        setBool(value, key);
    }

    public Map<String, NSObject> asMap() {
        return asDictionary().asStringMap();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSUbiquitousKeyValueStoreDidChangeExternallyNotification", optional=true)
    public static native NSString DidChangeExternallyNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSUbiquitousKeyValueStoreChangeReasonKey", optional=true)
    protected static native NSString ChangeReasonKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSUbiquitousKeyValueStoreChangedKeysKey", optional=true)
    protected static native NSString ChangedKeysKey();
    
    @Method(selector = "objectForKey:")
    public native NSObject get(String aKey);
    @Method(selector = "setObject:forKey:")
    protected native void setObject(NSObject anObject, String aKey);
    @Method(selector = "removeObjectForKey:")
    public native void remove(String aKey);
    @Method(selector = "stringForKey:")
    public native String getString(String aKey);
    @Method(selector = "arrayForKey:")
    public native NSArray<?> getArray(String aKey);
    @Method(selector = "dictionaryForKey:")
    public native NSDictionary<?, ?> getDictionary(String aKey);
    @Method(selector = "dataForKey:")
    public native NSData getData(String aKey);
    @Method(selector = "longLongForKey:")
    public native long getLong(String aKey);
    @Method(selector = "doubleForKey:")
    public native double getDouble(String aKey);
    @Method(selector = "boolForKey:")
    public native boolean getBoolean(String aKey);
    @Method(selector = "setString:forKey:")
    protected native void setString(String aString, String aKey);
    @Method(selector = "setData:forKey:")
    protected native void setData(NSData aData, String aKey);
    @Method(selector = "setArray:forKey:")
    protected native void setArray(NSArray<?> anArray, String aKey);
    @Method(selector = "setDictionary:forKey:")
    protected native void setDictionary(NSDictionary<?, ?> aDictionary, String aKey);
    @Method(selector = "setLongLong:forKey:")
    protected native void setLongLong(long value, String aKey);
    @Method(selector = "setDouble:forKey:")
    protected native void setDouble(double value, String aKey);
    @Method(selector = "setBool:forKey:")
    protected native void setBool(boolean value, String aKey);
    @Method(selector = "dictionaryRepresentation")
    public native NSDictionary<NSString, NSObject> asDictionary();
    @Method(selector = "synchronize")
    public native boolean synchronize();
    @Method(selector = "defaultStore")
    public static native NSUbiquitousKeyValueStore getDefaultStore();
    /*</methods>*/
}
