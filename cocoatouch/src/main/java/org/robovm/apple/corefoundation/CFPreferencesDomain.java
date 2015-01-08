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
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CFPreferencesDomain.Marshaler.class)
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFPreferencesDomain/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CFPreferencesDomain toObject(Class<CFPreferencesDomain> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFPreferencesDomain.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFPreferencesDomain o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFPreferencesDomain.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final CFPreferencesDomain AnyApplication = new CFPreferencesDomain("AnyApplicationValue");
    public static final CFPreferencesDomain CurrentApplication = new CFPreferencesDomain("CurrentApplicationValue");
    public static final CFPreferencesDomain AnyHost = new CFPreferencesDomain("AnyHostValue");
    public static final CFPreferencesDomain CurrentHost = new CFPreferencesDomain("CurrentHostValue");
    public static final CFPreferencesDomain AnyUser = new CFPreferencesDomain("AnyUserValue");
    public static final CFPreferencesDomain CurrentUser = new CFPreferencesDomain("CurrentUserValue");
    
    private static CFPreferencesDomain[] values = new CFPreferencesDomain[] {AnyApplication, CurrentApplication, AnyHost, 
        CurrentHost, AnyUser, CurrentUser};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CFPreferencesDomain(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CFPreferencesDomain valueOf(CFString value) {
        for (CFPreferencesDomain v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFPreferencesDomain/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="kCFPreferencesAnyApplication", optional=true)
    protected static native CFString AnyApplicationValue();
    @GlobalValue(symbol="kCFPreferencesCurrentApplication", optional=true)
    protected static native CFString CurrentApplicationValue();
    @GlobalValue(symbol="kCFPreferencesAnyHost", optional=true)
    protected static native CFString AnyHostValue();
    @GlobalValue(symbol="kCFPreferencesCurrentHost", optional=true)
    protected static native CFString CurrentHostValue();
    @GlobalValue(symbol="kCFPreferencesAnyUser", optional=true)
    protected static native CFString AnyUserValue();
    @GlobalValue(symbol="kCFPreferencesCurrentUser", optional=true)
    protected static native CFString CurrentUserValue();
    /*</methods>*/
}
