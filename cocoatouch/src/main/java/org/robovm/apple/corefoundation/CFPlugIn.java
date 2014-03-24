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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFPlugIn/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFPlugIn.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCFPlugInDynamicRegistrationKey")
    public static native CFString KeyDynamicRegistration();
    @GlobalValue(symbol="kCFPlugInDynamicRegisterFunctionKey")
    public static native CFString KeyDynamicRegisterFunction();
    @GlobalValue(symbol="kCFPlugInUnloadFunctionKey")
    public static native CFString KeyUnloadFunction();
    @GlobalValue(symbol="kCFPlugInFactoriesKey")
    public static native CFString KeyFactories();
    @GlobalValue(symbol="kCFPlugInTypesKey")
    public static native CFString KeyTypes();
    
    @Bridge(symbol="CFPlugInGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFPlugInCreate")
    public static native CFBundle create(CFAllocator allocator, CFURL plugInURL);
    @Bridge(symbol="CFPlugInGetBundle")
    public static native CFBundle getBundle(CFBundle plugIn);
    @Bridge(symbol="CFPlugInSetLoadOnDemand")
    public static native void setLoadOnDemand(CFBundle plugIn, boolean flag);
    @Bridge(symbol="CFPlugInIsLoadOnDemand")
    public static native boolean isLoadOnDemand(CFBundle plugIn);
    @Bridge(symbol="CFPlugInFindFactoriesForPlugInType")
    public static native CFArray findFactoriesForPlugInType(CFUUID typeUUID);
    @Bridge(symbol="CFPlugInFindFactoriesForPlugInTypeInPlugIn")
    public static native CFArray findFactoriesForPlugInTypeInPlugIn(CFUUID typeUUID, CFBundle plugIn);
    @Bridge(symbol="CFPlugInRegisterFactoryFunction")
    public static native boolean registerFactoryFunction(CFUUID factoryUUID, FunctionPtr func);
    @Bridge(symbol="CFPlugInRegisterFactoryFunctionByName")
    public static native boolean registerFactoryFunctionByName(CFUUID factoryUUID, CFBundle plugIn, CFString functionName);
    @Bridge(symbol="CFPlugInUnregisterFactory")
    public static native boolean unregisterFactory(CFUUID factoryUUID);
    @Bridge(symbol="CFPlugInRegisterPlugInType")
    public static native boolean registerPlugInType(CFUUID factoryUUID, CFUUID typeUUID);
    @Bridge(symbol="CFPlugInUnregisterPlugInType")
    public static native boolean unregisterPlugInType(CFUUID factoryUUID, CFUUID typeUUID);
    @Bridge(symbol="CFPlugInAddInstanceForFactory")
    public static native void addInstanceForFactory(CFUUID factoryID);
    @Bridge(symbol="CFPlugInRemoveInstanceForFactory")
    public static native void removeInstanceForFactory(CFUUID factoryID);
    /*</methods>*/
}
