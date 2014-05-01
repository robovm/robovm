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
    @GlobalValue(symbol="kCFPlugInDynamicRegistrationKey", optional=true)
    public static native CFString KeyDynamicRegistration();
    @GlobalValue(symbol="kCFPlugInDynamicRegisterFunctionKey", optional=true)
    public static native CFString KeyDynamicRegisterFunction();
    @GlobalValue(symbol="kCFPlugInUnloadFunctionKey", optional=true)
    public static native CFString KeyUnloadFunction();
    @GlobalValue(symbol="kCFPlugInFactoriesKey", optional=true)
    public static native CFString KeyFactories();
    @GlobalValue(symbol="kCFPlugInTypesKey", optional=true)
    public static native CFString KeyTypes();
    
    @Bridge(symbol="CFPlugInGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFPlugInCreate", optional=true)
    public static native CFBundle create(CFAllocator allocator, CFURL plugInURL);
    @Bridge(symbol="CFPlugInGetBundle", optional=true)
    public static native CFBundle getBundle(CFBundle plugIn);
    @Bridge(symbol="CFPlugInSetLoadOnDemand", optional=true)
    public static native void setLoadOnDemand(CFBundle plugIn, boolean flag);
    @Bridge(symbol="CFPlugInIsLoadOnDemand", optional=true)
    public static native boolean isLoadOnDemand(CFBundle plugIn);
    @Bridge(symbol="CFPlugInFindFactoriesForPlugInType", optional=true)
    public static native CFArray findFactoriesForPlugInType(CFUUID typeUUID);
    @Bridge(symbol="CFPlugInFindFactoriesForPlugInTypeInPlugIn", optional=true)
    public static native CFArray findFactoriesForPlugInTypeInPlugIn(CFUUID typeUUID, CFBundle plugIn);
    @Bridge(symbol="CFPlugInRegisterFactoryFunction", optional=true)
    public static native boolean registerFactoryFunction(CFUUID factoryUUID, FunctionPtr func);
    @Bridge(symbol="CFPlugInRegisterFactoryFunctionByName", optional=true)
    public static native boolean registerFactoryFunctionByName(CFUUID factoryUUID, CFBundle plugIn, CFString functionName);
    @Bridge(symbol="CFPlugInUnregisterFactory", optional=true)
    public static native boolean unregisterFactory(CFUUID factoryUUID);
    @Bridge(symbol="CFPlugInRegisterPlugInType", optional=true)
    public static native boolean registerPlugInType(CFUUID factoryUUID, CFUUID typeUUID);
    @Bridge(symbol="CFPlugInUnregisterPlugInType", optional=true)
    public static native boolean unregisterPlugInType(CFUUID factoryUUID, CFUUID typeUUID);
    @Bridge(symbol="CFPlugInAddInstanceForFactory", optional=true)
    public static native void addInstanceForFactory(CFUUID factoryID);
    @Bridge(symbol="CFPlugInRemoveInstanceForFactory", optional=true)
    public static native void removeInstanceForFactory(CFUUID factoryID);
    /*</methods>*/
}
