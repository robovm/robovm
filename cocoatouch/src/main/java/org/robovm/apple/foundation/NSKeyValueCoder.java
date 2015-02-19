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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.robovm.objc.annotation.Method;

public class NSKeyValueCoder extends NSExtensions {
    private NSObject object;
    
    public NSKeyValueCoder (NSObject object) {
        this.object = object;
    }
    
    public NSObject getValue(String keyPath) {
        return getValueForKeyPath(object, keyPath);
    }
    @Method(selector = "valueForKeyPath:")
    private static native NSObject getValueForKeyPath(NSObject thiz, String keyPath);
    
    public void setValue(String keyPath, NSObject value) {
        setValueForKeyPath(object, value, keyPath);
    }
    @Method(selector = "setValue:forKeyPath:")
    private static native void setValueForKeyPath(NSObject thiz, NSObject value, String keyPath);
    
    /**
     * 
     * @param keyPath
     * @param value
     * @return
     * @throws NSErrorException
     */
    public boolean validateValue(String keyPath, NSObject value) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = validateValueForKeyPath(object, value, keyPath, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    @Method(selector = "validateValue:forKeyPath:error:")
    private static native boolean validateValueForKeyPath(NSObject thiz, NSObject ioValue, String inKeyPath, NSError.NSErrorPtr outError);
   
    public NSMutableArray<?> getMutableArrayValue(String keyPath) {
        return getMutableArrayValueForKeyPath(object, keyPath);
    }
    @Method(selector = "mutableArrayValueForKeyPath:")
    private static native NSMutableArray<?> getMutableArrayValueForKeyPath(NSObject thiz, String keyPath);
    
    public NSMutableSet<?> getMutableSetValue(String keyPath) {
        return getMutableSetValueForKeyPath(object, keyPath);
    }
    @Method(selector = "mutableSetValueForKeyPath:")
    private static native NSMutableSet<?> getMutableSetValueForKeyPath(NSObject thiz, String keyPath);
    
    public NSMutableOrderedSet<?> getMutableOrderedSetValue(String keyPath) {
        return getMutableOrderedSetValueForKeyPath(object, keyPath);
    }
    @Method(selector = "mutableOrderedSetValueForKeyPath:")
    private static native NSMutableOrderedSet<?> getMutableOrderedSetValueForKeyPath(NSObject thiz, String keyPath);
    
    public NSObject getValueForUndefinedKey(String key) {
        return getValueForUndefinedKey(object, key);
    }
    @Method(selector = "valueForUndefinedKey:")
    private static native NSObject getValueForUndefinedKey(NSObject thiz, String key);
    
    public void setValueForUndefinedKey(String key, NSObject value) {
        setValueForUndefinedKey(object, value, key);
    }
    @Method(selector = "setValue:forUndefinedKey:")
    private static native void setValueForUndefinedKey(NSObject thiz, NSObject value, String key);
    
    public void setNullValue(String key) {
        setNullValueForKey(object, key);
    }
    @Method(selector = "setNilValueForKey:")
    private static native void setNullValueForKey(NSObject thiz, String key);
    
    public Map<String, NSObject> getValues(String...keys) {
        return getValues(Arrays.asList(keys));
    }
    public Map<String, NSObject> getValues(List<String> keys) {
        return getDictionaryWithValuesForKeys(object, keys);
    }
    @Method(selector = "dictionaryWithValuesForKeys:")
    private static native @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringMapMarshaler.class) Map<String, NSObject> getDictionaryWithValuesForKeys(NSObject thiz, @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> keys);
    
    public void setValues(Map<String, NSObject> values) {
        setValuesForKeys(object, values);
    }
    @Method(selector = "setValuesForKeysWithDictionary:")
    private static native void setValuesForKeys(NSObject thiz, @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringMapMarshaler.class) Map<String, NSObject> keyedValues);

    
    // ROBOVM NOTE: For simplicity we only expose the key-path methods 
    // as they already have the same functionality as the key-only methods.
    @Method(selector = "valueForKey:")
    private static native NSObject getValueForKey(NSObject thiz, String key);
    @Method(selector = "setValue:forKey:")
    private static native void setValueForKey(NSObject thiz, NSObject value, String key);
    @Method(selector = "validateValue:forKey:error:")
    private static native boolean validateValueForKey(NSObject thiz, NSObject ioValue, String inKey, NSError.NSErrorPtr outError);
    @Method(selector = "mutableArrayValueForKey:")
    private static native NSMutableArray<?> getMutableArrayValueForKey(NSObject thiz, String key);
    @Method(selector = "mutableOrderedSetValueForKey:")
    private static native NSMutableOrderedSet<?> getMutableOrderedSetValueForKey(NSObject thiz, String key);
    @Method(selector = "mutableSetValueForKey:")
    private static native NSMutableSet<?> getMutableSetValueForKey(NSObject thiz, String key);
}
