/*
 * Copyright (C) 2013-2015 RoboVM AB
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

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("System")/*</annotations>*/
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
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="_dispatch_data_empty", optional=true, dereference=false)
    public static native DispatchData Empty();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="_dispatch_data_destructor_free", optional=true, dereference=false)
    public static native DispatchData DestructorFree();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="_dispatch_data_destructor_munmap", optional=true, dereference=false)
    public static native DispatchData DestructorMunmap();
    
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="dispatch_data_create", optional=true)
    public static native DispatchData create(VoidPtr buffer, @MachineSizedUInt long size, DispatchQueue queue, @Block Runnable destructor);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="dispatch_data_get_size", optional=true)
    public native @MachineSizedUInt long getSize();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="dispatch_data_create_map", optional=true)
    public native DispatchData createMap(VoidPtr.VoidPtrPtr buffer_ptr, MachineSizedUIntPtr size_ptr);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="dispatch_data_create_concat", optional=true)
    public native DispatchData createConcat(DispatchData data2);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="dispatch_data_create_subrange", optional=true)
    public native DispatchData createSubrange(@MachineSizedUInt long offset, @MachineSizedUInt long length);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="dispatch_data_apply", optional=true)
    public native boolean apply(@Block("(,@MachineSizedUInt,,@MachineSizedUInt)") VoidBlock4<DispatchData, Long, VoidPtr, Long> applier);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="dispatch_data_copy_region", optional=true)
    public native DispatchData copyRegion(@MachineSizedUInt long location, MachineSizedUIntPtr offset_ptr);
    /*</methods>*/
}
