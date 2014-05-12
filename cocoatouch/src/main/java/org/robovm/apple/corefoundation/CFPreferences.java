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

/*<javadoc>*/
/*</javadoc>*/
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
    @Bridge(symbol="CFPreferencesCopyAppValue", optional=true)
    public static native CFType copyAppValue(CFString key, CFString applicationID);
    @Bridge(symbol="CFPreferencesGetAppBooleanValue", optional=true)
    public static native boolean getAppBooleanValue(CFString key, CFString applicationID, BytePtr keyExistsAndHasValidFormat);
    @Bridge(symbol="CFPreferencesGetAppIntegerValue", optional=true)
    public static native @MachineSizedSInt long getAppIntegerValue(CFString key, CFString applicationID, BytePtr keyExistsAndHasValidFormat);
    @Bridge(symbol="CFPreferencesSetAppValue", optional=true)
    public static native void setAppValue(CFString key, CFType value, CFString applicationID);
    @Bridge(symbol="CFPreferencesAddSuitePreferencesToApp", optional=true)
    public static native void addSuitePreferencesToApp(CFString applicationID, CFString suiteID);
    @Bridge(symbol="CFPreferencesRemoveSuitePreferencesFromApp", optional=true)
    public static native void removeSuitePreferencesFromApp(CFString applicationID, CFString suiteID);
    @Bridge(symbol="CFPreferencesAppSynchronize", optional=true)
    public static native boolean appSynchronize(CFString applicationID);
    @Bridge(symbol="CFPreferencesCopyValue", optional=true)
    public static native CFType copyValue(CFString key, CFString applicationID, CFString userName, CFString hostName);
    @Bridge(symbol="CFPreferencesCopyMultiple", optional=true)
    public static native CFDictionary copyMultiple(CFArray keysToFetch, CFString applicationID, CFString userName, CFString hostName);
    @Bridge(symbol="CFPreferencesSetValue", optional=true)
    public static native void setValue(CFString key, CFType value, CFString applicationID, CFString userName, CFString hostName);
    @Bridge(symbol="CFPreferencesSetMultiple", optional=true)
    public static native void setMultiple(CFDictionary keysToSet, CFArray keysToRemove, CFString applicationID, CFString userName, CFString hostName);
    @Bridge(symbol="CFPreferencesSynchronize", optional=true)
    public static native boolean synchronize(CFString applicationID, CFString userName, CFString hostName);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="CFPreferencesCopyApplicationList", optional=true)
    public static native CFArray copyApplicationList(CFString userName, CFString hostName);
    @Bridge(symbol="CFPreferencesCopyKeyList", optional=true)
    public static native CFArray copyKeyList(CFString applicationID, CFString userName, CFString hostName);
    @Bridge(symbol="CFPreferencesAppValueIsForced", optional=true)
    public static native boolean appValueIsForced(CFString key, CFString applicationID);
    /*</methods>*/
}
