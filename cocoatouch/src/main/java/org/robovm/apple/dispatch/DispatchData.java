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
package org.robovm.apple.dispatch;

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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("dispatch")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/DispatchData/*</name>*/ 
    extends /*<extends>*/DispatchObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class DispatchDataPtr extends Ptr<DispatchData, DispatchDataPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(DispatchData.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected DispatchData() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="_dispatch_data_empty")
    public static native DispatchData Empty();
    @GlobalValue(symbol="_dispatch_data_empty")
    public static native void Empty(DispatchData v);
    @GlobalValue(symbol="_dispatch_data_destructor_free")
    public static native DispatchData DestructorFree();
    @GlobalValue(symbol="_dispatch_data_destructor_munmap")
    public static native DispatchData DestructorMunmap();
    
    @Bridge(symbol="dispatch_data_create")
    public static native DispatchData create(VoidPtr buffer, @MachineSizedUInt long size, DispatchQueue queue, @Block Runnable destructor);
    @Bridge(symbol="dispatch_data_get_size")
    public native @MachineSizedUInt long getSize();
    @Bridge(symbol="dispatch_data_create_map")
    public native DispatchData createMap(VoidPtr.VoidPtrPtr buffer_ptr, MachineSizedUIntPtr size_ptr);
    @Bridge(symbol="dispatch_data_create_concat")
    public native DispatchData createConcat(DispatchData data2);
    @Bridge(symbol="dispatch_data_create_subrange")
    public native DispatchData createSubrange(@MachineSizedUInt long offset, @MachineSizedUInt long length);
    @Bridge(symbol="dispatch_data_apply")
    public native boolean apply(FunctionPtr applier);
    @Bridge(symbol="dispatch_data_copy_region")
    public native DispatchData copyRegion(@MachineSizedUInt long location, MachineSizedUIntPtr offset_ptr);
    /*</methods>*/
}
