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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFPreferences/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFPreferences.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCFPreferencesAnyApplication")
    public static native CFString KeyAnyApplication();
    @GlobalValue(symbol="kCFPreferencesCurrentApplication")
    public static native CFString KeyCurrentApplication();
    @GlobalValue(symbol="kCFPreferencesAnyHost")
    public static native CFString KeyAnyHost();
    @GlobalValue(symbol="kCFPreferencesCurrentHost")
    public static native CFString KeyCurrentHost();
    @GlobalValue(symbol="kCFPreferencesAnyUser")
    public static native CFString KeyAnyUser();
    @GlobalValue(symbol="kCFPreferencesCurrentUser")
    public static native CFString KeyCurrentUser();
    
    @Bridge(symbol="CFPreferencesCopyAppValue")
    public static native CFType copyAppValue(CFString key, CFString applicationID);
    @Bridge(symbol="CFPreferencesGetAppBooleanValue")
    public static native boolean getAppBooleanValue(CFString key, CFString applicationID, BytePtr keyExistsAndHasValidFormat);
    @Bridge(symbol="CFPreferencesGetAppIntegerValue")
    public static native @MachineSizedSInt long getAppIntegerValue(CFString key, CFString applicationID, BytePtr keyExistsAndHasValidFormat);
    @Bridge(symbol="CFPreferencesSetAppValue")
    public static native void setAppValue(CFString key, CFType value, CFString applicationID);
    @Bridge(symbol="CFPreferencesAddSuitePreferencesToApp")
    public static native void addSuitePreferencesToApp(CFString applicationID, CFString suiteID);
    @Bridge(symbol="CFPreferencesRemoveSuitePreferencesFromApp")
    public static native void removeSuitePreferencesFromApp(CFString applicationID, CFString suiteID);
    @Bridge(symbol="CFPreferencesAppSynchronize")
    public static native boolean appSynchronize(CFString applicationID);
    @Bridge(symbol="CFPreferencesCopyValue")
    public static native CFType copyValue(CFString key, CFString applicationID, CFString userName, CFString hostName);
    @Bridge(symbol="CFPreferencesCopyMultiple")
    public static native CFDictionary copyMultiple(CFArray keysToFetch, CFString applicationID, CFString userName, CFString hostName);
    @Bridge(symbol="CFPreferencesSetValue")
    public static native void setValue(CFString key, CFType value, CFString applicationID, CFString userName, CFString hostName);
    @Bridge(symbol="CFPreferencesSetMultiple")
    public static native void setMultiple(CFDictionary keysToSet, CFArray keysToRemove, CFString applicationID, CFString userName, CFString hostName);
    @Bridge(symbol="CFPreferencesSynchronize")
    public static native boolean synchronize(CFString applicationID, CFString userName, CFString hostName);
    @Bridge(symbol="CFPreferencesCopyApplicationList")
    public static native CFArray copyApplicationList(CFString userName, CFString hostName);
    @Bridge(symbol="CFPreferencesCopyKeyList")
    public static native CFArray copyKeyList(CFString applicationID, CFString userName, CFString hostName);
    @Bridge(symbol="CFPreferencesAppValueIsForced")
    public static native boolean appValueIsForced(CFString key, CFString applicationID);
    /*</methods>*/
}
