/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.coremidi;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/
import org.robovm.apple.coremidi.MIDIPort.MIDIPortPtr;

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMIDI")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDIClient/*</name>*/ 
    extends /*<extends>*/MIDIObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDIClientPtr extends Ptr<MIDIClient, MIDIClientPtr> {}/*</ptr>*/
    private static java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private static Map<Long, MIDINotifyProc> notifyProcs = new HashMap<Long, MIDINotifyProc>();
    private static final java.lang.reflect.Method cbNotifyProc;
    
    static {
        try {
            cbNotifyProc = MIDIClient.class.getDeclaredMethod("cbNotifyProc", MIDINotification.class, long.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<bind>*/static { Bro.bind(MIDIClient.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected MIDIClient() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbNotifyProc(MIDINotification message, @Pointer long refCon) {
        MIDINotifyProc callback = null;
        synchronized (notifyProcs) {
            callback = notifyProcs.get(refCon);
        }
        callback.notify(message);
    }
    public static MIDIClient create(String name, MIDINotifyProc notifyProc) {
        long refconId = MIDIClient.refconId.getAndIncrement();
        MIDIClientPtr ptr = new MIDIClientPtr();
        MIDIError err = create(name, new FunctionPtr(cbNotifyProc), refconId, ptr);
        if (err == MIDIError.No) {
            synchronized (notifyProcs) {
                notifyProcs.put(refconId, notifyProc);
            }
            return ptr.get();
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIClientCreate", optional=true)
    protected static native MIDIError create(String name, FunctionPtr notifyProc, @Pointer long notifyRefCon, MIDIClient.MIDIClientPtr outClient);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIClientDispose", optional=true)
    public native MIDIError dispose();
    /*</methods>*/
}
