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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/Dispatch/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(Dispatch.class); }/*</bind>*/
    /*<constants>*/
    public static final int API_VERSION = 20141121;
    protected static final long TIME_NOW = 0L;
    protected static final long TIME_FOREVER = ~0L;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static void once(Runnable block) {
        MachineSizedSIntPtr ptr = new MachineSizedSIntPtr();
        once(ptr, block);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_time", optional=true)
    protected static native long time(long when, long delta);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_walltime", optional=true)
    protected static native long walltime(timespec when, long delta);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_main", optional=true)
    public static native void main();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="dispatch_once", optional=true)
    protected static native void once(MachineSizedSIntPtr predicate, @Block Runnable block);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="dispatch_read", optional=true)
    public static native void read(int fd, @MachineSizedUInt long length, DispatchQueue queue, @Block VoidBlock2<DispatchData, Integer> handler);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="dispatch_write", optional=true)
    public static native void write(int fd, DispatchData data, DispatchQueue queue, @Block VoidBlock2<DispatchData, Integer> handler);
    /*</methods>*/
}
