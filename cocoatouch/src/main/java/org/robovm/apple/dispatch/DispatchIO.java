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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/DispatchIO/*</name>*/ 
    extends /*<extends>*/DispatchObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class DispatchIOPtr extends Ptr<DispatchIO, DispatchIOPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(DispatchIO.class); }/*</bind>*/
    /*<constants>*/
    public static final int STREAM = 0;
    public static final int RANDOM = 1;
    public static final int STOP = 0x1;
    public static final int STRICT_INTERVAL = 0x1;
    /*</constants>*/
    /*<constructors>*/
    protected DispatchIO() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="dispatch_io_create")
    public static native DispatchIO create(@MachineSizedUInt long type, int fd, DispatchQueue queue, ObjCBlock cleanup_handler);
    @Bridge(symbol="dispatch_io_create_with_path")
    public static native DispatchIO createWithPath(@MachineSizedUInt long type, BytePtr path, int oflag, short mode, DispatchQueue queue, ObjCBlock cleanup_handler);
    @Bridge(symbol="dispatch_io_create_with_io")
    public static native DispatchIO createWithIo(@MachineSizedUInt long type, DispatchIO io, DispatchQueue queue, ObjCBlock cleanup_handler);
    @Bridge(symbol="dispatch_io_read")
    public native void read(long offset, @MachineSizedUInt long length, DispatchQueue queue, FunctionPtr io_handler);
    @Bridge(symbol="dispatch_io_write")
    public native void write(long offset, DispatchData data, DispatchQueue queue, FunctionPtr io_handler);
    @Bridge(symbol="dispatch_io_close")
    public native void close(@MachineSizedUInt long flags);
    @Bridge(symbol="dispatch_io_barrier")
    public native void barrier(ObjCBlock barrier);
    @Bridge(symbol="dispatch_io_get_descriptor")
    public native int getDescriptor();
    @Bridge(symbol="dispatch_io_set_high_water")
    public native void setHighWater(@MachineSizedUInt long high_water);
    @Bridge(symbol="dispatch_io_set_low_water")
    public native void setLowWater(@MachineSizedUInt long low_water);
    @Bridge(symbol="dispatch_io_set_interval")
    public native void setInterval(long interval, @MachineSizedUInt long flags);
    /*</methods>*/
}
