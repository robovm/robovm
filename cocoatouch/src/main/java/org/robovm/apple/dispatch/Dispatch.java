/*
 * Copyright (C) 2014 Trillian AB
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
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("dispatch")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/Dispatch/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(Dispatch.class); }/*</bind>*/
    /*<constants>*/
    public static final int API_VERSION = 20130725;
    public static final long TIME_NOW = 0L;
    public static final long TIME_FOREVER = ~0L;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="dispatch_time")
    public static native long time(long when, long delta);
    @Bridge(symbol="dispatch_walltime")
    public static native long walltime(timespec when, long delta);
    @Bridge(symbol="dispatch_main")
    public static native void main();
    @Bridge(symbol="dispatch_get_specific")
    public static native VoidPtr getSpecific(VoidPtr key);
    @Bridge(symbol="dispatch_once")
    public static native void once(MachineSizedSIntPtr predicate, ObjCBlock block);
    @Bridge(symbol="dispatch_once_f")
    public static native void onceF(MachineSizedSIntPtr predicate, VoidPtr context, FunctionPtr function);
    @Bridge(symbol="dispatch_read")
    public static native void read(int fd, @MachineSizedUInt long length, DispatchQueue queue, ObjCBlock handler);
    @Bridge(symbol="dispatch_write")
    public static native void write(int fd, DispatchData data, DispatchQueue queue, ObjCBlock handler);
    /*</methods>*/
}
