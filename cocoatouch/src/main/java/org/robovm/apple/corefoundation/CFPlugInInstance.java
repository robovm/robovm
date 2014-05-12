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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFPlugInInstance/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFPlugInInstancePtr extends Ptr<CFPlugInInstance, CFPlugInInstancePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFPlugInInstance.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFPlugInInstance() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFPlugInInstanceCreate", optional=true)
    public static native VoidPtr create(CFAllocator allocator, CFUUID factoryUUID, CFUUID typeUUID);
    @Bridge(symbol="CFPlugInInstanceGetInterfaceFunctionTable", optional=true)
    public native boolean getInterfaceFunctionTable(CFString interfaceName, VoidPtr.VoidPtrPtr ftbl);
    @Bridge(symbol="CFPlugInInstanceGetFactoryName", optional=true)
    public native CFString getFactoryName();
    @Bridge(symbol="CFPlugInInstanceGetInstanceData", optional=true)
    public native VoidPtr getInstanceData();
    @Bridge(symbol="CFPlugInInstanceGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFPlugInInstanceCreateWithInstanceDataSize", optional=true)
    public static native CFPlugInInstance createWithInstanceDataSize(CFAllocator allocator, @MachineSizedSInt long instanceDataSize, FunctionPtr deallocateInstanceFunction, CFString factoryName, FunctionPtr getInterfaceFunction);
    /*</methods>*/
}
