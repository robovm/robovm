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
@Marshaler(CFPlugInProperty.Marshaler.class)
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFPlugInProperty/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CFPlugInProperty toObject(Class<CFPlugInProperty> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFPlugInProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFPlugInProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFPlugInProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final CFPlugInProperty DynamicRegistration = new CFPlugInProperty("DynamicRegistrationValue");
    public static final CFPlugInProperty DynamicRegisterFunction = new CFPlugInProperty("DynamicRegisterFunctionValue");
    public static final CFPlugInProperty UnloadFunction = new CFPlugInProperty("UnloadFunctionValue");
    public static final CFPlugInProperty Factories = new CFPlugInProperty("FactoriesValue");
    public static final CFPlugInProperty Types = new CFPlugInProperty("TypesValue");
    
    private static CFPlugInProperty[] values = new CFPlugInProperty[] {DynamicRegistration, DynamicRegisterFunction, 
        UnloadFunction, Factories, Types};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CFPlugInProperty(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CFPlugInProperty valueOf(CFString value) {
        for (CFPlugInProperty v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFPlugInProperty/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="kCFPlugInDynamicRegistrationKey", optional=true)
    protected static native CFString DynamicRegistrationValue();
    @GlobalValue(symbol="kCFPlugInDynamicRegisterFunctionKey", optional=true)
    protected static native CFString DynamicRegisterFunctionValue();
    @GlobalValue(symbol="kCFPlugInUnloadFunctionKey", optional=true)
    protected static native CFString UnloadFunctionValue();
    @GlobalValue(symbol="kCFPlugInFactoriesKey", optional=true)
    protected static native CFString FactoriesValue();
    @GlobalValue(symbol="kCFPlugInTypesKey", optional=true)
    protected static native CFString TypesValue();
    /*</methods>*/
}
