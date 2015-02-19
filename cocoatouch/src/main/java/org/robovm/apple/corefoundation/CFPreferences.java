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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFPreferences/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFPreferences.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    private String applicationID;
    
    public CFPreferences(CFPreferencesDomain domain) {
        this(domain.value().toString());
    }
    public CFPreferences(String applicationID) {
        this.applicationID = applicationID;
    }
    
    public static CFPreferences create(CFPreferencesDomain domain) {
        return new CFPreferences(domain);
    }
    public static CFPreferences create(String applicationID) {
        return new CFPreferences(applicationID);
    }
    
    public CFType getValue(String key) {
        return getAppValue(applicationID, key);
    }
    public boolean getBooleanValue(String key) {
        return getAppBooleanValue(key, applicationID, new BooleanPtr());
    }
    public boolean hasBooleanValue(String key) {
        BooleanPtr ptr = new BooleanPtr();
        getAppBooleanValue(key, applicationID, ptr);
        return ptr.get();
    }
    public long getLongValue(String key) {
        return getAppIntegerValue(key, applicationID, new BooleanPtr());
    }
    public boolean hasLongValue(String key) {
        BooleanPtr ptr = new BooleanPtr();
        getAppIntegerValue(key, applicationID, ptr);
        return ptr.get();
    }
    public void setValue(String key, CFType value) {
        setAppValue(key, value, applicationID);
    }
    public void addSuitePreferences(String suiteID) {
        addSuitePreferencesToApp(applicationID, suiteID);
    }
    public void removeSuitePreferences(String suiteID) {
        removeSuitePreferencesFromApp(applicationID, suiteID);
    }
    public boolean synchronize() {
        return appSynchronize(applicationID);
    }
    public CFType getValue(String key, CFPreferencesDomain userName, CFPreferencesDomain hostName) {
        return getValue(key, applicationID, userName.value().toString(), hostName.value().toString());
    }
    public CFType getValue(String key, String userName, String hostName) {
        return getValue(key, applicationID, userName, hostName);
    }
    public Map<String, ?> getMultipleValues(List<String> keysToFetch, CFPreferencesDomain userName, CFPreferencesDomain hostName) {
        return getMultiple(keysToFetch, applicationID, userName.value().toString(), hostName.value().toString());
    }
    public Map<String, ?> getMultipleValues(List<String> keysToFetch, String userName, String hostName) {
        return getMultiple(keysToFetch, applicationID, userName, hostName);
    }
    public void setValue(String key, CFType value, CFPreferencesDomain userName, CFPreferencesDomain hostName) {
        setValue(key, value, applicationID, userName.value().toString(), hostName.value().toString());
    }
    public void setValue(String key, CFType value, String userName, String hostName) {
        setValue(key, value, applicationID, userName, hostName);
    }
    public void setMultipleValues(Map<String, ?> keysToSet, List<String> keysToRemove, CFPreferencesDomain userName, CFPreferencesDomain hostName) {
        setMultiple(keysToSet, keysToRemove, applicationID, userName.value().toString(), hostName.value().toString());
    }
    public void setMultipleValues(Map<String, ?> keysToSet, List<String> keysToRemove, String userName, String hostName) {
        setMultiple(keysToSet, keysToRemove, applicationID, userName, hostName);
    }
    public boolean synchronize(CFPreferencesDomain userName, CFPreferencesDomain hostName) {
        return synchronize(applicationID, userName.value().toString(), hostName.value().toString());
    }
    public boolean synchronize(String userName, String hostName) {
        return synchronize(applicationID, userName, hostName);
    }
    public List<String> getKeyList(CFPreferencesDomain userName, CFPreferencesDomain hostName) {
        return getKeyList(applicationID, userName.value().toString(), hostName.value().toString());
    }
    public List<String> getKeyList(String userName, String hostName) {
        return getKeyList(applicationID, userName, hostName);
    }
    public boolean isValueForced(String key) {
        return appValueIsForced(key, applicationID);
    }
    /*<methods>*/
    @Bridge(symbol="CFPreferencesCopyAppValue", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFType getAppValue(String key, String applicationID);
    @Bridge(symbol="CFPreferencesGetAppBooleanValue", optional=true)
    protected static native boolean getAppBooleanValue(String key, String applicationID, BooleanPtr keyExistsAndHasValidFormat);
    @Bridge(symbol="CFPreferencesGetAppIntegerValue", optional=true)
    protected static native @MachineSizedSInt long getAppIntegerValue(String key, String applicationID, BooleanPtr keyExistsAndHasValidFormat);
    @Bridge(symbol="CFPreferencesSetAppValue", optional=true)
    protected static native void setAppValue(String key, CFType value, String applicationID);
    @Bridge(symbol="CFPreferencesAddSuitePreferencesToApp", optional=true)
    protected static native void addSuitePreferencesToApp(String applicationID, String suiteID);
    @Bridge(symbol="CFPreferencesRemoveSuitePreferencesFromApp", optional=true)
    protected static native void removeSuitePreferencesFromApp(String applicationID, String suiteID);
    @Bridge(symbol="CFPreferencesAppSynchronize", optional=true)
    protected static native boolean appSynchronize(String applicationID);
    @Bridge(symbol="CFPreferencesCopyValue", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFType getValue(String key, String applicationID, String userName, String hostName);
    @Bridge(symbol="CFPreferencesCopyMultiple", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFDictionary.AsStringMapMarshaler.class) Map<String, ?> getMultiple(@org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> keysToFetch, String applicationID, String userName, String hostName);
    @Bridge(symbol="CFPreferencesSetValue", optional=true)
    protected static native void setValue(String key, CFType value, String applicationID, String userName, String hostName);
    @Bridge(symbol="CFPreferencesSetMultiple", optional=true)
    protected static native void setMultiple(@org.robovm.rt.bro.annotation.Marshaler(CFDictionary.AsStringMapMarshaler.class) Map<String, ?> keysToSet, @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> keysToRemove, String applicationID, String userName, String hostName);
    @Bridge(symbol="CFPreferencesSynchronize", optional=true)
    protected static native boolean synchronize(String applicationID, String userName, String hostName);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="CFPreferencesCopyApplicationList", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> getApplicationList(String userName, String hostName);
    @Bridge(symbol="CFPreferencesCopyKeyList", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> getKeyList(String applicationID, String userName, String hostName);
    @Bridge(symbol="CFPreferencesAppValueIsForced", optional=true)
    protected static native boolean appValueIsForced(String key, String applicationID);
    /*</methods>*/
}
